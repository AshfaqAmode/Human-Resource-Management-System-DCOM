package com.mycompany.hr_system;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface HRManagement extends Remote {
    boolean addEmployee(String icPassport, String firstName, String lastName, String role, String department)
            throws RemoteException;

    List<String> viewEmployees() throws RemoteException;

    boolean updateEmployeeRole(String icPassport, String newRole) throws RemoteException;

    boolean deleteEmployee(String icPassport) throws RemoteException;
}
