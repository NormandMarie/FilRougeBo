package com.m2i.filrougebo.entity;

public class Admin {
    private int idAdmin;
    private String username;
    private boolean isSuperAdmin = false;
    private String password;
    private String firstName;
    private String lastName;
    private String email;

    public Admin() {

    }

    // TODO: DELETE
    public Admin(int idAdmin, String username, boolean isSuperAdmin, String password) {
        this.idAdmin = idAdmin;
        this.username = username;
        this.password = password;
    }

    public Admin(String username, String password,
                 String firstName, String lastName, String email) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public Admin(String username, boolean isSuperAdmin, String password,
                 String firstName, String lastName, String email) {
        this.username = username;
        this.isSuperAdmin = isSuperAdmin;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public Admin(int idAdmin, String username, boolean isSuperAdmin, String password,
                 String firstName, String lastName, String email) {
        this.idAdmin = idAdmin;
        this.username = username;
        this.isSuperAdmin = isSuperAdmin;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
