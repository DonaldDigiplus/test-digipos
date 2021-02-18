package com.devsdg.digipos.GestionUtilisateurs.Services;

import com.devsdg.digipos.GestionErreurs.ErrorMessages;
import com.devsdg.digipos.GestionUtilisateurs.DTO.AppUserDTO;
import com.devsdg.digipos.GestionUtilisateurs.Metiers.AccountMetier;
import com.devsdg.digipos.GestionUtilisateurs.Models.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AccountService implements AccountMetier {
    @Autowired
    AppUserSercice appUserSercice;

    @Override
    public AppUserDTO RegisterAccount(AppUserDTO appUserDTO) {
        AppUser appUser=appUserSercice.getUserByLogin(appUserDTO.getPhone());
        if (appUser!=null) throw new ErrorMessages("Ce numéro est deja utilisé par un autre utilisateur!!!", HttpStatus.CONFLICT);
        appUser=appUserSercice.getUserByLogin(appUserDTO.getUsername());
        if (appUser!=null) throw new ErrorMessages("Ce nom d'utilisateur est deja utilisé par un autre utilisateur!!!", HttpStatus.CONFLICT);
        appUser=appUserSercice.getUserByLogin(appUserDTO.getEmail());
        if (appUser!=null) throw new ErrorMessages("Ce E-mail est deja utilisée par un autre utilisateur!!!", HttpStatus.CONFLICT);

        return appUserSercice.saveUser(appUserDTO);
    }

    @Override
    public AppUserDTO ConnectAccount(String login) {
        AppUser user = appUserSercice.getUserByLogin(login);
        if(user==null)
            throw new ErrorMessages("Utilisateur introuvable", HttpStatus.NOT_FOUND);

        return AppUserSercice.permuteAppUserToAppUserDTO(user);
    }


}
