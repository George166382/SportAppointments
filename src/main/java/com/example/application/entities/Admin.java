package com.example.application.entities;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;




@Getter
@Setter
@Entity
@Table(name = "basic_admin")
public class Admin extends Person{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ADMIN")
   
    private Long idAdmin;
    
   
    @OneToMany(mappedBy = "admin", cascade = CascadeType.ALL)
   
    private List<SportsBase> basesList;
    
    
   public Admin() {
	}
	  

	public Admin(Long idAdmin) {
		this.idAdmin = idAdmin;
	}

	
}

