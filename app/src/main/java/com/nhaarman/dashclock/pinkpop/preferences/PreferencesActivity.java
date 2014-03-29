package com.nhaarman.dashclock.pinkpop.preferences;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import com.nhaarman.dashclock.pinkpop.R;
import com.nhaarman.dashclock.pinkpop.dates.DefaultPinkpopDates;

import org.joda.time.DateTime;

public class PreferencesActivity extends PreferenceActivity {

    private static final String CONTENT = "content://";

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        Intent intent = getIntent();
        if (intent.getStringArrayExtra(EXTRA_SHOW_FRAGMENT) == null) {
            getIntent().putExtra(EXTRA_SHOW_FRAGMENT, PreferencesFragment.class.getName());
        }
        super.onCreate(savedInstanceState);

        getActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected boolean isValidFragment(final String fragmentName) {
        return fragmentName.equals(PreferencesFragment.class.getName());
    }

    @Override
    protected void onPause() {
        super.onPause();
        getContentResolver().notifyChange(Uri.parse(CONTENT + getResources().getString(R.string.authority)), null);
    }

    public static class PreferencesFragment extends PreferenceFragment {

        @Override
        public void onCreate(final Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.fragmented_preferences_inner);

            Preference startDateTimePreference = findPreference(getString(R.string.pref_start_datetime));
            startDateTimePreference.setOnPreferenceClickListener(new OnStartDatePreferenceClickListener());
        }

        private class OnStartDatePreferenceClickListener implements Preference.OnPreferenceClickListener {

            @Override
            @SuppressLint("InflateParams")
            public boolean onPreferenceClick(final Preference preference) {
                View layout = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_datetimepicker, null, false);
                DatePicker datePicker = (DatePicker) layout.findViewById(R.id.dialog_datetimepicker_datepicker);
                TimePicker timePicker = (TimePicker) layout.findViewById(R.id.dialog_datetimepicker_timepicker);

                DateTime prefStartDate = Preferences.getStartDate(getActivity());
                DateTime startDate = prefStartDate == null ? DefaultPinkpopDates.DEFAULT_START_DATETIME : prefStartDate;

                datePicker.updateDate(startDate.getYear(), startDate.getMonthOfYear() - 1, startDate.getDayOfMonth());
                timePicker.setCurrentHour(startDate.getHourOfDay());
                timePicker.setCurrentMinute(startDate.getMinuteOfHour());

                new AlertDialog.Builder(getActivity())
                        .setView(layout)
                        .setPositiveButton(android.R.string.ok, new OnStartDatePreferencePositiveButtonClickListener(datePicker, timePicker))
                        .setNegativeButton(android.R.string.cancel, null)
                        .show();

                return true;
            }

            private class OnStartDatePreferencePositiveButtonClickListener implements DialogInterface.OnClickListener {

                private final DatePicker mDatePicker;
                private final TimePicker mTimePicker;

                public OnStartDatePreferencePositiveButtonClickListener(final DatePicker datePicker, final TimePicker timePicker) {
                    mDatePicker = datePicker;
                    mTimePicker = timePicker;
                }

                @Override
                public void onClick(final DialogInterface dialog, final int which) {
                    DateTime dateTime = new DateTime(0).withYear(mDatePicker.getYear()).withMonthOfYear(mDatePicker.getMonth() + 1).withDayOfMonth(mDatePicker.getDayOfMonth()).withHourOfDay
                            (mTimePicker.getCurrentHour()).withMinuteOfHour(mTimePicker.getCurrentMinute());
                    Preferences.setStartDate(getActivity(), dateTime);
                }
            }
        }
    }
}
