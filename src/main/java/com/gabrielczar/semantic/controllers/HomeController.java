package com.gabrielczar.semantic.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping(value = {"/", ""}, produces = "application/json")
    public ResponseEntity home() {
        return ResponseEntity.ok().body("{ \"message\":\"Access the endpoint /api.\"} ");
    }

}
