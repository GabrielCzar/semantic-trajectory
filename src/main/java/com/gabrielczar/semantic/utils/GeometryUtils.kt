/*
 * Copyright (c) 2018 Gabriel César
 */

package com.gabrielczar.semantic.utils

import com.vividsolutions.jts.geom.*

/**
 * Util functions to Geometry Type
 *
 * @author GabrielCzar
 * @since 1.0.0
 *
 * */

private val gf = GeometryFactory(PrecisionModel(), 4326)

/**
 * @param latitude is required to create the point
 * @param longitude is required to create the point
 * @return Point or Null in case latitude or longitude invalid
 *
 * */
fun createPoint(latitude : Double, longitude : Double) : Point {
    return gf.createPoint(Coordinate(longitude, latitude))
}

/**
 * found by assumption     final point
 *                     \ /
 *                     / \
 *             initial    found by assumption
 * @param initialLatitude is required to create the polygon
 * @param initialLongitude is required to create the polygon
 * @param finalLatitude is required to create the polygon
 * @param finalLongitude is required to create the polygon
 * @return Polygon or Null in case latitudes or longitudes are invalid
 *
 * */
fun createPolygon(initialLatitude : Double,
                  initialLongitude : Double,
                  finalLatitude : Double,
                  finalLongitude : Double) : Polygon {

    // begin and end are the same
    return gf.createPolygon(arrayOf(
            Coordinate(initialLongitude, initialLatitude),
            Coordinate(finalLongitude, initialLatitude),
            Coordinate(finalLongitude, finalLatitude),
            Coordinate(initialLongitude, finalLatitude),
            Coordinate(initialLongitude, initialLatitude)
    ))
}

