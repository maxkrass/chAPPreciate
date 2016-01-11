package com.maxkrass.appreciate.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.maxkrass.appreciate.R;
import com.maxkrass.appreciate.Tools;
import com.maxkrass.appreciate.adapter.MatchScoutTeamAdapter;
import com.maxkrass.appreciate.objects.MatchRecord;
import com.orm.query.Select;

import java.util.List;

//TODO renew data storage system; take a look at PitScoutFragment for that
public class MatchScoutFragment extends Fragment {
	RecyclerView recyclerView;
	public MatchScoutTeamAdapter teamAdapter;

	public List<MatchRecord> getTeams() {
		return Select.from(MatchRecord.class).groupBy("team_number").orderBy("CAST(team_number AS int)").list();
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		recyclerView = new RecyclerView(getContext());
		teamAdapter = new MatchScoutTeamAdapter(getActivity(), getTeams());
		recyclerView.setPadding(0, (int) Tools.dpToPixels(getContext(), 8), 0, 0);
		recyclerView.setClipToPadding(false);
		recyclerView.setAdapter(teamAdapter);
		recyclerView.setHasFixedSize(true);
		recyclerView.setBackgroundColor(ContextCompat.getColor(getActivity(), R.color.grey50));
		recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
		return recyclerView;
	}
}
