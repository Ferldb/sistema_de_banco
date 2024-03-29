create table cliente (
    idcliente BIGINT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(255),
    sobrenome VARCHAR(255),
    rg VARCHAR(9),
    cpf VARCHAR(11),
    endereco VARCHAR(255),
    salario DOUBLE,
    constraint primary key (idcliente),
    constraint unique (cpf)
);

create table tipoconta(
    idtipo BIGINT NOT NULL AUTO_INCREMENT,
    descricao VARCHAR(255),
    constraint primary key (idtipo)
);

create table conta (
    numconta BIGINT NOT NULL AUTO_INCREMENT,
    tipoconta BIGINT NOT NULL,
    saldo DOUBLE,
    limite DOUBLE,
    depositoMinimo DOUBLE,
    montanteMinimo DOUBLE,
    idcliente BIGINT NOT NULL,
    constraint primary key (numconta),
    constraint foreign key (tipoconta) references tipoconta (idtipo),
    constraint foreign key (idcliente) references cliente (idcliente)
);

INSERT INTO tipoconta (descricao) VALUES ('Conta Corrente');
INSERT INTO tipoconta (descricao) VALUES ('Conta Investimento');
