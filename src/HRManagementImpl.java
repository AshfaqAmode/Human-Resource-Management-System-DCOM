
package com.mycompany.hr_system;


import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HRManagementImpl extends UnicastRemoteObject implements HRManagement {
    private Connection connection;

public HRManagementImpl() throws RemoteException {
    super();
    try {
        // Connect to the  database
        connection = DriverManager.getConnection("jdbc:sqlite:hrm.db");
        System.out.println("Connected to SQLite database.");

        // Create tables if they don't exist
        try (Statement stmt = connection.createStatement()) {
            String createEmployeesTable = "CREATE TABLE IF NOT EXISTS employees ("
                    + "ic_passport TEXT PRIMARY KEY,"
                    + "first_name TEXT NOT NULL,"
                    + "last_name TEXT NOT NULL,"
                    + "role TEXT NOT NULL,"
                    + "department TEXT NOT NULL);";
            stmt.execute(createEmployeesTable);

            String createLeaveApplicationsTable = "CREATE TABLE IF NOT EXISTS leave_applications ("
                    + "leave_id TEXT PRIMARY KEY,"
                    + "ic_passport TEXT NOT NULL,"
                    + "start_date TEXT NOT NULL,"
                    + "end_date TEXT NOT NULL,"
                    + "status TEXT NOT NULL,"
                    + "FOREIGN KEY (ic_passport) REFERENCES employees(ic_passport));";
            stmt.execute(createLeaveApplicationsTable);

            System.out.println("Tables created successfully.");
        }
    } catch (Exception e) {
        System.err.println("Failed to connect to database or create tables: " + e.getMessage());
    }
}


    @Override
public boolean addEmployee(String icPassport, String firstName, String lastName, String role, String department)
        throws RemoteException {
    // Check if the employee already exists
    String checkSql = "SELECT ic_passport FROM employees WHERE ic_passport = ?";
    try (PreparedStatement checkStmt = connection.prepareStatement(checkSql)) {
        checkStmt.setString(1, icPassport);
        ResultSet rs = checkStmt.executeQuery();
        if (rs.next()) {
            System.out.println("Employee with IC/Passport " + icPassport + " already exists.");
            return false; // Employee already exists
        }
    } catch (Exception e) {
        System.err.println("Error checking employee existence: " + e.getMessage());
        return false;
    }

    // Insert the employee
    String insertSql = "INSERT INTO employees (ic_passport, first_name, last_name, role, department) VALUES (?, ?, ?, ?, ?)";
    try (PreparedStatement insertStmt = connection.prepareStatement(insertSql)) {
        insertStmt.setString(1, icPassport);
        insertStmt.setString(2, firstName);
        insertStmt.setString(3, lastName);
        insertStmt.setString(4, role);
        insertStmt.setString(5, department);
        insertStmt.executeUpdate();
        System.out.println("Employee added: " + icPassport);
        return true;
    } catch (Exception e) {
        System.err.println("Error adding employee: " + e.getMessage());
        return false;
    }
}


    @Override
public List<String> viewEmployees() throws RemoteException {
    List<String> employees = new ArrayList<>();
    String sql = "SELECT * FROM employees";
    try (PreparedStatement pstmt = connection.prepareStatement(sql);
         ResultSet rs = pstmt.executeQuery()) {
        System.out.println("Executing query: " + sql);
        while (rs.next()) {
            String employee = String.format("IC/Passport: %s, Name: %s %s, Role: %s, Department: %s",
                    rs.getString("ic_passport"), rs.getString("first_name"),
                    rs.getString("last_name"), rs.getString("role"), rs.getString("department"));
            employees.add(employee);
            System.out.println("Fetched employee: " + employee);
        }
    } catch (Exception e) {
        System.err.println("Failed to fetch employees: " + e.getMessage());
    }
    return employees;
}
public void addTestData() {
    try (Statement stmt = connection.createStatement()) {
        stmt.execute("INSERT INTO employees (ic_passport, first_name, last_name, role, department) "
                + "VALUES ('123456', 'John', 'Doe', 'Manager', 'HR')");
        stmt.execute("INSERT INTO employees (ic_passport, first_name, last_name, role, department) "
                + "VALUES ('789012', 'Jane', 'Smith', 'Developer', 'IT')");
        System.out.println("Test data added successfully.");
    } catch (Exception e) {
        System.err.println("Failed to add test data: " + e.getMessage());
    }
}

    

    @Override
public boolean updateEmployeeRole(String icPassport, String newRole) throws RemoteException {
    String sql = "UPDATE employees SET role = ? WHERE ic_passport = ?";
    try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
        pstmt.setString(1, newRole); // Set the new role
        pstmt.setString(2, icPassport); // Set the IC/Passport of the employee
        int rowsUpdated = pstmt.executeUpdate(); // Execute the update
        return rowsUpdated > 0; // Return true if the role was updated
    } catch (SQLException e) {
        System.err.println("Error updating employee role: " + e.getMessage());
        throw new RemoteException("Failed to update employee role.", e);
    }
}


@Override
public boolean deleteEmployee(String icPassport) throws RemoteException {
    String sql = "DELETE FROM employees WHERE ic_passport = ?";
    try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
        pstmt.setString(1, icPassport); // Set the IC/Passport of the employee to delete
        int rowsDeleted = pstmt.executeUpdate(); // Execute the delete query
        return rowsDeleted > 0; // Return true if an employee was deleted
    } catch (SQLException e) {
        System.err.println("Error deleting employee: " + e.getMessage());
        throw new RemoteException("Failed to delete employee.", e);
    }
}

// For future, when leave management part is completed
public boolean applyForLeave(String icPassport, String startDate, String endDate, String reason) throws RemoteException {
    throw new RemoteException("Functionality not supported yet: applyForLeave");
}


public List<String> viewLeaveApplications() throws RemoteException {
    throw new RemoteException("Functionality not supported yet: viewLeaveApplications");
}
    @Override
    public String generateYearlyLeaveReport(String icPassport) throws RemoteException {
        throw new RemoteException("Not supported yet."); 
    }

    @Override
    public boolean approveLeave(String icPassport, String leaveId) throws RemoteException {
        throw new RemoteException("Not supported yet."); 
    }

    @Override
    public boolean rejectLeave(String icPassport, String leaveId) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
}

