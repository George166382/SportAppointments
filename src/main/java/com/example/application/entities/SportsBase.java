package com.example.application.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;



@Entity
public class SportsBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;

    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Admin admin;
    
    @OneToMany(mappedBy = "sportsBase", cascade = CascadeType.ALL)
    private List<SportGround> groundsList;
    
    

	public SportsBase(List<SportGround> groundsList) {
		
		this.groundsList = groundsList;
	}

	public List<SportGround> getGroundsList() {
		return groundsList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
    
	
}
