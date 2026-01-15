

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
('Sala 3'),
('Sala 4'),
('Sala 5'),
('Sala IMAX'),
('Sala VIP'),
('Sala Infantil');

INSERT INTO Pelicula (Duracion, Genero, Precio_Base, Nombre_Pelicula) VALUES
(120, 'Acción', 7.50, 'Misión Final'),
(95, 'Comedia', 6.50, 'Risas sin Fin'),
(140, 'Drama', 8.00, 'El Último Viaje'),
(110, 'Terror', 7.00, 'La Casa Oscura'),
(130, 'Ciencia Ficción', 8.50, 'Galaxia Perdida'),
(100, 'Animación', 6.00, 'El Dragón Azul'),
(150, 'Épica', 9.00, 'Reinos en Guerra'),
(105, 'Romance', 6.75, 'Amor en París');

INSERT INTO Cliente (DNI, Email, Nombre_Apellidos, Contraseña) VALUES
('12345678A', 'ana@email.com', 'Ana López García', AES_ENCRYPT('Ana1234', 'cineadmin')),
('23456789B', 'juan@email.com', 'Juan Pérez Ruiz', AES_ENCRYPT('Juan5678', 'cineadmin')),
('34567890C', 'maria@email.com', 'María Sánchez Gil', AES_ENCRYPT('Maria2025', 'cineadmin')),
('45678901D', 'luis@email.com', 'Luis Martín Torres', AES_ENCRYPT('LuisPass', 'cineadmin')),
('56789012E', 'laura@email.com', 'Laura Gómez Díaz', AES_ENCRYPT('Laura#99', 'cineadmin')),
('67890123F', 'carlos@email.com', 'Carlos Romero Vega', AES_ENCRYPT('Carlos456', 'cineadmin')),
('78901234G', 'elena@email.com', 'Elena Navarro León', AES_ENCRYPT('ElenaKey', 'cineadmin')),
('89012345H', 'david@email.com', 'David Molina Cruz', AES_ENCRYPT('David777', 'cineadmin'));

INSERT INTO Compra (Precio_Base, Fecha_Hora, Descuento, DNI) VALUES
(15.00, '2025-01-10 18:30:00', 20,  '12345678A'),
(12.00, '2025-01-11 19:00:00', NULL,'23456789B'),
(20.00, '2025-01-12 20:15:00', 30,  '34567890C'),
(10.00, '2025-01-13 17:45:00', NULL,'45678901D'),
(18.00, '2025-01-14 21:00:00', 30,  '56789012E'),
(14.00, '2025-01-15 18:00:00', 20,  '67890123F'),
(16.00, '2025-01-16 20:30:00', NULL,'78901234G'),
(22.00, '2025-01-17 22:00:00', 30,  '89012345H');

INSERT INTO Sesion 
(Hora_Inicio, Hora_Fin, Fecha, Numero_Espectadores_Actuales, Precio_Sesion, ID_Sala, ID_Pelicula) VALUES
('16:00:00', '18:00:00', '2025-01-20', 50, 7.50, 1, 1),
('18:30:00', '20:05:00', '2025-01-20', 60, 6.50, 2, 2),
('20:00:00', '22:20:00', '2025-01-20', 45, 8.00, 3, 3),
('22:30:00', '00:20:00', '2025-01-20', 30, 7.00, 4, 4),
('17:00:00', '19:10:00', '2025-01-21', 70, 8.50, 5, 5),
('16:30:00', '18:10:00', '2025-01-21', 80, 6.00, 8, 6),
('19:00:00', '21:30:00', '2025-01-21', 40, 9.00, 6, 7),
('21:00:00', '22:45:00', '2025-01-21', 55, 6.75, 7, 8);

INSERT INTO Entrada 
(Precio_Entrada, Descuento, Numero_Personas, ID_Sesion, ID_Compra) VALUES
(7.50, 20,   2, 1, 1),
(6.50, NULL, 1, 2, 2),
(8.00, 30,   3, 3, 3),
(7.00, NULL, 1, 4, 4),
(8.50, 20,   2, 5, 5),
(6.00, 30,   4, 6, 6),
(9.00, 20,   2, 7, 7),
(6.75, 30,   3, 8, 8);



