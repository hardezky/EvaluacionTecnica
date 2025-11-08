mysql:
  image: mysql:8
  container_name: mysql-db
  restart: always
  environment:
    MYSQL_ROOT_PASSWORD: 123456
    MYSQL_DATABASE: credit_app
  ports:
    - "3306:3306"
  volumes:
    - "C:/Users/harvy/Desktop/EvaluacionTecnica/bd/schema_db.sql:/docker-entrypoint-initdb.d/01-schema_db.sql"
    - "C:/Users/harvy/Desktop/EvaluacionTecnica/bd/store_procedure.sql:/docker-entrypoint-initdb.d/02-store_procedure.sql"
    - "C:/Users/harvy/Desktop/EvaluacionTecnica/bd/inserts.sql:/docker-entrypoint-initdb.d/03-inserts.sql"   
    - ./mysql_data:/var/lib/mysql
