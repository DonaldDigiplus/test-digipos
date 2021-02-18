package com.devsdg.digipos.GestionUtilisateurs.DTO;

public class AppRoleDTO {
    private Long id_role;
    private String rolename;

    public AppRoleDTO(String rolename) {
        this.rolename = rolename;
    }

    public AppRoleDTO() {
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
}
