create table USUARIO(
	ID SERIAL primary key,
	NOME VARCHAR(50),
	EMAIL VARCHAR(50),
	SENHA VARCHAR(50)

);


create table MUSICA(
	ID SERIAL primary key,
	NOME VARCHAR(50),
	DESCRICAO VARCHAR(500),
	ARQUIVO VARCHAR(500)
);


create table ETIQUETA(
	ID SERIAL primary key,
	NOME VARCHAR(50)
);


create table COMENTARIO(
	ID SERIAL primary key,
	CONTEUDO VARCHAR(500),
	DATA DATE,
	USUARIO_ID INTEGER,
	MUSICA_ID INTEGER,
	constraint COMENTARIO_USUARIO_FK foreign key (USUARIO_ID)
		references USUARIO (ID)
		on update cascade
		on delete no ACTION,

constraint COMENTARIO_MUSICA_FK foreign key (MUSICA_ID)
		references MUSICA (ID)
		on update cascade
		on delete no ACTION
	

);



create table ETIQUETA_MUSICA(
	PESO INTEGER,
	ETIQUETA_ID INTEGER,
	MUSICA_ID INTEGER,
	
	Constraint ETIQUETA_MUSICA_PK primary key (ETIQUETA_ID, MUSICA_ID),		
	
	Constraint EM_ETIQUETA_FK foreign key (ETIQUETA_ID)
		references ETIQUETA (ID)
		on update cascade
		on delete CASCADE,

constraint EM_MUSICA_FK foreign key (MUSICA_ID)
		references MUSICA (ID)
		on update cascade
		on delete CASCADE
	

);



create table ETIQUETA_USUARIO(
	ETIQUETA_ID INTEGER,
	USUARIO_ID INTEGER,
	PESO integer,
	
	Constraint ETIQUETA_USUARIO_PK primary key (ETIQUETA_ID, USUARIO_ID),		
	
	Constraint EU_ETIQUETA_FK foreign key (ETIQUETA_ID)
		references ETIQUETA (ID)
		on update cascade
		on delete CASCADE,

constraint EU_USUARIO_FK foreign key (USUARIO_ID)
		references USUARIO(ID)
		on update cascade
		on delete CASCADE
	

);

create table MUSICA_USUARIO(
	MUSICA_ID INTEGER,
	USUARIO_ID INTEGER,
	
	Constraint MUSICA_USUARIO_PK primary key (MUSICA_ID, USUARIO_ID),		
	
	Constraint MU_MUSICA_FK foreign key (MUSICA_ID)
		references MUSICA (ID)
		on update cascade
		on delete CASCADE,

constraint MU_USUARIO_FK foreign key (USUARIO_ID)
		references USUARIO(ID)
		on update cascade
		on delete cascade
	

);



