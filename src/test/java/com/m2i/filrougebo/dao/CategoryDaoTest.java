package com.m2i.filrougebo.dao;
import com.m2i.filrougebo.entity.Category;
import org.junit.jupiter.api.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

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
        //AtomicInteger i = new AtomicInteger();
        //        result
        //                .stream()
        //                .forEach(cat -> {
        //                    assertEquals(expected.get(i.get()).getIdCategory(),cat.getIdCategory());
        //                    assertEquals(expected.get(i.get()).getName(),cat.getName());
        //                    i.getAndIncrement();
        //                });
    }
    @AfterEach
    void tearDown() throws SQLException {
        Statement s = conn.createStatement();
        s.executeUpdate("DROP ALL OBJECTS DELETE FILES");
    }

    private void createSchema() throws SQLException {

        String createQuery ="DROP SCHEMA IF EXISTS test_db;\n" +
                "CREATE SCHEMA test_db;\n" +
                "create table Admins\n" +
                "(\n" +
                "    id           int auto_increment\n" +
                "        primary key,\n" +
                "    isSuperAdmin bit          not null,\n" +
                "    password     varchar(255) null,\n" +
                "    username     varchar(255) null,\n" +
                "    firstName     varchar(255) null,\n" +
                "    lastName     varchar(255) null,\n" +
                "    email        varchar(255) null,\n" +
                "    constraint UK_sr28qnsnytjjht54j6fa88uvn\n" +
                "        unique (username)\n" +
                ");\n" +
                "\n" +
                "create table Categories\n" +
                "(\n" +
                "    id   int auto_increment\n" +
                "        primary key,\n" +
                "    name varchar(255) null,\n" +
                "    constraint UK_cqsfg7tv7vfw6vjx36u4ym0i9\n" +
                "        unique (name)\n" +
                ");\n" +
                "\n" +
                "create table Clients\n" +
                "(\n" +
                "    id        bigint auto_increment\n" +
                "        primary key,\n" +
                "    avatarUrl varchar(255) null,\n" +
                "    email     varchar(255) null,\n" +
                "    firstName varchar(255) null,\n" +
                "    lastName  varchar(255) null,\n" +
                "    password  varchar(255) null,\n" +
                "    constraint UK_96icggkuasv49rrd8osfg6g7w\n" +
                "        unique (email)\n" +
                ");\n" +
                "\n" +
                "create table Adresses\n" +
                "(\n" +
                "    id         bigint auto_increment\n" +
                "        primary key,\n" +
                "    city       varchar(255) null,\n" +
                "    complement varchar(255) null,\n" +
                "    number     varchar(255) null,\n" +
                "    roadName   varchar(255) null,\n" +
                "    roadPrefix varchar(255) null,\n" +
                "    title      varchar(255) null,\n" +
                "    zipCode    varchar(255) null,\n" +
                "    id_client  bigint       null,\n" +
                "    constraint FKalivqho1bc99sh3rbn3pl40ow\n" +
                "        foreign key (id_client) references Clients (id)\n" +
                ");\n" +
                "\n" +
                "create table Months\n" +
                "(\n" +
                "    id   int auto_increment\n" +
                "        primary key,\n" +
                "    name varchar(255) null\n" +
                ");\n" +
                "\n" +
                "create table OrderStatuses\n" +
                "(\n" +
                "    id   bigint auto_increment\n" +
                "        primary key,\n" +
                "    name varchar(255) null\n" +
                ");\n" +
                "\n" +
                "create table PaymentMethods\n" +
                "(\n" +
                "    id   bigint auto_increment\n" +
                "        primary key,\n" +
                "    name varchar(255) null\n" +
                ");\n" +
                "\n" +
                "create table Orders\n" +
                "(\n" +
                "    id               bigint auto_increment\n" +
                "        primary key,\n" +
                "    date             date   null,\n" +
                "    id_client        bigint null,\n" +
                "    id_paymentMethod bigint null,\n" +
                "    id_status        bigint null,\n" +
                "    constraint FKlqi0cg9pm1dv88xnqhdl6lxmw\n" +
                "        foreign key (id_paymentMethod) references PaymentMethods (id),\n" +
                "    constraint FKnjt0mo5eqgqpdsl0qkxm8pr5e\n" +
                "        foreign key (id_status) references OrderStatuses (id),\n" +
                "    constraint FKnkb0ephnwm59q78xaedb3m95\n" +
                "        foreign key (id_client) references Clients (id)\n" +
                ");\n" +
                "\n" +
                "create table PhoneNumbers\n" +
                "(\n" +
                "    id          bigint auto_increment\n" +
                "        primary key,\n" +
                "    phoneNumber varchar(255) null,\n" +
                "    id_client   bigint       null,\n" +
                "    constraint FKqntxxridd3hhrjqk7pnebr6ax\n" +
                "        foreign key (id_client) references Clients (id)\n" +
                ");\n" +
                "\n" +
                "create table Products\n" +
                "(\n" +
                "    id           int auto_increment\n" +
                "        primary key,\n" +
                "    description  varchar(255)   null,\n" +
                "    imgUrl       varchar(255)   null,\n" +
                "    name         varchar(255)   null,\n" +
                "    pricePerUnit decimal(38, 2) null,\n" +
                "    stock        decimal(38, 2) null,\n" +
                "    unit         varchar(255)   null,\n" +
                "    vat          decimal(38, 2) null,\n" +
                "    idCategory   int            null,\n" +
                "    constraint UK_fx9yjtfi73058657et69e5ywj\n" +
                "        unique (name),\n" +
                "    constraint FK31ime15l4wjjes1jgg9qlada\n" +
                "        foreign key (idCategory) references Categories (id)\n" +
                ");\n" +
                "\n" +
                "create table OrderLines\n" +
                "(\n" +
                "    id        bigint auto_increment\n" +
                "        primary key,\n" +
                "    discount  decimal(38, 2) null,\n" +
                "    quantity  decimal(38, 2) null,\n" +
                "    idOrder   bigint         null,\n" +
                "    idProduct int            null,\n" +
                "    constraint FKcvf51kqpiiym3m1kkejb0xsy9\n" +
                "        foreign key (idOrder) references Orders (id),\n" +
                "    constraint FKfa5s8mrn2qj7ymwde5pf4odmp\n" +
                "        foreign key (idProduct) references Products (id)\n" +
                ");\n" +
                "\n" +
                "create table product_months\n" +
                "(\n" +
                "    idProduct int not null,\n" +
                "    idMonth   int not null,\n" +
                "    PRIMARY KEY(idProduct, idMonth),\n" +
                "    CONSTRAINT Product_months_product_id_fk FOREIGN KEY (idProduct) REFERENCES Products (id) ON DELETE CASCADE,\n" +
                "    CONSTRAINT Product_months_months_id_fk FOREIGN KEY (idMonth) REFERENCES Months (id) ON DELETE CASCADE ON UPDATE CASCADE\n" +
                ");" +
                "INSERT INTO Categories" +
                " (name)" +
                "VALUES ('categ1');";

        Statement statement = conn.createStatement();
        statement.execute(createQuery);
        //System.out.println(row);

        statement.close();
    }
}