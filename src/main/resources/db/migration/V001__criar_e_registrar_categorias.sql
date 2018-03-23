CREATE TABLE categoria
(
  codigo serial NOT NULL,
  nome character varying(50) NOT NULL,
  CONSTRAINT categoria_pkey PRIMARY KEY (codigo)
);

INSERT INTO categoria (nome) values ('Lazer');
INSERT INTO categoria (nome) values ('Alimentação');
INSERT INTO categoria (nome) values ('Supermercado');
INSERT INTO categoria (nome) values ('Farmácia');
INSERT INTO categoria (nome) values ('Outros');