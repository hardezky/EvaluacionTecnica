FROM mysql:8.0.33

# Copiamos los scripts al directorio de inicializacion
COPY *.sql /docker-entrypoint-initdb.d/


# Variables de entorno para crear la base de datos automaticamente
ENV MYSQL_ROOT_PASSWORD=123456
ENV MYSQL_DATABASE=credit_app
