--RedacChefDB

create table Titre (
    idTitre int NOT NULL PRIMARY KEY
);

create table Article (
    idArticle int NOT NULL PRIMARY KEY,
    titre varchar(100),
    motscles varchar(100),
    auteur int,
    contenu varchar(1000),
    idTitre int,
    CONSTRAINT fk_article_titre_idTitre FOREIGN KEY (idTitre) REFERENCES Titre(idTitre)
);

--RechercheArchive

create table Titre (
	idTitre int NOT NULL PRIMARY KEY,
	nom varchar(100),
	motscles varchar(100)
);

create table Volume (
	idVolume int NOT NULL PRIMARY KEY,
	termine boolean,
	nom varchar(100),
	numero int,
	idTitre int,
	CONSTRAINT fk_volume_titre_idTitre FOREIGN KEY (idTitre) REFERENCES Titre(idTitre)
);

--MiseSousPresse

create table Titre (
	idTitre int NOT NULL PRIMARY KEY,
	nom varchar(100),
	motscles varchar(100)
);

create table Volume (
	idVolume int NOT NULL PRIMARY KEY,
	termine boolean,
	nom varchar(100),
	numero int,
	idTitre int,
	CONSTRAINT fk_volume_titre_idTitre FOREIGN KEY (idTitre) REFERENCES Titre(idTitre)
);

create table Publicite (
	idPub int NOT NULL PRIMARY KEY,
	contenu varchar(1000),
	image varchar(100)
);

create table Integrer (
	idVolume int NOT NULL,
	idPub int NOT NULL,
	CONSTRAINT pk_integrer_idVolume_idPub PRIMARY KEY (idVolume, idPub),
	CONSTRAINT fk_integrer_volume_idVolume FOREIGN KEY (idVolume) REFERENCES Volume(idVolume),
	CONSTRAINT fk_integrer_publicite_idPub FOREIGN KEY (idPub) REFERENCES Publicite(idPub)
);


--GestionDistributeurs

create table Titre (
	idTitre int NOT NULL PRIMARY KEY,
	nom varchar(100),
	motscles varchar(100)
);

create table Volume (
	idVolume int NOT NULL PRIMARY KEY,
	termine boolean,
	nom varchar(100),
	numero int,
	idTitre int,
	CONSTRAINT fk_volume_titre_idTitre FOREIGN KEY (idTitre) REFERENCES Titre(idTitre)
);

create table Distributeur (
	idDistributeur int NOT NULL PRIMARY KEY
);

create table Abonner (
	idDistributeur int NOT NULL,
	idTitre int NOT NULL,
	nbCopies int,
	cout decimal,
	duree int,
	valide boolean,
	CONSTRAINT pk_abonner PRIMARY KEY (idDistributeur, idTitre),
	CONSTRAINT fk_abonner_distributeur_idDistributeur FOREIGN KEY (idDistributeur) REFERENCES Distributeur(idDistributeur),
	CONSTRAINT fk_abonner_titre_idTitre FOREIGN KEY (idTitre) REFERENCES Titre(idTitre)
);