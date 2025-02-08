package server;
import java.io.Serializable;

public class Employee implements Serializable {
    private static final long serialVersionUID = 1L; // Ensure compatibility across different JVMs
    
    private String employeeId;
    private String firstName;
    private String lastName;
    private String icPassport;
    private String password;
    private String familyDetails;
    private int leaveBalance;

    // Constructor
    public Employee(String employeeId, String firstName, String lastName, String icPassport, String password, String familyDetails, int leaveBalance) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.icPassport = icPassport;
        this.password = password;
        this.familyDetails = familyDetails;
        this.leaveBalance = leaveBalance;
    }

    // Getters and Setters
    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getIcPassport() {
        return icPassport;
    }

    public void setIcPassport(String icPassport) {
        this.icPassport = icPassport;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFamilyDetails() {
        return familyDetails;
    }

    public void setFamilyDetails(String familyDetails) {
        this.familyDetails = familyDetails;
    }

    public int getLeaveBalance() {
        return leaveBalance;
    }

    public void setLeaveBalance(int leaveBalance) {
        this.leaveBalance = leaveBalance;
    }
    
    // Override toString() for debugging and logging
    @Override
    public String toString() {
        return "Employee{" +
                "employeeId='" + employeeId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", icPassport='" + icPassport + '\'' +
                ", leaveBalance=" + leaveBalance +
                '}';
    }
}

