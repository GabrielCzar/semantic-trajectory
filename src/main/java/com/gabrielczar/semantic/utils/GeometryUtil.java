package com.gabrielczar.semantic.utils;

import com.vividsolutions.jts.geom.*;

public class GeometryUtil {
    private static final GeometryFactory gf = new GeometryFactory(new PrecisionModel(), 4326);

    /**
     * @param latitude is required to create the point
     * @param longitude is required to create the point
     * @return Point or Null in case latitude or longitude invalid
     *
     * */
    public static Point createPoint(Double latitude, Double longitude) {
        if (latitude == null || longitude == null)
            return null;

        return gf.createPoint(
                new Coordinate(
                        longitude,
                        latitude
                )
        );
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
    public static Polygon createPolygon(Double initialLatitude, Double initialLongitude,
                                        Double finalLatitude, Double finalLongitude) {
        if (initialLatitude == null || initialLongitude == null || finalLatitude == null || finalLongitude == null)
            return null;

        return gf.createPolygon(new Coordinate[] {
                new Coordinate(initialLongitude, initialLatitude),
                new Coordinate(finalLongitude, initialLatitude),
                new Coordinate(finalLongitude, finalLatitude),
                new Coordinate(initialLongitude, finalLatitude),
                new Coordinate(initialLongitude, initialLatitude)
        });

        // begin and end are the same
    }
}
