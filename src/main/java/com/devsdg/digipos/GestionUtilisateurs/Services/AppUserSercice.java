package com.devsdg.digipos.GestionUtilisateurs.Services;

import com.devsdg.digipos.GestionUtilisateurs.DTO.AppUserDTO;
import com.devsdg.digipos.GestionUtilisateurs.Metiers.AppUserMetier;
import com.devsdg.digipos.GestionUtilisateurs.Models.*;
import com.devsdg.digipos.GestionUtilisateurs.Repositories.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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

    @Override
    public AppUserDTO saveUser(AppUserDTO appUserDTO) {
        String hashpw=bCryptPasswordEncoder.encode(appUserDTO.getPassword());
        appUserDTO.setPassword(hashpw);

        if(appUserDTO.isClient()){
            ClientPOS clientPOS = new ClientPOS();

            BeanUtils.copyProperties(appUserDTO, clientPOS);
            clientPOS = clientRepository.save(clientPOS);
            clientPOS.setClient(true);

            appRoleService.addRoleToUser(clientPOS.getId_user(), "CLIENT");

            BeanUtils.copyProperties(clientPOS, appUserDTO);
        } else if(appUserDTO.isAdmin()){
            Admin admin = new Admin();

            BeanUtils.copyProperties(appUserDTO, admin);
            admin = adminRepository.save(admin);
            admin.setStaff(true);

            appRoleService.addRoleToUser(admin.getId_user(), "ADMIN");
            appRoleService.addRoleToUser(admin.getId_user(), "PROPRIETAIRE");
            appRoleService.addRoleToUser(admin.getId_user(), "VENDEUR");
            appRoleService.addRoleToUser(admin.getId_user(), "SUPPORT");
            appRoleService.addRoleToUser(admin.getId_user(), "CLIENT");

            BeanUtils.copyProperties(admin, appUserDTO);
        } else if (appUserDTO.isSupport()){
            Support support = new Support();

            BeanUtils.copyProperties(appUserDTO, support);
            support = supportRepository.save(support);
            support.setStaff(true);

            appRoleService.addRoleToUser(support.getId_user(), "PROPRIETAIRE");
            appRoleService.addRoleToUser(support.getId_user(), "VENDEUR");
            appRoleService.addRoleToUser(support.getId_user(), "SUPPORT");
            appRoleService.addRoleToUser(support.getId_user(), "CLIENT");

            BeanUtils.copyProperties(support, appUserDTO);
        } else if(appUserDTO.isProprietaire()){
            Proprietaire proprietaire = new Proprietaire();

            BeanUtils.copyProperties(appUserDTO, proprietaire);
            proprietaire = proprietaireRepository.save(proprietaire);
            proprietaire.setStaff(true);

            appRoleService.addRoleToUser(proprietaire.getId_user(), "PROPRIETAIRE");
            appRoleService.addRoleToUser(proprietaire.getId_user(), "VENDEUR");
            appRoleService.addRoleToUser(proprietaire.getId_user(), "CLIENT");

            BeanUtils.copyProperties(proprietaire, appUserDTO);
        } else if(appUserDTO.isVendeur()){
            Vendeur vendeur = new Vendeur();

            BeanUtils.copyProperties(appUserDTO, vendeur);
            vendeur = vendeurRepository.save(vendeur);
            vendeur.setStaff(true);

            appRoleService.addRoleToUser(vendeur.getId_user(), "VENDEUR");
            appRoleService.addRoleToUser(vendeur.getId_user(), "CLIENT");

            BeanUtils.copyProperties(vendeur, appUserDTO);
        } else if(!appUserDTO.isAdmin() && !appUserDTO.isSupport() && !appUserDTO.isProprietaire()
                && !appUserDTO.isVendeur() && !appUserDTO.isClient()){
            AppUser appUser = new AppUser();

            BeanUtils.copyProperties(appUserDTO, appUser);
            appUser = appUserRepository.save(appUser);
            appUser.setStaff(true);

            BeanUtils.copyProperties(appUser, appUserDTO);
        }

        return appUserDTO;
    }

    @Override
    public AppUserDTO updateUser(Long id_user, AppUserDTO appUserDTO) {

        if(appUserDTO.isClient()){
            ClientPOS clientPOS = clientRepository.getOne(id_user);
            this.updateProccess(clientPOS, appUserDTO);
            return this.permuteAppUserToAppUserDTO(clientPOS);
        } else if(appUserDTO.isAdmin()){
            Admin admin = adminRepository.getOne(id_user);
            this.updateProccess(admin, appUserDTO);
            return this.permuteAppUserToAppUserDTO(admin);
        } else if (appUserDTO.isSupport()){
            Support support = supportRepository.getOne(id_user);
            this.updateProccess(support, appUserDTO);
            return this.permuteAppUserToAppUserDTO(support);
        } else if(appUserDTO.isProprietaire()){
            Proprietaire proprietaire = proprietaireRepository.getOne(id_user);
            this.updateProccess(proprietaire, appUserDTO);
            return this.permuteAppUserToAppUserDTO(proprietaire);
        } else if(appUserDTO.isVendeur()){
            Vendeur vendeur = vendeurRepository.getOne(id_user);
            this.updateProccess(vendeur, appUserDTO);
            return this.permuteAppUserToAppUserDTO(vendeur);
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
        if (!appUserDTO.getUsername().equalsIgnoreCase("")) {
            user.setUsername(appUserDTO.getUsername());
        }
        user.setFirstName(appUserDTO.getFirstName());
        user.setLastName(appUserDTO.getLastName());
        user.setPhotouser(appUserDTO.getPhotouser());
        user.setCni(appUserDTO.getCni());
    }

    @Override
    public AppUserDTO getUser(Long id_user) {
        return this.permuteAppUserToAppUserDTO(appUserRepository.getOne(id_user));
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
    public boolean activeUser(Long id_user) {
        AppUser user = appUserRepository.getOne(id_user);
        if(!user.isAtiveuser()){
            user.setAtiveuser(true);
            return true;
        } else
            return user.isAtiveuser();
    }

    @Override
    public boolean desactiveUser(Long id_user) {
        AppUser user = appUserRepository.getOne(id_user);
        if(!user.isAtiveuser()){
            user.setAtiveuser(false);
            return true;
        } else
            return user.isAtiveuser();
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
}
