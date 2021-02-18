package com.devsdg.digipos.GestionUtilisateurs.WebRest;


import com.devsdg.digipos.GestionUtilisateurs.DTO.AppRoleDTO;
import com.devsdg.digipos.GestionUtilisateurs.Metiers.AppRoleMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@Transactional
@RequestMapping(("/role"))
public class RoleRestController {
    @Autowired
    AppRoleMetier appRoleMetier;

    @PostMapping("/save")
    AppRoleDTO createRole(@RequestBody AppRoleDTO appRoleDTO){
        return appRoleMetier.saveRole(appRoleDTO);
    }

    @GetMapping("/get/{rolename}")
    AppRoleDTO getRole(@PathVariable String rolename){
        return appRoleMetier.getRole(rolename);
    }

    @GetMapping("/get")
    List<AppRoleDTO> getRoles(){
        return appRoleMetier.getRoles();
    }

    @PutMapping("/update")
    AppRoleDTO updateRole(@RequestBody AppRoleDTO appRoleDTO){
        return appRoleMetier.updateRole(appRoleDTO);
    }

    @PutMapping("/update/{rolename}")
    AppRoleDTO updateRole(@PathVariable String rolename){
        return appRoleMetier.updateRoleByRolename(rolename);
    }
}
