package com.nhaarman.dashclock.pinkpop.displaystrategy.test;

import android.content.res.Configuration;
import android.test.AndroidTestCase;

import com.nhaarman.dashclock.pinkpop.dates.PinkpopDates;
import com.nhaarman.dashclock.pinkpop.displaystrategy.CountDownStrategy;

import org.joda.time.DateTime;

import java.util.Locale;

import static com.nhaarman.dashclock.pinkpop.displaystrategy.test.matchers.StartsWithMatcher.startsWith;
import static org.junit.Assert.assertThat;

@SuppressWarnings({"HardCodedStringLiteral", "MagicNumber"})
public class DutchCountDownStrategyTest extends AndroidTestCase {


    private MockPinkpopDates mMockPinkpopDates;
    private CountDownStrategy mCountDownStrategy;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        Locale locale = new Locale("nl_NL");
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getContext().getResources().updateConfiguration(config, null);

        mMockPinkpopDates = new MockPinkpopDates();
        mCountDownStrategy = new CountDownStrategy(getContext(), mMockPinkpopDates);
    }

    /* Single type */

    public void test1Day() {
        mMockPinkpopDates.setStartDateTime(DateTime.now().plusDays(1));
        assertThat(mCountDownStrategy.getExpandedBody(), startsWith("1 dag te gaan"));
    }

    public void test365Days() {
        mMockPinkpopDates.setStartDateTime(DateTime.now().plusDays(365));
        assertThat(mCountDownStrategy.getExpandedBody(), startsWith("365 dagen te gaan"));
    }

    public void test366Days() {
        mMockPinkpopDates.setStartDateTime(DateTime.now().plusDays(366));
        assertThat(mCountDownStrategy.getExpandedBody(), startsWith("366 dagen te gaan"));
    }

    public void test1Hour() {
        mMockPinkpopDates.setStartDateTime(DateTime.now().plusHours(1));
        assertThat(mCountDownStrategy.getExpandedBody(), startsWith("1 uur te gaan"));
    }

    public void test23Hours() {
        mMockPinkpopDates.setStartDateTime(DateTime.now().plusHours(23));
        assertThat(mCountDownStrategy.getExpandedBody(), startsWith("23 uur te gaan"));
    }

    public void test1Minute() {
        mMockPinkpopDates.setStartDateTime(DateTime.now().plusMinutes(1));
        assertThat(mCountDownStrategy.getExpandedBody(), startsWith("1 minuut te gaan"));
    }

    public void test59Minutes() {
        mMockPinkpopDates.setStartDateTime(DateTime.now().plusMinutes(59));
        assertThat(mCountDownStrategy.getExpandedBody(), startsWith("59 minuten te gaan"));
    }

    /* Double types */

    public void test1Day1Hour() {
        mMockPinkpopDates.setStartDateTime(DateTime.now().plusDays(1).plusHours(1));
        assertThat(mCountDownStrategy.getExpandedBody(), startsWith("1 dag, 1 uur te gaan"));
    }

    public void test1Day23Hours() {
        mMockPinkpopDates.setStartDateTime(DateTime.now().plusDays(1).plusHours(23));
        assertThat(mCountDownStrategy.getExpandedBody(), startsWith("1 dag, 23 uur te gaan"));
    }

    public void test265Days1Hour() {
        mMockPinkpopDates.setStartDateTime(DateTime.now().plusDays(265).plusHours(1));
        assertThat(mCountDownStrategy.getExpandedBody(), startsWith("265 dagen, 1 uur te gaan"));
    }

    public void test265Days23Hours() {
        mMockPinkpopDates.setStartDateTime(DateTime.now().plusDays(265).plusHours(23));
        assertThat(mCountDownStrategy.getExpandedBody(), startsWith("265 dagen, 23 uur te gaan"));
    }

    public void test266Days1Hour() {
        mMockPinkpopDates.setStartDateTime(DateTime.now().plusDays(266).plusHours(1));
        assertThat(mCountDownStrategy.getExpandedBody(), startsWith("266 dagen, 1 uur te gaan"));
    }

    public void test266Days23Hours() {
        mMockPinkpopDates.setStartDateTime(DateTime.now().plusDays(266).plusHours(23));
        assertThat(mCountDownStrategy.getExpandedBody(), startsWith("266 dagen, 23 uur te gaan"));
    }

    public void test1Day1Minute() {
        mMockPinkpopDates.setStartDateTime(DateTime.now().plusDays(1).plusMinutes(1));
        assertThat(mCountDownStrategy.getExpandedBody(), startsWith("1 dag, 1 minuut te gaan"));
    }

    public void test1Day59Minutes() {
        mMockPinkpopDates.setStartDateTime(DateTime.now().plusDays(1).plusMinutes(59));
        assertThat(mCountDownStrategy.getExpandedBody(), startsWith("1 dag, 59 minuten te gaan"));
    }

    public void test265Days1Minute() {
        mMockPinkpopDates.setStartDateTime(DateTime.now().plusDays(265).plusMinutes(1));
        assertThat(mCountDownStrategy.getExpandedBody(), startsWith("265 dagen, 1 minuut te gaan"));
    }

    public void test265Days59Minutes() {
        mMockPinkpopDates.setStartDateTime(DateTime.now().plusDays(265).plusMinutes(59));
        assertThat(mCountDownStrategy.getExpandedBody(), startsWith("265 dagen, 59 minuten te gaan"));
    }

    public void test266Days1Minute() {
        mMockPinkpopDates.setStartDateTime(DateTime.now().plusDays(266).plusMinutes(1));
        assertThat(mCountDownStrategy.getExpandedBody(), startsWith("266 dagen, 1 minuut te gaan"));
    }

    public void test266Days59Minutes() {
        mMockPinkpopDates.setStartDateTime(DateTime.now().plusDays(266).plusMinutes(59));
        assertThat(mCountDownStrategy.getExpandedBody(), startsWith("266 dagen, 59 minuten te gaan"));
    }

    public void test1Hour1Minute() {
        mMockPinkpopDates.setStartDateTime(DateTime.now().plusHours(1).plusMinutes(1));
        assertThat(mCountDownStrategy.getExpandedBody(), startsWith("1 uur, 1 minuut te gaan"));
    }

    public void test1Hour59Minutes() {
        mMockPinkpopDates.setStartDateTime(DateTime.now().plusHours(1).plusMinutes(59));
        assertThat(mCountDownStrategy.getExpandedBody(), startsWith("1 uur, 59 minuten te gaan"));
    }

    public void test23Hours1Minute() {
        mMockPinkpopDates.setStartDateTime(DateTime.now().plusHours(23).plusMinutes(1));
        assertThat(mCountDownStrategy.getExpandedBody(), startsWith("23 uur, 1 minuut te gaan"));
    }

    public void test23Hours59Minutes() {
        mMockPinkpopDates.setStartDateTime(DateTime.now().plusHours(23).plusMinutes(59));
        assertThat(mCountDownStrategy.getExpandedBody(), startsWith("23 uur, 59 minuten te gaan"));
    }

    /* Triple types */
    public void test1Day1Hour1Minute() {
        mMockPinkpopDates.setStartDateTime(DateTime.now().plusDays(1).plusHours(1).plusMinutes(1));
        assertThat(mCountDownStrategy.getExpandedBody(), startsWith("1 dag, 1 uur, 1 minuut te gaan"));
    }

    public void test1Day1Hour59Minutes() {
        mMockPinkpopDates.setStartDateTime(DateTime.now().plusDays(1).plusHours(1).plusMinutes(59));
        assertThat(mCountDownStrategy.getExpandedBody(), startsWith("1 dag, 1 uur, 59 minuten te gaan"));
    }

    public void test1Day23Hours1Minute() {
        mMockPinkpopDates.setStartDateTime(DateTime.now().plusDays(1).plusHours(23).plusMinutes(1));
        assertThat(mCountDownStrategy.getExpandedBody(), startsWith("1 dag, 23 uur, 1 minuut te gaan"));
    }

    public void test1Day23Hours59Minutes() {
        mMockPinkpopDates.setStartDateTime(DateTime.now().plusDays(1).plusHours(23).plusMinutes(59));
        assertThat(mCountDownStrategy.getExpandedBody(), startsWith("1 dag, 23 uur, 59 minuten te gaan"));
    }

    public void test265Days1Hour1Minute() {
        mMockPinkpopDates.setStartDateTime(DateTime.now().plusDays(265).plusHours(1).plusMinutes(1));
        assertThat(mCountDownStrategy.getExpandedBody(), startsWith("265 dagen, 1 uur, 1 minuut te gaan"));
    }

    public void test265Days1Hour59Minutes() {
        mMockPinkpopDates.setStartDateTime(DateTime.now().plusDays(265).plusHours(1).plusMinutes(59));
        assertThat(mCountDownStrategy.getExpandedBody(), startsWith("265 dagen, 1 uur, 59 minuten te gaan"));
    }

    public void test265Days23Hours1Minute() {
        mMockPinkpopDates.setStartDateTime(DateTime.now().plusDays(265).plusHours(23).plusMinutes(1));
        assertThat(mCountDownStrategy.getExpandedBody(), startsWith("265 dagen, 23 uur, 1 minuut te gaan"));
    }

    public void test265Days23Hours59Minutes() {
        mMockPinkpopDates.setStartDateTime(DateTime.now().plusDays(265).plusHours(23).plusMinutes(59));
        assertThat(mCountDownStrategy.getExpandedBody(), startsWith("265 dagen, 23 uur, 59 minuten te gaan"));
    }

    public void test266Days1Hour1Minute() {
        mMockPinkpopDates.setStartDateTime(DateTime.now().plusDays(266).plusHours(1).plusMinutes(1));
        assertThat(mCountDownStrategy.getExpandedBody(), startsWith("266 dagen, 1 uur, 1 minuut te gaan"));
    }

    public void test266Days1Hour59Minutes() {
        mMockPinkpopDates.setStartDateTime(DateTime.now().plusDays(266).plusHours(1).plusMinutes(59));
        assertThat(mCountDownStrategy.getExpandedBody(), startsWith("266 dagen, 1 uur, 59 minuten te gaan"));
    }

    public void test266Days23Hours1Minute() {
        mMockPinkpopDates.setStartDateTime(DateTime.now().plusDays(266).plusHours(23).plusMinutes(1));
        assertThat(mCountDownStrategy.getExpandedBody(), startsWith("266 dagen, 23 uur, 1 minuut te gaan"));
    }

    public void test266Days23Hours59Minutes() {
        mMockPinkpopDates.setStartDateTime(DateTime.now().plusDays(266).plusHours(23).plusMinutes(59));
        assertThat(mCountDownStrategy.getExpandedBody(), startsWith("266 dagen, 23 uur, 59 minuten te gaan"));
    }
}
