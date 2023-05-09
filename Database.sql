-- public.orders definition

-- Drop table

-- DROP TABLE public.orders;

CREATE TABLE public.orders (
	order_id bigserial NOT NULL,
	mode_of_payment varchar(255) NULL,
	modified_at timestamp NULL,
	order_status varchar(255) NULL,
	ordered_at timestamp NULL,
	product_id int8 NOT NULL,
	product_rating float4 NOT NULL,
	product_review varchar(255) NULL,
	user_id int8 NOT NULL,
	CONSTRAINT orders_pkey PRIMARY KEY (order_id)
);


-- public.products definition

-- Drop table

-- DROP TABLE public.products;

CREATE TABLE public.products (
	product_id bigserial NOT NULL,
	added_by int8 NOT NULL,
	inserted_at timestamp NULL,
	price float4 NOT NULL,
	product_description varchar(255) NULL,
	product_name varchar(255) NULL,
	seller_id int8 NOT NULL,
	CONSTRAINT products_pkey PRIMARY KEY (product_id)
);


-- public.seller definition

-- Drop table

-- DROP TABLE public.seller;

CREATE TABLE public.seller (
	seller_id bigserial NOT NULL,
	added_by int8 NOT NULL,
	address varchar(255) NULL,
	email_id varchar(255) NULL,
	fba_seller bool NULL,
	inserted_at timestamp NULL,
	is_active bool NOT NULL,
	main_category varchar(255) NULL,
	phone_number varchar(255) NULL,
	seller_identification_number varchar(255) NULL,
	seller_name varchar(255) NULL,
	seller_rating float4 NOT NULL,
	CONSTRAINT seller_pkey PRIMARY KEY (seller_id)
);


-- public.stocks definition

-- Drop table

-- DROP TABLE public.stocks;

CREATE TABLE public.stocks (
	stock_id bigserial NOT NULL,
	product_id int8 NOT NULL,
	stock_left int8 NOT NULL,
	updated_at timestamp NULL,
	CONSTRAINT stocks_pkey PRIMARY KEY (stock_id)
);




-- public.users definition

-- Drop table

-- DROP TABLE public.users;

CREATE TABLE public.users (
	user_id bigserial NOT NULL,
	address varchar(255) NULL,
	country varchar(255) NULL,
	create_at timestamp NULL,
	email_id varchar(255) NULL,
	full_name varchar(255) NULL,
	mobile_no varchar(255) NULL,
	"password" varchar(255) NULL,
	user_name varchar(255) NULL,
	user_role varchar(255) NULL,
	CONSTRAINT users_pkey PRIMARY KEY (user_id)
);