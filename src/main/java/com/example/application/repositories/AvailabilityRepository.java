package com.example.application.repositories;


import java.sql.Date;
import java.sql.Time;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.application.entities.Appointment;
import com.example.application.entities.TrainerAvailability;

@Repository
public interface AvailabilityRepository extends JpaRepository<TrainerAvailability, Long>{
	 @Query("SELECT a FROM TrainerAvailability a WHERE a.availableDate = :date AND a.availableHour = :hour")
	    Optional<TrainerAvailability> findByDateHour(@Param("date") Date date, @Param("hour") String hour);
}
