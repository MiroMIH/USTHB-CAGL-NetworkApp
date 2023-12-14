package Clients;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class Client1 {
    private static int timerSeconds = 60;

    public static void main(String[] args) {
        try {
            DatagramSocket clientSocket = new DatagramSocket(9876);
            clientSocket.setSoTimeout(5000); // Set timeout to 5 seconds
            InetAddress serverAddress = InetAddress.getByName("localhost");
            int sendPort = 9877;
            String[] referenceChain1 = {"S7", "S10", "S1", "S2", "S4", "S3", "S0", "S6", "S12", "S11", "S8", "S5", "S2", "S1", "S0", "S5", "S1", "S7", "S9", "S2", "FIN"};
            int refChainIndex = 0;

            

            boolean ClientOn=true;
            while (ClientOn) {
            	String message = "\u001B[38;5;206mTOKEN FROM CLIENT 1\u001B[0m";
            	System.out.println("\u001B[32mProcessing Service: " + referenceChain1[refChainIndex] + "\u001B[0m");
                
                //Send Service to InterMedaireServer
                try {
                    Registry registry = LocateRegistry.getRegistry("localhost", 1099);

                    IntermediateServerInterface server = (IntermediateServerInterface) registry.lookup("IntermediateServer");

                    String returnedServiceInformation;
                    returnedServiceInformation=server.sendMessage(referenceChain1[refChainIndex]);
                    System.out.println("\u001B[32m" + returnedServiceInformation + "\u001B[0m");
                    
                } catch (Exception e) {
                    System.err.println("Client1 exception: " + e.toString());
                    e.printStackTrace();
                }
                
                refChainIndex++;

               
                
                // Send message to Client2
                byte[] sendData = message.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, sendPort);
                clientSocket.send(sendPacket);

                // Reset the timer when a service is processed
                timerSeconds = 60;

                
                //check if end of chain
                if ("FIN".equals(referenceChain1[refChainIndex])) {
                    System.out.println("\u001B[31mReceived FIN.");
                    ClientOn = false;
                }
                
                
                // a timeout
                try {
                    byte[] receiveData = new byte[1024];
                    DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                    clientSocket.receive(receivePacket);

                    // Process and print the received message
                    String receivedMessage = new String(receivePacket.getData(), 0, receivePacket.getLength());
                    System.out.println(receivedMessage);
                   
                } catch (SocketTimeoutException e) {
                    System.out.println("\u001B[31mTimeout occurred. No response received from Client2.\u001B[0m");
                    // Handle the timeout as needed
                }
                
               
                
                

  
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

