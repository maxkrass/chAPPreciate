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
		// Create the AlertDialog object and return it
		return null;
	}

}
