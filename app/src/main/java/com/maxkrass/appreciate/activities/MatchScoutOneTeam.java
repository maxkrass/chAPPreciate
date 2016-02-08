package com.maxkrass.appreciate.activities;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

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


	private CheckBoxWidget pickPort;
	private CheckBoxWidget pickChevel;
	private CheckBoxWidget pickMoat;
	private CheckBoxWidget pickRamp;
	private CheckBoxWidget pickSally;
	private CheckBoxWidget pickRock;
	private CheckBoxWidget pickRough;
	private CheckBoxWidget startBall;
	private CheckBoxWidget autoSpy;
	private CheckBoxWidget reachDefense;

	Spinner defenseReach;

	Button autoLowGoalMinus;
	TextView autoLowGoal;
	Button getAutoLowGoalPlus;

	TextView lowGoal;

	Spinner defenseTwoSpinner;
	Spinner defenseThreeSpinner;
	Spinner defenseFourSpinner;
	Spinner defenseFiveSpinner;

	Button defenseOneMinus;
	TextView defenseOne;
	Button defenseOnePlus;

	Button defenseTwoMinus;
	TextView defenseTwo;
	Button defenseTwoPlus;

	Button defenseThreeMinus;
	TextView defenseThree;
	Button defenseThreePlus;

	Button defenseFourMinus;
	TextView defenseFour;
	Button defenseFourPlus;

	Button defenseFiveMinus;
	TextView defenseFive;
	Button defenseFivePlus;


	Button lowShotMinus;
	TextView lowShotScreen;
	Button lowShotPlus;


	Button highShotMinus;
	TextView highShotScreen;
	Button highShotPlus;

	private CheckBoxWidget secretPassage;
	private CheckBoxWidget netural;
	private CheckBoxWidget courtyard;
	private CheckBoxWidget Steal;

	private CheckBoxWidget block;
	private CheckBoxWidget push;

	private CheckBoxWidget canPickUp;
	private CheckBoxWidget fast;
	private CheckBoxWidget penalty;
	private CheckBoxWidget breach;

	private CheckBoxWidget capture;
	private CheckBoxWidget scale;

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
