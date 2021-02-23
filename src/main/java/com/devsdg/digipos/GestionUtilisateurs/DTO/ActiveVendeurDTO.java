package com.devsdg.digipos.GestionUtilisateurs.DTO;

public class ActiveVendeurDTO {
    private Long idVendeur;
    private String nomBoutique;
    private boolean active_desactive;

    public Long getIdVendeur() {
        return idVendeur;
    }

    public void setIdVendeur(Long idVendeur) {
        this.idVendeur = idVendeur;
    }

    public String getNomBoutique() {
        return nomBoutique;
    }

    public void setNomBoutique(String nomBoutique) {
        this.nomBoutique = nomBoutique;
    }

    public boolean isActive_desactive() {
        return active_desactive;
    }

    public void setActive_desactive(boolean active_desactive) {
        this.active_desactive = active_desactive;
    }
}
