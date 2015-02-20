package com.maxkrass.appreciate.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.maxkrass.appreciate.activities.MatchScout;
import com.maxkrass.appreciate.R;

public class CreateMatchDialog extends DialogFragment {

	View v;

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		// Use the Builder class for convenient dialog construction
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		LayoutInflater inflater = getActivity().getLayoutInflater();
		v = inflater.inflate(R.layout.dialog_layout, null);
		builder.setView(v)
				.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						Intent matchScout = new Intent(getActivity(), MatchScout.class);
						matchScout.putExtra("team1", ((EditText) v.findViewById(R.id.first_team)).getText() + "");
						matchScout.putExtra("team2", ((EditText) v.findViewById(R.id.second_team)).getText() + "");
						matchScout.putExtra("team3", ((EditText) v.findViewById(R.id.third_team)).getText() + "");
						startActivity(matchScout);
					}
				})
				.setNegativeButton(android.R.string.cancel, null)
				.setMessage("Define teams");
		// Create the AlertDialog object and return it
		return builder.create();
	}

}
