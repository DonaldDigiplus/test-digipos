package com.devsdg.digipos.GestionUtilisateurs.Metiers;

import com.devsdg.digipos.GestionUtilisateurs.DTO.AppUserDTO;

public interface AccountMetier {

    AppUserDTO RegisterAccount(AppUserDTO appUserDTO);
    AppUserDTO ConnectAccount(String login);
}
