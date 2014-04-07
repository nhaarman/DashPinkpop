package com.nhaarman.dashclock.pinkpop.dates;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class DefaultPinkpopDates implements PinkpopDates {

    private static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm";
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormat.forPattern(DATETIME_FORMAT);

    private static final String DEFAULT_START_DATE = "2014-06-07 12:00";
    private static final String DEFAULT_END_DATE = "2014-06-09 22:30";

    public static final DateTime DEFAULT_START_DATETIME = DATE_TIME_FORMATTER.parseDateTime(DEFAULT_START_DATE);
    public static final DateTime DEFAULT_END_DATETIME = DATE_TIME_FORMATTER.parseDateTime(DEFAULT_END_DATE);

    @Override
    public DateTime getStartDateTime() {
        return DEFAULT_START_DATETIME;
    }

    @Override
    public DateTime getEndDateTime() {
        return DEFAULT_END_DATETIME;
    }

}
