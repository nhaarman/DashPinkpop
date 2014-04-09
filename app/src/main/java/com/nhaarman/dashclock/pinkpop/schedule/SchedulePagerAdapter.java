package com.nhaarman.dashclock.pinkpop.schedule;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

class SchedulePagerAdapter extends FragmentPagerAdapter {

    private final Schedule mSchedule;

    SchedulePagerAdapter(final FragmentManager fm, final Schedule schedule) {
        super(fm);
        mSchedule = schedule;
    }

    @Override
    public int getCount() {
        return mSchedule.getNumberOfDays();
    }

    @Override
    public Fragment getItem(final int position) {
        String[] artists;
        switch (position) {
            case 0:
                artists = mSchedule.getSaturdayArtists();
                break;
            case 1:
                artists = mSchedule.getSundayArtists();
                break;
            case 2:
                artists = mSchedule.getMondayArtists();
                break;
            default:
                throw new IllegalArgumentException("Unexpected day: " + position);
        }
        return ScheduleFragment.newInstance(artists);
    }
}
