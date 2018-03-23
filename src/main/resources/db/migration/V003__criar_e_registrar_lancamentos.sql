CREATE TABLE lancamento
(
  codigo serial NOT NULL,
  descricao character varying(50) NOT NULL,
  data_vencimento date NOT NULL,
  data_pagamento date,
  valor numeric(10,2) NOT NULL,
  observacao character varying(100),
  tipo character varying(10) NOT NULL,
  codigo_categoria bigint NOT NULL,
  codigo_pessoa bigint NOT NULL,
  CONSTRAINT lancamento_pkey PRIMARY KEY (codigo),
  CONSTRAINT categoria_fkey FOREIGN KEY (codigo_categoria) REFERENCES categoria (codigo),
  CONSTRAINT pessoa_fkey FOREIGN KEY (codigo_pessoa) REFERENCES pessoa (codigo)
);

INSERT INTO lancamento (descricao,data_vencimento,data_pagamento,valor,tipo,codigo_categoria,codigo_pessoa) VALUES ('Café da manhã','2018-03-01','2018-03-01',15,'DESPESA',2,1);
INSERT INTO lancamento (descricao,data_vencimento,data_pagamento,valor,tipo,codigo_categoria,codigo_pessoa) VALUES ('Almoço','2018-03-02','2018-03-02',50,'DESPESA',2,2);
INSERT INTO lancamento (descricao,data_vencimento,data_pagamento,valor,tipo,codigo_categoria,codigo_pessoa) VALUES ('Combustível','2018-03-03','2018-03-03',100,'DESPESA',5,3);
INSERT INTO lancamento (descricao,data_vencimento,data_pagamento,valor,tipo,codigo_categoria,codigo_pessoa) VALUES ('Mensalidade João','2018-03-10',null,200,'RECEITA',7,6);
INSERT INTO lancamento (descricao,data_vencimento,data_pagamento,valor,tipo,codigo_categoria,codigo_pessoa) VALUES ('Mensalidade Maria','2018-03-10',null,500,'RECEITA',7,6);
INSERT INTO lancamento (descricao,data_vencimento,data_pagamento,valor,tipo,codigo_categoria,codigo_pessoa) VALUES ('BMW - parcela 10/24','2018-03-15',null,3000,'DESPESA',6,1);
INSERT INTO lancamento (descricao,data_vencimento,data_pagamento,valor,tipo,codigo_categoria,codigo_pessoa) VALUES ('ICMS','2018-03-05','2018-03-05',300,'DESPESA',8,6);
INSERT INTO lancamento (descricao,data_vencimento,data_pagamento,valor,tipo,codigo_categoria,codigo_pessoa) VALUES ('Juros','2018-03-07','2018-03-07',50,'DESPESA',9,6);
INSERT INTO lancamento (descricao,data_vencimento,data_pagamento,valor,tipo,codigo_categoria,codigo_pessoa) VALUES ('Macbook Pro','2018-03-11','2018-03-11',5000,'DESPESA',12,6);
