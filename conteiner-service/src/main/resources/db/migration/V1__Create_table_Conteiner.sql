CREATE TABLE conteiner
(
    id      INT(10) AUTO_INCREMENT PRIMARY KEY,
    codigo  varchar(36) not null,
    numero  varchar(50) NOT NULL,
    tamanho INT,
    status  VARCHAR(5)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

ALTER TABLE conteiner
    ADD CONSTRAINT uk_conteiner_codigo unique (codigo);
