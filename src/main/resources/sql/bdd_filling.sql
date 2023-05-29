USE filrouge_bdd;
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

INSERT INTO OrderStatuses(id, name) VALUES
                                        (1, 'PENDING'), # = basket
                                        (2, 'VALIDATED'),
                                        (3, 'SHIPPED'),
                                        (4, 'CANCELED');

INSERT INTO PaymentMethods(id, name) VALUES
                                         (1, 'CREDIT CARD'),
                                         (2, 'PayPal');


INSERT INTO Admins(id, username, isSuperAdmin, password, firstName, lastName, email) VALUES
                                                                                         (1,'superadmin', 1, 'superadmin', 'Super', 'Admin', 'superadmin@panierprimeur.m2i.com'),
                                                                                         (2,'admin', 0, 'admin', 'Simple', 'Admin', 'admin@panierprimeur.m2i.com');

INSERT INTO Categories(id, name) VALUES
                                     (1, 'Légume-feuille'),
                                     (2, 'Légume-fleur'),
                                     (3, 'Légume-racine'),
                                     (4, 'Légume-bulbe'),
                                     (5, 'Légume-fruit');

INSERT INTO Products(id, name, unit, priceperunit, vat, description, stock, idcategory, imgUrl) VALUES
    (1, 'Carottes', 'kg', 1.89, 0.2,
     'Un légume plein de bêta-carotènes.',
     12.00, 3, '');
INSERT INTO Product_months(idProduct, idMonth) VALUES
                                                   (1, 7),
                                                   (1, 8),
                                                   (1, 9),
                                                   (1, 10);

INSERT INTO Products(id, name, unit, priceperunit, vat, description, stock, idcategory, imgUrl) VALUES
    (2, 'Tomates Cocktail', 'kg', 4.55, 0.2,
     'Les tomates sont un en réalité un fruit.\nLes tomates cerises sont de toutes petites tomates.',
     22, 5, '');
INSERT INTO Product_months(idProduct, idMonth) VALUES
                                                   (2, 6),
                                                   (2, 7),
                                                   (2, 8),
                                                   (2, 9),
                                                   (2, 10),
                                                   (2, 11);

INSERT INTO Products(id, name, unit, priceperunit, vat, description, stock, idcategory, imgUrl) VALUES
    (3, 'Avocats Hass', 'pièce', 1.80, 0.2,
     'Un fruit cultivé dans des régions chaudes.',
     30, 5, '');
INSERT INTO Product_months(idProduct, idMonth) VALUES
                                                   (3, 12),
                                                   (3, 1),
                                                   (3, 3),
                                                   (3, 4),
                                                   (3, 5);

INSERT INTO Products(id, name, unit, priceperunit, vat, description, stock, idcategory, imgUrl) VALUES
    (4, 'Epinards', 'kg', 2.56, 0.2,
     'Riche en fer d\'après la culture populaire',
     8, 1, '');
INSERT INTO Product_months(idProduct, idMonth) VALUES
                                                   (4, 12),
                                                   (4, 1),
                                                   (4, 2),
                                                   (4, 3),
                                                   (4, 4),
                                                   (4, 5),
                                                   (4, 6);

INSERT INTO Products(id, name, unit, priceperunit, vat, description, stock, idcategory, imgUrl) VALUES
    (5, 'Artichaut', 'kg', 5.56, 0.2,
     'L\'artichaut est un légume savoureux avec de feuilles charnues et un cœur tendre, souvent considéré comme le meilleur morceau.',
     12, 2, '');
INSERT INTO Product_months(idProduct, idMonth) VALUES
                                                   (5, 3),
                                                   (5, 4),
                                                   (5, 5),
                                                   (5, 6),
                                                   (5, 7),
                                                   (5, 8),
                                                   (5, 9);

INSERT INTO Products(id, name, unit, priceperunit, vat, description, stock, idcategory, imgUrl) VALUES
    (6, 'Ail', 'kg', 10.45, 0.2,
     'Condiment piquant, l\'ail ajoute saveur et vertus santé aux plats.',
     7, 4, '');
INSERT INTO Product_months(idProduct, idMonth) VALUES
                                                   (6, 8),
                                                   (6, 9),
                                                   (6, 10),
                                                   (6, 11),
                                                   (6, 12);

INSERT INTO Products(id, name, unit, priceperunit, vat, description, stock, idcategory, imgUrl) VALUES
    (7, 'Citrouille', 'kg', 6.45, 0.2,
     'Goût doux, couleur vibrante, parfaite pour les recettes automnales.',
     0, 5, '');
INSERT INTO Product_months(idProduct, idMonth) VALUES
                                                   (7, 8),
                                                   (7, 9),
                                                   (7, 10);

INSERT INTO Products(id, name, unit, priceperunit, vat, description, stock, idcategory, imgUrl) VALUES
    (8, 'Chou Vert', 'pièce', 4.65, 0.2,
     'Feuilles croquantes, saveur légèrement amère, idéal pour les plats cuisinés.',
     15, 1, '');
INSERT INTO Product_months(idProduct, idMonth) VALUES
                                                   (8, 10),
                                                   (8, 11),
                                                   (8, 12),
                                                   (8,  1),
                                                   (8,  2),
                                                   (8,  3),
                                                   (8,  4);
