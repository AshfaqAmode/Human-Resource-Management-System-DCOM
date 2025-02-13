package com.mycompany.hr_system;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface HRManagement extends Remote {
    boolean addEmployee(String firstName, String lastName, String icPassport, String role, String department) throws RemoteException;
    List<String> viewEmployees() throws RemoteException;
    boolean updateEmployeeRole(String icPassport, String newRole) throws RemoteException;
    String generateYearlyLeaveReport(String icPassport) throws RemoteException;
    boolean approveLeave(String icPassport, String leaveId) throws RemoteException;
    boolean rejectLeave(String icPassport, String leaveId) throws RemoteException;
    boolean deleteEmployee(String icPassport) throws RemoteException; // New method
    List<String> viewLeaveApplications() throws RemoteException; // New method
    boolean applyForLeave(String icPassport, String startDate, String endDate, String reason) throws RemoteException; // New method
    
}
