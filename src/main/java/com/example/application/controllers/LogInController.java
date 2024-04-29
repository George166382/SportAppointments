package com.example.application.controllers;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.application.repositories.AdminRepository;
import com.example.application.repositories.UserRepository;
import com.example.application.services.LogInService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.MapType;
import com.fasterxml.jackson.databind.type.TypeFactory;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.*;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@RestController
@RequestMapping("/api/v1/login")
public class LogInController {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	private LogInService lgIn ;
	
	
	@PostMapping
	public ResponseEntity<?> logIn(HttpServletRequest request, HttpServletResponse response,  @RequestHeader("Authorization") String authorizationHeader) {
	    try {
	        HttpSession session = request.getSession();
	        String sessionId = session.getId();

	        if (authorizationHeader != null && authorizationHeader.startsWith("Basic ")) {
                String base64Credentials = authorizationHeader.substring("Basic ".length()).trim();
                String credentials = new String(Base64.getDecoder().decode(base64Credentials));

                final String[] values = credentials.split(":", 2);

                if (values.length == 2) {
                    String name = values[0];
                    String password = values[1];

                    if (userRepository.findByName(name).isPresent() && password.equals(userRepository.findByName(name).get().getPassword())) {
        	            JSONObject sessionData = new JSONObject();
        	            sessionData.put("sessionId", sessionId);
        	            sessionData.put("username", name);
        	            sessionData.put("type", "User");
        	            lgIn.setSessions(lgIn.getSessions().put(sessionData));
        	            lgIn.writeSessionDataToFile();
        	            System.out.println(sessionId.toString());
        	            response.setContentType("application/json");
        	            response.setHeader("X-SessionId", sessionId.toString());
        	            //response.addHeader("sessionId", sessionId);
        	            System.out.println(response.toString());
        	            return ResponseEntity.ok().body("{\"message\": \"User logged in successfully.\", \"type\": \"User\"}");
        	        } else if (adminRepository.findByName(name).isPresent() && password.equals(adminRepository.findByName(name).get().getPassword())) {
        	            JSONObject sessionData = new JSONObject();
        	            sessionData.put("sessionId", sessionId);
        	            sessionData.put("username", name);
        	            sessionData.put("type", "Admin");
        	            lgIn.setSessions(lgIn.getSessions().put(sessionData));
        	            lgIn.writeSessionDataToFile();
        	         // Set Content-Type header to indicate JSON response
        	            response.setContentType("application/json");
        	            response.setHeader("X-SessionId", sessionId.toString());
        	            return ResponseEntity.ok().body("{\"message\": \"Admin logged in successfully.\", \"type\": \"Admin\"}");
        	        } else {
        	            return ResponseEntity.badRequest().body("{\"error\": \"Invalid username or password\"}");
        	        }
                }
            }
           
            response.setHeader("WWW-Authenticate", "Basic realm=\"User Visible Realm\"");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("{\"error\": \"Authorization header is missing or invalid\"}");
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("{\"error\": \"An error occurred: " + e.getMessage() + "\"}");
	    }
	}

	



}
