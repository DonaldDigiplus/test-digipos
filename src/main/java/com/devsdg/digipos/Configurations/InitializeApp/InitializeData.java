package com.devsdg.digipos.Configurations.InitializeApp;

import com.devsdg.digipos.GestionUtilisateurs.DTO.AppRoleDTO;
import com.devsdg.digipos.GestionUtilisateurs.DTO.AppUserDTO;
import com.devsdg.digipos.GestionUtilisateurs.Metiers.AppRoleMetier;
import com.devsdg.digipos.GestionUtilisateurs.Metiers.AppUserMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class InitializeData {
    @Autowired
    private AppUserMetier  appUserMetier;
    @Autowired
    private AppRoleMetier appRoleMetier;

    @Bean
    void initializeSuperAdmin(){
        AppUserDTO user= appUserMetier.saveUser(new AppUserDTO("root","1234","digiplusproject@gmail.com","6"));

        //Creation des role
        appRoleMetier.saveRole(new AppRoleDTO("SUPERADMIN"));
        appRoleMetier.saveRole(new AppRoleDTO("ADMIN"));
        appRoleMetier.saveRole(new AppRoleDTO("CLIENT"));
        appRoleMetier.saveRole(new AppRoleDTO("VENDEUR"));
        appRoleMetier.saveRole(new AppRoleDTO("PROPRIETAIRE"));
        //Affectation des roles au superadmin
        appRoleMetier.addRoleToUser(user.getId_user(),"CLIENT");
        appRoleMetier.addRoleToUser(user.getId_user(),"ADMIN");
        appRoleMetier.addRoleToUser(user.getId_user(),"SUPERADMIN");
        appRoleMetier.addRoleToUser(user.getId_user(),"VENDEUR");
        appRoleMetier.addRoleToUser(user.getId_user(),"PROPRIETAIRE");
    }

}
