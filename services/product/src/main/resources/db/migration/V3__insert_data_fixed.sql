-- Store category IDs for later use in product inserts
WITH category_ids AS (
    SELECT 
        NEXTVAL('category_seq') as id,
        unnest(ARRAY['Cat description 1', 'Cat description 2', 'Cat description 3', 'Cat description 4', 'Cat description 5']) as description,
        unnest(ARRAY['Cat 1', 'Cat 2', 'Cat 3', 'Cat 4', 'Cat 5']) as name
),
inserted_categories AS (
    INSERT INTO category (id, description, name)
    SELECT id, description, name FROM category_ids
    RETURNING id
)
-- Insert products using the actual category IDs that were created
INSERT INTO product (id, name, description, available_quantity, price, category_id)
SELECT 
    NEXTVAL('product_seq'),
    unnest(ARRAY['Prod 1', 'Prod 2', 'Prod 3', 'Prod 4', 'Prod 5', 'Prod 6', 'Prod 7', 'Prod 8', 'Prod 9', 'Prod 10']),
    unnest(ARRAY['Prod Desc 1', 'Prod Desc 2', 'Prod Desc 3', 'Prod Desc 4', 'Prod Desc 5', 'Prod Desc 6', 'Prod Desc 7', 'Prod Desc 8', 'Prod Desc 9', 'Prod Desc 10']),
    unnest(ARRAY[10, 20, 30, 40, 50, 60, 70, 80, 90, 100]),
    unnest(ARRAY[10.99, 19.99, 29.99, 39.99, 49.99, 59.99, 69.99, 79.99, 89.99, 99.99]),
    (SELECT array_agg(id ORDER BY id) FROM inserted_categories)[(row_number() OVER () - 1) % 5 + 1];
