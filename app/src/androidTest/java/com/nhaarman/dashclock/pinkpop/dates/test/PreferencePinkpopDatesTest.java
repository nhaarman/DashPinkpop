package com.nhaarman.dashclock.pinkpop.dates.test;

import android.test.AndroidTestCase;

import com.nhaarman.dashclock.pinkpop.dates.DefaultPinkpopDates;
import com.nhaarman.dashclock.pinkpop.dates.PreferencePinkpopDates;
import com.nhaarman.dashclock.pinkpop.preferences.Preferences;

import org.joda.time.DateTime;
import org.joda.time.ReadableInstant;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

public class PreferencePinkpopDatesTest extends AndroidTestCase {

    private static final long MILLIS_START = 1L;
    private static final ReadableInstant DATETIME_START = new DateTime(MILLIS_START);

    private PreferencePinkpopDates mPreferencePinkpopDates;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        mPreferencePinkpopDates = new PreferencePinkpopDates(getContext());
    }

    public void testStartDateTime() {
        assertThat(mPreferencePinkpopDates.getStartDateTime(), is(nullValue()));
        Preferences.setStartDate(getContext(), DATETIME_START);
        assertThat(mPreferencePinkpopDates.getStartDateTime().getMillis(), is(MILLIS_START));
    }

    public void testEndDateTime() {
        assertThat(mPreferencePinkpopDates.getEndDateTime().getMillis(), is(DefaultPinkpopDates.DEFAULT_END_DATETIME.getMillis()));
    }


    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        Preferences.setStartDate(getContext(), null);
    }
}
