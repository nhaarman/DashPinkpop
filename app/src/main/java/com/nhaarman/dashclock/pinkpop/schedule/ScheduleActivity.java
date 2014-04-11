package com.nhaarman.dashclock.pinkpop.schedule;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.LocalBroadcastManager;
import android.view.Menu;
import android.view.MenuItem;

import com.nhaarman.dashclock.pinkpop.R;
import com.nhaarman.dashclock.pinkpop.preferences.PreferencesActivity;

public class ScheduleActivity extends FragmentActivity {

    private SchedulePresenter mPresenter;

    private SchedulePageSelectedBroadcastReceiver mSchedulePageSelectedBroadcastReceiver;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        mPresenter = (SchedulePresenter) findViewById(R.id.activity_schedule_presenter);
        mPresenter.setSchedule(Schedule.createSchedule(getResources()), getSupportFragmentManager());

        if (mPresenter.hasTabs()) {
            setupTabs();
        }
    }

    private void setupTabs() {
        ActionBar.TabListener scheduleTabListener = new ScheduleTabListener();

        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        actionBar.addTab(actionBar.newTab().setText(R.string.saturday).setTabListener(scheduleTabListener));
        actionBar.addTab(actionBar.newTab().setText(R.string.sunday).setTabListener(scheduleTabListener));
        actionBar.addTab(actionBar.newTab().setText(R.string.monday).setTabListener(scheduleTabListener));
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter filter = new IntentFilter(ScheduleIntent.ACTION_PAGE_SELECTED);
        mSchedulePageSelectedBroadcastReceiver = new SchedulePageSelectedBroadcastReceiver();
        LocalBroadcastManager.getInstance(this).registerReceiver(mSchedulePageSelectedBroadcastReceiver, filter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mSchedulePageSelectedBroadcastReceiver);
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.menu_fragment_schedule, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        boolean result;
        switch (item.getItemId()) {
            case R.id.menu_fragment_schedule_preferences:
                startActivity(new Intent(this, PreferencesActivity.class));
                result = true;
                break;
            default:
                result = super.onOptionsItemSelected(item);
        }
        return result;
    }

    private class ScheduleTabListener implements ActionBar.TabListener {

        @Override
        public void onTabSelected(final ActionBar.Tab tab, final FragmentTransaction ft) {
            mPresenter.onTabSelected(getActionBar().getSelectedNavigationIndex());
        }

        @Override
        public void onTabUnselected(final ActionBar.Tab tab, final FragmentTransaction ft) {
        }

        @Override
        public void onTabReselected(final ActionBar.Tab tab, final FragmentTransaction ft) {
        }
    }

    private class SchedulePageSelectedBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(final Context context, final Intent intent) {
            int page = intent.getIntExtra(ScheduleIntent.EXTRA_SELECTED_PAGE, 0);
            getActionBar().setSelectedNavigationItem(page);
        }
    }
}
