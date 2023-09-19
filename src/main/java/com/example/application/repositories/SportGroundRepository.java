package com.example.application.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.application.entities.SportGround;

public interface SportGroundRepository extends JpaRepository<SportGround, Long> {

	@Query("SELECT g FROM SportGround g WHERE g.name = :name")
	public Optional<SportGround> findByName(@Param("name") String name);
	
	@Query("SELECT g FROM SportGround g JOIN g.sportsBase s WHERE s.name = :name")
	public Optional<SportGround> findBySportsBaseName(@Param("name") String name);


	
}
