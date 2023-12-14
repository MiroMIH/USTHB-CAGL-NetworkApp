package Clients;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class IntermediateServerMainOne {
    public static void main(String[] args) {
    	//RMI CONNECTION TO RECIEVE ALL SERVICES BY THE ORDER THEY COME IN 
        try {
            // Create and export the remote object
            IntermediateServerImpl server = new IntermediateServerImpl();

            // Get the RMI registry on the server's host 
            Registry registry = LocateRegistry.createRegistry(1099);

            // Bind the remote object to the registry
            registry.rebind("IntermediateServer", server);

            System.out.println("Intermediate Server is ready.");
        } catch (Exception e) {
            System.err.println("Intermediate Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
