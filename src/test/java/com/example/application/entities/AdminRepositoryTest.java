package com.example.application.entities;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.event.annotation.AfterTestMethod;
import org.springframework.test.context.event.annotation.BeforeTestMethod;

import com.example.application.AppConfig;
import com.example.application.repositories.AdminRepository;
import com.example.application.repositories.UserRepository;

@SpringBootTest
class AdminRepositoryTest {

	ApplicationContext factory = new AnnotationConfigApplicationContext(AppConfig.class);
	Person a = factory.getBean(Admin.class);
	
	@Autowired
	AdminRepository rep;
	
	
	@BeforeEach
	void beforeTest() throws Exception
	{
		a.setName("Andrew");
		a.setEmail("andrew@gmail.com");
		a.setPassword("and");
		rep.save((Admin)a);
	}
	
	@Test
	void test() throws Exception{
		
		Optional<Admin> a_copy = rep.findByName("Andrew");
		System.out.println(a.getPassword());
		System.out.println(a_copy.get().getPassword());
		assertEquals(a.getPassword(),a_copy.get().getPassword());
		
	}
	
	@AfterEach
	 void afterTest() throws Exception
	{
		rep.delete((Admin) a);
	}
}
