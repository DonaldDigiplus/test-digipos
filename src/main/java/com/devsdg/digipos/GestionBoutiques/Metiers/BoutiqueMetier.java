package com.devsdg.digipos.GestionBoutiques.Metiers;

import com.devsdg.digipos.GestionBoutiques.DTO.BoutiqueDTO;
import com.devsdg.digipos.GestionBoutiques.Models.Boutique;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface BoutiqueMetier {
    BoutiqueDTO saveBoutique(BoutiqueDTO boutiqueDTO);
    BoutiqueDTO updateBoutique(BoutiqueDTO boutiqueDTO);
    BoutiqueDTO getBoutiqueById(Long idBoutique);
    BoutiqueDTO getBoutiqueByName(String nomBoutique);
    Boutique getBoutiqueByNomBoutique(String nomBoutique);
    BoutiqueDTO getBoutiqueByPhone(String phoneBoutique);
    BoutiqueDTO getBoutiqueByLien(String lienBoutique);
    Page<Boutique> getAllBoutiques(Pageable pageable);
    Page<Boutique> getAllBoutiquesIsActive(Pageable pageable);
    List<String> getAllNomBoutique();
    List<String> getAllNomBoutiqueIsActive();
    boolean active_and_desactive_boutique(Long idBoutique);
    BoutiqueDTO addProprietaireToBoutique(Long idboutique, Long idProprietaire);
    Boutique addVendeurToBoutique(Long idboutique, Long idVendeur);
    Boutique getBoutiqueByProprietaire(Long id_proprietaire);
    BoutiqueDTO getBoutiqueDTOByProprietaire(Long id_proprietaire);

}
