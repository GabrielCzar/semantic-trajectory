package com.gabrielczar.semantic.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TrajectoryController {

    @GetMapping(value = {"/", ""})
    public ResponseEntity home() {
        return ResponseEntity.ok().body("{ \"message\":\"Hello World.\"} ");
    }
}
