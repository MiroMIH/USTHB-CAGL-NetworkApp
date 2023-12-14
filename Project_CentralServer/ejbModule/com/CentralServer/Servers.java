package com.CentralServer;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "table_Servers")
public class Servers implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "serverName_Column")
    private int serverName;

    @Column(name = "serverIP_Column")
    private String serverIP;

    @Column(name = "serverPort_Column")
    private String serverPort;

    public Servers(int serverName, String serverIP, String serverPort) {
        super();
        this.serverName = serverName;
        this.serverIP = serverIP;
        this.serverPort = serverPort;
    }

    public int getServerName() {
        return serverName;
    }

    public void setServerName(int serverName) {
        this.serverName = serverName;
    }

    public String getServerIP() {
        return serverIP;
    }

    public void setServerIP(String serverIP) {
        this.serverIP = serverIP;
    }

    public String getServerPort() {
        return serverPort;
    }

    public void setServerPort(String serverPort) {
        this.serverPort = serverPort;
    }
}

