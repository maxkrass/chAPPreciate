package com.maxkrass.appreciate.activities;

import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.support.v4.app.FragmentManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.maxkrass.appreciate.R;
import com.maxkrass.appreciate.Tools;
import com.maxkrass.appreciate.adapter.AlliancePagerAdapter;
import com.maxkrass.appreciate.adapter.MainPagerAdapter;
import com.maxkrass.appreciate.fragments.TeamFragment;
import com.maxkrass.appreciate.objects.MatchRecord;
import com.maxkrass.appreciate.views.CheckBoxWidget;

import java.util.ArrayList;
import java.lang.reflect.Field;

/**
 * Sarah made this for APPreciate on 12/17/15.
 */

public class MatchScoutOneTeam extends BaseActivity implements View.OnClickListener {

    TeamFragment fragment;

    EditText teamNumberField;
    EditText matchNumberField;
    Spinner matchTypeSpinner;


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


    private CheckBox fast1;
    private CheckBox fast2;
    private CheckBox fast3;
    private CheckBox fast4;
    private CheckBox fast5;



    Button autoLowGoalMinus;
    TextView autoLowGoal;
    Button getAutoLowGoalPlus;

    ;
    int alg = 0;

    TextView autoHighGoal;
    int ahg = 0;


    TextView def1;
    int d1;

    TextView def2;
    int d2 = 0;

    TextView def3;
    int d3 = 0;
    TextView def4;
    int d4 = 0;

    TextView def5;
    int d5 = 0;

    TextView lowGoal;
    NumberPicker autoLG;


    int defenseNumber;
    ArrayList<String> shots;
    TextView highTextView;
    TextView lowTextView;
    TextView missTextView;
    TextView defenseSelectedTextView;
    final String defenseSelectedString = "Defense Selected:";

    public CheckBox scaleLeftCheckbox;
    public CheckBox scaleRightCheckbox;
    public CheckBox scaleMiddleCheckbox;

    AdapterView.OnItemSelectedListener defenseItemSelectedListener;

    RelativeLayout defenseImageLayout;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.match_layout_one_team);
        Toolbar toolbar = (Toolbar) findViewById(R.id.one_team_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        teamNumberField = (EditText) findViewById(R.id.team_number_field);
        matchNumberField = (EditText) findViewById(R.id.match_number_field);
        matchTypeSpinner = (Spinner) findViewById(R.id.match_number_spinner);
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

        defenseImageLayout = (RelativeLayout)findViewById(R.id.defense_image_layout);





        capture = (CheckBox) findViewById(R.id.capture_checkbox);

        defenseItemSelectedListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Button defenseTwoButton = (Button)findViewById(R.id.defenseButton2);
                Button defenseThreeButton = (Button)findViewById(R.id.defenseButton3);
                Button defenseFourButton = (Button)findViewById(R.id.defenseButton4);
                Button defenseFiveButton = (Button)findViewById(R.id.defenseButton5);

                Spinner defenseTwoSpinner = (Spinner)findViewById(R.id.defense2);
                Spinner defenseThreeSpinner = (Spinner)findViewById(R.id.defense3);
                Spinner defenseFourSpinner = (Spinner)findViewById(R.id.defense4);
                Spinner defenseFiveSpinner = (Spinner)findViewById(R.id.defense5);
                if(!(defenseTwoButton == null))
                {
                    defenseTwoButton.setText(defenseTwoSpinner.getSelectedItem().toString());
                    defenseThreeButton.setText(defenseThreeSpinner.getSelectedItem().toString());
                    defenseFourButton.setText(defenseFourSpinner.getSelectedItem().toString());
                    defenseFiveButton.setText(defenseFiveSpinner.getSelectedItem().toString());

                }
                else
                {
                    System.out.println("Buttons null");
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };

        ((Button)findViewById(R.id.defenseButton1)).setText("Low Bar");

        defenseTwoSpinner = (Spinner) findViewById(R.id.defense2);
        defenseThreeSpinner = (Spinner) findViewById(R.id.defense3);
        defenseFourSpinner = (Spinner) findViewById(R.id.defense4);
        defenseFiveSpinner = (Spinner) findViewById(R.id.defense5);

        defenseTwoSpinner.setOnItemSelectedListener(defenseItemSelectedListener);
        defenseThreeSpinner.setOnItemSelectedListener(defenseItemSelectedListener);
        defenseFourSpinner.setOnItemSelectedListener(defenseItemSelectedListener);
        defenseFiveSpinner.setOnItemSelectedListener(defenseItemSelectedListener);



        fast1 = (CheckBox) findViewById(R.id.fast);
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

        highTextView.setText(numShots('H') + "");
        lowTextView.setText(numShots('L') + "");
        missTextView.setText(numShots('M') + "");
        defenseSelectedTextView.setText(defenseSelectedString +  " None");

        scaleLeftCheckbox = (CheckBox)findViewById(R.id.hang_left_checkbox);
        scaleMiddleCheckbox = (CheckBox)findViewById(R.id.hang_middle_checkbox);
        scaleRightCheckbox = (CheckBox)findViewById(R.id.hang_right_checkbox);



        fragment = (TeamFragment) getSupportFragmentManager().findFragmentById(R.id.content_fragment);

        autoLowGoal = (TextView)findViewById(R.id.auto_lg);
        autoLowGoal.setText("0");
        autoHighGoal = (TextView)findViewById(R.id.auto_hg);
        autoHighGoal.setText("0");
        def1 = (TextView)findViewById(R.id.def1);
        def1.setText("0");
        def2 = (TextView)findViewById(R.id.def2);
        def2.setText("0");
        def3 = (TextView)findViewById(R.id.def3);
        def3.setText("0");
        def4 = (TextView)findViewById(R.id.def4);
        def4.setText("0");
        def5 = (TextView)findViewById(R.id.def5);
        def5.setText("0");
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
                    matchRecord.setMatchNumber(matchTypeSpinner.getSelectedItem().toString() + " " + matchNumberField.getText().toString());
                    //////

                    matchRecord.setPickPort(pickPort.isChecked());
                    matchRecord.setPickChevel(pickChevel.isChecked());
                    matchRecord.setPickMoat(pickMoat.isChecked());
                    matchRecord.setPickRamp(pickRamp.isChecked());
                    matchRecord.setPickSally(pickSally.isChecked());
                    matchRecord.setPickRock(pickRock.isChecked());
                    matchRecord.setPickRough(pickRough.isChecked());

                    matchRecord.setStartWithBall(startBall.isChecked());
                    matchRecord.setAutoSpy(autoSpy.isChecked());
                    matchRecord.setDefenseReach(defenseReach.getSelectedItem().toString());
                    matchRecord.setReachedDefense(didReachDefense.isChecked());


                    // TODO: 2/23/16 add spinner 1
                    matchRecord.setSpinner2(defenseTwoSpinner.getSelectedItemPosition());
                    matchRecord.setSpinner3(defenseThreeSpinner.getSelectedItemPosition());
                    matchRecord.setSpinner4(defenseFourSpinner.getSelectedItemPosition());
                    matchRecord.setSpinner5(defenseFiveSpinner.getSelectedItemPosition());


                    matchRecord.setSecreatPassage(secretPassage.isChecked());
                    matchRecord.setNetural(netural.isChecked());
                    matchRecord.setCourtYard(courtyard.isChecked());
                    matchRecord.setSteal(steal.isChecked());
                    matchRecord.setBlock(block.isChecked());
                    matchRecord.setFast(fast.isChecked());
                    matchRecord.setPenalty(penalty.isChecked());
                    matchRecord.setBreach(breach.isChecked());
                    matchRecord.setCapture(capture.isChecked());
                    matchRecord.setScaleLeft(scaleLeftCheckbox.isChecked());
                    matchRecord.setScaleMiddle(scaleMiddleCheckbox.isChecked());
                    matchRecord.setScaleRight(scaleRightCheckbox.isChecked());


                    matchRecord.setFast1(fast1.isChecked());
                    matchRecord.setFast2(fast2.isChecked());
                    matchRecord.setFast3(fast3.isChecked());
                    matchRecord.setFast4(fast4.isChecked());
                    matchRecord.setFast5(fast5.isChecked());


                    matchRecord.setLowGoalAuto(Integer.parseInt(autoLowGoal.getText().toString()));
                    matchRecord.setHighGoalAuto(Integer.parseInt(autoHighGoal.getText().toString()));



                    System.out.println("Saving data");
                    matchRecord.save();




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

    @Override
    public void onClick(View view) {
        if (view instanceof CheckBoxWidget) {
            ((CheckBoxWidget) view).toggle();
        }
    }

    @Override
    public void onStop()
    {
        super.onStop();
        System.out.println("MtchScoutOneTeam onStop called");
    }

    @Override
    public void onResume()
    {
        super.onResume();
        System.out.println("MatchScoutOneTeam onResume called");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        System.out.println("MatchScoutOneTeam onDestroy called");
    }

    public void algSubClick(View view)
    {
        alg--;
        if (alg<0)
        {
            alg=0;
        }
        autoLowGoal.setText(Integer.toString(alg));
    }

    public void algPlusClick(View view)
    {
        alg++;
        autoLowGoal.setText(Integer.toString(alg));

    }

    public void ahgSubClick(View view)
    {
        ahg--;
        if (ahg<0)
        {
            ahg=0;
        }
        autoHighGoal.setText(Integer.toString(ahg));
    }

    public void ahgPlusClick(View view) {
        ahg++;
        autoHighGoal.setText(Integer.toString(ahg));

    }


    public void addShotClicked(View v)
    {
        String tag = v.getTag().toString();
        if(defenseNumber == -1)// no defense selected
        {
            Toast.makeText(this, "Must select a defense", Toast.LENGTH_SHORT).show();// tellthe player to select a defense
            return;
        }

        if(tag.equals("HIGH"))
        {
            shots.add(defenseNumber + "H"); //add the shot to the arraylist
            highTextView.setText(Integer.parseInt(highTextView.getText().toString()) + 1 + "");// add 1 to the textview

            // set the defense to none


        }
        else if(tag.equals("LOW"))
        {
            shots.add(defenseNumber + "L");// add the shot to the arraylist
            lowTextView.setText(Integer.parseInt(lowTextView.getText().toString()) + 1 + "");// add 1 to the textview

            //set the defense to none

        } else if (tag.equals("MISS"))
        {
            shots.add(defenseNumber + "M");// add the shot to the arraylist
            missTextView.setText(Integer.parseInt(missTextView.getText().toString()) + 1 + "");// add 1 to the textview
        }
        else
        {
            System.out.println("Could not find tag " + tag);
        }
        System.out.println("Added string " + shots.get(shots.size() - 1));

    }

    public void defenseButtonClicked(View v)
    {
        defenseNumber = Integer.parseInt(((Button)v).getTag().toString());//change the defense
        defenseSelectedTextView.setText(defenseSelectedString + " " + ((Button) v).getText().toString());// update the defense selected textview
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig)
    {
        super.onConfigurationChanged(newConfig);
        if(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE)
        {


        }
        if(newConfig.orientation == Configuration.ORIENTATION_PORTRAIT)
        {

        }
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
                    System.out.println("Removed string " + shots.remove(i));
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
                    System.out.println("Removed string " + shots.remove(i));
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
                    System.out.println("Removed string " + shots.remove(i));
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

    public void scaleCheckboxClicked(View v)
    {
        String tag = v.getTag().toString();
        if(!tag.equals("left"))
        {
            scaleLeftCheckbox.setChecked(false);
        }
        if (!tag.equals("middle"))
        {
            scaleMiddleCheckbox.setChecked(false);
        }
        if (!tag.equals("right"))
        {
            scaleRightCheckbox.setChecked(false);
        }
    }

    public int numShots(char shotType)
    {
        int numShots = 0;
        for(String shotString : shots)
        {
            if(shotString.charAt(1) == shotType)
            {
                numShots++;
            }
        }

        return numShots;
    }

    public void defensePlusClicked(View v)
    {
        String tag = v.getTag().toString();
        if(tag.equals("1"))
        {
            d1++;
            def1.setText(d1 + "");
        }
        if(tag.equals("2"))
        {
            d2++;
            def2.setText(d2 + "");
        }
        if(tag.equals("3"))
        {
            d3++;
            def3.setText(d3 + "");
        }
        if(tag.equals("4"))
        {
            d4++;
            def4.setText(d4 + "");
        }
        if(tag.equals("5"))
        {
            d5++;
            def5.setText(d5 + "");
        }

    }

    public void defenseMinusClicked(View v)
    {
        String tag = v.getTag().toString();

        if(tag.equals("1"))
        {
            d1--;
            if(d1 < 0)
            {
                d1 = 0;
            }
            def1.setText(d1 + "");
        }
        if(tag.equals("2"))
        {
            d2--;
            if(d2 < 0)
            {
                d2 = 0;
            }
            def2.setText(d2 + "");

        }
        if(tag.equals("3"))
        {
            d3--;
            if(d3 < 0)
            {
                d3 = 0;
            }
            def3.setText(d3 + "");
        }
        if(tag.equals("4"))
        {
            d4--;
            if(d4 < 0)
            {
                d4 =0;
            }
            def4.setText(d4 + "");
        }
        if(tag.equals("5"))
        {
            d5--;
            if(d5 < 0)
            {
                d5 = 0;
            }
            def5.setText(d5 + "");
        }


    }





}
