package com.devsdg.digipos.GestionCatalogueCategorie.Metiers;

import com.devsdg.digipos.GestionCatalogueCategorie.DTO.CategorieProduitDTO;
import com.devsdg.digipos.GestionCatalogueCategorie.Models.CategorieProduit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CategorieProduitMetier {

    CategorieProduitDTO saveCategorie(CategorieProduitDTO categorieProduitDTO);
    CategorieProduitDTO updateCategorie(CategorieProduitDTO categorieProduitDTO);
    boolean active_desactive_categorie(Long id_categorie);
    CategorieProduitDTO getCategorieByNom(String nom_categorie);
    CategorieProduit getCategorieByNomIgnoreCase(String nom_categorie);
    CategorieProduitDTO getCategorieById(Long id_categorie);
    Page<CategorieProduit> getAllCategorie(Pageable pageable);
    Page<CategorieProduit> getAllCategorieBySecteuractivite(String secteur, Pageable pageable);
    Page<CategorieProduit> getAllCategorieBySecteuractiviteIsActive(String secteur, Pageable pageable);
    Page<CategorieProduit> getAllCategorieByClasse(String classe, Pageable pageable);
    Page<CategorieProduit> getAllCategorieByClasseIsActive(String classe, Pageable pageable);
    List<String> getAllNomCategorie();
}
