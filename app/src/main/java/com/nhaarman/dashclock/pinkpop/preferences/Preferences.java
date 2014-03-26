package com.nhaarman.dashclock.pinkpop.preferences;

import android.content.Context;
import android.preference.PreferenceManager;

import com.nhaarman.dashclock.pinkpop.dates.DefaultPinkpopDates;
import com.nhaarman.dashclock.pinkpop.dates.PinkpopDates;
import com.nhaarman.dashclock.pinkpop.dates.PreferencePinkpopDates;
import com.nhaarman.dashclock.pinkpop.displaystrategy.CountDownStrategy;
import com.nhaarman.dashclock.pinkpop.displaystrategy.DisplayStrategy;

import org.joda.time.DateTime;

public class Preferences {

    private static final String PREF_START_DATE = "PREF_START_DATE";
    private static final String PREF_END_DATE = "PREF_END_DATE";

    public static DisplayStrategy getExtensionStrategy(final Context context) {
        return new CountDownStrategy(context, getPinkpopDates(context));
    }

    public static PinkpopDates getPinkpopDates(final Context context) {
        PinkpopDates result;

        DateTime prefStartDate = getStartDate(context);
        if (prefStartDate == null) {
            result = new DefaultPinkpopDates();
        } else {
            result = new PreferencePinkpopDates(context);
        }

        return result;
    }

    public static DateTime getStartDate(final Context context) {
        long startDateMillis = PreferenceManager.getDefaultSharedPreferences(context).getLong(PREF_START_DATE, -1);
        return startDateMillis == -1 ? null : new DateTime(startDateMillis);
    }

    public static void setStartDate(final Context context, final DateTime startDateTime) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putLong(PREF_START_DATE, startDateTime.getMillis()).commit();
    }

    public static DateTime getEndDate(final Context context) {
        long endDateMillis = PreferenceManager.getDefaultSharedPreferences(context).getLong(PREF_END_DATE, -1);
        return endDateMillis == -1 ? null : new DateTime(endDateMillis);
    }

    public static void setEndDate(final Context context, final DateTime endDateTime) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putLong(PREF_END_DATE, endDateTime.getMillis()).commit();
    }

}
