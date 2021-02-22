package com.devsdg.digipos.GestionBoutiques.DTO;

import java.util.Date;

public class BoutiqueDTO {

    private Long idBoutique;
    private String nomBoutique;
    private String lien;
    private String adressBoutique;
    private String pays;
    private String ville;
    private String quartier;
    private String secteur;
    private String emailBoutique;
    private String telephoneBoutique;
    private String photoboutique;
    private String description;
    private Date date;
    private boolean activeboutique;
    private String proprietaire;

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

    public boolean isActiveboutique() {
        return activeboutique;
    }

    public void setActiveboutique(boolean activeboutique) {
        this.activeboutique = activeboutique;
    }

    public String getProprietaire() {
        return proprietaire;
    }

    public void setProprietaire(String proprietaire) {
        this.proprietaire = proprietaire;
    }
}
