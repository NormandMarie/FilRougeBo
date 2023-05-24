package com.m2i.filrougebo.dao;

import com.m2i.filrougebo.enums.Month;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// TODO : Rename to ProductMonthsDAO ?

public class ProductMonthsDao {
    Connection conn = DataBase.getInstance();

    private Month mapToMonth(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        return Month.values()[id - 1];
    }

    public List<Month> findAll() {

        List<Month> monthList = new ArrayList<>();
        String sqlQuery = "SELECT id, name FROM Months";

        try (PreparedStatement pst = conn.prepareStatement(sqlQuery)) {
            ResultSet rs = pst.executeQuery(sqlQuery);
            while (rs.next()) {
                Month month = mapToMonth(rs);
                monthList.add(month);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to gather MonthList from database.", e);
        }

        return monthList;
    }

    public Month findById(Integer id) {

        String sqlQuery = "SELECT id, name FROM Months WHERE id=?;";
        Month month = null;

        try (PreparedStatement pst = conn.prepareStatement(sqlQuery)) {

            pst.setInt(1, id);
            ResultSet resultSet = pst.executeQuery();

            if (resultSet.next()) {
                month = mapToMonth(resultSet);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Could not find month", e);
        }

        return month;
    }

    public List<Month> findAllMonthsPerProduct(int productId) {

        List<Month> monthList = new ArrayList<>();
        String sqlQuery = "SELECT pm.idProduct, pm.idMonth as id FROM Product_Months pm WHERE pm.idProduct = ?;";

        try (PreparedStatement pst = conn.prepareStatement(sqlQuery)) {

            pst.setInt(1, productId);
            ResultSet resultSet = pst.executeQuery();

            while (resultSet.next()) {
                Month month = mapToMonth(resultSet);
                monthList.add(month);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Could not find months", e);
        }

        return monthList;

    }

}
