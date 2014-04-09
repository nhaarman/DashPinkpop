package com.nhaarman.dashclock.pinkpop.schedule;

import android.support.v4.app.FragmentManager;

public interface SchedulePresenter  {

    void setSchedule(Schedule schedule, FragmentManager fragmentManager);

    boolean hasTabs();

    void onTabSelected(int tab);

}
