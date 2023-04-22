package org.example;

import java.sql.*;

public class JdbcInsertDemo {
    public static void main(String[] args) throws SQLException {
        Connection myConn = null;
        Statement myStmt = null;
        ResultSet myRs = null;

        try {
            // 1. Get a connection to database
            myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/demo", "student", "student");

            System.out.println("Database connection successful!\n");

            // 2. Create a statement
            myStmt = myConn.createStatement();

            // 3. Insert a new employee
            int rowsAffected = myStmt.executeUpdate("" +
                    "insert into employees " +
                    "(last_name, first_name, email, department, salary) " +
                    "values " +
                    "('Wright', 'Eric', 'eric.wright@foo.com', 'HR', 33000.00)");

            // 4. Verify this by getting a list of employees
            myRs = myStmt.executeQuery("select * from employees order by last_name");

            while (myRs.next()) {
                System.out.println(myRs.getString("last_name") + ", " +
                        myRs.getString("first_name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (myRs != null) {
                myRs.close();
            }
            if (myStmt != null) {
                myStmt.close();
            }
            if (myConn != null) {
                myConn.close();
            }
        }
    }
}
