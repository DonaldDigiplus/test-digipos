package com.devsdg.digipos.GestionCatalogueCategorie.WebRest;


import com.devsdg.digipos.GestionCatalogueCategorie.DTO.CatalogueProduitDTO;
import com.devsdg.digipos.GestionCatalogueCategorie.DTO.CategorieProduitDTO;
import com.devsdg.digipos.GestionCatalogueCategorie.Metiers.CatalogueProduitMetier;
import com.devsdg.digipos.GestionCatalogueCategorie.Metiers.CategorieProduitMetier;
import com.devsdg.digipos.GestionCatalogueCategorie.Metiers.ShareMetier;
import com.devsdg.digipos.GestionCatalogueCategorie.Models.CatalogueProduit;
import com.devsdg.digipos.GestionCatalogueCategorie.Models.CategorieProduit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin("*")
@RestController
@Transactional
public class CatalogueCategorieRestController {
    @Autowired
    CategorieProduitMetier categorieProduitMetier;
    @Autowired
    CatalogueProduitMetier catalogueProduitMetier;
    @Autowired
    ShareMetier shareMetier;

    @PostMapping("/updatexlsfile")
    boolean updloadXLSFile(@RequestBody MultipartFile multipartFile){
        return shareMetier.uploadXLSFile(multipartFile);
    }

    /*C A T E G O R I E    DE   P R O D U I T S*/
    // creer une nouvelle categorie
    @PostMapping("/categorie/save")
    CategorieProduitDTO saveCategorie(@RequestBody CategorieProduitDTO categorieProduitDTO) {
        return categorieProduitMetier.saveCategorie(categorieProduitDTO);
    }

    //retrouver une categorie existante à partir de son ID
    @GetMapping("/categorie/getcategoriebyid/{id}")
    CategorieProduitDTO getCategorieById(@PathVariable Long id) {
        return categorieProduitMetier.getCategorieById(id);
    }

    //Mettre a jour une categorie existante
    @PutMapping("/categorie/update")
    CategorieProduitDTO updateCategorie(@RequestBody CategorieProduitDTO categorieProduitDTO) {
        return categorieProduitMetier.updateCategorie(categorieProduitDTO);
    }

    //liste de toutes les categories existantes
    @GetMapping("/categorie/getallcategories")
    Page<CategorieProduit> getAllCategories(Pageable pageable) {
        return categorieProduitMetier.getAllCategorie(pageable);
    }

    //liste des categories  par classe
    @GetMapping("/categorie/getallcategoriesbyclasse/{classe}")
    Page<CategorieProduit> getCategorieByClasse(@PathVariable String classe, Pageable pageable) {
        return categorieProduitMetier.getAllCategorieByClasse(classe, pageable);
    }

    //liste des catgories par nom
    @GetMapping("/categorie/getcategoriebynom/{nom}")
    CategorieProduitDTO getCategorieByNom(@PathVariable String nom) {
        return categorieProduitMetier.getCategorieByNom(nom);
    }

    //liste des categories par secteur
    @GetMapping("/categorie/getcategoriebytype/{secteur}")
    Page<CategorieProduit> getCategoryById(@PathVariable String secteur, Pageable pageable) {
        return categorieProduitMetier.getAllCategorieBySecteuractivite(secteur, pageable);
    }
    //liste des nom categories
    @GetMapping("/categorie/nom")
    List<String> getAllNomategorie() {
        return categorieProduitMetier.getAllNomCategorie();
    }

    /* C A T A L O G U E   DE   P R O D U I T S*/
    // creer une nouvelle Catalogue
    @PostMapping("/catalogue/save")
    CatalogueProduitDTO saveCatalogue(@RequestBody CatalogueProduitDTO catalogueProduitDTO) {
        return catalogueProduitMetier.saveCatalogue(catalogueProduitDTO);
    }

    // Mettre a jour un Catalogue
    @PutMapping("/catalogue/update")
    CatalogueProduitDTO updateCatalogue(@RequestBody CatalogueProduitDTO catalogueProduitDTO) {
        return catalogueProduitMetier.updateCatalogue(catalogueProduitDTO);
    }

    @GetMapping("/catalogue/getallcatalogues")
    Page<CatalogueProduit> getAllCatalogues(Pageable pageable) {
        return catalogueProduitMetier.getAllCatalogue(pageable);
    }

    @GetMapping("/catalogue/getallcataloguebynom")
    List<String> findallnomcatalogue() {
        return catalogueProduitMetier.getAllNomCatalogues();
    }

    @GetMapping("/catalogue/getcataloguebynom/{nomcatalogue}")
    CatalogueProduitDTO getCatalogueByNom(@PathVariable String nomcatalogue) {
        return catalogueProduitMetier.getCatalogueByNom(nomcatalogue);
    }

    @GetMapping("/catalogue/getcataloguebycategorie/{nomcategorie}")
    Page<CatalogueProduit> getCatalogueByCategorie(@PathVariable String nomcategorie, Pageable pageable) {
        return catalogueProduitMetier.getCatalogueByCategorie(nomcategorie, pageable);
    }
}
