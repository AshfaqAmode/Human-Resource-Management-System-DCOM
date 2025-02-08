package client;

import java.rmi.Naming;
import java.util.Scanner;

public class EmployeeClient {
    private static HRMService hrmService;  // Reference to the RMI Service
    private static String loggedInEmployeeId = null;  // Store logged-in employee ID

    public static void main(String[] args) {
        try {
            // Lookup the RMI registry to find the HRMService
            hrmService = (HRMService) Naming.lookup("rmi://localhost:1044/HRMService");

            Scanner scanner = new Scanner(System.in);
            System.out.println("Welcome to the Employee HRM System");

            while (true) {
                if (loggedInEmployeeId == null) {
                    System.out.println("1. Login");
                    System.out.println("2. Exit");
                    System.out.print("Choose an option: ");
                    int choice = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    if (choice == 1) {
                        login(scanner);
                    } else {
                        System.out.println("Exiting...");
                        break;
                    }
                } else {
                    showEmployeeMenu(scanner);
                }
            }
            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void login(Scanner scanner) throws Exception {
        System.out.print("Enter Employee ID: ");
        String empId = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        boolean isAuthenticated = hrmService.authenticateEmployee(empId, password);
        if (isAuthenticated) {
            loggedInEmployeeId = empId;
            System.out.println("Login successful! Welcome.");
        } else {
            System.out.println("Invalid credentials. Try again.");
        }
    }

    private static void showEmployeeMenu(Scanner scanner) throws Exception {
        while (true) {
            System.out.println("\n1. View Profile");
            System.out.println("2. Update Profile");
            System.out.println("3. View Leave Balance");
            System.out.println("4. Apply for Leave");
            System.out.println("5. View Leave History");
            System.out.println("6. Logout");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> viewProfile();
                case 2 -> updateProfile(scanner);
                case 3 -> viewLeaveBalance();
                case 4 -> applyForLeave(scanner);
                case 5 -> viewLeaveHistory();
                case 6 -> {
                    loggedInEmployeeId = null;
                    System.out.println("Logged out successfully.");
                    return;
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void viewProfile() throws Exception {
        Employee employee = hrmService.getEmployeeDetails(loggedInEmployeeId);
        System.out.println("\n--- Employee Profile ---");
        System.out.println("Name: " + employee.getFirstName() + " " + employee.getLastName());
        System.out.println("IC/Passport: " + employee.getIcPassport());
    }

    private static void updateProfile(Scanner scanner) throws Exception {
        System.out.print("Enter new First Name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter new Last Name: ");
        String lastName = scanner.nextLine();

        boolean success = hrmService.updateEmployeeProfile(loggedInEmployeeId, firstName, lastName);
        if (success) {
            System.out.println("Profile updated successfully.");
        } else {
            System.out.println("Profile update failed.");
        }
    }

    private static void viewLeaveBalance() throws Exception {
        int balance = hrmService.getLeaveBalance(loggedInEmployeeId);
        System.out.println("Your available leave balance: " + balance + " days");
    }

    private static void applyForLeave(Scanner scanner) throws Exception {
        System.out.print("Enter leave start date (YYYY-MM-DD): ");
        String startDate = scanner.nextLine();
        System.out.print("Enter leave end date (YYYY-MM-DD): ");
        String endDate = scanner.nextLine();

        boolean success = hrmService.applyLeave(loggedInEmployeeId, startDate, endDate);
        if (success) {
            System.out.println("Leave application submitted.");
        } else {
            System.out.println("Leave application failed.");
        }
    }

    private static void viewLeaveHistory() throws Exception {
        String history = hrmService.getLeaveHistory(loggedInEmployeeId);
        System.out.println("\n--- Leave History ---");
        System.out.println(history);
    }
}
