package com.nhaarman.dashclock.pinkpop.schedule;

import android.content.Intent;

public class ScheduleIntent extends Intent {

    public static final String ACTION_PAGE_SELECTED = "com.nhaarman.schedule.ScheduleActivity.ACTION_PAGE_SELECTED";

    public static final String EXTRA_SELECTED_PAGE = "com.nhaarman.schedule.ScheduleActivity.EXTRA_SELECTED_PAGE";

    public ScheduleIntent(final String action) {
        super(action);
    }

}
