package com.m2i.filrougebo.dao;

import com.m2i.filrougebo.entity.Category;
import com.m2i.filrougebo.entity.Product;
import com.m2i.filrougebo.enums.Month;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ProductDao implements IntProductDao{

    Connection conn = DataBase.getInstance();

    private Product mapToProduct(ResultSet resultSet) throws SQLException {

        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        String unit = resultSet.getString("unit");
        double pricePerUnit = resultSet.getDouble("pricePerUnit");
        String imgUrl = resultSet.getString("imgUrl");
        double vat = resultSet.getDouble("vat");
        String description = resultSet.getString("description");
        Double stock = resultSet.getDouble("stock");

        int idCategory = resultSet.getInt("idCategory");

        CategoryDao categoryDAO = new CategoryDao();
        Category category = categoryDAO.findById(idCategory);

        List<Month> seasonalMonths = new ArrayList<>();
        ProductMonthsDao productMonthsDao = new ProductMonthsDao();
        seasonalMonths = productMonthsDao.findAllMonthsPerProduct(id);

        return new Product(id, name, unit, pricePerUnit, imgUrl, vat, description, stock, category, seasonalMonths);
    }

    @Override
    public Product create(Product product) {

        String sqlQueryCreateProduct =
                "INSERT INTO Products(name, unit, pricePerUnit, imgUrl, vat, description, stock, idCategory)" +
                        " VALUES (?,?,?,?,?,?,?,?)";
        String sqlQueryCreateProductMonths = "INSERT INTO product_months(idProduct, idMonth) VALUES (?, ?)";

        try (PreparedStatement pstCreateProduct = conn.prepareStatement(sqlQueryCreateProduct, PreparedStatement.RETURN_GENERATED_KEYS);
             PreparedStatement pstCreateProductMonths = conn.prepareStatement(sqlQueryCreateProductMonths)) {


            pstCreateProduct.setString(1, product.getName());
            pstCreateProduct.setString(2, product.getUnit());
            pstCreateProduct.setDouble(3, product.getPricePerUnit());
            pstCreateProduct.setString(4, product.getImgUrl());
            pstCreateProduct.setDouble(5, product.getVat());
            pstCreateProduct.setString(6, product.getDescription());
            pstCreateProduct.setDouble(7, product.getStock());
            pstCreateProduct.setInt(8, product.getCategory().getIdCategory());

            int row = pstCreateProduct.executeUpdate();

            if (row == 1) {
                ResultSet generatedKeys = pstCreateProduct.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int id = generatedKeys. getInt(1);
                    product.setId(id);
                }
            }

            for (Month month : product.getSeasonalMonths()) {
                pstCreateProductMonths.setInt(1, product.getId());
                pstCreateProductMonths.setInt(2, month.id);
                pstCreateProductMonths.executeUpdate();
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error creating Product", e);
        }

        return product;

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
    public boolean update(Product product) {

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
            pstUpdateProduct.setString(1, product.getName());
            pstUpdateProduct.setString(2, product.getUnit());
            pstUpdateProduct.setDouble(3, product.getPricePerUnit());
            pstUpdateProduct.setString(4, product.getImgUrl());
            pstUpdateProduct.setDouble(5, product.getVat());
            pstUpdateProduct.setString(6, product.getDescription());
            pstUpdateProduct.setDouble(7, product.getStock());
            pstUpdateProduct.setInt(8, product.getCategory().getIdCategory());
            pstUpdateProduct.setInt(9, product.getId());

            pstUpdateProduct.executeUpdate();

            // DELETE PRODUCT_MONTHS
            pstDeleteProductMonths.setInt(1, product.getId());
            pstDeleteProductMonths.executeUpdate();

            // INSERT PRODUCT_MONTHS
            for (Month month : product.getSeasonalMonths()) {

                pstInsertProductMonths.setInt(1, product.getId());
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
        return true;
    }

    @Override
    public boolean delete(Product product) {

        String sqlQuery = "DELETE FROM Products WHERE id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sqlQuery)) {

            ps.setInt(1, product.getId());
            ps.executeUpdate();

            // Delete from Products Months -> automated in the DB !

        } catch (SQLException e) {
            throw new RuntimeException("Error deleting Product", e);
        }

        return true;
    }

    public List<Product> searchProductPerNameOrDescriptionOrCategoryName(String search) {

        List<Product> productList = new ArrayList<>();
        String sqlQuery =
                "SELECT DISTINCT p.* FROM Categories c " +
                "LEFT JOIN Products p ON c.id = p.idCategory " +
                "WHERE c.name LIKE ? OR p.name LIKE ? OR p.description LIKE ?";

        try (PreparedStatement pst = conn.prepareStatement(sqlQuery)) {

            String searchTerm = "%" + search + "%";

            pst.setString(1, searchTerm);
            pst.setString(2, searchTerm);
            pst.setString(3, searchTerm);

            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    Product product = mapToProduct(rs);
                    productList.add(product);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productList;
    }

}
