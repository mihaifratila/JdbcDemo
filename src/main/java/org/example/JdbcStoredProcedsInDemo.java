package org.example;

import java.sql.*;

public class JdbcStoredProcedsInDemo {

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
            int salaryIncrease = 10000;

            // Show salaries BEFORE
            System.out.println("Salaries BEFORE\n");
            showSalaries(myConn, department);

            // Prepare the stored procedure call
            myStmt = myConn
                    .prepareCall("{call increase_salaries_for_department(?, ?)}");

            // Set the parameters
            myStmt.setString(1, department);
            myStmt.setDouble(2, salaryIncrease);

            myStmt.execute();

            // Show salaries AFTER
            System.out.println("Salaries AFTER\n");
            showSalaries(myConn, department);


        } catch (Exception exc) {
            exc.printStackTrace();
        } finally {
            close(myConn, myStmt, null);
        }
    }

    private static void showSalaries(Connection myConn, String theDepartment) throws SQLException {
        PreparedStatement myStmt = null;
        ResultSet myRs = null;

        try {
            // Prepare statement
            myStmt = myConn
                    .prepareStatement("select * from employees where department=?");

            myStmt.setString(1, theDepartment);

            // Execute SQL query
            myRs = myStmt.executeQuery();

            // Process result set
            while (myRs.next()) {
                String lastName = myRs.getString("last_name");
                String firstName = myRs.getString("first_name");
                double salary = myRs.getDouble("salary");
                String department = myRs.getString("department");

                System.out.printf("%s, %s, %s, %.2f\n", lastName, firstName, department, salary);
            }
        } catch (Exception exc) {
            exc.printStackTrace();
        } finally {
            close(myStmt, myRs);
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

