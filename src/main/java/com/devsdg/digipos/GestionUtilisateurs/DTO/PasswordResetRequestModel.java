package com.devsdg.digipos.GestionUtilisateurs.DTO;

import com.devsdg.digipos.GestionUtilisateurs.Models.AppUser;

public class PasswordResetRequestModel {

    private Long id;
    private String password;
    private String token;
    private String email;
    private AppUser userdetails;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public AppUser getUserdetails() {
        return userdetails;
    }

    public void setUserdetails(AppUser userdetails) {
        this.userdetails = userdetails;
    }
}
