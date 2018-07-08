package com.gabrielczar.semantic.services;

import com.gabrielczar.semantic.dto.UnMatchingResult;
import com.gabrielczar.semantic.entities.UnMatchingEntry;
import com.gabrielczar.semantic.repositories.UnMatchingEntryRepository;
import com.graphhopper.util.GPXEntry;
import com.graphhopper.util.shapes.GHPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.gabrielczar.semantic.utils.ApplicationUtil.convertStringToLong;
import static com.gabrielczar.semantic.utils.ApplicationUtil.generateToken;
import static com.gabrielczar.semantic.utils.Contants.COMMA_DELIMITER;

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

        UnMatchingResult result = new UnMatchingResult();
        result.setEntries(unMatchingEntryRepository.saveAll(unMatchingEntries));
        result.setKey(token);

        return result;
    }

    public Map<Integer, List<GPXEntry>> findEntriesByToken(String token) {
        Map<Integer, List<GPXEntry>> trajectories = new HashMap<>();
        List<UnMatchingEntry> entries = unMatchingEntryRepository.findAllByToken(token);
        for (UnMatchingEntry entry : entries) {
            Integer _id = entry.getId();

            if (!trajectories.containsKey(_id)) {
                trajectories.put(_id, new ArrayList<>());
            }

            if (trajectories.containsKey(_id)) {
                trajectories.get(_id).add(
                        new GPXEntry(
                                new GHPoint(
                                        entry.getLatitude(),
                                        entry.getLongitude()
                                ),
                                entry.getDate().getTime()
                        )
                );
            }

        }
        return trajectories;
    }
}
