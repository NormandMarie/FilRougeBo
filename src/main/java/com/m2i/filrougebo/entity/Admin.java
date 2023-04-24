package com.m2i.filrougebo.entity;

public class Admin {
    private int idAdmin;
    private String username;
    private boolean isSuperAdmin=false;
    private String password;

    public Admin() {
    }

    public Admin(String username, boolean isSuperAdmin, String password) {
        this.username = username;
        this.isSuperAdmin = isSuperAdmin;
        this.password = password;
    }

    public Admin(int idAdmin, String username, boolean isSuperAdmin, String password) {
        this.idAdmin = idAdmin;
        this.username = username;
        this.isSuperAdmin = isSuperAdmin;
        this.password = password;
    }

    public int getIdAdmin() {
        return idAdmin;
    }

    public void setIdAmin(int idAmin) {
        this.idAdmin = idAmin;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean getIsSuperAdmin() {
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
