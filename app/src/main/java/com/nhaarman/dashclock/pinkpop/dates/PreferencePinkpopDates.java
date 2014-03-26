package com.nhaarman.dashclock.pinkpop.dates;

import android.content.Context;

import com.nhaarman.dashclock.pinkpop.preferences.Preferences;

import org.joda.time.DateTime;

public class PreferencePinkpopDates implements PinkpopDates {

    private final Context mContext;

    public PreferencePinkpopDates(final Context context) {
        mContext = context;
    }

    @Override
    public DateTime getStartDateTime() {
        return Preferences.getStartDate(mContext);
    }

    @Override
    public DateTime getEndDateTime() {
        return Preferences.getEndDate(mContext);
    }
}
