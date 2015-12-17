package com.maxkrass.appreciate.fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.maxkrass.appreciate.R;
import com.maxkrass.appreciate.activities.ViewPitScout;
import com.maxkrass.appreciate.adapter.PitScoutTeamAdapter;
import com.maxkrass.appreciate.objects.PitRecord;
import com.orm.SugarRecord;


public class PitScoutFragment extends Fragment {

	public RecyclerView recyclerView;
	public PitScoutTeamAdapter teamAdapter;


	SharedPreferences settings;


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.main_layout, container, false);
		settings = PreferenceManager.getDefaultSharedPreferences(getActivity());
		recyclerView = (RecyclerView) v.findViewById(R.id.scouts_list);
		View.OnClickListener TimClickListener = new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent timIntent = new Intent(getContext(), ViewPitScout.class);
				timIntent.putExtra("position", recyclerView.getChildAdapterPosition(v) + 1);
				getActivity().startActivity(timIntent);
			}
		};
		teamAdapter = new PitScoutTeamAdapter(getActivity(), TimClickListener, null, SugarRecord.listAll(PitRecord.class));
		recyclerView.setAdapter(teamAdapter);
		recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
		return v;
	}
}
