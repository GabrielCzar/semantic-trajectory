package com.gabrielczar.semantic.entities

import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity(name = "un_matching_entries")
data class UnMatchingEntry (
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var serial : Long? = null,
        var token  : String,
        var id : Int,
        var date : Date,
        var latitude : Double,
        var longitude : Double
) {
    constructor(token: String, id: Int, date: Date, latitude: Double, longitude: Double) : this(null, token, id, date, latitude, longitude)
}
