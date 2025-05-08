package com.example.application.controllers;

import java.util.ArrayList;
import java.util.List;

import com.example.application.exceptions.SportGroundException;
import com.example.application.services.mappers.SportGroundMap;
import com.example.application.services.mappers.SportsBaseMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.application.controllers.dto.SportGroundDTO;
import com.example.application.entities.SportGround;
import com.example.application.services.SportGroundService;

@RestController
@RequestMapping(path = "/api/v1/grounds")
public class SportGroundController {

    @Autowired
    private SportGroundService sportGroundService;


    @GetMapping
    public ResponseEntity<List<SportGroundDTO>> getSportGrounds() {

            List<SportGround> grounds = sportGroundService.getSportGrounds();
            if (grounds.isEmpty())
            {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            List<SportGroundDTO> groundsDTO = grounds.stream().map(sportGround -> SportGroundMap.toDTO(sportGround)).toList();

            return new ResponseEntity<>(groundsDTO, HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<String> addSportGround(@RequestBody SportGround sportGround) {
        try {
            sportGroundService.addSportGround(sportGround);
            return new ResponseEntity<>("Sport ground added successfully", HttpStatus.CREATED);
        } catch (SportGroundException e) {
            if (e.getMessage().equals("Sport ground with this name already exists")) {
                return new ResponseEntity<>(e.toString(), HttpStatus.CONFLICT);
            }
            return new ResponseEntity<>(e.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<String> updateSportGround(@RequestParam(name = "name") String name, @RequestParam(name = "idSg") Long id) {
        try {
            sportGroundService.updateSportGround(name, id);
            return new ResponseEntity<>("Sport ground updated successfully", HttpStatus.OK);
        } catch (SportGroundException e) {
            if (e.getMessage().equals("Sport ground not found"))
            {
                return new ResponseEntity<>(e.toString(), HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(e.toString(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping
    public ResponseEntity<String> deleteSportGround(@RequestParam(name = "id") Long id) {
        try {
            sportGroundService.deleteSportGround(id);
            return new ResponseEntity<>("Sport ground deleted successfully", HttpStatus.NO_CONTENT);
        } catch (SportGroundException e) {
            return new ResponseEntity<>(e.toString(), HttpStatus.NOT_FOUND);
        }
    }
}
