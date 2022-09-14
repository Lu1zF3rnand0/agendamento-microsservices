CREATE TABLE veiculo
(
    id        INT(10) AUTO_INCREMENT PRIMARY KEY,
    codigo    VARCHAR(36) not null,
    chassi    VARCHAR(17) NOT NULL,
    modelo    VARCHAR(50),
    montadora VARCHAR(50)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

ALTER TABLE veiculo
    ADD CONSTRAINT uk_veiculo_codigo unique (codigo);
