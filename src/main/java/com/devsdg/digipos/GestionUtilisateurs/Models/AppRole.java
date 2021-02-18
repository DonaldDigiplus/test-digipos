package com.devsdg.digipos.GestionUtilisateurs.Models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "app_role")
public class AppRole implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id_role;
    private String rolename;
    private final Date date=new Date();

    public AppRole() {
    }
    public AppRole(String rolename) {
        this.rolename = rolename;
    }

    public Long getId_role() {
        return id_role;
    }

    public void setId_role(Long id_role) {
        this.id_role = id_role;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public Date getDate() {
        return date;
    }
}
