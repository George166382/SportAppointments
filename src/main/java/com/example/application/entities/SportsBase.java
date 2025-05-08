package com.example.application.entities;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;



@Entity
@Getter
@Setter
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
    
    
    public SportsBase() {
	}

	public SportsBase(Long id) {
		this.id = id;
	}

}
