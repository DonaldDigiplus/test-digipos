package com.devsdg.digipos.GestionCatalogueCategorie.Metiers;

import com.devsdg.digipos.GestionCatalogueCategorie.DTO.CatalogueProduitDTO;
import com.devsdg.digipos.GestionCatalogueCategorie.Models.CatalogueProduit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CatalogueProduitMetier {
    CatalogueProduitDTO saveCatalogue(CatalogueProduitDTO catalogueProduitDTO);
    CatalogueProduitDTO updateCatalogue(CatalogueProduitDTO catalogueProduitDTO);
    boolean active_desactive_catalogue(Long id_catalogue);
    CatalogueProduitDTO getCatalogueByNom(String nom_catalogue);
    CatalogueProduit getCatalogueByNomIgnoreCase(String nom_catalogue);
    CatalogueProduitDTO getCatalogueById(Long id_catalogue);
    Page<CatalogueProduit> getAllCatalogue(Pageable pageable);
    Page<CatalogueProduit> getAllCatalogueIsActive(Pageable pageable);
    List<String> getAllNomCatalogues();
    Page<CatalogueProduit> getCatalogueByCategorie(String nomcategorie, Pageable pageable);
}
