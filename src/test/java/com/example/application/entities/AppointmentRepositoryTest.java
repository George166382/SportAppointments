package com.example.application.entities;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.sql.Time;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.example.application.AppConfig;
import com.example.application.repositories.AppointmentRepository;

@SpringBootTest
class AppointmentRepositoryTest {

	ApplicationContext factory = new AnnotationConfigApplicationContext(AppConfig.class);
	Appointment appointment = factory.getBean(Appointment.class);
	long millisecondsSinceEpoch = System.currentTimeMillis();
	Date date = new Date(millisecondsSinceEpoch);
	Time hour = new Time(millisecondsSinceEpoch);
	
	
	@Autowired
	AppointmentRepository underTest;
	
	@BeforeEach
	void setUp() throws Exception {
		appointment.setAppointmentDate(date);
		appointment.setAppointmentHour(hour);
		underTest.save(appointment);
	}
	
	
	@Test
	void test1() {
	    Optional<Appointment> appointment_copy = underTest.findByDateHour(date, hour);
	    System.out.println(appointment.getAppointmentDate());
	    System.out.println(appointment_copy.get().getAppointmentDate());
	    
	    assertEquals(appointment.getAppointmentDate().toLocalDate(), appointment_copy.get().getAppointmentDate().toLocalDate());
	}

	
	@Test
	void test2() {
		Optional<Appointment> appointment_copy = underTest.findByDateHour(date, hour);
		System.out.println(appointment.getAppointmentHour());
		System.out.println(appointment_copy.get().getAppointmentHour());
		assertEquals(appointment.getAppointmentHour().toLocalTime(),appointment_copy.get().getAppointmentHour().toLocalTime());
	}

	@AfterEach
	void tearDown() throws Exception {
		underTest.delete(appointment);
	}


}
