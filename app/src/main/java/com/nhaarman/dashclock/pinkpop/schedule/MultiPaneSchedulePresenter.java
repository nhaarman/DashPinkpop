package com.nhaarman.dashclock.pinkpop.schedule;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.nhaarman.dashclock.pinkpop.R;

public class MultiPaneSchedulePresenter extends LinearLayout implements SchedulePresenter {

    public MultiPaneSchedulePresenter(final Context context) {
        super(context);
    }

    public MultiPaneSchedulePresenter(final Context context, final AttributeSet attrs) {
        super(context, attrs);
    }

    public MultiPaneSchedulePresenter(final Context context, final AttributeSet attrs, final int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void setSchedule(final Schedule schedule, final FragmentManager fragmentManager) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.activity_schedule_saturdayfragmentholder, ScheduleFragment.newInstance(schedule.getSaturdayArtists()));
        transaction.replace(R.id.activity_schedule_sundayfragmentholder, ScheduleFragment.newInstance(schedule.getSundayArtists()));
        transaction.replace(R.id.activity_schedule_mondayfragmentholder, ScheduleFragment.newInstance(schedule.getMondayArtists()));
        transaction.commit();
    }

    @Override
    public boolean hasTabs() {
        return false;
    }

    @Override
    public void onTabSelected(final int tab) {
    }
}
