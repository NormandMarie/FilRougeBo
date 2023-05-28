package com.m2i.filrougebo.entity;

import jakarta.validation.constraints.Pattern;

public class Category {
    private int idCategory;
    @Pattern(regexp = "[a-zA-Z0-9 -.,'\\p{L}]{4,20}", message = "4 à 20 caractères.")
    private String name;

    public Category(int idCategory, String name) {
        this.idCategory = idCategory;
        this.name = name;
    }

    public Category(String name) {
        this.name = name;
    }

    public Category() {
    }

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
