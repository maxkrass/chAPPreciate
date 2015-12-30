package com.maxkrass.appreciate.activities;

import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.maxkrass.appreciate.R;
import com.maxkrass.appreciate.adapter.MainPagerAdapter;
import com.maxkrass.appreciate.objects.PitRecord;
import com.maxkrass.appreciate.views.CheckBoxWidget;
import com.orm.SugarRecord;

import java.util.List;

/**
 * Sarah made this for APPreciate on 12/17/15.
 */

public class MatchScoutOneTeam extends BaseActivity implements View.OnClickListener {
    LinearLayout autoList;
    LinearLayout teleList;
    public LinearLayout teleMatchList;

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

    private void initCBWs() {
        autoList = (LinearLayout) findViewById(R.id.auto_match_list);
        autoZoneCBW = new CheckBoxWidget(MatchScoutOneTeam.this);
        autoZoneCBW.setTitleView(getString(R.string.auto_zone_match_label));
        autoZoneCBW.setOnClickListener(this);
        autoList.addView(autoZoneCBW);
        stackedTotesCBW = new CheckBoxWidget(MatchScoutOneTeam.this);
        stackedTotesCBW.setTitleView(getString(R.string.totes_auto_label));
        stackedTotesCBW.setOnClickListener(this);
        autoList.addView(stackedTotesCBW);
        workedCBW = new CheckBoxWidget(MatchScoutOneTeam.this);
        workedCBW.setTitleView(getString(R.string.program_auto_worked));
        workedCBW.setOnClickListener(this);
        autoList.addView(workedCBW);
        teleList = (LinearLayout) findViewById(R.id.tele_list);
        functionalCBW = new CheckBoxWidget(MatchScoutOneTeam.this);
        functionalCBW.setTitleView(getString(R.string.functional_tele_match));
        functionalCBW.setOnClickListener(this);
        teleList.addView(functionalCBW);
        coopertitionCBW = new CheckBoxWidget(MatchScoutOneTeam.this);
        coopertitionCBW.setTitleView(getString(R.string.coopertition_tele_match));
        coopertitionCBW.setOnClickListener(this);
        teleList.addView(coopertitionCBW);
    }

    @Nullable
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.team_layout);
        teleMatchList = (LinearLayout) findViewById(R.id.tele_match_list);
        autoPoints = (EditText) findViewById(R.id.auto_points_field);
        autoComment = (EditText) findViewById(R.id.auto_comment_field);
        totalPoints = (EditText) findViewById(R.id.total_score_field);
        teleComment = (EditText) findViewById(R.id.tele_comment_field);
        scrollView = (ScrollView) findViewById(R.id.scrollView);
        initCBWs();
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


}
