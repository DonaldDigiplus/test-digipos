package com.devsdg.digipos.GestionUtilisateurs.Models;

import com.devsdg.digipos.GestionBoutiques.Models.Boutique;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "id_user")
public class Proprietaire extends ShopUser{
    @JsonIgnore
    @OneToOne(mappedBy = "proprietaire")
    private Boutique boutique;

    public Boutique getBoutique() {
        return boutique;
    }

    public void setBoutique(Boutique boutique) {
        this.boutique = boutique;
    }
}
