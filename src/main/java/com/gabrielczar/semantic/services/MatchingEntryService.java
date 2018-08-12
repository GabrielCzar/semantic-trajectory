package com.gabrielczar.semantic.services;

import com.gabrielczar.semantic.dto.LocationCityDTO;
import com.gabrielczar.semantic.entities.MatchingEntry;
import com.gabrielczar.semantic.utils.EntryUtilsKt;
import com.graphhopper.util.GPXEntry;
import main.java.matching.controller.MatchingService;
import main.java.matching.models.XFDEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static com.gabrielczar.semantic.utils.ConstantsUtils.GH_LOCATION_PREFIX;

@Service
public class MatchingEntryService {
    private final MatchingService matchingService;
    private final StorageService storageService;

    @Autowired
    public MatchingEntryService(MatchingService matchingService, StorageService storageService) {
        this.matchingService = matchingService;
        this.storageService = storageService;
    }

    public List<MatchingEntry> mapMatching(String osmPath, Map<Integer, List<GPXEntry>> unMatchingEntries, LocationCityDTO city) {
        final String GH_LOCATION = GH_LOCATION_PREFIX + city.getCity().toLowerCase();
        List<MatchingEntry> matchingEntries = new ArrayList<>();

        matchingService.configMatching(storageService.load(osmPath).toUri().getPath(), GH_LOCATION);

        for (Integer key: unMatchingEntries.keySet()) {
            try {
                List<XFDEntry> entries = matchingService.matchingEntries(unMatchingEntries.get(key), key);

                matchingEntries.addAll(
                        entries
                                .parallelStream()
                                .map(EntryUtilsKt::convertXFDEntryToMatchingEntry)
                                .collect(Collectors.toList())
                );
            } catch (Exception e) {
                Logger.getLogger(this.getClass().getName()).info(key + "SS >> " +  unMatchingEntries.get(key).size());
            }
        }

        return matchingEntries;
    }
}
