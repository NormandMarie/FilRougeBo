package com.m2i.filrougebo.service;

import com.m2i.filrougebo.dao.DataBase;
import com.m2i.filrougebo.entity.Admin;

import java.sql.*;

public class AuthenticationService {
    public static boolean authenticate(String username, String password) {
        boolean isAuthenticated = false;
        Connection connection = DataBase.getInstance();
        String query = "SELECT * FROM admins WHERE username = ? AND password = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try (PreparedStatement stmt = connection.prepareStatement(query)) {


            statement = connection.prepareStatement(query);
            statement.setString(1, username);
            statement.setString(2, password);
            resultSet = statement.executeQuery();

            // Si l'utilisateur existe, l'authentification est réussie
            isAuthenticated = resultSet.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return isAuthenticated;
    }

    public static boolean isSuperAdmin(String username) {
        Connection connection = DataBase.getInstance();
        String query = "SELECT * FROM admins WHERE username = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                boolean isSuperAdmin = rs.getBoolean("isSuperAdmin");
                if (isSuperAdmin) {
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
    public static Admin authenticatewithSuper(String username, String password) {
        Connection connection = DataBase.getInstance();
        String query = "SELECT * FROM admins WHERE username = ? AND password = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                boolean isSuperAdmin = rs.getBoolean("isSuperAdmin");
                int id = rs.getInt("id");
                return new Admin(id, username, isSuperAdmin, password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


}



