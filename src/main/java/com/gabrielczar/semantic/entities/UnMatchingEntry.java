package com.gabrielczar.semantic.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;

@Data
@NoArgsConstructor
@Entity(name = "un_matching_entries")
public class UnMatchingEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long serial;

    private String token;

    private Integer id;

    private Date date;

    private Double latitude;

    private Double longitude;

    public UnMatchingEntry(String token, Integer id, Date date, Double latitude, Double longitude) {
        this.token = token;
        this.id = id;
        this.date = date;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
