package com.gabrielczar.semantic.controllers;

import com.gabrielczar.semantic.entities.City;
import com.gabrielczar.semantic.repositories.CityRepository;
import com.gabrielczar.semantic.services.CitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/cities")
public class CitiesController {
    private final Logger LOG = Logger.getLogger(this.getClass().getName());
    private final CitiesService citiesService;
    private final CityRepository cityRepository;

    @Autowired
    public CitiesController(CitiesService citiesService, CityRepository cityRepository) {
        this.citiesService = citiesService;
        this.cityRepository = cityRepository;
    }

    @GetMapping("/fetch")
    public ResponseEntity citiesFromAPI() throws IOException {
        LOG.info("Show cities");

        List<String> cities = citiesService.listCities();

        LOG.info("Quantity of cities is " + cities.size());

        LOG.info("Update cities in database");

        cityRepository.saveAll(cities
                .parallelStream()
                .map(City::new)
                .collect(Collectors.toList()));

        LOG.info("Database updated");

        return ResponseEntity.ok(cities);
    }

}
