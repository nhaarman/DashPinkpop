package com.nhaarman.dashclock.pinkpop.dates;

import android.content.Context;

import com.nhaarman.dashclock.pinkpop.preferences.Preferences;

import org.joda.time.DateTime;

public class PreferencePinkpopDates extends DefaultPinkpopDates {

    private final Context mContext;

    public PreferencePinkpopDates(final Context context) {
        mContext = context;
    }

    @Override
    public DateTime getStartDateTime() {
        return Preferences.getStartDate(mContext);
    }
}
