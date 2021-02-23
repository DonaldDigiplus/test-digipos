package com.devsdg.digipos.Configurations.InitializeApp;

import com.devsdg.digipos.GestionErreurs.ErrorMessages;
import com.devsdg.digipos.GestionUtilisateurs.DTO.AppRoleDTO;
import com.devsdg.digipos.GestionUtilisateurs.DTO.AppUserDTO;
import com.devsdg.digipos.GestionUtilisateurs.Metiers.AppRoleMetier;
import com.devsdg.digipos.GestionUtilisateurs.Metiers.AppUserMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class InitializeData {
    @Autowired
    private AppUserMetier appUserMetier;
    @Autowired
    private AppRoleMetier appRoleMetier;

    @Bean
    void initializeSuperAdmin() {
      /*  appRoleMetier.saveRole(new AppRoleDTO("ADMIN"));
        appRoleMetier.saveRole(new AppRoleDTO("SUPPORT"));
        appRoleMetier.saveRole(new AppRoleDTO("PROPRIETAIRE"));
        appRoleMetier.saveRole(new AppRoleDTO("VENDEUR"));
        appRoleMetier.saveRole(new AppRoleDTO("CLIENT"));
        AppUserDTO user = appUserMetier.saveUser(new AppUserDTO("root", "1234", "digiplusproject@gmail.com", "6", true));
        if (user == null) {
            throw new ErrorMessages("L'utilisateur n'a pas ete cree.", HttpStatus.BAD_REQUEST);
        }*/
    }

}
