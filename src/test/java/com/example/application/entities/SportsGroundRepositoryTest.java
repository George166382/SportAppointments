package com.example.application.entities;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.exceptions.base.MockitoException;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.example.application.AppConfig;
import com.example.application.repositories.AdminRepository;
import com.example.application.repositories.SportGroundRepository;
import com.example.application.repositories.SportsBaseRepository;

import jakarta.transaction.Transactional;


@SpringBootTest
class SportsGroundRepositoryTest {
	
	ApplicationContext factory = new AnnotationConfigApplicationContext(AppConfig.class);
	SportGround sportGround = factory.getBean(SportGround.class);
	
	@Autowired
	private SportGroundRepository underTest;

	@SuppressWarnings("removal")
	@BeforeEach
	void setUp() throws Exception {
		sportGround.setName("Field 1");  
		sportGround.setCapacity(new Long(100));
		underTest.save(sportGround);
	}
	
	@Test
	void test() {
		Optional<SportGround> sportGroundCopy = underTest.findByName(sportGround.getName());
		System.out.println(sportGround.getName());
		System.out.println(sportGroundCopy.get().getName());
		assertEquals(sportGround.getName(), sportGroundCopy.get().getName());
	}
	
	

	@AfterEach
	void tearDown() throws Exception {
		underTest.delete(sportGround);
	}


}
