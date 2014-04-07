package com.nhaarman.dashclock.pinkpop.schedule;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.nhaarman.dashclock.pinkpop.R;
import com.nhaarman.dashclock.pinkpop.artistinfo.ArtistInfo;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Comparator;

import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;
import se.emilsjolander.stickylistheaders.StickyListHeadersListView;


public class ScheduleFragment extends Fragment {

    private static final String ARGUMENT_ARTISTS = "com.nhaarman.dashclock.pinkpop.schedule.argument_artists";

    public static final String EVENT_PREFERENCES_CLICKED = "com.nhaarman.dashclock.pinkpop.schedule.event_preferences_clicked";

    private StickyListHeadersListView mListView;

    public static ScheduleFragment newInstance(final String[] artists) {
        ScheduleFragment scheduleFragment = new ScheduleFragment();

        Bundle arguments = new Bundle();
        arguments.putStringArray(ARGUMENT_ARTISTS, artists);
        scheduleFragment.setArguments(arguments);

        return scheduleFragment;
    }

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        mListView = new StickyListHeadersListView(getActivity());
        mListView.setDivider(null);
        return mListView;
    }

    @Override
    public void onActivityCreated(final Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Bundle arguments = getArguments();
        String[] artists = arguments.getStringArray(ARGUMENT_ARTISTS);
        StickyListHeadersAdapter artistsListAdapter = new ArtistsListAdapter(getActivity(), artists);
        mListView.setAdapter(artistsListAdapter);
    }

    @Override
    public void onCreateOptionsMenu(final Menu menu, final MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_fragment_schedule, menu);
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        boolean result;
        switch (item.getItemId()) {
            case R.id.menu_fragment_schedule_preferences:
                Intent intent = new Intent(EVENT_PREFERENCES_CLICKED);
                LocalBroadcastManager.getInstance(getActivity()).sendBroadcast(intent);
                result = true;
                break;
            default:
                result = super.onOptionsItemSelected(item);
        }
        return result;
    }

    private static class ArtistsListAdapter extends BaseAdapter implements StickyListHeadersAdapter {

        private final Context mContext;
        private final String[] mArtists;
        private final ArtistInfo mArtistInfo;

        private ArtistsListAdapter(final Context context, final String[] artists) {
            mContext = context;
            mArtists = artists;
            mArtistInfo = new ArtistInfo(context.getResources());
            Arrays.sort(mArtists, new ArtistsComparator(context, mArtistInfo));
        }

        @Override
        public int getCount() {
            return mArtists.length;
        }

        @Override
        public String getItem(final int position) {
            return mArtists[position];
        }

        @Override
        public long getHeaderId(final int position) {
            return mArtistInfo.getPerformingStage(getItem(position)).hashCode();
        }

        @Override
        public long getItemId(final int position) {
            return position;
        }

        @Override
        public View getHeaderView(final int position, final View convertView, final ViewGroup parent) {
            TextView tv = (TextView) convertView;
            if (tv == null) {
                tv = (TextView) LayoutInflater.from(mContext).inflate(R.layout.fragment_schedule_headerview, parent, false);
            }
            tv.setText(mArtistInfo.getPerformingStage(getItem(position)));
            return tv;
        }

        @Override
        public View getView(final int position, final View convertView, final ViewGroup parent) {
            TextView tv = (TextView) convertView;
            if (tv == null) {
                tv = (TextView) LayoutInflater.from(mContext).inflate(R.layout.fragment_schedule_artistview, parent, false);
            }
            tv.setText(getItem(position));
            return tv;
        }

        @Override
        public boolean isEnabled(final int position) {
            return false;
        }
    }

    private static class ArtistsComparator implements Comparator<String>, Serializable {

        private final ArtistInfo mArtistInfo;
        private final StageComparator mStageComparator;

        private ArtistsComparator(final Context context, final ArtistInfo artistInfo) {
            mArtistInfo = artistInfo;
            mStageComparator = new StageComparator(context);
        }

        @Override
        public int compare(final String leftArtist, final String rightArtist) {
            String leftStage = mArtistInfo.getPerformingStage(leftArtist);
            String rightStage = mArtistInfo.getPerformingStage(rightArtist);

            return 100 * mStageComparator.compare(leftStage, rightStage) + leftArtist.compareTo(rightArtist);
        }
    }

    private static class StageComparator implements Comparator<String> {

        private final String mMainStageString;
        private final String m3FMStageString;

        private StageComparator(final Context context) {
            mMainStageString = context.getString(R.string.stage_main);
            m3FMStageString = context.getString(R.string.stage_3fm);
        }

        @Override
        public int compare(final String leftStage, final String rightStage) {
            if (leftStage.equals(rightStage)) {
                return 0;
            } else if (leftStage.equals(mMainStageString)) {
                return -1;
            } else if (rightStage.equals(mMainStageString)) {
                return 1;
            } else {
                return leftStage.equals(m3FMStageString) ? -1 : 1;
            }
        }
    }
}
