package com.m2i.filrougebo.entity;

public class Admin {
    private int idAmin;
    private String username;
    private boolean isSuperAdmin;
    private String password;

    public Admin() {
    }

    public Admin(String username, boolean isSuperAdmin, String password) {
        this.username = username;
        this.isSuperAdmin = isSuperAdmin;
        this.password = password;
    }

    public Admin(int idAmin, String username, boolean isSuperAdmin, String password) {
        this.idAmin = idAmin;
        this.username = username;
        this.isSuperAdmin = isSuperAdmin;
        this.password = password;
    }

    public int getIdAmin() {
        return idAmin;
    }

    public void setIdAmin(int idAmin) {
        this.idAmin = idAmin;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isSuperAdmin() {
        return isSuperAdmin;
    }

    public void setSuperAdmin(boolean superAdmin) {
        isSuperAdmin = superAdmin;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
