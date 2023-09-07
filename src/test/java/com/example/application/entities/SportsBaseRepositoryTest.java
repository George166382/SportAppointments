package com.example.application.entities;



import java.util.ArrayList;

import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import jakarta.transaction.Transactional;

@Transactional
@ExtendWith(MockitoExtension.class)
@SpringBootTest
class SportsBaseRepositoryTest {

	@Autowired
	SportsBaseRepository underTest;

	@Autowired
	AdminRepository forAdmin;
	
	
	
	@BeforeEach
	void beforeEach() {
	    Admin a = new Admin(new ArrayList<SportsBase>());
	    a.setName("Drango");
	    a.setEmail("drago@gmail.com");
	    a.setPassword("drgo");

	    SportsBase sb = new SportsBase(new ArrayList<SportGround>());
	    sb.setAdmin(a);
	    sb.setName("Baza 3");
	    sb.setAddress("Strada Plopului");
	    
	    a.getBasesList().add(sb);

	    // First, save the Admin entity to the database
	    forAdmin.save(a);

	}


	@Test
	void testFindByName() {
		Optional<SportsBase> s = underTest.findByAddress("Strada Plopului");
		if(s.isPresent())
		{
			System.out.println("Yes, it is");
		}
		else
		{
			throw new IllegalStateException("Not found");
		}
	}

	
	@AfterEach
	void afterEach()
	{
		Optional<SportsBase> s = underTest.findByAddress("Strada Plopului");
		if(s.isPresent())
		{
			SportsBase sb = s.get();
			Admin a = sb.getAdmin();
			forAdmin.delete(a);
			
		}
		else
		{
			throw new IllegalStateException("Not found");
		}
	}
}
