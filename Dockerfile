FROM mysql:latest

ENV MYSQL_ROOT_PASSWORD=password
ENV MYSQL_DATABASE=my_database

EXPOSE 3306

COPY sql /docker-entrypoint-initdb.d/