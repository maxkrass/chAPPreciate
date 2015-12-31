package com.maxkrass.appreciate.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.maxkrass.appreciate.R;
import com.maxkrass.appreciate.adapter.AlliancePagerAdapter;
import com.maxkrass.appreciate.views.CheckBoxWidget;

public class MatchScout extends AppCompatActivity implements View.OnClickListener {

	ViewPager viewPager;
	AlliancePagerAdapter alliance;
	TabLayout tabLayout;
	SharedPreferences settings;
	String matchNumber = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Intent intent = getIntent();
		setContentView(R.layout.match_save_dialog);
		Toolbar toolbar = (Toolbar) findViewById(R.id.match_toolbar);
		matchNumber = intent.getStringExtra("matchNumber");
		//toolbar.setTitle("Match " + matchNumber);
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
		settings = PreferenceManager.getDefaultSharedPreferences(MainActivity.singleton);
		alliance = new AlliancePagerAdapter(getSupportFragmentManager(), Integer.parseInt(matchNumber), Integer.parseInt(intent.getStringExtra("team1")), Integer.parseInt(intent.getStringExtra("team2")), Integer.parseInt(intent.getStringExtra("team3")));
		viewPager = (ViewPager) findViewById(R.id.viewPager);
		viewPager.setOffscreenPageLimit(2);
		viewPager.setAdapter(alliance);
		tabLayout = (TabLayout) findViewById(R.id.tabStrip);
		tabLayout.setupWithViewPager(viewPager);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.match_save_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case android.R.id.home:
				finish();
				break;
			case R.id.save_action:
				AlliancePagerAdapter.team1.fetchMatch().save();
				AlliancePagerAdapter.team2.fetchMatch().save();
				AlliancePagerAdapter.team3.fetchMatch().save();
				finish();
				break;
			case R.id.clear_action:
				AlliancePagerAdapter.team1.clearFields();
				AlliancePagerAdapter.team2.clearFields();
				AlliancePagerAdapter.team3.clearFields();
				break;
			case R.id.settings:
				startActivity(new Intent(this, SettingsActivity.class));
				break;
			default:
				return false;
		}
		return true;
	}

	@Override
	public void onClick(View v) {
		if (v instanceof CheckBoxWidget) {
			((CheckBoxWidget) v).setCheckBox(!((CheckBoxWidget) v).isChecked());
		}
	}
}
