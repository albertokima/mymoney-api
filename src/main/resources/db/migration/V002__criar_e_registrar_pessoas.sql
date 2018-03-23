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

INSERT INTO pessoa (nome, ativo, logradouro, numero, bairro, cep, cidade, estado) values ('Alberto','true','Av Gaturamo','320','Jd Primavera','86702-001','Arapongas','PR');
INSERT INTO pessoa VALUES (3, 'Eder Yvan Gomes', 'Rua Osório Ribas de Paula', '794', NULL, 'Centro', '86.800-140', 'Apucarana', 'PR', false);
INSERT INTO pessoa VALUES (6, 'Comercial', 'Av Arapongas', '1000', 'Sala 401', 'Centro', '86.800-002', 'Arapongas', 'PR', true);
INSERT INTO pessoa VALUES (2, 'Charles', 'Av Curitiba', '1234', 'de 1122/1123 ao fim', 'Centro', '86.800-704', 'Apucarana', 'PR', false);

