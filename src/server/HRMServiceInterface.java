package server;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface HRMServiceInterface extends Remote {
    // Employee authentication
    boolean authenticateEmployee(String empId, String password) throws RemoteException;

    // Employee profile management
    boolean registerEmployee(String empId, String firstName, String lastName, String icPassport, String password, String familyDetails, int leaveBalance) throws RemoteException;
    Employee getEmployeeDetails(String empId) throws RemoteException;
    boolean updateEmployeeProfile(String empId, String firstName, String lastName) throws RemoteException;

    // Leave management
    int getLeaveBalance(String empId) throws RemoteException;
    boolean applyLeave(String empId, String startDate, String endDate) throws RemoteException;
    String getLeaveHistory(String empId) throws RemoteException;
}
