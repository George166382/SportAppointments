package com.example.application.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.application.controllers.dto.UserDTO;
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
		 for(User user : users)
		 {
			 UserDTO userDTO = userMapper.toDTO(user);
			 usersListDTO.add(userDTO);
		 }
		 return usersListDTO;
	 }


	public void addUser(User user) {
		Optional<User> userOptional = userRepository.findByName(user.getName());
		if(userOptional.isPresent())
		{
			throw new IllegalStateException("This user already exists");
		}
		userRepository.save(user);
	}


	public void updateUser(String email, Long id) {
		Optional<User> userOptional = userRepository.findById(id);
		if(!userOptional.isPresent())
		{
			throw new IllegalStateException("This user does not exist");
		}
		User user = userOptional.get();
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
