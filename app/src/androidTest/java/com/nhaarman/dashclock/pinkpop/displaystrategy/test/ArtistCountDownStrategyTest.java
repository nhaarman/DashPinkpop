package com.nhaarman.dashclock.pinkpop.displaystrategy.test;

import android.test.AndroidTestCase;

import com.nhaarman.dashclock.pinkpop.displaystrategy.ArtistCountDownStrategy;

import static com.nhaarman.dashclock.pinkpop.displaystrategy.test.matchers.RegexMatcher.matches;
import static org.junit.Assert.assertThat;

public class ArtistCountDownStrategyTest extends AndroidTestCase {

    private static final String PATTERN_EXPANDED_TITLE = ".*:.*\\s\\(.*\\)";

    private ArtistCountDownStrategy mArtistCountDownStrategy;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        MockPinkpopDates mockPinkpopDates = new MockPinkpopDates();
        mArtistCountDownStrategy = new ArtistCountDownStrategy(getContext(), mockPinkpopDates);
    }

    public void testExpandedTitle() {
        for (int i = 0; i < 100; i++) {
            String expandedTitle = mArtistCountDownStrategy.getExpandedTitle();
            assertThat(expandedTitle, matches(PATTERN_EXPANDED_TITLE));
        }
    }
}
