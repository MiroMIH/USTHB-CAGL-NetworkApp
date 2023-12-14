package com.CentralServer;

import java.util.List;

import javax.*;
import jakarta.*;
import jakarta.ejb.Local;
import jakarta.ejb.Remote;

@Remote
public interface ExampleServerRemote {
	 String getMsg();
	 List<Employee> getAllEmployees();
	 List<Services> getAllServices();
	 void registerServer(Servers Server);
	 void printRegisteredServers();
	 String getServiceInformation(String serviceName);
}
