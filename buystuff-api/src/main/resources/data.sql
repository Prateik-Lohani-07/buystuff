-- USERS
INSERT INTO "users"(user_id, first_name, last_name, date_of_birth, phone, country_code, created_at, updated_at)
VALUES 
	(1, 'John', 'Doe', '1991-01-08 05:20:17', '1234567890', '91', '2026-02-04 10:09:20.341153+05:30', '2026-02-04 10:09:20.341153+05:30'),
	(2, 'Cart', 'User', '1991-01-08 05:20:17', '1234567890', '91', '2026-02-04 10:09:20.341153+05:30', '2026-02-04 10:09:20.341153+05:30'),
	(3, 'Fulfillment', 'Manager guy', '1991-01-08 05:20:17', '1234567890', '91', '2026-02-04 10:09:20.341153+05:30', '2026-02-04 10:09:20.341153+05:30')
ON CONFLICT (user_id) DO NOTHING;;

-- CART
INSERT INTO carts(cart_id, created_at, updated_at)
VALUES 
	(1, '2026-02-04 10:09:20.341153+05:30', '2026-02-04 10:09:20.341153+05:30')
ON CONFLICT (cart_id) DO NOTHING;;

-- ACCOUNTS
INSERT INTO accounts(account_id, user_id, cart_id, email, password_hash, role, created_at, updated_at)
VALUES 
	('c0fbe5a8-3d2f-44dc-a55d-6fb4cdc8cde1', 1, null, 'admin@mail.com', '$2a$12$rncu5936xOngYlGi7oZPhe4eHIjnU9GqE4f87w/9cIdG3o.hTCiDq', 'ADMIN', '2026-02-04 10:09:20.341153+05:30', '2026-02-04 10:09:20.341153+05:30'),
	('07debfdb-405c-4301-85e7-123b3544107b', 2, 1, 'cartuser@mail.com', '$2a$12$rncu5936xOngYlGi7oZPhe4eHIjnU9GqE4f87w/9cIdG3o.hTCiDq', 'CUSTOMER', '2026-02-04 10:09:20.341153+05:30', '2026-02-04 10:09:20.341153+05:30'),
	('d369aca6-46ba-4846-adc5-eb8424c23867', 3, null, 'fulfillment_operations@mail.com', '$2a$12$rncu5936xOngYlGi7oZPhe4eHIjnU9GqE4f87w/9cIdG3o.hTCiDq', 'FULFILLMENT_MANAGER', '2026-02-04 10:09:20.341153+05:30', '2026-02-04 10:09:20.341153+05:30')
ON CONFLICT (email) DO NOTHING;;

-- PRODUCTS
INSERT INTO products(avg_rating, discount, is_active, price, stock, created_at, updated_at, product_id, "description", "name", product_code)
VALUES
	(0, 0, 't', 20,  5000, '2026-02-12 17:20:37.949343+05:30', '2026-02-12 17:20:37.949343+05:30', '71f62cd1-816b-4dce-8617-7af327b9c77a', 'Yeh dil maange more'      , 'Pepsi'   , 'PEPSICO1'),
	(0, 0, 't', 50, 50000, '2026-02-12 17:20:44.806482+05:30', '2026-02-12 17:20:44.806482+05:30', 'a39a2e77-82cb-4b03-ae1c-7ac3e0b67402', 'Light plastic bottle'     , 'Bottle'  , 'C345AAK'),
	(0, 0, 't',  5,   450, '2026-02-14 08:08:01.725315+05:30', '2026-02-14 08:08:01.725315+05:30', '44eea1cd-8c55-4c75-a6b3-5256ad7f03ab', 'Nice sleek ball point pen', 'Ball Pen', 'PEN1')
ON CONFLICT(product_id) DO NOTHING;;

-- CART ITEMS -> for cartuser@mail.com
INSERT INTO cart_items(price, quantity, cart_id, item_id, product_id)
VALUES
    (20, 2, 1, 1, '71f62cd1-816b-4dce-8617-7af327b9c77a'),
    (50, 5, 1, 2, 'a39a2e77-82cb-4b03-ae1c-7ac3e0b67402')
ON CONFLICT(item_id) DO NOTHING;;

-- INSERT INTO addresses()

-- off-setting incremental ids 
SELECT setval('users_user_id_seq', (SELECT MAX(user_id) FROM "users"));;
SELECT setval('carts_cart_id_seq', (SELECT MAX(cart_id) FROM "carts"));;
SELECT setval('cart_items_item_id_seq', (SELECT MAX(item_id) FROM "cart_items"));;