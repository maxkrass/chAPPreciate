package com.maxkrass.appreciate.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.maxkrass.appreciate.R;
import com.maxkrass.appreciate.Tools;
import com.maxkrass.appreciate.activities.PitScout;
import com.maxkrass.appreciate.activities.ViewPitScout;
import com.maxkrass.appreciate.adapter.IconArrayAdapter;
import com.maxkrass.appreciate.adapter.PitScoutTeamAdapter;
import com.maxkrass.appreciate.objects.PitRecord;
import com.orm.query.Select;


public class PitScoutFragment extends Fragment {

	public RecyclerView recyclerView;
	public PitScoutTeamAdapter teamAdapter;

	SharedPreferences settings;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.main_layout, container, false);
		settings = PreferenceManager.getDefaultSharedPreferences(getActivity());
		recyclerView = (RecyclerView) v.findViewById(R.id.scouts_list);
		recyclerView.setPadding(0, (int) Tools.dpToPixels(getContext(), 8), 0, 0);
		recyclerView.setClipToPadding(false);
		View.OnClickListener TimClickListener = new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent timIntent = new Intent(getContext(), ViewPitScout.class);
				timIntent.putExtra("position", recyclerView.getChildAdapterPosition(v));
				getActivity().startActivity(timIntent);
			}
		};
		View.OnLongClickListener SarahClickListener = new View.OnLongClickListener() {
			@Override
			public boolean onLongClick(View v) {
				final int position = recyclerView.getChildAdapterPosition(v);
				final PitRecord clickedPitScout = (PitRecord) Select.from(PitRecord.class).orderBy("CAST(team_number AS int)").list().get(position);
				Integer[] icons = new Integer[]{R.drawable.ic_edit_black, R.drawable.ic_clear_black};
				ListAdapter adapter = new IconArrayAdapter(PitScoutFragment.this.getContext(), getResources().getStringArray(R.array.pit_scout_options), icons);
				new AlertDialog.Builder(getContext())
						.setTitle("Team " + clickedPitScout.getTeamNumber())
						.setAdapter(adapter, new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int which) {
								switch (which) {
									case 0:
										Intent intent = new Intent(getContext(), PitScout.class);
										intent.putExtra("teamNumber", String.valueOf(clickedPitScout.getTeamNumber()));
										startActivity(intent);
										break;
									case 1:
										final PitRecord temporaryPitRecord = ((PitScoutTeamAdapter) recyclerView.getAdapter()).remove(position);
										Snackbar snackbar = Snackbar.make(getActivity().findViewById(R.id.content), "Team " + clickedPitScout.getTeamNumber() + " has been deleted", Snackbar.LENGTH_LONG)
												.setAction("Restore", new View.OnClickListener() {
													@Override
													public void onClick(View v) {
														((PitScoutTeamAdapter) recyclerView.getAdapter()).add(temporaryPitRecord);
													}
												})
												.setCallback(new Snackbar.Callback() {
													@Override
													public void onDismissed(Snackbar snackbar, int event) {
														if (event != Snackbar.Callback.DISMISS_EVENT_ACTION)
															temporaryPitRecord.delete();
													}
												});
										((TextView) snackbar.getView().findViewById(android.support.design.R.id.snackbar_text)).setTextColor(Color.WHITE);
										snackbar.show();
										break;
								}
							}
						})
						.show();
				return true;
			}
		};
		teamAdapter = new PitScoutTeamAdapter(getActivity(), TimClickListener, SarahClickListener, Select.from(PitRecord.class).orderBy("CAST(team_number AS int)").list());
		recyclerView.setAdapter(teamAdapter);
		recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
		return v;
	}

}
