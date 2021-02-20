package com.devsdg.digipos.GestionUtilisateurs.WebRest;


import com.devsdg.digipos.GestionUtilisateurs.DTO.AppUserDTO;
import com.devsdg.digipos.GestionUtilisateurs.DTO.PasswordResetRequestModel;
import com.devsdg.digipos.GestionUtilisateurs.Metiers.AccountMetier;
import com.devsdg.digipos.GestionUtilisateurs.Metiers.AppUserMetier;
import com.devsdg.digipos.GestionUtilisateurs.Models.PasswordResetTokenEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping(
            path = "/password-request",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public PasswordResetTokenEntity requestPasswordReset(@RequestBody PasswordResetRequestModel passwordResetRequestModel) {
        System.out.println("Requete en cours ..................");
        return accountMetier.requestPasswordReset(passwordResetRequestModel.getEmail());
    }
    @PostMapping(
            path = "/password-reset",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    public boolean resetPassword(@RequestBody PasswordResetRequestModel passwordResetRequestModel) {
        return accountMetier.resetPassword(passwordResetRequestModel);
    }

    @PostMapping("/renew-pass")
    public boolean renewPass(@RequestBody PasswordResetRequestModel passwordResetRequestModel){
        return accountMetier.renewPassword(passwordResetRequestModel.getId(), passwordResetRequestModel.getPassword());
    }

}
