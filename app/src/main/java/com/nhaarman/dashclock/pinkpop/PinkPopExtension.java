package com.nhaarman.dashclock.pinkpop;

import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.preference.PreferenceManager;

import com.google.android.apps.dashclock.api.DashClockExtension;
import com.google.android.apps.dashclock.api.ExtensionData;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Hours;
import org.joda.time.Minutes;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class PinkPopExtension extends DashClockExtension {


    private static final String WEB_URL = "http://www.pinkpop.nl";

    @Override
    protected void onInitialize(final boolean isReconnect) {
        setUpdateWhenScreenOn(true);
    }

    @Override
    protected void onUpdateData(final int reason) {
        ExtensionStrategy strategy = Preferences.getExtensionStrategy();
        ExtensionData data = new ExtensionData();

        data.visible(true);
        data.icon(R.drawable.icon);
        data.status(strategy.getStatus(getResources()));
        data.expandedTitle(strategy.getExpandedTitle(getResources()));
        data.expandedBody(strategy.getExpandedBody(getResources()));
        data.clickIntent(createClickIntent());

        publishUpdate(data);
    }


    private static Intent createClickIntent() {
        return new Intent(Intent.ACTION_VIEW, Uri.parse(WEB_URL));
    }
}
