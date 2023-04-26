package com.m2i.filrougebo.dao;

import com.m2i.filrougebo.entity.Category;
import com.m2i.filrougebo.entity.Product;
import com.m2i.filrougebo.enums.Month;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


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

        try (PreparedStatement ps = conn.prepareStatement(sqlQuery))
        {

            ps.setString(1, entity.getProductName());
            ps.setString(2, entity.getUnit());
            ps.setDouble(3, entity.getPricePerUnit());
            ps.setString(4, entity.getImgUrl());
            ps.setDouble(5, entity.getVat());
            ps.setString(6, entity.getDescription());
            ps.setInt(7, entity.getStock());
            ps.setInt(8, entity.getCategory().getIdCategory());

            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error creating Product", e);
        }
    }

    @Override
    public List<Product> findAll() {

        List<Product> productList = new ArrayList<>();
        String sqlQuery = "SELECT id, name, unit, pricePerUnit, imgUrl, vat, description, stock, idCategory FROM Products";

        try (PreparedStatement ps = conn.prepareStatement(sqlQuery);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Product product = mapToProduct(rs);
                productList.add(product);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error fetching Products", e);
        }
        return productList;
    }

    @Override
    public Product findById(Integer productId) {

        Product productFound = null;
        String sqlQuery = "SELECT * FROM Products WHERE id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sqlQuery)) {

            ps.setInt(1, productId);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    productFound = mapToProduct(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching Product", e);
        }
        return productFound;
    }

    @Override
    public void update(Product product) {

        String sqlQueryUpdateProduct = "UPDATE Products SET" +
                " name = ?" +
                ", unit = ?" +
                ", pricePerUnit = ?" +
                ", imgUrl = ?" +
                ", vat = ?" +
                ", description = ?" +
                ", stock = ?" +
                ", idCategory = ?" +
                " WHERE id = ?";

        String sqlQueryDeleteProductMonths = "DELETE FROM product_months WHERE idProduct = ?";
        String sqlQueryInsertProductMonths = "INSERT INTO product_months(idProduct, idMonth) VALUES (?, ?)";

        try (PreparedStatement pstUpdateProduct = conn.prepareStatement(sqlQueryUpdateProduct);
             PreparedStatement pstDeleteProductMonths = conn.prepareStatement(sqlQueryDeleteProductMonths);
             PreparedStatement pstInsertProductMonths = conn.prepareStatement(sqlQueryInsertProductMonths);) {

            conn.setAutoCommit(false); // To avoid updating Product table if any errors are raised when deleting/inserting Months

            // UPDATE PRODUCT
            pstUpdateProduct.setString(1, product.getProductName());
            pstUpdateProduct.setString(2, product.getUnit());
            pstUpdateProduct.setDouble(3, product.getPricePerUnit());
            pstUpdateProduct.setString(4, product.getImgUrl());
            pstUpdateProduct.setDouble(5, product.getVat());
            pstUpdateProduct.setString(6, product.getDescription());
            pstUpdateProduct.setInt(7, product.getStock());
            pstUpdateProduct.setInt(8, product.getCategory().getIdCategory());
            pstUpdateProduct.setInt(9, product.getIdProduct());

            pstUpdateProduct.executeUpdate();

            // DELETE PRODUCT_MONTHS
            pstDeleteProductMonths.setInt(1, product.getIdProduct());
            pstDeleteProductMonths.executeUpdate();

            for (Month month : product.getSeasonalMonths()) {

                pstInsertProductMonths.setInt(1, product.getIdProduct());
                pstInsertProductMonths.setInt(2, month.id);

                pstInsertProductMonths.executeUpdate();
            }

            conn.commit();

        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback();
                    System.err.print("Transaction is being rolled back");
                } catch (SQLException ex) {
                    throw new RuntimeException("Error updating Product", ex);
                }
            }
            throw new RuntimeException("Error updating Product", e);
        }

        // return true;


    }

    @Override
    public void delete(Product product) {

        String sqlQuery = "DELETE FROM Products WHERE id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sqlQuery)) {

            ps.setInt(1, product.getIdProduct());
            ps.executeUpdate();

            // Delete from Products Months -> automated from DB connection!

        } catch (SQLException e) {
            throw new RuntimeException("Error deleting Product", e);
        }
    }
}
