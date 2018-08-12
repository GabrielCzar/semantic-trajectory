package com.gabrielczar.semantic.entities;

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonManagedReference
import com.gabrielczar.semantic.dto.InterestPointDTO
import com.gabrielczar.semantic.utils.createPoint
import com.vividsolutions.jts.geom.Point
import java.math.BigInteger
import javax.persistence.*

@Entity(name = "interest_points")
data class InterestPoint (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var serial : Int? = null,

    @JsonManagedReference
    @ManyToOne(cascade = [CascadeType.MERGE])
    var city : City? = null,

    var id : BigInteger,
    var name : String,
    var amenity : String,
    var lat : Double,
    var lon : Double
) {

    constructor(city : City, ip : InterestPointDTO) : this(
            city = city,
            id = ip.id,
            amenity = ip.amenity,
            lat = ip.lat,
            lon = ip.lon,
            name = ip.name
    )

    @JsonIgnore
    fun getGeometry() : Point {
        return createPoint(lat, lon)
    }

}
