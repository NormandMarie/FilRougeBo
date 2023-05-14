package com.m2i.filrougebo.dao;
import com.m2i.filrougebo.entity.Category;
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
    void testCreate(){

        Category cat = new Category("test category");
        Category created = categoryDao.create(cat);

        String query = "SELECT * FROM categories WHERE name = ?";
        try(PreparedStatement ps = conn.prepareStatement(query)){

            ps.setString(1, cat.getName());
            ResultSet rs = ps.executeQuery();
            assertTrue(rs.next());
            assertEquals(cat.getName(),rs.getString("name"));
            assertEquals(3,created.getIdCategory());

        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    @Test
    void testFindAll(){

        List<Category> categories = new ArrayList<>();
        categories.add(new Category(1,"categ1"));
        categories.add(new Category(2,"categ2"));

        String query = "SELECT * FROM categories";
        int i = 0;

        try (PreparedStatement ps = conn.prepareStatement(query)) {

            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                assertEquals(categories.get(i).getName(),rs.getString("name"));
                assertEquals(categories.get(i).getIdCategory(),rs.getInt("id"));
                i++;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testFindById(){

        int id = 1;
        Category cat = new Category(1,"categ1");
        String query = "SELECT * FROM categories WHERE id = ? ";

        try(PreparedStatement ps = conn.prepareStatement(query)){

            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            assertTrue(rs.next());
            assertEquals(cat.getName(),rs.getString("name"));
            assertEquals(cat.getIdCategory(),rs.getInt("id"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    void testUpdate(){

        String name = "categ1 updated";
        int id = 2;
        String query = "UPDATE categories SET name=? WHERE id=?";
        try (PreparedStatement preparedStatement = conn.prepareStatement(query)) {

            preparedStatement.setString(1, name);
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
        String query = "DELETE FROM categories WHERE id = ?";

        try(PreparedStatement ps = conn.prepareStatement(query)){
            ps.setInt(1,id);
            int row = ps.executeUpdate();
            assertTrue(row==1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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