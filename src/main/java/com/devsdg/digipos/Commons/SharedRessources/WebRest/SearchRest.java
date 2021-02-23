package com.devsdg.digipos.Commons.SharedRessources.WebRest;

import com.devsdg.digipos.GestionBoutiques.Metiers.BoutiqueMetier;
import com.devsdg.digipos.GestionUtilisateurs.Metiers.AppUserMetier;
import com.devsdg.digipos.GestionUtilisateurs.Models.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/search")
public class SearchRest {
    @Autowired
    BoutiqueMetier boutiqueMetier;
    @Autowired
    AppUserMetier appUserMetier;

    @GetMapping("/clients/{key}")
    List<AppUser> findAllClientByKeyWord(@PathVariable String key){
        return appUserMetier.findAllClientByKeyWord(key);
    }

    @GetMapping("/staff/{key}")
    List<AppUser> findAllStaffByKeyWord(@PathVariable String key){
        return appUserMetier.findAllStaffByKeyWord(key);
    }

}
