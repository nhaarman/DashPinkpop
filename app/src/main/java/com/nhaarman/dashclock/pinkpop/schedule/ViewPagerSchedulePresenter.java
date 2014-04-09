package com.nhaarman.dashclock.pinkpop.schedule;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

public class ViewPagerSchedulePresenter extends ViewPager implements SchedulePresenter {

    public ViewPagerSchedulePresenter(final Context context) {
        super(context);
    }

    public ViewPagerSchedulePresenter(final Context context, final AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void setSchedule(final Schedule schedule, final FragmentManager fragmentManager) {
        SchedulePagerAdapter schedulePagerAdapter = new SchedulePagerAdapter(fragmentManager, schedule);
        setAdapter(schedulePagerAdapter);
        setOnPageChangeListener(new OnSchedulePageChangeListener());
    }

    @Override
    public boolean hasTabs() {
        return true;
    }

    @Override
    public void onTabSelected(final int tab) {
        setCurrentItem(tab);
    }

    private class OnSchedulePageChangeListener implements OnPageChangeListener {

        @Override
        public void onPageScrolled(final int position, final float positionOffset, final int positionOffsetPixels) {
        }

        @Override
        public void onPageSelected(final int position) {
            ScheduleIntent intent = new ScheduleIntent(ScheduleIntent.ACTION_PAGE_SELECTED);
            intent.putExtra(ScheduleIntent.EXTRA_SELECTED_PAGE, position);
            LocalBroadcastManager.getInstance(getContext()).sendBroadcast(intent);
        }

        @Override
        public void onPageScrollStateChanged(final int state) {
        }
    }
}
