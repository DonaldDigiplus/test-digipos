package com.devsdg.digipos.GestionUtilisateurs.Models;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name = "id_user")
@Inheritance(strategy = InheritanceType.JOINED)
public class ShopUser extends AppUser {

}
