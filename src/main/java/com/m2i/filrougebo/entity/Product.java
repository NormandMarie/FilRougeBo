package com.m2i.filrougebo.entity;

import com.m2i.filrougebo.enums.Month;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.URL;

import java.util.ArrayList;
import java.util.List;

public class Product {
    private int id;
    @Pattern(regexp = "[a-zA-Z0-9]{4,20}", message = "4 à 20 caractères.")
    private String name;

    //TODO: option select ou form control pour unit ?
    private String unit;
    @DecimalMin(value = "0.0", message = "Le prix doit être supérieur ou égal à 0.")
    private double pricePerUnit;
    @URL
    private String imgUrl;
    private double vat;
    @Size(max = 100, message = "La description doit contenir au maximum 100 caractères.")
    @Pattern(regexp = "[a-zA-Z0-9.,]{0,100}", message = "La description ne peut contenir que des lettres, des chiffres, des points et des virgules.")
    private String description;
    @DecimalMin(value = "0.0", message = "Le stock doit être supérieur ou égal à 0.")
    private double stock;
    private Category category;
    //TODO: Change to EnumSet?
    private List<Month> seasonalMonths;

    public Product() {
        this.seasonalMonths=new ArrayList<>();
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
