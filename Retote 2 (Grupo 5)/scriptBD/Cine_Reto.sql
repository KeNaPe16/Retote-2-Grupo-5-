create database cine_reto collate utf8mb4_spanish_ci;

use cine_reto;


create table Sala
(ID_Sala int auto_increment Primary Key,
Nombre varchar(50) not null);

create table Pelicula
(ID_Pelicula int auto_increment Primary Key,
Duracion int not null,
Genero varchar(50),
Precio_Base decimal(4,2) not null,
Nombre_Pelicula varchar(100) not null);

create table Cliente 
(DNI char(9) Primary Key,
Email varchar(50) not null,
Nombre_Apellidos varchar(75) not null,
Contraseña blob not null);

create table Compra
(ID_Compra int auto_increment Primary Key,
Precio_Base decimal(5,2) not null,
Fecha_Hora datetime not null,
Descuento decimal(5,2),
DNI char(9) not null, constraint FK_Compra_Cliente foreign key (DNI) references Cliente(DNI) on delete cascade on update cascade);

create table Sesion 
(ID_Sesion  int auto_increment Primary Key,
Hora_Inicio time not null,
Hora_Fin time not null,
Fecha date not null,
Numero_Espectadores_Actuales int not null,
Precio_Sesion decimal(4,2) not null,
ID_Sala int not null, constraint FK_Sesion_Sala foreign key (ID_Sala) references Sala(ID_Sala) on delete cascade on update cascade,
ID_Pelicula int not null, constraint FK_Sesion_Pelicula foreign key (ID_Pelicula) references Pelicula(ID_Pelicula) on delete cascade on update cascade);

create table Entrada
(ID_Entrada  int auto_increment Primary Key,
Precio_Entrada decimal(4,2) not null,
Descuento decimal(5,2),
Numero_Personas int not null,
ID_Sesion int not null, constraint FK_Entrada_Sesion foreign key (ID_Sesion) references Sesion(ID_Sesion) on delete cascade on update cascade,
ID_Compra int not null, constraint FK_Entrada_Compra foreign key (ID_Compra) references Compra(ID_Compra) on delete cascade on update cascade);


INSERT INTO Sala (Nombre) VALUES
('Sala 1'),('Sala 2'),('Sala 3'),('Sala 4'),('Sala 5');


INSERT INTO Pelicula (Duracion, Genero, Precio_Base, Nombre_Pelicula) VALUES
(120,'Acción',8.50,'Rápidos y Furiosos 10'),
(95,'Comedia',7.00,'La Gran Risa'),
(150,'Drama',9.00,'El Camino Infinito'),
(110,'Terror',6.50,'Noche de Terror'),
(130,'Animación',7.50,'Aventuras en el Bosque'),
(105,'Acción',8.00,'Misión Imposible: Fallout'),
(100,'Comedia',7.50,'Superbad: La Fiesta Continúa'),
(125,'Drama',9.50,'La Lista Infinita'),
(115,'Terror',6.75,'El Refugio del Miedo'),
(90,'Animación',7.25,'La Gran Aventura de Max'),
(140,'Acción',9.00,'Guardianes de la Galaxia Vol.3'),
(95,'Comedia',6.75,'Locuras en la Oficina'),
(130,'Drama',9.00,'Ecos del Pasado'),
(100,'Terror',6.50,'Sombras Nocturnas'),
(110,'Animación',7.00,'Mundo de Colores'),
(125,'Acción',8.50,'El Último Combate'),
(105,'Comedia',7.00,'Risas y Más Risas'),
(135,'Drama',9.50,'Historias Cruzadas'),
(120,'Terror',6.75,'El Laberinto del Miedo'),
(95,'Animación',7.25,'Pequeños Héroes');


INSERT INTO Cliente (DNI, Email, Nombre_Apellidos, Contraseña) VALUES
('12345678A','juan.perez@email.com','Juan Pérez',AES_ENCRYPT('juan123','cineadmin')),
('87654321B','maria.gomez@email.com','María Gómez',AES_ENCRYPT('maria456','cineadmin')),
('45678912C','luis.sanchez@email.com','Luis Sánchez',AES_ENCRYPT('luis789','cineadmin')),
('23456789D','ana.lopez@email.com','Ana López',AES_ENCRYPT('ana321','cineadmin')),
('34567891E','carlos.martin@email.com','Carlos Martín',AES_ENCRYPT('carlos123','cineadmin')),
('56789123F','laura.garcia@email.com','Laura García',AES_ENCRYPT('laura456','cineadmin')),
('67891234G','pablo.rodriguez@email.com','Pablo Rodríguez',AES_ENCRYPT('pablo789','cineadmin'));


INSERT INTO Sesion (Hora_Inicio, Hora_Fin, Fecha, Numero_Espectadores_Actuales, Precio_Sesion, ID_Sala, ID_Pelicula) VALUES

('10:00:00','12:00:00','2026-01-15',50,8.50,1,1),
('12:15:00','14:15:00','2026-01-15',45,7.00,1,2),
('14:30:00','16:45:00','2026-01-15',60,9.00,1,3),
('17:00:00','18:50:00','2026-01-15',55,6.50,1,4),
('19:00:00','21:10:00','2026-01-15',70,7.50,1,5),

('10:00:00','12:00:00','2026-01-15',50,8.50,2,6),
('12:15:00','14:15:00','2026-01-15',45,7.00,2,7),
('14:30:00','16:45:00','2026-01-15',60,9.00,2,8),
('17:00:00','18:50:00','2026-01-15',55,6.50,2,9),
('19:00:00','21:10:00','2026-01-15',70,7.50,2,10),

('10:00:00','12:00:00','2026-01-15',50,8.50,3,11),
('12:15:00','14:15:00','2026-01-15',45,7.00,3,12),
('14:30:00','16:45:00','2026-01-15',60,9.00,3,13),
('17:00:00','18:50:00','2026-01-15',55,6.50,3,14),
('19:00:00','21:10:00','2026-01-15',70,7.50,3,15),

('10:00:00','12:00:00','2026-01-15',50,8.50,4,16),
('12:15:00','14:15:00','2026-01-15',45,7.00,4,17),
('14:30:00','16:45:00','2026-01-15',60,9.00,4,18),
('17:00:00','18:50:00','2026-01-15',55,6.50,4,19),
('19:00:00','21:10:00','2026-01-15',70,7.50,4,20),

('10:00:00','12:00:00','2026-01-15',50,8.50,5,1),
('12:15:00','14:15:00','2026-01-15',45,7.00,5,2),
('14:30:00','16:45:00','2026-01-15',60,9.00,5,3),
('17:00:00','18:50:00','2026-01-15',55,6.50,5,4),
('19:00:00','21:10:00','2026-01-15',70,7.50,5,5);



INSERT INTO Compra (Precio_Base, Fecha_Hora, Descuento, DNI) VALUES
(15.50,'2026-01-15 10:00:00',20.0,'12345678A');

INSERT INTO Entrada (Precio_Entrada, Descuento, Numero_Personas, ID_Sesion, ID_Compra) VALUES
(8.50,20.0,1,1,1),
(7.00,20.0,1,2,1);

INSERT INTO Compra (Precio_Base, Fecha_Hora, Descuento, DNI) VALUES
(22.00,'2026-01-15 12:00:00',30.0,'87654321B');

INSERT INTO Entrada (Precio_Entrada, Descuento, Numero_Personas, ID_Sesion, ID_Compra) VALUES
(7.50,30.0,1,3,2),
(6.50,30.0,1,4,2),
(8.00,30.0,1,5,2);

INSERT INTO Compra (Precio_Base, Fecha_Hora, Descuento, DNI) VALUES
(8.50,'2026-01-15 14:00:00',0.0,'45678912C');

INSERT INTO Entrada (Precio_Entrada, Descuento, Numero_Personas, ID_Sesion, ID_Compra) VALUES
(8.50,0.0,1,6,3);

INSERT INTO Compra (Precio_Base, Fecha_Hora, Descuento, DNI) VALUES
(17.00,'2026-01-15 16:00:00',0.0,'23456789D');

INSERT INTO Entrada (Precio_Entrada, Descuento, Numero_Personas, ID_Sesion, ID_Compra) VALUES
(8.50,0.0,1,7,4),
(8.50,0.0,1,7,4);

INSERT INTO Compra (Precio_Base, Fecha_Hora, Descuento, DNI) VALUES
(25.50,'2026-01-15 18:00:00',30.0,'12345678A');

INSERT INTO Entrada (Precio_Entrada, Descuento, Numero_Personas, ID_Sesion, ID_Compra) VALUES
(8.50,30.0,1,8,5),
(7.25,30.0,1,9,5),
(9.75,30.0,1,10,5);
