package com.nhaarman.dashclock.pinkpop.artistinfo.test;

import android.test.AndroidTestCase;

import com.nhaarman.dashclock.pinkpop.R;
import com.nhaarman.dashclock.pinkpop.artistinfo.ArtistInfo;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

public class ArtistInfoTest extends AndroidTestCase {

    private ArtistInfo mArtistInfo;

    @Override
    public void setUp() throws Exception {
        super.setUp();

        mArtistInfo = new ArtistInfo(getContext().getResources());
    }

    public void testGetRandomArtist() {
        String[] allArtists = getContext().getResources().getStringArray(R.array.artists);
        for (int i = 0; i < 100; i++) {
            String randomArtist = mArtistInfo.getRandomArtist();
            assertThat(randomArtist, isIn(allArtists));
        }
    }

    public void testGetDayOfPerformanceSaturday() {
        String[] artists = getContext().getResources().getStringArray(R.array.artists_saturday);
        String expectedResult = getContext().getResources().getString(R.string.saturday);

        for (String artist : artists) {
            assertThat(mArtistInfo.getDayOfPerformance(artist), is(expectedResult));
        }
    }

    public void testGetDayOfPerformanceSunday() {
        String[] artists = getContext().getResources().getStringArray(R.array.artists_sunday);
        String expectedResult = getContext().getResources().getString(R.string.sunday);

        for (String artist : artists) {
            assertThat(mArtistInfo.getDayOfPerformance(artist), is(expectedResult));
        }
    }

    public void testGetDayOfPerformanceMonday() {
        String[] artists = getContext().getResources().getStringArray(R.array.artists_monday);
        String expectedResult = getContext().getResources().getString(R.string.monday);

        for (String artist : artists) {
            assertThat(mArtistInfo.getDayOfPerformance(artist), is(expectedResult));
        }
    }

    public void testGetPerformingStageMain() {
        String[] artists = getContext().getResources().getStringArray(R.array.artists_mainstage);
        String expectedResult = getContext().getResources().getString(R.string.stage_main);

        for (String artist : artists) {
            assertThat(mArtistInfo.getPerformingStage(artist), is(expectedResult));
        }
    }

    public void testGetPerformingStage3fm() {
        String[] artists = getContext().getResources().getStringArray(R.array.artists_3fmstage);
        String expectedResult = getContext().getResources().getString(R.string.stage_3fm);

        for (String artist : artists) {
            assertThat(mArtistInfo.getPerformingStage(artist), is(expectedResult));
        }
    }

    public void testGetPerformingStageBrand() {
        String[] artists = getContext().getResources().getStringArray(R.array.artists_brandstage);
        String expectedResult = getContext().getResources().getString(R.string.stage_brand);

        for (String artist : artists) {
            assertThat(mArtistInfo.getPerformingStage(artist), is(expectedResult));
        }
    }
}
