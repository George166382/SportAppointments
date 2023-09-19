package com.example.application.entities;

import lombok.Data;

@jakarta.persistence.MappedSuperclass
@Data
public abstract class Person {
    private String name;
    private String email;
    private String password;
	
	  
    
}
