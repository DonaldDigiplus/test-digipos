package com.devsdg.digipos.GestionUtilisateurs.Services;

import com.devsdg.digipos.GestionErreurs.ErrorMessages;
import com.devsdg.digipos.GestionUtilisateurs.DTO.AppRoleDTO;
import com.devsdg.digipos.GestionUtilisateurs.Metiers.AppRoleMetier;
import com.devsdg.digipos.GestionUtilisateurs.Metiers.AppUserMetier;
import com.devsdg.digipos.GestionUtilisateurs.Models.AppRole;
import com.devsdg.digipos.GestionUtilisateurs.Models.AppUser;
import com.devsdg.digipos.GestionUtilisateurs.Repositories.AppRoleRepository;
import org.junit.Assert;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class AppRoleService implements AppRoleMetier {
    @Autowired
    AppRoleRepository appRoleRepository;
    @Autowired
    AppUserMetier appUserMetier;

    @Override
    public AppRoleDTO saveRole(AppRoleDTO appRoleDTO) {
        AppRole role = appRoleRepository.findByRolename(appRoleDTO.getRolename());
        if(role==null){
            AppRole appRole = appRoleRepository.save(new AppRole(appRoleDTO.getRolename()));

            //Assert.assertNotNull(appRole);
            return this.permuteAppRoleToAppRoleDTO(appRole);
        } else
            throw new ErrorMessages("Le role que vous essayer d'enregistrer existe deja.", HttpStatus.CONFLICT);
    }

    @Override
    public AppRoleDTO updateRole(AppRoleDTO appRoleDTO) {
        AppRole role = appRoleRepository.getOne(appRoleDTO.getId_role());
        if(role!=null){
            role.setRolename(appRoleDTO.getRolename());
            role = appRoleRepository.save(role);
            return this.permuteAppRoleToAppRoleDTO(role);
        } else {
            throw new ErrorMessages("Le role que vous essayer de mettre a jour n'existe deja.", HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public AppRoleDTO updateRoleByRolename(String rolename) {
        AppRole role = appRoleRepository.findByRolename(rolename);
        if(role!=null){
            role.setRolename(rolename);
            role = appRoleRepository.save(role);
            return this.permuteAppRoleToAppRoleDTO(role);
        } else {
            throw new ErrorMessages("Le role que vous essayer de mettre a jour n'existe deja.", HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public AppRoleDTO getRole(String rolename) {
        AppRole role = appRoleRepository.findByRolename(rolename);
        return this.permuteAppRoleToAppRoleDTO(role);
    }

    @Override
    public List<AppRoleDTO> getRoles() {
        return this.permuteAppRoleListToAppRoleDTOList(appRoleRepository.findAll());
    }

    @Override
    public boolean addRoleToUser(Long id_user, String rolename) {
        AppUser user = appUserMetier.findUserById(id_user);
        if(user!=null){
            AppRole role = appRoleRepository.findByRolename(rolename);
            if(role!=null){
                user.getRoles().add(role);
                return true;
            } else
                throw new ErrorMessages("Impossible d'affecter ce role car il n'existe pas.", HttpStatus.NOT_FOUND);
        } else
            throw new ErrorMessages("Impossible d'affecter ce role car l'utilisateur n'existe pas.", HttpStatus.NOT_FOUND);
    }

    AppRoleDTO permuteAppRoleToAppRoleDTO(AppRole role){
        AppRoleDTO appRoleDTO = new AppRoleDTO();
        BeanUtils.copyProperties(role, appRoleDTO);
        return appRoleDTO;
    }

    List<AppRoleDTO> permuteAppRoleListToAppRoleDTOList(List<AppRole> role){
        List<AppRoleDTO> appRoleDTOList = new ArrayList<>();
        role.forEach(r->{
            AppRoleDTO appRoleDTO = new AppRoleDTO();
            BeanUtils.copyProperties(r, appRoleDTO);
            appRoleDTOList.add(appRoleDTO);
        });
        return appRoleDTOList;
    }
}
