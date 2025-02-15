package com.mycompany.hr_system;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HRManagementImpl extends UnicastRemoteObject implements HRManagement {
    private Connection connection;

    public HRManagementImpl() throws RemoteException {
        super();
        initializeDatabase();
    }

    private void initializeDatabase() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:hrm.db");
            System.out.println("Connected to SQLite database.");

            try (Statement stmt = connection.createStatement()) {
                String sql = "CREATE TABLE IF NOT EXISTS employees ("
                        + "ic_passport TEXT PRIMARY KEY,"
                        + "first_name TEXT NOT NULL,"
                        + "last_name TEXT NOT NULL,"
                        + "role TEXT NOT NULL,"
                        + "department TEXT NOT NULL)";
                stmt.execute(sql);
                System.out.println("Table created successfully.");
            }
        } catch (Exception e) {
            System.err.println("Database initialization error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public boolean addEmployee(String icPassport, String firstName, String lastName, String role, String department)
            throws RemoteException {
        String sql = "INSERT INTO employees (ic_passport, first_name, last_name, role, department) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, icPassport);
            pstmt.setString(2, firstName);
            pstmt.setString(3, lastName);
            pstmt.setString(4, role);
            pstmt.setString(5, department);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error adding employee: " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<String> viewEmployees() throws RemoteException {
        List<String> employees = new ArrayList<>();
        String sql = "SELECT * FROM employees";
        try (Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                String employee = String.format("IC/Passport: %s, Name: %s %s, Role: %s, Department: %s",
                        rs.getString("ic_passport"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("role"),
                        rs.getString("department"));
                employees.add(employee);
            }
        } catch (SQLException e) {
            System.err.println("Error viewing employees: " + e.getMessage());
        }
        return employees;
    }

    @Override
    public boolean updateEmployeeRole(String icPassport, String newRole) throws RemoteException {
        String sql = "UPDATE employees SET role = ? WHERE ic_passport = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, newRole);
            pstmt.setString(2, icPassport);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error updating role: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteEmployee(String icPassport) throws RemoteException {
        String sql = "DELETE FROM employees WHERE ic_passport = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, icPassport);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error deleting employee: " + e.getMessage());
            return false;
        }
    }
}
