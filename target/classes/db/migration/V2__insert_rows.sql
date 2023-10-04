INSERT INTO public."_users"
 (id, email, extra_info, "password", "role")
 VALUES(nextval('_users_id_seq'::regclass), 'lutendo@momentum.com', '', '$2a$10$qVsfyIg6uwIeZKBAUmMlE.cIYB8HG3wkqLxDL8uA3Xr0laHUxoWyy', 'INVESTORS');

INSERT INTO public."_users"
 (id, email, extra_info, "password", "role")
 VALUES(nextval('_users_id_seq'::regclass), 'admin@momentum.com', '', '$2a$10$qVsfyIg6uwIeZKBAUmMlE.cIYB8HG3wkqLxDL8uA3Xr0laHUxoWyy', 'ADMIN');

INSERT INTO public.address
(address_id, city, street_address, surbub, zip_code)
VALUES(nextval('address_address_id_seq'::regclass), 'Gauteng', '68 Main Street', 'Tshiawelo', '1818');

INSERT INTO public.address
(address_id, city, street_address, surbub, zip_code)
VALUES(nextval('address_address_id_seq'::regclass), 'PLK', '36 Main Street', 'Nelspruit', '1919');

INSERT INTO public.investor
(address_id, birth_date, investor_id, email, firstname, lastname, phone)
VALUES(1, '2023-09-30', nextval('investor_investor_id_seq'::regclass), 'lutendo@momentum.com', 'Lutendo', 'Damuleli', '0815588153');

INSERT INTO public.products
(current_balance, investor_id, ip_fk, product_id, "type", "name")
VALUES(500000, 1, 1, 1, 0, 'RETIREMENT');

INSERT INTO public.products
(current_balance, investor_id, ip_fk, product_id, "type", "name")
VALUES(36000, 1, 1, 2, 1, 'SAVINGS');
