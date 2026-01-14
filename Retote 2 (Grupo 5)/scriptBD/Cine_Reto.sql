
create database cine_reto collate utf8mb4_spanish_ci;
grant all on cine_reto.* to daw;

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
('Sala 1'),
('Sala 2'),
('Sala 3');

INSERT INTO Pelicula (Duracion, Genero, Precio_Base, Nombre_Pelicula) VALUES
(120, 'Acción', 8.50, 'Misión Final'),
(95, 'Comedia', 7.00, 'Risas sin Fin'),
(140, 'Drama', 9.00, 'El Último Viaje');

INSERT INTO Cliente (DNI, Email, Nombre_Apellidos, Contraseña) VALUES
('12345678A', 'juan@email.com', 'Juan Pérez', AES_ENCRYPT('Juan1234', 'cineadmin')),
('87654321B', 'ana@email.com', 'Ana García', AES_ENCRYPT('Ana5678', 'cineadmin')),
('11223344C', 'luis@email.com', 'Luis Martínez', AES_ENCRYPT('Luis91011', 'cineadmin'));

INSERT INTO Sesion 
(Hora_Inicio, Hora_Fin, Fecha, Numero_Espectadores_Actuales, Precio_Sesion, ID_Sala, ID_Pelicula)
VALUES
('16:00:00', '18:00:00', '2026-01-15', 50, 8.50, 1, 1),
('18:30:00', '20:05:00', '2026-01-15', 40, 7.00, 2, 2),
('21:00:00', '23:20:00', '2026-01-15', 60, 9.00, 3, 3);

INSERT INTO Compra 
(Precio_Base, Fecha_Hora, Descuento, DNI)
VALUES
(17.00, '2026-01-14 15:30:00', 2.00, '12345678A'),
(9.00, '2026-01-14 17:45:00', NULL, '87654321B'),
(18.00, '2026-01-14 20:10:00', 3.00, '11223344C');

INSERT INTO Entrada 
(Precio_Entrada, Descuento, Numero_Personas, ID_Sesion, ID_Compra)
VALUES
(8.50, 1.00, 2, 1, 1),
(7.00, NULL, 1, 2, 2),
(9.00, 1.50, 2, 3, 3);

