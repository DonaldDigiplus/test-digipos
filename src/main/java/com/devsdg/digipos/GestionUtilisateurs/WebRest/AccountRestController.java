package com.devsdg.digipos.GestionUtilisateurs.WebRest;


import com.devsdg.digipos.GestionUtilisateurs.DTO.AppUserDTO;
import com.devsdg.digipos.GestionUtilisateurs.Metiers.AccountMetier;
import com.devsdg.digipos.GestionUtilisateurs.Metiers.AppUserMetier;
import com.devsdg.digipos.GestionUtilisateurs.Models.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@CrossOrigin("*")
@RestController
@Transactional
public class AccountRestController {
    @Autowired
    AccountMetier accountMetier;
    @Autowired
    AppUserMetier appUserMetier;

    @PostMapping("/register")
    public AppUserDTO register(@RequestBody AppUserDTO user){
        return accountMetier.RegisterAccount(user);
    }

    @GetMapping("/connection/{login}")
    public AppUserDTO connectUser(@PathVariable String login){
        return accountMetier.ConnectAccount(login);
    }

}
