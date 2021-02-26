package com.devsdg.digipos.GestionProduits.Metier;

import com.devsdg.digipos.GestionProduits.DTO.FiltreBoutiqueCatalogue;
import com.devsdg.digipos.GestionProduits.DTO.ProduitDTO;
import com.devsdg.digipos.GestionProduits.Models.Produit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface ProduitMetier {
    ProduitDTO saveProduit(ProduitDTO produitDTO);
    ProduitDTO updateProduit(ProduitDTO produitDTO);
    ProduitDTO getProduitById(Long id);
    ProduitDTO getProduitByQcode(String qcode);
    ProduitDTO getProduitByNomProduit(String nomproduit);
    ProduitDTO getProduitByNomAndBoutique(String nomProduit, Long idBoutique);
    ProduitDTO getProduitByNomAndBoutiqueIsActive(String nomProduit, Long idBoutique);
    ProduitDTO AddProduitAssocie(Long idProduit, Long idProduitAssocie);
    ProduitDTO removeProduitAssocie(Long idProduit, Long idProdutAssocie);
    List<ProduitDTO> getProduitAssocie(Long idProduit);
    List<String> getAllNomProduit();
    List<String> getAllNomProduitByBoutique(String nomboutique);
    Page<Produit> getAllProduitByBoutique(String nomBoutique, Pageable pageable);
    Page<Produit> getAllProduitByBoutiqueIsActive(Long idBoutique, Pageable pageable);
    Page<Produit> getAllProduit(Pageable pageable);
    Page<Produit> getAllProduitIsActive(Pageable pageable);
    Page<Produit> getProdtuiByPage(String nomProduit, Pageable pageable);
    Page <Produit> filtreBoutiqueCategorie(FiltreBoutiqueCatalogue filtreBoutiqueCatalogue, Pageable pageable);
    Page<Produit> findAllByNomboutiqueAndNomProduitIgnoreCaseLike(String nom_boutique,String nom_produit, Pageable pageable);
    boolean active_and_desactive_produit(Long idProduit);
    boolean active_and_desactive_reduction(Long idProduit);
    boolean active_and_desactive_prix(Long idProduit);
    boolean active_and_desactive_stock(Long idProduit);
}
