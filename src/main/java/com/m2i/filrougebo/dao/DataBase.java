
package com.m2i.filrougebo.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public final class DataBase {

    private static Connection INSTANCE;

    private DataBase() {
        // avoid instantiation
    }

    public static Connection getInstance() {
        if (INSTANCE == null) {
            try {

                Properties properties = new Properties();
                String path = Thread.currentThread().getContextClassLoader().getResource("").getPath();
                properties.load( new FileInputStream(path + "app.properties"));

//                DriverManager.registerDriver(new com.mysql.jdbc.Driver());
                Class.forName("com.mysql.cj.jdbc.Driver");
                INSTANCE = DriverManager.getConnection(
                        properties.getProperty("bdd_url"),
                        properties.getProperty("bdd_user"),
                        properties.getProperty("bdd_password")
                );
            } catch (SQLException | ClassNotFoundException | IOException e) {
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
