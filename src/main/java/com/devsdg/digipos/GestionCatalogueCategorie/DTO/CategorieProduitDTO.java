package com.devsdg.digipos.GestionCatalogueCategorie.DTO;

import com.devsdg.digipos.GestionCatalogueCategorie.Models.CatalogueProduit;
import java.util.Date;
import java.util.List;

public class CategorieProduitDTO {
    private Long id;
    private String nomCategorie;
    private String classe;
    private String secteuractivite;
    private String imageCategorie;
    private List<CatalogueProduit> catalogueProduits;
    private Date date;
    private boolean active;

    public CategorieProduitDTO(String nomCategorie, String classe, String secteuractivite) {
        this.nomCategorie = nomCategorie;
        this.classe = classe;
        this.secteuractivite = secteuractivite;
    }

    public CategorieProduitDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomCategorie() {
        return nomCategorie;
    }

    public void setNomCategorie(String nomCategorie) {
        this.nomCategorie = nomCategorie;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public String getSecteuractivite() {
        return secteuractivite;
    }

    public void setSecteuractivite(String secteuractivite) {
        this.secteuractivite = secteuractivite;
    }

    public String getImageCategorie() {
        return imageCategorie;
    }

    public void setImageCategorie(String imageCategorie) {
        this.imageCategorie = imageCategorie;
    }

    public List<CatalogueProduit> getCatalogueProduits() {
        return catalogueProduits;
    }

    public void setCatalogueProduits(List<CatalogueProduit> catalogueProduits) {
        this.catalogueProduits = catalogueProduits;
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
}
