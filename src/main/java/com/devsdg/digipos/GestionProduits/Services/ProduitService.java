package com.devsdg.digipos.GestionProduits.Services;

import com.devsdg.digipos.Cloudinary.Services.ServiceCloudinary;
import com.devsdg.digipos.GestionBoutiques.Metiers.BoutiqueMetier;
import com.devsdg.digipos.GestionBoutiques.Models.Boutique;
import com.devsdg.digipos.GestionErreurs.ErrorMessages;
import com.devsdg.digipos.GestionProduits.DTO.FiltreBoutiqueCatalogue;
import com.devsdg.digipos.GestionProduits.DTO.ProduitDTO;
import com.devsdg.digipos.GestionProduits.Metier.ProduitMetier;
import com.devsdg.digipos.GestionProduits.Models.ImageProduit;
import com.devsdg.digipos.GestionProduits.Models.Produit;
import com.devsdg.digipos.GestionProduits.Repository.ImageRepository;
import com.devsdg.digipos.GestionProduits.Repository.ProduitRepository;
import com.devsdg.digipos.GestionStock.Metier.StockMetier;
import com.devsdg.digipos.GestionStock.Models.Stock;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ProduitService implements ProduitMetier {
    final
    ProduitRepository produitRepository;
    final
    ImageRepository imageRepository;
    final
    BoutiqueMetier boutiqueMetier;
    final
    StockMetier stockMetier;
    final
    ServiceCloudinary serviceCloudinary;

    public ProduitService(ProduitRepository produitRepository, ImageRepository imageRepository, BoutiqueMetier boutiqueMetier, StockMetier stockMetier, ServiceCloudinary serviceCloudinary) {
        this.produitRepository = produitRepository;
        this.imageRepository = imageRepository;
        this.boutiqueMetier = boutiqueMetier;
        this.stockMetier = stockMetier;
        this.serviceCloudinary = serviceCloudinary;
    }

    @Override
    public ProduitDTO saveProduit(ProduitDTO produitDTO) {
        Boutique boutique = boutiqueMetier.getBoutiqueByNomBoutique(produitDTO.getNomBoutique());
        if(boutique!=null){
            List<Produit> produitList = produitRepository.findAllByNomProduitAndBoutiquesProduit(
                    produitDTO.getNomProduit(), boutique);
            if(produitList.size()>1)
                throw new ErrorMessages("Impossible de poursuivre un produit du meme nom existe deja dans la boutique", HttpStatus.NOT_FOUND);

            Stock stock = new Stock();
            stock = stockMetier.saveStock(stock);

            Produit produit = new Produit();
            BeanUtils.copyProperties(produitDTO, produit);
            produit.setStock(stock);
            produit.setDateproduit(new Date());
            produit.setActiveproduit(true);
            produit.setAfficherprix(true);
            produit.setAppliqueReduction(true);
            produit.setPromoAcitve(false);
            produit.setManageStock(true);
            produit.setNomCategorie(produitDTO.getCatalogueProduit().getCategorieProduit().getNomCategorie());
            produit.setNomboutique(boutique.getNomBoutique());
            produit = produitRepository.save(produit);

            produit.setImage(serviceCloudinary.saveObjectOnCloudinary(
                    produit.getImage(),
                    "Produits",
                    boutique.getNomBoutique(),
                    produitDTO.getCatalogueProduit().getCategorieProduit().getSecteuractivite(),
                    produit.getNomProduit()
            ));

            List<ImageProduit> imageProduits = new ArrayList<>();
            if(produitDTO.getImageProduits()!=null){
                Produit finalProduit = produit;
                produitDTO.getImageProduits().forEach(ip->{
                    ip = imageRepository.save(ip);
                    ip.setImage(serviceCloudinary.saveObjectOnCloudinary(
                            ip.getImage(),
                            "Produits",
                            boutique.getNomBoutique(),
                            produitDTO.getCatalogueProduit().getCategorieProduit().getSecteuractivite(),
                            finalProduit.getNomProduit()+ip.getIdimage()
                    ));
                    imageProduits.add(imageRepository.save(ip));
                });
                produit.setImageProduits(imageProduits);
            }

            return permuteProduitToProduitDTO(produit);
        } else
            throw new ErrorMessages("Impossible de poursuivre car la boutique specifie n'existe pas", HttpStatus.NOT_FOUND);
    }

    @Override
    public ProduitDTO updateProduit(ProduitDTO produitDTO) {
        Produit produit=produitRepository.getOne(produitDTO.getIdProduit());

        produit.setNomProduit(produitDTO.getNomProduit());
        produit.setPrixProduit(produitDTO.getPrixProduit());
        produit.setImage(produitDTO.getImage());
        produit.setCaracteristiques(produitDTO.getCaracteristiques());
        produit.setFraisLivraisonPointRelais(produitDTO.getFraisLivraisonPointRelais());
        produit.setFraisLivraisonPointSpecifique(produitDTO.getFraisLivraisonPointSpecifique());
        produit.setPoids(produitDTO.getPoids());

        List<ImageProduit> imageProduits = new ArrayList<>();
        if(produitDTO.getImageProduits()!=null){
            Produit finalProduit = produit;
            produitDTO.getImageProduits().forEach(ip->{
                ip = imageRepository.save(ip);
                ip.setImage(serviceCloudinary.saveObjectOnCloudinary(
                        ip.getImage(),
                        "Produits",
                        produitDTO.getBoutiquesProduit().getNomBoutique(),
                        produitDTO.getCatalogueProduit().getCategorieProduit().getSecteuractivite(),
                        finalProduit.getNomProduit()+ip.getIdimage()
                ));
                imageProduits.add(imageRepository.save(ip));
            });
            produit.setImageProduits(imageProduits);
        }

        if (produit.getStock() == null) throw new ErrorMessages("le stock de ce produit n'existe pas bien vouloir ajouter avant de modifier !!!", HttpStatus.NOT_FOUND);

        return permuteProduitToProduitDTO(produit);
    }

    @Override
    public ProduitDTO getProduitById(Long id) {
        return permuteProduitToProduitDTO(produitRepository.getOne(id));
    }

    @Override
    public ProduitDTO getProduitByQcode(String qcode) {
        Produit produit = produitRepository.findByQcode(qcode);
        if(produit!=null){
            return permuteProduitToProduitDTO(produit);
        } else
            throw new ErrorMessages("Le QCode entree n'existe pas", HttpStatus.NOT_FOUND);
    }

    @Override
    public ProduitDTO getProduitByNomProduit(String nomproduit) {
        Produit produit = produitRepository.findByNomProduit(nomproduit);
        if(produit!=null){
            return permuteProduitToProduitDTO(produit);
        } else
            throw new ErrorMessages("Le Nom du produit entree n'existe pas", HttpStatus.NOT_FOUND);

    }

    @Override
    public ProduitDTO getProduitByNomAndBoutique(String nomProduit, Long idBoutique) {
        Boutique boutique = boutiqueMetier.getBoutiqueByIdBoutique(idBoutique);
        if ((boutique!=null)){
            Produit produit = produitRepository.findByNomProduitAndBoutiquesProduit(nomProduit, boutique);
            if(produit!=null){
                return permuteProduitToProduitDTO(produit);
            } else
                throw new ErrorMessages("Le QCode entree n'existe pas", HttpStatus.NOT_FOUND);
        } else
            throw new ErrorMessages("La boutique entree n'existe pas", HttpStatus.NOT_FOUND);
    }

    @Override
    public ProduitDTO getProduitByNomAndBoutiqueIsActive(String nomProduit, Long idBoutique) {
        Boutique boutique = boutiqueMetier.getBoutiqueByIdBoutique(idBoutique);
        if ((boutique!=null)){
            Produit produit = produitRepository.findByNomProduitAndBoutiquesProduitAndActiveproduitIsTrue(nomProduit, boutique);
            if(produit!=null){
                return permuteProduitToProduitDTO(produit);
            } else
                throw new ErrorMessages("Le QCode entree n'existe pas", HttpStatus.NOT_FOUND);
        } else
            throw new ErrorMessages("La boutique entree n'existe pas", HttpStatus.NOT_FOUND);
    }

    @Override
    public ProduitDTO AddProduitAssocie(Long idProduit, Long idProduitAssocie) {
        Produit produit_associer = produitRepository.findByIdProduit(idProduitAssocie);
        Produit produit = produitRepository.getOne(idProduit);
        if (produit_associer == null)
            throw new ErrorMessages("le produit à associer n'existe pas", HttpStatus.NOT_FOUND);

        produit.getListproduitAssociers().add(produit_associer);
        produit_associer.getListproduitAssociers().add(produit);

        return permuteProduitToProduitDTO(produit);
    }

    @Override
    public ProduitDTO removeProduitAssocie(Long idProduit, Long idProdutAssocie) {
        Produit produit_associer = produitRepository.findByIdProduit(idProdutAssocie);
        Produit produit =  produitRepository.findByIdProduit(idProduit);

        if (produit == null && produit_associer == null) throw new ErrorMessages("Un ou les produits associes sont inexistants!!!", HttpStatus.NOT_FOUND);

        assert produit != null;
        produit.getListproduitAssociers().remove(produit_associer);
        produit_associer.getListproduitAssociers().remove(produit);
        return permuteProduitToProduitDTO(produit);
    }

    @Override
    public List<ProduitDTO> getProduitAssocie(Long idProduit) {
        Produit produit = produitRepository.getOne(idProduit);
        List<Produit> produitList = new ArrayList<>(produit.getListproduitAssociers());

        return permuteProduitListToProduitDTOList(produitList);
    }

    @Override
    public List<String> getAllNomProduit() {
        return produitRepository.findAllNomProduit();
    }

    @Override
    public List<String> getAllNomProduitByBoutique(String nomboutique) {
            return produitRepository.findAllNomProduitByShop(nomboutique);
    }

    @Override
    public Page<Produit> getAllProduitByBoutique(String nomBoutique, Pageable pageable) {
        return  produitRepository.findAllByNomboutiqueIgnoreCaseLike(nomBoutique, pageable);

       /* Boutique boutique = boutiqueMetier.getBoutiqueByIdBoutique(idBoutique);
        if(boutique!=null){
            return produitRepository.findAllByBoutiquesProduit(boutique, pageable);
        } else
            throw new ErrorMessages("La boutique entree n'existe pas", HttpStatus.NOT_FOUND);*/
    }

    @Override
    public Page<Produit> getAllProduitByBoutiqueIsActive(Long idBoutique, Pageable pageable) {
        Boutique boutique = boutiqueMetier.getBoutiqueByIdBoutique(idBoutique);
        if(boutique!=null){
            return produitRepository.findAllByBoutiquesProduitAndActiveproduitIsTrue(boutique, pageable);
        } else
            throw new ErrorMessages("La boutique entree n'existe pas", HttpStatus.NOT_FOUND);
    }

    @Override
    public Page<Produit> getAllProduit(Pageable pageable) {
        return produitRepository.findAll(pageable);
    }

    @Override
    public Page<Produit> getAllProduitIsActive(Pageable pageable) {
        return produitRepository.findAllByActiveproduitIsTrue(pageable);
    }

    @Override
    public Page<Produit> getProdtuiByPage(String nomProduit, Pageable pageable) {
        return produitRepository.findAllByNomProduitIgnoreCaseLike(nomProduit, pageable);
    }

    @Override
    public Page<Produit> filtreBoutiqueCategorie(FiltreBoutiqueCatalogue filtreBoutiqueCatalogue, Pageable pageable) {
        Page<Produit> produitPage = null;
        if (filtreBoutiqueCatalogue.getBoutique() != null && filtreBoutiqueCatalogue.getCategorie() != null) {
            Boutique boutique = boutiqueMetier.getBoutiqueByNomBoutiqueIsActive(filtreBoutiqueCatalogue.getBoutique());
            produitPage = produitRepository.findAllByBoutiquesProduitAndNomCategorie(boutique, filtreBoutiqueCatalogue.getCategorie(), pageable);
        } else if (filtreBoutiqueCatalogue.getBoutique() != null && filtreBoutiqueCatalogue.getCategorie() == null) {
            Boutique boutique = boutiqueMetier.getBoutiqueByNomBoutiqueIsActive(filtreBoutiqueCatalogue.getBoutique());
            produitPage = produitRepository.findAllByBoutiquesProduit(boutique, pageable);
        } else if (filtreBoutiqueCatalogue.getBoutique() == null && filtreBoutiqueCatalogue.getCategorie() != null) {
            produitPage = produitRepository.findAllByNomCategorie(filtreBoutiqueCatalogue.getCategorie(), pageable);
        } else if (filtreBoutiqueCatalogue.getBoutique() == null && filtreBoutiqueCatalogue.getCategorie() == null) {
            produitPage = produitRepository.findAll(pageable);
        }
        return produitPage;
    }

    @Override
    public Page<Produit> findAllByNomboutiqueAndNomProduitIgnoreCaseLike(String nom_boutique, String nom_produit, Pageable pageable) {
        return produitRepository.findAllByNomboutiqueAndNomProduitIgnoreCaseLike(nom_boutique, '%'+nom_produit+'%', pageable);
    }

    @Override
    public boolean active_and_desactive_produit(Long idProduit) {
        Produit produit = produitRepository.getOne(idProduit);
        produit.setActiveproduit(!produit.isActiveproduit());
        return produit.isActiveproduit();
    }

    @Override
    public boolean active_and_desactive_reduction(Long idProduit) {
        Produit produit = produitRepository.getOne(idProduit);
        produit.setAppliqueReduction(!produit.isAppliqueReduction());
        return produit.isAppliqueReduction();
    }

    @Override
    public boolean active_and_desactive_prix(Long idProduit) {
        Produit produit = produitRepository.getOne(idProduit);
        produit.setAfficherprix(!produit.isAfficherprix());
        return produit.isAfficherprix();
    }

    @Override
    public boolean active_and_desactive_stock(Long idProduit) {
        Produit produit = produitRepository.getOne(idProduit);
        produit.setManageStock(!produit.isManageStock());
        return produit.isManageStock();
    }

    static ProduitDTO permuteProduitToProduitDTO(Produit produit){
        ProduitDTO produitDTO = new ProduitDTO();
        BeanUtils.copyProperties(produit, produitDTO);
        return produitDTO;
    }

    static List<ProduitDTO> permuteProduitListToProduitDTOList(List<Produit> produits){
        List<ProduitDTO> produitDTOList = new ArrayList<>();
        produits.forEach(p->{
            ProduitDTO appUserDTO = new ProduitDTO();
            BeanUtils.copyProperties(p, appUserDTO);
            produitDTOList.add(appUserDTO);
        });
        return produitDTOList;
    }
}
