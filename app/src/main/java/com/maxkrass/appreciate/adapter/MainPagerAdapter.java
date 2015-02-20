package com.maxkrass.appreciate.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.maxkrass.appreciate.fragments.MatchScoutFragment;
import com.maxkrass.appreciate.fragments.PitScoutFragment;

public class MainPagerAdapter extends FragmentStatePagerAdapter {

	public static PitScoutFragment pitScouts;
	public static MatchScoutFragment matchScouts;

	public MainPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public Fragment getItem(int position) {
		switch (position) {
			case 0:
				return pitScouts = new PitScoutFragment();
			case 1:
				return matchScouts = new MatchScoutFragment();
			default:
				return null;
		}
	}

	@Override
	public int getCount() {
		return 2;
	}

	@Override
	public CharSequence getPageTitle(int position) {
		switch (position) {
			case 0:
				return "Pit Scouts";
			case 1:
				return "Match Scouts";
			default:
				return null;
		}
	}
}
