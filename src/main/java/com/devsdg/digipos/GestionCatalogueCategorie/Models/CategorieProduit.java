package com.devsdg.digipos.GestionCatalogueCategorie.Models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
public class CategorieProduit implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idCategorie")
    private Long id;
    @Column(unique = true)
    private String nomCategorie;
    private String classe;
    private String secteuractivite;

    private String imageCategorie;
    @JsonIgnore
    @OneToMany(mappedBy = "categorieProduit")
    private List<CatalogueProduit> catalogueProduits;
    @JsonFormat(timezone = "GMT+01:00")
    private Date date=new Date();
    private boolean active=true;

    public CategorieProduit(String nomCategorie, String classe, String secteuractivite, String gamme) {
        this.nomCategorie = nomCategorie;
        this.classe = classe;
        this.secteuractivite = secteuractivite;

    }

    public CategorieProduit(String nomCategorie, String classe, String secteuractivite) {
        this.nomCategorie = nomCategorie;
        this.classe = classe;
        this.secteuractivite = secteuractivite;
    }

    public CategorieProduit() {
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
