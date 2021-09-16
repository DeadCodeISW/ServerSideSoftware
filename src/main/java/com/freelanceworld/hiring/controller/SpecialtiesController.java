package com.freelanceworld.hiring.controller;


import com.freelanceworld.hiring.domain.model.Specialty;
import com.freelanceworld.hiring.domain.repository.SpecialtyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/specialties")
public class SpecialtiesController {
    @Autowired
    private SpecialtyRepository specialtyRepository;

    @GetMapping()
    public List<Specialty> getAllOffers(){
        return specialtyRepository.findAll();
    }
}
