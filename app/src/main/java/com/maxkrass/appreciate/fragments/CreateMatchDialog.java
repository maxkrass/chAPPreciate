package com.maxkrass.appreciate.fragments;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class CreateMatchDialog extends DialogFragment {

	View v;
	EditText matchNumber;
	EditText firstTeamNumber;
	EditText secondTeamNumber;
	EditText thirdTeamNumber;

	//TODO merge simple AlertDialog implementation to DialogFragment classes
	//consult docs on how to do this

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		// Use the Builder class for convenient dialog construction
		// Create the AlertDialog object and return it
		return null;
	}

}
