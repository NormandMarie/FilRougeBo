package com.m2i.filrougebo.dto;

import com.m2i.filrougebo.entity.Admin;

public class AdminDto {
    private int idAdmin;
    private String username;
    private boolean isSuperAdmin;
    private String firstName;
    private String lastName;
    private String email;

    public AdminDto(Admin admin) {
        this.idAdmin = admin.getIdAdmin();
        this.username = admin.getUsername();
        this.isSuperAdmin = admin.getIsSuperAdmin();
        this.firstName = admin.getFirstName();
        this.lastName = admin.getLastName();
        this.email = admin.getEmail();
    }

    public AdminDto(int idAdmin, String username, boolean isSuperAdmin,
                    String firstName, String lastName, String email) {
        this.idAdmin = idAdmin;
        this.username = username;
        this.isSuperAdmin = isSuperAdmin;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public int getIdAdmin() {
            return idAdmin;
        }

    public void setIdAdmin(int idAdmin) {
        this.idAdmin = idAdmin;
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

    public void setIsSuperAdmin(boolean isSuperAdmin) {
        this.isSuperAdmin = isSuperAdmin;
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


