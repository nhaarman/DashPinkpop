package com.nhaarman.dashclock.pinkpop;

import android.content.Intent;
import android.net.Uri;

import com.google.android.apps.dashclock.api.DashClockExtension;
import com.google.android.apps.dashclock.api.ExtensionData;
import com.nhaarman.dashclock.pinkpop.displaystrategy.DisplayStrategy;
import com.nhaarman.dashclock.pinkpop.preferences.Preferences;

public class PinkPopExtension extends DashClockExtension {

    private static final String WEB_URL = "http://www.pinkpop.nl";

    @Override
    protected void onInitialize(final boolean isReconnect) {
        setUpdateWhenScreenOn(true);
    }

    @Override
    protected void onUpdateData(final int reason) {
        DisplayStrategy displayStrategy = Preferences.getDisplayStrategy(this);

        ExtensionData data = new ExtensionData();
        data.visible(true);
        data.icon(R.drawable.icon);
        data.status(displayStrategy.getStatus());
        data.expandedTitle(displayStrategy.getExpandedTitle());
        data.expandedBody(displayStrategy.getExpandedBody());
        data.clickIntent(createClickIntent());

        publishUpdate(data);
    }


    private static Intent createClickIntent() {
        return new Intent(Intent.ACTION_VIEW, Uri.parse(WEB_URL));
    }
}
