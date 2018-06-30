package com.gabrielczar.semantic.services;

import com.gabrielczar.semantic.entities.UnMatchingEntry;
import com.gabrielczar.semantic.repositories.UnMatchingEntryRepository;
import com.graphhopper.util.GPXEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.gabrielczar.semantic.utils.ApplicationUtil.generateToken;

@Service
public class UnMatchingService {
    private final UnMatchingEntryRepository unMatchingEntryRepository;

    @Autowired
    public UnMatchingService(UnMatchingEntryRepository unMatchingEntryRepository) {
        this.unMatchingEntryRepository = unMatchingEntryRepository;
    }

    public List<UnMatchingEntry> saveUnMacthingEntriesFromCSV(Map<Integer, List<GPXEntry>> trajectories) {
        List<UnMatchingEntry> unMatchingEntries = new ArrayList<>();

        for (Integer entry:  trajectories.keySet()) {
            String token = generateToken();
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

        return unMatchingEntryRepository.saveAll(unMatchingEntries);
    }
}
