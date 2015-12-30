package com.maxkrass.appreciate.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.maxkrass.appreciate.R;
import com.maxkrass.appreciate.Team;
import com.maxkrass.appreciate.adapter.MatchScoutTeamAdapter;
import com.maxkrass.appreciate.objects.MatchRecord;
import com.orm.query.Select;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//TODO renew data storage system; take a look at PitScoutFragment for that
public class MatchScoutFragment extends Fragment {
	RecyclerView recyclerView;
	public MatchScoutTeamAdapter teamAdapter;


	SharedPreferences settings;

	public List<MatchRecord> getTeams() {


		return Select.from(MatchRecord.class).orderBy("CAST(team_number AS int)").list();
		}


	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.main_layout, container, false);
		settings = PreferenceManager.getDefaultSharedPreferences(getActivity());
		recyclerView = (RecyclerView) v.findViewById(R.id.scouts_list);
		teamAdapter = new MatchScoutTeamAdapter(getActivity(), getTeams());
		recyclerView.setAdapter(teamAdapter);
		recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
		return v;
	}
}
