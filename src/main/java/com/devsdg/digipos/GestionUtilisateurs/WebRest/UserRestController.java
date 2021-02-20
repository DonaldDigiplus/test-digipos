package com.devsdg.digipos.GestionUtilisateurs.WebRest;


import com.devsdg.digipos.GestionUtilisateurs.DTO.AppUserDTO;
import com.devsdg.digipos.GestionUtilisateurs.Metiers.AppUserMetier;
import com.devsdg.digipos.GestionUtilisateurs.Models.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
public class UserRestController {
    @Autowired
    AppUserMetier userMetier;

    @GetMapping("all_staff")
    Page<AppUser> findAllByStaffIsTrue(Pageable pageable){
        return userMetier.findAllByStaffIsTrue(pageable);
    }

    @PutMapping("/update")
    public AppUserDTO update(@RequestBody AppUserDTO user){
        return userMetier.updateUser(user);
    }

}
