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
