package com.gabrielczar.semantic.entities;

import com.gabrielczar.semantic.utils.createPoint
import com.vividsolutions.jts.geom.Point
import java.math.BigInteger
import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity(name = "matching_entries")
data class MatchingEntry (
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        var serial : Long? = null,
        var token : String? = null,
        var id : Int,
        var latitude : Double,
        var longitude : Double,
        var dateTime : Date,
        var edgeId : BigInteger,
        var geometry : Point
){

    fun createGeometry() {
        geometry = createPoint(latitude, longitude)
    }
}
