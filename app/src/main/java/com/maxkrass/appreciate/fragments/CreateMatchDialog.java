package com.maxkrass.appreciate.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.maxkrass.appreciate.activities.MatchScout;
import com.maxkrass.appreciate.R;

public class CreateMatchDialog extends DialogFragment {

	View v;
	EditText matchNumber;
	EditText firstTeamNumber;
	EditText secondTeamNumber;
	EditText thirdTeamNumber;

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		// Use the Builder class for convenient dialog construction
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		LayoutInflater inflater = getActivity().getLayoutInflater();
		v = inflater.inflate(R.layout.dialog_layout, null);
		matchNumber = (EditText) v.findViewById(R.id.match_number);
		firstTeamNumber = (EditText) v.findViewById(R.id.first_team);
		secondTeamNumber = (EditText) v.findViewById(R.id.second_team);
		thirdTeamNumber = (EditText) v.findViewById(R.id.third_team);
		builder.setView(v)
				.setPositiveButton(android.R.string.ok, null)
				.setNegativeButton(android.R.string.cancel, null)
				.setMessage("Define teams");
		final AlertDialog dialog = builder.create();
		dialog.show();
		final Button theButton = dialog.getButton(DialogInterface.BUTTON_POSITIVE);
		theButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				boolean error = false;
				if (matchNumber.getText().toString().equals("")) {
					matchNumber.setError("A match number is required");
					error = true;
				}
				if (firstTeamNumber.getText().toString().equals("")) {
					firstTeamNumber.setError("Team number one is required");
					error = true;
				}
				if (secondTeamNumber.getText().toString().equals("")) {
					secondTeamNumber.setError("Team number two is required");
					error = true;
				} else if (firstTeamNumber.getText().toString().equals(secondTeamNumber.getText().toString())) {
					secondTeamNumber.setError("Team numbers must not be equal");
					error = true;
				}
				if (thirdTeamNumber.getText().toString().equals("")) {
					thirdTeamNumber.setError("Team number three is required");
					error = true;
				} else if (secondTeamNumber.getText().toString().equals(thirdTeamNumber.getText().toString()) || firstTeamNumber.getText().toString().equals(thirdTeamNumber.getText().toString())) {
					thirdTeamNumber.setError("Team numbers must no be equal");
					error = true;
				}
				if (!error) {
					Intent matchScout = new Intent(getActivity(), MatchScout.class);
					matchScout.putExtra("matchNumber", matchNumber.getText() + "");
					matchScout.putExtra("team1", firstTeamNumber.getText() + "");
					matchScout.putExtra("team2", secondTeamNumber.getText() + "");
					matchScout.putExtra("team3", thirdTeamNumber.getText() + "");
					startActivity(matchScout);
					dialog.dismiss();
				}
			}
		});
		// Create the AlertDialog object and return it
		return dialog;
	}

}
