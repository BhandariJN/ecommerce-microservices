-- Insert categories with predictable IDs following sequence increment pattern
INSERT INTO category (id, description, name) VALUES (50, 'Cat description 1', 'Cat 1');
INSERT INTO category (id, description, name) VALUES (100, 'Cat description 2', 'Cat 2');
INSERT INTO category (id, description, name) VALUES (150, 'Cat description 3', 'Cat 3');
INSERT INTO category (id, description, name) VALUES (200, 'Cat description 4', 'Cat 4');
INSERT INTO category (id, description, name) VALUES (250, 'Cat description 5', 'Cat 5');

-- Insert products referencing the actual category IDs that exist
INSERT INTO product (id, name, description, available_quantity, price, category_id) VALUES (50, 'Prod 1', 'Prod Desc 1', 10, 10.99, 50);
INSERT INTO product (id, name, description, available_quantity, price, category_id) VALUES (100, 'Prod 2', 'Prod Desc 2', 20, 19.99, 100);
INSERT INTO product (id, name, description, available_quantity, price, category_id) VALUES (150, 'Prod 3', 'Prod Desc 3', 30, 29.99, 150);
INSERT INTO product (id, name, description, available_quantity, price, category_id) VALUES (200, 'Prod 4', 'Prod Desc 4', 40, 39.99, 200);
INSERT INTO product (id, name, description, available_quantity, price, category_id) VALUES (250, 'Prod 5', 'Prod Desc 5', 50, 49.99, 250);

-- Insert more products referencing existing categories
INSERT INTO product (id, name, description, available_quantity, price, category_id) VALUES (300, 'Prod 6', 'Prod Desc 6', 60, 59.99, 50);
INSERT INTO product (id, name, description, available_quantity, price, category_id) VALUES (350, 'Prod 7', 'Prod Desc 7', 70, 69.99, 100);
INSERT INTO product (id, name, description, available_quantity, price, category_id) VALUES (400, 'Prod 8', 'Prod Desc 8', 80, 79.99, 150);
INSERT INTO product (id, name, description, available_quantity, price, category_id) VALUES (450, 'Prod 9', 'Prod Desc 9', 90, 89.99, 200);
INSERT INTO product (id, name, description, available_quantity, price, category_id) VALUES (500, 'Prod 10', 'Prod Desc 10', 100, 99.99, 250);
