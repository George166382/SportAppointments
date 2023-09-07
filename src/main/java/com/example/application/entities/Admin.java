package com.example.application.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Builder;




@Entity
@Builder
public class Admin extends Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_admin")
    private Long idAdmin;
    
    @OneToMany(mappedBy = "admin", cascade = CascadeType.ALL)
    private List<SportsBase> basesList;

	public List<SportsBase> getBasesList() {
		return basesList;
	}


	public Admin(List<SportsBase> basesList) {
		this.basesList = basesList;
	}

	public Long getIdAdmin() {
		return idAdmin;
	}

	public void setIdAdmin(Long idAdmin) {
		this.idAdmin = idAdmin;
	}


	public void setBasesList(List<SportsBase> basesList) {
		this.basesList = basesList;
	}



}

