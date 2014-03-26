package com.nhaarman.dashclock.pinkpop;

import android.content.Context;

import com.nhaarman.dashclock.pinkpop.dates.DefaultPinkpopDates;
import com.nhaarman.dashclock.pinkpop.dates.PinkpopDates;
import com.nhaarman.dashclock.pinkpop.displaystrategy.CountDownStrategy;
import com.nhaarman.dashclock.pinkpop.displaystrategy.DisplayStrategy;

import org.joda.time.DateTime;

public class Preferences {

    public static DisplayStrategy getExtensionStrategy(final Context context) {
        return new CountDownStrategy(context, getPinkpopDates(context));
    }

    public static PinkpopDates getPinkpopDates(final Context context) {
        return new DefaultPinkpopDates();
    }

    public static String getStartDate(final Context context) {
        return null;
    }

    public static void setStartDate(final DateTime startDateTime) {

    }

    public static String getEndDate(final Context context) {
        return null;
    }

    public static void setEndDate(final DateTime endDateTime) {

    }

}
