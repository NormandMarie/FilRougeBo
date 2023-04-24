package com.m2i.filrougebo.dao;

import com.m2i.filrougebo.entity.Category;
import com.m2i.filrougebo.entity.Product;
import com.m2i.filrougebo.enums.Month;

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

    private Product mapToProduct(ResultSet resultSet) throws SQLException {

        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        String unit = resultSet.getString("unit");
        double pricePerUnit = resultSet.getDouble("pricePerUnit");
        String imgUrl = resultSet.getString("imgUrl");
        double vat = resultSet.getDouble("vat");
        String description = resultSet.getString("description");
        int stock = resultSet.getInt("stock");

        int idCategory = resultSet.getInt("idCategory");

        CategoryDao categoryDAO = new CategoryDao();
        Category category = categoryDAO.findById(idCategory);

        List<Month> seasonalMonths = new ArrayList<>();
        MonthDAO monthDAO = new MonthDAO();
        seasonalMonths = monthDAO.findAllMonthsPerProduct(id);

        return new Product(id, name, unit, pricePerUnit, imgUrl, vat, description, stock, category, seasonalMonths);


    }

    @Override
    public void create(Product entity) {

        String sqlQuery =
                "INSERT INTO Products(name, unit, pricePerUnit, imgUrl, vat, description, stock, idCategory)" +
                " VALUES (?,?,?,?,?,?,?,?)";

        try{
            PreparedStatement ps = conn.prepareStatement(sqlQuery);

            ps.setString(1,entity.getProductName());
            ps.setString(2,entity.getUnit());
            ps.setDouble(3,entity.getPricePerUnit());
            ps.setString(4,entity.getImgUrl());
            ps.setDouble(5,entity.getVat());
            ps.setString(6,entity.getDescription());
            ps.setInt(7,entity.getStock());
            ps.setInt(8,entity.getCategory().getIdCategory());

            ps.executeUpdate();

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Product> findAll() {
        List<Product> productList = new ArrayList<>();
        String sqlQuery = "SELECT id, name, unit, pricePerUnit, imgUrl, vat, description, stock, idCategory FROM Products";

        try {

            PreparedStatement ps = conn.prepareStatement(sqlQuery);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Product product = mapToProduct(rs);
                productList.add(product);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return productList;
    }

    @Override
    public Product findById(Integer integer) {

        Product productFound = new Product();
        String sqlQuery = "SELECT * FROM Products WHERE id = ?";

        try{

            PreparedStatement ps = conn.prepareStatement(sqlQuery);
            ps.setInt(1,integer);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                productFound = mapToProduct(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return productFound;
    }

    @Override
    public void update(Product entity) {

        String sqlQuery = "UPDATE Product SET" +
                " name = ?" +
                " unit = ?" +
                " pricePerUnit = ?" +
                " vat = ?" +
                " description = ?" +
                " stock = ?" +
                " idCategory = ?" +
                " WHERE id = ?";

        try {

            PreparedStatement preparedStatement = conn.prepareStatement(sqlQuery);

            preparedStatement.setString(1, entity.getProductName());
            preparedStatement.setString(2, entity.getUnit());
            preparedStatement.setDouble(3, entity.getPricePerUnit());
            preparedStatement.setString(4, entity.getImgUrl());
            preparedStatement.setDouble(5, entity.getVat());
            preparedStatement.setString(6, entity.getDescription());
            preparedStatement.setInt(7, entity.getStock());
            preparedStatement.setInt(8, entity.getCategory().getIdCategory());

        } catch (SQLException e) {
            e.printStackTrace();
        }

        sqlQuery = "UPDATE product_months SET" +
                " idMonth = ?";
        // TODO: Update the Product_Months Table in db
        try {



        }
    }
    @Override
    public void delete(Product entity) {

        String sqlQuery = "DELETE FROM Product WHERE id = ?";

        try{

            PreparedStatement ps = conn.prepareStatement(sqlQuery);
            ps.setInt(1,entity.getIdProduct());
            ps.executeUpdate();

            // TODO : Delete in Products Months ? Or its automatised by SQL?

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
