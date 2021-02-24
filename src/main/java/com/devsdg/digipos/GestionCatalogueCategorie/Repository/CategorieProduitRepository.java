package com.devsdg.digipos.GestionCatalogueCategorie.Repository;

import com.devsdg.digipos.GestionCatalogueCategorie.Models.CategorieProduit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategorieProduitRepository extends JpaRepository<CategorieProduit, Long> {
    CategorieProduit findByNomCategorie(String nomCategorie);
    CategorieProduit findByNomCategorieIgnoreCase(String nomCategorie);
    Page<CategorieProduit> findAllBySecteuractivite(String secteur, Pageable pageable);
    Page<CategorieProduit> findAllBySecteuractiviteAndActiveIsTrue(String secteur, Pageable pageable);
    Page<CategorieProduit> findAllByClasse(String classe, Pageable pageable);
    Page<CategorieProduit> findAllByClasseAndActiveIsTrue(String classe, Pageable pageable);
    @Query("select q.nomCategorie from CategorieProduit q order by q.date desc")
    List<String> findAllNomCategorie();
}
