package com.devsdg.digipos.GestionCatalogueCategorie.DTO;

import com.devsdg.digipos.GestionCatalogueCategorie.Models.CategorieProduit;
import java.util.Date;

public class CatalogueProduitDTO {
    private Long id;
    private CategorieProduit categorieProduit;
    private String nomProduit;
    private double prix;
    private String genreClient;
    private String image;
    private Date date;
    private boolean active;
    private String nomcategorie;

    public CatalogueProduitDTO(CategorieProduit categorieProduit, String nomProduit, String genreClient, double prix) {
        this.categorieProduit = categorieProduit;
        this.nomProduit = nomProduit;
        this.genreClient = genreClient;
        this.prix = prix;
    }

    public CatalogueProduitDTO() {
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

    public String getNomcategorie() {
        return nomcategorie;
    }

    public void setNomcategorie(String nomcategorie) {
        this.nomcategorie = nomcategorie;
    }
}
