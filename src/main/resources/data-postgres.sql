TRUNCATE customers,drivers,users,roles RESTART IDENTITY CASCADE;
INSERT INTO customers(username, email, password, phone_number) VALUES('Monika','asdf@jt.ee', '1234', '+37258902211');
-- INSERT INTO customers(username, email, password, phone_number) VALUES('jtm','abc@jt.ee', '1234', '+37211223344');
-- INSERT INTO customers(username, email, password, phone_number) VALUES('sano','def@jt.ee', '1234', '+3798341256');
