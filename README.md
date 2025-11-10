# EvaluacionTecnica — Simulador de Solicitud de Crédito

Proyecto Full Stack desarrollado como evaluación técnica.  
Consta de una arquitectura basada en 
	** microservicios Spring Boot (Java 17) para el backend, 
	** Angular 19 para el frontend, y 
	** MySQL 8 como base de datos.  

Todo el entorno se puede ejecutar mediante **Docker Compose**.

---

## Estructura del Proyecto

```
EvaluacionTecnica/
├── auth-ms/               → Microservicio de autenticación y emisión de tokens JWT
├── clientes-ms/           → Microservicio de gestión de clientes
├── solicitudes-ms/        → Microservicio para procesar solicitudes de crédito
├── api-gateway/           → Puerta de enlace que enruta peticiones a los microservicios
├── eureka-server/         → Servidor de descubrimiento de servicios
├── credit-frontend/       → Aplicación Angular que consume los microservicios
├── bd/                    → Scripts de base de datos
│   ├── 1-schema_db.sql
│   ├── 2-store_procedure.sql
│   ├── 3-inserts.sql
├── documentación/         → Casos de uso, matriz de pruebas, diagramas
├── docker-compose.yml     → Archivo principal de orquestación Docker
└── README.md
```

---

## Clonar el Repositorio

```bash
git clone https://github.com/<tu-usuario>/EvaluacionTecnica.git
cd EvaluacionTecnica
```

---

## Requisitos Previos

Asegúrate de tener instaladas las siguientes herramientas:

| Requisito | Versión mínima |
|------------|----------------|
| [Java JDK](https://adoptium.net/) | 17 |
| [Node.js](https://nodejs.org/) | 18 |
| [Angular CLI](https://angular.io/cli) | 19 |
| [Maven](https://maven.apache.org/) | 3.9 |
| [Docker Desktop](https://www.docker.com/products/docker-desktop) | Última versión |
| [Git](https://git-scm.com/downloads) | Cualquiera |

---

## Base de Datos MySQL

Los scripts SQL se encuentran en la carpeta `/bd`.

Se copian en automático al momento de crear la imágen de mysql, y cuando se ejecuta el docker-compose se crea el contenedor de mysql con la base de datos y tablas creadas, asi como la data
Debes ejecutarlos **en el siguiente orden**:

`01-schema_db.sql` → crea el esquema y las tablas principales (`clientes`, `empleados`, `solicitudes`, `sucursales`).  
`02-store_procedure.sql` → crea el procedimiento almacenado principal `sp_valida_credito`.  
`03-inserts.sql` → inserta datos iniciales para pruebas (clientes, empleados, sucursales, etc).

### Funcionalidad del Store Procedure

El procedimiento `sp_valida_credito` evalúa una solicitud de crédito en base a:
- El **monto solicitado**,  
- El **plazo en meses**,  
- Y los **ingresos mensuales** del cliente.  

Con base en estas reglas devuelve una **decisión de crédito (`APROBADO` o `RECHAZADO`)** .  

Este procedimiento es invocado por el microservicio `solicitudes-ms`.

---

## Ejecución con Docker Compose

El proyecto incluye un `docker-compose.yml` que orquesta todos los servicios.

Para construir e iniciar todo el entorno:

```bash
docker-compose up -d --build
```

Esto levantará los siguientes contenedores:

| Servicio | Puerto | Descripción |
|-----------|--------|--------------|
| mysql-db | 3306 | Base de datos MySQL con los scripts cargados |
| eureka-server | 8761 | Servidor de registro de microservicios |
| api-gateway | 8080 | Punto de entrada a los microservicios |
| auth-ms | 8083 | Autenticación JWT |
| clientes-ms | 8082 | Gestión de clientes |
| solicitudes-ms | 8081 | Procesamiento de solicitudes |
| angular-frontend | 4200 | Interfaz web del sistema |

Una vez desplegado, accede al frontend en:
**http://localhost:4200**

usuario de prueba: admin
contraseña de prueba: admin123

---

## Pruebas Unitarias

### clientes-ms

Ejecuta pruebas unitarias de clientes:

```bash
cd clientes-ms
mvn test
```

Estas pruebas validan:
- Búsqueda de cliente existente.
- Manejo de cliente inexistente.
- Consulta de lista completa de clientes.

### solicitudes-ms

Ejecuta pruebas unitarias de solicitudes:

```bash
cd solicitudes-ms
mvn test
```

Estas pruebas validan:
- Casos aprobados/rechazados según reglas de negocio.
- Persistencia de solicitudes.
- Evaluación del procedimiento almacenado `sp_valida_credito`.

Los resultados se muestran en consola y en:
```bash
directamente en consola
```

---

## Documentación

Dentro de la carpeta `/documentación` encontrarás:

| Archivo | Contenido |
|----------|------------|
| `Casos_de_uso.docx` | Diagrama y descripción de casos de uso para Clientes y Solicitudes |
| `Matriz_de_pruebas.docx` | Escenarios de prueba y resultados esperados |
| `Diagrama_ER.pdf` | Diagrama entidad-relación de la base de datos |

---

## Ejecución Manual (sin Docker)

Si prefieres ejecutar cada componente manualmente:

**Base de datos:**  
   Crear en MySQL la BD `credit_app` y ejecutar los scripts SQL.

**Eureka Server:**  
   ```bash
   cd eureka-server
   mvn spring-boot:run
   ```

**API Gateway:**  
   ```bash
   cd api-gateway
   mvn spring-boot:run
   ```

**Microservicios (auth, clientes, solicitudes):**  
   ```bash
   mvn spring-boot:run
   ```

**Frontend:**  
   ```bash
   cd credit-frontend
   npm install
   ng serve
   ```
   Acceder a `http://localhost:4200`.
   
   Se tienen pantalla de:
	** Login  -  Se utiliza validación mediante tokenb JWT
	** Inicio con menu en el header
	** Capturar solicitudes
	** Consultar solicitudes
	** Pantalla de Resultado de solicitud (se muestra despues de capturar la solicitud)
	** Estadisticos muestra el conteo total de solicitudes realizdas, total de APROBADA y total de RECHAZADA
	** Pantalla para enviar solicitudes masivas (en este ejemplo solo se tienen 10 pero se puede modificar el archivo mock-solicitudes.json)
	** Salir - (Logout)
	
	NOTA: La contraseña esta encriptada
		  Los inserts de prueba incluyen un usuario que no tiene encriptada la contraseña
		  Contraseña de pruebas: admin123

---

## Autor

**Desarrollado por:**  
Harvyn Hernández  
Contacto: hardezky@gmail.com

---

## Licencia

Este proyecto es de uso académico y técnico, orientado a demostrar conocimientos en:
- Arquitectura de microservicios  
- Dockerización de entornos  
- Integración Frontend/Backend  
- Pruebas unitarias y automatización
