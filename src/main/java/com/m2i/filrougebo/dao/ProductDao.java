package com.m2i.filrougebo.dao;

import com.m2i.filrougebo.entity.Category;
import com.m2i.filrougebo.entity.Product;
import com.m2i.filrougebo.enums.SeasonalMonths;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductDao implements IntProductDao{
    Connection conn = ConnectionManager.getInstance();

    @Override
    public void create(Product entity) {
        try{
            PreparedStatement ps=conn.prepareStatement("INSERT INTO product (name,unit,pricePerUnit,imgUrl,vat,description,stock,idCategory) VALUES (?,?,?,?,?,?,?,?)");
            ps.setString(1,entity.getProductName());
            ps.setString(2,entity.getUnit());
            ps.setDouble(3,entity.getPricePerUnit());
            ps.setString(4,entity.getImgUrl());
            ps.setDouble(5,entity.getVat());
            ps.setString(6,entity.getDescription());
            ps.setInt(7,entity.getStock());
            ps.setInt(8,entity.getIdCategory());
            ps.executeUpdate();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();

        try {
            PreparedStatement ps2 = conn.prepareStatement("SELECT *,ps.seasonName FROM product p" +
                    "INNER JOIN product_seasons ps" +
                    "ON p.id = ps.idProduct " +
                    "INNER JOIN seasonal_months sm" +
                    "ON sm.id = ps.idSeasonalMonth");

            Map<Integer, Product> productMap = new HashMap<>(); // MAP temporaire
            ResultSet rs = ps2.executeQuery();
            while (rs.next()){
                int productId = rs.getInt("id");
                Product product = productMap.get("id");
                if(product==null){ // first time passing by this product_id in result set
                    product = new Product();
                    product.setIdProduct(productId);
                    product.setProductName(rs.getString("name"));
                    product.setUnit(rs.getString("unit"));
                    product.setPricePerUnit(rs.getDouble("pricePerUnit"));
                    product.setImgUrl(rs.getString("imgUrl"));
                    product.setVat(rs.getDouble("vat"));
                    product.setDescription(rs.getString("description"));
                    product.setStock(rs.getInt("stock"));
                    product.setSeasonalMonths(new ArrayList<>());
                    productMap.put(productId,product); // storing the product in the map
                    products.add(product);
                }
                SeasonalMonths seasonalMonth = SeasonalMonths.valueOf(rs.getString("seasonName"));
                product.getSeasonalMonths().add(seasonalMonth);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return products;
    }

    @Override
    public Product findById(Integer integer) {
        Product product=new Product();
        try{
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM product WHERE id = ? ");
            ps.setInt(1,integer);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                product.setIdProduct(rs.getInt("id"));
                product.setProductName(rs.getString("name"));
                product.setUnit(rs.getString("unit"));
                product.setPricePerUnit(rs.getDouble("pricePerUnit"));
                product.setImgUrl(rs.getString("imgUrl"));
                product.setVat(rs.getDouble("vat"));
                product.setDescription(rs.getString("description"));
                product.setStock(rs.getInt("stock"));
                //product.setSeasonalMonths(new ArrayList<>());
                SeasonalMonths seasonalMonth = SeasonalMonths.valueOf(rs.getString("seasonName"));
                product.getSeasonalMonths().add(seasonalMonth);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return product;
    }

    @Override
    public void update(Product entity) {
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("UPDATE product SET name=?,unit=?,pricePerUnit=?,imgUrl=?,vat=?,description=?,stock=?,idCategory=? WHERE id=?");
            preparedStatement.setString(1, entity.getProductName());
            preparedStatement.setString(2, entity.getUnit());
            preparedStatement.setDouble(3, entity.getPricePerUnit());
            preparedStatement.setString(4, entity.getImgUrl());
            preparedStatement.setDouble(5, entity.getVat());
            preparedStatement.setString(6, entity.getDescription());
            preparedStatement.setInt(7, entity.getStock());
            preparedStatement.setInt(8, entity.getIdCategory());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void delete(Product entity) {
        try{
            PreparedStatement ps = conn.prepareStatement("DELETE FROM product WHERE id = ?");
            ps.setInt(1,entity.getIdProduct());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
