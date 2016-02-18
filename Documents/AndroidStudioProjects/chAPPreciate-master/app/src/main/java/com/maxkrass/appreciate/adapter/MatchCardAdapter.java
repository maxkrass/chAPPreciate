package com.maxkrass.appreciate.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.maxkrass.appreciate.R;
import com.maxkrass.appreciate.objects.MatchRecord;

import java.util.Collections;
import java.util.List;

/**
 * Max made this for APPreciate on 11.01.2016.
 */
public class MatchCardAdapter extends RecyclerView.Adapter<MatchCardAdapter.MatchCardViewHolder> {

	LayoutInflater inflater;
	List<MatchRecord> matchRecords = Collections.emptyList();

	public MatchCardAdapter(Context context, List<MatchRecord> matchRecords) {
		inflater = LayoutInflater.from(context);
		this.matchRecords = matchRecords;
	}

	@Override
	public MatchCardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		return new MatchCardViewHolder(inflater.inflate(R.layout.match_card, parent, false));
	}

	@Override
	public void onBindViewHolder(MatchCardViewHolder holder, int position) {
		MatchRecord matchRecord = matchRecords.get(position);
		holder.matchNumber.setText("Match " + String.valueOf(matchRecord.getMatchNumber()));
		holder.pointsAutoLabel.setText(String.valueOf(matchRecord.getAutoPoints()));
		holder.commentAutoLabel.setText(matchRecord.getAutoComment());
		holder.pointsTeleLabel.setText(String.valueOf(matchRecord.getTotalPoints()));
		holder.commentTeleLabel.setText(matchRecord.getTeleComment());
	}

	@Override
	public int getItemCount() {
		return matchRecords.size();
	}

	class MatchCardViewHolder extends RecyclerView.ViewHolder {

		TextView matchNumber;
		TextView pointsAutoLabel;
		TextView commentAutoLabel;
		TextView pointsTeleLabel;
		TextView commentTeleLabel;

		public MatchCardViewHolder(View itemView) {
			super(itemView);
			matchNumber = (TextView) itemView.findViewById(R.id.match_number);
			pointsAutoLabel = (TextView) itemView.findViewById(R.id.points_auto_label);
			commentAutoLabel = (TextView) itemView.findViewById(R.id.comment_auto_label);
			pointsTeleLabel = (TextView) itemView.findViewById(R.id.points_total_label);
			commentTeleLabel = (TextView) itemView.findViewById(R.id.comments_tele_label);
		}
	}

}
