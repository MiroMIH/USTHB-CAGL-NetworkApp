package com.CentralServer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.*;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import java.util.List;


/**
 * Session Bean implementation class ExampleServer
 */
@Stateless
@LocalBean
public class ExampleServer implements ExampleServerRemote {

    /**
     * Default constructor. 
     */
    public ExampleServer() {
        // TODO Auto-generated constructor stub
    	
    }
    @Override
    public String getMsg() {
    	
    	return "This is a message from Server"; 
    }
    
    //datebase part
    @PersistenceContext(unitName = "CentralServerPersistenceUnit")
    private EntityManager entityManager;
    
    @Override
    public List<Employee> getAllEmployees() {
        String jpql = "SELECT e FROM Employee e"; 

        TypedQuery<Employee> query = entityManager.createQuery(jpql, Employee.class);
        return query.getResultList();
    }
    
    @Override
    public List<Services> getAllServices() {
        String jpql = "SELECT s FROM Services s "; 

        TypedQuery<Services> query = entityManager.createQuery(jpql, Services.class);
        return query.getResultList();
    }
 
    
    //Registeration
    
    // Map to store registered servers
    //private Map<String, ServerInfo> registeredServers = new HashMap<>();
    

    public void registerServer(Servers Server) {
    	System.out.println("\u001B[31mRegistering server:" + Server + "\u001B[0m");
    	entityManager.persist(Server);
    }

    
    
	 
	    public void printRegisteredServers() {
//	        System.out.println("Registered Servers:");
//	        System.out.println("| Server Name | IP Address | Communication Port |");
//	        System.out.println("|-------------|------------|----------------------|");
//	
//	        for (Map.Entry<String, ServerInfo> entry : registeredServers.entrySet()) {
//	            ServerInfo serverInfo = entry.getValue();
//	            System.out.printf("| %-12s| %-11s| %-20s|\n",
//	                    serverInfo.getServerName(), serverInfo.getIpAddress(), serverInfo.getCommunicationPort());
//	        }
//	
//	        System.out.println("-----------------------------------------------");
	    }
	    
	 // Method to search the database based on service name and return information as a string
	    public String getServiceInformation(String serviceName) {

	        System.out.println("service name:" + serviceName);
	        //
	        String jpql = "SELECT s FROM Services s WHERE s.service_name = :serviceName";
	        System.out.println(jpql);
	        TypedQuery<Services> query = entityManager.createQuery(jpql, Services.class);
	        query.setParameter("serviceName", serviceName);

	        List<Services> services = query.getResultList();

	        if (!services.isEmpty()) {
	            // Service found, format the information as a string
	            Services service = services.get(0); 
	            return String.format("DATA:[Service Number: %s Service Name: %s Service Description: %s]",
	                    service.getServiceNumber(), service.getServiceName(), service.getServiceDescription());
	        } else {
	            // Service not found
	            return "Service not found in the database.";
	        }
	    }


	    
//    //class to save server information 
//    private static class ServerInfo {
//        private String serverName;
//        private String ipAddress;
//        private int communicationPort;
//
//        public ServerInfo(String serverName, String ipAddress, int communicationPort) {
//            this.serverName = serverName;
//            this.ipAddress = ipAddress;
//            this.communicationPort = communicationPort;
//        }
//        
//        public String getIpAddress() {
//            return ipAddress;
//        }
//
//        public String getServerName() {
//            return serverName;
//        }
//
//        public int getCommunicationPort() {
//            return communicationPort;
//        }
//        
//        @Override
//        public String toString() {
//            return "ServerInfo{" +
//                    "serverName='" + serverName + '\'' +
//                    ", ipAddress='" + ipAddress + '\'' +
//                    ", communicationPort=" + communicationPort +
//                    '}';
//        }
//    }
//
}
