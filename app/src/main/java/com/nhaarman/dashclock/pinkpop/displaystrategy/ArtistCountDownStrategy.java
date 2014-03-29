package com.nhaarman.dashclock.pinkpop.displaystrategy;

import android.content.Context;

import com.nhaarman.dashclock.pinkpop.R;
import com.nhaarman.dashclock.pinkpop.artistinfo.ArtistInfo;
import com.nhaarman.dashclock.pinkpop.dates.PinkpopDates;

public class ArtistCountDownStrategy extends CountDownStrategy {

    private final Context mContext;
    private final ArtistInfo mArtistInfo;

    public ArtistCountDownStrategy(final Context context, final PinkpopDates pinkpopDates) {
        super(context, pinkpopDates);
        mContext = context;
        mArtistInfo = new ArtistInfo(context.getResources());
    }

    @Override
    public String getExpandedTitle() {
        String artist = mArtistInfo.getRandomArtist();
        String day = mArtistInfo.getDayOfPerformance(artist);
        String stage = mArtistInfo.getPerformingStage(artist);

        return mContext.getResources().getString(R.string.day_artist_stage, day, artist, stage);
    }
}
