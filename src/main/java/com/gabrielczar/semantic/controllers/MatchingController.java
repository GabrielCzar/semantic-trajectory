package com.gabrielczar.semantic.controllers;

import com.gabrielczar.semantic.dto.LocationCityDTO;
import com.gabrielczar.semantic.entities.MatchingEntry;
import com.gabrielczar.semantic.repositories.MatchingEntryRepository;
import com.gabrielczar.semantic.services.CitiesService;
import com.gabrielczar.semantic.services.MatchingEntryService;
import com.gabrielczar.semantic.services.StorageService;
import com.gabrielczar.semantic.services.UnMatchingService;
import com.graphhopper.util.GPXEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/matching-entries")
public class MatchingController {
    private final MatchingEntryRepository matchingEntryRepository;
    private final MatchingEntryService matchingEntryService;
    private final UnMatchingService unMatchingService;
    private final CitiesService citiesService;
    private final StorageService storageService;

    @Autowired
    public MatchingController(MatchingEntryRepository matchingEntryRepository,
                              UnMatchingService unMatchingService,
                              MatchingEntryService matchingEntryService, CitiesService citiesService, StorageService storageService) {
        this.matchingEntryService = matchingEntryService;
        this.matchingEntryRepository = matchingEntryRepository;
        this.unMatchingService = unMatchingService;
        this.citiesService = citiesService;
        this.storageService = storageService;
    }

    @PutMapping("/map-matching/{token}/city/{city}")
    public ResponseEntity matchingEntries(@PathVariable("token") String token,
                                          @PathVariable("city") String city,
                                          @RequestParam("file") MultipartFile osm) throws IOException {

        String osmPath = storageService.store(osm);

        LocationCityDTO locationCity = citiesService.getLocation(city);
        locationCity.setCity(city);

        Map<Integer, List<GPXEntry>> unMatchingEntries = unMatchingService.findEntriesByToken(token);

        List<MatchingEntry> entries = matchingEntryService.mapMatching(osmPath, unMatchingEntries, locationCity);

        List<MatchingEntry> entriesSaved = matchingEntryRepository.saveAll(
                entries
                        .parallelStream()
                        .peek(matchingEntry -> matchingEntry.setToken(token))
                        .collect(Collectors.toList())
        );

        return ResponseEntity.ok(entriesSaved.size());
    }
}
