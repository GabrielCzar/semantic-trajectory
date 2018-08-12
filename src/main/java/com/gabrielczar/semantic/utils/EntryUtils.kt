/*
 * Copyright (c) 2018 Gabriel César
 */

package com.gabrielczar.semantic.utils

import com.gabrielczar.semantic.entities.MatchingEntry
import com.gabrielczar.semantic.entities.UnMatchingEntry
import com.graphhopper.util.GPXEntry
import com.graphhopper.util.shapes.GHPoint
import main.java.matching.models.XFDEntry
import java.sql.Date

/**
 * Util functions to Entry Types
 *
 * @author GabrielCzar
 * @since 1.0.0
 *
 * */

fun convertUnMatchingEntryToGpxEntry(entry : UnMatchingEntry) : GPXEntry {
    return createGpxEntry(entry.latitude, entry.longitude, entry.date.time)
}

fun convertXFDEntryToMatchingEntry(xfdEntry: XFDEntry): MatchingEntry {
    return MatchingEntry().apply {
        id = xfdEntry.tid.toInt()
        latitude = xfdEntry.lat
        longitude = xfdEntry.lon
        edgeId = xfdEntry.edgeId
        dateTime = Date(xfdEntry.time)
        geometry = xfdEntry.geometry.centroid
    }
}

fun createGpxEntry (lat : Double, lon: Double, time : Long) : GPXEntry {
    return GPXEntry(GHPoint(lat, lon), time)
}