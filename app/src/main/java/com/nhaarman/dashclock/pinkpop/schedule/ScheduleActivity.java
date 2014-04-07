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
import android.support.v4.view.ViewPager;

import com.nhaarman.dashclock.pinkpop.R;
import com.nhaarman.dashclock.pinkpop.preferences.PreferencesActivity;

public class ScheduleActivity extends FragmentActivity {

    private ViewPager mViewPager;
    private PreferencesClickedBroadcastReceiver mPreferencesClickedBroadcastReceiver;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        String[][] artistLists = new String[3][];
        artistLists[0] = getResources().getStringArray(R.array.artists_saturday);
        artistLists[1] = getResources().getStringArray(R.array.artists_sunday);
        artistLists[2] = getResources().getStringArray(R.array.artists_monday);

        mViewPager = (ViewPager) findViewById(R.id.activity_schedule_viewpager);
        SchedulePagerAdapter schedulePagerAdapter = new SchedulePagerAdapter(getSupportFragmentManager(), artistLists);
        mViewPager.setAdapter(schedulePagerAdapter);
        mViewPager.setOnPageChangeListener(new OnSchedulePageChangeListener());

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
        IntentFilter intentFilter = new IntentFilter(ScheduleFragment.EVENT_PREFERENCES_CLICKED);
        mPreferencesClickedBroadcastReceiver = new PreferencesClickedBroadcastReceiver();
        LocalBroadcastManager.getInstance(this).registerReceiver(mPreferencesClickedBroadcastReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mPreferencesClickedBroadcastReceiver);
    }

    private class PreferencesClickedBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(final Context context, final Intent intent) {
            startActivity(new Intent(context, PreferencesActivity.class));
        }
    }

    private class OnSchedulePageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(final int position, final float positionOffset, final int positionOffsetPixels) {
        }

        @Override
        public void onPageSelected(final int position) {
            getActionBar().setSelectedNavigationItem(position);
        }

        @Override
        public void onPageScrollStateChanged(final int state) {
        }
    }

    private class ScheduleTabListener implements ActionBar.TabListener {

        @Override
        public void onTabSelected(final ActionBar.Tab tab, final FragmentTransaction ft) {
            mViewPager.setCurrentItem(getActionBar().getSelectedNavigationIndex());
        }

        @Override
        public void onTabUnselected(final ActionBar.Tab tab, final FragmentTransaction ft) {
        }

        @Override
        public void onTabReselected(final ActionBar.Tab tab, final FragmentTransaction ft) {

        }
    }
}
