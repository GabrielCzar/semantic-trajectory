package com.gabrielczar.semantic.entities;

import com.fasterxml.jackson.annotation.JsonBackReference
import javax.persistence.*

@Entity(name = "cities")
data class City (
        @GeneratedValue(strategy = GenerationType.IDENTITY) @Id val id : Int? = null,

        @Column(unique = true) var name : String,

        @JsonBackReference @OneToMany(mappedBy = "city", cascade = [(CascadeType.MERGE)])
        var interestPoints : MutableList<InterestPoint>? = null
){
    constructor(name : String) : this(id = null, name = name, interestPoints = null)
}
