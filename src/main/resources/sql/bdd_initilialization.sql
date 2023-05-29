DROP DATABASE IF EXISTS filrouge_bdd;
CREATE DATABASE filrouge_bdd;
USE filrouge_bdd;

CREATE TABLE filrouge_bdd.Admins
(
    id           INT AUTO_INCREMENT
        PRIMARY KEY,
    email        VARCHAR(255) NULL,
    firstName    VARCHAR(255) NULL,
    isSuperAdmin BIT          NOT NULL,
    lastName     VARCHAR(255) NULL,
    password     VARCHAR(255) NULL,
    username     VARCHAR(255) NULL,
    CONSTRAINT UK_sr28qnsnytjjht54j6fa88uvn
        UNIQUE (username)
);

CREATE TABLE filrouge_bdd.Categories
(
    id   INT AUTO_INCREMENT
        PRIMARY KEY,
    name VARCHAR(255) NULL,
    CONSTRAINT UK_cqsfg7tv7vfw6vjx36u4ym0i9
        UNIQUE (name)
);

CREATE TABLE filrouge_bdd.Clients
(
    id        BIGINT AUTO_INCREMENT
        PRIMARY KEY,
    avatarUrl VARCHAR(255) NULL,
    email     VARCHAR(255) NULL,
    firstName VARCHAR(255) NULL,
    lastName  VARCHAR(255) NULL,
    password  VARCHAR(255) NULL,
    CONSTRAINT UK_96icggkuasv49rrd8osfg6g7w
        UNIQUE (email)
);

CREATE TABLE filrouge_bdd.Addresses
(
    id         BIGINT AUTO_INCREMENT
        PRIMARY KEY,
    city       VARCHAR(255) NULL,
    complement VARCHAR(255) NULL,
    number     VARCHAR(255) NULL,
    roadName   VARCHAR(255) NULL,
    roadPrefix VARCHAR(255) NULL,
    title      VARCHAR(255) NULL,
    zipCode    VARCHAR(255) NULL,
    id_client  BIGINT       NULL,
    CONSTRAINT FKsh6p949g7ck7cqvkrtm3nu0t
        FOREIGN KEY (id_client) REFERENCES filrouge_bdd.Clients (id)
);

CREATE TABLE filrouge_bdd.Months
(
    id   INT AUTO_INCREMENT
        PRIMARY KEY,
    name VARCHAR(255) NULL
);

CREATE TABLE filrouge_bdd.OrderStatuses
(
    id   BIGINT AUTO_INCREMENT
        PRIMARY KEY,
    name VARCHAR(255) NULL
);

CREATE TABLE filrouge_bdd.PaymentMethods
(
    id   BIGINT AUTO_INCREMENT
        PRIMARY KEY,
    name VARCHAR(255) NULL
);

CREATE TABLE filrouge_bdd.Orders
(
    id               BIGINT AUTO_INCREMENT
        PRIMARY KEY,
    date             DATE   NULL,
    id_client        BIGINT NULL,
    id_paymentMethod BIGINT NULL,
    id_status        BIGINT NULL,
    CONSTRAINT FKlqi0cg9pm1dv88xnqhdl6lxmw
        FOREIGN KEY (id_paymentMethod) REFERENCES filrouge_bdd.PaymentMethods (id),
    CONSTRAINT FKnjt0mo5eqgqpdsl0qkxm8pr5e
        FOREIGN KEY (id_status) REFERENCES filrouge_bdd.OrderStatuses (id),
    CONSTRAINT FKnkb0ephnwm59q78xaedb3m95
        FOREIGN KEY (id_client) REFERENCES filrouge_bdd.Clients (id)
);

CREATE TABLE filrouge_bdd.PhoneNumbers
(
    id          BIGINT AUTO_INCREMENT
        PRIMARY KEY,
    phoneNumber VARCHAR(255) NULL,
    id_client   BIGINT       NULL,
    CONSTRAINT FKqntxxridd3hhrjqk7pnebr6ax
        FOREIGN KEY (id_client) REFERENCES filrouge_bdd.Clients (id)
);

CREATE TABLE filrouge_bdd.Products
(
    id           INT AUTO_INCREMENT
        PRIMARY KEY,
    description  VARCHAR(255)   NULL,
    imgUrl       VARCHAR(255)   NULL,
    name         VARCHAR(255)   NULL,
    pricePerUnit DECIMAL(38, 2) NULL,
    stock        DECIMAL(38, 2) NULL,
    unit         VARCHAR(255)   NULL,
    vat          DECIMAL(38, 2) NULL,
    idCategory   INT            NULL,
    CONSTRAINT UK_fx9yjtfi73058657et69e5ywj
        UNIQUE (name),
    CONSTRAINT FK31ime15l4wjjes1jgg9qlada
        FOREIGN KEY (idCategory) REFERENCES filrouge_bdd.Categories (id)
);

CREATE TABLE filrouge_bdd.OrderLines
(
    id        BIGINT AUTO_INCREMENT
        PRIMARY KEY,
    discount  DECIMAL(38, 2) NULL,
    quantity  DECIMAL(38, 2) NULL,
    idOrder   BIGINT         NULL,
    idProduct INT            NULL,
    CONSTRAINT FKcvf51kqpiiym3m1kkejb0xsy9
        FOREIGN KEY (idOrder) REFERENCES filrouge_bdd.Orders (id),
    CONSTRAINT FKfa5s8mrn2qj7ymwde5pf4odmp
        FOREIGN KEY (idProduct) REFERENCES filrouge_bdd.Products (id)
);

CREATE TABLE filrouge_bdd.Product_Months
(
    idProduct INT NOT NULL,
    idMonth   INT NOT NULL,
    PRIMARY KEY (idProduct, idMonth), # Prevents two months for same product
    KEY Product_months_product_id_fk (idProduct),
    KEY Product_months_months_id_fk (idMonth),
    CONSTRAINT Product_months_product_id_fk FOREIGN KEY (idProduct) REFERENCES Products (id) ON DELETE CASCADE,
    CONSTRAINT Product_months_months_id_fk FOREIGN KEY (idMonth) REFERENCES Months (id) ON DELETE CASCADE ON UPDATE CASCADE
);
