package com.m2i.filrougebo.dao;
import com.m2i.filrougebo.entity.Admin;
import com.m2i.filrougebo.entity.Category;
import org.junit.jupiter.api.*;
import java.sql.*;
import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AdminDaoTest {
    private IntAdminDao adminDao = new AdminDao();
    private static Connection conn;

    @BeforeAll
    static void setUp() throws SQLException {
        conn = DataBase.getInstance();
        createSchema();
    }
    @Test
    void testCreate(){

        Admin admin = new Admin();
        admin.setUsername("new admin");
        Admin created = adminDao.create(admin);
        assertEquals(3,created.getIdAdmin());
        assertEquals(admin.getUsername(),created.getUsername());

    }
    @Test
    void ShouldReturnAllAdmins(){

        List<Admin> admins = adminDao.findAll();

        assertTrue(admins.size()!=0);
    }
    @Test
    void ShouldReturnAnAdminGivenAnId(){

        Admin admin = adminDao.findById(2);
        assertTrue(admin.getIdAdmin()==2);
        assertEquals("password2",admin.getPassword());
    }
    @Test
    void ShouldReturnTrueWhenUpdated(){

        String name = "admin1 updated";
        int id = 2;
        Admin admin = new Admin();
        admin.setIdAmin(id);
        admin.setUsername(name);

        boolean isUpdated = adminDao.update(admin);
        assertTrue(isUpdated==true);
    }
    @Test
    void ShouldReturnFalseWhenUpdated(){

        String name = "admin1 updated";
        int id = 5;
        Admin admin = new Admin();
        admin.setIdAmin(id);
        admin.setUsername(name);

        boolean isUpdated = adminDao.update(admin);
        assertTrue(isUpdated==false);
    }
    @Test
    void ShouldReturnTrueWhenDeleted(){

        int id = 1;
        Admin admin = new Admin();
        admin.setIdAmin(id);

        boolean isDeleted = adminDao.delete(admin);
        assertTrue(isDeleted==true);
    }
    @Test
    void ShouldReturnFalseWhenDeleted(){

        int id = 4;
        Admin admin = new Admin();
        admin.setIdAmin(id);

        boolean isDeleted = adminDao.delete(admin);
        assertTrue(isDeleted==false);
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
                "INSERT INTO admins (username, issuperadmin, password) " +
                "VALUES ('admin1',0,'password1');" +
                "INSERT INTO admins (username, issuperadmin, password) " +
                "VALUES ('admin2',0,'password2');";


        Statement statement = conn.createStatement();
        statement.execute(query);
        statement.close();
    }
}