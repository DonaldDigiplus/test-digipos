package com.devsdg.digipos.GestionUtilisateurs.DTO;

import java.util.Date;

public class AppUserDTO {
    private Long id_user;
    private String username;
    private String email;
    private String phone;
    private String password;
    private String nomcomplet;
    private String cni;
    private String photouser;
    private boolean client;
    private boolean staff;
    private boolean isAdmin;
    private boolean isProprietaire;
    private boolean isVendeur;
    private boolean isSupport;
    private Date date = new Date();

    public AppUserDTO() {
    }

    public AppUserDTO(String username,String password, String email, String phone,boolean isAdmin) {
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.isAdmin=true;
    }

    public Long getId_user() {
        return id_user;
    }

    public void setId_user(Long id_user) {
        this.id_user = id_user;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNomcomplet() {
        return nomcomplet;
    }

    public void setNomcomplet(String nomcomplet) {
        this.nomcomplet = nomcomplet;
    }

    public String getCni() {
        return cni;
    }

    public void setCni(String cni) {
        this.cni = cni;
    }

    public String getPhotouser() {
        return photouser;
    }

    public void setPhotouser(String photouser) {
        this.photouser = photouser;
    }

    public boolean isStaff() {
        return staff;
    }

    public void setStaff(boolean staff) {
        this.staff = staff;
    }

    public boolean isClient() {
        return client;
    }

    public void setClient(boolean client) {
        this.client = client;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public boolean isProprietaire() {
        return isProprietaire;
    }

    public void setProprietaire(boolean proprietaire) {
        isProprietaire = proprietaire;
    }

    public boolean isVendeur() {
        return isVendeur;
    }

    public void setVendeur(boolean vendeur) {
        isVendeur = vendeur;
    }

    public boolean isSupport() {
        return isSupport;
    }

    public void setSupport(boolean support) {
        isSupport = support;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
