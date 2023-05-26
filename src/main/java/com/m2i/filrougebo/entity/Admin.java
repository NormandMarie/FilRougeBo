package com.m2i.filrougebo.entity;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

public class Admin {
    private int idAdmin;
    @Pattern(regexp = "[a-zA-Z0-9]{4,20}", message = "4 à 20 caractères.")
    @NotEmpty
    private String username;
    private boolean isSuperAdmin = false;
    @Pattern(regexp = "[a-zA-Z0-9]{4,20}", message = "4 à 20 lettres ou chiffres.")
    @NotEmpty
    private String password;
    @Pattern(regexp = "[a-zA-Z]{2,20}", message = "2 à 20 lettres.")
    private String firstName;
    @Pattern(regexp = "[a-zA-Z]{2,20}", message = "2 à 20 lettres.")
    private String lastName;
    //@Email(message = "Email invalide")
    @Pattern(regexp = "^[A-Za-z0-9-]+(\\.[A-Za-z0-9-]{2,}){0,1}@([A-Za-z0-9-]+\\.)+[A-Za-z]{2,}$", message = "Email invalide")
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
