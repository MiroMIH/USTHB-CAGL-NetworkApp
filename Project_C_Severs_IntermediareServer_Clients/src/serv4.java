import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.CentralServer.ExampleServerRemote;
import com.CentralServer.Servers;
import com.CentralServer.Services;

public class serv4 {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(9093)) {
            System.out.println("Server 4 ON ON PORT 9093");

            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

                    // Communication loop
                    while (true) {
                        // Receive data from the client
                        String receivedData = in.readLine();

                        // Check if the client sent "exit" or closed the connection
                        if (receivedData == null || receivedData.equalsIgnoreCase("exit")) {

                            break; // Exit the communication loop
                        }

                        System.out.println("Client says: " + receivedData);
                        Properties props = new Properties();
                		props.put("java.naming.factory.url.pkgs","org.jboss.ejb.client.naming");
                        InitialContext context = new InitialContext(props);

                        
                        ExampleServerRemote bean = (ExampleServerRemote)context.lookup("ejb:/Project_CentralServer/ExampleServer!com.CentralServer.ExampleServerRemote");
                        //before communication the server need to register with the centeral server
                        // Register the server with the central server
                        // Register the server with the central server
                        int serverName =4; // Provide the server name
                        String ipAddress = "localhost";// Provide the server IP address
                        String communicationPort = "9093"; // Provide the server communication port
                        
                        Servers server=new Servers(serverName,ipAddress,communicationPort);
                        
                        bean.registerServer(server);

                        
                        //simple method call to check if connection is made and working aproprietly
                        String msg = bean.getMsg();
                        System.out.println(msg);
                    
                
                        String messageToSendBack=bean.getServiceInformation(receivedData);
                        
                        
                        
                        // Send data to the client
                        System.out.println("\u001B[92m" + messageToSendBack + "\u001B[0m");
                        out.println(messageToSendBack);

                    }
                    
                    in.close();
                    out.close();
                    clientSocket.close();

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


