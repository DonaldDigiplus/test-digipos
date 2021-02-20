package com.devsdg.digipos.GestionUtilisateurs.Models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "PasswordEntity")
public class PasswordResetTokenEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String token;

    @OneToOne
    @JoinColumn(name = "id_user")
    private AppUser userdetails;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public AppUser getUserdetails() {
        return userdetails;
    }

    public void setUserdetails(AppUser userdetails) {
        this.userdetails = userdetails;
    }
}
