package com.m2i.filrougebo.dao;
import com.m2i.filrougebo.entity.Admin;
import com.m2i.filrougebo.entity.Category;
import org.junit.jupiter.api.*;
import java.sql.*;
import java.util.ArrayList;
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

        String query = "SELECT * FROM admins WHERE username = ?";
        try(PreparedStatement ps = conn.prepareStatement(query)){

            ps.setString(1, admin.getUsername());
            ResultSet rs = ps.executeQuery();
            assertTrue(rs.next());
            assertEquals(admin.getUsername(),rs.getString("username"));
            assertEquals(3,created.getIdAdmin());
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    @Test
    void testFindAll(){

        List<Admin> admins = new ArrayList<>();
        Admin admin1 = new Admin("admin1","password1");
        admin1.setIdAmin(1);
        Admin admin2 = new Admin("admin2","password2");
        admin2.setIdAmin(2);
        admins.add(admin1);
        admins.add(admin2);

        String query = "SELECT * FROM admins";
        int i = 0;

        try (PreparedStatement ps = conn.prepareStatement(query)) {

            ResultSet rs = ps.executeQuery();
            assertTrue(rs.last());
            assertEquals(2,rs.getRow());
            while (rs.next()){
                assertEquals(admins.get(i).getUsername(),rs.getString("username"));
                assertEquals(admins.get(i).getIdAdmin(),rs.getInt("id"));
                assertEquals(admins.get(i).getPassword(),rs.getString("password"));
                i++;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    void testFindById(){

        int id = 1;
        Admin admin = new Admin("admin1","password1");
        admin.setIdAmin(1);
        String query = "SELECT * FROM admins WHERE id = ? ";

        try(PreparedStatement ps = conn.prepareStatement(query)){

            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            assertTrue(rs.next());
            assertEquals(admin.getUsername(),rs.getString("username"));
            assertEquals(admin.getIdAdmin(),rs.getInt("id"));
            assertEquals(admin.getPassword(),rs.getString("password"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    void testUpdate(){

        String username = "admin1 updated";
        int id = 2;
        String query = "UPDATE admins SET username=? WHERE id=?";
        try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {

            preparedStatement.setString(1, username);
            preparedStatement.setInt(2, id);
            int row = preparedStatement.executeUpdate();
            assertTrue(row==1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Test
    void testDelete(){

        int id = 1;
        String query = "DELETE FROM admins WHERE id = ?";

        try(PreparedStatement ps = conn.prepareStatement(query)){
            ps.setInt(1,id);
            int row = ps.executeUpdate();
            assertTrue(row==1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
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