package com.nhaarman.dashclock.pinkpop.schedule;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import com.nhaarman.dashclock.pinkpop.R;

public class ScheduleActivity extends FragmentActivity {

    private ViewPager mViewPager;

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

        ScheduleTabListener scheduleTabListener = new ScheduleTabListener();

        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        actionBar.addTab(actionBar.newTab().setText(R.string.saturday).setTabListener(scheduleTabListener));
        actionBar.addTab(actionBar.newTab().setText(R.string.sunday).setTabListener(scheduleTabListener));
        actionBar.addTab(actionBar.newTab().setText(R.string.monday).setTabListener(scheduleTabListener));
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
