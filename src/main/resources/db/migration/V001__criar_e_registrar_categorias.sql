CREATE TABLE categoria
(
  codigo serial NOT NULL,
  nome character varying(50) NOT NULL,
  CONSTRAINT categoria_pkey PRIMARY KEY (codigo)
);

INSERT INTO categoria VALUES (1, 'Lazer');
INSERT INTO categoria VALUES (2, 'Alimentação');
INSERT INTO categoria VALUES (3, 'Supermercado');
INSERT INTO categoria VALUES (4, 'Farmácia');
INSERT INTO categoria VALUES (6, 'Financiamento');
INSERT INTO categoria VALUES (8, 'Impostos');
INSERT INTO categoria VALUES (9, 'Despesas Bancárias');
INSERT INTO categoria VALUES (10, 'Restaurantes');
INSERT INTO categoria VALUES (12, 'Informática');
INSERT INTO categoria VALUES (13, 'Clientes');
INSERT INTO categoria VALUES (14, 'Vendas');
INSERT INTO categoria VALUES (5, 'Transporte');
INSERT INTO categoria VALUES (7, 'Receitas');
INSERT INTO categoria VALUES (15, 'Salários');

