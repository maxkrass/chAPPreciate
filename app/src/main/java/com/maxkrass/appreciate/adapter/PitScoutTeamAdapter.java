package com.maxkrass.appreciate.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.maxkrass.appreciate.R;
import com.maxkrass.appreciate.objects.PitRecord;
import com.orm.query.Select;

import java.util.Collections;
import java.util.List;

public class PitScoutTeamAdapter extends RecyclerView.Adapter<PitScoutTeamAdapter.TeamViewHolder> {

	LayoutInflater inflater;
	List<PitRecord> teamList = Collections.emptyList();
	View.OnClickListener onClickListener;
	View.OnLongClickListener onLongClickListener;

	public PitScoutTeamAdapter(Context context, View.OnClickListener onClickListener, View.OnLongClickListener onLongClickListener, List<PitRecord> teamList) {
		inflater = LayoutInflater.from(context);
		this.teamList = teamList;
		this.onClickListener = onClickListener;
		this.onLongClickListener = onLongClickListener;
	}

	@Override
	public TeamViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = inflater.inflate(R.layout.team_row, parent, false);
		return new TeamViewHolder(view);
	}

	@Override
	public void onBindViewHolder(TeamViewHolder holder, int position) {
		holder.textView.setText("Team " + teamList.get(position).getTeamNumber() + (teamList.get(position).getTeamName().equals("") ? "" : ": " + teamList.get(position).getTeamName()));
	}

	public void add(PitRecord t) {
		teamList.add(t);
		Collections.sort(teamList);
		notifyItemInserted(teamList.indexOf(t));
	}

	public void update(int from, PitRecord pitRecord) {
		teamList = Select.from(PitRecord.class).orderBy("CAST(team_number AS int)").list();
		int to = teamList.indexOf(pitRecord);
		notifyItemMoved(from, to);
		notifyItemChanged(to);
	}

	public int indexOf(PitRecord pitRecord) {
		return teamList.indexOf(pitRecord);
	}

	public PitRecord remove(int position) {
		PitRecord pitRecord = teamList.remove(position);
		notifyItemRemoved(position);
		return pitRecord;
	}

	@Override
	public int getItemCount() {
		return teamList.size();
	}

	class TeamViewHolder extends RecyclerView.ViewHolder {

		TextView textView;

		public TeamViewHolder(View itemView) {
			super(itemView);
			itemView.setOnClickListener(onClickListener);
			itemView.setOnLongClickListener(onLongClickListener);
			textView = (TextView) itemView.findViewById(R.id.list_team_number);
		}


	}
}
