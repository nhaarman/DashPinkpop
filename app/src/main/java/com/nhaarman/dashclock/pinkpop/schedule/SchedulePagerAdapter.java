package com.nhaarman.dashclock.pinkpop.schedule;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

public class SchedulePagerAdapter extends FragmentPagerAdapter {

    private final String[][] mArtistLists;

    public SchedulePagerAdapter(final FragmentManager fm, final String[][] artistLists) {
        super(fm);
        mArtistLists = artistLists;
    }

    @Override
    public int getCount() {
        return mArtistLists.length;
    }

    @Override
    public Fragment getItem(final int position) {
        return ScheduleFragment.newInstance(mArtistLists[position]);
    }
}
