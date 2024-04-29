package com.example.application.entities;



import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

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
class SportsBaseRepositoryTest {

	ApplicationContext factory = new AnnotationConfigApplicationContext(AppConfig.class);
	SportsBase sportBase = factory.getBean(SportsBase.class);
	
	@Autowired
	private SportsBaseRepository underTest;

	@SuppressWarnings("removal")
	@BeforeEach
	void setUp() throws Exception {
		sportBase.setName("Baza 3");  
		sportBase.setAddress("Strada Noua");
		underTest.save(sportBase);
	}
	
	@Test
	void test1() {
		Optional<SportsBase> sportBaseCopy = underTest.findByAddress(sportBase.getAddress());
		System.out.println(sportBase.getAddress());
		System.out.println(sportBaseCopy.get().getAddress());
		assertEquals(sportBase.getAddress(), sportBaseCopy.get().getAddress());
	}
	
	@Test
	void test2() {
		Optional<SportsBase> sportBaseCopy = underTest.findByName(sportBase.getName());
		System.out.println(sportBase.getName());
		System.out.println(sportBaseCopy.get().getName());
		assertEquals(sportBase.getName(), sportBaseCopy.get().getName());
	}
	

	@AfterEach
	void tearDown() throws Exception {
		underTest.delete(sportBase);
	}
}
