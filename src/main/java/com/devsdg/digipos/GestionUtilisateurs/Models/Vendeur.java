package com.devsdg.digipos.GestionUtilisateurs.Models;

import com.devsdg.digipos.GestionBoutiques.Models.Boutique;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "id_user")
public class Vendeur extends ShopUser{

    private boolean activevendeur=true;

    @ManyToOne
    @JoinColumn(name = "idBoutique")
    private Boutique boutique;

    public boolean isActivevendeur() {
        return activevendeur;
    }

    public void setActivevendeur(boolean activevendeur) {
        this.activevendeur = activevendeur;
    }

    public Boutique getBoutique() {
        return boutique;
    }

    public void setBoutique(Boutique boutique) {
        this.boutique = boutique;
    }
}
