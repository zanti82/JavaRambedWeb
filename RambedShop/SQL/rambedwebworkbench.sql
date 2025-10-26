CREATE DATABASE IF NOT EXISTS rambedWeb;


use rambedWeb;

CREATE TABLE Administrador (
    ID_Admin INT PRIMARY KEY,
    Nombre_Admin VARCHAR(100) NOT NULL,
    Email VARCHAR(100),
    Contrasena VARCHAR(100),
    Telefono VARCHAR(20),
    Direccion VARCHAR(100)
    
);
INSERT INTO Administrador (ID_Admin, Nombre_Admin, Email, Contrasena, Telefono, Direccion)
VALUES
(1, 'Laura Martínez', 'laura@rambed.com', 'Laura2025*', '3104589654', 'Cra 12 #45-33, Bogotá'),
(2, 'Carlos Gómez', 'carlos@rambed.com', 'CarGo#89', '3172548963', 'Cl 8 #21-10, Medellín'),
(3, 'Diana Torres', 'diana@rambed.com', 'DianaT!25', '3017895462', 'Av. 80 #45-67, Cali'),
(4, 'Santiago Ramírez', 'santi@rambed.com', 'Santi_2025', '3126548795', 'Cl 93 #12-45, Bogotá'),
(5, 'Valentina Ruiz', 'vale@rambed.com', 'ValeRuiz$', '3208956412', 'Cra 18 #22-33, Bucaramanga');

CREATE TABLE Cliente (
    ID_Cliente INT PRIMARY KEY,
    Nombre VARCHAR(100) NOT NULL,
    Email VARCHAR(100),
    Contrasena VARCHAR(100),
    Telefono VARCHAR(20),
    Direccion VARCHAR(100)
);
INSERT INTO Cliente (ID_Cliente, Nombre, Email, Contrasena, Telefono, Direccion)
VALUES
(1, 'María López', 'mlopez@gmail.com', 'Maria2025*', '3102458963', 'Cra 45 #12-30, Bogotá'),
(2, 'Juan Rodríguez', 'jrodriguez@gmail.com', 'JuanR#89', '3014567890', 'Cl 22 #15-50, Medellín'),
(3, 'Sofía Hernández', 'sofia.hz@gmail.com', 'SofiH_12', '3158796541', 'Av. 80 #32-10, Cali'),
(4, 'Pedro Jiménez', 'pjimenez@hotmail.com', 'PedroJ!45', '3124785963', 'Cra 10 #45-70, Bucaramanga'),
(5, 'Lucía Torres', 'lucia.t@gmail.com', 'LuciaT_99', '3204569871', 'Cl 90 #20-40, Cartagena'),
(6, 'Camilo Vargas', 'cvargas@gmail.com', 'CamiV@2025', '3178965412', 'Cra 18 #55-21, Barranquilla'),
(7, 'Daniela Rojas', 'drojas@gmail.com', 'DaniR*33', '3114528796', 'Av. Bolívar 100, Pereira'),
(8, 'Felipe González', 'fgonzalez@gmail.com', 'FeliG#10', '3009876541', 'Cl 70 #25-15, Manizales'),
(9, 'Valeria Castillo', 'vcastillo@gmail.com', 'ValeC@11', '3147896321', 'Cra 12 #60-10, Cúcuta'),
(10, 'Andrés Pérez', 'aperez@gmail.com', 'AndresP_77', '3024569812', 'Cl 33 #40-20, Neiva');

CREATE TABLE ItemsJeans (
    ID_Ref INT PRIMARY KEY,
    Nombre_Ref VARCHAR(50),
    Precio DECIMAL(10,2)
);

INSERT INTO ItemsJeans (ID_Ref, Nombre_Ref, Precio)
VALUES
(1, 'Rambed Clásico Azul', 159000.00),
(2, 'Rambed Slim Fit', 169000.00),
(3, 'Rambed Urban Cut', 179000.00),
(4, 'Rambed Relaxed Fit', 149000.00),
(5, 'Rambed Destroyed', 189000.00),
(6, 'Rambed Vintage Gris', 199000.00),
(7, 'Rambed Cargo Verde', 209000.00),
(8, 'Rambed Stretch Azul', 179000.00),
(9, 'Rambed Street Negro', 189000.00),
(10, 'Rambed Premium Indigo', 229000.00);

CREATE TABLE Venta (
    ID_Venta INT PRIMARY KEY auto_increment,
    Fecha_Venta DATE NOT NULL,
    ID_Cliente INT NOT NULL,
    ID_Admin INT NOT NULL,
    Total DECIMAL(10,2),

    CONSTRAINT fk_cliente FOREIGN KEY (ID_Cliente) REFERENCES Cliente(ID_Cliente),
    CONSTRAINT fk_admin FOREIGN KEY (ID_Admin) REFERENCES Administrador(ID_Admin)
);

INSERT INTO Venta (Fecha_Venta, ID_Cliente, ID_Admin, Total)
VALUES
('2025-10-15', 3, 1, 358000.00),
('2025-10-16', 7, 2, 189000.00),
('2025-10-17', 1, 4, 418000.00),
('2025-10-18', 5, 3, 229000.00),
('2025-10-20', 9, 2, 398000.00);

CREATE TABLE Detalle_Venta (
    ID_Detalle INT PRIMARY KEY,
    ID_Venta INT NOT NULL,
    ID_Ref INT NOT NULL,
    Cantidad INT,
    Subtotal DECIMAL(10,2),

    CONSTRAINT fk_venta FOREIGN KEY (ID_Venta) REFERENCES Venta(ID_Venta),
    CONSTRAINT fk_Jeans_detalle FOREIGN KEY (ID_Ref) REFERENCES ItemsJeans(ID_Ref)
);
INSERT INTO Detalle_Venta (ID_Detalle, ID_Venta, ID_Ref, Cantidad, Subtotal)
VALUES
(1, 1, 2, 2, 179000.00),
(2, 1, 5, 1, 179000.00),

(3, 2, 3, 1, 189000.00),

(4, 3, 7, 2, 199000.00),
(5, 3, 8, 1, 20000.00),  -- pequeño accesorio o descuento simbólico

(6, 4, 1, 1, 229000.00),

(7, 5, 4, 2, 199000.00),
(8, 5, 6, 1, 199000.00),
(9, 5, 9, 1, 0.00),  -- posible regalo/promoción
(10, 5, 10, 1, 0.00);  -- otro bono
 
CREATE TABLE Despacho (
    ID_Despacho INT PRIMARY KEY,
    ID_Venta INT NOT NULL,
    Direccion_Entrega VARCHAR(150),
    Fecha_Despacho DATE,

    CONSTRAINT fk_despacho_venta FOREIGN KEY (ID_Venta) REFERENCES Venta(ID_Venta)
);
INSERT INTO Despacho (ID_Despacho, ID_Venta, Direccion_Entrega, Fecha_Despacho)
VALUES
(1, 1, 'Cra 12 #45-67, Bogotá, Cundinamarca', '2025-10-10'),
(2, 2, 'Calle 23 #10-15, Medellín, Antioquia', '2025-10-11'),
(3, 3, 'Av. 30 de Agosto #22-44, Pereira, Risaralda', '2025-10-12'),
(4, 4, 'Cl. 9 #18-05, Cali, Valle del Cauca', '2025-10-13'),
(5, 5, 'Cra 7 #55-22, Bucaramanga, Santander', '2025-10-14');



CREATE TABLE Stock (
    ID_Bodega INT PRIMARY KEY ,
    ID_Ref INT NOT NULL,
    Cantidad_Disponible INT,
    Fecha_Actualizacion DATE,
    ID_Admin INT NOT NULL,

    CONSTRAINT fk_bodega_jeans FOREIGN KEY (ID_Ref) REFERENCES ItemsJeans(ID_Ref),
    CONSTRAINT fk_bodega_admin FOREIGN KEY (ID_Admin) REFERENCES Administrador(ID_Admin)
);
INSERT INTO Stock (ID_Bodega, ID_Ref, Cantidad_Disponible, Fecha_Actualizacion, ID_Admin)
VALUES
(1, 1, 120, '2025-10-15', 1),
(2, 2, 85, '2025-10-15', 2),
(3, 3, 60, '2025-10-16', 3),
(4, 4, 150, '2025-10-16', 1),
(5, 5, 45, '2025-10-17', 4),
(6, 6, 200, '2025-10-17', 5),
(7, 7, 90, '2025-10-18', 2),
(8, 8, 70, '2025-10-18', 3),
(9, 9, 110, '2025-10-19', 1),
(10, 10, 30, '2025-10-19', 4);


SELECT * FROM Administrador;
SELECT * FROM Cliente order by Nombre;
SELECT * FROM ItemsJeans;
SELECT * FROM Venta;
SELECT * FROM Detalle_Venta;
SELECT * FROM Despacho;
SELECT * FROM Stock;