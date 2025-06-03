/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hrs.service;

import com.mycompany.hrs.dto.Country;
import com.mycompany.hrs.dto.CountryResponse;
import org.springframework.stereotype.Service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

/**
 *
 * @author Yaqoub Alshatti
 */

@Service
public class CountryService {

    private static final Logger logger = LoggerFactory.getLogger(CountryService.class);

    private final RestTemplate restTemplate;
    private static final String COUNTRIES_API_URL = "https://countriesnow.space/api/v0.1/countries/iso";

    @Autowired
    public CountryService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * Fetches all countries (with ISO codes) and returns List<Country>.
     */
    public List<Country> getAllCountries() {
        try {
            CountryResponse response =
                    restTemplate.getForObject(COUNTRIES_API_URL, CountryResponse.class);

            if (response != null && !response.isError()) {
                return response.getData();
            } else {
                logger.warn("API returned error or null: {}", response);
                return Collections.emptyList();
            }

        } catch (RestClientException ex) {
            logger.error("Failed to fetch countries: {}", ex.getMessage());
            return Collections.emptyList();
        }
    }

    /**
     * Returns the full wrapper (error, msg, data) if you need it.
     */
    public CountryResponse getRawApiResponse() {
        try {
            return restTemplate.getForObject(COUNTRIES_API_URL, CountryResponse.class);
        } catch (RestClientException ex) {
            logger.error("Failed to fetch raw response: {}", ex.getMessage());
            return null;
        }
    }
}