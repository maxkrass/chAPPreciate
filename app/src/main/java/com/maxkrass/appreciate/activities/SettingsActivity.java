package com.maxkrass.appreciate.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.maxkrass.appreciate.R;

import java.io.IOException;

public class SettingsActivity extends PreferenceActivity implements SharedPreferences.OnSharedPreferenceChangeListener, View.OnClickListener {

	protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
		setContentView(R.layout.settings_toolbar);
		Toolbar mToolBar = (Toolbar) findViewById(R.id.toolbar);
		mToolBar.setNavigationOnClickListener(this);
		getListView().setPadding(0, 0, 0, 0);

    }

	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return true;
    }


    @Override
    protected void onResume() {
        super.onResume();
        // Set up a listener whenever a key changes
        PreferenceManager.getDefaultSharedPreferences(this)
                .registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Unregister the listener whenever a key changes
        PreferenceManager.getDefaultSharedPreferences(this)
                .unregisterOnSharedPreferenceChangeListener(this);
    }

    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (key.equals("use_bluetooth") || key.equals("is_receiver")) {
	        /*try {
		        //MainActivity.singleton.setupBluetooth();
	        } catch (IOException e) {
		        e.printStackTrace();
	        }*/
        }
    }

	@Override
	public void onClick(View v) {
		finish();
	}
}
