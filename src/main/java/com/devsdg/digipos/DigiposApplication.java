package com.devsdg.digipos;

import com.devsdg.digipos.GestionErreurs.ErrorMessages;
import com.devsdg.digipos.GestionUtilisateurs.DTO.AppRoleDTO;
import com.devsdg.digipos.GestionUtilisateurs.DTO.AppUserDTO;
import com.devsdg.digipos.GestionUtilisateurs.Metiers.AppRoleMetier;
import com.devsdg.digipos.GestionUtilisateurs.Metiers.AppUserMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class DigiposApplication implements CommandLineRunner {
    @Autowired
    private AppUserMetier appUserMetier;
    @Autowired
    private AppRoleMetier appRoleMetier;

    @Bean
    public BCryptPasswordEncoder getBcp() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SpringApplicationContext applicationContext() {
        return new SpringApplicationContext();
    }

    public static void main(String[] args) {
        SpringApplication.run(DigiposApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
		appRoleMetier.saveRole(new AppRoleDTO("ADMIN"));
		appRoleMetier.saveRole(new AppRoleDTO("SUPPORT"));
		appRoleMetier.saveRole(new AppRoleDTO("PROPRIETAIRE"));
		appRoleMetier.saveRole(new AppRoleDTO("VENDEUR"));
		appRoleMetier.saveRole(new AppRoleDTO("CLIENT"));
        AppUserDTO user = appUserMetier.saveUser(new AppUserDTO("root", "1234", "digiplusproject@gmail.com", "6", true));
        if (user != null) {
            //Creation des role

            //Affectation des roles au superadmin
           /* appRoleMetier.addRoleToUser(user.getId_user(),"CLIENT");
            appRoleMetier.addRoleToUser(user.getId_user(),"ADMIN");
            appRoleMetier.addRoleToUser(user.getId_user(),"SUPPORT");
            appRoleMetier.addRoleToUser(user.getId_user(),"VENDEUR");
            appRoleMetier.addRoleToUser(user.getId_user(),"PROPRIETAIRE");*/
        } else
            throw new ErrorMessages("L'utilisateur n'a pas ete cree.", HttpStatus.BAD_REQUEST);

    }
}

