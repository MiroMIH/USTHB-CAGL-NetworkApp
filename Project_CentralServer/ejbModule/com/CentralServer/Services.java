package com.CentralServer;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.io.Serializable;

@Entity
public class Services implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int service_number;
    private String service_name;
    private String service_description;

    public int getServiceNumber() {
        return service_number;
    }

    public String getServiceName() {
        return service_name;
    }

    public String getServiceDescription() {
        return service_description;
    }

    // Additional getters and setters as needed
}
