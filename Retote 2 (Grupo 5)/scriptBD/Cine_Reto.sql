create database cine_reto collate utf8mb4_spanish_ci;
grant all on cine_reto.* to daw;

use cine_reto;


create table Sala
(ID_Sala int auto_increment Primary Key,
Nombre varchar(50) not null);

create table Pelicula
(ID_Pelicula int auto_increment Primary Key,
Duracion int not null check (Duracion >= 0),
Genero varchar(50),
Precio_Base decimal(4,2) not null check(Precio_Base >= 0),
Nombre_Pelicula varchar(100) not null);

create table Cliente 
(DNI char(9) Primary Key,
Email varchar(50) not null check (Email like '%_@_%._%'),
Nombre_Apellidos varchar(75) not null,
Contraseña blob not null);

create table Compra
(ID_Compra int auto_increment Primary Key,
Precio_Compra decimal(5,2) not null check (Precio_Compra >= 0),
Fecha_Hora datetime not null,
Descuento decimal(5,2) check (Descuento >= 0 ),
DNI char(9) not null, constraint FK_Compra_Cliente foreign key (DNI) references Cliente(DNI) on delete cascade on update cascade);

create table Sesion 
(ID_Sesion  int auto_increment Primary Key,
Hora_Inicio time not null,
Hora_Fin time not null, constraint Horas_Bien check (Hora_Fin > Hora_Inicio),
Fecha date not null, constraint sala_sesion_hora unique (id_sala, fecha, hora_inicio),
Numero_Espectadores_Actuales int not null  check (Numero_Espectadores_Actuales >= 0),
Precio_Sesion decimal(4,2) not null check (Precio_Sesion >= 0),
ID_Sala int not null, constraint FK_Sesion_Sala foreign key (ID_Sala) references Sala(ID_Sala) on delete cascade on update cascade,
ID_Pelicula int not null, constraint FK_Sesion_Pelicula foreign key (ID_Pelicula) references Pelicula(ID_Pelicula) on delete cascade on update cascade);

create table Entrada
(ID_Entrada  int auto_increment Primary Key,
Precio_Entrada decimal(4,2) not null check (Precio_Entrada >= 0),
Descuento decimal(5,2) check (Descuento >= 0),
Numero_Personas int not null check (Numero_Personas > 0),
ID_Sesion int not null, constraint FK_Entrada_Sesion foreign key (ID_Sesion) references Sesion(ID_Sesion) on delete cascade on update cascade,
ID_Compra int not null, constraint FK_Entrada_Compra foreign key (ID_Compra) references Compra(ID_Compra) on delete cascade on update cascade);

INSERT INTO Sala (Nombre) VALUES
('Sala 1 - IMAX'),
('Sala 2 - 3D'),
('Sala 3 - VIP'),
('Sala 4 - Estándar'),
('Sala 5 - Infantil'),
('Sala 6 - Clásicos'),
('Sala 7 - Premium'),
('Sala 8 - 4DX'),
('Sala 9 - Estándar 2'),
('Sala 10 - Estándar 3'),
('Sala 11 - VIP 2'),
('Sala 12 - IMAX 2');



INSERT INTO Pelicula (Duracion, Genero, Precio_Base, Nombre_Pelicula) VALUES
(120,'Acción',12.50,'Furia en la ciudad'),
(95,'Comedia',8.50,'Risas garantizadas'),
(110,'Drama',10.00,'El último adiós'),
(130,'Acción',11.00,'Misión imposible 6'),
(105,'Terror',9.50,'La casa embrujada'),
(125,'Comedia',8.00,'Vacaciones locas'),
(115,'Drama',10.50,'Corazones rotos'),
(90,'Infantil',7.00,'El viaje de Lilo'),
(140,'Acción',13.00,'Velocidad extrema'),
(100,'Comedia',9.00,'Amigos para siempre'),
(135,'Drama',11.50,'Sombras del pasado'),
(105,'Terror',9.50,'Noche sin fin'),
(110,'Acción',12.00,'Operación rescate'),
(95,'Comedia',7.50,'Fiesta sorpresa'),
(125,'Drama',11.00,'Entre dos mundos'),
(100,'Infantil',7.50,'Magia en el bosque'),
(115,'Acción',12.50,'Asalto final'),
(105,'Comedia',8.50,'Locuras urbanas'),
(120,'Drama',10.00,'El precio de la verdad'),
(130,'Acción',13.50,'Guerreros del aire'),
(90,'Infantil',6.50,'El reino de los juguetes'),
(100,'Terror',9.00,'El espejo maldito'),
(110,'Comedia',8.00,'Risas sin fin'),
(120,'Drama',11.00,'Almas perdidas'),
(115,'Acción',12.50,'Contraataque'),
(105,'Terror',9.50,'Pesadilla en la ciudad'),
(95,'Comedia',7.50,'Humor a la carta'),
(130,'Drama',11.50,'La decisión final'),
(125,'Acción',12.50,'Misión rescate'),
(100,'Infantil',7.00,'El pequeño héroe'),
(105,'Comedia',8.50,'Fiesta de locos'),
(110,'Drama',10.50,'Secretos del alma'),
(120,'Acción',13.00,'Riesgo total'),
(95,'Comedia',7.50,'Risas compartidas'),
(115,'Terror',9.50,'Oscuridad eterna'),
(105,'Infantil',6.50,'Aventuras de Pipo');



INSERT INTO Cliente (DNI, Email, Nombre_Apellidos, Contraseña) VALUES
('12345678A','juan.perez@example.com','Juan Pérez',AES_ENCRYPT('pass123','cineadmin')),
('23456789B','maria.gomez@example.com','María Gómez',AES_ENCRYPT('maria2026','cineadmin')),
('34567890C','luis.lopez@example.com','Luis López',AES_ENCRYPT('luispass','cineadmin')),
('45678901D','ana.sanchez@example.com','Ana Sánchez',AES_ENCRYPT('anaspass','cineadmin')),
('56789012E','carlos.ramirez@example.com','Carlos Ramírez',AES_ENCRYPT('carlitos','cineadmin')),
('67890123F','laura.martinez@example.com','Laura Martínez',AES_ENCRYPT('lauram','cineadmin')),
('78901234G','jorge.fernandez@example.com','Jorge Fernández',AES_ENCRYPT('jorgepass','cineadmin')),
('89012345H','sofia.garcia@example.com','Sofía García',AES_ENCRYPT('sofiapass','cineadmin')),
('90123456J','pablo.torres@example.com','Pablo Torres',AES_ENCRYPT('pablot','cineadmin')),
('01234567K','marta.alvarez@example.com','Marta Álvarez',AES_ENCRYPT('martita','cineadmin')),
('11223344L','diego.molina@example.com','Diego Molina',AES_ENCRYPT('diego123','cineadmin')),
('22334455M','clara.romero@example.com','Clara Romero',AES_ENCRYPT('clara456','cineadmin')),
('33445566N','felipe.vargas@example.com','Felipe Vargas',AES_ENCRYPT('felipe789','cineadmin')),
('44556677P','isabel.fuentes@example.com','Isabel Fuentes',AES_ENCRYPT('isabel2026','cineadmin')),
('55667788Q','ricardo.suarez@example.com','Ricardo Suárez',AES_ENCRYPT('ricardo321','cineadmin')),
('66778899R','valeria.castillo@example.com','Valeria Castillo',AES_ENCRYPT('valeria987','cineadmin')),
('77889900S','andres.mejia@example.com','Andrés Mejía',AES_ENCRYPT('andres555','cineadmin')),
('88990011T','elena.cabrera@example.com','Elena Cabrera',AES_ENCRYPT('elena666','cineadmin')),
('99001122U','raul.moreno@example.com','Raúl Moreno',AES_ENCRYPT('raul777','cineadmin')),
('10111213V','susana.soto@example.com','Susana Soto',AES_ENCRYPT('susana888','cineadmin')),
('20212223W','miriam.lopez@example.com','Miriam López',AES_ENCRYPT('miriam111','cineadmin')),
('21222324X','adrian.martin@example.com','Adrián Martín',AES_ENCRYPT('adrian222','cineadmin')),
('22232425Y','laura.rodriguez@example.com','Laura Rodríguez',AES_ENCRYPT('laura333','cineadmin')),
('23242526Z','david.mendez@example.com','David Méndez',AES_ENCRYPT('david444','cineadmin')),
('24252627A','patricia.ramos@example.com','Patricia Ramos',AES_ENCRYPT('patricia555','cineadmin')),
('25262728B','daniel.soto@example.com','Daniel Soto',AES_ENCRYPT('daniel666','cineadmin')),
('26272829C','carolina.vargas@example.com','Carolina Vargas',AES_ENCRYPT('carolina777','cineadmin')),
('27282930D','fernando.suarez@example.com','Fernando Suárez',AES_ENCRYPT('fernando888','cineadmin')),
('28293031E','angela.gonzalez@example.com','Ángela González',AES_ENCRYPT('angela999','cineadmin')),
('29303132F','javier.torres@example.com','Javier Torres',AES_ENCRYPT('javier000','cineadmin')),
('30313233G','monica.morales@example.com','Mónica Morales',AES_ENCRYPT('monica111','cineadmin')),
('31323334H','sergio.martinez@example.com','Sergio Martínez',AES_ENCRYPT('sergio222','cineadmin')),
('32333435J','paula.molina@example.com','Paula Molina',AES_ENCRYPT('paula333','cineadmin')),
('33343536K','alfonso.rodriguez@example.com','Alfonso Rodríguez',AES_ENCRYPT('alfonso444','cineadmin')),
('34353637L','soledad.perez@example.com','Soledad Pérez',AES_ENCRYPT('soledad555','cineadmin')),
('35363738M','antonio.ramos@example.com','Antonio Ramos',AES_ENCRYPT('antonio666','cineadmin')),
('36373839N','silvia.fuentes@example.com','Silvia Fuentes',AES_ENCRYPT('silvia777','cineadmin')),
('37383940P','miguel.cabrera@example.com','Miguel Cabrera',AES_ENCRYPT('miguel888','cineadmin')),
('38394041Q','alicia.lopez@example.com','Alicia López',AES_ENCRYPT('alicia999','cineadmin')),
('39304142R','jorge.martinez@example.com','Jorge Martínez',AES_ENCRYPT('jorge111','cineadmin')),
('40314243S','cristina.torres@example.com','Cristina Torres',AES_ENCRYPT('cristina222','cineadmin')),
('41324344T','felix.rodriguez@example.com','Félix Rodríguez',AES_ENCRYPT('felix333','cineadmin')),
('42334445U','beatriz.moreno@example.com','Beatriz Moreno',AES_ENCRYPT('beatriz444','cineadmin')),
('43344546V','ignacio.garcia@example.com','Ignacio García',AES_ENCRYPT('ignacio555','cineadmin')),
('44354647W','ana.martin@example.com','Ana Martín',AES_ENCRYPT('ana666','cineadmin')),
('45364748X','oscar.lopez@example.com','Óscar López',AES_ENCRYPT('oscar777','cineadmin')),
('46374849Y','lidia.ramirez@example.com','Lidia Ramírez',AES_ENCRYPT('lidia888','cineadmin')),
('47384950Z','raul.gonzalez@example.com','Raúl González',AES_ENCRYPT('raul999','cineadmin')),
('48395051A','marta.suarez@example.com','Marta Suárez',AES_ENCRYPT('marta111','cineadmin')),
('49305152B','jose.martinez@example.com','José Martínez',AES_ENCRYPT('jose222','cineadmin')),
('50315253C','veronica.cabrera@example.com','Verónica Cabrera',AES_ENCRYPT('veronica333','cineadmin')),
('51325354D','daniela.rodriguez@example.com','Daniela Rodríguez',AES_ENCRYPT('daniela444','cineadmin')),
('52335455E','miguel.lopez@example.com','Miguel López',AES_ENCRYPT('miguel555','cineadmin')),
('53345556F','sofia.martinez@example.com','Sofía Martínez',AES_ENCRYPT('sofia666','cineadmin')),
('54355657G','eduardo.gomez@example.com','Eduardo Gómez',AES_ENCRYPT('eduardo777','cineadmin')),
('55365758H','marcela.torres@example.com','Marcela Torres',AES_ENCRYPT('marcela888','cineadmin')),
('56375859J','victor.ramirez@example.com','Víctor Ramírez',AES_ENCRYPT('victor999','cineadmin')),
('57385960K','paulina.lopez@example.com','Paulina López',AES_ENCRYPT('paulina111','cineadmin')),
('58396061L','alberto.soto@example.com','Alberto Soto',AES_ENCRYPT('alberto222','cineadmin'));

INSERT INTO Sesion (Hora_Inicio, Hora_Fin, Fecha, Numero_Espectadores_Actuales, Precio_Sesion, ID_Sala, ID_Pelicula) VALUES

('10:00:00','12:00:00','2026-01-19',3,17.00,1,1), -- 1 dinero = 17
('11:00:00','13:00:00','2026-01-19',3,7.20,2,2), -- 2 dinero = 7.2
('12:00:00','14:00:00','2026-01-19',6,10.40,3,3), -- 3 dinero = 10.40
('13:00:00','15:00:00','2026-01-19',6,18.00,4,4), -- 4 dinero = 18
('14:00:00','16:00:00','2026-01-19',3,13.00,5,5), -- 5 dinero = 13
('15:00:00','17:00:00','2026-01-19',3,6.50,6,6), -- 6 dinero = 6.5
('16:00:00','18:00:00','2026-01-19',2,7.20,7,7), -- 7 dinero = 7.2
('17:00:00','19:00:00','2026-01-19',4,10.80,8,8), -- 8 dinero = 10.8
('18:00:00','20:00:00','2026-01-19',3,6.40,9,9), -- 9 dinero = 6.4
('19:00:00','21:00:00','2026-01-19',3,8.00,10,10), -- 10 dinero = 8
('10:30:00','12:30:00','2026-01-20',2,7.70,1,11), -- 11 dinero = 7.7
('11:30:00','13:30:00','2026-01-20',2,9.50,2,12), -- 12 dinero = 9.5
('12:30:00','14:30:00','2026-01-20',4,8.70,3,13), -- 13 dinero = 8.7
('13:30:00','15:30:00','2026-01-20',2,12.50,4,14), -- 14 dinero = 12.5
('14:30:00','16:30:00','2026-01-20',3,14.30,5,15), -- 15 dinero = 14.3
('15:30:00','17:30:00','2026-01-20',3,14.30,6,16), -- 16 dinero = 14.3
('16:30:00','18:30:00','2026-01-20',2,10.40,7,17), -- 17 dinero = 10.4
('17:30:00','19:30:00','2026-01-20',4,7.80,8,18), -- 18 dinero = 7.8
('18:30:00','20:30:00','2026-01-20',2,7.80,9,19), -- 19 dinero = 7.8
('19:30:00','21:30:00','2026-01-20',2,7.80,10,20), -- 20 dinero = 7.8
('10:00:00','12:00:00','2026-01-21',1,7.80,1,21), -- 21 dinero = 7.8
('11:00:00','13:00:00','2026-01-21',2,13.60,2,23), -- 22 dinero = 13.6
('12:00:00','14:00:00','2026-01-21',3,13.60,3,24), -- 23 dinero = 13.6
('13:00:00','15:00:00','2026-01-21',1,9.45,4,25), -- 24 dinero = 9.45
('14:00:00','16:00:00','2026-01-21',1,11.50,5,26), -- 25 dinero = 11.5
('15:00:00','17:00:00','2026-01-21',2,12.50,6,27), -- 26 dinero = 12.5 
('16:00:00','18:00:00','2026-01-21',3,13.00,7,28), -- 27 dinero = 13
('17:00:00','19:00:00','2026-01-21',1,12.90,8,29), -- 28 dinero = 12.9
('18:00:00','20:00:00','2026-01-21',1,12.90,9,30), -- 29 dinero = 12.9
('19:00:00','21:00:00','2026-01-21',1,13.00,10,31), -- 30 dinero = 13
('20:00:00','22:00:00','2026-01-21',1,13.50,1,32), -- 31 dinero = 13.5
('10:30:00','12:30:00','2026-01-22',2,12.00,2,33), -- 32 dinero = 12
('11:30:00','13:30:00','2026-01-22',1,7.50,3,22), -- 33 dinero = 7.5
('12:30:00','14:30:00','2026-01-22',1,10.50,4,29), -- 34 dinero = 10.5
('13:30:00','15:30:00','2026-01-22',2,9.50,5,23), -- 35 dinero = 9.5
('14:30:00','16:30:00','2026-01-22',3,8.50,6,12), -- 36 dinero = 8.5
('15:30:00','17:30:00','2026-01-22',2,12.50,7,5), -- 37 dinero = 12.5
('16:30:00','18:30:00','2026-01-22',2,7.80,8,7), -- 38 dinero = 7.8
('17:30:00','19:30:00','2026-01-22',2,10.50,9,7), -- 39 dinero = 10.5
('18:30:00','20:30:00','2026-01-22',1,8.50,10,9), -- 40 dinero = 8.5
('19:30:00','21:30:00','2026-01-22',2,9.00,1,8), -- 41 dinero = 9
('20:00:00','22:00:00','2026-01-22',1,7.80,2,7), -- 42 dinero = 7.8
('11:00:00','13:00:00','2026-01-10',3,17.00,5,1), -- Sesiones de prueba para programacion
('11:00:00','13:00:00','2026-01-09',3,17.00,6,1),
('11:00:00','13:00:00','2026-01-18',3,17.00,7,1),
('11:00:00','13:00:00','2026-01-01',3,17.00,8,1);

INSERT INTO Compra (Precio_Compra, Fecha_Hora, Descuento, DNI) VALUES

(17.00,'2026-01-19 09:55:00',0.00,'12345678A'),  -- 1
(35.00,'2026-01-19 11:15:00',20.00,'12345678A'), -- 2
(29.00,'2026-01-19 13:30:00',30.00,'12345678A'), -- 3
(9.50,'2026-01-19 10:00:00',0.00,'23456789B'), -- 4
(20.00,'2026-01-19 12:00:00',20.00,'23456789B'), -- 5 
(11.00,'2026-01-19 10:30:00',0.00,'34567890C'), -- 6
(10.50,'2026-01-19 11:00:00',0.00,'45678901D'), -- 7
(17.50,'2026-01-19 13:00:00',20.00,'45678901D'), -- 8
(8.00,'2026-01-19 10:15:00',0.00,'56789012E'), -- 9
(11.50,'2026-01-19 09:50:00',0.00,'67890123F'), -- 10
(18.50,'2026-01-19 11:20:00',20.00,'67890123F'), -- 11
(29.00,'2026-01-19 13:50:00',30.00,'67890123F'), -- 12
(9.00,'2026-01-19 10:10:00',0.00,'78901234G'), -- 13
(20.30,'2026-01-19 12:40:00',20.00,'78901234G'), -- 14
(7.50,'2026-01-19 09:40:00',0.00,'89012345H'), -- 15
(10.00,'2026-01-19 10:20:00',0.00,'90123456J'), -- 16
(20.50,'2026-01-19 12:00:00',20.00,'90123456J'), -- 17
(27.80,'2026-01-19 14:20:00',30.00,'90123456J'), -- 18
(11.50,'2026-01-19 10:05:00',0.00,'01234567K'), -- 19
(9.50,'2026-01-19 09:55:00',0.00,'11223344L'), -- 20
(18.30,'2026-01-19 12:05:00',20.00,'11223344L'), -- 21
(8.00,'2026-01-19 10:30:00',0.00,'22334455M'), -- 22
(7.50,'2026-01-19 09:50:00',0.00,'33445566N'), -- 23
(17.50,'2026-01-19 11:50:00',20.00,'33445566N'), -- 24
(12.00,'2026-01-19 10:15:00',0.00,'44556677P'), -- 25
(16.50,'2026-01-19 12:15:00',20.00,'44556677P'), -- 26
(31.50,'2026-01-19 14:45:00',30.00,'44556677P'), -- 27
(10.00,'2026-01-19 09:40:00',0.00,'55667788Q'), -- 28
(12.50,'2026-01-19 10:10:00',0.00,'66778899R'), -- 29
(17.50,'2026-01-19 12:10:00',20.00,'66778899R'), -- 30
(11.00,'2026-01-19 09:50:00',0.00,'77889900S'), -- 31
(8.50,'2026-01-19 10:30:00',0.00,'88990011T'), -- 32
(9.00,'2026-01-19 10:00:00',0.00,'99001122U'), -- 33
(10.50,'2026-01-19 10:15:00',0.00,'10111213V'), -- 34
(17.30,'2026-01-19 12:00:00',20.00,'10111213V'), -- 35
(12.00,'2026-01-18 10:00:00',0,'12345678A'),  -- Compra 36, 1 película
(15.60,'2026-01-18 10:30:00',20,'23456789B'), -- Compra 37, 2 películas
(28.50,'2026-01-18 11:00:00',30,'34567890C'), -- Compra 38, 3 películas
(18.00,'2026-01-19 09:30:00',0,'45678901D'),  -- Compra 39, 1 película
(19.50,'2026-01-19 10:00:00',20,'56789012E'), -- Compra 40, 2 películas
(10.50,'2026-01-19 11:00:00',0,'67890123F'),  -- Compra 41, 1 película
(16.00,'2026-01-20 09:45:00',20,'78901234G'), -- Compra 42, 2 películas
(28.50,'2026-01-20 10:15:00',30,'89012345H'), -- Compra 43, 3 películas
(9.50,'2026-01-20 11:00:00',0,'90123456J'),   -- Compra 44, 1 película
(20.00,'2026-01-20 12:00:00',30,'01234567K'); -- Compra 45, 3 películas

-- =========================
-- ENTRADAS CORRESPONDIENTES A TODAS LAS COMPRAS
-- =========================


INSERT INTO Entrada (Precio_Entrada, Descuento, Numero_Personas, ID_Sesion, ID_Compra) VALUES
(17.00, 0.00, 1, 1, 1),   -- 1 persona, Precio_Sesion=17
(14.40, 20.00, 2, 2, 2),  -- 2 personas, Precio_Sesion=7.2 → 2*7.2=14.4
(31.20, 20.00, 3, 3, 2),  -- 3 personas, Precio_Sesion=10.4 → 3*10.4=31.2
(72.00, 20.00, 4, 4, 2),  -- 4 personas, Precio_Sesion=18 → 4*18=72
(13.00, 30.00, 1, 5, 3),  -- 1 persona
(13.00, 30.00, 2, 6, 3),  -- 2 personas, Precio_Sesion=6.5 → 2*6.5=13
(7.20, 0.00, 1, 7, 4),    -- 1 persona
(32.40, 0.00, 3, 8, 5),   -- 3 personas, Precio_Sesion=10.8 → 3*10.8=32.4
(12.80, 20.00, 2, 9, 6),  -- 2 personas, Precio_Sesion=6.4 → 2*6.4=12.8
(8.00, 0.00, 1, 10, 7),   -- 1 persona
(7.70, 0.00, 1, 11, 8),   -- 1 persona
(9.50, 0.00, 1, 12, 9),   -- 1 persona
(17.40, 20.00, 2, 13, 10), -- 2 personas, Precio_Sesion=8.7 → 2*8.7=17,4
(12.50, 30.00, 1, 14, 11),-- 1 persona
(14.30, 20.00, 2, 15, 12),-- 2 personas, Precio_Sesion=7.15 → 2*7.15=14.3
(14.30, 0.00, 1, 16, 13), -- 1 persona
(10.40, 2.00, 1, 17, 14), -- 1 persona
(15.60, 20.00, 2, 18, 15), -- 2 personas, Precio_Sesion=7.8 → 2*7.8=15,6
(7.80, 0.00, 1, 19, 16),  -- 1 persona
(15.60, 20.00, 2, 20, 17), -- 2 personas, Precio_Sesion=7.8 → 2*7.8=15,6
(13.60, 30.00, 1, 22, 18), -- 1 persona
(27.20, 30.00, 2, 23, 18), -- 2 personas, Precio_Sesion=4.75 → 2*13.6=27.2
(18.90, 30.00, 3, 24, 18),-- 3 personas, Precio_Sesion=3.5 → 3*9.45=18.9
(11.50, 0.00, 1, 25, 19), -- 1 persona
(12.50, 0.00, 1, 26, 20),  -- 1 persona
(26.00, 20.00, 2, 27, 21),-- 2 personas, Precio_Sesion=6.5 → 2*13=26
(25.80, 20.00, 2, 28, 21),-- 2 personas, Precio_Sesion=5.25 → 2*12.9=25.8
(12.90, 0.00, 1, 29, 22),  -- 1 persona
(13.00, 0.00, 1, 30, 23),  -- 1 persona
(19.00, 20.00, 2, 31, 24),-- 2 personas, Precio_Sesion=9.5 → 2*9.5=19
(24.00, 20.00, 2, 32, 24),-- 2 personas, Precio_Sesion=8 → 2*12=24
(7.50, 0.00, 1, 33, 25), -- 1 persona
(21.00, 20.00, 2, 34, 26),-- 2 personas, Precio_Sesion=7.5 → 2*10.5=21
(19.00, 20.00, 2, 35, 26),-- 2 personas, Precio_Sesion=9 → 2*9.5=19
(25.50, 30.00, 3, 36, 27),-- 3 personas, Precio_Sesion=10.5 → 3*8.5=25.5
(37.50, 30.00, 3, 37, 27),-- 3 personas, Precio_Sesion=8.5 → 3*12.5=37.5
(23.40, 30.00, 3, 38, 27),-- 3 personas, Precio_Sesion=12.5 → 3*7.8=23.4
(10.50, 0.00, 1, 39, 28), -- 1 persona
(25.00, 0.00, 2, 40, 29), -- 2 personas, Precio_Sesion=12.5 → 2*8.5=17
(18.00, 20.00, 2, 41, 30), -- 2 personas, Precio_Sesion=9 → 2*9=18
(17.00, 20.00, 2, 42, 30), -- 2 personas, Precio_Sesion=8.5 → 2*7.8=15.6
(17.00, 0.00, 1, 1, 31), -- 1 persona
(17.00, 0.00, 1, 1, 32),  -- 1 persona
(7.20, 0.00, 1, 2, 33),  -- 1 persona
(31.50, 0.00, 3, 3, 34), -- 3 personas, Precio_Sesion=10.5 → 3*10.5=31.5
(36.00, 20.00, 2, 4, 35), -- 2 personas
(26.00, 20.00, 2, 5, 35), -- 2 personas, Precio_Sesion=7.8 → 2*13=26
(13.00,0.00,2,6,36),  -- Compra 36
(14.40,20.00,2,7,37),  -- 2 personas × 7.2
(10.80,20.00,1,8,37), -- 1 persona × 10.80 
(6.40,30.00,1,9,38), -- 1 persona × 6.40
(16.00,30.00,2,10,38),-- 2 personas × 8.00
(7.70,30.00,1,11,38), -- 1 persona × 7.70
(9.50,0.00,1,12,39), -- 1 persona × 10.50
(17.40,20.00,2,13,40),-- 2 personas × 8.70
(12.50,20.00,1,14,40),-- 1 persona × 12.50
(14.30,0.00,1,15,41),  -- 1 persona × 14.3
(28.60,20.00,2,16,42),-- 2 personas × 14.3
(10.40,20.00,1,17,42),-- 1 persona × 10.40
(7.80,30.00,1,18,43), -- 1 persona × 7.80
(15.60,30.00,2,19,43), -- 2 personas × 7.8
(7.80,30.00,1,20,43),-- 1 persona × 7.80 
(7.80,0.00,1,21,44), -- 1 persona × 7.80
(27.20,20.00,2,22,45),-- 2 personas × 13.60
(13.60,20.00,1,23,45),-- 1 persona × 13.60
(9.45,30.00,1,24,45),-- 1 persona × 9.45
(11.50,0.00,1,25,36),  -- 1 persona × 11.50
(25.00,20.00,2,26,37),-- 2 personas × 12.50
(13.00,20.00,1,27,38),-- 1 persona × 13.00
(12.90,30.00,1,28,39),-- 1 persona × 12.90
(25.80,30.00,2,29,40),-- 2 personas × 12.90
(13.00,0.00,1,30,41),  -- 1 persona × 13
(13.50,20.00,1,31,42), -- 1 persona × 13.50
(24.00,30.00,2,32,43),-- 2 personas × 12.00
(7.50,30.00,1,33,44), -- 1 persona × 7.50
(10.50,0.00,1,34,45),  -- 1 persona × 10.50
(9.50,20.00,1,35,36), -- 1 persona × 9.50
(17.00,20.00,2,36,37),-- 2 personas × 8.50
(12.50,30.00,1,37,38), -- 1 persona × 12.50
(15.60,30.00,2,38,39),-- 2 personas × 7.80
(10.50,30.00,1,39,40), -- 1 persona × 10.50
(8.50,0.00,1,40,41), -- 1 persona × 8.50
(18.00,20.00,2,41,42),-- 2 personas × 9.00
(7.80,20.00,1,42,43); -- 1 persona × 7.80