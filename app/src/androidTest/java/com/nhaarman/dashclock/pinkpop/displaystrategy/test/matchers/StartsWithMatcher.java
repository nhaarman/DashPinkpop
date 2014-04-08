package com.nhaarman.dashclock.pinkpop.displaystrategy.test.matchers;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;

public class StartsWithMatcher extends BaseMatcher<String> {

    private final String mPrefix;

    private StartsWithMatcher(final String prefix) {
        mPrefix = prefix;
    }

    public static Matcher<String> startsWith(final String prefix) {
        return new StartsWithMatcher(prefix);
    }

    @SuppressWarnings("HardCodedStringLiteral")
    @Override
    public void describeTo(final Description description) {
        description.appendText("starts with \"" + mPrefix + '"');

    }

    @Override
    public boolean matches(final Object item) {
        return item instanceof String && ((String) item).startsWith(mPrefix);
    }
}