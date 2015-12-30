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
import com.maxkrass.appreciate.objects.MatchRecord;
import com.maxkrass.appreciate.views.CheckBoxWidget;
import com.orm.query.Condition;
import com.orm.query.Select;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

public class ViewMatchScoutSelect extends ActionBarActivity {


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
		List<MatchRecord> leroy = Select.from(MatchRecord.class).where(Condition.prop("team_number").eq(teamNumber)).orderBy("CAST(team_number AS int)").list();
		for (MatchRecord record : leroy) {
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
			((TextView) v.findViewById(R.id.match_number)).setText(record.getMatchNumber());
			pointsAutoLabel.setText(String.valueOf(record.getAutoPoints()));
			commentAutoLabel.setText(record.getAutoComment().equals("") ? "No Comments" : record.getAutoComment());
			pointsTeleLabel.setText(String.valueOf(record.getTotalPoints()));
			commentTeleLabel.setText(record.getTeleComment().equals("") ? "No Comments" : record.getTeleComment());
				view.addView(v);
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
