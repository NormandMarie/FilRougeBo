INSERT INTO Months(id, name) VALUES
    (1,'January'),
    (2,'February'),
    (3,'March'),
    (4,'April'),
    (5,'May'),
    (6,'June'),
    (7,'July'),
    (8,'August'),
    (9,'September'),
    (10,'October'),
    (11,'November'),
    (12,'December');

INSERT INTO Admins(id, username, isSuperAdmin, password) VALUES
   (1,'superadmin', 1, 'superadmin'),
   (2,'admin', 0, 'admin');

INSERT INTO Categories(id, name) VALUES
    (1, 'Catégorie 1'),
    (2, 'Catégorie 2');

INSERT INTO Products(id, name, unit, priceperunit, imgurl, vat, description, stock, idcategory) VALUES
    (1, 'Carrots', 'kg', 1.68, '', 0.2, 'A vegetable full of beta-carotenes.', 12.00, 1);
INSERT INTO Product_months(idProduct, idMonth) VALUES
    (1, 9),
    (1, 10),
    (1, 11);

INSERT INTO Products(id, name, unit, priceperunit, imgurl, vat, description, stock, idcategory) VALUES
    (2, 'Tomatoes', 'kg', 4.55, '', 0.2, 'Actually a fruit.', 15.00, 2);
INSERT INTO Product_months(idProduct, idMonth) VALUES
   (2, 6),
   (2, 7),
   (2, 8);

