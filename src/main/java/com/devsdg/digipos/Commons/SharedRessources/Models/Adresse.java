package com.devsdg.digipos.Commons.SharedRessources.Models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "adresse")
public class Adresse implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id_adresse;

}
