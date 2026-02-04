INSERT INTO accounts(account_id, email, password_hash, role, created_at, updated_at)
VALUES 
	('c0fbe5a8-3d2f-44dc-a55d-6fb4cdc8cde1', 'admin@mail.com', '$2a$12$rncu5936xOngYlGi7oZPhe4eHIjnU9GqE4f87w/9cIdG3o.hTCiDq', 'ADMIN', '2026-02-04 10:09:20.341153+05:30', '2026-02-04 10:09:20.341153+05:30')
ON CONFLICT (email) DO NOTHING;;

INSERT INTO "users"(account_id, first_name, last_name, date_of_birth, phone, country_code, created_at, updated_at)
VALUES 
	('c0fbe5a8-3d2f-44dc-a55d-6fb4cdc8cde1', 'John', 'Doe', '1991-01-08 05:20:17', '1234567890', '91', '2026-02-04 10:09:20.341153+05:30', '2026-02-04 10:09:20.341153+05:30')
ON CONFLICT (account_id) DO NOTHING;;