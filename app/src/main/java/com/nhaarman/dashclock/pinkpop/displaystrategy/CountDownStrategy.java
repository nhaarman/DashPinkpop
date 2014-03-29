package com.nhaarman.dashclock.pinkpop.displaystrategy;

import android.content.Context;
import android.content.res.Resources;

import com.nhaarman.dashclock.pinkpop.R;
import com.nhaarman.dashclock.pinkpop.dates.PinkpopDates;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Hours;
import org.joda.time.Minutes;
import org.joda.time.Period;

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
            result = generateTimeLeftString(DateTime.now().withSecondOfMinute(0).withMillisOfSecond(0), startDateTime);
        } else if (mPinkpopDates.getEndDateTime().isAfterNow()) {
            result = mContext.getString(R.string.active);
        } else {
            result = mContext.getString(R.string.finished);
        }

        return result;
    }

    public String generateTimeLeftString(final DateTime fromDateTime, final DateTime toDateTime) {
        Period period = new Period(fromDateTime, toDateTime);

        int days = Days.daysBetween(fromDateTime, toDateTime).getDays();
        int hours = period.getHours();
        int minutes = period.getMinutes();

        Resources resources = mContext.getResources();

        String daysString = days > 0 ? resources.getQuantityString(R.plurals.days, days, days) : "";
        String hoursString = hours > 0 ? resources.getQuantityString(R.plurals.hours, hours, hours) : "";
        String minutesString = minutes > 0 ? resources.getQuantityString(R.plurals.minutes, minutes, minutes) : "";

        if (days > 0 && (hours > 0 || minutes > 0)) {
            daysString += ", ";
        }
        if (hours > 0 && minutes > 0) {
            hoursString += ", ";
        }

        return resources.getString(R.string.time_left, daysString, hoursString, minutesString).trim();
    }
}
