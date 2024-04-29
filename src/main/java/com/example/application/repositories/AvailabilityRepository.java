package com.example.application.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.application.entities.TrainerAvailability;

@Repository
public interface AvailabilityRepository extends JpaRepository<TrainerAvailability, Long>{
	
}
