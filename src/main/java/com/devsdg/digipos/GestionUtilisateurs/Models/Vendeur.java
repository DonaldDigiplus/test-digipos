package com.devsdg.digipos.GestionUtilisateurs.Models;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "id_user")
public class Vendeur extends ShopUser{

    private boolean activevendeur=true;

    public boolean isActivevendeur() {
        return activevendeur;
    }

    public void setActivevendeur(boolean activevendeur) {
        this.activevendeur = activevendeur;
    }
}
