package com.maxkrass.appreciate.activities;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.maxkrass.appreciate.R;
import com.maxkrass.appreciate.adapter.AlliancePagerAdapter;
import com.maxkrass.appreciate.adapter.MainPagerAdapter;
import com.maxkrass.appreciate.fragments.TeamFragment;
import com.maxkrass.appreciate.objects.MatchRecord;
import com.maxkrass.appreciate.views.CheckBoxWidget;

import java.util.ArrayList;
/**
 * Sarah made this for APPreciate on 12/17/15.
 */

public class MatchScoutOneTeam extends BaseActivity implements View.OnClickListener {

	TeamFragment fragment;

	EditText teamNumberField;
	EditText matchNumberField;


	private CheckBox pickPort;
	private CheckBox pickChevel;
	private CheckBox pickMoat;
	private CheckBox pickRamp;
	private CheckBox pickSally;
	private CheckBox pickRock;
	private CheckBox pickRough;
	private CheckBox startBall;
	private CheckBox autoSpy;
    private CheckBox didReachDefense;
	Spinner defenseReach;

    Spinner defenseTwoSpinner;
    Spinner defenseThreeSpinner;
    Spinner defenseFourSpinner;
    Spinner defenseFiveSpinner;


    private CheckBox secretPassage;
    private CheckBox netural;
    private CheckBox courtyard;
    private CheckBox steal;

    private CheckBox block;
    private CheckBox push;

    private CheckBox canPickUp;
    private CheckBox fast;
    private CheckBox penalty;
    private CheckBox breach;

    private CheckBox capture;
    private CheckBox scale;

    private CheckBox fast1;
    private CheckBox fast2;
    private CheckBox fast3;
    private CheckBox fast4;
    private CheckBox fast5;
   
    Button autoLowGoalMinus;
    TextView autoLowGoal;
    Button getAutoLowGoalPlus;
    
    TextView lowGoal;
    
    int defenseNumber;
    ArrayList<String> shots;
    TextView highTextView;
    TextView lowTextView;
    TextView missTextView;
    TextView defenseSelectedTextView;
    final String defenseSelectedString = "Defense Selected:";
	
	






	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.match_layout_one_team);
		Toolbar toolbar = (Toolbar) findViewById(R.id.one_team_toolbar);
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayShowTitleEnabled(false);
		teamNumberField = (EditText) findViewById(R.id.team_number_field);
		matchNumberField = (EditText) findViewById(R.id.match_number_field);
        pickPort = (CheckBox) findViewById(R.id.portcheckbox);
        pickChevel = (CheckBox) findViewById(R.id.chevel_checkbox);
        pickMoat = (CheckBox) findViewById(R.id.moat_checkbox);
        pickRamp = (CheckBox) findViewById(R.id.ramp_checkbox);
        pickSally = (CheckBox) findViewById(R.id.sally_checkbox);
        pickRock = (CheckBox) findViewById(R.id.rock_checkbox);
        pickRough = (CheckBox) findViewById(R.id.rough_checkbox);
        startBall = (CheckBox) findViewById(R.id.start_ball_checkbox);
        autoSpy = (CheckBox) findViewById(R.id.autospy_checkbox);
        defenseReach = (Spinner) findViewById(R.id.defenseBreachSpinner);
        didReachDefense = (CheckBox) findViewById(R.id.reach_def_checkbox);

        secretPassage = (CheckBox) findViewById(R.id.secret_passage_checkbox);
        netural = (CheckBox) findViewById(R.id.neutral_checkbox);
        courtyard = (CheckBox) findViewById(R.id.courtyard_checkbox);
        steal = (CheckBox) findViewById(R.id.steal_checkbox);
        block = (CheckBox) findViewById(R.id.block_checkbox);
        push = (CheckBox) findViewById(R.id.push_checkbox);
        canPickUp = (CheckBox) findViewById(R.id.can_pick_up_checkbox);
        fast = (CheckBox) findViewById(R.id.fast_checkbox);
        penalty = (CheckBox) findViewById(R.id.penalty_checkbox);
        breach = (CheckBox) findViewById(R.id.breach_checkbox);

        capture = (CheckBox) findViewById(R.id.capture_checkbox);
        scale = (CheckBox) findViewById(R.id.scale_checkbox);

        defenseTwoSpinner = (Spinner) findViewById(R.id.defense2);
        defenseThreeSpinner = (Spinner) findViewById(R.id.defense3);
        defenseFourSpinner = (Spinner) findViewById(R.id.defense4);
        defenseFiveSpinner = (Spinner) findViewById(R.id.defense5);

        fast2 = (CheckBox) findViewById(R.id.fast2);
        fast3 = (CheckBox) findViewById(R.id.fast3);
        fast4 = (CheckBox) findViewById(R.id.fast4);
        fast5 = (CheckBox) findViewById(R.id.fast5);
        
        defenseNumber = -1;
        shots = new ArrayList<>();
        highTextView = (TextView)findViewById(R.id.high_text_view);
        lowTextView = (TextView)findViewById(R.id.low_text_view);
        missTextView = (TextView)findViewById(R.id.miss_text_view);
        defenseSelectedTextView = (TextView)findViewById(R.id.defense_selected_text_view);

        highTextView.setText(0 + "");
        lowTextView.setText(0 + "");
        missTextView.setText(0 + "");
        defenseSelectedTextView.setText(defenseSelectedString +  " None");


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
                    //////
                    pickPort.setChecked(matchRecord.getPickPort());
                    pickChevel.setChecked(matchRecord.getPickChevel());
                    pickMoat.setChecked(matchRecord.getPickMoat());
                    pickRamp.setChecked(matchRecord.getPickRamp());
                    pickSally.setChecked(matchRecord.getPickSally());
                    pickRock.setChecked(matchRecord.getPickRock());
                    pickRough.setChecked(matchRecord.getPickRough());
                    startBall.setChecked(matchRecord.getStartWithBall());
                    autoSpy.setChecked(matchRecord.getAutoSpy());
                    defenseReach.setSelection(matchRecord.getDefenseSpinner());
                    didReachDefense.setChecked(matchRecord.getReachedDefense());


                    defenseTwoSpinner.setSelection(matchRecord.getSpinner2());
                    defenseThreeSpinner.setSelection(matchRecord.getSpinner3());
                    defenseFourSpinner.setSelection(matchRecord.getSpinner4());
                    defenseFiveSpinner.setSelection(matchRecord.getSpinner5());

                    secretPassage.setChecked(matchRecord.getSecreatPassage());
                    netural.setChecked(matchRecord.getNetural());
                    courtyard.setChecked(matchRecord.getCourtYard());
                    steal.setChecked(matchRecord.getSteal());
                    block.setChecked(matchRecord.getBlock());
                    fast.setChecked(matchRecord.getFast());
                    penalty.setChecked(matchRecord.getPenalty());
                    breach.setChecked(matchRecord.getBreach());
                    capture.setChecked(matchRecord.getCapture());
                    scale.setChecked(matchRecord.getScale());

                    fast2.setChecked(matchRecord.isFast2());
                    fast3.setChecked(matchRecord.isFast3());
                    fast4.setChecked(matchRecord.isFast4());
                    fast5.setChecked(matchRecord.isFast5());


                    /////
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
            highTextView.setText(Integer.parseInt(highTextView.getText().toString()) + 1 + "");
            defenseNumber = -1;
            defenseSelectedTextView.setText(defenseSelectedString + " None");

        }
        else if(tag.equals("LOW"))
        {
            shots.add(defenseNumber + "L");
            lowTextView.setText(Integer.parseInt(lowTextView.getText().toString()) + 1 + "");
            defenseNumber = -1;
            defenseSelectedTextView.setText(defenseSelectedString + " None");
        }
        else if(tag.equals("MISS"))
        {
            shots.add(defenseNumber + "M");
            missTextView.setText(Integer.parseInt(missTextView.getText().toString()) + 1 + "");
            defenseNumber = -1;
            defenseSelectedTextView.setText(defenseSelectedString + " None");

        }
        else
        {
            System.out.println("Could not find tag " + tag);
        }


    }

    public void defenseButtonClicked(View v)
    {
        defenseNumber = Integer.parseInt(((Button)v).getTag().toString());
        defenseSelectedTextView.setText(defenseSelectedString + " " + defenseNumber);
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
                highTextView.setText(Integer.parseInt(highTextView.getText().toString()) - 1 + "");
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
                lowTextView.setText(Integer.parseInt(lowTextView.getText().toString()) - 1 + "");
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
                missTextView.setText(Integer.parseInt(missTextView.getText().toString()) - 1 + "");
            }
        }
        else
        {
            System.out.println("Could not fing tag " + tag);
        }
    }

}
