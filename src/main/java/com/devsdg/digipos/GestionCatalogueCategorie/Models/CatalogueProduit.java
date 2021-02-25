package com.devsdg.digipos.GestionCatalogueCategorie.Models;

import com.devsdg.digipos.GestionProduits.Models.Produit;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

@Entity
public class CatalogueProduit implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "idCategorie")
    private CategorieProduit categorieProduit;
    @Column(unique = true)
    private String nomProduit;

    private double prix= 0;

    private String genreClient;
    @Column(columnDefinition = "text")
    private String image;
    @JsonFormat(timezone = "GMT+01:00")
    private Date date=new Date();
    @Column()
    private boolean active=true;

    public CatalogueProduit() {
    }

    public CatalogueProduit(CategorieProduit categorieProduit, String nomProduit, String genreClient, String image) {
        this.categorieProduit = categorieProduit;
        this.nomProduit = nomProduit;
        this.genreClient = genreClient;
        this.image = image;
    }

    public CatalogueProduit(CategorieProduit categorieProduit, String nomProduit, String genreClient, String image, double prix) {
        this.categorieProduit = categorieProduit;
        this.nomProduit = nomProduit;
        this.genreClient = genreClient;
        this.image = image;
        this.prix = prix;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CategorieProduit getCategorieProduit() {
        return categorieProduit;
    }

    public void setCategorieProduit(CategorieProduit categorieProduit) {
        this.categorieProduit = categorieProduit;
    }

    public String getNomProduit() {
        return nomProduit;
    }

    public void setNomProduit(String nomProduit) {
        this.nomProduit = nomProduit;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public String getGenreClient() {
        return genreClient;
    }

    public void setGenreClient(String genreClient) {
        this.genreClient = genreClient;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @OneToMany(mappedBy = "catalogueProduit")
    private Collection<Produit> produit;

    public Collection<Produit> getProduit() {
        return produit;
    }

    public void setProduit(Collection<Produit> produit) {
        this.produit = produit;
    }
}
