package com.CentralServer;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.io.Serializable;

@Entity
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int employee_id;
    private String first_name;
    private String last_name;

    public int getEmployeeId() {
        return employee_id;
    }

    public String getName() {
        return first_name;
    }

    public String getTitle() {
        return last_name;
    }

    // Additional getters and setters as needed
}


  

