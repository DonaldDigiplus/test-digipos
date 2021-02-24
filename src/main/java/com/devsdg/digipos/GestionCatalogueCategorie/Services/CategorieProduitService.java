package com.devsdg.digipos.GestionCatalogueCategorie.Services;

import com.devsdg.digipos.Cloudinary.Services.ServiceCloudinary;
import com.devsdg.digipos.GestionCatalogueCategorie.DTO.CategorieProduitDTO;
import com.devsdg.digipos.GestionCatalogueCategorie.Metiers.CategorieProduitMetier;
import com.devsdg.digipos.GestionCatalogueCategorie.Models.CategorieProduit;
import com.devsdg.digipos.GestionCatalogueCategorie.Repository.CategorieProduitRepository;
import com.devsdg.digipos.GestionErreurs.ErrorMessages;
import com.devsdg.digipos.GestionUtilisateurs.DTO.AppUserDTO;
import com.devsdg.digipos.GestionUtilisateurs.Models.AppUser;
import io.jsonwebtoken.lang.Assert;
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
public class CategorieProduitService implements CategorieProduitMetier {
    @Autowired
    CategorieProduitRepository categorieProduitRepository;
    @Autowired
    ServiceCloudinary serviceCloudinary;

    @Override
    public CategorieProduitDTO saveCategorie(CategorieProduitDTO categorieProduitDTO) {
        CategorieProduit categorieProduit = categorieProduitRepository.findByNomCategorie(categorieProduitDTO.getNomCategorie());
        if(categorieProduit==null){
            BeanUtils.copyProperties(categorieProduitDTO, categorieProduit);
            categorieProduit.setDate(new Date());
            categorieProduit.setActive(true);
            categorieProduitRepository.save(categorieProduit);
            categorieProduit.setImageCategorie(serviceCloudinary.saveObjectOnCloudinary(categorieProduitDTO.getImageCategorie(),
                    "Categories", null,
                    categorieProduitDTO.getSecteuractivite(),
                    categorieProduit.getNomCategorie()));
            return permuteCategorieProduitToCategorieProduitDTO(categorieProduit);
        } else
            throw new ErrorMessages("La categorie que vous essayer de creer existe deja.", HttpStatus.CONFLICT);
    }

    @Override
    public CategorieProduitDTO updateCategorie(CategorieProduitDTO categorieProduitDTO) {
        CategorieProduit categorieProduit = categorieProduitRepository.findByNomCategorie(categorieProduitDTO.getNomCategorie());
        if(categorieProduit!=null){
            boolean b=false;
            if(categorieProduitDTO.getImageCategorie()!=null){
                b = categorieProduit.getImageCategorie().equalsIgnoreCase(categorieProduitDTO.getImageCategorie());
            }
            BeanUtils.copyProperties(categorieProduitDTO, categorieProduit);
            if(!b){
                categorieProduit.setImageCategorie(serviceCloudinary.saveObjectOnCloudinary(categorieProduitDTO.getImageCategorie(),
                        "Categories", null,
                        categorieProduitDTO.getSecteuractivite(),
                        categorieProduit.getNomCategorie()));
            }
            return permuteCategorieProduitToCategorieProduitDTO(categorieProduit);
        } else
            throw new ErrorMessages("La categorie que vous essayer de mettre a jour n'existe pas.", HttpStatus.CONFLICT);
    }

    @Override
    public boolean active_desactive_categorie(Long id_categorie) {
        CategorieProduit categorieProduit = categorieProduitRepository.getOne(id_categorie);
        categorieProduit.setActive(!categorieProduit.isActive());
        return categorieProduit.isActive();
    }

    @Override
    public CategorieProduitDTO getCategorieByNom(String nom_categorie) {
        CategorieProduit categorieProduit = categorieProduitRepository.findByNomCategorie(nom_categorie);
        Assert.notNull(categorieProduit);
        return permuteCategorieProduitToCategorieProduitDTO(categorieProduit);
    }

    @Override
    public CategorieProduit getCategorieByNomIgnoreCase(String nom_categorie) {
        return categorieProduitRepository.findByNomCategorieIgnoreCase(nom_categorie);
    }

    @Override
    public CategorieProduitDTO getCategorieById(Long id_categorie) {
        return permuteCategorieProduitToCategorieProduitDTO( categorieProduitRepository.getOne(id_categorie));
    }

    @Override
    public Page<CategorieProduit> getAllCategorie(Pageable pageable) {
        return categorieProduitRepository.findAll(pageable);
    }

    @Override
    public Page<CategorieProduit> getAllCategorieBySecteuractivite(String secteur, Pageable pageable) {
        return categorieProduitRepository.findAllBySecteuractivite(secteur, pageable);
    }

    @Override
    public Page<CategorieProduit> getAllCategorieBySecteuractiviteIsActive(String secteur, Pageable pageable) {
        return categorieProduitRepository.findAllBySecteuractiviteAndActiveIsTrue(secteur, pageable);
    }

    @Override
    public Page<CategorieProduit> getAllCategorieByClasse(String classe, Pageable pageable) {
        return categorieProduitRepository.findAllByClasse(classe, pageable);
    }

    @Override
    public Page<CategorieProduit> getAllCategorieByClasseIsActive(String classe, Pageable pageable) {
        return categorieProduitRepository.findAllByClasseAndActiveIsTrue(classe, pageable);
    }

    @Override
    public List<String> getAllNomCategorie() {
        return categorieProduitRepository.findAllNomCategorie();
    }

    static CategorieProduitDTO permuteCategorieProduitToCategorieProduitDTO(CategorieProduit categorieProduit){
        CategorieProduitDTO categorieProduitDTO = new CategorieProduitDTO();
        BeanUtils.copyProperties(categorieProduit, categorieProduitDTO);
        return categorieProduitDTO;
    }
}
