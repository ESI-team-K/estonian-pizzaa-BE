INSERT INTO public.alluser (user_id, name, password, roles)
SELECT * FROM (SELECT 1, 'staff', 'staff', 'STAFF') AS tmp
WHERE NOT EXISTS (
    SELECT name FROM pizzaa.alluser  WHERE user_id = '1'
) LIMIT 1;

INSERT INTO public.alluser (user_id, name, password, roles)
SELECT * FROM (SELECT 2, 'driver', 'driver', 'DRIVER') AS tmp
WHERE NOT EXISTS (
    SELECT name FROM pizzaa.alluser  WHERE user_id = '2'
) LIMIT 1;

INSERT INTO pulic.alluser (user_id, name, password, roles)
SELECT * FROM (SELECT 3, 'customer', 'customer', 'CUSTOMER') AS tmp
WHERE NOT EXISTS (
    SELECT name FROM pizzaa.alluser  WHERE user_id = '3'
) LIMIT 1;

