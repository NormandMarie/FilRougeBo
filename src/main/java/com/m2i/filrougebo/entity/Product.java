package com.m2i.filrougebo.entity;

import com.m2i.filrougebo.enums.SeasonalMonths;

import java.util.List;

public class Product {
    private int idProduct;
    private String productName;
    private String unit;
    private double pricePerUnit;
    private String imgUrl;
    private double vat;
    private String description;
    private int stock;
    private int idCategory;
    private List<SeasonalMonths> seasonalMonths;

    public Product() {
    }

    public Product(String productName, String unit, double pricePerUnit, String imgUrl, double vat, String description, int stock, int idCategory, List<SeasonalMonths> seasonalMonths) {
        this.productName = productName;
        this.unit = unit;
        this.pricePerUnit = pricePerUnit;
        this.imgUrl = imgUrl;
        this.vat = vat;
        this.description = description;
        this.stock = stock;
        this.idCategory = idCategory;
        this.seasonalMonths = seasonalMonths;
    }
    // constructeur sans liste seasonal, pour la méthode create. la donnée seasonal month est à préciser directement dans la table products_months!
    public Product(String productName, String unit, double pricePerUnit, String imgUrl, double vat, String description,int stock, int idCategory) {
        this.productName = productName;
        this.unit = unit;
        this.pricePerUnit = pricePerUnit;
        this.imgUrl = imgUrl;
        this.vat = vat;
        this.description = description;
        this.stock = stock;
        this.idCategory = idCategory;
    }

    public Product(int idProduct, String productName, String unit, double pricePerUnit, String imgUrl, double vat, String description,int stock, int idCategory, List<SeasonalMonths> seasonalMonths) {
        this.idProduct = idProduct;
        this.productName = productName;
        this.unit = unit;
        this.pricePerUnit = pricePerUnit;
        this.imgUrl = imgUrl;
        this.vat = vat;
        this.description = description;
        this.stock = stock;
        this.idCategory = idCategory;
        this.seasonalMonths = seasonalMonths;
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

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public List<SeasonalMonths> getSeasonalMonths() {
        return seasonalMonths;
    }

    public void setSeasonalMonths(List<SeasonalMonths> seasonalMonths) {
        this.seasonalMonths = seasonalMonths;
    }
}
