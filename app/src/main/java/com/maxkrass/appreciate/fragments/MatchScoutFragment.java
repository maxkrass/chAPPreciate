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

import com.maxkrass.appreciate.AlphanumComparator;
import com.maxkrass.appreciate.R;
import com.maxkrass.appreciate.Team;
import com.maxkrass.appreciate.adapter.MatchScoutTeamAdapter;
import com.maxkrass.appreciate.adapter.PitScoutTeamAdapter;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MatchScoutFragment extends Fragment {
	RecyclerView recyclerView;
	public MatchScoutTeamAdapter teamAdapter;

	public File[] teamFiles;
	File localScoutFolder;
	SharedPreferences settings;

	public List<Team> getTeams() {
		List<Team> teams = new ArrayList<>();
		localScoutFolder =  new File(String.valueOf(Environment.getExternalStorageDirectory()) + "/" + settings.getString("folder_name", "FRCScouting") + "/data");
		if (!localScoutFolder.exists() && !localScoutFolder.mkdir()) {
			teams.add(new Team("0"));
		}
		final FilenameFilter filenameFilter = new FilenameFilter() {
			@Override
			public boolean accept(File dir, String filename) {
				return filename.startsWith("Match ") && filename.endsWith(".match");
			}
		};
		FilenameFilter filter = new FilenameFilter() {
			@Override
			public boolean accept(File dir, String filename) {
				File folder = new File(dir, filename);
				File[] files = folder.listFiles(filenameFilter);
				return files != null && files.length > 0;
			}
		};
		teamFiles = localScoutFolder.listFiles(filter);
		if (teamFiles == null || teamFiles.length == 0) {
			teams.add(new Team("0"));
		} else {
			for (File teamFile : teamFiles) {
				teams.add(new Team(teamFile.getName().substring(5)));
			}
		}
		Collections.sort(teams, new AlphanumComparator());
		return teams;
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
