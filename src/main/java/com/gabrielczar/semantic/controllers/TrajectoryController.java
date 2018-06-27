package com.gabrielczar.semantic.controllers;

import com.gabrielczar.semantic.services.CSVService;
import com.gabrielczar.semantic.services.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/semantic")
public class TrajectoryController {
    private final CSVService csvService;
    private final StorageService storageService;

    @Autowired
    public TrajectoryController(CSVService csvService, StorageService storageService) {
        this.csvService = csvService;
        this.storageService = storageService;
    }

    @PostMapping("/upload")
    public ResponseEntity handleFileUpload(@RequestParam("file") MultipartFile multipartFile) {
        String file = storageService.store(multipartFile);
        csvService.transformCSVinGpxEntries(file);
        return new ResponseEntity<>(multipartFile.getOriginalFilename(), HttpStatus.CREATED);
    }

}
