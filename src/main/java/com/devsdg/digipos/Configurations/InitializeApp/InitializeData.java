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


    @Bean
    void initializeSuperAdmin() {
    }

}
