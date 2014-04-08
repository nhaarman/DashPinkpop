package com.nhaarman.dashclock.pinkpop.displaystrategy.test;

import android.content.res.Configuration;
import android.test.AndroidTestCase;

import com.nhaarman.dashclock.pinkpop.displaystrategy.CountDownStrategy;

import org.joda.time.DateTime;

import java.util.Locale;

import static com.nhaarman.dashclock.pinkpop.displaystrategy.test.matchers.StartsWithMatcher.startsWith;
import static org.junit.Assert.assertThat;

@SuppressWarnings({"HardCodedStringLiteral", "MagicNumber"})
public class EnglishCountDownStrategyTest extends AndroidTestCase {

    private CountDownStrategy mCountDownStrategy;

    private static final DateTime DATETIME_FROM = DateTime.now();

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        Locale locale = new Locale("en_US");
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getContext().getResources().updateConfiguration(config, null);

        mCountDownStrategy = new CountDownStrategy(getContext(), null);
    }

    /* Single type */

    public void test1Day() {
        assertThat(mCountDownStrategy.generateTimeLeftString(DATETIME_FROM, DATETIME_FROM.plusDays(1)), startsWith("1 day left"));
    }

    public void test30Days() {
        assertThat(mCountDownStrategy.generateTimeLeftString(DATETIME_FROM, DATETIME_FROM.plusDays(30)), startsWith("30 days left"));
    }

    public void test1Hour() {
        assertThat(mCountDownStrategy.generateTimeLeftString(DATETIME_FROM, DATETIME_FROM.plusHours(1)), startsWith("1 hour left"));
    }

    public void test23Hours() {
        assertThat(mCountDownStrategy.generateTimeLeftString(DATETIME_FROM, DATETIME_FROM.plusHours(23)), startsWith("23 hours left"));
    }

    public void test1Minute() {
        assertThat(mCountDownStrategy.generateTimeLeftString(DATETIME_FROM, DATETIME_FROM.plusMinutes(1)), startsWith("1 minute left"));
    }

    public void test59Minutes() {
        assertThat(mCountDownStrategy.generateTimeLeftString(DATETIME_FROM, DATETIME_FROM.plusMinutes(59)), startsWith("59 minutes left"));
    }

    /* Double types */

    public void test1Day1Hour() {
        assertThat(mCountDownStrategy.generateTimeLeftString(DATETIME_FROM, DATETIME_FROM.plusDays(1).plusHours(1)), startsWith("1 day, 1 hour left"));
    }

    public void test1Day23Hours() {
        assertThat(mCountDownStrategy.generateTimeLeftString(DATETIME_FROM, DATETIME_FROM.plusDays(1).plusHours(23)), startsWith("1 day, 23 hours left"));
    }

    public void test1Day1Minute() {
        assertThat(mCountDownStrategy.generateTimeLeftString(DATETIME_FROM, DATETIME_FROM.plusDays(1).plusMinutes(1)), startsWith("1 day, 1 minute left"));
    }

    public void test1Day59Minutes() {
        assertThat(mCountDownStrategy.generateTimeLeftString(DATETIME_FROM, DATETIME_FROM.plusDays(1).plusMinutes(59)), startsWith("1 day, 59 minutes left"));
    }

    public void test1Hour1Minute() {
        assertThat(mCountDownStrategy.generateTimeLeftString(DATETIME_FROM, DATETIME_FROM.plusHours(1).plusMinutes(1)), startsWith("1 hour, 1 minute left"));
    }

    public void test1Hour59Minutes() {
        assertThat(mCountDownStrategy.generateTimeLeftString(DATETIME_FROM, DATETIME_FROM.plusHours(1).plusMinutes(59)), startsWith("1 hour, 59 minutes left"));
    }

    public void test23Hours1Minute() {
        assertThat(mCountDownStrategy.generateTimeLeftString(DATETIME_FROM, DATETIME_FROM.plusHours(23).plusMinutes(1)), startsWith("23 hours, 1 minute left"));
    }

    public void test23Hours59Minutes() {
        assertThat(mCountDownStrategy.generateTimeLeftString(DATETIME_FROM, DATETIME_FROM.plusHours(23).plusMinutes(59)), startsWith("23 hours, 59 minutes left"));
    }

    /* Triple types */
    public void test1Day1Hour1Minute() {
        assertThat(mCountDownStrategy.generateTimeLeftString(DATETIME_FROM, DATETIME_FROM.plusDays(1).plusHours(1).plusMinutes(1)), startsWith("1 day, 1 hour, 1 minute left"));
    }

    public void test1Day1Hour59Minutes() {
        assertThat(mCountDownStrategy.generateTimeLeftString(DATETIME_FROM, DATETIME_FROM.plusDays(1).plusHours(1).plusMinutes(59)), startsWith("1 day, 1 hour, 59 minutes left"));
    }

    public void test1Day23Hours1Minute() {
        assertThat(mCountDownStrategy.generateTimeLeftString(DATETIME_FROM, DATETIME_FROM.plusDays(1).plusHours(23).plusMinutes(1)), startsWith("1 day, 23 hours, 1 minute left"));
    }

    public void test1Day23Hours59Minutes() {
        assertThat(mCountDownStrategy.generateTimeLeftString(DATETIME_FROM, DATETIME_FROM.plusDays(1).plusHours(23).plusMinutes(59)), startsWith("1 day, 23 hours, 59 minutes left"));
    }
}