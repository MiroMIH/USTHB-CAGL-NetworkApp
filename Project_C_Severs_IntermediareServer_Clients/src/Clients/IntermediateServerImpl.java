package Clients;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class IntermediateServerImpl extends UnicastRemoteObject implements IntermediateServerInterface {
    protected IntermediateServerImpl() throws RemoteException {
        super();
    }

    @Override
    public String sendMessage(String message) throws RemoteException {

        System.out.println("Received message: " + message);
        String returnMessage;
 
        if ("S0".equals(message) || "S1".equals(message) || "S2".equals(message) || "S3".equals(message)) {
            // Send to serv1 on port 9090
        	returnMessage=interactWithServ1("localhost", 9090, message);
        	System.out.println("\u001B[92mReturned data: " + returnMessage + "\u001B[0m");
        	return returnMessage;
        }
        
        if ("S4".equals(message) || "S5".equals(message) || "S6".equals(message)) {
            // Send to serv2 on port 9091
        	returnMessage=interactWithServ1("localhost", 9091, message);
        	System.out.println("\u001B[92mReturned data: " + returnMessage + "\u001B[0m");
        	return returnMessage;
        }
        
        if ("S7".equals(message) || "S8".equals(message) || "S9".equals(message)) {
            // Send to serv3 on port 9092
        	returnMessage=interactWithServ1("localhost", 9092, message);
        	System.out.println("\u001B[92mReturned data: " + returnMessage + "\u001B[0m");
        	return returnMessage;
        }
        
        if ("S10".equals(message) || "S11".equals(message) || "S12".equals(message)) {
            // Send to serv4 on port 9093
        	returnMessage=interactWithServ1("localhost", 9093, message);
        	System.out.println("\u001B[92mReturned data: " + returnMessage + "\u001B[0m");
        	return returnMessage;
        }
        if ("S13".equals(message) || "S14".equals(message) || "S15".equals(message)) {
            // Send to serv5 on port 9094
        	returnMessage=interactWithServ1("localhost", 9094, message);
        	System.out.println("\u001B[92mReturned data: " + returnMessage + "\u001B[0m");
        	return returnMessage;
        }
        

        return "null";
    }
    
    
    private static String interactWithServ1(String host, int port, String messageToSend) {
        try (Socket socket = new Socket(host, port);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             Scanner scanner = new Scanner(System.in)) {

            // Send data to the server
        	System.out.println("\u001B[38;5;206mMessage to send to server on port:" + port + "--" + messageToSend + "\u001B[0m");
            out.println(messageToSend);

            // Receive data from serv1
            String receivedData = in.readLine();
            System.out.println("\u001B[91mServ on port: " + port + " says:" + receivedData + "\u001B[0m");
            in.close();
            out.close();
            socket.close();
            return receivedData;
            
            

        } catch (IOException e) {
            e.printStackTrace();
            return "null";
        }
		
    }
}
