package com.m2i.filrougebo.dao;
import com.m2i.filrougebo.entity.Category;
import com.mysql.cj.jdbc.result.ResultSetImpl;
import org.junit.jupiter.api.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class CategoryDaoTest {
    private IntCategoryDao categoryDao = new CategoryDao();
    private static Connection conn;
    @BeforeAll
    static void setUp() throws SQLException {
        conn = DataBase.getInstance();
        createSchema();
    }

    @Test
    void ShouldMapResultSetToCategoryObject(){

    }
    @Test
    void testCreate(){

        Category cat = new Category("test category");
        Category created = categoryDao.create(cat);

        assertEquals(3,created.getIdCategory());
        assertEquals(cat.getName(),created.getName());
    }
    @Test
    void findAll2(){

        List<Category> categories = categoryDao.findAll();

        assertTrue(categories.size()==2);
    }
    @Test
    void testFindById(){

        Category cat = categoryDao.findById(1);
        assertTrue(cat.getIdCategory()==1);
        assertTrue(cat.getName().equals("categ1"));
    }
    @Test
    void testUpdate(){

        String name = "categ1 updated";
        int id = 2;
        Category cat = new Category(id,name);

        boolean isUpdated = categoryDao.update(cat);

        assertTrue(isUpdated==true);
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
    void testSearchByName(){
        String search = "test";
        String query =
                "SELECT DISTINCT c.* FROM categories c " +
                        "WHERE c.name LIKE ?";

        try (PreparedStatement pst = conn.prepareStatement(query)) {

            String searchTerm = "%" + search + "%";
            pst.setString(1, searchTerm);
            ResultSet rs = pst.executeQuery();
            assertTrue(!rs.next());

        } catch (SQLException e) {
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
                ");\n" +
                "\n" +
                "INSERT INTO categories (name) " +
                "VALUES ('categ1');" +
                "INSERT INTO categories (name) " +
                "VALUES ('categ2');";


        Statement statement = conn.createStatement();
        statement.execute(query);
        statement.close();

    }
}