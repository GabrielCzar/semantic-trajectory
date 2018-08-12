/*
 * Copyright (c) 2018 Gabriel César
 */

package com.gabrielczar.semantic.services;

import com.gabrielczar.semantic.dto.UnMatchingResult;
import com.gabrielczar.semantic.entities.UnMatchingEntry;
import com.gabrielczar.semantic.repositories.UnMatchingEntryRepository;
import com.graphhopper.util.GPXEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.gabrielczar.semantic.utils.ApplicationUtilKt.generateToken;
import static com.gabrielczar.semantic.utils.EntryUtilsKt.convertUnMatchingEntryToGpxEntry;

/**
 * Service ratiocinated to un matching entries
 *
 * @author GabrielCzar
 * @since 1.0.0
 *
 * */
@Service
public class UnMatchingService {
    private final UnMatchingEntryRepository unMatchingEntryRepository;

    @Autowired
    public UnMatchingService(UnMatchingEntryRepository unMatchingEntryRepository) {
        this.unMatchingEntryRepository = unMatchingEntryRepository;
    }

    public UnMatchingResult saveUnMatchingEntriesFromCSV(Map<Integer, List<GPXEntry>> trajectories) {
        List<UnMatchingEntry> unMatchingEntries = new ArrayList<>();

        String token = generateToken();

        for (Integer entry:  trajectories.keySet()) {
            List<GPXEntry> entries = trajectories.get(entry);

            for (GPXEntry gpxEntry: entries) {
                unMatchingEntries.add(
                        new UnMatchingEntry(
                                token,
                                entry,
                                new Date(gpxEntry.getTime()),
                                gpxEntry.getLat(),
                                gpxEntry.getLon()
                        )
                );
            }
        }

        return new UnMatchingResult(
                token, unMatchingEntryRepository.saveAll(unMatchingEntries)
        );

    }

    /**
     * Search in database for entries based in a token identification
     *
     * @return HashMap where key is the ID from unMatched entry and
     *          your value is an GpxEntry converted from unMatched entry
     * */
    public Map<Integer, List<GPXEntry>> findEntriesByToken(String token) {
        Map<Integer, List<GPXEntry>> trajectories = new HashMap<>();
        List<UnMatchingEntry> entries = unMatchingEntryRepository.findAllByToken(token);
        for (UnMatchingEntry entry : entries) {
            Integer _id = entry.getId();

            if (!trajectories.containsKey(_id)) {
                trajectories.put(_id, new ArrayList<>());
            }

            if (trajectories.containsKey(_id)) {
                trajectories.get(_id).add(convertUnMatchingEntryToGpxEntry(entry));
            }

        }
        return trajectories;
    }
}
