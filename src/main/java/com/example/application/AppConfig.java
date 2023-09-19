package com.example.application;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.application.entities.Admin;
import com.example.application.entities.Appointment;
import com.example.application.entities.SportGround;
import com.example.application.entities.SportsBase;
import com.example.application.entities.User;

@Configuration
public class AppConfig {
	@Bean
	public User getUser()
	{
		return new User();
	}
	@Bean
	public Admin getAdmin()
	{
		return new Admin();
	}
	
	@Bean
	public SportsBase getSportsBase()
	{
		return new SportsBase();
	}
	
	@Bean
	public SportGround getSportGround()
	{
		return new SportGround();
	}
	
	@Bean
	public Appointment getAppointment()
	{
		return new Appointment();
	}
}





