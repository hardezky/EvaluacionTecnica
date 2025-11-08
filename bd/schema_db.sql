CREATE DATABASE IF NOT EXISTS credit_app CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE credit_app;


-- Sucursales
CREATE TABLE sucursales (
id BIGINT AUTO_INCREMENT PRIMARY KEY,
nombre VARCHAR(150) NOT NULL,
direccion VARCHAR(255),
creado_en TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


-- Clientes
CREATE TABLE clientes (
id BIGINT AUTO_INCREMENT PRIMARY KEY,
nombre VARCHAR(200) NOT NULL,
rfc VARCHAR(20),
email VARCHAR(200),
telefono VARCHAR(20),
creado_en TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


-- Empleados (incluye autorizadores)
CREATE TABLE empleados (
id BIGINT AUTO_INCREMENT PRIMARY KEY,
nombre VARCHAR(200) NOT NULL,
usuario VARCHAR(100) UNIQUE,
password VARCHAR(255) NOT NULL,
rol VARCHAR(50) DEFAULT 'USER',
sucursal_id BIGINT,
creado_en TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
FOREIGN KEY (sucursal_id) REFERENCES sucursales(id)
);


-- Solicitudes
CREATE TABLE solicitudes (
id BIGINT AUTO_INCREMENT PRIMARY KEY,
cliente_id BIGINT NOT NULL,
sucursal_id BIGINT,
monto DECIMAL(15,2) NOT NULL,
plazo_meses INT NOT NULL,
ingresos_mensuales DECIMAL(15,2),
destino VARCHAR(255),
fecha_solicitud TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
estado VARCHAR(50) DEFAULT 'PENDIENTE', -- PENDIENTE/APROBADO/RECHAZADO
score INT DEFAULT 0,
decision_motivo VARCHAR(500),
procesado_por BIGINT, -- empleado que procesó (opcional)
FOREIGN KEY (cliente_id) REFERENCES clientes(id),
FOREIGN KEY (sucursal_id) REFERENCES sucursales(id),
FOREIGN KEY (procesado_por) REFERENCES empleados(id)
);


-- Autorizaciones (quien autorizó el credito)
CREATE TABLE autorizaciones (
id BIGINT AUTO_INCREMENT PRIMARY KEY,
solicitud_id BIGINT NOT NULL,
empleado_id BIGINT NOT NULL,
fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
resultado VARCHAR(50), -- APROBADO/RECHAZADO
comentarios VARCHAR(1000),
FOREIGN KEY (solicitud_id) REFERENCES solicitudes(id),
FOREIGN KEY (empleado_id) REFERENCES empleados(id)
);


-- Indices para consultas frecuentes
CREATE INDEX idx_solicitudes_estado ON solicitudes(estado);
CREATE INDEX idx_solicitudes_cliente ON solicitudes(cliente_id);