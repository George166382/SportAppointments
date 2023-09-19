package com.example.application.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.example.application.entities.Admin;

public interface AdminRepository extends JpaRepository<Admin, Long> {

	
	
	@Query("SELECT a FROM Admin a WHERE a.name = :name")
	public Optional<Admin> findByName(@Param("name") String name);

	@Transactional
    @Modifying
    @Query("UPDATE Admin a SET a.name = ?1 WHERE a.id = ?2")
	public void updateAdmin(String name, Long id);


}
