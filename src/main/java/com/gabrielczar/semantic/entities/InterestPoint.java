package com.gabrielczar.semantic.entities;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigInteger;

@Data
@Entity(name = "interest_points")
public class InterestPoint {
    private static final GeometryFactory gf = new GeometryFactory();

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer serial;

    @NotNull @NotEmpty
    private String city;

    private BigInteger id;
    private String name;
    private String amenity;
    private Double lat;
    private Double lon;

    public Geometry getGeometry() {
        return gf.createPoint(
                new Coordinate(
                        lon,
                        lat
                )
        );
    }
}
