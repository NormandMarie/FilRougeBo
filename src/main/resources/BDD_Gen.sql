DROP DATABASE IF EXISTS filrouge_bdd;
CREATE DATABASE filrouge_bdd;
USE filrouge_bdd;

CREATE TABLE `Admins`
(
    `id`           INT         NOT NULL AUTO_INCREMENT,
    `username`     VARCHAR(20) NOT NULL,
    `isSuperAdmin` BOOLEAN     NOT NULL,
    `password`     VARCHAR(20) NOT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT username UNIQUE(`username`)
);

CREATE TABLE `Categories` (
    `id`      INT         NOT NULL AUTO_INCREMENT,
    `name`    VARCHAR(20) NOT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT name UNIQUE(`name`)
);

CREATE TABLE `Months` (
    `id`   INT           NOT NULL AUTO_INCREMENT,
    `name` VARCHAR (15)  NOT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT monthsName UNIQUE(`name`)
);

CREATE TABLE `Products` (
    `id`           INT           NOT NULL AUTO_INCREMENT,
    `name`         VARCHAR(100),
    `unit`         VARCHAR(50)   DEFAULT NULL,
    `pricePerUnit` DECIMAL(10,3) DEFAULT NULL,
    `imgUrl`       VARCHAR(500)  DEFAULT NULL,
    `vat`          DECIMAL(4,3)  DEFAULT NULL,
    `description`  TEXT          DEFAULT NULL,
    `stock`        DECIMAL(10,3) DEFAULT NULL,
    `idCategory`   INT,
    PRIMARY KEY (`id`),
    CONSTRAINT ProductsName UNIQUE(`name`),
    KEY `product_category_id_fk` (`idCategory`),
    CONSTRAINT `product_category_id_fk` FOREIGN KEY (`idCategory`) REFERENCES `Categories` (`id`)
);

CREATE TABLE `Product_Months` (
  `idProduct`   INT NOT NULL,
  `idMonth`     INT NOT NULL,
  PRIMARY KEY(`idProduct`, `idMonth`), # Prevents two months for same product
  KEY `Product_months_product_id_fk` (`idProduct`),
  KEY `Product_months_months_id_fk` (`idMonth`),
  CONSTRAINT `Product_months_product_id_fk` FOREIGN KEY (`idProduct`) REFERENCES `Products` (`id`) ON DELETE CASCADE,
  CONSTRAINT `Product_months_months_id_fk` FOREIGN KEY (`idMonth`) REFERENCES `Months` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
)



