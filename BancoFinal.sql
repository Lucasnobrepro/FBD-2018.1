
Create table Musica(
	id serial,
	nome varchar(50),
	cantor varchar(50),
	genero varchar(50),
constraint IDMusica primary key (id)
);

Create table Usuario(
	nome varchar(50),
	id serial,
Constraint IDuser primary key (id)
);

Create table playList(
	idUser int,
	idMusc int,

constraint PL primary key (idUser,idMusc), 
constraint P2 foreign key (idUser) references Usuario(id),foreign key (idMusc) references musica(id)
);

