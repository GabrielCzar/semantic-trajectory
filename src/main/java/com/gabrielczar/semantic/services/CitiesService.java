package com.gabrielczar.semantic.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import static com.gabrielczar.semantic.Contants.URL_BASE__API_INTEREST_POINTS;
import static com.gabrielczar.semantic.Contants.URL__API_CITIES;

@Service
public class CitiesService {

    private final ObjectMapper mapper;

    @Autowired
    public CitiesService(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    public List<String> listCities() throws IOException {
        final String url = URL_BASE__API_INTEREST_POINTS + URL__API_CITIES;
        TypeReference<?> typeReference = new TypeReference<List<String>>(){};

        return mapper.readValue(new URL(url), typeReference);
    }
}
