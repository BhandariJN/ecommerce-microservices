INSERT INTO category (id, description, name) VALUES (NEXTVAL('category_seq'), 'Cat description 1', 'Cat 1');
INSERT INTO category (id, description, name) VALUES (NEXTVAL('category_seq'), 'Cat description 2', 'Cat 2');
INSERT INTO category (id, description, name) VALUES (NEXTVAL('category_seq'), 'Cat description 3', 'Cat 3');
INSERT INTO category (id, description, name) VALUES (NEXTVAL('category_seq'), 'Cat description 4', 'Cat 4');
INSERT INTO category (id, description, name) VALUES (NEXTVAL('category_seq'), 'Cat description 5', 'Cat 5');

INSERT INTO product (id, name, description, available_quantity, price, category_id) VALUES (NEXTVAL('product_seq'), 'Prod 1', 'Prod Desc 1', 10, 10.99, 1);
INSERT INTO product (id, name, description, available_quantity, price, category_id) VALUES (NEXTVAL('product_seq'), 'Prod 2', 'Prod Desc 2', 20, 19.99, 2);
INSERT INTO product (id, name, description, available_quantity, price, category_id) VALUES (NEXTVAL('product_seq'), 'Prod 3', 'Prod Desc 3', 30, 29.99, 3);
INSERT INTO product (id, name, description, available_quantity, price, category_id) VALUES (NEXTVAL('product_seq'), 'Prod 4', 'Prod Desc 4', 40, 39.99, 4);
INSERT INTO product (id, name, description, available_quantity, price, category_id) VALUES (NEXTVAL('product_seq'), 'Prod 5', 'Prod Desc 5', 50, 49.99, 5);


INSERT INTO product (id, name, description, available_quantity, price, category_id) VALUES (NEXTVAL('product_seq'), 'Prod 6', 'Prod Desc 6', 60, 59.99, 1);
INSERT INTO product (id, name, description, available_quantity, price, category_id) VALUES (NEXTVAL('product_seq'), 'Prod 7', 'Prod Desc 7', 70, 69.99, 2);
INSERT INTO product (id, name, description, available_quantity, price, category_id) VALUES (NEXTVAL('product_seq'), 'Prod 8', 'Prod Desc 8', 80, 79.99, 3);
INSERT INTO product (id, name, description, available_quantity, price, category_id) VALUES (NEXTVAL('product_seq'), 'Prod 9', 'Prod Desc 9', 90, 89.99, 4);
INSERT INTO product (id, name, description, available_quantity, price, category_id) VALUES (NEXTVAL('product_seq'), 'Prod 10', 'Prod Desc 10', 100, 99.99, 5);