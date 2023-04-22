package org.example;

import java.sql.*;

public class JdbcStoredProcedsOutDemo {

    public static void main(String[] args) throws SQLException {

        Connection myConn = null;
        CallableStatement myStmt = null;

        try {
            // Get a connection to database
            myConn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/demo",
                    "student",
                    "student" );

            String department = "Engineering";

            // Prepare the stored procedure call
            myStmt = myConn
                    .prepareCall("{call get_count_for_department(?, ?)}");

            // Set the parameters
            myStmt.setString(1, department);
            myStmt.registerOutParameter(2, Types.INTEGER);

            // Call stored procedure
            System.out.println("Calling stored procedure. get_count_for_department('" + department + "', ?)");
            myStmt.execute();
            System.out.println("Finished calling stored procedure");

            // Get the value of the INOUT parameter
            int result = myStmt.getInt(2);

            System.out.println("\nThe count = " + result);


        } catch (Exception exc) {
            exc.printStackTrace();
        } finally {
            close(myConn, myStmt, null);
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
