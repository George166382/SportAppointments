package com.example.application.services;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.stereotype.Service;

@Service
public class LogInService {
	private final String SESSIONS_FILE = "data/sessions.json";

	private JSONArray sessions; 

	public LogInService() {
	    this.sessions = readExistingData();
	}

	public JSONArray getSessions() {
		return sessions;
	}
	
	public void setSessions(JSONArray sessions) {
		this.sessions = sessions;
	}

	public void writeSessionDataToFile() {
	    try (FileWriter writer = new FileWriter(SESSIONS_FILE)) {
	        writer.write(sessions.toString());
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

	public JSONArray readExistingData() {
	    JSONArray jsonArray = new JSONArray();
	    try (BufferedReader reader = new BufferedReader(new FileReader(SESSIONS_FILE))) {
	        StringBuilder jsonString = new StringBuilder();
	        String line;
	        while ((line = reader.readLine()) != null) {
	            jsonString.append(line);
	        }
	        if (jsonString.length() > 0) {
	            jsonArray = new JSONArray(jsonString.toString());
	        }
	    } catch (FileNotFoundException e) {
	        System.out.println("Sessions file is empty or does not exist.");
	    } catch (IOException | JSONException e) {
	        e.printStackTrace();
	    }
	    return jsonArray;
	}


	
}
