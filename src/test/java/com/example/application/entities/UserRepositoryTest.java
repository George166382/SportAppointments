package com.example.application.entities;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.example.application.AppConfig;
import com.example.application.repositories.AdminRepository;
import com.example.application.repositories.UserRepository;

@SpringBootTest
class UserRepositoryTest {
	
	ApplicationContext factory = new AnnotationConfigApplicationContext(AppConfig.class);
	Person u =  factory.getBean(User.class);
	
	@Autowired
	 private UserRepository underTestU;
	
	@BeforeEach
	void beforeEach()
	{
		u.setName("Andrew");
		u.setEmail("andrew@gmail.com");
		u.setPassword("andr");
		underTestU.save((User) u);
	}
	
	@Test
	void test() throws Exception{
		
		Optional<User> u_copy = underTestU.findByName("Andrew");
		System.out.println(u.getPassword());
		System.out.println(u_copy.get().getPassword());
		assertEquals(u.getPassword(),u_copy.get().getPassword());
		
	}
	
	@AfterEach
	 void afterTest() throws Exception
	{
		underTestU.delete((User) u);
	}

}
