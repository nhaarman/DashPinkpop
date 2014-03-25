package com.nhaarman.dashclock.pinkpop;

import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;

import com.google.android.apps.dashclock.api.DashClockExtension;
import com.google.android.apps.dashclock.api.ExtensionData;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Hours;
import org.joda.time.Minutes;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class PinkPopExtension extends DashClockExtension {

    private static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm";
    private static final DateTimeFormatter sDateTimeFormatter = DateTimeFormat.forPattern(DATETIME_FORMAT);

    private static final String START_DATE = "2014-06-07 12:00";
    private static final String END_DATE = "2014-06-09 22:30";

    private static final DateTime START_DATETIME = sDateTimeFormatter.parseDateTime(START_DATE);
    private static final DateTime END_DATETIME = sDateTimeFormatter.parseDateTime(END_DATE);

    private static final String WEB_URL = "http://www.pinkpop.nl";

    @Override
    protected void onInitialize(final boolean isReconnect) {
        setUpdateWhenScreenOn(true);
    }

    @Override
    protected void onUpdateData(final int reason) {
        ExtensionData data = new ExtensionData();

        data.visible(true);
        data.icon(R.drawable.icon);
        data.status(getBody(getResources()));
        data.expandedTitle(getString(R.string.extension_title));
        data.expandedBody(getBody(getResources()));
        data.clickIntent(createClickIntent());

        publishUpdate(data);
    }

    private static String getBody(final Resources resources) {
        String result;

        if (START_DATETIME.isAfterNow()) {
            result = generateTimeLeftString(resources);
        } else if (END_DATETIME.isAfterNow()) {
            result = resources.getString(R.string.active);
        } else {
            result = resources.getString(R.string.finished);
        }

        return result;
    }

    private static String generateTimeLeftString(final Resources resources) {
        DateTime now = DateTime.now();

        int days = Days.daysBetween(now, START_DATETIME).getDays();
        int hours = Hours.hoursBetween(now, START_DATETIME.minusDays(days)).getHours();
        int minutes = Minutes.minutesBetween(now, START_DATETIME.minusDays(days).minusHours(hours)).getMinutes() + 1;

        String daysString = days > 0 ? resources.getQuantityString(R.plurals.days, days, days) : "";
        String hoursString = hours > 0 ? resources.getQuantityString(R.plurals.hours, hours, hours) : "";
        String minutesString = resources.getQuantityString(R.plurals.minutes, minutes, minutes);

        return resources.getString(R.string.time_left, daysString, hoursString, minutesString).trim();
    }

    private static Intent createClickIntent() {
        return new Intent(Intent.ACTION_VIEW, Uri.parse(WEB_URL));
    }
}
