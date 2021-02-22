package com.devsdg.digipos.GestionBoutiques.Models;

import com.devsdg.digipos.GestionUtilisateurs.Models.Proprietaire;
import com.devsdg.digipos.GestionUtilisateurs.Models.Vendeur;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Entity
public class Boutique implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idBoutique;
    @Column(unique = true)
    private String nomBoutique;
    @Column(unique = true)
    private String lien;
    private String adressBoutique;
    private String pays;
    private String ville;
    private String quartier;
    private String secteur;
    @Column(unique = true)
    @Email(message = "Email should be valid")
    private String emailBoutique;
    @Column(unique = true)
    private String telephoneBoutique;
    @Column(columnDefinition = "text")
    private String photoboutique;
    @Column(columnDefinition = "text")
    private String description;
    @JsonFormat(timezone = "GMT+01:00")
    private Date date = new Date();
    private Date dateLastModification;
    private boolean activeboutique = true;

    //Entity relation map
    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "id_user")
    private Proprietaire proprietaire;
    @JsonIgnore
    @OneToMany(mappedBy = "boutique")
    private Collection<Vendeur> vendeurs = new ArrayList<>();

    public Long getIdBoutique() {
        return idBoutique;
    }

    public void setIdBoutique(Long idBoutique) {
        this.idBoutique = idBoutique;
    }

    public String getNomBoutique() {
        return nomBoutique;
    }

    public void setNomBoutique(String nomBoutique) {
        this.nomBoutique = nomBoutique;
    }

    public String getLien() {
        return lien;
    }

    public void setLien(String lien) {
        this.lien = lien;
    }

    public String getAdressBoutique() {
        return adressBoutique;
    }

    public void setAdressBoutique(String adressBoutique) {
        this.adressBoutique = adressBoutique;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getQuartier() {
        return quartier;
    }

    public void setQuartier(String quartier) {
        this.quartier = quartier;
    }

    public String getSecteur() {
        return secteur;
    }

    public void setSecteur(String secteur) {
        this.secteur = secteur;
    }

    public String getEmailBoutique() {
        return emailBoutique;
    }

    public void setEmailBoutique(String emailBoutique) {
        this.emailBoutique = emailBoutique;
    }

    public String getTelephoneBoutique() {
        return telephoneBoutique;
    }

    public void setTelephoneBoutique(String telephoneBoutique) {
        this.telephoneBoutique = telephoneBoutique;
    }

    public String getPhotoboutique() {
        return photoboutique;
    }

    public void setPhotoboutique(String photoboutique) {
        this.photoboutique = photoboutique;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDateLastModification() {
        return dateLastModification;
    }

    public void setDateLastModification(Date dateLastModification) {
        this.dateLastModification = dateLastModification;
    }

    public boolean isActiveboutique() {
        return activeboutique;
    }

    public void setActiveboutique(boolean activeboutique) {
        this.activeboutique = activeboutique;
    }

    public Proprietaire getProprietaire() {
        return proprietaire;
    }

    public void setProprietaire(Proprietaire proprietaire) {
        this.proprietaire = proprietaire;
    }

    public Collection<Vendeur> getVendeurs() {
        return vendeurs;
    }

    public void setVendeurs(Collection<Vendeur> vendeurs) {
        this.vendeurs = vendeurs;
    }
}
