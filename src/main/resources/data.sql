INSERT INTO public.roles (id, name)
SELECT * FROM (SELECT 1, 'ROLE_CUSTOMER') AS tp
WHERE NOT EXISTS (
    SELECT name FROM public.roles  WHERE id = '1'
) LIMIT 1 ON CONFLICT DO NOTHING;

INSERT INTO public.roles (id, name)
SELECT * FROM (SELECT 2, 'ROLE_STAFF') AS tp
WHERE NOT EXISTS (
    SELECT name FROM public.roles  WHERE id = '2'
) LIMIT 1 ON CONFLICT DO NOTHING;


INSERT INTO public.roles (id, name)
SELECT * FROM (SELECT 3,'ROLE_DRIVER') AS tp
WHERE NOT EXISTS (
    SELECT name FROM public.roles  WHERE id = '3'
) LIMIT 1 ON CONFLICT DO NOTHING;

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
