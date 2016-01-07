package com.maxkrass.appreciate.activities;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.maxkrass.appreciate.R;
import com.maxkrass.appreciate.adapter.AlliancePagerAdapter;
import com.maxkrass.appreciate.adapter.MainPagerAdapter;
import com.maxkrass.appreciate.fragments.TeamFragment;
import com.maxkrass.appreciate.objects.MatchRecord;
import com.maxkrass.appreciate.views.CheckBoxWidget;

/**
 * Sarah made this for APPreciate on 12/17/15.
 */

public class MatchScoutOneTeam extends BaseActivity implements View.OnClickListener {

	TeamFragment fragment;

	EditText teamNumberField;
	EditText matchNumberField;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.match_layout_one_team);
		Toolbar toolbar = (Toolbar) findViewById(R.id.one_team_toolbar);
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayShowTitleEnabled(false);
		teamNumberField = (EditText) findViewById(R.id.team_number_field);
		matchNumberField = (EditText) findViewById(R.id.match_number_field);
		fragment = (TeamFragment) getSupportFragmentManager().findFragmentById(R.id.content_fragment);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.match_save_menu, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case android.R.id.home:
				//TODO create a new Dialog Fragment (not simple Alert Dialog) and prompt user if action was intended
				finish();
				break;
			case R.id.save_action:
				if (!teamNumberField.getText().toString().equals("") && !matchNumberField.getText().toString().equals("")) {
					MatchRecord matchRecord = fragment.fetchMatch();
					matchRecord.setTeamNumber(Integer.parseInt(teamNumberField.getText().toString()));
					matchRecord.setMatchNumber(Integer.parseInt(matchNumberField.getText().toString()));
					matchRecord.save();
                    MainPagerAdapter.matchScouts.teamAdapter.add(matchRecord);
                    finish();
				} else if (teamNumberField.getText().toString().equals(""))
					((TextInputLayout) teamNumberField.getParent()).setError("A team number is required");
				else if (matchNumberField.getText().toString().equals(""))
					((TextInputLayout) matchNumberField.getParent()).setError("A match number is required");
				break;
			case R.id.clear_action:
				fragment.clearFields();
				break;
		}
		return super.onOptionsItemSelected(item);
	}

	public void onClick(View view) {
		if (view instanceof CheckBoxWidget) {
			((CheckBoxWidget) view).toggle();
		}
	}

}
