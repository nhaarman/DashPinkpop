package com.nhaarman.dashclock.pinkpop.displaystrategy.test;

import com.nhaarman.dashclock.pinkpop.dates.PinkpopDates;

import org.joda.time.DateTime;

public class MockPinkpopDates implements PinkpopDates {

    private DateTime mStartDateTime;

    @Override
    public DateTime getStartDateTime() {
        return mStartDateTime;
    }

    public void setStartDateTime(final DateTime startDateTime) {
        mStartDateTime = startDateTime.withSecondOfMinute(0).withMillisOfSecond(0);
    }

    @Override
    public DateTime getEndDateTime() {
        return null;
    }
}