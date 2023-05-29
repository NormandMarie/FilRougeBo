package com.m2i.filrougebo.dao;

import com.m2i.filrougebo.entity.Category;
import org.junit.jupiter.api.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CategoryDaoTest {
    private IntCategoryDao categoryDao = new CategoryDao();
    private Connection conn;
    @BeforeEach
    void setUp() throws SQLException {
        conn = DataBase.getInstance();
        createSchema();
    }

    @Test
    void ShouldCreateACategoryAndReturnIt(){

        Category cat = new Category("test category");
        Category created = categoryDao.create(cat);

        assertEquals(2,created.getIdCategory());
        assertEquals(cat.getName(),created.getName());
    }
    @Test
    void ShouldReturnAllCategories(){

        List<Category> categories = categoryDao.findAll();

        assertTrue(categories.size()!=0);
    }
    @Test
    void ShouldReturnACategoryGivenAnId(){

        Category cat = categoryDao.findById(1);
        assertTrue(cat.getIdCategory()==1);
        assertTrue(cat.getName().equals("categ1"));
    }
    @Test
    void ShouldReturnTrueWhenUpdated(){

        String name = "categ1 updated";
        int id = 1;
        Category cat = new Category(id,name);

        boolean isUpdated = categoryDao.update(cat);

        assertTrue(isUpdated==true);
    }
    @Test
    void ShouldReturnFalseWhenUpdated(){

        String name = "categ1 updated";
        int id = 3;
        Category cat = new Category(id,name);

        boolean isUpdated = categoryDao.update(cat);

        assertTrue(isUpdated==false);
    }
    @Test
    void ShouldReturnTrueWhenDeleted(){

        String name = "";
        int id = 1;
        Category cat = new Category(id,name);

        boolean isDeleted = categoryDao.delete(cat);

        assertTrue(isDeleted==true);
    }
    @Test
    void ShouldReturnFalseWhenDeleted(){

        String name = "";
        int id = 3;
        Category cat = new Category(id,name);

        boolean isDeleted = categoryDao.delete(cat);

        assertTrue(isDeleted==false);
    }
    @Test
    void ShouldReturnCategoriesWithNameMatchingPatternName(){

        String search = "test";
        List<Category> expected = new ArrayList<>();

        List<Category> result = categoryDao.searchByName(search);
        assertTrue(result.size()==0);

    }
    @AfterEach
    void tearDown() throws SQLException {
        Statement s = conn.createStatement();
        s.executeUpdate("DROP ALL OBJECTS DELETE FILES");
    }

    private void createSchema() throws SQLException {

        String createQuery ="DROP SCHEMA IF EXISTS test_db;\n" +
                "CREATE SCHEMA test_db;\n" +
                "CREATE TABLE Admins\n" +
                "(\n" +
                "    id           INT AUTO_INCREMENT\n" +
                "        PRIMARY KEY,\n" +
                "    email        VARCHAR(255) NULL,\n" +
                "    firstName    VARCHAR(255) NULL,\n" +
                "    isSuperAdmin BIT          NOT NULL,\n" +
                "    lastName     VARCHAR(255) NULL,\n" +
                "    password     VARCHAR(255) NULL,\n" +
                "    username     VARCHAR(255) NULL,\n" +
                "    CONSTRAINT UK_sr28qnsnytjjht54j6fa88uvn\n" +
                "        UNIQUE (username)\n" +
                ");\n" +
                "\n" +
                "CREATE TABLE Categories\n" +
                "(\n" +
                "    id   INT AUTO_INCREMENT\n" +
                "        PRIMARY KEY,\n" +
                "    name VARCHAR(255) NULL,\n" +
                "    CONSTRAINT UK_cqsfg7tv7vfw6vjx36u4ym0i9\n" +
                "        UNIQUE (name)\n" +
                ");\n" +
                "\n" +
                "CREATE TABLE Clients\n" +
                "(\n" +
                "    id        BIGINT AUTO_INCREMENT\n" +
                "        PRIMARY KEY,\n" +
                "    avatarUrl VARCHAR(255) NULL,\n" +
                "    email     VARCHAR(255) NULL,\n" +
                "    firstName VARCHAR(255) NULL,\n" +
                "    lastName  VARCHAR(255) NULL,\n" +
                "    password  VARCHAR(255) NULL,\n" +
                "    CONSTRAINT UK_96icggkuasv49rrd8osfg6g7w\n" +
                "        UNIQUE (email)\n" +
                ");\n" +
                "\n" +
                "CREATE TABLE Addresses\n" +
                "(\n" +
                "    id         BIGINT AUTO_INCREMENT\n" +
                "        PRIMARY KEY,\n" +
                "    city       VARCHAR(255) NULL,\n" +
                "    complement VARCHAR(255) NULL,\n" +
                "    number     VARCHAR(255) NULL,\n" +
                "    roadName   VARCHAR(255) NULL,\n" +
                "    roadPrefix VARCHAR(255) NULL,\n" +
                "    title      VARCHAR(255) NULL,\n" +
                "    zipCode    VARCHAR(255) NULL,\n" +
                "    id_client  BIGINT       NULL,\n" +
                "    CONSTRAINT FKsh6p949g7ck7cqvkrtm3nu0t\n" +
                "        FOREIGN KEY (id_client) REFERENCES Clients (id)\n" +
                ");\n" +
                "\n" +
                "CREATE TABLE Months\n" +
                "(\n" +
                "    id   INT AUTO_INCREMENT\n" +
                "        PRIMARY KEY,\n" +
                "    name VARCHAR(255) NULL\n" +
                ");\n" +
                "\n" +
                "CREATE TABLE OrderStatuses\n" +
                "(\n" +
                "    id   BIGINT AUTO_INCREMENT\n" +
                "        PRIMARY KEY,\n" +
                "    name VARCHAR(255) NULL\n" +
                ");\n" +
                "\n" +
                "CREATE TABLE PaymentMethods\n" +
                "(\n" +
                "    id   BIGINT AUTO_INCREMENT\n" +
                "        PRIMARY KEY,\n" +
                "    name VARCHAR(255) NULL\n" +
                ");\n" +
                "\n" +
                "CREATE TABLE Orders\n" +
                "(\n" +
                "    id               BIGINT AUTO_INCREMENT\n" +
                "        PRIMARY KEY,\n" +
                "    date             DATE   NULL,\n" +
                "    id_client        BIGINT NULL,\n" +
                "    id_paymentMethod BIGINT NULL,\n" +
                "    id_status        BIGINT NULL,\n" +
                "    CONSTRAINT FKlqi0cg9pm1dv88xnqhdl6lxmw\n" +
                "        FOREIGN KEY (id_paymentMethod) REFERENCES PaymentMethods (id),\n" +
                "    CONSTRAINT FKnjt0mo5eqgqpdsl0qkxm8pr5e\n" +
                "        FOREIGN KEY (id_status) REFERENCES OrderStatuses (id),\n" +
                "    CONSTRAINT FKnkb0ephnwm59q78xaedb3m95\n" +
                "        FOREIGN KEY (id_client) REFERENCES Clients (id)\n" +
                ");\n" +
                "\n" +
                "CREATE TABLE PhoneNumbers\n" +
                "(\n" +
                "    id          BIGINT AUTO_INCREMENT\n" +
                "        PRIMARY KEY,\n" +
                "    phoneNumber VARCHAR(255) NULL,\n" +
                "    id_client   BIGINT       NULL,\n" +
                "    CONSTRAINT FKqntxxridd3hhrjqk7pnebr6ax\n" +
                "        FOREIGN KEY (id_client) REFERENCES Clients (id)\n" +
                ");\n" +
                "\n" +
                "CREATE TABLE Products\n" +
                "(\n" +
                "    id           INT AUTO_INCREMENT\n" +
                "        PRIMARY KEY,\n" +
                "    description  VARCHAR(255)   NULL,\n" +
                "    imgUrl       VARCHAR(255)   NULL,\n" +
                "    name         VARCHAR(255)   NULL,\n" +
                "    pricePerUnit DECIMAL(38, 2) NULL,\n" +
                "    stock        DECIMAL(38, 2) NULL,\n" +
                "    unit         VARCHAR(255)   NULL,\n" +
                "    vat          DECIMAL(38, 2) NULL,\n" +
                "    idCategory   INT            NULL,\n" +
                "    CONSTRAINT UK_fx9yjtfi73058657et69e5ywj\n" +
                "        UNIQUE (name),\n" +
                "    CONSTRAINT FK31ime15l4wjjes1jgg9qlada\n" +
                "        FOREIGN KEY (idCategory) REFERENCES Categories (id)\n" +
                ");\n" +
                "\n" +
                "CREATE TABLE OrderLines\n" +
                "(\n" +
                "    id        BIGINT AUTO_INCREMENT\n" +
                "        PRIMARY KEY,\n" +
                "    discount  DECIMAL(38, 2) NULL,\n" +
                "    quantity  DECIMAL(38, 2) NULL,\n" +
                "    idOrder   BIGINT         NULL,\n" +
                "    idProduct INT            NULL,\n" +
                "    CONSTRAINT FKcvf51kqpiiym3m1kkejb0xsy9\n" +
                "        FOREIGN KEY (idOrder) REFERENCES Orders (id),\n" +
                "    CONSTRAINT FKfa5s8mrn2qj7ymwde5pf4odmp\n" +
                "        FOREIGN KEY (idProduct) REFERENCES Products (id)\n" +
                ");\n" +
                "\n" +
                "CREATE TABLE Product_Months\n" +
                "(\n" +
                "    idProduct INT NOT NULL,\n" +
                "    idMonth   INT NOT NULL,\n" +
                "    PRIMARY KEY (idProduct, idMonth),\n" +
                "    CONSTRAINT Product_months_product_id_fk FOREIGN KEY (idProduct) REFERENCES Products (id) ON DELETE CASCADE,\n" +
                "    CONSTRAINT Product_months_months_id_fk FOREIGN KEY (idMonth) REFERENCES Months (id) ON DELETE CASCADE ON UPDATE CASCADE\n" +
                ");" +
                "INSERT INTO Admins" +
                " (username, isSuperAdmin, password, firstName, lastName, email)" +
                "VALUES ('superadmin', 1, 'superadmin', 'Super', 'Admin', 'superadmin@panierprimeur.m2i.com');"+
                "INSERT INTO Categories" +
                " (name)" +
                "VALUES ('categ1');";

        Statement statement = conn.createStatement();
        statement.execute(createQuery);

        statement.close();
    }
}