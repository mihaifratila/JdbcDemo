FROM mysql:latest

ENV MYSQL_ROOT_PASSWORD=password
ENV MYSQL_DATABASE=my_database

EXPOSE 3306

COPY sql/table-setup-chapter4.sql /docker-entrypoint-initdb.d

RUN echo "CREATE USER 'student'@'%' IDENTIFIED BY 'student';" > /docker-entrypoint-initdb.d/user.sql
RUN echo "GRANT ALL PRIVILEGES ON *.* TO 'student'@'%';" >> /docker-entrypoint-initdb.d/user.sql
RUN echo "FLUSH PRIVILEGES;" >> /docker-entrypoint-initdb.d/user.sql
