package com.nhaarman.dashclock.pinkpop.displaystrategy.test;

import android.content.res.Configuration;
import android.test.AndroidTestCase;

import com.nhaarman.dashclock.pinkpop.displaystrategy.CountDownStrategy;

import org.joda.time.DateTime;

import java.util.Locale;

import static com.nhaarman.dashclock.pinkpop.displaystrategy.test.matchers.StartsWithMatcher.startsWith;
import static org.junit.Assert.assertThat;

@SuppressWarnings({"HardCodedStringLiteral", "MagicNumber"})
public class DutchCountDownStrategyTest extends AndroidTestCase {

    private static final DateTime DATETIME_FROM = DateTime.now();

    private CountDownStrategy mCountDownStrategy;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        Locale locale = new Locale("nl_NL");
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getContext().getResources().updateConfiguration(config, null);

        mCountDownStrategy = new CountDownStrategy(getContext(), null);
    }

    /* Single type */

    public void test1Day() {
        assertThat(mCountDownStrategy.generateTimeLeftString(DATETIME_FROM, DATETIME_FROM.plusDays(1)), startsWith("1 dag te gaan"));
    }

    public void test30Days() {
        assertThat(mCountDownStrategy.generateTimeLeftString(DATETIME_FROM, DATETIME_FROM.plusDays(30)), startsWith("30 dagen te gaan"));
    }

    public void test1Hour() {
        assertThat(mCountDownStrategy.generateTimeLeftString(DATETIME_FROM, DATETIME_FROM.plusHours(1)), startsWith("1 uur te gaan"));
    }

    public void test23Hours() {
        assertThat(mCountDownStrategy.generateTimeLeftString(DATETIME_FROM, DATETIME_FROM.plusHours(23)), startsWith("23 uur te gaan"));
    }

    public void test1Minute() {
        assertThat(mCountDownStrategy.generateTimeLeftString(DATETIME_FROM, DATETIME_FROM.plusMinutes(1)), startsWith("1 minuut te gaan"));
    }

    public void test59Minutes() {
        assertThat(mCountDownStrategy.generateTimeLeftString(DATETIME_FROM, DATETIME_FROM.plusMinutes(59)), startsWith("59 minuten te gaan"));
    }

    /* Double types */

    public void test1Day1Hour() {
        assertThat(mCountDownStrategy.generateTimeLeftString(DATETIME_FROM, DATETIME_FROM.plusDays(1).plusHours(1)), startsWith("1 dag, 1 uur te gaan"));
    }

    public void test1Day23Hours() {
        assertThat(mCountDownStrategy.generateTimeLeftString(DATETIME_FROM, DATETIME_FROM.plusDays(1).plusHours(23)), startsWith("1 dag, 23 uur te gaan"));
    }

    public void test1Day1Minute() {
        assertThat(mCountDownStrategy.generateTimeLeftString(DATETIME_FROM, DATETIME_FROM.plusDays(1).plusMinutes(1)), startsWith("1 dag, 1 minuut te gaan"));
    }

    public void test1Day59Minutes() {
        assertThat(mCountDownStrategy.generateTimeLeftString(DATETIME_FROM, DATETIME_FROM.plusDays(1).plusMinutes(59)), startsWith("1 dag, 59 minuten te gaan"));
    }

    public void test1Hour1Minute() {
        assertThat(mCountDownStrategy.generateTimeLeftString(DATETIME_FROM, DATETIME_FROM.plusHours(1).plusMinutes(1)), startsWith("1 uur, 1 minuut te gaan"));
    }

    public void test1Hour59Minutes() {
        assertThat(mCountDownStrategy.generateTimeLeftString(DATETIME_FROM, DATETIME_FROM.plusHours(1).plusMinutes(59)), startsWith("1 uur, 59 minuten te gaan"));
    }

    public void test23Hours1Minute() {
        assertThat(mCountDownStrategy.generateTimeLeftString(DATETIME_FROM, DATETIME_FROM.plusHours(23).plusMinutes(1)), startsWith("23 uur, 1 minuut te gaan"));
    }

    public void test23Hours59Minutes() {
        assertThat(mCountDownStrategy.generateTimeLeftString(DATETIME_FROM, DATETIME_FROM.plusHours(23).plusMinutes(59)), startsWith("23 uur, 59 minuten te gaan"));
    }

    /* Triple types */
    public void test1Day1Hour1Minute() {
        assertThat(mCountDownStrategy.generateTimeLeftString(DATETIME_FROM, DATETIME_FROM.plusDays(1).plusHours(1).plusMinutes(1)), startsWith("1 dag, 1 uur, 1 minuut te gaan"));
    }

    public void test1Day1Hour59Minutes() {
        assertThat(mCountDownStrategy.generateTimeLeftString(DATETIME_FROM, DATETIME_FROM.plusDays(1).plusHours(1).plusMinutes(59)), startsWith("1 dag, 1 uur, 59 minuten te gaan"));
    }

    public void test1Day23Hours1Minute() {
        assertThat(mCountDownStrategy.generateTimeLeftString(DATETIME_FROM, DATETIME_FROM.plusDays(1).plusHours(23).plusMinutes(1)), startsWith("1 dag, 23 uur, 1 minuut te gaan"));
    }

    public void test1Day23Hours59Minutes() {
        assertThat(mCountDownStrategy.generateTimeLeftString(DATETIME_FROM, DATETIME_FROM.plusDays(1).plusHours(23).plusMinutes(59)), startsWith("1 dag, 23 uur, 59 minuten te gaan"));
    }
}
