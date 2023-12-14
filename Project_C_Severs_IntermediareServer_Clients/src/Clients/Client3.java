package Clients;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class Client3 {
    private static int timerSeconds = 60;

    public static void main(String[] args) {
        try {
            DatagramSocket clientSocket = new DatagramSocket(9878);
            clientSocket.setSoTimeout(5000); // Set timeout to 5 seconds
            InetAddress serverAddress = InetAddress.getByName("localhost");
            int sendPort = 9879; // Send back to Client1
            String[] referenceChain3 = {"S10", "S3", "S6", "S4", "S2", "S7", "S0", "S3", "S12", "S8", "S7", "S0", "S6", "S5", "S3", "S2", "S1", "S7", "S0", "S4", "FIN"};
            int refChainIndex = 0;

          

         

            boolean ClientOn = true;
            while (ClientOn) {
            	String message = "\u001B[38;5;206mTOKEN FROM CLIENT 3\u001B[0m";
            	System.out.println("\u001B[32mProcessing Service: " + referenceChain3[refChainIndex] + "\u001B[0m");
                
                try {
                    Registry registry = LocateRegistry.getRegistry("localhost", 1099);

                    IntermediateServerInterface server = (IntermediateServerInterface) registry.lookup("IntermediateServer");

                    String returnedServiceInformation;
                    returnedServiceInformation=server.sendMessage(referenceChain3[refChainIndex]);
                    System.out.println("\u001B[32m" + returnedServiceInformation + "\u001B[0m");
                } catch (Exception e) {
                    System.err.println("Client3 exception: " + e.toString());
                    e.printStackTrace();
                }
            
                
                refChainIndex++;

                // Send message back to Client1
                byte[] sendData = message.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, sendPort);
                clientSocket.send(sendPacket);

                // Reset the timer when a service is processed
                timerSeconds = 60;
                
                // Check if end of chain
                if ("FIN".equals(referenceChain3[refChainIndex])) {
                    System.out.println("\u001B[31mReceived FIN.");
                    ClientOn = false;
                }

                // Receive response from Client1 with a timeout
                try {
                    byte[] receiveData = new byte[1024];
                    DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                    clientSocket.receive(receivePacket);

                    String receivedMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());
                    System.out.println(receivedMessage);

                } catch (SocketTimeoutException e) {
                    System.out.println("\u001B[31mTimeout occurred. No response received from Client1.\u001B[0m");
                }

                

                Thread.sleep(1000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

