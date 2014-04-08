package com.nhaarman.dashclock.pinkpop.displaystrategy.test.matchers;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;

@SuppressWarnings("HardCodedStringLiteral")
public class RegexMatcher extends BaseMatcher<String> {

    private final String mRegex;

    private RegexMatcher(final String regex) {
        mRegex = regex;
    }

    public static Matcher<String> matches(final String regex) {
        return new RegexMatcher(regex);
    }

    @Override
    public void describeTo(final Description description) {
        description.appendText("matches regex \"" + mRegex + '"');
    }

    @Override
    public boolean matches(final Object item) {
        return item instanceof String && ((String) item).matches(mRegex);
    }
}
