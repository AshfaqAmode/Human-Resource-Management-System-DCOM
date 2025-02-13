
package com.mycompany.hr_system;


import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class HRServer {
    public static void main(String[] args) {
        try {
            HRManagementImpl system = new HRManagementImpl();
            Naming.rebind("rmi://localhost/HRManagement", system);
            System.out.println("HR Management System is ready.");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.getMessage());
        }
    }
}

