package com.m2i.filrougebo.entity;

public class Admin {
    private int idAdmin;
    private String username;
    private Boolean isSuperAdmin;
    private String password;

    public Admin(int idAdmin, String username, Boolean isSuperAdmin, String password) {
        this.idAdmin = idAdmin;
        this.username = username;
        this.isSuperAdmin = isSuperAdmin;
        this.password = password;
    }
    public Admin(String username, Boolean isSuperAdmin, String password) {
        this.username = username;
        this.isSuperAdmin = isSuperAdmin;
        this.password = password;
    }

    public Admin() {
    }

    public int getIdAdmin() {
        return idAdmin;
    }

    public String getUsername() {
        return username;
    }

    public Boolean getSuperAdmin() {
        return isSuperAdmin;
    }

    public String getPassword() {
        return password;
    }

    public void setIdAdmin(int idAdmin) {
        this.idAdmin = idAdmin;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setSuperAdmin(Boolean superAdmin) {
        isSuperAdmin = superAdmin;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
