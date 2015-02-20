package com.maxkrass.appreciate.fragments;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import com.maxkrass.appreciate.R;

public class SettingsFragment extends PreferenceFragment
{

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        addPreferencesFromResource(R.xml.settings);
    }
}
