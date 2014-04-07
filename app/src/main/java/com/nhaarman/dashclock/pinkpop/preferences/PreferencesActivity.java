package com.nhaarman.dashclock.pinkpop.preferences;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.view.MenuItem;

import com.nhaarman.dashclock.pinkpop.R;

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
        boolean result;
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                result = true;
                break;
            default:
                result = super.onOptionsItemSelected(item);
        }
        return result;
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
}
