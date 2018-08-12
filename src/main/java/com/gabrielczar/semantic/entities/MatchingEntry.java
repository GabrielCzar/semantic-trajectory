package com.gabrielczar.semantic.entities;

import com.vividsolutions.jts.geom.Point;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigInteger;
import java.sql.Date;

import static com.gabrielczar.semantic.utils.GeometryUtilsKt.createPoint;

@Data
@Entity(name = "matching_entries")
public class MatchingEntry {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long serial;

    private String token;

    private Integer id;
    private Double latitude;
    private Double longitude;
    private Date dateTime;
    private BigInteger edgeId;

    private Point geometry;

    public void createGeometry() {
        this.geometry = createPoint(latitude, longitude);
    }
}
