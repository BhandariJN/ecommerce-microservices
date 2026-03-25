CREATE TABLE IF NOT EXISTS category
(
    id          INT PRIMARY KEY,
    description VARCHAR(255),
    name        VARCHAR(255)
    );

CREATE TABLE IF NOT EXISTS product
(
    id                 INT PRIMARY KEY,
    name               VARCHAR(255),
    description        VARCHAR(255),   -- ← add this
    available_quantity INT,
    price              NUMERIC(38, 2),
    category_id        INT,
    CONSTRAINT fk_category_id FOREIGN KEY (category_id) REFERENCES category (id)
    );

CREATE SEQUENCE IF NOT EXISTS category_seq INCREMENT BY 50;
CREATE SEQUENCE IF NOT EXISTS product_seq INCREMENT BY 50;