package com.m2i.filrougebo.dao;
import com.m2i.filrougebo.entity.Category;
import org.junit.jupiter.api.*;
import java.sql.*;
import static org.junit.jupiter.api.Assertions.*;

class CategoryDaoTest {
    private CategoryDao categoryDao = new CategoryDao();
    private static Connection conn;
    @BeforeAll
    static void setUp() throws SQLException {
        conn = DataBase.getInstance();
        createSchema();
    }
    @Test
    void testCreate(){

        Category cat = new Category("test category");
        categoryDao.create(cat);

        String query = "SELECT * FROM categories WHERE name = ?";
        try(PreparedStatement ps = conn.prepareStatement(query)){

            ps.setString(1, cat.getName());
            ResultSet rs = ps.executeQuery();
            assertTrue(rs.next());
            assertEquals(cat.getName(),rs.getString("name"));

        }catch (SQLException e){
            e.printStackTrace();
        }
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
                ")";

        Statement statement = conn.createStatement();
        statement.execute(query);
        statement.close();

    }
}