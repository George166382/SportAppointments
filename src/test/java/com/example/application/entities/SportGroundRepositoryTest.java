package com.example.application.entities;

import static org.assertj.core.api.Assertions.assertThat;


import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import jakarta.transaction.Transactional;

@Transactional
@SpringBootTest
class SportGroundRepositoryTest {

	@Autowired
	private SportGroundRepository underTest;
	
	@Autowired
	private SportsBaseRepository repoForTesting;
	
	@BeforeEach
	void beforeEach()
	{
		SportsBase sb = new SportsBase(new ArrayList<SportGround>());
		sb.setName("Baza 3");
	    sb.setAddress("Strada Plopului");
	    
	    SportGround sg = new SportGround();
	    sg.setCapacity((long) 1000);
		sg.setName("tenis");
		sg.setSportsBase(sb);
		
		sb.getGroundsList().add(sg);
		
		repoForTesting.save(sb);
	}
	
	@Test
	void testGetBySportsBaseName() {
		
		Optional<SportGround> sg = underTest.findBySportsBaseName("Baza 3");
		assertThat(sg).isPresent();
		
	}
	
	@AfterEach
	void afterEach()
	{
		Optional<SportGround> obj = underTest.findBySportsBaseName("Baza 3");
		if(obj.isPresent())
		{
			SportGround sg = obj.get();
			SportsBase sb = sg.getSportsBase();
			repoForTesting.delete(sb);
		}
		else
		{
			throw new IllegalStateException("Sport Ground not found");
		}
	}

}
