package com.gabrielczar.semantic.services;

import com.graphhopper.util.GPXEntry;
import com.graphhopper.util.shapes.GHPoint;
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
import java.util.logging.Logger;

import static com.gabrielczar.semantic.utils.ApplicationUtil.convertStringToLong;
import static com.gabrielczar.semantic.utils.Contants.COMMA_DELIMITER;

@Service
public class CSVService {
    private final Logger LOG = Logger.getLogger(this.getClass().getName());

    public Map<Integer, List<GPXEntry>> transformCSVinGpxEntries(MultipartFile file) {
        Map<Integer, List<GPXEntry>> trajectories = new HashMap<>();
        BufferedReader br = null;

        try {
            InputStream in = file.getInputStream();
            br = new BufferedReader(new InputStreamReader(in));
            String line;

            br.readLine(); // HEADER offset

            while ((line = br.readLine()) != null) {

                String[] details = line.split(COMMA_DELIMITER);

                if (details.length > 0) {
                    Integer _id = Integer.parseInt(details[0]);

                    LOG.info("Entrou >> " + _id);

                    if (!trajectories.containsKey(_id)) {
                        trajectories.put(_id, new ArrayList<>());
                    }
                    if (trajectories.containsKey(_id)) {
                        trajectories.get(_id).add(
                                new GPXEntry(
                                        new GHPoint(
                                                Double.parseDouble(details[3]),
                                                Double.parseDouble(details[2])
                                        ),
                                        convertStringToLong(details[1])
                                )
                        );
                    }
                }
            }
        }
        catch (IOException e) { e.printStackTrace(); LOG.severe("Error in read csv with gpx entries");}
        finally { try { if (br != null) br.close(); } catch (IOException e) { e.printStackTrace(); } }

        return trajectories;
    }

}
