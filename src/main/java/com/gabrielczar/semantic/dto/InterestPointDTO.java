package com.gabrielczar.semantic.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vividsolutions.jts.geom.Geometry;
import lombok.Data;

import java.math.BigInteger;

@Data
public class InterestPointDTO {
    private BigInteger id;
    private String amenity;
    private String name;
    private Double lat;
    private Double lon;

    @JsonIgnore
    private Geometry geometry;
}
