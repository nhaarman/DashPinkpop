package com.nhaarman.dashclock.pinkpop.schedule;

import android.content.res.Resources;
import android.content.res.TypedArray;

import com.nhaarman.dashclock.pinkpop.R;

public class Schedule {

    private final String[][] mArtistLists;

    private Schedule(final Resources resources) {
        TypedArray ta = resources.obtainTypedArray(R.array.artists_schedule);
        int n = ta.length();
        mArtistLists = new String[n][];
        for (int i = 0; i < n; ++i) {
            int id = ta.getResourceId(i, 0);
            mArtistLists[i] = resources.getStringArray(id);
        }
        ta.recycle();
    }

    public static Schedule createSchedule(final Resources resources) {
        return new Schedule(resources);
    }

    public String[] getSaturdayArtists() {
        return mArtistLists[0];
    }

    public String[] getSundayArtists() {
        return mArtistLists[1];
    }

    public String[] getMondayArtists() {
        return mArtistLists[2];
    }

    public int getNumberOfDays() {
        return mArtistLists.length;
    }
}
