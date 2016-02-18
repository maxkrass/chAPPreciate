package com.maxkrass.appreciate.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.maxkrass.appreciate.R;
import com.maxkrass.appreciate.adapter.MatchCardAdapter;
import com.maxkrass.appreciate.objects.MatchRecord;
import com.orm.query.Condition;
import com.orm.query.Select;

import java.util.List;

public class ViewMatchScoutSelect extends BaseActivity {


	TextView pointsAutoLabel;
	TextView commentAutoLabel;
	TextView pointsTeleLabel;
	TextView commentTeleLabel;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_match_scouts);
		Intent intent = getIntent();
		String teamNumber = String.valueOf(intent.getIntExtra("teamNumber", 0));
		Toolbar toolbar = (Toolbar) findViewById(R.id.view_match_toolbar);
		toolbar.setTitle("Team " + teamNumber);
		setSupportActionBar(toolbar);
		RecyclerView recyclerView = (RecyclerView) findViewById(R.id.match_card_list);
		List<MatchRecord> leroy = Select.from(MatchRecord.class).where(Condition.prop("team_number").eq(teamNumber)).orderBy("CAST(team_number AS int)").list();
		if (leroy.size() < 1) finish();
		recyclerView.setAdapter(new MatchCardAdapter(ViewMatchScoutSelect.this, leroy));
		recyclerView.setHasFixedSize(true);         //Subject to change as card get fuller and information might be collapsed at first
		/*if (leroy.size() > 0)
			for (MatchRecord record : leroy) {
				View v = getLayoutInflater().inflate(R.layout.match_card, null);
				pointsAutoLabel = (TextView) v.findViewById(R.id.points_auto_label);
				commentAutoLabel = (TextView) v.findViewById(R.id.comment_auto_label);
				pointsTeleLabel = (TextView) v.findViewById(R.id.points_total_label);
				commentTeleLabel = (TextView) v.findViewById(R.id.comments_tele_label);
				//TODO When new season starts, add required CheckBoxWidgets
				//Left this piece of code as an example
				//There are two LinearLayouts (auto, tele) dedicated for checkboxes
				//autoZoneCBW = new CheckBoxWidget(this);
				//autoZoneCBW.setTitleView(getString(R.string.auto_zone_match_label));
				//autoMatchList.addView(autoZoneCBW);
				((TextView) v.findViewById(R.id.match_number)).setText("Match " + String.valueOf(record.getMatchNumber()));
				pointsAutoLabel.setText(String.valueOf(record.getAutoPoints()));
				commentAutoLabel.setText(record.getAutoComment().equals("") ? "No Comments" : record.getAutoComment());
				pointsTeleLabel.setText(String.valueOf(record.getTotalPoints()));
				commentTeleLabel.setText(record.getTeleComment().equals("") ? "No Comments" : record.getTeleComment());
				view.addView(v);
			}
		else finish();*/
	}


	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == android.R.id.home)
			finish();
		return true;
	}
}
