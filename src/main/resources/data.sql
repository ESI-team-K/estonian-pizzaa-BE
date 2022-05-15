INSERT INTO roles(name) VALUES('ROLE_CUSTOMER');
INSERT INTO roles(name) VALUES('ROLE_STAFF');
INSERT INTO roles(name) VALUES('ROLE_DRIVER');

-- INSERT INTO public.alluser (user_id, name, password, roles)
-- SELECT * FROM (SELECT 1, 'staff', 'pass', 'STAFF') AS tmp
-- WHERE NOT EXISTS (
--     SELECT name FROM public.alluser  WHERE user_id = '1'
-- ) LIMIT 1;

-- INSERT INTO public.alluser (user_id, name, password, roles)
-- SELECT * FROM (SELECT 2, 'driver', 'pass', 'DRIVER') AS tmp
-- WHERE NOT EXISTS (
--     SELECT name FROM public.alluser  WHERE user_id = '2'
-- ) LIMIT 1;

-- INSERT INTO public.alluser (user_id, name, password, roles)
-- SELECT * FROM (SELECT 3, 'customer', 'pass', 'CUSTOMER') AS tmp
-- WHERE NOT EXISTS (
--     SELECT name FROM public.alluser  WHERE user_id = '3'
-- ) LIMIT 1;

INSERT INTO customers(username, email, password, phone_number)
VALUES ('Monika', 'asdf@jt.ee', '1234', '+37258902211') ON CONFLICT DO NOTHING;
INSERT INTO menuitems(id, name, price, size, ingredients)
VALUES (1, 'Pizza Margarita', '5', 12, 'tomato, cheese') ON CONFLICT DO NOTHING;
INSERT INTO menuitems(id, name, price, size, ingredients)
VALUES (2, 'Pizza Napoli', '5', 12, 'tomato, cheese, ham') ON CONFLICT DO NOTHING;
INSERT INTO menuitems(id, name, price, size, ingredients)
VALUES (3, 'Pizza Capricciosa', '5', 12, 'tomato, cheese, ham, mushrooms') ON CONFLICT DO NOTHING;
INSERT INTO menuitems(id, name, price, size, ingredients)
VALUES (4, 'Pizza Funghi', '5', 12, 'tomato, cheese, mushrooms') ON CONFLICT DO NOTHING;
INSERT INTO menuitems(id, name, price, size, ingredients)
VALUES (5, 'Pizza Prosciutto', '5', 12, 'tomato, cheese, ham, mushrooms, salami') ON CONFLICT DO NOTHING;
INSERT INTO menuitems(id, name, price, size, ingredients)
VALUES (6, 'Pizza Quattro Formaggi', '5', 12, 'tomato, cheese, mozzarella, parmezan, ricotta') ON CONFLICT DO NOTHING;
INSERT INTO menuitems(id, name, price, size, ingredients)
VALUES (7, 'Pizza Quattro Stagioni', '5', 12,
        'tomato, cheese, mozzarella, parmezan, ricotta, ham') ON CONFLICT DO NOTHING;
INSERT INTO menuitems(id, name, price, size, ingredients)
VALUES (8, 'Pizza Vegetariana', '5', 12, 'tomato, cheese, mushrooms, peppers, olives') ON CONFLICT DO NOTHING;

-- INSERT INTO customers(username, email, password, phone_number) VALUES('jtm','abc@jt.ee', '1234', '+37211223344');
-- INSERT INTO customers(username, email, password, phone_number) VALUES('sano','def@jt.ee', '1234', '+3798341256');
