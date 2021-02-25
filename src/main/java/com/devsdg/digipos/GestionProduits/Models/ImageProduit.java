package com.devsdg.digipos.GestionProduits.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class ImageProduit implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long idimage;
    @Column(columnDefinition = "text")
    private String image;
    private String public_id;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "idProduit")
    private Produit produit;

    public ImageProduit(Long idimage, String image, String public_id, Produit produit) {
        this.idimage = idimage;
        this.image = image;
        this.public_id = public_id;
        this.produit = produit;
    }

    public ImageProduit() {

    }

    public Long getIdimage() {
        return idimage;
    }

    public void setIdimage(Long idimage) {
        this.idimage = idimage;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPublic_id() {
        return public_id;
    }

    public void setPublic_id(String publi_id) {
        this.public_id = publi_id;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }
}
