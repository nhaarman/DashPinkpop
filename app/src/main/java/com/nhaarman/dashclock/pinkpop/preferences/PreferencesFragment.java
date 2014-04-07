package com.nhaarman.dashclock.pinkpop.preferences;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.nhaarman.dashclock.pinkpop.R;
import com.nhaarman.dashclock.pinkpop.dates.DefaultPinkpopDates;

import org.joda.time.DateTime;

class PreferencesFragment extends PreferenceFragment {

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.fragmented_preferences_inner);

        Preference startDateTimePreference = findPreference(getString(R.string.pref_start_datetime));
        startDateTimePreference.setOnPreferenceClickListener(new OnStartDatePreferenceClickListener());
    }

    private class OnStartDatePreferenceClickListener implements Preference.OnPreferenceClickListener {

        @Override
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
    }

    private class OnStartDatePreferencePositiveButtonClickListener implements DialogInterface.OnClickListener {

        private final DatePicker mDatePicker;
        private final TimePicker mTimePicker;

        private OnStartDatePreferencePositiveButtonClickListener(final DatePicker datePicker, final TimePicker timePicker) {
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