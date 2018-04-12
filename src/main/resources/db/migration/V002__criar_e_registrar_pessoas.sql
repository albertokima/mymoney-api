CREATE TABLE pessoa
(
  codigo serial NOT NULL,
  nome character varying(50) NOT NULL,
  logradouro character varying(100),
  numero character varying(20),
  complemento character varying(100),
  bairro character varying(100),
  cep character varying(10),
  cidade character varying(50),
  estado character varying(2), 
  ativo boolean NOT NULL,
  CONSTRAINT pessoa_pkey PRIMARY KEY (codigo)
);

INSERT INTO pessoa (nome, ativo, logradouro, numero, bairro, cep, cidade, estado) values ('Alberto','true','Avenida','32','Jd Sul','88.888-888','Curitiba','PR');
INSERT INTO pessoa VALUES (3, 'Eder', 'Rua 1', '74', NULL, 'Centro', '86.868-686', 'Curitiba', 'PR', false);
INSERT INTO pessoa VALUES (6, 'Comercial', 'Rua 2', '24', 'Sala 401', 'Centro', '80.808-080', 'Curitiba', 'PR', true);
INSERT INTO pessoa VALUES (2, 'Charles', 'Rua 3', '12', '', 'Centro', '86.688-668', 'Curitiba', 'PR', false);

