INSERT INTO "users"(user_id, first_name, last_name, date_of_birth, phone, country_code, created_at, updated_at)
VALUES 
	(1, 'John', 'Doe', '1991-01-08 05:20:17', '1234567890', '91', '2026-02-04 10:09:20.341153+05:30', '2026-02-04 10:09:20.341153+05:30'),
	(2, 'Cart', 'User', '1991-01-08 05:20:17', '1234567890', '91', '2026-02-04 10:09:20.341153+05:30', '2026-02-04 10:09:20.341153+05:30')
ON CONFLICT (user_id) DO NOTHING;;

INSERT INTO carts(cart_id, created_at, updated_at)
VALUES 
	(1, '2026-02-04 10:09:20.341153+05:30', '2026-02-04 10:09:20.341153+05:30')
ON CONFLICT (cart_id) DO NOTHING;;

INSERT INTO accounts(account_id, user_id, cart_id, email, password_hash, role, created_at, updated_at)
VALUES 
	('c0fbe5a8-3d2f-44dc-a55d-6fb4cdc8cde1', 1, null, 'admin@mail.com', '$2a$12$rncu5936xOngYlGi7oZPhe4eHIjnU9GqE4f87w/9cIdG3o.hTCiDq', 'ADMIN', '2026-02-04 10:09:20.341153+05:30', '2026-02-04 10:09:20.341153+05:30'),
	('07debfdb-405c-4301-85e7-123b3544107b', 2, 1, 'cartuser@mail.com', '$2a$12$rncu5936xOngYlGi7oZPhe4eHIjnU9GqE4f87w/9cIdG3o.hTCiDq', 'CUSTOMER', '2026-02-04 10:09:20.341153+05:30', '2026-02-04 10:09:20.341153+05:30')
ON CONFLICT (email) DO NOTHING;;

SELECT setval('users_user_id_seq', (SELECT MAX(user_id) FROM "users"));
SELECT setval('carts_cart_id_seq', (SELECT MAX(cart_id) FROM "carts"));