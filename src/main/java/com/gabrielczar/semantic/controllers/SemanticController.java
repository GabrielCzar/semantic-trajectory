package com.gabrielczar.semantic.controllers;

import com.gabrielczar.semantic.entities.MatchingEntry;
import com.gabrielczar.semantic.repositories.MatchingEntryRepository;
import main.java.matching.controller.MatchingService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/api/semantics")
public class SemanticController {
    private final MatchingEntryRepository matchingEntryRepository;
    private final Double bufferSize = 50.0;
    private final Long RFMinTime = 120L; // seconds

    public SemanticController(MatchingEntryRepository matchingEntryRepository) {
        this.matchingEntryRepository = matchingEntryRepository;
    }

    /**
     * Add semantic to trajectories using the algorithm IB-SMoT
     *
     * @return Stops in a trajectory
     * */
    @PostMapping("/ib/token/{token}")
    public ResponseEntity executeAlgorithmIBSMoT(@PathVariable("token") String token) {
        final List<MatchingEntry> entries = matchingEntryRepository.findAllByToken(token);

        return ResponseEntity.ok("IB");
    }

    /**
     * Add semantic to trajectories using the algorithm CB-SMoT
     *
     * @param maxAverageSpeed maximum average speed in trajectory using km
     * @param maxSpeed maximum speed in a valid trajectory using km
     * @param minTime minimum time to consider stop
     *
     * @return Stops in a trajectory
     *
     * */
    @PostMapping("/cb/token/{token}")
    public ResponseEntity executeAlgorithmCBSMoT(
            @RequestParam("max_avg_speed") String maxAverageSpeed,
            @RequestParam("max_speed") String maxSpeed,
            @RequestParam("min_time") String minTime,
            @PathVariable("token") String token
    ) {
        final List<MatchingEntry> entries = matchingEntryRepository.findAllByToken(token);

        return ResponseEntity.ok("CB | " + maxAverageSpeed + " | " + maxSpeed + " | " + minTime);
    }
}
