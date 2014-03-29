package com.nhaarman.dashclock.pinkpop.displaystrategy;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.nhaarman.dashclock.pinkpop.R;
import com.nhaarman.dashclock.pinkpop.artistinfo.ArtistInfo;
import com.nhaarman.dashclock.pinkpop.dates.PinkpopDates;

public class ArtistCountDownStrategy extends CountDownStrategy {

    private static final String PREF_LASTARTISTCHANGE = "last_artist_change";
    private static final String PREF_LASTARTIST = "last_artist";
    private static final int ONE_HOUR = 60 * 60 * 1000;

    private final Context mContext;
    private final ArtistInfo mArtistInfo;

    public ArtistCountDownStrategy(final Context context, final PinkpopDates pinkpopDates) {
        super(context, pinkpopDates);
        mContext = context;
        mArtistInfo = new ArtistInfo(context.getResources());
    }

    @Override
    public String getExpandedTitle() {
        String artist;
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);

        if (System.currentTimeMillis() - sharedPreferences.getLong(PREF_LASTARTISTCHANGE, 0) > ONE_HOUR) {
            artist = mArtistInfo.getRandomArtist();
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(PREF_LASTARTIST, artist);
            editor.putLong(PREF_LASTARTISTCHANGE, System.currentTimeMillis());
            editor.commit();
        } else {
            artist = sharedPreferences.getString(PREF_LASTARTIST, null);
        }

        String day = mArtistInfo.getDayOfPerformance(artist);
        String stage = mArtistInfo.getPerformingStage(artist);

        return mContext.getResources().getString(R.string.day_artist_stage, day, artist, stage);
    }
}
