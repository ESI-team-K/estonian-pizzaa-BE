TRUNCATE customers,drivers,users RESTART IDENTITY;
INSERT INTO customers(username, email, password, phone_number) VALUES('Monika','asdf@jt.ee', '1234', '+37258902211');
