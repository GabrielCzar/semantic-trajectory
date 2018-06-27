package com.gabrielczar.semantic.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gabrielczar.semantic.dto.InterestPointDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import static com.gabrielczar.semantic.utils.Contants.URL_BASE__API_INTEREST_POINTS;
import static com.gabrielczar.semantic.utils.Contants.URL__API_CITY_INTEREST_POINTS;

@Service
public class InterestPointService {
    private final ObjectMapper mapper;

    @Autowired
    public InterestPointService(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    /**
     * Make request a return all interest points of the city
     *
     * @param city is the local to find this interest points
     * @return List<InterestPoint>
     * @throws IOException by invalid url or type
     */
    public List<InterestPointDTO> list(String city) throws IOException {
        final String url = URL_BASE__API_INTEREST_POINTS + URL__API_CITY_INTEREST_POINTS + "/" + city;

        TypeReference<?> typeReference = new TypeReference<List<InterestPointDTO>>(){};

        return mapper.readValue(new URL(url), typeReference);
    }

}
