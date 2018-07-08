package com.gabrielczar.semantic.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gabrielczar.semantic.utils.GeometryUtil;
import com.vividsolutions.jts.geom.Polygon;
import lombok.Data;

@Data
public class LocationCityDTO {
    private LocationDTO initial;

    @JsonProperty("final")
    private LocationDTO ending;

    private String city;

    public Polygon getGeometry() {
        return GeometryUtil.createPolygon(
                initial.getLatitude(),
                initial.getLongitude(),
                ending.getLatitude(),
                ending.getLongitude()
        );
    }
}
