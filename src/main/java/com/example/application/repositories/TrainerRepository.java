package com.example.application.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.application.entities.Trainer;

@Repository
public interface TrainerRepository extends JpaRepository<Trainer, Long> {
    
	@Query("SELECT t FROM Trainer t WHERE t.name = :name")
	public Optional<Trainer> findByName(@Param("name") String name);
	
	@Query("SELECT t FROM Trainer t JOIN t.sportGround g WHERE g.name = :name")
	public Optional<Trainer> findBySportGroundName(@Param("name") String name);
}
