CREATE TABLE carga_solta
(
    id        INT(10) AUTO_INCREMENT PRIMARY KEY,
    codigo    VARCHAR(36) NOT NULL,
    nome      VARCHAR(50) NOT NULL,
    descricao VARCHAR(255),
    imo       BIT
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

ALTER TABLE carga_solta
    ADD CONSTRAINT uk_carga_solta_codigo unique (codigo);
