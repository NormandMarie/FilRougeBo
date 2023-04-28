
package com.m2i.filrougebo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

    private static final String URL_DATABASE = "jdbc:mysql://localhost:3306/filrouge_bdd";
    private static final String USER = "root";
    private static final String PASSWORD = "Marie121";
//    private static final String PASSWORD = "abdallah";

    private static Connection INSTANCE;

    private ConnectionManager() {
        // avoid instantiation
    }

    public static Connection getInstance() {
        if (INSTANCE == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                INSTANCE = DriverManager.getConnection(URL_DATABASE, USER, PASSWORD);
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        return INSTANCE;
    }

    public static void closeConnection() {
        if (INSTANCE != null) {
            try {
                INSTANCE.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            INSTANCE = null;
        }
    }

}
