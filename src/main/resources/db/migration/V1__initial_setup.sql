CREATE TABLE public."_users" (
	id bigserial NOT NULL,
	email varchar(255) NULL,
	extra_info varchar(255) NULL,
	"password" varchar(255) NULL,
	"role" varchar(255) NULL,
	CONSTRAINT "_users_pkey" PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS  public.address (
	address_id serial4 NOT NULL,
	city varchar(255) NULL,
	street_address varchar(255) NULL,
	surbub varchar(255) NULL,
	zip_code varchar(255) NULL,
	CONSTRAINT address_pkey PRIMARY KEY (address_id)
);

CREATE TABLE IF NOT EXISTS  public.withdraw (
	id serial4 NOT NULL,
	withdrawal_amount float8 NULL,
	account_number varchar(255) NULL,
	reference_name varchar(255) NULL,
	CONSTRAINT withdraw_pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS  public.investor (
	address_id int4 NULL,
	birth_date date NULL,
	investor_id serial4 NOT NULL,
	email varchar(255) NULL,
	firstname varchar(255) NULL,
	lastname varchar(255) NULL,
	phone varchar(255) NULL,
	CONSTRAINT investor_address_id_key UNIQUE (address_id),
	CONSTRAINT investor_pkey PRIMARY KEY (investor_id),
	CONSTRAINT fkrga81xovaj520possfs4q4p5f FOREIGN KEY (address_id) REFERENCES public.address(address_id)
);

CREATE TABLE IF NOT EXISTS  public.products (
	current_balance float8 NULL,
	investor_id int4 NULL,
	ip_fk int4 NULL,
	product_id int4 NOT NULL,
	"type" int2 NULL,
	"name" varchar(255) NULL,
	CONSTRAINT products_pkey PRIMARY KEY (product_id),
	CONSTRAINT products_type_check CHECK (((type >= 0) AND (type <= 1))),
	CONSTRAINT fk8oj52flkd7h3uhfeq3en0wo2j FOREIGN KEY (ip_fk) REFERENCES public.investor(investor_id),
	CONSTRAINT fk9gqp4ho1nrnha0tajxugjq5u8 FOREIGN KEY (investor_id) REFERENCES public.investor(investor_id)
);