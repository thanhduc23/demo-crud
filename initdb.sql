-- DROP TYPE public.vehicle_type;

CREATE TYPE public.vehicle_type AS ENUM (
	'CAR',
	'TRUCK',
	'BUS');

-- public.brands definition

-- Drop table

-- DROP TABLE public.brands;

CREATE TABLE public.brands (
	id serial4 NOT NULL,
	"name" varchar(100) NOT NULL,
	"type" public.vehicle_type NOT NULL,
	created_at timestamp(6) DEFAULT now() NULL,
	updated_at timestamp(6) DEFAULT now() NULL,
	CONSTRAINT brands_pkey PRIMARY KEY (id)
);

-- public.users definition

-- Drop table

-- DROP TABLE public.users;

CREATE TABLE public.users (
	id serial4 NOT NULL,
	"name" varchar(100) NOT NULL,
	email varchar(100) NOT NULL,
	created_at timestamp(6) DEFAULT now() NULL,
	updated_at timestamp(6) DEFAULT now() NULL,
	CONSTRAINT users_email_key UNIQUE (email),
	CONSTRAINT users_pkey PRIMARY KEY (id)
);

-- public.vehicles definition

-- Drop table

-- DROP TABLE public.vehicles;

CREATE TABLE public.vehicles (
	id serial4 NOT NULL,
	"name" varchar(100) NOT NULL,
	manufacture_year int4 NOT NULL,
	price numeric(15, 2) NOT NULL,
	owner_id int4 NOT NULL,
	brand_id int4 NOT NULL,
	created_at timestamp(6) DEFAULT now() NULL,
	updated_at timestamp(6) DEFAULT now() NULL,
	CONSTRAINT check_manufacture_year CHECK (((manufacture_year > 1900) AND ((manufacture_year)::numeric <= EXTRACT(year FROM CURRENT_DATE)))),
	CONSTRAINT check_price CHECK ((price > (0)::numeric)),
	CONSTRAINT vehicles_pkey PRIMARY KEY (id)
);


-- public.vehicles foreign keys

ALTER TABLE public.vehicles ADD CONSTRAINT fk_vehicles_brand FOREIGN KEY (brand_id) REFERENCES public.brands(id);
ALTER TABLE public.vehicles ADD CONSTRAINT fk_vehicles_owner FOREIGN KEY (owner_id) REFERENCES public.users(id);