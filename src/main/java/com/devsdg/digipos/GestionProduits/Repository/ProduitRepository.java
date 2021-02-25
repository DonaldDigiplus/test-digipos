package com.devsdg.digipos.GestionProduits.Repository;

import com.devsdg.digipos.GestionBoutiques.Models.Boutique;
import com.devsdg.digipos.GestionProduits.Models.Produit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProduitRepository extends JpaRepository<Produit, Long> {
    List<Produit> findAllByNomProduitAndBoutiquesProduit(String nomProdut, Boutique boutique);
    Produit findByQcode(String qcode);
    Produit findByIdProduit(Long idProduit);
    Produit findByNomProduit(String nomProduit);
    Produit findByNomProduitAndBoutiquesProduit(String nomProduit, Boutique boutique);
    Produit findByNomProduitAndBoutiquesProduitAndActiveproduitIsTrue(String nomProduit, Boutique boutique);
    @Query("select q.nomProduit from Produit q order by q.dateproduit desc")
    List<String> findAllNomProduit();
    @Query("select q.nomProduit from Produit q where q.boutiquesProduit =: x order by q.dateproduit desc")
    List<String> findAllNomProduitByShop(@Param("x") Boutique boutique);
    Page<Produit> findAllByBoutiquesProduit(Boutique boutique, Pageable pageable);
    Page<Produit> findAllByBoutiquesProduitAndActiveproduitIsTrue(Boutique boutique, Pageable pageable);
    Page<Produit> findAllByActiveproduitIsTrue(Pageable pageable);
    Page<Produit> findAllByNomProduitIgnoreCaseLike(String NomProduit,Pageable pageable);
    Page<Produit> findAllByNomCategorie(String nomCategorie, Pageable pageable);
    Page<Produit> findAllByBoutiquesProduitAndNomCategorie(Boutique boutique, String NomCategorie, Pageable pageable);
}
