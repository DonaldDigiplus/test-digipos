package com.devsdg.digipos.GestionUtilisateurs.WebRest;


import com.devsdg.digipos.GestionUtilisateurs.DTO.AppUserDTO;
import com.devsdg.digipos.GestionUtilisateurs.Metiers.AppUserMetier;
import com.devsdg.digipos.GestionUtilisateurs.Models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/getuser/{id_user}")
    public AppUserDTO getUser(@PathVariable Long id_user){
        return userMetier.getUser(id_user);
    }

    @GetMapping("/getalladmins")
    public Page<Admin> getAllAdmins(Pageable pageable){
        return userMetier.getAllAdmins(pageable);
    }

    @GetMapping("/getalladminsisactive")
    public Page<Admin> getAllAdminsIsActive(Pageable pageable){
        return userMetier.getAllAdminsIsActive(pageable);
    }

    @GetMapping("/getallsupports")
    public Page<Support> getAllSupports(Pageable pageable){
        return userMetier.getAllSupports(pageable);
    }

    @GetMapping("/getallsupportsisactive")
    public Page<Support> getAllSupportsIsActive(Pageable pageable){
        return userMetier.getAllSupportsIsActive(pageable);
    }

    @GetMapping("/all_proprietaires")
    public Page<Proprietaire> getAllProprietaires(Pageable pageable){
        return userMetier.getAllProprietaires(pageable);
    }

    @GetMapping("/getallproprietairesisactive")
    public Page<Proprietaire> getAllProprietairesIsActive(Pageable pageable){
        return userMetier.getAllProprietairesIsActive(pageable);
    }

    @GetMapping("/all_vendeurs")
    public Page<Vendeur> getAllVendeurs(Pageable pageable){
        return userMetier.getAllVendeurs(pageable);
    }

    @GetMapping("/getallvendeursisactive")
    public Page<Vendeur> getAllVendeursIsActive(Pageable pageable){
        return userMetier.getAllVendeursIsActive(pageable);
    }

    @GetMapping("/getallclients")
    public Page<ClientPOS> getAllClientPOS(Pageable pageable){
        return userMetier.getAllClientPOSs(pageable);
    }

    @GetMapping("/getallclientsisactive")
    public Page<ClientPOS> getAllClientPOSIsActive(Pageable pageable){
        return userMetier.getAllClientPOSsIsActive(pageable);
    }

    @GetMapping("/search_client/{username}")
    public List<ClientPOS> searchAllClientPOS(@PathVariable String username){
        return userMetier.findAllByUsernameLike(username);
    }
    @GetMapping("/search_staff/{username}")
    public List<AppUser> searchAllSTAFPOS(@PathVariable String username){
        return userMetier.findAllByUsernameLikeAndStaffIsTrue(username);
    }

    @GetMapping("/active_desactive_user/{id_user}")
    public boolean active_desactive_user(@PathVariable Long id_user){
        return userMetier.active_and_desactive_user(id_user);
    }

}
