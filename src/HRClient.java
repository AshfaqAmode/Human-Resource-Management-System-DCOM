package com.mycompany.hr_system;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.Scanner;

public class HRClient {
    private static HRManagement system;
    private static Scanner scanner;

    public static void main(String[] args) {
        try {
            // Get registry
            Registry registry = LocateRegistry.getRegistry("127.0.0.1", 1098);

            // Look up the remote object
            system = (HRManagement) registry.lookup("HRSystem");

            scanner = new Scanner(System.in);

            while (true) {
                showMenu();
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        addEmployee();
                        break;
                    case 2:
                        viewEmployees();
                        break;
                    case 3:
                        updateEmployeeRole();
                        break;
                    case 4:
                        deleteEmployee();
                        break;
                    case 5:
                        System.out.println("Exiting...");
                        return;
                    default:
                        System.out.println("Invalid option");
                }
            }
        } catch (Exception e) {
            System.err.println("Client exception: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void showMenu() {
        System.out.println("\nHR Management System");
        System.out.println("1. Add Employee");
        System.out.println("2. View Employees");
        System.out.println("3. Update Employee Role");
        System.out.println("4. Delete Employee");
        System.out.println("5. Exit");
        System.out.print("Choose an option: ");
    }

    private static void addEmployee() {
        try {
            System.out.print("Enter IC/Passport: ");
            String icPassport = scanner.nextLine();
            System.out.print("Enter First Name: ");
            String firstName = scanner.nextLine();
            System.out.print("Enter Last Name: ");
            String lastName = scanner.nextLine();
            System.out.print("Enter Role: ");
            String role = scanner.nextLine();
            System.out.print("Enter Department: ");
            String department = scanner.nextLine();

            boolean success = system.addEmployee(icPassport, firstName, lastName, role, department);
            System.out.println(success ? "Employee added successfully" : "Failed to add employee");
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private static void viewEmployees() {
        try {
            List<String> employees = system.viewEmployees();
            if (employees.isEmpty()) {
                System.out.println("No employees found.");
            } else {
                System.out.println("\nEmployee List:");
                employees.forEach(System.out::println);
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private static void updateEmployeeRole() {
        try {
            System.out.print("Enter IC/Passport: ");
            String icPassport = scanner.nextLine();
            System.out.print("Enter new role: ");
            String newRole = scanner.nextLine();

            boolean success = system.updateEmployeeRole(icPassport, newRole);
            System.out.println(success ? "Role updated successfully" : "Failed to update role");
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private static void deleteEmployee() {
        try {
            System.out.print("Enter IC/Passport to delete: ");
            String icPassport = scanner.nextLine();

            boolean success = system.deleteEmployee(icPassport);
            System.out.println(success ? "Employee deleted successfully" : "Failed to delete employee");
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
