/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

create table conta (
    numconta BIGINT NOT NULL AUTO_INCREMENT,
    saldo DOUBLE,
    
);
    

create table cliente (
    idcliente BIGINT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(255),
    sobrenome VARCHAR(255),
    rg VARCHAR(8),
    cpf VARCHAR(11),
    endereco VARCHAR(255),
    salario DOUBLE,
    constraint primary key (idcliente),
    constraint unique (cpf)
);

