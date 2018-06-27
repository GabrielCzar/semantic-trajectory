package com.gabrielczar.semantic.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/semantic")
public class TrajectoryController {

    @PostMapping("/upload")
    public ResponseEntity addSemanticByCsvFile(MultipartFile file) {

        return ResponseEntity.ok().build(); // return resource to data
    }

}
