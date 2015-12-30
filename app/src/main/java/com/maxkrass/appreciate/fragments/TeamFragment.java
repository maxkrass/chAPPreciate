package com.maxkrass.appreciate.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.maxkrass.appreciate.R;
import com.maxkrass.appreciate.views.CheckBoxWidget;

public class TeamFragment extends Fragment implements android.view.View.OnClickListener {

	LinearLayout autoList;
	LinearLayout teleList;
	public LinearLayout teleMatchList;

	public EditText teamName;
	public EditText autoPoints;
	public EditText totalPoints;
	public EditText autoComment;
	public EditText teleComment;

	public CheckBoxWidget stackedTotesCBW;
	public CheckBoxWidget autoZoneCBW;
	public CheckBoxWidget workedCBW;
	public CheckBoxWidget functionalCBW;
	public CheckBoxWidget coopertitionCBW;

	ScrollView scrollView;

	public void onClick(View view) {
		if (view instanceof CheckBoxWidget) {
			CheckBoxWidget checkboxwidget = (CheckBoxWidget) view;
			boolean flag;
			flag = !checkboxwidget.isChecked();
			checkboxwidget.setCheckBox(flag);
		}
	}

	private void initCBWs(View v) {
		autoList = (LinearLayout) v.findViewById(R.id.auto_match_list);
		autoZoneCBW = new CheckBoxWidget(getActivity());
		autoZoneCBW.setTitleView(getString(R.string.auto_zone_match_label));
		autoZoneCBW.setOnClickListener(this);
		autoList.addView(autoZoneCBW);
		stackedTotesCBW = new CheckBoxWidget(getActivity());
		stackedTotesCBW.setTitleView(getString(R.string.totes_auto_label));
		stackedTotesCBW.setOnClickListener(this);
		autoList.addView(stackedTotesCBW);
		workedCBW = new CheckBoxWidget(getActivity());
		workedCBW.setTitleView(getString(R.string.program_auto_worked));
		workedCBW.setOnClickListener(this);
		autoList.addView(workedCBW);
		teleList = (LinearLayout) v.findViewById(R.id.tele_list);
		functionalCBW = new CheckBoxWidget(getActivity());
		functionalCBW.setTitleView(getString(R.string.functional_tele_match));
		functionalCBW.setOnClickListener(this);
		teleList.addView(functionalCBW);
		coopertitionCBW = new CheckBoxWidget(getActivity());
		coopertitionCBW.setTitleView(getString(R.string.coopertition_tele_match));
		coopertitionCBW.setOnClickListener(this);
		teleList.addView(coopertitionCBW);
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View mainView = inflater.inflate(R.layout.team_layout, container, false);
		teleMatchList = (LinearLayout) mainView.findViewById(R.id.tele_match_list);
		autoPoints = (EditText) mainView.findViewById(R.id.auto_points_field);
		autoComment = (EditText) mainView.findViewById(R.id.auto_comment_field);
		totalPoints = (EditText) mainView.findViewById(R.id.total_score_field);
		teleComment = (EditText) mainView.findViewById(R.id.tele_comment_field);
		scrollView = (ScrollView) mainView.findViewById(R.id.scrollView);
		initCBWs(mainView);
		return mainView;
	}

	public void clearFields() {
		autoZoneCBW.setCheckBox(false);
		stackedTotesCBW.setCheckBox(false);
		workedCBW.setCheckBox(false);
		functionalCBW.setCheckBox(false);
		coopertitionCBW.setCheckBox(false);
		autoPoints.setText("0");
		totalPoints.setText("0");
		autoComment.setText("");
		teleComment.setText("");
		teleMatchList.removeAllViews();
	}

	public void saveMatchTeam() {
		//TODO save this team's match to the database
	}

	/*public void putDataIntoFields(String[] data) {
		String driveTypeArray[];
		String wheelNumArray[];
		String cimNumArray[];
		String wheelTypeArray[];
		String highestStackArray[];
		teamNumber.setText(data[0]);
		driveTypeArray = getResources().getStringArray(R.array.drive_type);
		driveSpinner.setSelection(Arrays.asList(driveTypeArray).indexOf(data[1]));
		wheelNumArray = getResources().getStringArray(R.array.num_wheels);
		wheelNumSpinner.setSelection(Arrays.asList(wheelNumArray).indexOf(data[2]));
		cimNumArray = getResources().getStringArray(R.array.num_cims);
		cimNumSpinner.setSelection(Arrays.asList(cimNumArray).indexOf(data[3]));
		wheelTypeArray = getResources().getStringArray(R.array.wheel_type);
		wheelTypeSpinner.setSelection(Arrays.asList(wheelTypeArray).indexOf(data[4]));
		maxSpeed.setText(data[5]);
		autoZoneAutoCBW.setCheckBox(Boolean.parseBoolean(data[6]));
		totesAutoCBW.setCheckBox(Boolean.parseBoolean(data[7]));
		containersAutoCBW.setCheckBox(Boolean.parseBoolean(data[8]));
		flexibleAutoCBW.setCheckBox(Boolean.parseBoolean(data[9]));
		containersAbilityCBW.setCheckBox(Boolean.parseBoolean(data[11]));
		totesAbilityCBW.setCheckBox(Boolean.parseBoolean(data[12]));
		noodlesAbilityCBW.setCheckBox(Boolean.parseBoolean(data[13]));
		shiftingAbilityCBW.setCheckBox(Boolean.parseBoolean(data[14]));
		coopAbilityCBW.setCheckBox(Boolean.parseBoolean(data[15]));
		wideTeleCBW.setCheckBox(Boolean.parseBoolean(data[16]));
		narrowTeleCBW.setCheckBox(Boolean.parseBoolean(data[17]));
		stepTeleCBW.setCheckBox(Boolean.parseBoolean(data[18]));
		landfillTeleCBW.setCheckBox(Boolean.parseBoolean(data[19]));
		humanPlayerTeleCBW.setCheckBox(Boolean.parseBoolean(data[20]));
		highestStackArray = getResources().getStringArray(R.array.highest_possible_stack);
		highestStackSpinner.setSelection(Arrays.asList(highestStackArray).indexOf(data[21]));
	}*/
}
