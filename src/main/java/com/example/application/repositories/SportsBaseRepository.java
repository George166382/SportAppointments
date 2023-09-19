package com.example.application.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.application.entities.SportsBase;

public interface SportsBaseRepository extends JpaRepository<SportsBase, Long> {


	@Query("SELECT s FROM SportsBase s WHERE s.address = :address")
	public Optional<SportsBase> findByAddress(@Param("address") String address);
}
