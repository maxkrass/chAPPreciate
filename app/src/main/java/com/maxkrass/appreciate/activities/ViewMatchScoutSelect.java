package com.maxkrass.appreciate.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.maxkrass.appreciate.R;
import com.maxkrass.appreciate.Team;
import com.maxkrass.appreciate.views.CheckBoxWidget;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;

public class ViewMatchScoutSelect extends ActionBarActivity {

	File[] allMatchFiles;

	TextView pointsAutoLabel;
	TextView commentAutoLabel;
	TextView pointsTeleLabel;
	TextView commentTeleLabel;

	CheckBoxWidget stackedTotesCBW;
	CheckBoxWidget autoZoneCBW;
	CheckBoxWidget workedCBW;
	CheckBoxWidget functionalCBW;
	CheckBoxWidget coopertitionCBW;

	LinearLayout autoMatchList;
	LinearLayout teleMatchList;
	LinearLayout stackList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		LayoutInflater inflater = getLayoutInflater();
		LinearLayout mainerMainView = new LinearLayout(this);
		mainerMainView.setOrientation(LinearLayout.VERTICAL);
		Toolbar toolbar = new Toolbar(this);
		Intent intent = getIntent();
		String teamNumber = intent.getStringExtra("teamNumber");
		toolbar.setTitle(teamNumber);
		setSupportActionBar(toolbar);
		toolbar.setBackgroundColor(getResources().getColor(R.color.primary));
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		mainerMainView.addView(toolbar, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) getResources().getDimension(R.dimen.abc_action_bar_default_height_material)));
		ScrollView mainView = new ScrollView(this);
		mainerMainView.addView(mainView);
		LinearLayout view = new LinearLayout(this);
		view.setOrientation(LinearLayout.VERTICAL);
		view.setPadding(0, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, getResources().getDisplayMetrics()), 0, 0);
		File teamFolder = new File(String.valueOf(Environment.getExternalStorageDirectory()) + "/" + MainActivity.singleton.settings.getString("folder_name", "FRCScouting") + "/data/" + teamNumber);
		FilenameFilter filter = new FilenameFilter() {
			@Override
			public boolean accept(File dir, String filename) {
				return filename.startsWith("Match ") && filename.endsWith(".match");
			}
		};
		allMatchFiles = teamFolder.listFiles(filter);
		if (allMatchFiles != null && allMatchFiles.length > 0) {
			for (File file : allMatchFiles) {
				View v = inflater.inflate(R.layout.match_card, null);
				stackList = (LinearLayout) v.findViewById(R.id.stack_list);
				pointsAutoLabel = (TextView) v.findViewById(R.id.points_auto_label);
				commentAutoLabel = (TextView) v.findViewById(R.id.comment_auto_label);
				pointsTeleLabel = (TextView) v.findViewById(R.id.points_total_label);
				commentTeleLabel = (TextView) v.findViewById(R.id.comments_tele_label);
				autoMatchList = (LinearLayout) v.findViewById(R.id.auto_match_list);
				teleMatchList = (LinearLayout) v.findViewById(R.id.tele_match_list);
				autoZoneCBW = new CheckBoxWidget(this);
				autoZoneCBW.setTitleView(getString(R.string.auto_zone_match_label));
				autoMatchList.addView(autoZoneCBW);
				stackedTotesCBW = new CheckBoxWidget(this);
				stackedTotesCBW.setTitleView(getString(R.string.totes_auto_label));
				autoMatchList.addView(stackedTotesCBW);
				workedCBW = new CheckBoxWidget(this);
				workedCBW.setTitleView(getString(R.string.program_auto_worked));
				autoMatchList.addView(workedCBW);
				functionalCBW = new CheckBoxWidget(this);
				functionalCBW.setTitleView(getString(R.string.functional_tele_match));
				teleMatchList.addView(functionalCBW);
				coopertitionCBW = new CheckBoxWidget(this);
				coopertitionCBW.setTitleView(getString(R.string.coopertition_tele_match));
				teleMatchList.addView(coopertitionCBW);
				ArrayList<String> matchData = Team.getTextFromFile(file);
				((TextView) v.findViewById(R.id.match_number)).setText(file.getName().substring(0, file.getName().length() - 6));
				autoZoneCBW.setCheckBox(Boolean.parseBoolean(matchData.get(0)));
				stackedTotesCBW.setCheckBox(Boolean.parseBoolean(matchData.get(1)));
				workedCBW.setCheckBox(Boolean.parseBoolean(matchData.get(2)));
				pointsAutoLabel.setText(matchData.get(3));
				commentAutoLabel.setText(matchData.get(4).equals(" ") ? "No Comments" : matchData.get(4));
				for (int i = 0; i < Integer.parseInt(matchData.get(5)); i++) {
					View stackView = inflater.inflate(R.layout.view_stack, stackList);
					((TextView) stackView.findViewById(R.id.stack_label)).setText("Stack " + (i + 1));
					((TextView) stackView.findViewById(R.id.totes_label)).setText(matchData.remove(6));
					((TextView) stackView.findViewById(R.id.containers_label)).setText(matchData.remove(6));
					FrameLayout noodle_cbw_container = (FrameLayout) stackView.findViewById(R.id.noodle_box);
					CheckBoxWidget noodle = new CheckBoxWidget(this);
					noodle.setTitleView("Noodle");
					noodle.setCheckBox(Boolean.parseBoolean(matchData.remove(6)));
					noodle_cbw_container.addView(noodle);
				}
				functionalCBW.setCheckBox(Boolean.parseBoolean(matchData.get(6)));
				coopertitionCBW.setCheckBox(Boolean.parseBoolean(matchData.get(7)));
				pointsTeleLabel.setText(matchData.get(8));
				commentTeleLabel.setText(matchData.get(9).equals(" ") ? "No Comments" : matchData.get(9));
				view.addView(v);
			}
		} else {
			finish();
		}
		mainView.addView(view);
		setContentView(mainerMainView);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == android.R.id.home)
			finish();
		return true;
	}
}
