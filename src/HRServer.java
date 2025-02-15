package com.mycompany.hr_system;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class HRServer {
    public static void main(String[] args) {
        try {
            System.setProperty("java.rmi.server.hostname", "127.0.0.1");

            // Create the RMI registry on port 1098
            Registry registry = LocateRegistry.createRegistry(1098);

            // Create the remote object
            HRManagementImpl hrSystem = new HRManagementImpl();

            // Bind the remote object to the registry
            registry.rebind("HRSystem", hrSystem);

            System.out.println("HR Server is up and running...");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
