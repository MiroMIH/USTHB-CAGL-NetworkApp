package Clients;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;




public class Client2 {
    private static int timerSeconds = 60;

    public static void main(String[] args) {
        try {
            DatagramSocket clientSocket = new DatagramSocket(9877);
            clientSocket.setSoTimeout(5000); // Set timeout to 5 seconds
            InetAddress serverAddress = InetAddress.getByName("localhost");
            int sendPort = 9878;
            String[] referenceChain2 = {"S15", "S1", "S4", "S2", "S3", "S6", "S7", "S10", "S1", "S13", "S6", "S14", "S2", "S0", "S1", "S3", "S2", "S7", "S0", "S1", "FIN"};
            int refChainIndex = 0;

           

            boolean ClientOn = true;
            while (ClientOn) {
            	String message = "\u001B[38;5;206mTOKEN FROM CLIENT 2\u001B[0m";
            	System.out.println("\u001B[32mProcessing Service: " + referenceChain2[refChainIndex] + "\u001B[0m");
                
                try {
                    Registry registry = LocateRegistry.getRegistry("localhost", 1099);

                    IntermediateServerInterface server = (IntermediateServerInterface) registry.lookup("IntermediateServer");

                    String returnedServiceInformation;
                    returnedServiceInformation=server.sendMessage(referenceChain2[refChainIndex]);
                    System.out.println("\u001B[32m" + returnedServiceInformation + "\u001B[0m");
                } catch (Exception e) {
                    System.err.println("Client2 exception: " + e.toString());
                    e.printStackTrace();
                }
                
                refChainIndex++;

                // Send message to Client3
                byte[] sendData = message.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, sendPort);
                clientSocket.send(sendPacket);

                // Reset the timer when a service is processed
                timerSeconds = 60;

                
              // Check if end of chain
                if ("FIN".equals(referenceChain2[refChainIndex])) {
                    System.out.println("\u001B[31mReceived FIN.");
                    ClientOn = false;
                }
                
                // Receive response from Client3 with a timeout
                try {
                    byte[] receiveData = new byte[1024];
                    DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                    clientSocket.receive(receivePacket);
                    

                    // Process and print the received message
                    String receivedMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());
                    System.out.println(receivedMessage);

                } catch (SocketTimeoutException e) {
                    System.out.println("\u001B[31mTimeout occurred. No response received from Client3.\u001B[0m");
                    // Handle the timeout as needed
                }

                

                Thread.sleep(1000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}



