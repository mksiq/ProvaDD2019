DROP DATABASE IF EXISTS provapratica20192;
CREATE DATABASE provapratica20192;

USE provapratica20192;

-- DDLs
CREATE TABLE USUARIO (
IDUSUARIO INT NOT NULL PRIMARY KEY AUTO_INCREMENT, 
NOME VARCHAR(255), 
CPF CHAR(11), 
TELEFONE CHAR(14), 
LOGIN VARCHAR(255), 
SENHA VARCHAR(255)
);

CREATE TABLE RECEITA (
IDRECEITA INT NOT NULL PRIMARY KEY AUTO_INCREMENT, 
IDUSUARIO INT, 
DESCRICAO VARCHAR(255), 
VALOR DECIMAL(10,2), 
DATARECEITA DATE,  
FOREIGN KEY (IDUSUARIO) REFERENCES USUARIO (IDUSUARIO)
);

CREATE TABLE DESPESA (
IDDESPESA INT NOT NULL PRIMARY KEY AUTO_INCREMENT, 
IDUSUARIO INT, 
DESCRICAO VARCHAR(255), 
VALOR DECIMAL(10,2), 
DATAVENCIMENTO DATE, 
DATAPAGAMENTO DATE, 
CATEGORIA VARCHAR(255),
FOREIGN KEY (IDUSUARIO) REFERENCES USUARIO (IDUSUARIO)
);

-- DMLs
INSERT INTO USUARIO (nome, cpf, telefone, login, senha) VALUES ('Chaves', '01234567890', '11111-1111', 'chaves', '1234');
INSERT INTO USUARIO (nome, cpf, telefone, login, senha) VALUES ('Quico', '09876543210', '22222-2222', 'kiko', '4321');
INSERT INTO USUARIO (nome, cpf, telefone, login, senha) VALUES ('Dona Florinda', '56789001234', '33333-3333', 'florinda', '3412');
INSERT INTO USUARIO (nome, cpf, telefone, login, senha) VALUES ('Sr. Madruga', '54321067890', '44444-4444', 'madruga', '2134');
INSERT INTO USUARIO (nome, cpf, telefone, login, senha) VALUES ('Chiquinha', '11111111111', '44444-4444', 'chiquinha', '1134');

-- Despesas do Chaves
INSERT INTO DESPESA (idusuario, descricao, valor, datavencimento, datapagamento, categoria) VALUES (1, 'Luva de Boxe', 1400, '2019-06-10', '2019-06-10', 'Diversão');
INSERT INTO DESPESA (idusuario, descricao, valor, datavencimento, datapagamento, categoria) VALUES (1, 'Suco de Tamarindo', 600, '2019-06-10', '2019-06-10', 'Alimentação');

-- Despesas do Quico
INSERT INTO DESPESA (idusuario, descricao, valor, datavencimento, datapagamento, categoria) VALUES (2, 'Sanduíche de presunto', 1500, '2019-06-15', '2019-06-15', 'Alimentação');
INSERT INTO DESPESA (idusuario, descricao, valor, datavencimento, datapagamento, categoria) VALUES (2, 'Bola quadrada', 150, '2019-06-18', '2019-06-18', 'Diversão');

-- Despesas da Dona Florinda
INSERT INTO DESPESA (idusuario, descricao, valor, datavencimento, datapagamento, categoria) VALUES (3, 'Janeiro/2019', 200, '2019-01-20', '2019-06-20', 'Aluguel');
INSERT INTO DESPESA (idusuario, descricao, valor, datavencimento, datapagamento, categoria) VALUES (3, 'Abril/2019', 1400, '2019-05-10', null, 'Aluguel');
INSERT INTO DESPESA (idusuario, descricao, valor, datavencimento, datapagamento, categoria) VALUES (3, 'Julho/2019', 600, '2019-07-10', null, 'Aluguel');
INSERT INTO DESPESA (idusuario, descricao, valor, datavencimento, datapagamento, categoria) VALUES (3, 'Roupa do Tesouro', 1400, '2019-08-10', null, 'Presentes');
