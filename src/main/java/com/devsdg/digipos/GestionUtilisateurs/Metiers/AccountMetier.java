package com.devsdg.digipos.GestionUtilisateurs.Metiers;

import com.devsdg.digipos.GestionUtilisateurs.DTO.AppUserDTO;
import com.devsdg.digipos.GestionUtilisateurs.DTO.PasswordResetRequestModel;
import com.devsdg.digipos.GestionUtilisateurs.Models.PasswordResetTokenEntity;

public interface AccountMetier {

    AppUserDTO RegisterAccount(AppUserDTO appUserDTO);
    AppUserDTO ConnectAccount(String login);
    PasswordResetTokenEntity requestPasswordReset(String email);
    boolean resetPassword(PasswordResetRequestModel passwordResetRequestModel);
    boolean renewPassword(Long idUser, String pass);

}
