package com.maxkrass.appreciate.activities;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.Toast;

import com.manuelpeinado.fadingactionbar.view.ObservableScrollable;
import com.manuelpeinado.fadingactionbar.view.OnScrollChangedCallback;
import com.maxkrass.appreciate.R;
import com.maxkrass.appreciate.adapter.MainPagerAdapter;
import com.maxkrass.appreciate.objects.PitRecord;
import com.maxkrass.appreciate.views.CheckBoxWidget;
import com.orm.SugarRecord;

import org.xmlpull.v1.XmlSerializer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by Sarah on 12/17/15.
 */
public class MatchScoutOneTeam extends ActionBarActivity implements View.OnClickListener {

    String matchNumber = "";
    public LinearLayout auto;
    LinearLayout tele;
    LinearLayout endgame;
    private CheckBoxWidget driveToAuto;
    private CheckBoxWidget stacksTotesInAuto;
    private CheckBoxWidget containersIntoAutoZone;
    EditText teamNumber;
    EditText teleComment;
    EditText autoComment;
    Toolbar toolbar;
    private Drawable mActionBarBackgroundDrawable;
    private ImageView robotImage;
    SharedPreferences settings;

    public void onClick(View view) {
        if (view instanceof CheckBoxWidget) {
            CheckBoxWidget checkboxwidget = (CheckBoxWidget) view;
            checkboxwidget.setCheckBox(!checkboxwidget.isChecked());
        }
    }

    private void initAutoCBWs() {

        driveToAuto = new CheckBoxWidget(this);
        driveToAuto.setTitleView(getString(R.string.auto_zone_match_label));
        driveToAuto.setOnClickListener(this);
        stacksTotesInAuto = new CheckBoxWidget(this);
        stacksTotesInAuto.setTitleView(getString(R.string.program_auto_worked));
        stacksTotesInAuto.setOnClickListener(this);
        containersIntoAutoZone = new CheckBoxWidget(this);
        containersIntoAutoZone.setTitleView(getString(R.string.totes_auto_label));
        containersIntoAutoZone.setOnClickListener(this);
    }

    private void initEditTexts() {
        teamNumber = (EditText) findViewById(R.id.teamNumberEditText);
        teleComment = (EditText) findViewById(R.id.tele_comment);
        autoComment = (EditText) findViewById(R.id.auto_comment);
    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.pit_toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_close);
        mActionBarBackgroundDrawable = toolbar.getBackground();
        robotImage = (ImageView) findViewById(R.id.pit_scout_image);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.save_menu, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pit_save_dialog);
        settings = PreferenceManager.getDefaultSharedPreferences(this);
        initToolbar();
        //initEditTexts();
        //initSpinners();
        initAutoCBWs();
        //initAbilitiesCBWs();
        // initTeleCBWs();
    }

    /*@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_action:
                if (teamNumber.getText().toString().equals("")) {
                    teamNumber.setError("A Team Number is required");
                } else {
                    savePitScoutToDatabase();
                }
                break;
            case R.id.clear_action:
                clearFields();
                break;
            case R.id.pic_action:
                dispatchTakePictureIntent();
                break;
            case android.R.id.home:
                finish();
                break;
            case R.id.settings:
                startActivity(new Intent(this, SettingsActivity.class));
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }*/
    private void clearFields() {
        driveToAuto.setCheckBox(false);
        stacksTotesInAuto.setCheckBox(false);
        containersIntoAutoZone.setCheckBox(false);

        //scrollView.scrollTo(0, 0);
    }

    public void saveMatchScoutToDatabase() {

        List<PitRecord> listTest = SugarRecord.find(PitRecord.class, "team_Number=?", teamNumber.getText().toString());
       /* if (listTest.size() > 0) {
            System.out.println("it works!");
            Log.e("Sarah1", String.valueOf(listTest.size()));

            // 1. Instantiate an AlertDialog.Builder with its constructor
            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            builder.setMessage("Team number already entered")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

                        }
                    });

            AlertDialog dialog = builder.create();
            dialog.show();
        }*/
        System.out.print(listTest.size());
        Log.e("Sarah2", String.valueOf(listTest.size()));
        PitRecord record = new PitRecord();


        record.setAutoComment(autoComment.getText().toString());

        record.setTeleComment(teleComment.getText().toString());


        record.setCoopAbilityCBW(stacksTotesInAuto.isChecked());
        record.setAutoZoneAutoCBW(driveToAuto.isChecked());
        record.setContainersAbilityCBW(containersIntoAutoZone.isChecked());

        record.setTeamNumber(teamNumber.getText().toString());
        record.save();
        finish();

        Toast.makeText(this, "Match Scout " + MainActivity.singleton.getLastSavedTeam() + " saved successfully", Toast.LENGTH_LONG).show();
        MainPagerAdapter.pitScouts.teamAdapter.add(record);

        Log.e("Sarah", "Saved 1");
        Log.e("Sarah", record.toString());


    }

}
