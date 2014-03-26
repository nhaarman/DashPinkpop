package com.nhaarman.dashclock.pinkpop.displaystrategy;

import android.content.Context;
import android.content.res.Resources;

import com.nhaarman.dashclock.pinkpop.R;
import com.nhaarman.dashclock.pinkpop.dates.PinkpopDates;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Hours;
import org.joda.time.Minutes;

public class CountDownStrategy implements DisplayStrategy {

    private final Context mContext;
    private final PinkpopDates mPinkpopDates;

    public CountDownStrategy(final Context context, final PinkpopDates pinkpopDates) {
        mContext = context;
        mPinkpopDates = pinkpopDates;
    }

    @Override
    public String getStatus() {
        return getBody();
    }

    @Override
    public String getExpandedTitle() {
        return mContext.getString(R.string.extension_title);
    }

    @Override
    public String getExpandedBody() {
        return getBody();
    }

    private String getBody() {
        String result;
        DateTime startDateTime = mPinkpopDates.getStartDateTime();

        if (startDateTime.isAfterNow()) {
            result = generateTimeLeftString(startDateTime);
        } else if (mPinkpopDates.getEndDateTime().isAfterNow()) {
            result = mContext.getString(R.string.active);
        } else {
            result = mContext.getString(R.string.finished);
        }

        return result;
    }

    private String generateTimeLeftString(final DateTime startDateTime) {
        DateTime now = DateTime.now();

        int days = Days.daysBetween(now, startDateTime).getDays();
        int hours = Hours.hoursBetween(now, startDateTime.minusDays(days)).getHours();
        int minutes = Minutes.minutesBetween(now, startDateTime.minusDays(days).minusHours(hours)).getMinutes() + 1;

        Resources resources = mContext.getResources();

        String daysString = days > 0 ? resources.getQuantityString(R.plurals.days, days, days) : "";
        String hoursString = hours > 0 ? resources.getQuantityString(R.plurals.hours, hours, hours) : "";
        String minutesString = resources.getQuantityString(R.plurals.minutes, minutes, minutes);

        return resources.getString(R.string.time_left, daysString, hoursString, minutesString).trim();
    }
}
