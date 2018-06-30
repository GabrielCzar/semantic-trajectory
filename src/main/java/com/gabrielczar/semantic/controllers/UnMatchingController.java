package com.gabrielczar.semantic.controllers;

import com.gabrielczar.semantic.entities.UnMatchingEntry;
import com.gabrielczar.semantic.services.CSVService;
import com.gabrielczar.semantic.services.UnMatchingService;
import com.graphhopper.util.GPXEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/un-matching-entries")
public class UnMatchingController {
    private final CSVService csvService;
    private final UnMatchingService unMatchingService;

    @Autowired
    public UnMatchingController(CSVService csvService, UnMatchingService unMatchingService
    ) {
        this.csvService = csvService;
        this.unMatchingService = unMatchingService;
    }

    @PostMapping("/upload")
    public ResponseEntity handleFileUpload(@RequestParam("file") MultipartFile multipartFile) {
        Map<Integer, List<GPXEntry>> trajectories = csvService.transformCSVinGpxEntries(multipartFile);
        List<UnMatchingEntry> unMatchingEntries = unMatchingService.saveUnMacthingEntriesFromCSV(trajectories);
        return new ResponseEntity<>(unMatchingEntries, HttpStatus.CREATED);
    }

}
