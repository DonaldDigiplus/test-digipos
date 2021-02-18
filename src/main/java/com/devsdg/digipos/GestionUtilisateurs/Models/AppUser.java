package com.devsdg.digipos.GestionUtilisateurs.Models;


import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "userdigipos")
@Inheritance(strategy = InheritanceType.JOINED)
public class AppUser implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id_user;
    @Column(unique=true)
    private String username;
    @Column(unique = true)
    @Email(message = "Entrer une email valide")
    private String email;
    @Column(unique = true)
    private String phone;
    private String password;
    private String nomcomplet;
    private String cni;
    private boolean ativeuser = true ;
    @Column(columnDefinition = "text")
    private String photouser;
    @JsonFormat(timezone = "GMT+01:00")
    private Date date = new Date();
    private boolean client;
    private boolean staff;

    @ManyToMany(fetch = FetchType.EAGER)//EAGER veut dire qu'a chaque fois que je charge un utilisateur il charge aussi ses roles
    //FetchType.EAGER : indique que la relation doit être chargée en même temps que l'entité qui la porte.
    List<AppRole> Roles =new ArrayList<>();

    public AppUser() {}

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

    public boolean isAtiveuser() {
        return ativeuser;
    }

    public void setAtiveuser(boolean ativeuser) {
        this.ativeuser = ativeuser;
    }

    public String getPhotouser() {
        return photouser;
    }

    public void setPhotouser(String photouser) {
        this.photouser = photouser;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isClient() {
        return client;
    }

    public void setClient(boolean client) {
        this.client = client;
    }

    public boolean isStaff() {
        return staff;
    }

    public void setStaff(boolean staff) {
        this.staff = staff;
    }

    public List<AppRole> getRoles() {
        return Roles;
    }

    public void setRoles(List<AppRole> roles) {
        Roles = roles;
    }
}
