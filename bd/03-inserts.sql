INSERT INTO sucursales (id, nombre, direccion) VALUES
(1, 'Sucursal Centro', 'Av. Reforma 100, CDMX'),
(2, 'Sucursal Norte', 'Av. Insurgentes 2450, CDMX'),
(3, 'Sucursal Sur', 'Periférico Sur 3450, CDMX'),
(4, 'Sucursal Oriente', 'Calz. Ignacio Zaragoza 780, CDMX'),
(5, 'Sucursal Poniente', 'Av. Observatorio 250, CDMX'),
(6, 'Sucursal Guadalajara', 'Av. Vallarta 5000, GDL'),
(7, 'Sucursal Monterrey', 'Av. Constitución 4500, MTY'),
(8, 'Sucursal Puebla', 'Blvd. Atlixco 340, Puebla'),
(9, 'Sucursal Mérida', 'Calle 60 #550, Mérida'),
(10, 'Sucursal Cancún', 'Av. Tulum 230, Cancún');


INSERT INTO empleados (id, nombre, usuario, password, rol, sucursal_id) VALUES
(1, 'Administrador General', 'admin', '$2a$10$KsQwwS5OO4EiF3pI9uPGYOBwccv8KvU3zw5qxqyUrsTXkRWSJfGTu', 'ADMIN', 1),
(2, 'María Gómez', 'mgomez', '$2a$10$KsQwwS5OO4EiF3pI9uPGYOBwccv8KvU3zw5qxqyUrsTXkRWSJfGTu', 'ADMIN', 2),
(3, 'Carlos López', 'clopez', '$2a$10$KsQwwS5OO4EiF3pI9uPGYOBwccv8KvU3zw5qxqyUrsTXkRWSJfGTu', 'USER', 3),
(4, 'Ana Torres', 'atorres', '$2a$10$KsQwwS5OO4EiF3pI9uPGYOBwccv8KvU3zw5qxqyUrsTXkRWSJfGTu', 'USER', 4),
(5, 'Luis Ramírez', 'lramirez', '$2a$10$KsQwwS5OO4EiF3pI9uPGYOBwccv8KvU3zw5qxqyUrsTXkRWSJfGTu', 'USER', 5),
(6, 'Diana Cruz', 'dcruz', '$2a$10$KsQwwS5OO4EiF3pI9uPGYOBwccv8KvU3zw5qxqyUrsTXkRWSJfGTu', 'USER', 6),
(7, 'José Hernández', 'jhernandez', '$2a$10$KsQwwS5OO4EiF3pI9uPGYOBwccv8KvU3zw5qxqyUrsTXkRWSJfGTu', 'USER', 7),
(8, 'Laura Chávez', 'lchavez', '$2a$10$KsQwwS5OO4EiF3pI9uPGYOBwccv8KvU3zw5qxqyUrsTXkRWSJfGTu', 'USER', 8),
(9, 'Roberto Díaz', 'rdiaz', '$2a$10$KsQwwS5OO4EiF3pI9uPGYOBwccv8KvU3zw5qxqyUrsTXkRWSJfGTu', 'USER', 9),
(10, 'Andrea Flores', 'aflores', 'admin123', 'USER', 10);


INSERT INTO clientes (id, nombre, rfc, email, telefono) VALUES
(1, 'Pedro Sánchez', 'SACP800101AA1', 'pedro.sanchez@mail.com', '5512340001'),
(2, 'Lucía Morales', 'MOLU850202BB2', 'lucia.morales@mail.com', '5512340002'),
(3, 'Jorge Herrera', 'HEJJ900303CC3', 'jorge.herrera@mail.com', '5512340003'),
(4, 'Carmen Vázquez', 'VACJ920404DD4', 'carmen.vazquez@mail.com', '5512340004'),
(5, 'Ricardo Díaz', 'DIRR930505EE5', 'ricardo.diaz@mail.com', '5512340005'),
(6, 'Paola Ortega', 'OERP940606FF6', 'paola.ortega@mail.com', '5512340006'),
(7, 'Miguel Torres', 'TORM950707GG7', 'miguel.torres@mail.com', '5512340007'),
(8, 'Laura López', 'LOPL960808HH8', 'laura.lopez@mail.com', '5512340008'),
(9, 'Sofía Romero', 'ROSA970909II9', 'sofia.romero@mail.com', '5512340009'),
(10, 'Daniel García', 'GADN980101JJ0', 'daniel.garcia@mail.com', '5512340010');
