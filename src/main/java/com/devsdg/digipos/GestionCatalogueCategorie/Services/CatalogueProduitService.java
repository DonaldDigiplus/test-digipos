package com.devsdg.digipos.GestionCatalogueCategorie.Services;

import com.devsdg.digipos.Cloudinary.Services.ServiceCloudinary;
import com.devsdg.digipos.GestionCatalogueCategorie.DTO.CatalogueProduitDTO;
import com.devsdg.digipos.GestionCatalogueCategorie.DTO.CategorieProduitDTO;
import com.devsdg.digipos.GestionCatalogueCategorie.Metiers.CatalogueProduitMetier;
import com.devsdg.digipos.GestionCatalogueCategorie.Models.CatalogueProduit;
import com.devsdg.digipos.GestionCatalogueCategorie.Models.CategorieProduit;
import com.devsdg.digipos.GestionCatalogueCategorie.Repository.CatalogueProduitRepository;
import com.devsdg.digipos.GestionCatalogueCategorie.Repository.CategorieProduitRepository;
import com.devsdg.digipos.GestionErreurs.ErrorMessages;
import org.junit.Assert;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class CatalogueProduitService implements CatalogueProduitMetier {
    @Autowired
    CatalogueProduitRepository catalogueProduitRepository;
    @Autowired
    ServiceCloudinary serviceCloudinary;
    @Autowired
    CategorieProduitRepository categorieProduitRepository;

    @Override
    public CatalogueProduitDTO saveCatalogue(CatalogueProduitDTO catalogueProduitDTO) {
        CatalogueProduit catalogueProduit = catalogueProduitRepository.findByNomProduit(catalogueProduitDTO.getNomProduit());
        if(catalogueProduit==null){
            CategorieProduit categorieProduit = categorieProduitRepository.findByNomCategorie(catalogueProduitDTO.getNomcategorie());

            if(categorieProduit!=null){
                BeanUtils.copyProperties(catalogueProduitDTO, catalogueProduit);
                catalogueProduit.setDate(new Date());
                catalogueProduit.setActive(true);
                catalogueProduit.setCategorieProduit(categorieProduit);
                catalogueProduit = catalogueProduitRepository.save(catalogueProduit);
                if(catalogueProduit.getImage()!=null || catalogueProduit.getImage().isEmpty()){
                    catalogueProduit.setImage(serviceCloudinary.saveObjectOnCloudinary(catalogueProduit.getImage(),
                            "Catalogues", null,
                            categorieProduit.getSecteuractivite(),
                            catalogueProduit.getNomProduit()));
                }
            } else
                throw new ErrorMessages("La categorie que vous entree n'existe pas.", HttpStatus.NOT_FOUND);
            return permuteCatalogueProduitToCatalogueProduitDTO(catalogueProduit);
        } else
            throw new ErrorMessages("Le catalogue que vous essayer de creer existe deja.", HttpStatus.CONFLICT);
    }

    @Override
    public CatalogueProduitDTO updateCatalogue(CatalogueProduitDTO catalogueProduitDTO) {
        CatalogueProduit catalogueProduit = catalogueProduitRepository.findByNomProduit(catalogueProduitDTO.getNomProduit());
        if(catalogueProduit!=null){
            CategorieProduit categorieProduit = catalogueProduit.getCategorieProduit();
            if(!catalogueProduitDTO.getNomcategorie().isEmpty() || catalogueProduitDTO.getNomcategorie()!=null){
                categorieProduit = categorieProduitRepository.findByNomCategorie(catalogueProduitDTO.getNomcategorie());
                Assert.assertNotNull(categorieProduit);
                catalogueProduit.setCategorieProduit(categorieProduit);
            }
                boolean b = catalogueProduit.getImage().equalsIgnoreCase(catalogueProduitDTO.getImage());
                BeanUtils.copyProperties(catalogueProduitDTO, catalogueProduit);
                catalogueProduit = catalogueProduitRepository.save(catalogueProduit);
                if(!b){
                    catalogueProduit.setImage(serviceCloudinary.saveObjectOnCloudinary(catalogueProduit.getImage(),
                            "Catalogues", null,
                            categorieProduit.getSecteuractivite(),
                            catalogueProduit.getNomProduit()));
                }
            return permuteCatalogueProduitToCatalogueProduitDTO(catalogueProduit);
        } else
            throw new ErrorMessages("Le catalogue que vous essayer de mettre a jour n'existe pas.", HttpStatus.CONFLICT);
    }

    @Override
    public boolean active_desactive_catalogue(Long id_catalogue) {
        CatalogueProduit catalogueProduit = catalogueProduitRepository.getOne(id_catalogue);
        Assert.assertNotNull(catalogueProduit);
        catalogueProduit.setActive(!catalogueProduit.isActive());
        return catalogueProduit.isActive();
    }

    @Override
    public CatalogueProduitDTO getCatalogueByNom(String nom_catalogue) {
        CatalogueProduit catalogueProduit = catalogueProduitRepository.findByNomProduit(nom_catalogue);
        Assert.assertNotNull(catalogueProduit);
        return permuteCatalogueProduitToCatalogueProduitDTO(catalogueProduit);
    }

    @Override
    public CatalogueProduit getCatalogueByNomIgnoreCase(String nom_catalogue) {
        return catalogueProduitRepository.findByNomProduitIgnoreCase(nom_catalogue);
    }

    @Override
    public CatalogueProduitDTO getCatalogueById(Long id_catalogue) {
        return permuteCatalogueProduitToCatalogueProduitDTO(catalogueProduitRepository.getOne(id_catalogue));
    }

    @Override
    public Page<CatalogueProduit> getAllCatalogue(Pageable pageable) {
        return catalogueProduitRepository.findAll(pageable);
    }

    @Override
    public Page<CatalogueProduit> getAllCatalogueIsActive(Pageable pageable) {
        return catalogueProduitRepository.findAllByActiveIsTrue(pageable);
    }

    @Override
    public List<String> getAllNomCatalogues() {
        return catalogueProduitRepository.findAllNomCatalogue();
    }

    @Override
    public Page<CatalogueProduit> getCatalogueByCategorie(String nomcategorie, Pageable pageable) {
        CategorieProduit categorieProduit = categorieProduitRepository.findByNomCategorie(nomcategorie);
        if(categorieProduit!=null){
            return catalogueProduitRepository.findAllByCategorieProduit(categorieProduit, pageable);
        } else {
            throw new ErrorMessages("La categorie entree n'existe pas.", HttpStatus.NOT_FOUND);
        }
    }

    static CatalogueProduitDTO permuteCatalogueProduitToCatalogueProduitDTO(CatalogueProduit catalogueProduit){
        CatalogueProduitDTO catalogueProduitDTO = new CatalogueProduitDTO();
        BeanUtils.copyProperties(catalogueProduit, catalogueProduitDTO);
        return catalogueProduitDTO;
    }

}
