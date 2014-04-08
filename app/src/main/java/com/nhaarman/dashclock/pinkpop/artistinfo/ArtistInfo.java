package com.nhaarman.dashclock.pinkpop.artistinfo;

import android.content.res.Resources;

import com.nhaarman.dashclock.pinkpop.R;

public class ArtistInfo {

    private final Resources mResources;

    public ArtistInfo(final Resources resources) {
        mResources = resources;
    }

    @SuppressWarnings("QuestionableName")
    private static boolean isContainedIn(final String string, final String[] array) {
        boolean found = false;
        for (int i = 0; i < array.length && !found; i++) {
            if (array[i].equals(string)) {
                found = true;
            }
        }
        return found;
    }

    public String getRandomArtist() {
        String[] artists = mResources.getStringArray(R.array.artists);
        int index = (int) (Math.random() * artists.length);
        return artists[index];
    }

    public String getDayOfPerformance(final String artist) {
        String day;
        if (isSaturdayArtist(artist)) {
            day = mResources.getString(R.string.saturday);
        } else if (isSundayArtist(artist)) {
            day = mResources.getString(R.string.sunday);
        } else if (isMondayArtist(artist)) {
            day = mResources.getString(R.string.monday);
        } else {
            throw new IllegalArgumentException("Artist " + artist + " not performing on any day!");
        }
        return day;
    }

    private boolean isSaturdayArtist(final String artist) {
        String[] artists = mResources.getStringArray(R.array.artists_saturday);
        return isContainedIn(artist, artists);
    }

    private boolean isSundayArtist(final String artist) {
        String[] artists = mResources.getStringArray(R.array.artists_sunday);
        return isContainedIn(artist, artists);
    }

    private boolean isMondayArtist(final String artist) {
        String[] artists = mResources.getStringArray(R.array.artists_monday);
        return isContainedIn(artist, artists);
    }

    public String getPerformingStage(final String artist) {
        String stage;
        if (isMainStageArtist(artist)) {
            stage = mResources.getString(R.string.stage_main);
        } else if (is3fmStageArtist(artist)) {
            stage = mResources.getString(R.string.stage_3fm);
        } else if (isBrandStageArtist(artist)) {
            stage = mResources.getString(R.string.stage_brand);
        } else {
            throw new IllegalArgumentException("Artist " + artist + " not performing on any stage!");
        }
        return stage;
    }

    private boolean isMainStageArtist(final String artist) {
        String[] artists = mResources.getStringArray(R.array.artists_mainstage);
        return isContainedIn(artist, artists);
    }

    private boolean is3fmStageArtist(final String artist) {
        String[] artists = mResources.getStringArray(R.array.artists_3fmstage);
        return isContainedIn(artist, artists);
    }

    private boolean isBrandStageArtist(final String artist) {
        String[] artists = mResources.getStringArray(R.array.artists_brandstage);
        return isContainedIn(artist, artists);
    }

}
