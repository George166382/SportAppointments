package com.example.application;

import java.io.IOException;
import java.util.List;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomHeaderAuthFilter extends GenericFilterBean {
	String sessionID;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        var request = (HttpServletRequest) servletRequest;
        var response = (HttpServletResponse) servletResponse;
        if(request.getContextPath().equals("/api/v1/login")){
            sessionID = request.getSession().getId();
        }
        else {
                sessionID = request.getHeader("X-SESSION-ID");
        }

      
        // Perform authentication based on sessionId
        if (isValidSession(sessionID)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }
    
    static class Session {
        private String sessionId;
        private String type;
        private String username;

        // Getter and Setter methods for sessionId
        public String getSessionId() {
            return sessionId;
        }

        public void setSessionId(String sessionId) {
            this.sessionId = sessionId;
        }

        // Getter and Setter methods for type and username
        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }


    private boolean isValidSession(String sessionId) {
        if (sessionId == null) {
            return false; // or handle this case as appropriate
        }

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            List<Session> sessions = objectMapper.readValue(new File("data/sessions.json"), new TypeReference<List<Session>>() {});
            for (Session session : sessions) {
                if (sessionId.equals(session.getSessionId())) {
                    return true; 
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

}
