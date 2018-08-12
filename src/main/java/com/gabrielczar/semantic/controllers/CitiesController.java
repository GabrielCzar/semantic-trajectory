package com.gabrielczar.semantic.controllers;

import com.gabrielczar.semantic.entities.City;
import com.gabrielczar.semantic.repositories.CityRepository;
import com.gabrielczar.semantic.services.CitiesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/cities")
public class CitiesController {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    private final CitiesService citiesService;
    private final CityRepository cityRepository;

    @Autowired
    public CitiesController(CitiesService citiesService, CityRepository cityRepository) {
        this.citiesService = citiesService;
        this.cityRepository = cityRepository;
    }

    @GetMapping("/fetch")
    public ResponseEntity citiesFromAPI() throws IOException {
        LOGGER.info("Show cities");

        List<String> cities = citiesService.listCities();

        LOGGER.info("Quantity of cities is " + cities.size());

        LOGGER.info("Update cities in database");

        final List<City> citiesInDB = cityRepository.findAll();

        cityRepository.saveAll(cities
                .parallelStream()
                .filter(cityName -> !citiesInDB.contains(new City(cityName)))
                .map(City::new)
                .collect(Collectors.toList()));

        LOGGER.info("Database updated");

        return ResponseEntity.ok(cities);
    }

    @GetMapping("/{city}/location/fetch")
    public ResponseEntity cityLocation(@PathVariable("city") String city) throws IOException {
        LOGGER.info("Retrieve location from " + city);
        return ResponseEntity.ok(citiesService.getLocation(city));
    }

}
