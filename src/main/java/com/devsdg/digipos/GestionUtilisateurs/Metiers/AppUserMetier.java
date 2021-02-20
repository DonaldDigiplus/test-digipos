package com.devsdg.digipos.GestionUtilisateurs.Metiers;

import com.devsdg.digipos.GestionUtilisateurs.DTO.AppUserDTO;
import com.devsdg.digipos.GestionUtilisateurs.Models.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AppUserMetier {
    AppUser saveAppuser(AppUser appUser);
    AppUserDTO saveUser(AppUserDTO appUserDTO);
    AppUserDTO updateUser(AppUserDTO appUserDTO);
    AppUserDTO getUser(Long id_user);
    AppUser findUserById(Long id_user);
    AppUser getUserByLogin(String login);
    Page<AppUser> getAllUsers(Pageable pageable);
    Page<Admin> getAllAdmins(Pageable pageable);
    Page<Admin> getAllAdminsIsActive(Pageable pageable);
    Page<Support> getAllSupports(Pageable pageable);
    Page<Support> getAllSupportsIsActive(Pageable pageable);
    Page<Proprietaire> getAllProprietaires(Pageable pageable);
    Page<Proprietaire> getAllProprietairesIsActive(Pageable pageable);
    Page<Vendeur> getAllVendeurs(Pageable pageable);
    Page<Vendeur> getAllVendeursIsActive(Pageable pageable);
    Page<ClientPOS> getAllClientPOSs(Pageable pageable);
    Page<ClientPOS> getAllClientPOSsIsActive(Pageable pageable);
    Page<AppUser> findAllByStaffIsTrue(Pageable pageable);
    boolean active_and_desactive_user(Long id_user);
}
