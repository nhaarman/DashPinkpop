package com.nhaarman.dashclock.pinkpop.displaystrategy.test;

import android.test.AndroidTestCase;

import com.nhaarman.dashclock.pinkpop.dates.PinkpopDates;
import com.nhaarman.dashclock.pinkpop.displaystrategy.ArtistCountDownStrategy;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static com.nhaarman.dashclock.pinkpop.displaystrategy.test.matchers.RegexMatcher.matches;
import static org.junit.Assert.assertThat;

public class ArtistCountDownStrategyTest extends AndroidTestCase {

    private static final String PATTERN_EXPANDED_TITLE = ".*:.*\\s\\(.*\\)";

    @Mock
    private PinkpopDates mPinkpopDates;

    private ArtistCountDownStrategy mArtistCountDownStrategy;

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        MockitoAnnotations.initMocks(this);
        mArtistCountDownStrategy = new ArtistCountDownStrategy(getContext(), mPinkpopDates);
    }

    public void testExpandedTitle() {
        for (int i = 0; i < 100; i++) {
            String expandedTitle = mArtistCountDownStrategy.getExpandedTitle();
            assertThat(expandedTitle, matches(PATTERN_EXPANDED_TITLE));
        }
    }
}
