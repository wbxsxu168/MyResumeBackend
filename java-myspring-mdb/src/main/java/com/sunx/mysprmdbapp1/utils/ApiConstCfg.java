package com.sunx.mysprmdbapp1.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * API const var definitions
 */
public final class ApiConstCfg {

    private ApiConstCfg() {}

    public static final String IMGRECORDS_SEQUENCE_NAME = "imagerecords_sequence";
    public static final String USERACCOUNT_SEQUENCE_NAME = "useraccount_sequence";
    public static final String IMGRECORDS_SIZE_DEFAULT = "5";
    public static final String IMGRECORDS_PAGE_DEFAULT = "0";
    private static final String zone = "GMT";
    // long timestamp = java.time.Instant.now().getEpochSecond();
    
    public static String convertToString(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
        dateFormat.setTimeZone(TimeZone.getTimeZone(zone));
        return dateFormat.format(date);
    }

    public static Date convertToDate(String strDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
        dateFormat.setTimeZone(TimeZone.getTimeZone(zone));
        Date parsedDate = null;
        try {
            parsedDate = dateFormat.parse(strDate);
        } catch (ParseException e) {
           ;// log.error(e.getMessage());
        }
        return parsedDate;
    }
}