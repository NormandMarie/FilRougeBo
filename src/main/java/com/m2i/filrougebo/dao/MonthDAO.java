package com.m2i.filrougebo.dao;

import com.m2i.filrougebo.enums.Month;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MonthDAO implements IntMonthDAO {

    private Month mapToMonth(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        return Month.values()[id];
    }

    @Override
    public void create(Month entity) {
        throw new RuntimeException("You cannot create months.");
    }

    @Override
    public List<Month> findAll() {

        List<Month> monthList = new ArrayList<>();
        Connection connection = ConnectionManager.getInstance();
        String sqlQuery = "SELECT id, name FROM Months";

        try {

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlQuery);

            while (resultSet.next()) {
                Month month = mapToMonth(resultSet);
                monthList.add(month);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to gather MonthList from database.");
        }

        return monthList;
    }


    @Override
    public Month findById(Integer id) {

        Connection connection = ConnectionManager.getInstance();
        String sqlQuery = "SELECT id, name FROM Months WHERE id=?;";
        Month month = null;

        try {

            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                month = mapToMonth(resultSet);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return month;
    }

    public List<Month> findAllMonthsPerProduct(int productId) {

        List<Month> monthList = new ArrayList<>();
        Connection connection = ConnectionManager.getInstance();
        String sqlQuery = "SELECT pm.idProduct, pm.idMonth as 'id' FROM Product_Months pm WHERE pm.idProduct = ?;";

        try {
            PreparedStatement prepStatement = connection.prepareStatement(sqlQuery);
            prepStatement.setInt(1, productId);
            ResultSet resultSet = prepStatement.executeQuery();

            while (resultSet.next()) {
                Month month = mapToMonth(resultSet);
                monthList.add(month);
            }

        } catch (SQLException e) {
            // TODO: add some verbosity?
            e.printStackTrace();
        }

        return monthList;


    }

    @Override
    public void update(Month entity) {

    }

    @Override
    public void delete(Month entity) {

    }
}
