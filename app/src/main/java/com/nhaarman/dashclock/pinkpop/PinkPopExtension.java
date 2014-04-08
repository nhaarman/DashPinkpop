package com.nhaarman.dashclock.pinkpop;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.PowerManager;

import com.google.android.apps.dashclock.api.DashClockExtension;
import com.google.android.apps.dashclock.api.ExtensionData;
import com.nhaarman.dashclock.pinkpop.displaystrategy.DisplayStrategy;
import com.nhaarman.dashclock.pinkpop.preferences.Preferences;
import com.nhaarman.dashclock.pinkpop.schedule.ScheduleActivity;

public class PinkPopExtension extends DashClockExtension {

    private static final String CONTENT = "content://";

    private MyBroadcastReceiver mReceiver;

    private static Intent createClickIntent(final Context context) {
        return new Intent(context, ScheduleActivity.class);
    }

    @Override
    protected void onInitialize(final boolean isReconnect) {
        setUpdateWhenScreenOn(true);
        addWatchContentUris(new String[]{CONTENT + getString(R.string.authority)});
        mReceiver = new MyBroadcastReceiver();
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
        data.clickIntent(createClickIntent(this));

        publishUpdate(data);

        IntentFilter intentFilter = new IntentFilter(Intent.ACTION_TIME_TICK);
        registerReceiver(mReceiver, intentFilter);
    }

    private static class MyBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(final Context context, final Intent intent) {
            context.unregisterReceiver(this);

            PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
            if (pm.isScreenOn()) {
                context.getContentResolver().notifyChange(Uri.parse(CONTENT + context.getString(R.string.authority)), null);
            }
        }
    }
}
