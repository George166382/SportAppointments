package com.example.application.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.application.controllers.dto.UserDTO;
import com.example.application.entities.Person;
import com.example.application.entities.User;
import com.example.application.repositories.UserRepository;
import com.example.application.services.mappers.UserMap;


@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserMap userMapper;
	


	 public List<UserDTO> getUsers() 
	 { 
		 List<User> users = userRepository.findAll();
		 List<UserDTO> usersListDTO = new ArrayList<>();
		 for(Person user : users)
		 {
			 UserDTO userDTO = userMapper.toDTO((User) user);
			 usersListDTO.add(userDTO);
		 }
		 return usersListDTO;
	 }

	 public User getUser(Long id) {
		 Optional<User> userOptional = userRepository.findById(id);
		 if(!userOptional.isPresent())
		 {
			 throw new IllegalStateException("This user does not exist");
		 }
		 User user = userOptional.get();;
		 return user;
	 }
	public void addUser(Person user) {
		Optional<User> userOptional = userRepository.findByName(((User)user).getName());
		if(userOptional.isPresent())
		{
			throw new IllegalStateException("This user already exists");
		}
		userRepository.save((User)user);
	}


	public void updateUser(String email, Long id) {
		Optional<User> userOptional = userRepository.findById(id);
		if(!userOptional.isPresent())
		{
			throw new IllegalStateException("This user does not exist");
		}
		User user = (User) userOptional.get();
		user.setEmail(email);
		userRepository.save(user);
		
	}




	public void deleteUser(Long id) {
		
		Optional<User> userOptional = userRepository.findById(id);
		if(!userOptional.isPresent())
		{
			throw new IllegalStateException("This user does not exist");
		}
		userRepository.deleteById(id);
	}
	 
	
}
