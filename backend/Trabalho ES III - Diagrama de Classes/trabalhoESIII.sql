-- =========================
-- CONFIGURAÇÕES INICIAIS
-- =========================
SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

ALTER SCHEMA public OWNER TO postgres;
SET default_tablespace = '';

-- =========================
-- SEQUENCES
-- =========================
CREATE SEQUENCE public.categoria_cat_id_seq START 1;
CREATE SEQUENCE public.usuario_usr_id_seq START 1;
CREATE SEQUENCE public.anuncio_anu_id_seq START 1;
CREATE SEQUENCE public.foto_anuncio_fot_id_seq START 1;
CREATE SEQUENCE public.pergunta_per_id_seq START 1;
CREATE SEQUENCE public.venda_venda_id_seq START 1;
CREATE SEQUENCE public.compra_compra_id_seq START 1;
CREATE SEQUENCE public.itens_itens_id_seq START 1;

-- =========================
-- TABELA: CATEGORIA
-- =========================
CREATE TABLE public.categoria (
    cat_id integer NOT NULL DEFAULT nextval('public.categoria_cat_id_seq') PRIMARY KEY,
    cat_name varchar(80) NOT NULL
);

-- =========================
-- TABELA: USUARIO
-- =========================
CREATE TABLE public.usuario (
    usr_id integer NOT NULL DEFAULT nextval('public.usuario_usr_id_seq') PRIMARY KEY,
    usr_name varchar(80) NOT NULL,
    usr_pass varchar(80) NOT NULL,
    usr_level integer NOT NULL
);

-- =========================
-- TABELA: ANUNCIO
-- =========================
CREATE TABLE public.anuncio (
    anu_id integer NOT NULL DEFAULT nextval('public.anuncio_anu_id_seq') PRIMARY KEY,
    anu_title varchar(80) NOT NULL,
    anu_date date NOT NULL,
    anu_desc text NOT NULL,
    anu_price numeric(10,1) NOT NULL,
    anu_estoque integer NOT NULL,
    cat_id integer NOT NULL,
    usr_id integer NOT NULL,
    CONSTRAINT fk_anuncio_cat FOREIGN KEY (cat_id)
        REFERENCES public.categoria (cat_id)
        ON UPDATE CASCADE
        ON DELETE NO ACTION,
    CONSTRAINT fk_anuncio_usr FOREIGN KEY (usr_id)
        REFERENCES public.usuario (usr_id)
        ON UPDATE CASCADE
        ON DELETE NO ACTION
);

-- =========================
-- TABELA: FOTO_ANUNCIO
-- =========================
CREATE TABLE public.foto_anuncio (
    fot_id integer NOT NULL DEFAULT nextval('public.foto_anuncio_fot_id_seq') PRIMARY KEY,
    fot_file bytea NOT NULL,
    fot_ext varchar(80) NOT NULL,
    anu_id integer NOT NULL,
    CONSTRAINT fk_foto_anuncio_anu FOREIGN KEY (anu_id)
        REFERENCES public.anuncio (anu_id)
        ON UPDATE CASCADE
        ON DELETE NO ACTION
);

-- =========================
-- TABELA: PERGUNTA
-- =========================
CREATE TABLE public.pergunta (
    per_id integer NOT NULL DEFAULT nextval('public.pergunta_per_id_seq') PRIMARY KEY,
    per_text varchar(80) NOT NULL,
    per_resp varchar(80),
    anu_id integer NOT NULL,
    CONSTRAINT fk_pergunta_anu FOREIGN KEY (anu_id)
        REFERENCES public.anuncio (anu_id)
        ON UPDATE CASCADE
        ON DELETE NO ACTION
);

-- =========================
-- TABELA: VENDA
-- =========================
CREATE TABLE public.venda (
    venda_id integer NOT NULL DEFAULT nextval('public.venda_venda_id_seq') PRIMARY KEY,
    venda_data timestamp NOT NULL
);

-- =========================
-- TABELA: COMPRA
-- =========================
CREATE TABLE public.compra (
    compra_id integer NOT NULL DEFAULT nextval('public.compra_compra_id_seq') PRIMARY KEY,
    compra_data timestamp NOT NULL
);

-- =========================
-- TABELA: ITENS
-- =========================
CREATE TABLE public.itens (
    itens_id integer NOT NULL DEFAULT nextval('public.itens_itens_id_seq') PRIMARY KEY,
    compra_id integer NOT NULL,
    venda_id integer NOT NULL,
    itens_qtd integer NOT NULL,
    itens_total numeric(10,2) NOT NULL,
    anu_id integer NOT NULL,
    CONSTRAINT fk_itens_anu FOREIGN KEY (anu_id)
        REFERENCES public.anuncio (anu_id)
        ON UPDATE CASCADE
        ON DELETE NO ACTION,
    CONSTRAINT fk_itens_compra FOREIGN KEY (compra_id)
        REFERENCES public.compra (compra_id)
        ON UPDATE CASCADE
        ON DELETE NO ACTION,
    CONSTRAINT fk_itens_venda FOREIGN KEY (venda_id)
        REFERENCES public.venda (venda_id)
        ON UPDATE CASCADE
        ON DELETE NO ACTION
);

-- =========================
-- DONOS DAS TABELAS
-- =========================
ALTER TABLE public.categoria OWNER TO postgres;
ALTER TABLE public.anuncio OWNER TO postgres;
ALTER TABLE public.usuario OWNER TO postgres;
ALTER TABLE public.foto_anuncio OWNER TO postgres;
ALTER TABLE public.itens OWNER TO postgres;
ALTER TABLE public.compra OWNER TO postgres;
ALTER TABLE public.venda OWNER TO postgres;
ALTER TABLE public.pergunta OWNER TO postgres;

-- =========================
-- INSERTS - CATEGORIA
-- =========================
INSERT INTO public.categoria (cat_name) VALUES ('informática');
INSERT INTO public.categoria (cat_name) VALUES ('livros');
INSERT INTO public.categoria (cat_name) VALUES ('acessórios');
INSERT INTO public.categoria (cat_name) VALUES ('celulares');

-- =========================
-- INSERTS - USUARIO
-- =========================
INSERT INTO public.usuario (usr_name, usr_pass, usr_level) VALUES ('ariane', '123', 1);
INSERT INTO public.usuario (usr_name, usr_pass, usr_level) VALUES ('savoldi', '123', 2);
INSERT INTO public.usuario (usr_name, usr_pass, usr_level) VALUES ('madu', '123', 2);
INSERT INTO public.usuario (usr_name, usr_pass, usr_level) VALUES ('isabelly', '123', 3);

-- =========================
-- INSERTS - USUARIO
-- =========================
INSERT INTO public.usuario (usr_name, usr_pass, usr_level) VALUES ('ariane', '123', 1);
INSERT INTO public.usuario (usr_name, usr_pass, usr_level) VALUES ('savoldi', '123', 2);
INSERT INTO public.usuario (usr_name, usr_pass, usr_level) VALUES ('madu', '123', 2);
INSERT INTO public.usuario (usr_name, usr_pass, usr_level) VALUES ('isabelly', '123', 3);

