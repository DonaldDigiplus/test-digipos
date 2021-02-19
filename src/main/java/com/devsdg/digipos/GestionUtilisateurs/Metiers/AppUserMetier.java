package com.devsdg.digipos.GestionUtilisateurs.Metiers;

import com.devsdg.digipos.GestionUtilisateurs.DTO.AppUserDTO;
import com.devsdg.digipos.GestionUtilisateurs.Models.AppUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AppUserMetier {
    AppUserDTO saveUser(AppUserDTO appUserDTO);
    AppUserDTO updateUser(Long id_user, AppUserDTO appUserDTO);
    AppUserDTO getUser(Long id_user);
    AppUser findUserById(Long id_user);
    AppUser getUserByLogin(String login);
    Page<AppUser> getAllUsers(Pageable pageable);
    Page<AppUser> findAllByStaffIsTrue(Pageable pageable);
    boolean activeUser(Long id_user);
    boolean desactiveUser(Long id_user);
}
