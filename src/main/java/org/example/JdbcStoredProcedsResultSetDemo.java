package org.example;

import java.sql.*;

public class JdbcStoredProcedsResultSetDemo {


    public static void main(String[] args) throws SQLException {

        Connection myConn = null;
        CallableStatement myStmt = null;
        ResultSet myRs = null;

        try {
            // Get a connection to database
            myConn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/demo",
                    "student",
                    "student" );

            String department = "Engineering";

            // Prepare the stored procedure call
            myStmt = myConn
                    .prepareCall("{call get_employees_for_department(?)}");

            // Set the parameters
            myStmt.setString(1, department);

            // Call stored procedure
            System.out.println("Calling stored procedure. get_employees_for_department('" + department + "')");
            myStmt.execute();
            System.out.println("Finished calling stored procedure");

            // Get the value of the INOUT parameter
            myRs = myStmt.getResultSet();

            display(myRs);

        } catch (Exception exc) {
            exc.printStackTrace();
        } finally {
            close(myConn, myStmt, null);
        }
    }

    private static void display(ResultSet myRs) throws SQLException {
        // Process result set
        while (myRs.next()) {
            String lastName = myRs.getString("last_name");
            String firstName = myRs.getString("first_name");
            double salary = myRs.getDouble("salary");
            String department = myRs.getString("department");

            System.out.printf("%s, %s, %s, %.2f\n", lastName, firstName, department, salary);
        }

    }

    private static void close(Connection myConn, Statement myStmt,
                              ResultSet myRs) throws SQLException {
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

    private static void close(Statement myStmt, ResultSet myRs)
            throws SQLException {

        close(null, myStmt, myRs);
    }
}
