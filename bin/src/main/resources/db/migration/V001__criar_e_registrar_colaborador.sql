create table colaborador (
codigo bigint primary key auto_increment,
nome varchar(60)not null,
cpf varchar(20)not null,
favor int not null,
contra int not null,
data_cadastro date
);

insert into colaborador (nome, cpf, favor, contra, data_cadastro) values('Romario', '111-111-11', '2', '0', '2022-11-21');
insert into colaborador (nome, cpf, favor, contra, data_cadastro) values('Maria', '222-222-22', '3', '0', '2022-11-21');
insert into colaborador (nome, cpf, favor, contra, data_cadastro) values('João', '333-333-33', '2', '0', '2022-11-21');
insert into colaborador (nome, cpf, favor, contra, data_cadastro) values('Luiz', '444-444-44', '4', '0', '2022-11-21');
insert into colaborador (nome, cpf, favor, contra, data_cadastro) values('José', '555-555-55', '1', '0', '2022-11-21');
insert into colaborador (nome, cpf, favor, contra, data_cadastro) values('Mario', '666-666-66', '2', '0', '2022-11-21');
insert into colaborador (nome, cpf, favor, contra, data_cadastro) values('Adriano', '777-777-77', '1', '0', '2022-11-21');