package com.example.application.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@jakarta.persistence.MappedSuperclass
@Getter
@Setter
public abstract class Person {
    private String name;
    private String email;
    private String password;
	
	  
    
}
