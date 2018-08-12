package com.gabrielczar.semantic.dto;

import com.fasterxml.jackson.annotation.JsonIgnore
import com.vividsolutions.jts.geom.Geometry
import java.math.BigInteger

data class InterestPointDTO (
    val id: BigInteger,
    val amenity : String,
    val name : String,
    val lat : Double,
    val lon : Double,

    @JsonIgnore
    val geometry : Geometry
)
