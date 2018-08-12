package com.gabrielczar.semantic.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.gabrielczar.semantic.utils.createPolygon
import com.vividsolutions.jts.geom.Polygon

data class LocationCityDTO (
        val initial : LocationDTO,

        @JsonProperty("final")
        val ending : LocationDTO,

        var city : String

){
    fun getGeometry() : Polygon {
        return createPolygon(
                initial.latitude,
                initial.longitude,
                ending.latitude,
                ending.longitude
        )
    }
}