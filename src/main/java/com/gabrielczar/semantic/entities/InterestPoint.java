package com.gabrielczar.semantic.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.gabrielczar.semantic.dto.InterestPointDTO;
import com.gabrielczar.semantic.utils.GeometryUtil;
import com.vividsolutions.jts.geom.Point;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigInteger;

@Data
@NoArgsConstructor
@Entity(name = "interest_points")
public class InterestPoint {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer serial;

    @JsonManagedReference
    @ManyToOne(cascade = CascadeType.MERGE)
    private City city;

    private BigInteger id;
    private String name;
    private String amenity;
    private Double lat;
    private Double lon;

    @JsonIgnore
    public Point getGeometry() {
        return GeometryUtil.createPoint(lat, lon);
    }

    public InterestPoint(City city, InterestPointDTO ip) {
        this.city = city;
        this.id = ip.getId();
        this.amenity = ip.getAmenity();
        this.lat = ip.getLat();
        this.lon = ip.getLon();
        this.name = ip.getName();
    }
}
