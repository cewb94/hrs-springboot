/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hrs.controller;


import com.mycompany.hrs.dto.Country;
import com.mycompany.hrs.dto.CountryResponse;
import com.mycompany.hrs.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 * @author Yaqoub Alshatti
 */
@RestController
@RequestMapping("/api")
public class CountryRestController {
    
    
    private final CountryService countryService;

    @Autowired
    public CountryRestController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping("/countries")
    public ResponseEntity<List<Country>> getAllCountries() {
        List<Country> countries = countryService.getAllCountries();
        return ResponseEntity.ok(countries);
    }

    @GetMapping("/countries/raw")
    public ResponseEntity<CountryResponse> getRawCountryResponse() {
        CountryResponse resp = countryService.getRawApiResponse();
        if (resp == null) {
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok(resp);
    }
    
}
