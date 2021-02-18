package com.devsdg.digipos.GestionUtilisateurs.Metiers;

import com.devsdg.digipos.GestionUtilisateurs.DTO.AppRoleDTO;
import java.util.List;

public interface AppRoleMetier {
    AppRoleDTO saveRole(AppRoleDTO appRoleDTO);
    AppRoleDTO updateRole(AppRoleDTO appRoleDTO);
    AppRoleDTO updateRoleByRolename(String rolename);
    AppRoleDTO getRole(String rolename);
    List<AppRoleDTO> getRoles();

    boolean addRoleToUser(Long id_user, String rolename);
}
