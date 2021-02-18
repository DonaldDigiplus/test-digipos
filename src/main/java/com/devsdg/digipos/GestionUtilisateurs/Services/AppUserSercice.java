package com.devsdg.digipos.GestionUtilisateurs.Services;

import com.devsdg.digipos.GestionUtilisateurs.DTO.AppUserDTO;
import com.devsdg.digipos.GestionUtilisateurs.Metiers.AppUserMetier;
import com.devsdg.digipos.GestionUtilisateurs.Models.*;
import com.devsdg.digipos.GestionUtilisateurs.Repositories.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        } else if (appUserDTO.isSupport()){
            Support support = new Support();

            BeanUtils.copyProperties(appUserDTO, support);
            support = supportRepository.save(support);
            support.setStaff(true);

            appRoleService.addRoleToUser(support.getId_user(), "PROPRIETAIRE");
            appRoleService.addRoleToUser(support.getId_user(), "VENDEUR");
            appRoleService.addRoleToUser(support.getId_user(), "SUPPORT");
            appRoleService.addRoleToUser(support.getId_user(), "CLIENT");
        } else if(appUserDTO.isProprietaire()){
            Proprietaire proprietaire = new Proprietaire();

            BeanUtils.copyProperties(appUserDTO, proprietaire);
            proprietaire = proprietaireRepository.save(proprietaire);
            proprietaire.setStaff(true);

            appRoleService.addRoleToUser(proprietaire.getId_user(), "PROPRIETAIRE");
            appRoleService.addRoleToUser(proprietaire.getId_user(), "VENDEUR");
            appRoleService.addRoleToUser(proprietaire.getId_user(), "CLIENT");
        } else if(appUserDTO.isVendeur()){
            Vendeur vendeur = new Vendeur();

            BeanUtils.copyProperties(appUserDTO, vendeur);
            vendeur = vendeurRepository.save(vendeur);
            vendeur.setStaff(true);

            appRoleService.addRoleToUser(vendeur.getId_user(), "VENDEUR");
            appRoleService.addRoleToUser(vendeur.getId_user(), "CLIENT");
        } else if(!appUserDTO.isAdmin() && !appUserDTO.isSupport() && !appUserDTO.isProprietaire()
                && !appUserDTO.isVendeur() && !appUserDTO.isClient()){
            AppUser appUser = new AppUser();

            BeanUtils.copyProperties(appUserDTO, appUser);
            appUser = appUserRepository.save(appUser);
            appUser.setStaff(true);

            appRoleService.addRoleToUser(appUser.getId_user(), "SUPERADMIN");
            appRoleService.addRoleToUser(appUser.getId_user(), "ADMIN");
            appRoleService.addRoleToUser(appUser.getId_user(), "PROPRIETAIRE");
            appRoleService.addRoleToUser(appUser.getId_user(), "VENDEUR");
            appRoleService.addRoleToUser(appUser.getId_user(), "SUPPORT");
            appRoleService.addRoleToUser(appUser.getId_user(), "CLIENT");
        }

        return appUserDTO;
    }

    @Override
    public AppUserDTO updateUser(AppUserDTO appUserDTO) {
        return null;
    }

    @Override
    public AppUserDTO getUser(Long id_user) {
        return null;
    }

    @Override
    public AppUser findUserById(Long id_user) {
        return null;
    }

    @Override
    public AppUser getUserByLogin(String login) {
        return appUserRepository.findByUsernameOrEmailOrPhone(login);
    }

    @Override
    public Page<AppUserDTO> getAllUsers(Pageable pageable) {
        return null;
    }

    @Override
    public boolean activeUser(Long id_user) {
        return false;
    }

    @Override
    public boolean desactiveUser(Long id_user) {
        return false;
    }
}
