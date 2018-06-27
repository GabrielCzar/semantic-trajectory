package com.gabrielczar.semantic.services;

import com.gabrielczar.semantic.beans.DataBean;
import com.graphhopper.util.GPXEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Service
public class CSVService {
    private final Logger LOG = Logger.getLogger(this.getClass().getName());
    private final StorageService storageService;

    @Autowired
    public CSVService(StorageService storageService) {
        this.storageService = storageService;
    }

    public Map<Integer, List<GPXEntry>> transformCSVinGpxEntries(String file) {
        Map<Integer, List<GPXEntry>> entries = new HashMap<>();

        /// make the pre process
        Path path = storageService.load(file);
        List<DataBean> dataset = handleContent(path);

        return entries;
    }


    public List<DataBean> handleContent(Path path) {

        /// read csv file

        return new ArrayList<>();
    }
}
