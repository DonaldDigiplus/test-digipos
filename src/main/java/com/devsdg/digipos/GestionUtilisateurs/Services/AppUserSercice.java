package com.devsdg.digipos.GestionUtilisateurs.Services;

import com.devsdg.digipos.Commons.GeneratorTools.Exceptions.AlgorithmException;
import com.devsdg.digipos.Commons.GeneratorTools.Voucher.VoucherGenerator;
import com.devsdg.digipos.GestionBoutiques.Metiers.BoutiqueMetier;
import com.devsdg.digipos.GestionBoutiques.Models.Boutique;
import com.devsdg.digipos.GestionEmail.Format.EmailModel;
import com.devsdg.digipos.GestionEmail.Service.MyAuthentication;
import com.devsdg.digipos.GestionErreurs.ErrorMessages;
import com.devsdg.digipos.GestionUtilisateurs.DTO.ActiveVendeurDTO;
import com.devsdg.digipos.GestionUtilisateurs.DTO.AppUserDTO;
import com.devsdg.digipos.GestionUtilisateurs.Metiers.AccountMetier;
import com.devsdg.digipos.GestionUtilisateurs.Metiers.AppUserMetier;
import com.devsdg.digipos.GestionUtilisateurs.Models.*;
import com.devsdg.digipos.GestionUtilisateurs.Repositories.*;
import org.junit.Assert;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Transactional
@Service
public class AppUserSercice implements AppUserMetier {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private AppUserRepository appUserRepository;
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private ProprietaireRepository proprietaireRepository;
    @Autowired
    private VendeurRepository vendeurRepository;
    @Autowired
    private SupportRepository supportRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private AppRoleService appRoleService;
    @Autowired
    private BoutiqueMetier boutiqueMetier;
    @Autowired
    private MyAuthentication myAuthentication;
    @Autowired
    private AccountMetier accountMetier;
    @Override
    public AppUser saveAppuser(AppUser appUser) {
        appUser.setDate(new Date());
        return appUserRepository.save(appUser);
    }

    @Override
    public AppUserDTO saveUser(AppUserDTO appUserDTO) {
        String password_to_send_user="";
        if (appUserDTO.getPassword()!=null){
            String hashpw=bCryptPasswordEncoder.encode(appUserDTO.getPassword());
            appUserDTO.setPassword(hashpw);
        }else {
            password_to_send_user=generatePassword();
            String hashpw=bCryptPasswordEncoder.encode(password_to_send_user);
            appUserDTO.setPassword(hashpw);
        }

        if(appUserDTO.isClient()){
            ClientPOS clientPOS = new ClientPOS();

            BeanUtils.copyProperties(appUserDTO, clientPOS);
            clientPOS.setClient(true);
            clientPOS.setDate(new Date());
            clientPOS = clientRepository.save(clientPOS);

            appRoleService.addRoleToUser(clientPOS.getId_user(), "CLIENT");

            BeanUtils.copyProperties(clientPOS, appUserDTO);
        } else if(appUserDTO.isAdmin()){
            Admin admin = new Admin();

            BeanUtils.copyProperties(appUserDTO, admin);
            admin.setStaff(true);
            admin.setDate(new Date());
            admin = adminRepository.save(admin);

            appRoleService.addRoleToUser(admin.getId_user(), "ADMIN");
            appRoleService.addRoleToUser(admin.getId_user(), "PROPRIETAIRE");
            appRoleService.addRoleToUser(admin.getId_user(), "VENDEUR");
            appRoleService.addRoleToUser(admin.getId_user(), "SUPPORT");
            appRoleService.addRoleToUser(admin.getId_user(), "CLIENT");

            BeanUtils.copyProperties(admin, appUserDTO);
            accountMetier.requestPasswordReset(admin.getEmail());
        } else if (appUserDTO.isSupport()){
            Support support = new Support();

            BeanUtils.copyProperties(appUserDTO, support);
            support.setStaff(true);
            support.setDate(new Date());
            support = supportRepository.save(support);

            appRoleService.addRoleToUser(support.getId_user(), "PROPRIETAIRE");
            appRoleService.addRoleToUser(support.getId_user(), "VENDEUR");
            appRoleService.addRoleToUser(support.getId_user(), "SUPPORT");
            appRoleService.addRoleToUser(support.getId_user(), "CLIENT");

            BeanUtils.copyProperties(support, appUserDTO);

            accountMetier.requestPasswordReset(support.getEmail());
        } else if(appUserDTO.isProprietaire()){
            Proprietaire proprietaire = new Proprietaire();

            BeanUtils.copyProperties(appUserDTO, proprietaire);
            proprietaire.setStaff(true);
            proprietaire.setDate(new Date());
            proprietaire = proprietaireRepository.save(proprietaire);

            appRoleService.addRoleToUser(proprietaire.getId_user(), "PROPRIETAIRE");
            appRoleService.addRoleToUser(proprietaire.getId_user(), "VENDEUR");
            appRoleService.addRoleToUser(proprietaire.getId_user(), "CLIENT");

            BeanUtils.copyProperties(proprietaire, appUserDTO);
            accountMetier.requestPasswordReset(proprietaire.getEmail());
        } else if(appUserDTO.isVendeur()){
            Boutique boutique = boutiqueMetier.getBoutiqueByNomBoutique(appUserDTO.getNomBoutique());
            if(boutique!=null){
                Vendeur vendeur = new Vendeur();

                BeanUtils.copyProperties(appUserDTO, vendeur);

                vendeur.setBoutique(boutique);
                vendeur.setStaff(true);
                vendeur.setDate(new Date());
                vendeur = vendeurRepository.save(vendeur);

                boutique.getVendeurs().add(vendeur);

                appRoleService.addRoleToUser(vendeur.getId_user(), "VENDEUR");
                appRoleService.addRoleToUser(vendeur.getId_user(), "CLIENT");


                BeanUtils.copyProperties(vendeur, appUserDTO);
                accountMetier.requestPasswordReset(vendeur.getEmail());
            } else {
                throw new ErrorMessages("Le nom de la boutique est invalide", HttpStatus.NOT_FOUND);
            }

        } else if(!appUserDTO.isAdmin() && !appUserDTO.isSupport() && !appUserDTO.isProprietaire()
                && !appUserDTO.isVendeur() && !appUserDTO.isClient()){
            AppUser appUser = new AppUser();

            BeanUtils.copyProperties(appUserDTO, appUser);
            appUser.setStaff(true);
            appUser.setDate(new Date());
            appUser = appUserRepository.save(appUser);
            accountMetier.requestPasswordReset(appUserDTO.getEmail());
            BeanUtils.copyProperties(appUser, appUserDTO);
        }
        //myAuthentication.sendMail(appUserDTO.getEmail(), EmailModel.firstConnexion(appUserDTO.getUsername(),password_to_send_user),"Nouveau compte");


        return appUserDTO;
    }

    @Override
    public AppUserDTO updateUser(AppUserDTO appUserDTO) {
        if(appUserDTO.isClient()){
            ClientPOS clientPOS = clientRepository.getOne(appUserDTO.getId_user());
            this.updateProccess(clientPOS, appUserDTO);
            return permuteAppUserToAppUserDTO(clientPOS);
        } else if(appUserDTO.isAdmin()){
            Admin admin = adminRepository.getOne(appUserDTO.getId_user());
            this.updateProccess(admin, appUserDTO);
            return permuteAppUserToAppUserDTO(admin);
        } else if (appUserDTO.isSupport()){
            Support support = supportRepository.getOne(appUserDTO.getId_user());
            this.updateProccess(support, appUserDTO);
            return permuteAppUserToAppUserDTO(support);
        } else if(appUserDTO.isProprietaire()){
            Proprietaire proprietaire = proprietaireRepository.getOne(appUserDTO.getId_user());
            this.updateProccess(proprietaire, appUserDTO);
            return permuteAppUserToAppUserDTO(proprietaire);
        } else if(appUserDTO.isVendeur()){
            Vendeur vendeur = vendeurRepository.getOne(appUserDTO.getId_user());
            this.updateProccess(vendeur, appUserDTO);
            return permuteAppUserToAppUserDTO(vendeur);
        }
        return null;
    }

    void updateProccess (AppUser user, AppUserDTO appUserDTO){
        if (!appUserDTO.getPhone().equalsIgnoreCase("")) {
            user.setPhone(appUserDTO.getPhone());
        }
        if (!appUserDTO.getEmail().equalsIgnoreCase("")) {
            user.setEmail(appUserDTO.getEmail());
        }
        user.setNomcomplet(appUserDTO.getNomcomplet());
        user.setPhotouser(appUserDTO.getPhotouser());
        user.setCni(appUserDTO.getCni());
    }

    @Override
    public AppUserDTO getUser(Long id_user) {
        return permuteAppUserToAppUserDTO(appUserRepository.getOne(id_user));
    }

    @Override
    public AppUser findUserById(Long id_user) {
        return appUserRepository.getOne(id_user);
    }

    @Override
    public AppUser getUserByLogin(String login) {
        return appUserRepository.findByUsernameOrEmailOrPhone(login);
    }

    @Override
    public Page<AppUser> getAllUsers(Pageable pageable) {
        return appUserRepository.findAll(pageable);
    }

    @Override
    public Page<Admin> getAllAdmins(Pageable pageable) {
        return adminRepository.findAll(pageable);
    }

    @Override
    public Page<Admin> getAllAdminsIsActive(Pageable pageable) {
        return adminRepository.findAllByAtiveuserIsTrue(pageable);
    }

    @Override
    public Page<Support> getAllSupports(Pageable pageable) {
        return supportRepository.findAll(pageable);
    }

    @Override
    public Page<Support> getAllSupportsIsActive(Pageable pageable) {
        return supportRepository.findAllByAtiveuserIsTrue(pageable);
    }

    @Override
    public Page<Proprietaire> getAllProprietaires(Pageable pageable) {
        return proprietaireRepository.findAll(pageable);
    }

    @Override
    public Page<Proprietaire> getAllProprietairesIsActive(Pageable pageable) {
        return proprietaireRepository.findAllByAtiveuserIsTrue(pageable);
    }

    @Override
    public Page<Vendeur> getAllVendeurs(Pageable pageable) {
        return vendeurRepository.findAll(pageable);
    }

    @Override
    public Page<Vendeur> getAllVendeursIsActive(Pageable pageable) {
        return vendeurRepository.findAllByAtiveuserIsTrue(pageable);
    }

    @Override
    public Page<ClientPOS> getAllClientPOSs(Pageable pageable) {
        return clientRepository.findAll(pageable);
    }

    @Override
    public Page<ClientPOS> getAllClientPOSsIsActive(Pageable pageable) {
        return clientRepository.findAllByAtiveuserIsTrue(pageable);
    }

    @Override
    public Page<AppUser> findAllByStaffIsTrue(Pageable pageable) {
        return appUserRepository.findAllByStaffIsTrue(pageable);
    }

    @Override
    public boolean active_and_desactive_user(Long id_user) {
        AppUser user = appUserRepository.getOne(id_user);

        Assert.assertNotNull(user);
        user.setAtiveuser(!user.isAtiveuser());

        return user.isAtiveuser();
    }

    @Override
    public List<ClientPOS> findAllByUsernameLike(String username) {
        return clientRepository.findAllByUsernameLike("%"+username+"%");
    }

    @Override
    public List<AppUser> findAllByUsernameLikeAndStaffIsTrue(String username) {
        return appUserRepository.findAllByUsernameLikeAndStaffIsTrue("%"+username+"%");
    }

    @Override
    public AppUserDTO active_and_desactive_vendeur(ActiveVendeurDTO activeVendeurDTO) {
        Vendeur vendeur = vendeurRepository.getOne(activeVendeurDTO.getIdVendeur());

        if(activeVendeurDTO.isActive_desactive()){
            vendeur.setActivevendeur(true);
            vendeur.setStaff(true);
            vendeur.setClient(false);

            Boutique boutique = boutiqueMetier.getBoutiqueByNomBoutique(activeVendeurDTO.getNomBoutique());
            if(boutique!=null){
                vendeur.setBoutique(boutique);
            } else
                throw new ErrorMessages("Impossible de continuer car la boutique choisi n'existe pas", HttpStatus.NOT_FOUND);

            appRoleService.addRoleToUser(vendeur.getId_user(), "VENDEUR");
        } else {
            //Suppression des fonction du vendeur
            vendeur.setStaff(false);
            vendeur.setBoutique(null);
            vendeur.setActivevendeur(false);
            appRoleService.deleteRoleToUser(vendeur.getId_user());

            //Definition des fonctions d'un client
            vendeur.setClient(true);
        }
        appRoleService.addRoleToUser(vendeur.getId_user(), "CLIENT");
        return permuteAppUserToAppUserDTO(vendeur);
    }

    static AppUserDTO permuteAppUserToAppUserDTO(AppUser user){
        AppUserDTO appUserDTO = new AppUserDTO();
        BeanUtils.copyProperties(user, appUserDTO);
        return appUserDTO;
    }

    static List<AppUserDTO> permuteAppUserListToAppUserDTOList(List<AppUser> users){
        List<AppUserDTO> appUserDTOList = new ArrayList<>();
        users.forEach(r->{
            AppUserDTO appUserDTO = new AppUserDTO();
            BeanUtils.copyProperties(r, appUserDTO);
            appUserDTOList.add(appUserDTO);
        });
        return appUserDTOList;
    }

    static String generatePassword(){
        try {
            VoucherGenerator voucherGenerator = VoucherGenerator.getVoucherGenerator(2);
            voucherGenerator.generate(1);
            if(!voucherGenerator.getCodes().isEmpty()){
                return voucherGenerator.getCodes().get(0);
            } else
                return "";
        } catch (AlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
}
