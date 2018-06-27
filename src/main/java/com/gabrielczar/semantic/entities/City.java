package com.gabrielczar.semantic.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"id", "interestPoints"})
@Entity(name = "cities")
public class City {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String name;

    @JsonBackReference
    @OneToMany(mappedBy = "city", cascade = CascadeType.MERGE)
    private List<InterestPoint> interestPoints;

    public City(String name) {
        this.name = name;
    }

}
