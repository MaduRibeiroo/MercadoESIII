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
CREATE SEQUENCE public.fornecedor_forn_id_seq START 1;
CREATE SEQUENCE public.venda_venda_id_seq START 1;
CREATE SEQUENCE public.compra_compra_id_seq START 1;
CREATE SEQUENCE public.item_item_id_seq START 1;


-- =========================
-- TABELA: CATEGORIA
-- =========================
CREATE TABLE public.categoria (
    cat_id BIGINT NOT NULL DEFAULT nextval('public.categoria_cat_id_seq') PRIMARY KEY,
    cat_name varchar(80) NOT NULL
);


-- =========================
-- TABELA: USUARIO
-- =========================
CREATE TABLE public.usuario (
    usr_id BIGINT NOT NULL DEFAULT nextval('public.usuario_usr_id_seq') PRIMARY KEY,
    usr_name varchar(80) NOT NULL,
    usr_pass varchar(80) NOT NULL,
    usr_level integer NOT NULL
);


-- =========================
-- TABELA: ANUNCIO
-- =========================
CREATE TABLE public.anuncio (
    anu_id BIGINT NOT NULL DEFAULT nextval('public.anuncio_anu_id_seq') PRIMARY KEY,
    anu_title varchar(80) NOT NULL,
    anu_date date NOT NULL,
    anu_desc varchar(500) NOT NULL,
    anu_price numeric(10,2) NOT NULL,
    anu_stock integer NOT NULL,
    cat_id BIGINT NOT NULL,
    usr_id BIGINT NOT NULL,
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
    fot_id BIGINT NOT NULL DEFAULT nextval('public.foto_anuncio_fot_id_seq') PRIMARY KEY,
    fot_file bytea NOT NULL,
    fot_ext varchar(80) NOT NULL,
    anu_id BIGINT NOT NULL,
    CONSTRAINT fk_foto_anuncio_anu FOREIGN KEY (anu_id)
        REFERENCES public.anuncio (anu_id)
        ON UPDATE CASCADE
    	ON DELETE CASCADE
);


-- =========================
-- TABELA: PERGUNTA
-- =========================
CREATE TABLE public.pergunta (
    per_id BIGINT NOT NULL DEFAULT nextval('public.pergunta_per_id_seq') PRIMARY KEY,
    per_text varchar(500) NOT NULL,
    per_resp varchar(500),
    anu_id BIGINT NOT NULL,
    CONSTRAINT fk_pergunta_anu FOREIGN KEY (anu_id)
        REFERENCES public.anuncio (anu_id)
        ON UPDATE CASCADE
    	ON DELETE CASCADE
);


-- =========================
-- TABELA: FORNECEDOR
-- =========================
CREATE TABLE public.fornecedor (
    forn_id BIGINT NOT NULL DEFAULT nextval('public.fornecedor_forn_id_seq') PRIMARY KEY,
    forn_name varchar(80) NOT NULL,
    forn_cnpj varchar(20)
);


-- =========================
-- TABELA: VENDA
-- =========================
CREATE TABLE public.venda (
    venda_id BIGINT NOT NULL DEFAULT nextval('public.venda_venda_id_seq') PRIMARY KEY,
    venda_total NUMERIC(10, 2) NOT NULL,
	venda_data TIMESTAMP WITHOUT TIME ZONE NOT NULL
);


-- =========================
-- TABELA: COMPRA
-- =========================
CREATE TABLE public.compra (
    compra_id BIGINT NOT NULL DEFAULT nextval('public.compra_compra_id_seq') PRIMARY KEY,
    compra_total NUMERIC(10, 2) NOT NULL,
	compra_data TIMESTAMP WITHOUT TIME ZONE NOT NULL
);


-- =========================
-- TABELA: ITEM
-- =========================
CREATE TABLE public.item (
    item_id BIGINT NOT NULL DEFAULT nextval('public.item_item_id_seq') PRIMARY KEY,
    item_qtde integer NOT NULL,
	anu_id BIGINT NOT NULL,
    item_total numeric(10,2) NOT NULL,
	item_tipo varchar(80) NOT NULL,
	item_tipoId BIGINT NOT NULL,
	compra_id BIGINT,
    venda_id BIGINT,
    CONSTRAINT fk_itens_anu FOREIGN KEY (anu_id)
        REFERENCES public.anuncio (anu_id)
        ON UPDATE CASCADE
    	ON DELETE NO ACTION,
    CONSTRAINT fk_itens_compra FOREIGN KEY (compra_id)
        REFERENCES public.compra (compra_id)
        ON UPDATE CASCADE
    	ON DELETE CASCADE,
    CONSTRAINT fk_itens_venda FOREIGN KEY (venda_id)
        REFERENCES public.venda (venda_id)
        ON UPDATE CASCADE
    	ON DELETE CASCADE
);


-- =========================
-- DONOS DAS SEQUENCIAS
-- =========================

ALTER SEQUENCE public.categoria_cat_id_seq OWNED BY public.categoria.cat_id;
ALTER SEQUENCE public.usuario_usr_id_seq OWNED BY public.usuario.usr_id;
ALTER SEQUENCE public.anuncio_anu_id_seq OWNED BY public.anuncio.anu_id;
ALTER SEQUENCE public.foto_anuncio_fot_id_seq OWNED BY public.foto_anuncio.fot_id;
ALTER SEQUENCE public.pergunta_per_id_seq OWNED BY public.pergunta.per_id;
ALTER SEQUENCE public.fornecedor_forn_id_seq OWNED BY public.fornecedor.forn_id;
ALTER SEQUENCE public.venda_venda_id_seq OWNED BY public.venda.venda_id;
ALTER SEQUENCE public.compra_compra_id_seq OWNED BY public.compra.compra_id;
ALTER SEQUENCE public.item_item_id_seq OWNED BY public.item.item_id;


-- =========================
-- DONOS DAS TABELAS
-- =========================
ALTER TABLE public.categoria OWNER TO postgres;
ALTER TABLE public.anuncio OWNER TO postgres;
ALTER TABLE public.usuario OWNER TO postgres;
ALTER TABLE public.foto_anuncio OWNER TO postgres;
ALTER TABLE public.item OWNER TO postgres;
ALTER TABLE public.compra OWNER TO postgres;
ALTER TABLE public.venda OWNER TO postgres;
ALTER TABLE public.pergunta OWNER TO postgres;
ALTER TABLE public.fornecedor OWNER TO postgres;


-- =========================
-- INSERTS - CATEGORIA
-- =========================
INSERT INTO public.categoria (cat_name) VALUES ('Informática');
INSERT INTO public.categoria (cat_name) VALUES ('Livros');
INSERT INTO public.categoria (cat_name) VALUES ('Acessórios');
INSERT INTO public.categoria (cat_name) VALUES ('Celulares');
INSERT INTO public.categoria (cat_name) VALUES ('Eletrodomésticos');


-- =========================
-- INSERTS - USUARIO
-- =========================
INSERT INTO public.usuario (usr_name, usr_pass, usr_level) VALUES ('ariane', '123', 1);
INSERT INTO public.usuario (usr_name, usr_pass, usr_level) VALUES ('savoldi', '123', 2);
INSERT INTO public.usuario (usr_name, usr_pass, usr_level) VALUES ('madu', '123', 2);
INSERT INTO public.usuario (usr_name, usr_pass, usr_level) VALUES ('isabelly', '123', 3);
INSERT INTO public.usuario (usr_name, usr_pass, usr_level) VALUES ('adm', '321', 1);


-- =========================
-- INSERTS - ANUNCIO
-- =========================
INSERT INTO public.anuncio (anu_title, anu_date, anu_desc, anu_price, anu_stock, cat_id, usr_id)
VALUES ('Notebook Dell Inspiron', '2025-01-12', 'Notebook i5 8GB SSD 512GB', 3500.00, 10, 1, 1);

INSERT INTO public.anuncio (anu_title, anu_date, anu_desc, anu_price, anu_stock, cat_id, usr_id)
VALUES ('Livro Java Completo', '2025-02-05', 'Livro sobre programação Java', 120.50, 30, 2, 2);

INSERT INTO public.anuncio (anu_title, anu_date, anu_desc, anu_price, anu_stock, cat_id, usr_id)
VALUES ('Fone Bluetooth', '2025-03-10', 'Fone sem fio com cancelamento de ruído', 199.90, 50, 3, 3);

INSERT INTO public.anuncio (anu_title, anu_date, anu_desc, anu_price, anu_stock, cat_id, usr_id)
VALUES ('iPhone 14 Pro', '2025-04-02', 'Celular Apple 256GB cor roxa', 7999.00, 5, 4, 4);

INSERT INTO public.anuncio (anu_title, anu_date, anu_desc, anu_price, anu_stock, cat_id, usr_id)
VALUES ('Geladeira Brastemp Frost Free', '2025-04-15', 'Geladeira duplex 400L', 2999.99, 8, 5, 5);


-- =========================
-- INSERTS - FOTO_ANUNCIO
-- =========================
INSERT INTO public.foto_anuncio (fot_file, fot_ext, anu_id) VALUES (decode('FFD8FFE0', 'hex'), '.jpg', 1);
INSERT INTO public.foto_anuncio (fot_file, fot_ext, anu_id) VALUES (decode('FFD8FFE1', 'hex'), '.jpg', 2);
INSERT INTO public.foto_anuncio (fot_file, fot_ext, anu_id) VALUES (decode('FFD8FFE2', 'hex'), '.jpg', 3);
INSERT INTO public.foto_anuncio (fot_file, fot_ext, anu_id) VALUES (decode('FFD8FFE3', 'hex'), '.jpg', 4);
INSERT INTO public.foto_anuncio (fot_file, fot_ext, anu_id) VALUES (decode('FFD8FFE4', 'hex'), '.jpg', 5);


-- =========================
-- INSERTS - PERGUNTA
-- =========================
INSERT INTO public.pergunta (per_text, per_resp, anu_id)
VALUES ('Esse notebook vem com Windows?', 'Sim, com Windows 11 instalado.', 1);

INSERT INTO public.pergunta (per_text, per_resp, anu_id)
VALUES ('O livro é novo ou usado?', 'Novo, lacrado.', 2);

INSERT INTO public.pergunta (per_text, per_resp, anu_id)
VALUES ('O fone é compatível com iPhone?', 'Sim, compatível com Android e iOS.', 3);

INSERT INTO public.pergunta (per_text, per_resp, anu_id)
VALUES ('Tem parcelamento sem juros?', 'Sim, em até 10x.', 4);

INSERT INTO public.pergunta (per_text, per_resp, anu_id)
VALUES ('Entrega para todo o Brasil?', 'Sim, frete calculado no carrinho.', 5);


-- =========================
-- INSERTS - COMPRA
-- =========================
INSERT INTO public.compra (compra_total, compra_data) VALUES (150.00, '2025-05-01 14:30:00');
INSERT INTO public.compra (compra_total, compra_data) VALUES (7999.00, '2025-05-02 10:00:00');
INSERT INTO public.compra (compra_total, compra_data) VALUES (3500.00, '2025-05-03 16:45:00');
INSERT INTO public.compra (compra_total, compra_data) VALUES (199.90, '2025-05-04 11:15:00');
INSERT INTO public.compra (compra_total, compra_data) VALUES (2999.99, '2025-05-05 18:20:00');


-- =========================
-- INSERTS - VENDA
-- =========================
INSERT INTO public.venda (venda_total, venda_data) VALUES (150.00, '2025-06-01 10:00:00');
INSERT INTO public.venda (venda_total, venda_data) VALUES (7999.00, '2025-06-02 09:30:00');
INSERT INTO public.venda (venda_total, venda_data) VALUES (3500.00, '2025-06-03 15:10:00');
INSERT INTO public.venda (venda_total, venda_data) VALUES (199.90, '2025-06-04 12:00:00');
INSERT INTO public.venda (venda_total, venda_data) VALUES (2999.99, '2025-06-05 17:45:00');


-- =========================
-- INSERTS - ITEM
-- =========================
INSERT INTO public.item (item_qtde, anu_id, item_total, item_tipo, item_tipoId, compra_id, venda_id)
VALUES (1, 1, 3500.00, 'venda', 1, NULL, 3);

INSERT INTO public.item (item_qtde, anu_id, item_total, item_tipo, item_tipoId, compra_id, venda_id)
VALUES (2, 2, 241.00, 'compra', 2, 1, NULL);

INSERT INTO public.item (item_qtde, anu_id, item_total, item_tipo, item_tipoId, compra_id, venda_id)
VALUES (3, 3, 599.70, 'venda', 3, NULL, 4);

INSERT INTO public.item (item_qtde, anu_id, item_total, item_tipo, item_tipoId, compra_id, venda_id)
VALUES (1, 4, 7999.00, 'compra', 4, 2, NULL);

INSERT INTO public.item (item_qtde, anu_id, item_total, item_tipo, item_tipoId, compra_id, venda_id)
VALUES (1, 5, 2999.99, 'venda', 5, NULL, 5);


-- =========================
-- INSERTS - FORNECEDOR
-- =========================
INSERT INTO public.fornecedor (forn_name, forn_cnpj) VALUES ('kleiton','75.113.461/0001-70');
INSERT INTO public.fornecedor (forn_name, forn_cnpj) VALUES ('valentina','03.426.613/0001-83');
INSERT INTO public.fornecedor (forn_name, forn_cnpj) VALUES ('enzo','37.151.966/0001-30');
INSERT INTO public.fornecedor (forn_name, forn_cnpj) VALUES ('antonia','67.829.216/0001-35');
INSERT INTO public.fornecedor (forn_name, forn_cnpj) VALUES ('maria maria de maria','11.943.955/0001-07');