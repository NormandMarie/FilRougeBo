package com.m2i.filrougebo.dao;

import com.m2i.filrougebo.entity.Category;
import com.m2i.filrougebo.entity.Product;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

class ProductDaoTest {
    private IntProductDao productDao = new ProductDao();
    private static Connection conn;
    @BeforeAll
    static void setUp() throws SQLException {
        conn = DataBase.getInstance();
        createSchema();
    }
    @Test
    void ShouldCreateAProductAndReturnIt(){

        Product product = new Product();
        product.setName("new product");
        product.setCategory(new Category(1,"categ1"));
        Product created = productDao.create(product);

        assertEquals(3,created.getId());
        assertEquals(product.getName(),created.getName());
    }










    @AfterAll
    static void tearDown() throws SQLException {
        conn.close();
    }
































    private static void createSchema() throws SQLException {
        String query = "create table admins\n" +
                "(\n" +
                "    id           int auto_increment\n" +
                "        primary key,\n" +
                "    username     varchar(20) null,\n" +
                "    isSuperAdmin tinyint(1)  not null,\n" +
                "    password     varchar(20) null\n" +
                ");\n" +
                "\n" +
                "create table categories\n" +
                "(\n" +
                "    id   int auto_increment\n" +
                "        primary key,\n" +
                "    name varchar(20) not null\n" +
                ");\n" +
                "\n" +
                "create table months\n" +
                "(\n" +
                "    id   int auto_increment\n" +
                "        primary key,\n" +
                "    name varchar(15) not null\n" +
                ");\n" +
                "\n" +
                "create table products\n" +
                "(\n" +
                "    id           int auto_increment\n" +
                "        primary key,\n" +
                "    name         varchar(100) null,\n" +
                "    unit         varchar(50)  null,\n" +
                "    pricePerUnit decimal      null,\n" +
                "    imgUrl       varchar(255) null,\n" +
                "    vat          decimal      null,\n" +
                "    description  varchar(50)  null,\n" +
                "    stock        int          null,\n" +
                "    idCategory   int          null,\n" +
                "    constraint product_category_id_fk\n" +
                "        foreign key (idCategory) references categories (id)\n" +
                ");\n" +
                "\n" +
                "create table product_months\n" +
                "(\n" +
                "    idProduct int         null,\n" +
                "    idMonth   int         null,\n" +
                "    monthName varchar(15) null,\n" +
                "    constraint Product_seasons_product_id_fk\n" +
                "        foreign key (idProduct) references products (id)\n" +
                "            on delete cascade,\n" +
                "    constraint Product_seasons_seasonal_months_id_fk\n" +
                "        foreign key (idMonth) references months (id)\n" +
                ");\n" +
                "\n" +
                "INSERT INTO products (name) " +
                "VALUES ('product1');" +
                "INSERT INTO products (name) " +
                "VALUES ('product2');" +
                "INSERT INTO categories (name)" +
                "VALUES ('categ1');";


        Statement statement = conn.createStatement();
        statement.execute(query);
        statement.close();

    }

}