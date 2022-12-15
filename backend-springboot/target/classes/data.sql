INSERT INTO attribute (attribute_name,description) VALUES
                                                       ('RAM',NULL),
                                                       ('CPU',NULL),
                                                       ('SSD',NULL),
                                                       ('RAM 1',NULL),
                                                       ('GPU',NULL),
                                                       ('Dung Lượng',NULL),
                                                       ('Chip',NULL),
                                                       ('Storage',NULL);


INSERT INTO role (role_name) VALUES
                                 ('ROLE_USER'),
                                 ('ROLE_ADMIN');


INSERT INTO category (category_name) VALUES
                                         ('Iphone'),
                                         ('Ipad'),
                                         ('Macbook');


INSERT INTO customer (address,created_date,email,first_name,last_name,"password",phone,user_name,cart_id) VALUES
                                                                                                              ('Binh Duong',NULL,'vinhquang1873@gmail.com','Le quang','Quang','$2a$10$Dniq4P5IWgUcVLsT/kl.aO5tLWRRSXwhLsaVM2X1tKO8YgNz/bswG','0968520710','ltv1874',1),
                                                                                                              ('1004, quốc lộ 1a, phường linh trung, quận thủ đức',NULL,'vinhquang1872323@gmail.com','Le','Quang','$2a$10$wl8MUtri9ik8PB19TKT45OK7UeV/03NjVBSm8ZcRVEVGhFQpcPH6C','0968520710','ltv18744',NULL),
                                                                                                              ('1004, quốc lộ 1a, phường linh trung, quận thủ đức',NULL,'vinhquang187333@gmail.com','Vinh','Quang Vinh','$2a$10$Q9Z6TXE8PZW3Vv6iarysZ.6ewuGl2mf4wlDBn1/IDBIqg6Zu2/dJa','0968520710','0968520710',16),
                                                                                                              ('1004, quốc lộ 1a, phường linh trung, quận thủ đức',NULL,'vinhquang18733@gmail.com','Quang','Le','$2a$10$W7aQ8GvPMeFbwBZo/.VJRO67lQZT7Iouoh7Mg19O7rmox.vkiGIdm','0968520710','ltv18745',17);

INSERT INTO customer_roles (customers_customer_id,roles_role_id) VALUES
                                                                     (1,2),
                                                                     (1,1),
                                                                     (11,1),
                                                                     (12,1),
                                                                     (11,2),
                                                                     (13,1);

INSERT INTO product (created_date,description,price,product_details,product_name,updated_date,category_id,order_item_id) VALUES
                                                                                                                             ('2022-11-04','',27990000.00,'null','iPad Pro M2 11 inch Wi-Fi + Cellular 128GB',NULL,3,NULL),
                                                                                                                             ('2022-11-02','',55950000.00,'null','MacBook Pro 14 M1 Pro (16-Core/16GB/1TB)',NULL,1,NULL),
                                                                                                                             ('2022-11-02','The MacBook Air (a review model from Apple) dispenses with the classical (and hugely popular) wedge shape that helped define the range. Apple’s newest machine is slim (0.4 inches high), occupies 20% less volume than before, and sports a design that very much brings it into line with the aesthetics across Apple’s range – rounded corners, thin, rectangular. I see it as similar to (but thinner than) a MacBook Pro. These design decisions matter because the MacBook Air is Apple’s most popular notebook, which de facto means it is also the company’s most popular Mac. (The assumptions is that Apple’s notebooks outsell its desktops by at least two to one.) The MacBook Air is also fanless, which means no matter what you ask it to do, it will be quieter than a whisper in the silence of the night.

The Mac weighs just 2.7 pounds. Dimensions are 0.4-in. by 12-in. by 8.4-in. The last-generation model with an M1 chip weighs 2.8-pounds and is roughly the same size, though 0.6 inches at the thickest point. (The last ever Intel model weighed 2.75 pounds and was about as thick. You’d be forgiven to see some consistency here.)',28990000.00,'null','MacBook Air M2 2022 (8GB RAM|256GB SSD)',NULL,2,NULL),
                                                                                                                             ('2022-11-02','Introduced on September 14, 2021, the iPhone 13 Pro and iPhone 13 Pro Max were Apple''s 2021 flagship iPhones, but have since been replaced with the iPhone 14 Pro and iPhone 14 Pro Max. Apple has discontinued the iPhone 13 Pro models and is no longer selling them, but they may be available on the refurbished site and from third-party retailers while supplies last.

The 6.1-inch iPhone 13 Pro is the successor to the iPhone 12 Pro, while the 6.7-inch iPhone 13 Pro Max is the replacement for the iPhone 12 Pro Max. Both of the new iPhone 13 Pro models are nearly identical in design to the iPhone 12 Pro models, featuring flat edges, a stainless steel frame, a textured matte glass back, and a slight increase in thickness (7.65mm). The iPhone 13 Pro models are available in Silver, Gold, Sierra Blue, Graphite, and Alpine Green.

Both of the new models feature OLED Super Retina XDR Displays that support ProMotion technology with adaptive refresh rates ranging from 10Hz up to 120Hz, much like the iPad Pro models. The displays are up to 25 percent brighter outdoors.

The iPhone 13 Pro has a 2532x1170 resolution with 460 pixels per inch, while the iPhone 13 Pro Max has a 2778x1284 resolution with 458 pixels per inch. Both iPhones feature 1200 nits max brightness for HDR, along with True Tone to match the color temperature of the display to the ambient light, Wide Color for rich, vivid hues, and Haptic Touch for feedback.

The front-facing TrueDepth camera system has been updated and the Face ID notch is now smaller, taking up less overall space. Like last year''s models, the iPhone 13 Pro and 13 Pro Max feature a Ceramic Shield cover glass that is infused with nano-ceramic crystals for better protection from drops. IP68 water and dust resistance is included, and the new iPhones can hold up to submersion in 6 meters of water for up to 30 minutes.

An upgraded A15 Bionic Chip powers the new iPhones. It features a 6-core CPU with 2 performance cores and 4 efficiency cores and a 5-core GPU, which is one more GPU core than is available in the iPhone 13 models. There''s also a 16-core Neural Engine. The 5-core GPU offers 50% faster graphics performance than any other smartphone chip.',27450000.00,'null','iPhone 13 Promax 128GB',NULL,1,NULL),
                                                                                                                             ('2022-11-02','Introduced on September 14, 2021, the iPhone 13 Pro and iPhone 13 Pro Max were Apple''s 2021 flagship iPhones, but have since been replaced with the iPhone 14 Pro and iPhone 14 Pro Max. Apple has discontinued the iPhone 13 Pro models and is no longer selling them, but they may be available on the refurbished site and from third-party retailers while supplies last.

The 6.1-inch iPhone 13 Pro is the successor to the iPhone 12 Pro, while the 6.7-inch iPhone 13 Pro Max is the replacement for the iPhone 12 Pro Max. Both of the new iPhone 13 Pro models are nearly identical in design to the iPhone 12 Pro models, featuring flat edges, a stainless steel frame, a textured matte glass back, and a slight increase in thickness (7.65mm). The iPhone 13 Pro models are available in Silver, Gold, Sierra Blue, Graphite, and Alpine Green.

Both of the new models feature OLED Super Retina XDR Displays that support ProMotion technology with adaptive refresh rates ranging from 10Hz up to 120Hz, much like the iPad Pro models. The displays are up to 25 percent brighter outdoors.

The iPhone 13 Pro has a 2532x1170 resolution with 460 pixels per inch, while the iPhone 13 Pro Max has a 2778x1284 resolution with 458 pixels per inch. Both iPhones feature 1200 nits max brightness for HDR, along with True Tone to match the color temperature of the display to the ambient light, Wide Color for rich, vivid hues, and Haptic Touch for feedback.

The front-facing TrueDepth camera system has been updated and the Face ID notch is now smaller, taking up less overall space. Like last year''s models, the iPhone 13 Pro and 13 Pro Max feature a Ceramic Shield cover glass that is infused with nano-ceramic crystals for better protection from drops. IP68 water and dust resistance is included, and the new iPhones can hold up to submersion in 6 meters of water for up to 30 minutes.

An upgraded A15 Bionic Chip powers the new iPhones. It features a 6-core CPU with 2 performance cores and 4 efficiency cores and a 5-core GPU, which is one more GPU core than is available in the iPhone 13 models. There''s also a 16-core Neural Engine. The 5-core GPU offers 50% faster graphics performance than any other smartphone chip.',29350000.00,'null','iPhone 13 Promax 256GB',NULL,1,NULL),
                                                                                                                             ('2022-11-02','Apple on October 13, 2020, introduced the iPhone 12 has been replaced by the iPhone 13 and iPhone 14 lineups. Apple is continuing to sell the iPhone 12 at a discounted price, while the 5.4-inch iPhone 12 mini has been discontinued entirely.',16190000.00,'null','iPhone 12 128GB',NULL,1,NULL),
                                                                                                                             ('2022-11-09','iPad Pro 12.9 inch (2021) Chiếc iPad đỉnh cao với chip Apple M1 siêu mạnh, màn hình Liquid Retina XDR 12.9 inch sống động, kết nối không dây siêu nhanh. Thắt dây an toàn vào đi nào.

Chip Apple M1 nâng hiệu suất lên một đẳng cấp mới
 Màn hình Liquid Retina XDR 12.9 inch lộng lẫy với ProMotion, True Tone và dải màu rộng P3
 Hệ thống camera TrueDepth với camera trước Ultra Wide tích hợp tính năng Trung Tâm Sân Khấu
 Camera Wide 12MP, camera Ultra Wide 10MP và LiDAR Scanner cho trải nghiệm thực tế ảo tăng cường sống động
 Luôn kết nối với Wi-Fi 6 siêu nhanh và mạng LTE
 Làm được nhiều việc hơn với thời lượng pin bền bỉ cả ngày
 Cổng Thunderbolt cho kết nối nhanh với bộ nhớ ngoài, màn hình và dock
 Xác thực bảo mật với Face ID
 Bốn loa âm thanh và năm micro chuẩn studio
 Hỗ trợ Apple Pencil (thế hệ thứ 2), Magic Keyboard và Smart Keyboard Folio
iPadOS mạnh mẽ, trực quan và được thiết kế riêng cho iPad
 Hơn 1 triệu ứng dụng trên App Store dành riêng cho iPad
Bộ sản phẩm bao gồm:',24750000.00,NULL,' iPad Pro 12.9 inch (M1, 2021) 128 GB',NULL,3,NULL),
                                                                                                                             ('2022-11-02','Introduced on September 14, 2021, the iPhone 13 Pro and iPhone 13 Pro Max were Apple''s 2021 flagship iPhones, but have since been replaced with the iPhone 14 Pro and iPhone 14 Pro Max. Apple has discontinued the iPhone 13 Pro models and is no longer selling them, but they may be available on the refurbished site and from third-party retailers while supplies last.

The 6.1-inch iPhone 13 Pro is the successor to the iPhone 12 Pro, while the 6.7-inch iPhone 13 Pro Max is the replacement for the iPhone 12 Pro Max. Both of the new iPhone 13 Pro models are nearly identical in design to the iPhone 12 Pro models, featuring flat edges, a stainless steel frame, a textured matte glass back, and a slight increase in thickness (7.65mm). The iPhone 13 Pro models are available in Silver, Gold, Sierra Blue, Graphite, and Alpine Green.

Both of the new models feature OLED Super Retina XDR Displays that support ProMotion technology with adaptive refresh rates ranging from 10Hz up to 120Hz, much like the iPad Pro models. The displays are up to 25 percent brighter outdoors.

The iPhone 13 Pro has a 2532x1170 resolution with 460 pixels per inch, while the iPhone 13 Pro Max has a 2778x1284 resolution with 458 pixels per inch. Both iPhones feature 1200 nits max brightness for HDR, along with True Tone to match the color temperature of the display to the ambient light, Wide Color for rich, vivid hues, and Haptic Touch for feedback.

The front-facing TrueDepth camera system has been updated and the Face ID notch is now smaller, taking up less overall space. Like last year''s models, the iPhone 13 Pro and 13 Pro Max feature a Ceramic Shield cover glass that is infused with nano-ceramic crystals for better protection from drops. IP68 water and dust resistance is included, and the new iPhones can hold up to submersion in 6 meters of water for up to 30 minutes.

An upgraded A15 Bionic Chip powers the new iPhones. It features a 6-core CPU with 2 performance cores and 4 efficiency cores and a 5-core GPU, which is one more GPU core than is available in the iPhone 13 models. There''s also a 16-core Neural Engine. The 5-core GPU offers 50% faster graphics performance than any other smartphone chip.',33850000.00,'null','iPhone 13 Promax 512GB',NULL,1,NULL),
                                                                                                                             ('2022-11-02','Introduced on September 14, 2021, the iPhone 13 Pro and iPhone 13 Pro Max were Apple''s 2021 flagship iPhones, but have since been replaced with the iPhone 14 Pro and iPhone 14 Pro Max. Apple has discontinued the iPhone 13 Pro models and is no longer selling them, but they may be available on the refurbished site and from third-party retailers while supplies last.

The 6.1-inch iPhone 13 Pro is the successor to the iPhone 12 Pro, while the 6.7-inch iPhone 13 Pro Max is the replacement for the iPhone 12 Pro Max. Both of the new iPhone 13 Pro models are nearly identical in design to the iPhone 12 Pro models, featuring flat edges, a stainless steel frame, a textured matte glass back, and a slight increase in thickness (7.65mm). The iPhone 13 Pro models are available in Silver, Gold, Sierra Blue, Graphite, and Alpine Green.

Both of the new models feature OLED Super Retina XDR Displays that support ProMotion technology with adaptive refresh rates ranging from 10Hz up to 120Hz, much like the iPad Pro models. The displays are up to 25 percent brighter outdoors.

The iPhone 13 Pro has a 2532x1170 resolution with 460 pixels per inch, while the iPhone 13 Pro Max has a 2778x1284 resolution with 458 pixels per inch. Both iPhones feature 1200 nits max brightness for HDR, along with True Tone to match the color temperature of the display to the ambient light, Wide Color for rich, vivid hues, and Haptic Touch for feedback.

The front-facing TrueDepth camera system has been updated and the Face ID notch is now smaller, taking up less overall space. Like last year''s models, the iPhone 13 Pro and 13 Pro Max feature a Ceramic Shield cover glass that is infused with nano-ceramic crystals for better protection from drops. IP68 water and dust resistance is included, and the new iPhones can hold up to submersion in 6 meters of water for up to 30 minutes.

An upgraded A15 Bionic Chip powers the new iPhones. It features a 6-core CPU with 2 performance cores and 4 efficiency cores and a 5-core GPU, which is one more GPU core than is available in the iPhone 13 models. There''s also a 16-core Neural Engine. The 5-core GPU offers 50% faster graphics performance than any other smartphone chip.',21690000.00,'null','iPhone 13 128GB',NULL,1,NULL),
                                                                                                                             ('2022-11-02','Introduced on September 14, 2021, the iPhone 13 Pro and iPhone 13 Pro Max were Apple''s 2021 flagship iPhones, but have since been replaced with the iPhone 14 Pro and iPhone 14 Pro Max. Apple has discontinued the iPhone 13 Pro models and is no longer selling them, but they may be available on the refurbished site and from third-party retailers while supplies last.

The 6.1-inch iPhone 13 Pro is the successor to the iPhone 12 Pro, while the 6.7-inch iPhone 13 Pro Max is the replacement for the iPhone 12 Pro Max. Both of the new iPhone 13 Pro models are nearly identical in design to the iPhone 12 Pro models, featuring flat edges, a stainless steel frame, a textured matte glass back, and a slight increase in thickness (7.65mm). The iPhone 13 Pro models are available in Silver, Gold, Sierra Blue, Graphite, and Alpine Green.

Both of the new models feature OLED Super Retina XDR Displays that support ProMotion technology with adaptive refresh rates ranging from 10Hz up to 120Hz, much like the iPad Pro models. The displays are up to 25 percent brighter outdoors.

The iPhone 13 Pro has a 2532x1170 resolution with 460 pixels per inch, while the iPhone 13 Pro Max has a 2778x1284 resolution with 458 pixels per inch. Both iPhones feature 1200 nits max brightness for HDR, along with True Tone to match the color temperature of the display to the ambient light, Wide Color for rich, vivid hues, and Haptic Touch for feedback.

The front-facing TrueDepth camera system has been updated and the Face ID notch is now smaller, taking up less overall space. Like last year''s models, the iPhone 13 Pro and 13 Pro Max feature a Ceramic Shield cover glass that is infused with nano-ceramic crystals for better protection from drops. IP68 water and dust resistance is included, and the new iPhones can hold up to submersion in 6 meters of water for up to 30 minutes.

An upgraded A15 Bionic Chip powers the new iPhones. It features a 6-core CPU with 2 performance cores and 4 efficiency cores and a 5-core GPU, which is one more GPU core than is available in the iPhone 13 models. There''s also a 16-core Neural Engine. The 5-core GPU offers 50% faster graphics performance than any other smartphone chip.',21690000.00,'null','iPhone 13 256GB',NULL,1,NULL);
INSERT INTO product (created_date,description,price,product_details,product_name,updated_date,category_id,order_item_id) VALUES
                                                                                                                             ('2022-11-02','Introduced on September 14, 2021, the iPhone 13 Pro and iPhone 13 Pro Max were Apple''s 2021 flagship iPhones, but have since been replaced with the iPhone 14 Pro and iPhone 14 Pro Max. Apple has discontinued the iPhone 13 Pro models and is no longer selling them, but they may be available on the refurbished site and from third-party retailers while supplies last.

The 6.1-inch iPhone 13 Pro is the successor to the iPhone 12 Pro, while the 6.7-inch iPhone 13 Pro Max is the replacement for the iPhone 12 Pro Max. Both of the new iPhone 13 Pro models are nearly identical in design to the iPhone 12 Pro models, featuring flat edges, a stainless steel frame, a textured matte glass back, and a slight increase in thickness (7.65mm). The iPhone 13 Pro models are available in Silver, Gold, Sierra Blue, Graphite, and Alpine Green.

Both of the new models feature OLED Super Retina XDR Displays that support ProMotion technology with adaptive refresh rates ranging from 10Hz up to 120Hz, much like the iPad Pro models. The displays are up to 25 percent brighter outdoors.

The iPhone 13 Pro has a 2532x1170 resolution with 460 pixels per inch, while the iPhone 13 Pro Max has a 2778x1284 resolution with 458 pixels per inch. Both iPhones feature 1200 nits max brightness for HDR, along with True Tone to match the color temperature of the display to the ambient light, Wide Color for rich, vivid hues, and Haptic Touch for feedback.

The front-facing TrueDepth camera system has been updated and the Face ID notch is now smaller, taking up less overall space. Like last year''s models, the iPhone 13 Pro and 13 Pro Max feature a Ceramic Shield cover glass that is infused with nano-ceramic crystals for better protection from drops. IP68 water and dust resistance is included, and the new iPhones can hold up to submersion in 6 meters of water for up to 30 minutes.

An upgraded A15 Bionic Chip powers the new iPhones. It features a 6-core CPU with 2 performance cores and 4 efficiency cores and a 5-core GPU, which is one more GPU core than is available in the iPhone 13 models. There''s also a 16-core Neural Engine. The 5-core GPU offers 50% faster graphics performance than any other smartphone chip.',24890000.00,'null','iPhone 13 512GB',NULL,1,NULL),
                                                                                                                             ('2022-11-02','CUPERTINO, CALIFORNIA Apple today introduced iPhone 14 and iPhone 14 Plus, featuring two sizes — 6.1 inches and 6.7 inches — in a sophisticated design with impressive camera upgrades and groundbreaking new safety capabilities. iPhone 14 and iPhone 14 Plus take stunning photos and video with a powerful camera system featuring new Main and front TrueDepth cameras, the Ultra Wide camera for unique perspectives, and Photonic Engine — an enhanced image pipeline. Both models include the A15 Bionic chip with a 5-core GPU, which offers incredible performance and efficiency for demanding workloads, and is designed with privacy and security built in. iPhone 14 and iPhone 14 Plus introduce critical safety capabilities such as Crash Detection and Emergency SOS via satellite, a first in the industry. And with amazing battery life, industry-leading durability features, and super-fast 5G, this iPhone lineup is more advanced than ever before. iPhone 14 and iPhone 14 Plus will be available in midnight, blue, starlight, purple, and (PRODUCT)RED1 finishes. Pre-orders begin Friday, September 9, with availability for iPhone 14 beginning Friday, September 16, and availability for iPhone 14 Plus beginning Friday, October 7.',28950000.00,'null','iPhone 14 256GB',NULL,1,NULL),
                                                                                                                             ('2022-11-02','CUPERTINO, CALIFORNIA Apple today introduced iPhone 14 and iPhone 14 Plus, featuring two sizes — 6.1 inches and 6.7 inches — in a sophisticated design with impressive camera upgrades and groundbreaking new safety capabilities. iPhone 14 and iPhone 14 Plus take stunning photos and video with a powerful camera system featuring new Main and front TrueDepth cameras, the Ultra Wide camera for unique perspectives, and Photonic Engine — an enhanced image pipeline. Both models include the A15 Bionic chip with a 5-core GPU, which offers incredible performance and efficiency for demanding workloads, and is designed with privacy and security built in. iPhone 14 and iPhone 14 Plus introduce critical safety capabilities such as Crash Detection and Emergency SOS via satellite, a first in the industry. And with amazing battery life, industry-leading durability features, and super-fast 5G, this iPhone lineup is more advanced than ever before. iPhone 14 and iPhone 14 Plus will be available in midnight, blue, starlight, purple, and (PRODUCT)RED1 finishes. Pre-orders begin Friday, September 9, with availability for iPhone 14 beginning Friday, September 16, and availability for iPhone 14 Plus beginning Friday, October 7.',35950000.00,'null','iPhone 14 512GB',NULL,1,NULL),
                                                                                                                             ('2022-11-02','CUPERTINO, CALIFORNIA Apple today introduced iPhone 14 and iPhone 14 Plus, featuring two sizes — 6.1 inches and 6.7 inches — in a sophisticated design with impressive camera upgrades and groundbreaking new safety capabilities. iPhone 14 and iPhone 14 Plus take stunning photos and video with a powerful camera system featuring new Main and front TrueDepth cameras, the Ultra Wide camera for unique perspectives, and Photonic Engine — an enhanced image pipeline. Both models include the A15 Bionic chip with a 5-core GPU, which offers incredible performance and efficiency for demanding workloads, and is designed with privacy and security built in. iPhone 14 and iPhone 14 Plus introduce critical safety capabilities such as Crash Detection and Emergency SOS via satellite, a first in the industry. And with amazing battery life, industry-leading durability features, and super-fast 5G, this iPhone lineup is more advanced than ever before. iPhone 14 and iPhone 14 Plus will be available in midnight, blue, starlight, purple, and (PRODUCT)RED1 finishes. Pre-orders begin Friday, September 9, with availability for iPhone 14 beginning Friday, September 16, and availability for iPhone 14 Plus beginning Friday, October 7.',36450000.00,'null','iPhone 14 Pro Max 256GB',NULL,1,NULL),
                                                                                                                             ('2022-11-02','CUPERTINO, CALIFORNIA Apple today introduced iPhone 14 and iPhone 14 Plus, featuring two sizes — 6.1 inches and 6.7 inches — in a sophisticated design with impressive camera upgrades and groundbreaking new safety capabilities. iPhone 14 and iPhone 14 Plus take stunning photos and video with a powerful camera system featuring new Main and front TrueDepth cameras, the Ultra Wide camera for unique perspectives, and Photonic Engine — an enhanced image pipeline. Both models include the A15 Bionic chip with a 5-core GPU, which offers incredible performance and efficiency for demanding workloads, and is designed with privacy and security built in. iPhone 14 and iPhone 14 Plus introduce critical safety capabilities such as Crash Detection and Emergency SOS via satellite, a first in the industry. And with amazing battery life, industry-leading durability features, and super-fast 5G, this iPhone lineup is more advanced than ever before. iPhone 14 and iPhone 14 Plus will be available in midnight, blue, starlight, purple, and (PRODUCT)RED1 finishes. Pre-orders begin Friday, September 9, with availability for iPhone 14 beginning Friday, September 16, and availability for iPhone 14 Plus beginning Friday, October 7.',40950000.00,'null','iPhone 14 Pro Max 512GB',NULL,1,NULL),
                                                                                                                             ('2022-11-02','Apple on October 13, 2020, introduced the iPhone 12 has been replaced by the iPhone 13 and iPhone 14 lineups. Apple is continuing to sell the iPhone 12 at a discounted price, while the 5.4-inch iPhone 12 mini has been discontinued entirely.

The 6.1-inch iPhone 12 was a successor to the iPhone 11 from 2019, while the 5.4-inch iPhone 12 was an all-new size and marked the smallest iPhone Apple had introduced since the 2016 iPhone SE. Aside from screen size and battery size, the two phones are technically identical. With its small size, the iPhone 12 mini is ideal for those who prefer an iPhone that can be used one-handed.

The iPhone 12 and 12 mini feature Super Retina XDR OLED displays, with an edge-to-edge design with the exception of the Face ID notch and small bezels around the edge.

The 5.4-inch iPhone 12 mini has a resolution of 2430 x 1080 with 476 pixels per inch and the 6.1-inch iPhone 12 has a resolution of 2532 x 1170 with 460 pixels per inch. The displays offer HDR support with 1200 nits peak brightness, Wide Color for vivid, true-to-life colors, Haptic Touch for feedback, and True Tone to match the color temperature of the display to the ambient lighting for a more natural viewing experience.

Apple overhauled the design of the iPhone 12 lineup in 2020, introducing flat edges that were a departure from the rounded edges of prior models and are similar in design to the iPad Pro. The front of the iPhone is protected by a Ceramic Shield cover that replaces the standard cover glass of previous models. Apple says the Ceramic Shield is infused with nano-ceramic crystals and offers 4x better drop performance. Apple largely carried over the iPhone 12 design and Ceramic Shield for the iPhone 13, and the two generations look very similar to one another.',17590000.00,'null','iPhone 12 128GB',NULL,1,NULL),
                                                                                                                             ('2022-11-02','Cupertino, California — Apple today unveiled an all-new 16-inch MacBook Pro — the world’s best pro notebook — designed for developers, photographers, filmmakers, scientists, music producers and anyone who relies on a Mac to create their life’s best work. The all-new MacBook Pro features a brilliant 16-inch Retina Display, the latest 8-core processors, up to 64GB of memory, next-generation graphics with up to 8GB of VRAM and a new advanced thermal design, making it the most powerful MacBook Pro ever.
Featuring a new Magic Keyboard with a redesigned scissor mechanism and 1mm travel for a more satisfying key feel, the 16-inch MacBook Pro delivers the best typing experience ever in a Mac notebook. The 16-inch MacBook Pro also includes a six-speaker sound system, longer battery life, Touch Bar, Touch ID, the Force Touch trackpad and the Apple T2 Security Chip.
“Our pro customers tell us they want their next MacBook Pro to have a larger display, blazing-fast performance, the biggest battery possible, the best notebook keyboard ever, awesome speakers and massive amounts of storage, and the 16-inch MacBook Pro delivers all of that and more,” said Tom Boger, Apple’s senior director of Mac and iPad Product Marketing. “With its brilliant 16-inch Retina display, 8-core processors, next-gen pro graphics, even better thermal design, new Magic Keyboard, six-speaker sound system, 100Wh battery, up to 8TB of storage and 64GB of fast memory, the 16-inch MacBook Pro is the world’s best pro notebook.”',35190000.00,'null','MacBook Pro 13 M2 2022 (8GB RAM|512GB SSD)',NULL,2,NULL),
                                                                                                                             ('2022-11-02','Cupertino, California — Apple today unveiled an all-new 16-inch MacBook Pro — the world’s best pro notebook — designed for developers, photographers, filmmakers, scientists, music producers and anyone who relies on a Mac to create their life’s best work. The all-new MacBook Pro features a brilliant 16-inch Retina Display, the latest 8-core processors, up to 64GB of memory, next-generation graphics with up to 8GB of VRAM and a new advanced thermal design, making it the most powerful MacBook Pro ever.
Featuring a new Magic Keyboard with a redesigned scissor mechanism and 1mm travel for a more satisfying key feel, the 16-inch MacBook Pro delivers the best typing experience ever in a Mac notebook. The 16-inch MacBook Pro also includes a six-speaker sound system, longer battery life, Touch Bar, Touch ID, the Force Touch trackpad and the Apple T2 Security Chip.
“Our pro customers tell us they want their next MacBook Pro to have a larger display, blazing-fast performance, the biggest battery possible, the best notebook keyboard ever, awesome speakers and massive amounts of storage, and the 16-inch MacBook Pro delivers all of that and more,” said Tom Boger, Apple’s senior director of Mac and iPad Product Marketing. “With its brilliant 16-inch Retina display, 8-core processors, next-gen pro graphics, even better thermal design, new Magic Keyboard, six-speaker sound system, 100Wh battery, up to 8TB of storage and 64GB of fast memory, the 16-inch MacBook Pro is the world’s best pro notebook.”',40890000.00,'null','MacBook Pro 13 M2 2022 (16GB RAM|512 SSD)',NULL,2,NULL),
                                                                                                                             ('2022-11-02','Cupertino, California — Apple today unveiled an all-new 16-inch MacBook Pro — the world’s best pro notebook — designed for developers, photographers, filmmakers, scientists, music producers and anyone who relies on a Mac to create their life’s best work. The all-new MacBook Pro features a brilliant 16-inch Retina Display, the latest 8-core processors, up to 64GB of memory, next-generation graphics with up to 8GB of VRAM and a new advanced thermal design, making it the most powerful MacBook Pro ever.
Featuring a new Magic Keyboard with a redesigned scissor mechanism and 1mm travel for a more satisfying key feel, the 16-inch MacBook Pro delivers the best typing experience ever in a Mac notebook. The 16-inch MacBook Pro also includes a six-speaker sound system, longer battery life, Touch Bar, Touch ID, the Force Touch trackpad and the Apple T2 Security Chip.
“Our pro customers tell us they want their next MacBook Pro to have a larger display, blazing-fast performance, the biggest battery possible, the best notebook keyboard ever, awesome speakers and massive amounts of storage, and the 16-inch MacBook Pro delivers all of that and more,” said Tom Boger, Apple’s senior director of Mac and iPad Product Marketing. “With its brilliant 16-inch Retina display, 8-core processors, next-gen pro graphics, even better thermal design, new Magic Keyboard, six-speaker sound system, 100Wh battery, up to 8TB of storage and 64GB of fast memory, the 16-inch MacBook Pro is the world’s best pro notebook.”',36950000.00,'null','MacBook Pro 13 M2 2022 (16GB RAM|256 SSD)',NULL,2,NULL),
                                                                                                                             ('2022-11-02','Display analyst Ross Young and others have stated that the iMac Pro will have a 27-inch display, which we have to admit to being disappointed by. However, the observations about a 27-inch display may have referred to the Studio Display. There could still be a iMac with a larger display in the pipeline.

A larger display would certainly make the iMac Pro more attractive to potential buyers. Next to the 24in iMac, with its 4.5K display (4,480 x 2,520 pixels), the 27-inch iMac only looks slightly more impressive with its 5K display (5,120 x 2,880 pixels). But it’s not only the 24-inch iMac that the iMac Pro will be compared to – the 27-inch screen also doesn’t compare particularly favourably with many modern displays that are larger than 30-inch (including the 32-inch Apple Pro Display XDR).

Perhaps new screen technology will allow Apple to squeeze in more pixels into a 27-inch display, but we would like to see a 32-inch display on the new iMac Pro.

A larger display could be possible without increasing the size of the iMac significantly. The 27-inch iMac measures 25.6 inches wide, 20.3 inches tall, and 8 inches deep, and while Apple’s new design helps make the 24-inch iMac smaller than the 21.5-inch iMac it replaced, there isn’t that much of a difference in size (21.5 inches x 18.1 inches x 5.8 inches (with the stand) vs 20.8 inches x 17.7 inches x 6.9 inches).',39890000.00,'null','iMac M1 2021 24 inch (8-Core GPU/16GB/256GB)',NULL,2,NULL);
INSERT INTO product (created_date,description,price,product_details,product_name,updated_date,category_id,order_item_id) VALUES
                                                                                                                             ('2022-11-02','Display analyst Ross Young and others have stated that the iMac Pro will have a 27-inch display, which we have to admit to being disappointed by. However, the observations about a 27-inch display may have referred to the Studio Display. There could still be a iMac with a larger display in the pipeline.

A larger display would certainly make the iMac Pro more attractive to potential buyers. Next to the 24in iMac, with its 4.5K display (4,480 x 2,520 pixels), the 27-inch iMac only looks slightly more impressive with its 5K display (5,120 x 2,880 pixels). But it’s not only the 24-inch iMac that the iMac Pro will be compared to – the 27-inch screen also doesn’t compare particularly favourably with many modern displays that are larger than 30-inch (including the 32-inch Apple Pro Display XDR).

Perhaps new screen technology will allow Apple to squeeze in more pixels into a 27-inch display, but we would like to see a 32-inch display on the new iMac Pro.

A larger display could be possible without increasing the size of the iMac significantly. The 27-inch iMac measures 25.6 inches wide, 20.3 inches tall, and 8 inches deep, and while Apple’s new design helps make the 24-inch iMac smaller than the 21.5-inch iMac it replaced, there isn’t that much of a difference in size (21.5 inches x 18.1 inches x 5.8 inches (with the stand) vs 20.8 inches x 17.7 inches x 6.9 inches).',26950000.00,'null','iMac M1 2021 24 inch (7-Core GPU/8GB/256GB)',NULL,2,NULL),
                                                                                                                             ('2022-11-02','Display analyst Ross Young and others have stated that the iMac Pro will have a 27-inch display, which we have to admit to being disappointed by. However, the observations about a 27-inch display may have referred to the Studio Display. There could still be a iMac with a larger display in the pipeline.

A larger display would certainly make the iMac Pro more attractive to potential buyers. Next to the 24in iMac, with its 4.5K display (4,480 x 2,520 pixels), the 27-inch iMac only looks slightly more impressive with its 5K display (5,120 x 2,880 pixels). But it’s not only the 24-inch iMac that the iMac Pro will be compared to – the 27-inch screen also doesn’t compare particularly favourably with many modern displays that are larger than 30-inch (including the 32-inch Apple Pro Display XDR).

Perhaps new screen technology will allow Apple to squeeze in more pixels into a 27-inch display, but we would like to see a 32-inch display on the new iMac Pro.

A larger display could be possible without increasing the size of the iMac significantly. The 27-inch iMac measures 25.6 inches wide, 20.3 inches tall, and 8 inches deep, and while Apple’s new design helps make the 24-inch iMac smaller than the 21.5-inch iMac it replaced, there isn’t that much of a difference in size (21.5 inches x 18.1 inches x 5.8 inches (with the stand) vs 20.8 inches x 17.7 inches x 6.9 inches).',29990000.00,'null','iMac M1 2021 24 inch (8-Core GPU/8GB/256GB)',NULL,2,NULL),
                                                                                                                             ('2022-11-02','Display analyst Ross Young and others have stated that the iMac Pro will have a 27-inch display, which we have to admit to being disappointed by. However, the observations about a 27-inch display may have referred to the Studio Display. There could still be a iMac with a larger display in the pipeline.

A larger display would certainly make the iMac Pro more attractive to potential buyers. Next to the 24in iMac, with its 4.5K display (4,480 x 2,520 pixels), the 27-inch iMac only looks slightly more impressive with its 5K display (5,120 x 2,880 pixels). But it’s not only the 24-inch iMac that the iMac Pro will be compared to – the 27-inch screen also doesn’t compare particularly favourably with many modern displays that are larger than 30-inch (including the 32-inch Apple Pro Display XDR).

Perhaps new screen technology will allow Apple to squeeze in more pixels into a 27-inch display, but we would like to see a 32-inch display on the new iMac Pro.

A larger display could be possible without increasing the size of the iMac significantly. The 27-inch iMac measures 25.6 inches wide, 20.3 inches tall, and 8 inches deep, and while Apple’s new design helps make the 24-inch iMac smaller than the 21.5-inch iMac it replaced, there isn’t that much of a difference in size (21.5 inches x 18.1 inches x 5.8 inches (with the stand) vs 20.8 inches x 17.7 inches x 6.9 inches).',35390000.00,'null','iMac M1 2021 24 inch (7-Core GPU/16GB/256GB)',NULL,2,NULL),
                                                                                                                             ('2022-11-02','The MacBook Air (a review model from Apple) dispenses with the classical (and hugely popular) wedge shape that helped define the range. Apple’s newest machine is slim (0.4 inches high), occupies 20% less volume than before, and sports a design that very much brings it into line with the aesthetics across Apple’s range – rounded corners, thin, rectangular. I see it as similar to (but thinner than) a MacBook Pro. These design decisions matter because the MacBook Air is Apple’s most popular notebook, which de facto means it is also the company’s most popular Mac. (The assumptions is that Apple’s notebooks outsell its desktops by at least two to one.) The MacBook Air is also fanless, which means no matter what you ask it to do, it will be quieter than a whisper in the silence of the night.

The Mac weighs just 2.7 pounds. Dimensions are 0.4-in. by 12-in. by 8.4-in. The last-generation model with an M1 chip weighs 2.8-pounds and is roughly the same size, though 0.6 inches at the thickest point. (The last ever Intel model weighed 2.75 pounds and was about as thick. You’d be forgiven to see some consistency here.)',36490000.00,'null','MacBook Air M2 2022 (8GB RAM|512GB SSD)',NULL,2,NULL),
                                                                                                                             ('2022-11-02','The MacBook Air (a review model from Apple) dispenses with the classical (and hugely popular) wedge shape that helped define the range. Apple’s newest machine is slim (0.4 inches high), occupies 20% less volume than before, and sports a design that very much brings it into line with the aesthetics across Apple’s range – rounded corners, thin, rectangular. I see it as similar to (but thinner than) a MacBook Pro. These design decisions matter because the MacBook Air is Apple’s most popular notebook, which de facto means it is also the company’s most popular Mac. (The assumptions is that Apple’s notebooks outsell its desktops by at least two to one.) The MacBook Air is also fanless, which means no matter what you ask it to do, it will be quieter than a whisper in the silence of the night.

The Mac weighs just 2.7 pounds. Dimensions are 0.4-in. by 12-in. by 8.4-in. The last-generation model with an M1 chip weighs 2.8-pounds and is roughly the same size, though 0.6 inches at the thickest point. (The last ever Intel model weighed 2.75 pounds and was about as thick. You’d be forgiven to see some consistency here.)',34950000.00,'null','MacBook Air M2 2022 (16GB RAM|256GB SSD)',NULL,2,NULL),
                                                                                                                             ('2022-11-02','The MacBook Air (a review model from Apple) dispenses with the classical (and hugely popular) wedge shape that helped define the range. Apple’s newest machine is slim (0.4 inches high), occupies 20% less volume than before, and sports a design that very much brings it into line with the aesthetics across Apple’s range – rounded corners, thin, rectangular. I see it as similar to (but thinner than) a MacBook Pro. These design decisions matter because the MacBook Air is Apple’s most popular notebook, which de facto means it is also the company’s most popular Mac. (The assumptions is that Apple’s notebooks outsell its desktops by at least two to one.) The MacBook Air is also fanless, which means no matter what you ask it to do, it will be quieter than a whisper in the silence of the night.

The Mac weighs just 2.7 pounds. Dimensions are 0.4-in. by 12-in. by 8.4-in. The last-generation model with an M1 chip weighs 2.8-pounds and is roughly the same size, though 0.6 inches at the thickest point. (The last ever Intel model weighed 2.75 pounds and was about as thick. You’d be forgiven to see some consistency here.)',41390000.00,'null','MacBook Air M2 2022 (16GB RAM|512GB SSD)',NULL,2,NULL),
                                                                                                                             ('2022-11-02','MacBook Pro M2 8GB 512GB là bản nâng cấp mới nhất của Apple năm 2022. Mặc dù có thiết kế không đổi so với phiên bản tiền nhiệm nhưng siêu phẩm công nghệ này sẽ không khiến bạn thất vọng với hiệu năng cực khủng nhờ chip Apple M2.MacBook Pro 13 M2 chính là một chiếc laptop đồ họa – kỹ thuật chuyên nghiệp với card tích hợp 10 nhân GPU. Thiết kế này giúp nâng cao hiệu suất hoạt động của máy cao hơn 25% so với chip M1 trong khi cả hai đều sử dụng mức điện năng tương đương.

Nhờ đó, các công việc liên quan đến thiết kế hình ảnh, đồ họa, render,… trên các phần mềm nặng trở nên nhẹ nhàng hơn bao giờ hết. Theo công bố từ Apple, MacBook Pro M2 2022 có khả năng xử lý lên đến 15.8 nghìn tỷ tác vụ chỉ trong 1 giây',35950000.00,'null','iMac M1 2021 24 inch (8-Core GPU/8GB/512GB)','2022-11-09',2,NULL),
                                                                                                                             ('2022-11-02','CUPERTINO, CALIFORNIA Apple today introduced iPhone 14 and iPhone 14 Plus, featuring two sizes — 6.1 inches and 6.7 inches — in a sophisticated design with impressive camera upgrades and groundbreaking new safety capabilities. iPhone 14 and iPhone 14 Plus take stunning photos and video with a powerful camera system featuring new Main and front TrueDepth cameras, the Ultra Wide camera for unique perspectives, and Photonic Engine — an enhanced image pipeline. Both models include the A15 Bionic chip with a 5-core GPU, which offers incredible performance and efficiency for demanding workloads, and is designed with privacy and security built in. iPhone 14 and iPhone 14 Plus introduce critical safety capabilities such as Crash Detection and Emergency SOS via satellite, a first in the industry. And with amazing battery life, industry-leading durability features, and super-fast 5G, this iPhone lineup is more advanced than ever before. iPhone 14 and iPhone 14 Plus will be available in midnight, blue, starlight, purple, and (PRODUCT)RED1 finishes. Pre-orders begin Friday, September 9, with availability for iPhone 14 beginning Friday, September 16, and availability for iPhone 14 Plus beginning Friday, October 7.',30000000.00,'null','Iphone 14 Promax 512 GB','2022-11-09',1,NULL),
                                                                                                                             ('2022-11-09','Liquid Retina display
10.9-inch (diagonal) LED‑backlit Multi‑Touch display with IPS technology
2360-by-1640-pixel resolution at 264 pixels per inch (ppi)
Wide color display (P3)
True Tone display
Fingerprint-resistant oleophobic coating
Fully laminated display
Antireflective coating
1.8% reflectivity
500 nits brightness
Supports Apple Pencil (2nd generation)
Apple introduced the iPad Air 4 in September 2020 as the next evolution of the non-pro tablet line. It has a 10.9-inch edge-to-edge display, Touch ID in the power button, and powerful A14 Bionic processor. It also adds support for the Magic Keyboard, second-generation Apple Pencil, and USB-C.The iPad Air 4 ditches the dated design of the iPad Air 3, moving to a 10.9-inch, nearly edge-to-edge screen design that takes on the same design as the iPad Pro. The case appears to be the same size as the 11-inch iPad Pro, but the bezels are slightly thicker due to display technology differences.',15950000.00,NULL,'iPad Air 4 10.9 inch Wi-Fi + Cellular 64GB',NULL,3,NULL);

INSERT INTO attribute_product (value,attribute_id,product_id) VALUES
                                                                  ('128GB',1,1),
                                                                  ('128GB',1,2),
                                                                  ('6GB',2,2),
                                                                  ('256GB',1,3),
                                                                  ('6GB',2,3),
                                                                  ('512GB',1,4),
                                                                  ('6GB',2,4),
                                                                  ('256GB',1,5),
                                                                  ('6GB',2,5),
                                                                  ('512GB',1,6);
INSERT INTO attribute_product (value,attribute_id,product_id) VALUES
                                                                  ('6GB',2,6),
                                                                  ('128GB',1,7),
                                                                  ('6GB',2,7),
                                                                  ('256GB',1,8),
                                                                  ('6GB',2,8),
                                                                  ('512GB',1,9),
                                                                  ('6GB',2,9),
                                                                  ('128GB',1,10),
                                                                  ('4GB',2,10),
                                                                  ('256GB',1,11);
INSERT INTO attribute_product (value,attribute_id,product_id) VALUES
                                                                  ('4GB',2,11),
                                                                  ('512GB',1,12),
                                                                  ('4GB',2,12),
                                                                  ('128GB',1,13),
                                                                  ('128GB',1,14),
                                                                  ('Chip M1',3,15),
                                                                  ('1 TB',4,15),
                                                                  ('16 GB',5,15),
                                                                  ('512 GB',4,16),
                                                                  ('Chip M1',3,17);
INSERT INTO attribute_product (value,attribute_id,product_id) VALUES
                                                                  ('512 GB',4,17),
                                                                  ('8 GB',5,17),
                                                                  ('Chip M1',3,18),
                                                                  ('256 GB',4,18),
                                                                  ('16 GB',2,18),
                                                                  ('Chip M1',3,19),
                                                                  ('256 GB',4,19),
                                                                  ('8G',2,19),
                                                                  ('7-Core',6,19),
                                                                  ('Chip M1',3,20);
INSERT INTO attribute_product (value,attribute_id,product_id) VALUES
                                                                  ('256 GB',4,20),
                                                                  ('8G',2,20),
                                                                  ('8-Core',6,20),
                                                                  ('Chip M1',3,21),
                                                                  ('256 GB',4,21),
                                                                  ('16GB',2,21),
                                                                  ('7-Core',6,21),
                                                                  ('Chip M1',3,22),
                                                                  ('512 GB',4,22),
                                                                  ('16GB',2,22);
INSERT INTO attribute_product (value,attribute_id,product_id) VALUES
                                                                  ('Chip M1',3,23),
                                                                  ('256 GB',4,23),
                                                                  ('16GB',2,23),
                                                                  ('Chip M2',3,24),
                                                                  ('256 GB',4,24),
                                                                  ('8GB',2,24),
                                                                  ('Chip M2',3,25),
                                                                  ('512 GB',4,25),
                                                                  ('8GB',2,25),
                                                                  ('Chip M2',3,26);
INSERT INTO attribute_product (value,attribute_id,product_id) VALUES
                                                                  ('256 GB',4,26),
                                                                  ('16GB',2,26),
                                                                  ('128GB',7,28),
                                                                  ('M2',8,28),
                                                                  ('8GB',2,28),
                                                                  ('Chip M2',3,16),
                                                                  ('8GB',2,16),
                                                                  ('M1',8,39),
                                                                  ('128GB',7,39),
                                                                  ('8GB',2,39);
INSERT INTO attribute_product (value,attribute_id,product_id) VALUES
                                                                  ('M1',8,40),
                                                                  ('8GB',2,40),
                                                                  ('64GB',7,40);

INSERT INTO cart_item (color,price,quantity,total_price,product_id,cart_id,order_item_id,item_id) VALUES
                                                                                                      ('Blue',39890000.00,1,39890000.00,17,16,NULL,NULL),
                                                                                                      ('Green',26950000.00,1,26950000.00,18,16,NULL,NULL),
                                                                                                      ('Blue',35390000.00,1,35390000.00,20,16,NULL,NULL),
                                                                                                      ('Deep Purple',40950000.00,1,40950000.00,5,16,NULL,NULL),
                                                                                                      ('Space Gray',35190000.00,1,35190000.00,15,16,NULL,NULL),
                                                                                                      ('Deep Purple',36450000.00,1,36450000.00,4,15,NULL,NULL);

INSERT INTO image (color,url,produc_id) VALUES
                                            ('Gold','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iphone-14-promax-vang-650x650-1.png',2),
                                            ('Silver','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iphone-14-promax-silver-650x650-1.png',2),
                                            ('Black','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iphone-14-promax-den-650x650-1.png',2),
                                            ('Deep Purple','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iphone-14-pro-max-tim-650x650-1.png',2),
                                            ('Gold','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iphone-14-promax-vang-650x650-1.png',3),
                                            ('Silver','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iphone-14-promax-silver-650x650-1.png',3),
                                            ('Black','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iphone-14-promax-den-650x650-1.png',3),
                                            ('Deep Purple','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iphone-14-pro-max-tim-650x650-1.png',3),
                                            ('Gold','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iphone-14-promax-vang-650x650-1.png',4),
                                            ('Silver','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iphone-14-promax-silver-650x650-1.png',4);
INSERT INTO image (color,url,produc_id) VALUES
                                            ('Black','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iphone-14-promax-den-650x650-1.png',4),
                                            ('Deep Purple','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iphone-14-pro-max-tim-650x650-1.png',4),
                                            ('Gold','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iphone-14-promax-vang-650x650-1.png',5),
                                            ('Silver','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iphone-14-promax-silver-650x650-1.png',5),
                                            ('Black','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iphone-14-promax-den-650x650-1.png',5),
                                            ('Deep Purple','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iphone-14-pro-max-tim-650x650-1.png',5),
                                            ('Gold','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iphone-14-promax-vang-650x650-1.png',6),
                                            ('Silver','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iphone-14-promax-silver-650x650-1.png',6),
                                            ('Black','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iphone-14-promax-den-650x650-1.png',6),
                                            ('Deep Purple','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iphone-14-pro-max-tim-650x650-1.png',6);
INSERT INTO image (color,url,produc_id) VALUES
                                            ('Gold','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iphone-13-pro-max-gold-650x650-2.png',7),
                                            ('Green','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iphone-13-pro-max-thumbtz-650x650-1.png',7),
                                            ('Blue','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iphone-13-pro-max-blue-650x650-1.png',7),
                                            ('Graphite','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iphone-13-pro-max-grey-650x650-1.png',7),
                                            ('Silver','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iphone-13-pro-max-silver-650x650-1.png',7),
                                            ('Gold','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iphone-13-pro-max-gold-650x650-2.png',8),
                                            ('Green','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iphone-13-pro-max-thumbtz-650x650-1.png',8),
                                            ('Blue','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iphone-13-pro-max-blue-650x650-1.png',8),
                                            ('Graphite','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iphone-13-pro-max-grey-650x650-1.png',8),
                                            ('Silver','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iphone-13-pro-max-silver-650x650-1.png',8);
INSERT INTO image (color,url,produc_id) VALUES
                                            ('Gold','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iphone-13-pro-max-gold-650x650-2.png',9),
                                            ('Green','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iphone-13-pro-max-thumbtz-650x650-1.png',9),
                                            ('Blue','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iphone-13-pro-max-blue-650x650-1.png',9),
                                            ('Graphite','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iphone-13-pro-max-grey-650x650-1.png',9),
                                            ('Silver','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iphone-13-pro-max-silver-650x650-1.png',9),
                                            ('Pink','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iPhone_13_PDP_Position-1A_Color_Pink__VN-1536x1536.webp',10),
                                            ('Red','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iPhone_13_red-1536x1536.webp',10),
                                            ('Blue','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iPhone_13_Blue__VN-1536x1536.webp',10),
                                            ('Startlight','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iPhone_13Color_Starlight__VN-1536x1536.webp',10),
                                            ('Midnight','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iPhone_13Midnight__VN-1536x1536.webp',10);
INSERT INTO image (color,url,produc_id) VALUES
                                            ('Green','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iPhone_13_Green_Color___SEA.jpg',10),
                                            ('Pink','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iPhone_13_PDP_Position-1A_Color_Pink__VN-1536x1536.webp',11),
                                            ('Red','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iPhone_13_red-1536x1536.webp',11),
                                            ('Blue','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iPhone_13_Blue__VN-1536x1536.webp',11),
                                            ('Startlight','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iPhone_13Color_Starlight__VN-1536x1536.webp',11),
                                            ('Midnight','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iPhone_13Midnight__VN-1536x1536.webp',11),
                                            ('Green','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iPhone_13_Green_Color___SEA.jpg',11),
                                            ('Pink','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iPhone_13_PDP_Position-1A_Color_Pink__VN-1536x1536.webp',12),
                                            ('Red','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iPhone_13_red-1536x1536.webp',12),
                                            ('Blue','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iPhone_13_Blue__VN-1536x1536.webp',12);
INSERT INTO image (color,url,produc_id) VALUES
                                            ('Startlight','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iPhone_13Color_Starlight__VN-1536x1536.webp',12),
                                            ('Midnight','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iPhone_13Midnight__VN-1536x1536.webp',12),
                                            ('Green','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iPhone_13_Green_Color___SEA.jpg',12),
                                            ('Red','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iphone-12-red-1-650x650-1.png',13),
                                            ('Purple','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iphone-12-purple-1-650x650-1.png',13),
                                            ('Green','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iphone-12-green-1-1-650x650-1.png',13),
                                            ('Blue','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iphone-12-blue-1-650x650-1.png',13),
                                            ('Black','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iphone-12-black-1-650x650-1.png',13),
                                            ('White','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iphone-12-white-1-650x650-1.png',13),
                                            ('Red','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iphone-12-red-1-650x650-1.png',14);
INSERT INTO image (color,url,produc_id) VALUES
                                            ('Purple','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iphone-12-purple-1-650x650-1.png',14),
                                            ('Green','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iphone-12-green-1-1-650x650-1.png',14),
                                            ('Blue','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iphone-12-blue-1-650x650-1.png',14),
                                            ('Black','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iphone-12-black-1-650x650-1.png',14),
                                            ('White','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iphone-12-white-1-650x650-1.png',14),
                                            ('Space Gray','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/MacBook_Pro_14-m1-gray.jpg',15),
                                            ('Silver','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/MacBook+Pro+14+M1+Pro+Silver.jpg',15),
                                            ('Pink','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iMac_m1_2021_24-in_Pink.webp',17),
                                            ('Green','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iMac_m1_2021_24-in_Green_.webp',17),
                                            ('Blue','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iMac_m1_2021_24-in_Blue_4.webp',17);
INSERT INTO image (color,url,produc_id) VALUES
                                            ('Silver','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iMac_m1_2021_24-in_Silver_4-1-1536x1536.webp',17),
                                            ('Pink','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iMac_m1_2021_24-in_Pink.webp',18),
                                            ('Green','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iMac_m1_2021_24-in_Green_.webp',18),
                                            ('Blue','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iMac_m1_2021_24-in_Blue_4.webp',18),
                                            ('Silver','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iMac_m1_2021_24-in_Silver_4-1-1536x1536.webp',18),
                                            ('Pink','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iMac_m1_2021_24-in_Pink.webp',19),
                                            ('Green','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iMac_m1_2021_24-in_Green_.webp',19),
                                            ('Blue','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iMac_m1_2021_24-in_Blue_4.webp',19),
                                            ('Silver','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iMac_m1_2021_24-in_Silver_4-1-1536x1536.webp',19),
                                            ('Pink','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iMac_m1_2021_24-in_Pink.webp',20);
INSERT INTO image (color,url,produc_id) VALUES
                                            ('Green','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iMac_m1_2021_24-in_Green_.webp',20),
                                            ('Blue','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iMac_m1_2021_24-in_Blue_4.webp',20),
                                            ('Silver','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iMac_m1_2021_24-in_Silver_4-1-1536x1536.webp',20),
                                            ('Pink','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iMac_m1_2021_24-in_Pink.webp',21),
                                            ('Green','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iMac_m1_2021_24-in_Green_.webp',21),
                                            ('Blue','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iMac_m1_2021_24-in_Blue_4.webp',21),
                                            ('Silver','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iMac_m1_2021_24-in_Silver_4-1-1536x1536.webp',21),
                                            ('Silver','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/MacBook+Pro-13-M2-2022-silver.webp',22),
                                            ('Space Gray','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/MacBook-Pro-13-M2-2022-gray.webp',22),
                                            ('Silver','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/MacBook+Pro-13-M2-2022-silver.webp',23);
INSERT INTO image (color,url,produc_id) VALUES
                                            ('Space Gray','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/MacBook-Pro-13-M2-2022-gray.webp',23),
                                            ('Space Gray','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/MacBook-Air-M2-2022-gray.png',24),
                                            ('Startlight','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/MacBook-Air-M2-2022-start.png',24),
                                            ('Silver','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/MacBook-Air-M2-2022-silver.png',24),
                                            ('Midnight','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/MacBook-Air-M2-2022-midnight.png',24),
                                            ('Space Gray','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/MacBook-Air-M2-2022-gray.png',25),
                                            ('Startlight','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/MacBook-Air-M2-2022-start.png',25),
                                            ('Silver','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/MacBook-Air-M2-2022-silver.png',25),
                                            ('Midnight','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/MacBook-Air-M2-2022-midnight.png',25),
                                            ('Space Gray','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/MacBook-Air-M2-2022-gray.png',26);
INSERT INTO image (color,url,produc_id) VALUES
                                            ('Startlight','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/MacBook-Air-M2-2022-start.png',26),
                                            ('Silver','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/MacBook-Air-M2-2022-silver.png',26),
                                            ('Midnight','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/MacBook-Air-M2-2022-midnight.png',26),
                                            ('Silver','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iPad_Pro_11-in_Wi-Fi_Silver_2.png',28),
                                            ('Space Gray','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iPad_Pro_11-in_Wi-Fi_Space_Gray.webp',28),
                                            ('Silver','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/1668003285656-Ipad-pro-Silver.jpg',39),
                                            ('Space Gray','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/1668003272191-Ipad-pro-space-gray.jpg',39),
                                            ('Silver','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/MacBook+Pro-13-M2-2022-silver.webp',16),
                                            ('Space Gray','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/MacBook-Pro-13-M2-2022-gray.webp',16),
                                            ('Silver','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iphone-14-promax-silver-650x650-1.png',1);
INSERT INTO image (color,url,produc_id) VALUES
                                            ('Deep Purple','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iphone-14-pro-max-tim-650x650-1.png',1),
                                            ('Gold','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iphone-14-promax-vang-650x650-1.png',1),
                                            ('Black','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/iphone-14-promax-den-650x650-1.png',1),
                                            ('Sky Blue','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/1668006151205-iPad-air-gen4-skyblue.png',40),
                                            ('Green','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/1668006155035-iPad-air-gen4-green.png',40),
                                            ('Silver','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/1668006170222-iPad-air-gen4-silver.png',40),
                                            ('Space Gray','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/1668006178169-iPad_Air_5_Space_Gray.jpg',40),
                                            ('Pink','https://ecommerce-lvquang0403.s3.us-west-1.amazonaws.com/1668006136300-iPad-air-gen4-gold-pink.png',40);

INSERT INTO orders (number_type_item,order_name,order_phone,order_state,ship_address,total_price,customer_id,create_day) VALUES
                                                                                                                             (NULL,NULL,'0968520710',0,'Binh Duong',132320000.00,1,NULL),
                                                                                                                             (NULL,NULL,'0968520710',0,'BINH DUONG',200000.00,1,'2022-11-09'),
                                                                                                                             (NULL,NULL,'0968520710',0,'1004, quốc lộ 1a, phường linh trung, quận thủ đức',680900000.00,13,'2022-11-09'),
                                                                                                                             (NULL,NULL,'0968520710',0,'1004, quốc lộ 1a, phường linh trung, quận thủ đức',33850000.00,13,'2022-11-09'),
                                                                                                                             (NULL,NULL,'0968520710',0,'BINH DUONG',200000.00,1,NULL),
                                                                                                                             (NULL,NULL,'0968520710',0,'BINH DUONG',200000.00,1,NULL),
                                                                                                                             (NULL,NULL,'0968520710',1,'TPHCM',172300000.00,11,'2022-11-09');

INSERT INTO order_item (order_item_id,color,price,quantity,total_price,product_id,order_id) VALUES
                                                                                                (33,'Gold',1000.00,1,1000.00,1,23),
                                                                                                (34,'Gold',28950000.00,1,28950000.00,2,24),
                                                                                                (35,'Silver',27990000.00,2,55980000.00,28,25),
                                                                                                (36,'Blue',39890000.00,1,39890000.00,17,25),
                                                                                                (37,'Gold',36450000.00,1,36450000.00,4,25),
                                                                                                (38,'Gold',28950000.00,1,28950000.00,2,26),
                                                                                                (39,'Blue',26950000.00,6,161700000.00,18,27),
                                                                                                (40,'Gold',35950000.00,1,35950000.00,3,27),
                                                                                                (41,'Green',39890000.00,5,199450000.00,17,27),
                                                                                                (42,'Deep Purple',35950000.00,1,35950000.00,3,27);
INSERT INTO order_item (order_item_id,color,price,quantity,total_price,product_id,order_id) VALUES
                                                                                                (43,'Silver',35190000.00,5,175950000.00,15,27),
                                                                                                (44,'Silver',35950000.00,1,35950000.00,3,27),
                                                                                                (45,'Black',35950000.00,1,35950000.00,3,27),
                                                                                                (46,'Graphite',33850000.00,1,33850000.00,8,28),
                                                                                                (47,'Gold',29350000.00,4,117400000.00,7,29),
                                                                                                (48,'Silver',27450000.00,2,54900000.00,6,29);

INSERT INTO rating ("comment",create_day,score,customer_id,product_id) VALUES
                                                                           ('asdd','2022-11-05',3,1,3),
                                                                           ('sad','2022-11-05',3,1,5),
                                                                           ('èwef','2022-11-06',3,1,14),
                                                                           ('good','2022-11-06',0,1,2),
                                                                           ('good','2022-11-06',5,1,1),
                                                                           ('godd','2022-11-07',4,1,28),
                                                                           ('hbhb','2022-11-08',4,1,22),
                                                                           ('very good','2022-11-09',4,11,7),
                                                                           ('hey','2022-11-09',4,13,5),
                                                                           ('Blue is good','2022-11-09',5,13,7);
INSERT INTO rating ("comment",create_day,score,customer_id,product_id) VALUES
                                                                           ('GOOD','2022-11-09',5,11,16),
                                                                           ('good','2022-11-09',4,11,15),
                                                                           ('Good','2022-11-09',4,11,20),
                                                                           ('Good','2022-11-09',5,11,10);



