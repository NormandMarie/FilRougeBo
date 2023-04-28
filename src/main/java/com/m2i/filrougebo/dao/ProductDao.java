package com.m2i.filrougebo.dao;

import com.m2i.filrougebo.entity.Category;
import com.m2i.filrougebo.entity.Product;
import com.m2i.filrougebo.enums.Month;

import java.sql.*;
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
        ProductMonthsDao productMonthsDao = new ProductMonthsDao();
        seasonalMonths = productMonthsDao.findAllMonthsPerProduct(id);

        return new Product(id, name, unit, pricePerUnit, imgUrl, vat, description, stock, category, seasonalMonths);

    }

    @Override
    public void create(Product product) {

        String sqlQueryCreateProduct =
                "INSERT INTO Products(name, unit, pricePerUnit, imgUrl, vat, description, stock, idCategory)" +
                        " VALUES (?,?,?,?,?,?,?,?)";
        String sqlQueryCreateProductMonths = "INSERT INTO product_months(idProduct, idMonth) VALUES (?, ?)";

        try (PreparedStatement pstCreateProduct = conn.prepareStatement(sqlQueryCreateProduct, PreparedStatement.RETURN_GENERATED_KEYS);
             PreparedStatement pstCreateProductMonths = conn.prepareStatement(sqlQueryCreateProductMonths)) {


            pstCreateProduct.setString(1, product.getProductName());
            pstCreateProduct.setString(2, product.getUnit());
            pstCreateProduct.setDouble(3, product.getPricePerUnit());
            pstCreateProduct.setString(4, product.getImgUrl());
            pstCreateProduct.setDouble(5, product.getVat());
            pstCreateProduct.setString(6, product.getDescription());
            pstCreateProduct.setInt(7, product.getStock());
            pstCreateProduct.setInt(8, product.getCategory().getIdCategory());

            int row = pstCreateProduct.executeUpdate();

            if (row == 1) {
                ResultSet generatedKeys = pstCreateProduct.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int id = generatedKeys. getInt(1);
                    product.setIdProduct(id);
                }
            }

            for (Month month : product.getSeasonalMonths()) {
                pstCreateProductMonths.setInt(1, product.getIdProduct());
                pstCreateProductMonths.setInt(2, month.id);
                pstCreateProductMonths.executeUpdate();
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error creating Product", e);
        }

    }

    @Override
    public List<Product> findAll() {

        List<Product> productList = new ArrayList<>();
        String sqlQuery = "SELECT id, name, unit, pricePerUnit, imgUrl, vat, description, stock, idCategory FROM Products";

        try (PreparedStatement pst = conn.prepareStatement(sqlQuery);
             ResultSet rs = pst.executeQuery()) {

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

            // INSERT PRODUCT_MONTHS
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
        // TODO: conn.setAutoCommit(true); ?
        // return true;


    }

    @Override
    public void delete(Product product) {

        String sqlQuery = "DELETE FROM Products WHERE id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sqlQuery)) {

            ps.setInt(1, product.getIdProduct());
            ps.executeUpdate();

            // Delete from Products Months -> automated in the DB !

        } catch (SQLException e) {
            throw new RuntimeException("Error deleting Product", e);
        }
    }

}
