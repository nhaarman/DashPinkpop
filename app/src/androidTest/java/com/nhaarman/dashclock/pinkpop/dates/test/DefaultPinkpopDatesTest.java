package com.nhaarman.dashclock.pinkpop.dates.test;

import com.nhaarman.dashclock.pinkpop.dates.DefaultPinkpopDates;

import junit.framework.TestCase;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class DefaultPinkpopDatesTest extends TestCase {

    private DefaultPinkpopDates mDefaultPinkpopDates;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mDefaultPinkpopDates = new DefaultPinkpopDates();
    }

    public void testDefaultStartDate() {
        assertThat(mDefaultPinkpopDates.getStartDateTime().getMillis(), is(DefaultPinkpopDates.DEFAULT_START_DATETIME.getMillis()));
    }

    public void testDefaultEndDate() {
        assertThat(mDefaultPinkpopDates.getEndDateTime().getMillis(), is(DefaultPinkpopDates.DEFAULT_END_DATETIME.getMillis()));
    }

}
