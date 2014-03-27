package com.nhaarman.dashclock.pinkpop.preferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.nhaarman.dashclock.pinkpop.R;
import com.nhaarman.dashclock.pinkpop.dates.DefaultPinkpopDates;
import com.nhaarman.dashclock.pinkpop.dates.PinkpopDates;
import com.nhaarman.dashclock.pinkpop.dates.PreferencePinkpopDates;
import com.nhaarman.dashclock.pinkpop.displaystrategy.CountDownStrategy;
import com.nhaarman.dashclock.pinkpop.displaystrategy.DisplayStrategy;

import org.joda.time.DateTime;

public class Preferences {

    public static DisplayStrategy getExtensionStrategy(final Context context) {
        return new CountDownStrategy(context, getPinkpopDates(context));
    }

    private static PinkpopDates getPinkpopDates(final Context context) {
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
        long startDateMillis = PreferenceManager.getDefaultSharedPreferences(context).getLong(context.getString(R.string.pref_start_datetime), -1);
        return startDateMillis == -1 ? null : new DateTime(startDateMillis);
    }

    public static void setStartDate(final Context context, final DateTime startDateTime) {
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
        if (startDateTime == null) {
            editor.remove(context.getString(R.string.pref_start_datetime)).commit();
        } else {
            editor.putLong(context.getString(R.string.pref_start_datetime), startDateTime.getMillis()).commit();
        }
    }
}
