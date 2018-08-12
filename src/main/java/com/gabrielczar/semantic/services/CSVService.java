package com.gabrielczar.semantic.services;

import com.graphhopper.util.GPXEntry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.gabrielczar.semantic.utils.ConstantsUtils.COMMA_DELIMITER;
import static com.gabrielczar.semantic.utils.EntryUtilsKt.createGpxEntry;
import static com.gabrielczar.semantic.utils.StringUtilsKt.convertDateStringToLong;

@Service
public class CSVService {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    public Map<Integer, List<GPXEntry>> transformCSVinGpxEntries(MultipartFile file) {
        Map<Integer, List<GPXEntry>> trajectories = new HashMap<>();
        BufferedReader br = null;

        try {
            InputStream in = file.getInputStream();
            br = new BufferedReader(new InputStreamReader(in));
            String line;

            br.readLine(); // HEADER offset

            LOGGER.info("Transform CSV in GPX Entries");

            while ((line = br.readLine()) != null) {

                String[] details = line.split(COMMA_DELIMITER);

                if (details.length > 0) {
                    Integer _id = Integer.parseInt(details[0]);

                    LOGGER.info("Key Entered >> " + _id);

                    if (!trajectories.containsKey(_id)) {
                        trajectories.put(_id, new ArrayList<>());
                    }
                    if (trajectories.containsKey(_id)) {
                        trajectories.get(_id)
                                .add(createGpxEntry(
                                        Double.parseDouble(details[3]),
                                        Double.parseDouble(details[2]),
                                        convertDateStringToLong(details[1])
                                )
                        );
                    }
                }
            }
        }
        catch (IOException e) { LOGGER.error("Error in read csv with gpx entries => ", e); }
        finally { try { if (br != null) br.close(); } catch (IOException e) { LOGGER.error("Error to close buffer => ", e); } }

        return trajectories;
    }

}
