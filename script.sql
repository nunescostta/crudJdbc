-- script para criação de tabela
create table pessoa(
	id uuid primary key,
	nome varchar(150) not null,
	cpf varchar(14) not null,
	telefone varchar(20) not null
);

-- consultar registro da tabela 
select * from pessoa;

-- gravar dados 
insert into pessoa(id, nome, cpf, telefone) values(gen_random_uuid(), 'Jean', '131.677.067-24', '(47)98844-2258')





