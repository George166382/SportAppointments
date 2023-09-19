package com.example.application.repositories;

import java.sql.Date;
import java.sql.Time;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.application.entities.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

	@Query("SELECT a FROM Appointment a WHERE a.appointmentDate = :date AND a.appointmentHour = :hour")
	public Optional<Appointment> findByDateHour( @Param("date" ) Date date, @Param("hour") Time hour);
}
