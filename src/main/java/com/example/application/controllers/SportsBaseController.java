package com.example.application.controllers;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.example.application.exceptions.SportsBaseException;
import com.example.application.services.mappers.AdminMap;
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

import com.example.application.controllers.dto.SportsBaseDTO;
import com.example.application.entities.SportsBase;
import com.example.application.services.SportsBaseService;

@RestController
@RequestMapping(path = "/api/v1/bases")
public class SportsBaseController {

    @Autowired
    private SportsBaseService sportsBaseService;



    @GetMapping
    public ResponseEntity<List<SportsBaseDTO>> getBases() {

			List<SportsBase> bases = sportsBaseService.getBases();
            if (bases.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            List<SportsBaseDTO> basesDTO = bases.stream().map(sportsBase -> SportsBaseMap.toDTO(sportsBase)).collect(Collectors.toList());
			return new ResponseEntity<>(basesDTO, HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<String> addBase(@RequestBody SportsBase sportsBase) {
        try {
            sportsBaseService.addNewBase(sportsBase);
            return new ResponseEntity<>("Base added successfully", HttpStatus.CREATED);
        } catch (SportsBaseException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<String> changeAdministratorOfTheBase(@RequestParam(name = "adminName") String name, @RequestParam(name = "idBase") Long id) {
        try {
            sportsBaseService.changeAdmin(name, id);
            return new ResponseEntity<>("Administrator changed successfully", HttpStatus.OK);
        } catch (SportsBaseException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping
    public ResponseEntity<String> deleteBase(@RequestParam(name = "id") Long id) {
        try {
            sportsBaseService.deleteBase(id);
            return new ResponseEntity<>("Base deleted successfully", HttpStatus.NO_CONTENT);
        } catch (SportsBaseException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}