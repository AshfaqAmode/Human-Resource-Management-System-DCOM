
package com.mycompany.hr_system;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.Scanner;

public class HRClient {
    public static void main(String[] args) {
        try {
            // Look up the RMI server
            HRManagement system = (HRManagement) Naming.lookup("rmi://localhost/HRManagement");
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("1. Add Employee");
                System.out.println("2. View Employees");
                System.out.println("3. Update Employee Role");
                System.out.println("4. Delete Employee");
                System.out.println("5. Apply for Leave");
                System.out.println("6. View Leave Applications");
                System.out.println("7. Approve Leave");
                System.out.println("8. Exit");
                System.out.print("Choose an option: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        // Add employee logic
                        System.out.print("Enter IC/Passport: ");
                        String icPassportAdd = scanner.nextLine();
                        System.out.print("Enter First Name: ");
                        String firstName = scanner.nextLine();
                        System.out.print("Enter Last Name: ");
                        String lastName = scanner.nextLine();
                        System.out.print("Enter Role: ");
                        String role = scanner.nextLine();
                        System.out.print("Enter Department: ");
                        String department = scanner.nextLine();
                        boolean addSuccess = system.addEmployee(icPassportAdd, firstName, lastName, role, department);
                        if (addSuccess) {
                            System.out.println("Employee added successfully.");
                        } else {
                            System.out.println("Failed to add employee. Employee may already exist.");
                        }
                        break;

                    case 2:
                        // View employees logic
                        List<String> employees = system.viewEmployees();
                        System.out.println("Employees in the database:");
                        employees.forEach(System.out::println);
                        break;

                    case 3:
                        // Update employee role logic
                        System.out.print("Enter employee IC/Passport: ");
                        String icPassportUpdate = scanner.nextLine();
                        System.out.print("Enter new role: ");
                        String newRole = scanner.nextLine();
                        boolean updateSuccess = system.updateEmployeeRole(icPassportUpdate, newRole);
                        if (updateSuccess) {
                            System.out.println("Employee role updated successfully.");
                        } else {
                            System.out.println("Failed to update employee role. Check the IC/Passport.");
                        }
                        break;

                    case 4: // Delete employee logic
                        System.out.print("Enter employee IC/Passport to delete: ");
                        String icPassportDelete = scanner.nextLine();
                        try {
                            boolean deleteSuccess = system.deleteEmployee(icPassportDelete);
                            if (deleteSuccess) {
                                System.out.println("Employee deleted successfully.");
                            } else {
                                System.out.println("Failed to delete employee.");
                            }
                        } catch (RemoteException e) {
                            System.err.println("Error: " + e.getMessage());
                        }
                        break;

                    case 5: // Apply for leave logic
                        System.out.print("Enter your IC/Passport: ");
                        String icPassportLeave = scanner.nextLine();
                        System.out.print("Enter start date (YYYY-MM-DD): ");
                        String startDate = scanner.nextLine();
                        System.out.print("Enter end date (YYYY-MM-DD): ");
                        String endDate = scanner.nextLine();
                        System.out.print("Enter reason for leave: ");
                        String reason = scanner.nextLine();
                        try {
                            boolean leaveSuccess = system.applyForLeave(icPassportLeave, startDate, endDate, reason);
                            if (leaveSuccess) {
                                System.out.println("Leave application submitted successfully.");
                            } else {
                                System.out.println("Failed to submit leave application.");
                            }
                        } catch (RemoteException e) {
                            System.err.println("Error: " + e.getMessage());
                        }
                        break;

                    case 6: // View leave applications logic
                        try {
                            List<String> leaveApplications = system.viewLeaveApplications();
                            System.out.println("Leave Applications:");
                            leaveApplications.forEach(System.out::println);
                        } catch (RemoteException e) {
                            System.err.println("Error: " + e.getMessage());
                        }
                        break;

                    case 7:
                        // Approve leave logic
                        System.out.print("Enter employee IC/Passport: ");
                        String icPassportApprove = scanner.nextLine();
                        System.out.print("Enter start date (YYYY-MM-DD): ");
                        String startDateApprove = scanner.nextLine();
                        try {
                            boolean approveSuccess = system.approveLeave(icPassportApprove, startDateApprove);
                            if (approveSuccess) {
                                System.out.println("Leave approved successfully.");
                            } else {
                                System.out.println("Failed to approve leave.");
                            }
                        } catch (RemoteException e) {
                            System.err.println("Error: " + e.getMessage());
                        }
                        break;

                    case 8:
                        System.out.println("Exiting...");
                        return;

                    default:
                        System.out.println("Invalid option.");
                }
            }
        } catch (Exception e) {
            System.err.println("Client exception: " + e.getMessage());
        }
    }
}
