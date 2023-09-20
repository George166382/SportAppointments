package com.example.application.controllers.dto;

import java.util.List;

import com.example.application.entities.Person;
import com.example.application.entities.SportsBase;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
public class AdminDTO extends Person {
	
    private Long idAdmin;
    private List<SportsBaseDTO> basesList;
    
}
