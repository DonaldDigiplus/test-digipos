package com.devsdg.digipos.GestionProduits.Models;

import com.devsdg.digipos.GestionBoutiques.Models.Boutique;
import com.devsdg.digipos.GestionCatalogueCategorie.Models.CatalogueProduit;
import com.devsdg.digipos.GestionStock.Models.Stock;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Produit implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long idProduit;
    private String nomProduit;
    private double prixProduit;
    private boolean afficherprix;
    private boolean appliqueReduction =false;// cette variable permet de donner l'autorisation a un produit qui va subir une reduction
    private String nomCategorie;
    private String referenceProduit;
    @Column(columnDefinition = "text")
    private String description;
    @Column(columnDefinition = "text")
    private String caracteristiques;
    @Column(columnDefinition = "text")
    private String image;
    private boolean arrivage=true;
    private String qcode="";
    private int quantiteEnStock;
    private String marque;
    private String gamme;
    private boolean activeproduit=true;//elle est à false car cest le proprietaire qui initie l'ajout d'un produit et l'admin central peut l'activer
    private double fraisLivraisonPointRelais;
    private double fraisLivraisonPointSpecifique;
    @Column(columnDefinition = "boolean default 'false'")
    private boolean activeprixlivraison;
    @Column(columnDefinition = "int default '0'")
    private int poids=0;
    /* popularite du produit */
    private BigDecimal purchases;
    private int nbredevues;
    private int vues;
    private int nbreDeFavoris;
    /* **************/
    @JsonFormat(timezone = "GMT+01:00")
    private Date dateproduit=new Date();
    @JsonFormat(timezone = "GMT+01:00")
    private  Date dateDebutPromotion;
    @JsonFormat(timezone = "GMT+01:00")
    private  Date dateFinPromotion;
    private  double prixpromotionnel=0;
    private  double pourcentagepromotion=0;
    private Boolean promoAcitve = false;
    @ManyToOne
    private Boutique boutiquesProduit;
    @JsonIgnore
    @Fetch(value = FetchMode.SUBSELECT)
    @ManyToMany(cascade = CascadeType.REMOVE)
    private List<Produit> listproduitAssociers = new ArrayList<>();
    @OneToMany(mappedBy = "produit")
    private List<ImageProduit> imageProduits=new ArrayList<>();
    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "idcatalogue")
    private CatalogueProduit catalogueProduit;
    private boolean manageStock;
    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "idstock")
    private Stock stock;

    public Produit() {
        this.appliqueReduction=false;
        this.arrivage=true;
        this.activeproduit=true;
        this.poids=0;
        this.dateproduit=new Date();
        this.prixpromotionnel=0;
        this.pourcentagepromotion=0;
        this.promoAcitve=false;
        this.listproduitAssociers = new ArrayList<>();
        this.imageProduits = new ArrayList<>();
    }



    public Long getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(Long idproduit) {
        this.idProduit = idproduit;
    }

    public String getNomProduit() {
        return nomProduit;
    }

    public void setNomProduit(String nomProduit) {
        this.nomProduit = nomProduit;
    }

    public double getPrixProduit() {
        return prixProduit;
    }

    public void setPrixProduit(double prixProduit) {
        this.prixProduit = prixProduit;
    }

    public boolean isAfficherprix() {
        return afficherprix;
    }

    public void setAfficherprix(boolean afficherprix) {
        this.afficherprix = afficherprix;
    }

    public boolean isAppliqueReduction() {
        return appliqueReduction;
    }

    public void setAppliqueReduction(boolean appliqueReduction) {
        this.appliqueReduction = appliqueReduction;
    }

    public String getNomCategorie() {
        return nomCategorie;
    }

    public void setNomCategorie(String nomCategorie) {
        this.nomCategorie = nomCategorie;
    }

    public String getReferenceProduit() {
        return referenceProduit;
    }

    public void setReferenceProduit(String referenceProduit) {
        this.referenceProduit = referenceProduit;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCaracteristiques() {
        return caracteristiques;
    }

    public void setCaracteristiques(String caracteristiques) {
        this.caracteristiques = caracteristiques;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isArrivage() {
        return arrivage;
    }

    public void setArrivage(boolean arrivage) {
        this.arrivage = arrivage;
    }

    public String getQcode() {
        return qcode;
    }

    public void setQcode(String qcode) {
        this.qcode = qcode;
    }

    public int getQuantiteEnStock() {
        return quantiteEnStock;
    }

    public void setQuantiteEnStock(int quantiteEnStock) {
        this.quantiteEnStock = quantiteEnStock;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getGamme() {
        return gamme;
    }

    public void setGamme(String gamme) {
        this.gamme = gamme;
    }

    public boolean isActiveproduit() {
        return activeproduit;
    }

    public void setActiveproduit(boolean activeproduit) {
        this.activeproduit = activeproduit;
    }

    public double getFraisLivraisonPointRelais() {
        return fraisLivraisonPointRelais;
    }

    public void setFraisLivraisonPointRelais(double fraisLivraisonPointRelais) {
        this.fraisLivraisonPointRelais = fraisLivraisonPointRelais;
    }

    public double getFraisLivraisonPointSpecifique() {
        return fraisLivraisonPointSpecifique;
    }

    public void setFraisLivraisonPointSpecifique(double fraisLivraisonPointSpecifique) {
        this.fraisLivraisonPointSpecifique = fraisLivraisonPointSpecifique;
    }

    public boolean isActiveprixlivraison() {
        return activeprixlivraison;
    }

    public void setActiveprixlivraison(boolean activeprixlivraison) {
        this.activeprixlivraison = activeprixlivraison;
    }

    public int getPoids() {
        return poids;
    }

    public void setPoids(int poids) {
        this.poids = poids;
    }

    public BigDecimal getPurchases() {
        return purchases;
    }

    public void setPurchases(BigDecimal purchases) {
        this.purchases = purchases;
    }

    public int getNbredevues() {
        return nbredevues;
    }

    public void setNbredevues(int nbredevues) {
        this.nbredevues = nbredevues;
    }

    public int getVues() {
        return vues;
    }

    public void setVues(int vues) {
        this.vues = vues;
    }

    public int getNbreDeFavoris() {
        return nbreDeFavoris;
    }

    public void setNbreDeFavoris(int nbreDeFavoris) {
        this.nbreDeFavoris = nbreDeFavoris;
    }

    public Date getDateproduit() {
        return dateproduit;
    }

    public void setDateproduit(Date dateproduit) {
        this.dateproduit = dateproduit;
    }

    public Date getDateDebutPromotion() {
        return dateDebutPromotion;
    }

    public void setDateDebutPromotion(Date dateDebutPromotion) {
        this.dateDebutPromotion = dateDebutPromotion;
    }

    public Date getDateFinPromotion() {
        return dateFinPromotion;
    }

    public void setDateFinPromotion(Date dateFinPromotion) {
        this.dateFinPromotion = dateFinPromotion;
    }

    public double getPrixpromotionnel() {
        return prixpromotionnel;
    }

    public void setPrixpromotionnel(double prixpromotionnel) {
        this.prixpromotionnel = prixpromotionnel;
    }

    public double getPourcentagepromotion() {
        return pourcentagepromotion;
    }

    public void setPourcentagepromotion(double pourcentagepromotion) {
        this.pourcentagepromotion = pourcentagepromotion;
    }

    public Boolean getPromoAcitve() {
        return promoAcitve;
    }

    public void setPromoAcitve(Boolean promoAcitve) {
        this.promoAcitve = promoAcitve;
    }

    public Boutique getBoutiquesProduit() {
        return boutiquesProduit;
    }

    public void setBoutiquesProduit(Boutique boutiquesProduit) {
        this.boutiquesProduit = boutiquesProduit;
    }

    public List<Produit> getListproduitAssociers() {
        return listproduitAssociers;
    }

    public void setListproduitAssociers(List<Produit> listproduitAssociers) {
        this.listproduitAssociers = listproduitAssociers;
    }

    public List<ImageProduit> getImageProduits() {
        return imageProduits;
    }

    public void setImageProduits(List<ImageProduit> imageProduits) {
        this.imageProduits = imageProduits;
    }

    public CatalogueProduit getCatalogueProduit() {
        return catalogueProduit;
    }

    public void setCatalogueProduit(CatalogueProduit catalogueProduit) {
        this.catalogueProduit = catalogueProduit;
    }

    public boolean isManageStock() {
        return manageStock;
    }

    public void setManageStock(boolean manageStock) {
        this.manageStock = manageStock;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }
}
