package com.gabrielczar.semantic.utils;

import com.gabrielczar.semantic.entities.MatchingEntry;
import main.java.matching.models.XFDEntry;

import java.security.SecureRandom;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

public class ApplicationUtil {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    public static Long convertStringToLong(String dateTime) {
        try {
            Date parsedDate = dateFormat.parse(dateTime);
            return new Timestamp(parsedDate.getTime()).getTime();
        } catch (ParseException e) { e.printStackTrace(); }
        return 0L;
    }

    public static String generateToken() {
        SecureRandom random = new SecureRandom();
        byte bytes[] = new byte[12];
        random.nextBytes(bytes);
        Base64.Encoder encoder = Base64.getUrlEncoder().withoutPadding();
        return encoder.encodeToString(bytes);
    }

    public static MatchingEntry convertXFDEntryToMatchingEntry(XFDEntry xfdEntry) {
        MatchingEntry matchingEntry = new MatchingEntry();

        matchingEntry.setId(Math.toIntExact(xfdEntry.getTid()));
        matchingEntry.setLatitude(xfdEntry.getLat());
        matchingEntry.setLongitude(xfdEntry.getLon());
        matchingEntry.setEdgeId(xfdEntry.getEdgeId());
        matchingEntry.setDateTime(new java.sql.Date(xfdEntry.getTime()));
        matchingEntry.setGeometry(xfdEntry.getGeometry().getCentroid());

        return matchingEntry;
    }
}
