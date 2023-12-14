package Clients;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IntermediateServerInterface extends Remote {
    String sendMessage(String message) throws RemoteException;
    
}
