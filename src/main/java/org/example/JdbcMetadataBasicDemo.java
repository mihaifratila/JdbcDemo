package org.example;

import java.sql.*;

public class JdbcMetadataBasicDemo {

    public static void main(String[] args) throws SQLException {

        try (Connection myConn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/demo",
                "student",
                "student")) {

            DatabaseMetaData dbMetaData = myConn.getMetaData();

            System.out.println("Product name: " + dbMetaData.getDatabaseProductName());
            System.out.println("Product version: " + dbMetaData.getDatabaseProductVersion());
            System.out.println();

            System.out.println("JDBC Driver Name: " + dbMetaData.getDriverName());
            System.out.println("JDBC Driver Version: " + dbMetaData.getDriverVersion());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
