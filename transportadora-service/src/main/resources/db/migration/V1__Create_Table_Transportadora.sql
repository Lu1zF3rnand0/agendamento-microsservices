CREATE TABLE transportadora
(
    id       INT(10) AUTO_INCREMENT PRIMARY KEY,
    codigo   VARCHAR(36) not null,
    nome     VARCHAR(50) NOT NULL,
    cnpj     VARCHAR(18) NOT NULL,
    telefone VARCHAR(15),
    email    VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

ALTER TABLE transportadora
    ADD CONSTRAINT uk_transportadora_codigo unique (codigo);
