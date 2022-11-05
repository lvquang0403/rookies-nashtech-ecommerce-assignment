INSERT INTO attribute (attribute_name,description) VALUES
                                                       ('Bộ nhớ',NULL),
                                                       ('RAM',NULL),
                                                       ('CPU',NULL),
                                                       ('SSD',NULL),
                                                       ('RAM 1',NULL),
                                                       ('GPU',NULL);


INSERT INTO category (category_name) VALUES
                                         ('Iphone'),
                                         ('Macbook'),
                                         ('Ipad');

INSERT INTO customer (address,created_date,email,first_name,last_name,"password",phone,user_name,cart_id) VALUES
    (NULL,NULL,NULL,NULL,NULL,'$2a$10$eP5BKLTCibR5qZ8Rt8GOquljPSnFGTUAygjp57ajxewWdxzt3x1zC',NULL,'ltv1874',NULL);

INSERT INTO role (role_name) VALUES
                                 ('ROLE_USER'),
                                 ('ROLE_ADMIN');
INSERT INTO product (created_date,description,price,product_details,product_name,updated_date,category_id) VALUES
('2022-11-02','',28950000.00,'null','iPhone 14 Pro 128GB',NULL,1),
                                                                                                               ('2022-11-02','',28950000.00,'null','iPhone 14 256GB',NULL,1),
                                                                                                               ('2022-11-02','',35950000.00,'null','iPhone 14 512GB',NULL,1),
                                                                                                               ('2022-11-02','',36450000.00,'null','iPhone 14 Pro Max 256GB',NULL,1),
                                                                                                               ('2022-11-02','',40950000.00,'null','iPhone 14 Pro Max 512GB',NULL,1),
                                                                                                               ('2022-11-02','',27450000.00,'null','iPhone 13 Promax 128GB',NULL,1),
                                                                                                               ('2022-11-02','',29350000.00,'null','iPhone 13 Promax 256GB',NULL,1),
                                                                                                               ('2022-11-02','',33850000.00,'null','iPhone 13 Promax 512GB',NULL,1),
                                                                                                               ('2022-11-02','',21690000.00,'null','iPhone 13 128GB',NULL,1);
INSERT INTO product (created_date,description,price,product_details,product_name,updated_date,category_id) VALUES
                                                                                                               ('2022-11-02','',21690000.00,'null','iPhone 13 256GB',NULL,1),
                                                                                                               ('2022-11-02','',24890000.00,'null','iPhone 13 512GB',NULL,1),
                                                                                                               ('2022-11-02','',16190000.00,'null','iPhone 12 128GB',NULL,1),
                                                                                                               ('2022-11-02','',17590000.00,'null','iPhone 12 128GB',NULL,1),
                                                                                                               ('2022-11-02','',55950000.00,'null','MacBook Pro 14 M1 Pro (16-Core/16GB/1TB)',NULL,2),
                                                                                                               ('2022-11-02','',35190000.00,'null','MacBook Pro 13 M2 2022 (8GB RAM|512GB SSD)',NULL,2),
                                                                                                               ('2022-11-02','',35950000.00,'null','iMac M1 2021 24 inch (8-Core GPU/8GB/512GB)',NULL,2),
                                                                                                               ('2022-11-02','',39890000.00,'null','iMac M1 2021 24 inch (8-Core GPU/16GB/256GB)',NULL,2),
                                                                                                               ('2022-11-02','',26950000.00,'null','iMac M1 2021 24 inch (7-Core GPU/8GB/256GB)',NULL,2),
                                                                                                               ('2022-11-02','',29990000.00,'null','iMac M1 2021 24 inch (8-Core GPU/8GB/256GB)',NULL,2);
INSERT INTO product (created_date,description,price,product_details,product_name,updated_date,category_id) VALUES
                                                                                                               ('2022-11-02','',35390000.00,'null','iMac M1 2021 24 inch (7-Core GPU/16GB/256GB)',NULL,2),
                                                                                                               ('2022-11-02','',40890000.00,'null','MacBook Pro 13 M2 2022 (16GB RAM|512 SSD)',NULL,2),
                                                                                                               ('2022-11-02','',36950000.00,'null','MacBook Pro 13 M2 2022 (16GB RAM|256 SSD)',NULL,2),
                                                                                                               ('2022-11-02','',28990000.00,'null','MacBook Air M2 2022 (8GB RAM|256GB SSD)',NULL,2),
                                                                                                               ('2022-11-02','',36490000.00,'null','MacBook Air M2 2022 (8GB RAM|512GB SSD)',NULL,2),
                                                                                                               ('2022-11-02','',34950000.00,'null','MacBook Air M2 2022 (16GB RAM|256GB SSD)',NULL,2),
                                                                                                               ('2022-11-02','',41390000.00,'null','MacBook Air M2 2022 (16GB RAM|512GB SSD)',NULL,2);

INSERT INTO attribute_product (value,attribute_id,product_id) VALUES
                                                                  ('128GB',1,1),
                                                                  ('6GB',2,1),
                                                                  ('128GB',1,2),
                                                                  ('6GB',2,2),
                                                                  ('256GB',1,3),
                                                                  ('6GB',2,3),
                                                                  ('512GB',1,4),
                                                                  ('6GB',2,4),
                                                                  ('256GB',1,5),
                                                                  ('6GB',2,5);
INSERT INTO attribute_product (value,attribute_id,product_id) VALUES
                                                                  ('512GB',1,6),
                                                                  ('6GB',2,6),
                                                                  ('128GB',1,7),
                                                                  ('6GB',2,7),
                                                                  ('256GB',1,8),
                                                                  ('6GB',2,8),
                                                                  ('512GB',1,9),
                                                                  ('6GB',2,9),
                                                                  ('128GB',1,10),
                                                                  ('4GB',2,10);
INSERT INTO attribute_product (value,attribute_id,product_id) VALUES
                                                                  ('256GB',1,11),
                                                                  ('4GB',2,11),
                                                                  ('512GB',1,12),
                                                                  ('4GB',2,12),
                                                                  ('128GB',1,13),
                                                                  ('128GB',1,14),
                                                                  ('Chip M1',3,15),
                                                                  ('1 TB',4,15),
                                                                  ('16 GB',5,15),
                                                                  ('Chip M2',3,16);
INSERT INTO attribute_product (value,attribute_id,product_id) VALUES
                                                                  ('512 GB',4,16),
                                                                  ('8 GB',5,16),
                                                                  ('Chip M1',3,17),
                                                                  ('512 GB',4,17),
                                                                  ('8 GB',5,17),
                                                                  ('Chip M1',3,18),
                                                                  ('256 GB',4,18),
                                                                  ('16 GB',2,18),
                                                                  ('Chip M1',3,19),
                                                                  ('256 GB',4,19);
INSERT INTO attribute_product (value,attribute_id,product_id) VALUES
                                                                  ('8G',2,19),
                                                                  ('7-Core',6,19),
                                                                  ('Chip M1',3,20),
                                                                  ('256 GB',4,20),
                                                                  ('8G',2,20),
                                                                  ('8-Core',6,20),
                                                                  ('Chip M1',3,21),
                                                                  ('256 GB',4,21),
                                                                  ('16GB',2,21),
                                                                  ('7-Core',6,21);
INSERT INTO attribute_product (value,attribute_id,product_id) VALUES
                                                                  ('Chip M1',3,22),
                                                                  ('512 GB',4,22),
                                                                  ('16GB',2,22),
                                                                  ('Chip M1',3,23),
                                                                  ('256 GB',4,23),
                                                                  ('16GB',2,23),
                                                                  ('Chip M2',3,24),
                                                                  ('256 GB',4,24),
                                                                  ('8GB',2,24),
                                                                  ('Chip M2',3,25);
INSERT INTO attribute_product (value,attribute_id,product_id) VALUES
                                                                  ('512 GB',4,25),
                                                                  ('8GB',2,25),
                                                                  ('Chip M2',3,26),
                                                                  ('256 GB',4,26),
                                                                  ('16GB',2,26);


INSERT INTO customer_roles (customers_customer_id,roles_role_id) VALUES
                                                                     (1,2),
                                                                     (1,1);

INSERT INTO image (color,url,produc_id) VALUES
                                                     ('Gold','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iphone-14-promax-vang-650x650-1.png',1),
                                                     ('Silver','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iphone-14-promax-silver-650x650-1.png',1),
                                                     ('Black','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iphone-14-promax-den-650x650-1.png',1),
                                                     ('Deep Purple','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iphone-14-pro-max-tim-650x650-1.png',1),
                                                     ('Gold','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iphone-14-promax-vang-650x650-1.png',2),
                                                     ('Silver','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iphone-14-promax-silver-650x650-1.png',2),
                                                     ('Black','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iphone-14-promax-den-650x650-1.png',2),
                                                     ('Deep Purple','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iphone-14-pro-max-tim-650x650-1.png',2),
                                                     ('Gold','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iphone-14-promax-vang-650x650-1.png',3),
                                                     ('Silver','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iphone-14-promax-silver-650x650-1.png',3);
INSERT INTO image (color,url,produc_id) VALUES
                                                     ('Black','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iphone-14-promax-den-650x650-1.png',3),
                                                     ('Deep Purple','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iphone-14-pro-max-tim-650x650-1.png',3),
                                                     ('Gold','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iphone-14-promax-vang-650x650-1.png',4),
                                                     ('Silver','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iphone-14-promax-silver-650x650-1.png',4),
                                                     ('Black','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iphone-14-promax-den-650x650-1.png',4),
                                                     ('Deep Purple','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iphone-14-pro-max-tim-650x650-1.png',4),
                                                     ('Gold','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iphone-14-promax-vang-650x650-1.png',5),
                                                     ('Silver','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iphone-14-promax-silver-650x650-1.png',5),
                                                     ('Black','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iphone-14-promax-den-650x650-1.png',5),
                                                     ('Deep Purple','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iphone-14-pro-max-tim-650x650-1.png',5);
INSERT INTO image (color,url,produc_id) VALUES
                                                     ('Gold','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iphone-14-promax-vang-650x650-1.png',6),
                                                     ('Silver','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iphone-14-promax-silver-650x650-1.png',6),
                                                     ('Black','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iphone-14-promax-den-650x650-1.png',6),
                                                     ('Deep Purple','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iphone-14-pro-max-tim-650x650-1.png',6),
                                                     ('Gold','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iphone-13-pro-max-gold-650x650-2.png',7),
                                                     ('Green','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iphone-13-pro-max-thumbtz-650x650-1.png',7),
                                                     ('Blue','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iphone-13-pro-max-blue-650x650-1.png',7),
                                                     ('Graphite','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iphone-13-pro-max-grey-650x650-1.png',7),
                                                     ('Silver','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iphone-13-pro-max-silver-650x650-1.png',7),
                                                     ('Gold','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iphone-13-pro-max-gold-650x650-2.png',8);
INSERT INTO image (color,url,produc_id) VALUES
                                                     ('Green','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iphone-13-pro-max-thumbtz-650x650-1.png',8),
                                                     ('Blue','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iphone-13-pro-max-blue-650x650-1.png',8),
                                                     ('Graphite','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iphone-13-pro-max-grey-650x650-1.png',8),
                                                     ('Silver','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iphone-13-pro-max-silver-650x650-1.png',8),
                                                     ('Gold','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iphone-13-pro-max-gold-650x650-2.png',9),
                                                     ('Green','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iphone-13-pro-max-thumbtz-650x650-1.png',9),
                                                     ('Blue','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iphone-13-pro-max-blue-650x650-1.png',9),
                                                     ('Graphite','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iphone-13-pro-max-grey-650x650-1.png',9),
                                                     ('Silver','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iphone-13-pro-max-silver-650x650-1.png',9),
                                                     ('Pink','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iPhone_13_PDP_Position-1A_Color_Pink__VN-1536x1536.webp',10);
INSERT INTO image (color,url,produc_id) VALUES
                                                     ('Red','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iPhone_13_red-1536x1536.webp',10),
                                                     ('Blue','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iPhone_13_Blue__VN-1536x1536.webp',10),
                                                     ('Startlight','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iPhone_13Color_Starlight__VN-1536x1536.webp',10),
                                                     ('Midnight','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iPhone_13Midnight__VN-1536x1536.webp',10),
                                                     ('Green','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iPhone_13_Green_Color___SEA.jpg',10),
                                                     ('Pink','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iPhone_13_PDP_Position-1A_Color_Pink__VN-1536x1536.webp',11),
                                                     ('Red','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iPhone_13_red-1536x1536.webp',11),
                                                     ('Blue','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iPhone_13_Blue__VN-1536x1536.webp',11),
                                                     ('Startlight','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iPhone_13Color_Starlight__VN-1536x1536.webp',11),
                                                     ('Midnight','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iPhone_13Midnight__VN-1536x1536.webp',11);
INSERT INTO image (color,url,produc_id) VALUES
                                                     ('Green','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iPhone_13_Green_Color___SEA.jpg',11),
                                                     ('Pink','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iPhone_13_PDP_Position-1A_Color_Pink__VN-1536x1536.webp',12),
                                                     ('Red','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iPhone_13_red-1536x1536.webp',12),
                                                     ('Blue','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iPhone_13_Blue__VN-1536x1536.webp',12),
                                                     ('Startlight','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iPhone_13Color_Starlight__VN-1536x1536.webp',12),
                                                     ('Midnight','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iPhone_13Midnight__VN-1536x1536.webp',12),
                                                     ('Green','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iPhone_13_Green_Color___SEA.jpg',12),
                                                     ('Red','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iphone-12-red-1-650x650-1.png',13),
                                                     ('Purple','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iphone-12-purple-1-650x650-1.png',13),
                                                     ('Green','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iphone-12-green-1-1-650x650-1.png',13);
INSERT INTO image (color,url,produc_id) VALUES
                                                     ('Blue','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iphone-12-blue-1-650x650-1.png',13),
                                                     ('Black','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iphone-12-black-1-650x650-1.png',13),
                                                     ('White','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iphone-12-white-1-650x650-1.png',13),
                                                     ('Red','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iphone-12-red-1-650x650-1.png',14),
                                                     ('Purple','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iphone-12-purple-1-650x650-1.png',14),
                                                     ('Green','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iphone-12-green-1-1-650x650-1.png',14),
                                                     ('Blue','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iphone-12-blue-1-650x650-1.png',14),
                                                     ('Black','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iphone-12-black-1-650x650-1.png',14),
                                                     ('White','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iphone-12-white-1-650x650-1.png',14),
                                                     ('Space Gray','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/MacBook_Pro_14-m1-gray.jpg',15);
INSERT INTO image (color,url,produc_id) VALUES
                                                     ('Silver','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/MacBook+Pro+14+M1+Pro+Silver.jpg',15),
                                                     ('Space Gray','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/MacBook-Pro-13-M2-2022-gray.webp',16),
                                                     ('Silver','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/MacBook+Pro-13-M2-2022-silver.webp',16),
                                                     ('Pink','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iMac_m1_2021_24-in_Pink.webp',17),
                                                     ('Green','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iMac_m1_2021_24-in_Green_.webp',17),
                                                     ('Blue','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iMac_m1_2021_24-in_Blue_4.webp',17),
                                                     ('Silver','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iMac_m1_2021_24-in_Silver_4-1-1536x1536.webp',17),
                                                     ('Pink','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iMac_m1_2021_24-in_Pink.webp',18),
                                                     ('Green','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iMac_m1_2021_24-in_Green_.webp',18),
                                                     ('Blue','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iMac_m1_2021_24-in_Blue_4.webp',18);
INSERT INTO image (color,url,produc_id) VALUES
                                                     ('Silver','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iMac_m1_2021_24-in_Silver_4-1-1536x1536.webp',18),
                                                     ('Pink','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iMac_m1_2021_24-in_Pink.webp',19),
                                                     ('Green','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iMac_m1_2021_24-in_Green_.webp',19),
                                                     ('Blue','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iMac_m1_2021_24-in_Blue_4.webp',19),
                                                     ('Silver','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iMac_m1_2021_24-in_Silver_4-1-1536x1536.webp',19),
                                                     ('Pink','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iMac_m1_2021_24-in_Pink.webp',20),
                                                     ('Green','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iMac_m1_2021_24-in_Green_.webp',20),
                                                     ('Blue','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iMac_m1_2021_24-in_Blue_4.webp',20),
                                                     ('Silver','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iMac_m1_2021_24-in_Silver_4-1-1536x1536.webp',20),
                                                     ('Pink','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iMac_m1_2021_24-in_Pink.webp',21);
INSERT INTO image (color,url,produc_id) VALUES
                                                     ('Green','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iMac_m1_2021_24-in_Green_.webp',21),
                                                     ('Blue','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iMac_m1_2021_24-in_Blue_4.webp',21),
                                                     ('Silver','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iMac_m1_2021_24-in_Silver_4-1-1536x1536.webp',21),
                                                     ('Silver','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/MacBook+Pro-13-M2-2022-silver.webp',22),
                                                     ('Space Gray','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/MacBook-Pro-13-M2-2022-gray.webp',22),
                                                     ('Silver','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/MacBook+Pro-13-M2-2022-silver.webp',23),
                                                     ('Space Gray','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/MacBook-Pro-13-M2-2022-gray.webp',23),
                                                     ('Space Gray','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/MacBook-Air-M2-2022-gray.png',24),
                                                     ('Startlight','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/MacBook-Air-M2-2022-start.png',24),
                                                     ('Silver','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/MacBook-Air-M2-2022-silver.png',24);
INSERT INTO image (color,url,produc_id) VALUES
                                                     ('Midnight','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/MacBook-Air-M2-2022-midnight.png',24),
                                                     ('Space Gray','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/MacBook-Air-M2-2022-gray.png',25),
                                                     ('Startlight','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/MacBook-Air-M2-2022-start.png',25),
                                                     ('Silver','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/MacBook-Air-M2-2022-silver.png',25),
                                                     ('Midnight','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/MacBook-Air-M2-2022-midnight.png',25),
                                                     ('Space Gray','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/MacBook-Air-M2-2022-gray.png',26),
                                                     ('Startlight','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/MacBook-Air-M2-2022-start.png',26),
                                                     ('Silver','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/MacBook-Air-M2-2022-silver.png',26),
                                                     ('Midnight','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/MacBook-Air-M2-2022-midnight.png',26);