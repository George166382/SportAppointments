package com.example.application.entities;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.application.repositories.AdminRepository;

@SpringBootTest
class AdminRepositoryTest {

	@Autowired
	private AdminRepository underTest;
	
	@BeforeEach
	void beforeEach()
	{
		Admin a = new Admin();
		a.setName("Drango");
		a.setEmail("drago@gmail.com");
		a.setPassword("drgo");
		underTest.save(a);
	}
	
	@Test
	void test() {
		Optional<Admin> a = underTest.findByName("Drango");
		if(a.isPresent())
		{
			System.out.println("Yes, is present");
		}
		else
		{
			throw new IllegalStateException("Not found");
		}
		
	}
	
	@AfterEach
	void afterEach()
	{
		Optional<Admin> a = underTest.findByName("Drango");
		if(a.isPresent())
		{
			Admin adminToDelete = a.get();
			underTest.deleteById(adminToDelete.getIdAdmin());;
		}
		else
		{
			throw new IllegalStateException("Not found");
		}
	}
}
