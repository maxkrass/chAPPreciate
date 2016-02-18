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
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.maxkrass.appreciate.R;
import com.maxkrass.appreciate.adapter.AlliancePagerAdapter;
import com.maxkrass.appreciate.adapter.MainPagerAdapter;
import com.maxkrass.appreciate.objects.MatchRecord;
import com.maxkrass.appreciate.views.CheckBoxWidget;

import java.util.ArrayList;

public class MatchScout extends AppCompatActivity implements View.OnClickListener {

	ViewPager viewPager;
	AlliancePagerAdapter alliance;
	TabLayout tabLayout;
	SharedPreferences settings;
	String matchNumber = "";

	int defenseNumber;
	ArrayList<String> shots;
	TextView highTextView;
	TextView lowTextView;
	TextView missTextView;



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

		defenseNumber = -1;
		shots = new ArrayList<>();
		highTextView = (TextView)findViewById(R.id.high_text_view);
		lowTextView = (TextView)findViewById(R.id.low_text_view);
		missTextView = (TextView)findViewById(R.id.miss_text_view);

		highTextView.setText(0);
		lowTextView.setText(0);
		missTextView.setText(0);


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

                MatchRecord team1 = AlliancePagerAdapter.team1.fetchMatch();
                MatchRecord team2 = AlliancePagerAdapter.team2.fetchMatch();
                MatchRecord team3 = AlliancePagerAdapter.team3.fetchMatch();
                team1.save();
                team2.save();
                team3.save();
                MainPagerAdapter.matchScouts.teamAdapter.add(team1);
                MainPagerAdapter.matchScouts.teamAdapter.add(team2);
                MainPagerAdapter.matchScouts.teamAdapter.add(team3);
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
			((CheckBoxWidget) v).setChecked(!((CheckBoxWidget) v).isChecked());
		}
	}
	public void addShotClicked(View v)
	{
		String tag = v.getTag().toString();
		if(defenseNumber == -1)// no defense selected
		{
			Toast.makeText(this, "Must select a defense", Toast.LENGTH_SHORT).show();
			return;
		}

		if(tag.equals("HIGH"))
		{
			shots.add(defenseNumber + "H");
			highTextView.setText(Integer.parseInt(highTextView.getText().toString()) + 1);

		}
		else if(tag.equals("LOW"))
		{
			shots.add(defenseNumber + "L");
			lowTextView.setText(Integer.parseInt(lowTextView.getText().toString()) + 1);
		}
		else if(tag.equals("MISS"))
		{
			shots.add(defenseNumber + "M");
			missTextView.setText(Integer.parseInt(missTextView.getText().toString()) + 1);
		}
		else
		{
			System.out.println("Could not find tag " + tag);
		}


	}

	public void defenseButtonClicked(View v)
	{
		defenseNumber = Integer.parseInt(((Button)v).getTag().toString());
	}



	public void subtractShotClicked(View v)
	{
		String tag = v.getTag().toString();
		if(tag.equals("HIGH"))
		{
			boolean removedItem = false;
			for(int i = shots.size() - 1; i >= 0; i--)
			{
				if(shots.get(i).charAt(1) == 'H')
				{
					shots.remove(i);
					removedItem = true;
					break;
				}
			}
			if(removedItem)
			{
				highTextView.setText(Integer.parseInt(highTextView.getText().toString()) - 1);
			}
		}
		else if(tag.equals("LOW"))
		{
			boolean removedItem = false;
			for(int i = shots.size() - 1; i >= 0; i--)
			{
				if(shots.get(i).charAt(1) == 'L')
				{
					shots.remove(i);
					removedItem = true;
					break;
				}
			}
			if(removedItem)
			{
				highTextView.setText(Integer.parseInt(highTextView.getText().toString()) - 1);
			}
		}
		else if (tag.equals("MISS"))
		{
			boolean removedItem = false;
			for(int i = shots.size() - 1; i >= 0; i--)
			{
				if(shots.get(i).charAt(1) == 'M')
				{
					shots.remove(i);
					removedItem = true;
					break;
				}
			}
			if(removedItem)
			{
				highTextView.setText(Integer.parseInt(highTextView.getText().toString()) - 1);
			}
		}
		else
		{
			System.out.println("Could not fing tag " + tag);
		}
	}



}
