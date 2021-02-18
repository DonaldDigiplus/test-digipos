package com.devsdg.digipos.GestionUtilisateurs.Models;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "id_user")
public class Vendeur extends ShopUser{

}
