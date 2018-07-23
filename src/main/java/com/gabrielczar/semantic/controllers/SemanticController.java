package com.gabrielczar.semantic.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/api/semantics")
public class SemanticController {
        private final Double bufferSize = 50.0; 
        private final Long RFMinTime = 120L; // seconds

        /**
         * Add semantic to trajectories using the algorithm IB-SMoT
         *
         * @return Stops in a trajectory
         * */
        @PostMapping("/ib")
        public ResponseEntity executeAlgorithmIBSMoT() {
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
        @PostMapping("/cb")
        public ResponseEntity executeAlgorithmCBSMoT(
                @RequestParam("max_avg_speed") String maxAverageSpeed,
                @RequestParam("max_speed") String maxSpeed,
                @RequestParam("min_time") String minTime
        ) {
                return ResponseEntity.ok("CB | " + maxAverageSpeed + " | " + maxSpeed + " | " + minTime);
        }
}
