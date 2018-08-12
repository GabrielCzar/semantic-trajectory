package com.gabrielczar.semantic.controllers;

import com.gabrielczar.semantic.dto.ErrorWrapper;
import com.gabrielczar.semantic.dto.InterestPointDTO;
import com.gabrielczar.semantic.entities.City;
import com.gabrielczar.semantic.entities.InterestPoint;
import com.gabrielczar.semantic.repositories.CityRepository;
import com.gabrielczar.semantic.repositories.InterestPointRepository;
import com.gabrielczar.semantic.services.InterestPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/interest-points")
public class InterestPointController {
    private final Logger LOG = Logger.getLogger(this.getClass().getName());
    private final InterestPointService interestPointService;
    private final InterestPointRepository interestPointRepository;
    private final CityRepository cityRepository;

    @Autowired
    public InterestPointController(InterestPointService interestPointService, InterestPointRepository interestPointRepository, CityRepository cityRepository) {
        this.interestPointService = interestPointService;
        this.interestPointRepository = interestPointRepository;
        this.cityRepository = cityRepository;
    }

    @GetMapping("/city/{city}/fetch")
    public ResponseEntity listCityInterestPoints(@PathVariable String city) throws IOException {
        if (city == null)
            return ResponseEntity.badRequest().body(ErrorWrapper
                    .builder()
                    .field("city")
                    .error("Required city name")
                    .build());

        LOG.info("Show interest points of the " + city);

        List<InterestPointDTO> ips = interestPointService.list(city);

        LOG.info("Quantity of interest points of " + city + " is " + ips.size());

        LOG.info("Update interest points in database");

        final City cityFounded = cityRepository.findByName(city);

        if (cityFounded == null)
            return ResponseEntity.badRequest().body(ErrorWrapper
                    .builder()
                    .field("city")
                    .error("Not found")
                    .build());

        LOG.info("City founded is " + cityFounded.getName());

        if (!interestPointRepository.existsByCity(cityFounded)) {

            List<InterestPoint> interestPoints = ips
                    .parallelStream()
                    .map(ip -> new InterestPoint(cityFounded, ip))
                    .collect(Collectors.toList());

            interestPointRepository.saveAll(interestPoints);

            LOG.info("Database updated");

        } else {
            LOG.info("Database has updated previously");
        }

        return ResponseEntity.ok(ips);
    }

}
