package com.maxkrass.appreciate.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.maxkrass.appreciate.R;
import com.maxkrass.appreciate.objects.PitRecord;
import com.maxkrass.appreciate.views.CheckBoxWidget;
import com.orm.SugarRecord;
import com.orm.query.Select;

import java.util.ArrayList;

public class ViewPitScout extends AppCompatActivity {
	LinearLayout abilitiesList;
	LinearLayout autoList;
	LinearLayout teleList;

	TextView maxSpeed;
	TextView teamNumber;
	TextView teamName;
	TextView mainComment;
	TextView teleComment;
	TextView autoComment;
	TextView abilitiesComment;

	CheckBoxWidget wideTeleCBW;
	CheckBoxWidget autoZoneAutoCBW;
	CheckBoxWidget flexibleAutoCBW;
	CheckBoxWidget containersAbilityCBW;
	CheckBoxWidget containersAutoCBW;
	CheckBoxWidget coopAbilityCBW;
	CheckBoxWidget humanPlayerTeleCBW;
	CheckBoxWidget landfillTeleCBW;
	CheckBoxWidget noodlesAbilityCBW;
	CheckBoxWidget narrowTeleCBW;
	CheckBoxWidget shiftingAbilityCBW;
	CheckBoxWidget stepTeleCBW;
	CheckBoxWidget totesAbilityCBW;
	CheckBoxWidget totesAutoCBW;

	TextView cimNumField;
	TextView driveField;
	TextView highestPossibleStackField;
	TextView wheelNumField;
	TextView wheelTypeField;

	Toolbar toolbar;

	Intent intent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_pit);
		toolbar = (Toolbar) findViewById(R.id.view_pit_toolbar);
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		teamNumber = (TextView) findViewById(R.id.team_number_field);
		teamName = (TextView) findViewById(R.id.team_name_field);
		driveField = (TextView) findViewById(R.id.drive_type_field);
		wheelTypeField = (TextView) findViewById(R.id.wheel_type_field);
		wheelNumField = (TextView) findViewById(R.id.wheel_num_field);
		cimNumField = (TextView) findViewById(R.id.cim_num_field);
		maxSpeed = (TextView) findViewById(R.id.max_speed_field);
		mainComment = (TextView) findViewById(R.id.comment_main_field);
		teleList = (LinearLayout) findViewById(R.id.tele_list);
		highestPossibleStackField = (TextView) findViewById(R.id.highest_possible_stack_field);
		teleComment = (TextView) findViewById(R.id.comment_tele_field);
		autoList = (LinearLayout) findViewById(R.id.auto_list);
		autoComment = (TextView) findViewById(R.id.comment_auto_field);
		abilitiesList = (LinearLayout) findViewById(R.id.abilities_list);
		abilitiesComment = (TextView) findViewById(R.id.comment_abilities_field);
		wideTeleCBW = new CheckBoxWidget(this);
		wideTeleCBW.setTitleView(getString(R.string.wide_tele_label));
		teleList.addView(wideTeleCBW);
		narrowTeleCBW = new CheckBoxWidget(this);
		narrowTeleCBW.setTitleView(getString(R.string.narrow_tele_label));
		teleList.addView(narrowTeleCBW);
		stepTeleCBW = new CheckBoxWidget(this);
		stepTeleCBW.setTitleView(getString(R.string.step_tele_label));
		teleList.addView(stepTeleCBW);
		landfillTeleCBW = new CheckBoxWidget(this);
		landfillTeleCBW.setTitleView(getString(R.string.landfill_tele_label));
		teleList.addView(landfillTeleCBW);
		humanPlayerTeleCBW = new CheckBoxWidget(this);
		humanPlayerTeleCBW.setTitleView(getString(R.string.tote_chute_tele_label));
		teleList.addView(humanPlayerTeleCBW);
		autoZoneAutoCBW = new CheckBoxWidget(this);
		autoZoneAutoCBW.setTitleView(getString(R.string.auto_zone_auto_label));
		autoList.addView(autoZoneAutoCBW);
		totesAutoCBW = new CheckBoxWidget(this);
		totesAutoCBW.setTitleView(getString(R.string.totes_auto_label));
		autoList.addView(totesAutoCBW);
		containersAutoCBW = new CheckBoxWidget(this);
		containersAutoCBW.setTitleView(getString(R.string.containers_auto_label));
		autoList.addView(containersAutoCBW);
		flexibleAutoCBW = new CheckBoxWidget(this);
		flexibleAutoCBW.setTitleView(getString(R.string.flexible_auto_label));
		autoList.addView(flexibleAutoCBW);
		containersAbilityCBW = new CheckBoxWidget(this);
		containersAbilityCBW.setTitleView(getString(R.string.containers_abilities_label));
		totesAbilityCBW = new CheckBoxWidget(this);
		totesAbilityCBW.setTitleView(getString(R.string.totes_abilities_label));
		noodlesAbilityCBW = new CheckBoxWidget(this);
		noodlesAbilityCBW.setTitleView(getString(R.string.noodles_abilities_label));
		shiftingAbilityCBW = new CheckBoxWidget(this);
		shiftingAbilityCBW.setTitleView(getString(R.string.shifting_abilities_label));
		coopAbilityCBW = new CheckBoxWidget(this);
		coopAbilityCBW.setTitleView(getString(R.string.coop_abilities_label));
		abilitiesList.addView(containersAbilityCBW);
		abilitiesList.addView(totesAbilityCBW);
		abilitiesList.addView(noodlesAbilityCBW);
		abilitiesList.addView(shiftingAbilityCBW);
		abilitiesList.addView(coopAbilityCBW);
		intent = getIntent();
		PitRecord timsquad = (PitRecord) Select.from(PitRecord.class).orderBy("CAST(team_number AS int)").list().get(intent.getIntExtra("position", 0));
		teamNumber.setText(String.valueOf(timsquad.getTeamNumber()));
		teamName.setText(timsquad.getTeamName());
		driveField.setText(String.valueOf(timsquad.getDriveSpinner()));
		wheelTypeField.setText(getResources().getStringArray(R.array.wheel_type)[timsquad.getWheelTypeSpinner()]);
		wheelNumField.setText(getResources().getStringArray(R.array.num_wheels)[timsquad.getWheelNumSpinner()]);
		cimNumField.setText(getResources().getStringArray(R.array.num_cims)[timsquad.getCimNumSpinner()]);
		maxSpeed.setText(getResources().getStringArray(R.array.speeds)[timsquad.getMaxSpeed()]);
		mainComment.setText(timsquad.getMainComment());
		wideTeleCBW.setCheckBox((timsquad.isWideTeleCBW()));
		narrowTeleCBW.setCheckBox((timsquad.isNarrowTeleCBW()));
		stepTeleCBW.setCheckBox((timsquad.isStepTeleCBW()));
		landfillTeleCBW.setCheckBox((timsquad.isLandfillTeleCBW()));
		humanPlayerTeleCBW.setCheckBox((timsquad.isHumanPlayerTeleCBW()));
		highestPossibleStackField.setText(getResources().getStringArray(R.array.highest_possible_stack)[timsquad.getHighestPossibleStackSpinner()]);
		teleComment.setText(timsquad.getTeleComment());
		autoZoneAutoCBW.setCheckBox((timsquad.isAutoZoneAutoCBW()));
		totesAutoCBW.setCheckBox((timsquad.isTotesAutoCBW()));
		containersAutoCBW.setCheckBox((timsquad.isContainersAbilityCBW()));
		flexibleAutoCBW.setCheckBox((timsquad.isFlexibleAutoCBW()));
		autoComment.setText(timsquad.getAutoComment());
		totesAbilityCBW.setCheckBox((timsquad.isTotesAbilityCBW()));
		containersAbilityCBW.setCheckBox((timsquad.isContainersAbilityCBW()));
		noodlesAbilityCBW.setCheckBox((timsquad.isNoodlesAbilityCBW()));
		shiftingAbilityCBW.setCheckBox((timsquad.isShiftingAbilityCBW()));
		coopAbilityCBW.setCheckBox((timsquad.isCoopAbilityCBW()));
		abilitiesComment.setText(timsquad.getAbilitiesComment());
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == android.R.id.home) {
			finish();
		}
		return true;
	}
}

