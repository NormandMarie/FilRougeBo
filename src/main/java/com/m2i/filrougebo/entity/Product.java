package com.m2i.filrougebo.entity;

import com.m2i.filrougebo.enums.Month;

import java.util.List;

public class Product {
    //TODO: rename to id ?
    private int idProduct;

    // TODO: rename to name?
    private String productName;
    private String unit;
    private double pricePerUnit;
    private String imgUrl;
    private double vat;
    private String description;
    private int stock;
    private Category category;
    private List<Month> seasonalMonths;

    public Product() {
    }

    public Product(String productName, String unit, double pricePerUnit, String imgUrl, double vat,
                   String description, int stock, Category category, List<Month> seasonalMonths) {
        this.productName = productName;
        this.unit = unit;
        this.pricePerUnit = pricePerUnit;
        this.imgUrl = imgUrl;
        this.vat = vat;
        this.description = description;
        this.stock = stock;
        this.category = category;
        this.seasonalMonths = seasonalMonths;
    }
    // constructeur sans liste seasonal, pour la méthode create. la donnée seasonal month est à préciser directement dans la table products_months!
//    public Product(String productName, String unit, double pricePerUnit, String imgUrl, double vat, String description, int stock, int idCategory) {
//        this.productName = productName;
//        this.unit = unit;
//        this.pricePerUnit = pricePerUnit;
//        this.imgUrl = imgUrl;
//        this.vat = vat;
//        this.description = description;
//        this.stock = stock;
//        this.idCategory = idCategory;
//    }

    public Product(int idProduct, String productName, String unit, double pricePerUnit, String imgUrl, double vat,
                   String description, int stock, Category category, List<Month> seasonalMonths) {
        this.idProduct = idProduct;
        this.productName = productName;
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
                "idProduct=" + idProduct +
                ", productName='" + productName + '\'' +
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

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
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

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
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
