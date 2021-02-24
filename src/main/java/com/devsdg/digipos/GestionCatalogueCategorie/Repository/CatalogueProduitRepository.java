package com.devsdg.digipos.GestionCatalogueCategorie.Repository;

import com.devsdg.digipos.GestionCatalogueCategorie.Models.CatalogueProduit;
import com.devsdg.digipos.GestionCatalogueCategorie.Models.CategorieProduit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CatalogueProduitRepository extends JpaRepository<CatalogueProduit, Long> {
    CatalogueProduit findByNomProduit(String nomCatalogue);
    CatalogueProduit findByNomProduitIgnoreCase(String nomCatalogue);
    Page<CatalogueProduit> findAllByActiveIsTrue(Pageable pageable);
    @Query("select q.nomProduit from CatalogueProduit q order by q.date desc")
    List<String> findAllNomCatalogue();
    Page<CatalogueProduit> findAllByCategorieProduit(CategorieProduit categorieProduit, Pageable pageable);
}
