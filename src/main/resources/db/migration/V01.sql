CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE public.profile (
	id UUID NOT NULL,
	description VARCHAR(100) NOT NULL UNIQUE,
	CONSTRAINT profile_pkey PRIMARY KEY (id)
);

CREATE TABLE public.user_account (
	id UUID NOT NULL,
	cell_number VARCHAR(150) NULL,
	cpf VARCHAR(16) NOT NULL,
	email VARCHAR(255) NOT NULL UNIQUE,
	login VARCHAR(50) NOT NULL UNIQUE,
	name VARCHAR(255) NOT NULL,
	password VARCHAR(255) NOT NULL,
	phone_number VARCHAR(150) NULL,
	profile UUID NOT NULL,
	active BOOL NOT NULL DEFAULT TRUE,
	CONSTRAINT user_account_pkey PRIMARY KEY (id)
);
ALTER TABLE public.user_account ADD CONSTRAINT FK_user_account_X_profile FOREIGN KEY (profile) REFERENCES profile(id);


CREATE TABLE public.house_log (
	id UUID NOT NULL,
	requested_id VARCHAR(100) NOT NULL,
	persona UUID,
	response VARCHAR NOT NULL,
	data_hora TIMESTAMP NOT NULL,
	CONSTRAINT house_log_pkey PRIMARY KEY (id)
);

--CREATE TABLE public.house (
--	id UUID NOT NULL,
--	house JSON NOT NULL,
--	--house_log UIID NOT NULL,
--	CONSTRAINT house_pkey PRIMARY KEY (id)
--);
--ALTER TABLE public.house ADD CONSTRAINT FK_house_X_house_log FOREIGN KEY (house_log) REFERENCES house_log(id);

CREATE TABLE public.persona (
	id UUID NOT NULL,
	name VARCHAR(100) NOT NULL ,
	role VARCHAR(50) NOT NULL ,
	school VARCHAR(100) NOT NULL ,
	patronus VARCHAR(20) NOT NULL ,
	house VARCHAR(100) NOT NULL,
	CONSTRAINT persona_pkey PRIMARY KEY (id)
);

INSERT INTO public.profile(id, description)
VALUES
    (uuid_generate_v4(), 'ADMINISTRATOR'),
    (uuid_generate_v4(), 'REVISOR'),
    (uuid_generate_v4(), 'OPERATOR');

INSERT INTO public.user_account(id, cpf, email, login, name, password, profile)
VALUES(uuid_generate_v4(), 'admin', 'admin@restapi.com', 'admin', 'Admin', '$2a$10$WC/WeLngu8r97ZMGgxY7cOwMht0iEmaou7syA6tq4F.wM/gAeZr6S', (SELECT p.id FROM profile p WHERE p.description = 'ADMINISTRATOR'));
