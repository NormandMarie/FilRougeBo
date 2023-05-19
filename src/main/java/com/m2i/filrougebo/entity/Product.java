package com.m2i.filrougebo.entity;

import com.m2i.filrougebo.enums.Month;

import java.util.List;

public class Product {
    private int id;
    private String name;
    private String unit;
    private double pricePerUnit;
    private String imgUrl;
    private double vat;
    private String description;
    private double stock;
    private Category category;
    //TODO: Change to EnumSet?
    private List<Month> seasonalMonths;

    public Product() {
    }

    public Product(String name, String unit, double pricePerUnit, String imgUrl, double vat,
                   String description, double stock, Category category, List<Month> seasonalMonths) {
        this.name = name;
        this.unit = unit;
        this.pricePerUnit = pricePerUnit;
        this.imgUrl = imgUrl;
        this.vat = vat;
        this.description = description;
        this.stock = stock;
        this.category = category;
        this.seasonalMonths = seasonalMonths;
    }

    public Product(int id, String name, String unit, double pricePerUnit, String imgUrl, double vat,
                   String description, double stock, Category category, List<Month> seasonalMonths) {
        this.id = id;
        this.name = name;
        this.unit = unit;
        this.pricePerUnit = pricePerUnit;
        this.imgUrl = imgUrl;
        this.vat = vat;
        this.description = description;
        this.stock = stock;
        this.category = category;
        this.seasonalMonths = seasonalMonths;
    }

    @Override
    public String toString() {
        return "Product{" +
                "idProduct=" + id +
                ", productName='" + name + '\'' +
                ", unit='" + unit + '\'' +
                ", pricePerUnit=" + pricePerUnit +
                ", imgUrl='" + imgUrl + '\'' +
                ", vat=" + vat +
                ", description='" + description + '\'' +
                ", stock=" + stock +
                ", category=" + category +
                ", seasonalMonths=" + seasonalMonths +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public double getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(double pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public double getVat() {
        return vat;
    }

    public void setVat(double vat) {
        this.vat = vat;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getStock() {
        return stock;
    }

    public void setStock(double stock) {
        this.stock = stock;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Month> getSeasonalMonths() {
        return seasonalMonths;
    }

    public void setSeasonalMonths(List<Month> seasonalMonths) {
        this.seasonalMonths = seasonalMonths;
    }

}
