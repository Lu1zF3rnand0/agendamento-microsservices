create table agendamento (id bigint not null auto_increment, codigo varchar(255), descricao varchar(255), status varchar(255), primary key (id)) engine=InnoDB;
create table agendamento_cargas (agendamento_id bigint not null, cargas_id bigint not null) engine=InnoDB;
create table agendamento_transportadoras (agendamento_id bigint not null, transportadoras_id bigint not null) engine=InnoDB;
create table carga (id bigint not null auto_increment, codigo varchar(255), descricao varchar(255), tipo_carga varchar(255), primary key (id)) engine=InnoDB;
create table transportadora (id bigint not null auto_increment, codigo varchar(255), email varchar(255), nome varchar(255), primary key (id)) engine=InnoDB;

alter table agendamento_cargas add constraint UK_id unique (cargas_id);
alter table agendamento_transportadoras add constraint UK_transportadoras_id unique (transportadoras_id);
alter table agendamento_cargas add constraint FK_cargas_id foreign key (cargas_id) references carga (id);
alter table agendamento_cargas add constraint FK_agendamento_id foreign key (agendamento_id) references agendamento (id);
alter table agendamento_transportadoras add constraint FK_transportadoras_id foreign key (transportadoras_id) references transportadora (id);
alter table agendamento_transportadoras add constraint FK_agendamento_id foreign key (agendamento_id) references agendamento (id);
