package com.devsdg.digipos.GestionProduits.WebRest;

import com.devsdg.digipos.GestionProduits.DTO.FiltreBoutiqueCatalogue;
import com.devsdg.digipos.GestionProduits.DTO.ProduitDTO;
import com.devsdg.digipos.GestionProduits.Metier.ProduitMetier;
import com.devsdg.digipos.GestionProduits.Models.Produit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@Transactional
public class ProduitRestController {
    final
    ProduitMetier produitMetier;

    public ProduitRestController(ProduitMetier produitMetier) {
        this.produitMetier = produitMetier;
    }

    @PostMapping("/saveproduit")
    public ProduitDTO saveProduit(@RequestBody ProduitDTO produitDTO){
        return produitMetier.saveProduit(produitDTO);
    }
    @PutMapping("/updateproduit")
    public ProduitDTO updateProduit(@RequestBody ProduitDTO produitDTO){
        return produitMetier.updateProduit(produitDTO);
    }
    @GetMapping("/getproduitbyid/{idproduit}")
    public ProduitDTO getProduitById(@PathVariable Long idproduit){
        return produitMetier.getProduitById(idproduit);
    }
    @GetMapping("/getproduitbyqcode/{qcode}")
    public ProduitDTO getProduitByQcode(@PathVariable String qcode){
        return produitMetier.getProduitByQcode(qcode);
    }
    @GetMapping("/getproduitbynomproduit/{nomproduit}")
    public ProduitDTO getProduitByNomProduit(@PathVariable String nomproduit){
        return produitMetier.getProduitByNomProduit(nomproduit);
    }
    @PostMapping("/addproduitassocie/{idproduit}/{idproduitassocie}")
    public ProduitDTO addProduitAssocie(@PathVariable Long idproduit, @PathVariable Long idproduitassocie){
        return produitMetier.AddProduitAssocie(idproduit,idproduitassocie);
    }
    @PostMapping("/removeproduitassocie/{idproduit}/{idproduitassocie}")
    public ProduitDTO removeProduitAssocie(@PathVariable Long idproduit, @PathVariable Long idproduitassocie){
        return produitMetier.removeProduitAssocie(idproduit,idproduitassocie);
    }
    @GetMapping("/getproduitassocie/{idproduit}")
    public List<ProduitDTO> getProduitAssocie(@PathVariable Long idproduit){
        return produitMetier.getProduitAssocie(idproduit);
    }
    @GetMapping("/getproduitbynomandboutique/{nomproduit}/{idboutique}")
    public ProduitDTO getProduitByNomAndBoutique(@PathVariable String nomproduit, @PathVariable Long idboutique){
        return produitMetier.getProduitByNomAndBoutique(nomproduit, idboutique);
    }
    @GetMapping("/getproduitbynomandboutiqueisactive/{nomproduit}/{idboutique}")
    public ProduitDTO getProduitByNomAndBoutiqueIsActive(@PathVariable String nomproduit, @PathVariable Long idboutique){
        return produitMetier.getProduitByNomAndBoutiqueIsActive(nomproduit, idboutique);
    }
    @GetMapping("/getallproduit")
    public Page<Produit> getallproduit(Pageable pageable) {
        return produitMetier.getAllProduit(pageable);
    }
    @GetMapping("/getallproduitisactive")
    public Page<Produit> getallproduitIsactive(Pageable pageable) {
        return produitMetier.getAllProduitIsActive(pageable);
    }
    @GetMapping("/getallproduitbynom/{nom}")
    public Page<Produit> getallproduitbynom(@PathVariable String nom, Pageable pageable) {
        return produitMetier.getProdtuiByPage(nom, pageable);
    }
    @GetMapping("/getallproduitbyboutique/{nomBoutique}")
    public Page<Produit> getallproduitbyboutique(@PathVariable String nomBoutique, Pageable pageable) {
        return produitMetier.getAllProduitByBoutique(nomBoutique, pageable);
    }
    @GetMapping("/getallproduitbyboutiqueisactive/{idboutique}")
    public Page<Produit> getallproduitbyboutiqueIsactive(@PathVariable Long idboutique, Pageable pageable) {
        return produitMetier.getAllProduitByBoutiqueIsActive(idboutique, pageable);
    }
    @GetMapping("/active_and_desactive_produit/{idproduit}")
    public boolean active_and_desactive_produit(@PathVariable Long idproduit) {
        return produitMetier.active_and_desactive_produit(idproduit);
    }
    @GetMapping("/active_and_desactive_reduction/{idproduit}")
    public boolean active_and_desactive_reduction(@PathVariable Long idproduit) {
        return produitMetier.active_and_desactive_reduction(idproduit);
    }
    @GetMapping("/active_and_desactive_prix/{idproduit}")
    public boolean active_and_desactive_prix(@PathVariable Long idproduit) {
        return produitMetier.active_and_desactive_prix(idproduit);
    }
    @GetMapping("/active_and_desactive_stock/{idproduit}")
    public boolean active_and_desactive_stock(@PathVariable Long idproduit) {
        return produitMetier.active_and_desactive_stock(idproduit);
    }
    @PostMapping("/filtreboutiqueandcategorie")
    public Page<Produit> filtreBoutiqueCategorie(@RequestBody FiltreBoutiqueCatalogue filtreBoutiqueCatalogue, Pageable pageable){
        return produitMetier.filtreBoutiqueCategorie(filtreBoutiqueCatalogue, pageable);
    }


    @GetMapping("/search_boutique_produit/{produit}/{boutique}")
    public Page<Produit> filtreproduitbyShop(@PathVariable String produit, @PathVariable String boutique,Pageable pageable){
        return produitMetier.findAllByNomboutiqueAndNomProduitIgnoreCaseLike(boutique,produit,pageable);
    }


    @GetMapping("/getallnomproduit")
    public List<String> getAllNomProduit(){
        return produitMetier.getAllNomProduit();
    }
    @GetMapping("/getallnomproduitbyshop/{nomboutique}")
    public List<String> getAllNomProduitByBoutique(@PathVariable String nomboutique){
        return produitMetier.getAllNomProduitByBoutique(nomboutique);
    }
}
