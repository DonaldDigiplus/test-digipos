package com.devsdg.digipos.GestionBoutiques.WebRest;

import com.devsdg.digipos.GestionBoutiques.DTO.BoutiqueDTO;
import com.devsdg.digipos.GestionBoutiques.Metiers.BoutiqueMetier;
import com.devsdg.digipos.GestionBoutiques.Models.Boutique;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@Transactional
public class BoutiqueRestController {
    @Autowired
    BoutiqueMetier boutiqueMetier;

    @PostMapping("/saveboutique")
    BoutiqueDTO saveBoutique(@RequestBody BoutiqueDTO boutiqueDTO){
        return boutiqueMetier.saveBoutique(boutiqueDTO);
    }

    @PutMapping("/updateboutique")
    BoutiqueDTO updateBoutique(@RequestBody BoutiqueDTO boutiqueDTO){
        return boutiqueMetier.updateBoutique(boutiqueDTO);
    }

    @GetMapping("/getboutiquebyproprietaire/{id_proprietaire}")
    BoutiqueDTO getBoutiqueByProprietaire(@PathVariable Long id_proprietaire){
        return boutiqueMetier.getBoutiqueDTOByProprietaire(id_proprietaire);
    }

    @GetMapping("/getboutiquebyid/{idBoutique}")
    BoutiqueDTO getBoutiqueById(@PathVariable Long idBoutique){
        return boutiqueMetier.getBoutiqueById(idBoutique);
    }

    @GetMapping("/getboutiquebyname/{nomBoutique}")
    BoutiqueDTO getBoutiqueByName(@PathVariable String nomBoutique){
        return boutiqueMetier.getBoutiqueByName(nomBoutique);
    }

    @GetMapping("/getboutiquebyphone/{phoneBoutique}")
    BoutiqueDTO getBoutiqueByPhone(@PathVariable String phoneBoutique){
        return boutiqueMetier.getBoutiqueByPhone(phoneBoutique);
    }

    @GetMapping("/getboutiquebylien/{lienBoutique}")
    BoutiqueDTO getBoutiqueByLien(@PathVariable String lienBoutique){
        return boutiqueMetier.getBoutiqueByLien(lienBoutique);
    }

    @GetMapping("/pages/getallboutiques")
    Page<Boutique> getAllBoutiques(Pageable pageable){
        return boutiqueMetier.getAllBoutiques(pageable);
    }

    @GetMapping("/pages/getallboutiquesisactive")
    Page<Boutique> getAllBoutiquesIsActive(Pageable pageable){
        return boutiqueMetier.getAllBoutiquesIsActive(pageable);
    }

    @GetMapping("/getallnomboutiques")
    List<String> getAllNomBoutiques(){
        return boutiqueMetier.getAllNomBoutique();
    }

    @GetMapping("/getallnomboutiquesisactive")
    List<String> getAllNomBoutiquesIsActive(){
        return boutiqueMetier.getAllNomBoutiqueIsActive();
    }

    @GetMapping("/activeanddesactiveboutique/{idboutique}")
    boolean active_and_desactive_boutique(@PathVariable Long idboutique){
        return boutiqueMetier.active_and_desactive_boutique(idboutique);
    }
}
