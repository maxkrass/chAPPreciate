package com.maxkrass.appreciate.activities;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.Toast;

import com.manuelpeinado.fadingactionbar.view.ObservableScrollable;
import com.manuelpeinado.fadingactionbar.view.OnScrollChangedCallback;
import com.maxkrass.appreciate.R;
import com.maxkrass.appreciate.adapter.MainPagerAdapter;
import com.maxkrass.appreciate.objects.PitRecord;
import com.maxkrass.appreciate.views.CheckBoxWidget;
import com.orm.SugarRecord;

import org.xmlpull.v1.XmlSerializer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class PitScout extends ActionBarActivity implements View.OnClickListener, OnScrollChangedCallback {
	LinearLayout abilitiesList;
	LinearLayout autoList;
	LinearLayout teleList;

	File pitScoutFile;
	File scoutFolder;
	FileOutputStream fOut;

	Spinner maxSpeed;
	EditText teamNumber;
	EditText teamName;
	EditText mainComment;
	EditText teleComment;
	EditText autoComment;
	EditText abilitiesComment;

	XmlSerializer xml;

	private Drawable mActionBarBackgroundDrawable;
	private ImageView robotImage;
	private int mLastDampedScroll;

	private CheckBoxWidget wideTeleCBW;
	private CheckBoxWidget autoZoneAutoCBW;
	private CheckBoxWidget flexibleAutoCBW;
	private CheckBoxWidget containersAbilityCBW;
	private CheckBoxWidget containersAutoCBW;
	private CheckBoxWidget coopAbilityCBW;
	private CheckBoxWidget humanPlayerTeleCBW;
	private CheckBoxWidget landfillTeleCBW;
	private CheckBoxWidget noodlesAbilityCBW;
	private CheckBoxWidget narrowTeleCBW;
	private CheckBoxWidget shiftingAbilityCBW;
	private CheckBoxWidget stepTeleCBW;
	private CheckBoxWidget totesAbilityCBW;
	private CheckBoxWidget totesAutoCBW;

	Toolbar toolbar;
	ScrollView scrollView;

	SharedPreferences settings;

	Spinner cimNumSpinner;
	Spinner driveSpinner;
	Spinner highestPossibleStackSpinner;
	Spinner wheelNumSpinner;
	Spinner wheelTypeSpinner;

	public void onClick(View view) {
		if (view instanceof CheckBoxWidget) {
			CheckBoxWidget checkboxwidget = (CheckBoxWidget) view;
			checkboxwidget.setCheckBox(!checkboxwidget.isChecked());
		}
	}

	private void initAbilitiesCBWs() {
		abilitiesList = (LinearLayout) findViewById(R.id.abilities_list);
		containersAbilityCBW = new CheckBoxWidget(this);
		containersAbilityCBW.setTitleView(getString(R.string.containers_abilities_label));
		containersAbilityCBW.setOnClickListener(this);
		totesAbilityCBW = new CheckBoxWidget(this);
		totesAbilityCBW.setTitleView(getString(R.string.totes_abilities_label));
		totesAbilityCBW.setOnClickListener(this);
		noodlesAbilityCBW = new CheckBoxWidget(this);
		noodlesAbilityCBW.setTitleView(getString(R.string.noodles_abilities_label));
		noodlesAbilityCBW.setOnClickListener(this);
		shiftingAbilityCBW = new CheckBoxWidget(this);
		shiftingAbilityCBW.setTitleView(getString(R.string.shifting_abilities_label));
		shiftingAbilityCBW.setOnClickListener(this);
		coopAbilityCBW = new CheckBoxWidget(this);
		coopAbilityCBW.setTitleView(getString(R.string.coop_abilities_label));
		coopAbilityCBW.setOnClickListener(this);
		abilitiesList.addView(containersAbilityCBW);
		abilitiesList.addView(totesAbilityCBW);
		abilitiesList.addView(noodlesAbilityCBW);
		abilitiesList.addView(shiftingAbilityCBW);
		abilitiesList.addView(coopAbilityCBW);
	}

	private void initAutoCBWs() {
		autoList = (LinearLayout) findViewById(R.id.auto_list);
		autoZoneAutoCBW = new CheckBoxWidget(this);
		autoZoneAutoCBW.setTitleView(getString(R.string.auto_zone_auto_label));
		autoZoneAutoCBW.setOnClickListener(this);
		autoList.addView(autoZoneAutoCBW);
		totesAutoCBW = new CheckBoxWidget(this);
		totesAutoCBW.setTitleView(getString(R.string.totes_auto_label));
		totesAutoCBW.setOnClickListener(this);
		autoList.addView(totesAutoCBW);
		containersAutoCBW = new CheckBoxWidget(this);
		containersAutoCBW.setTitleView(getString(R.string.containers_auto_label));
		containersAutoCBW.setOnClickListener(this);
		autoList.addView(containersAutoCBW);
		flexibleAutoCBW = new CheckBoxWidget(this);
		flexibleAutoCBW.setTitleView(getString(R.string.flexible_auto_label));
		flexibleAutoCBW.setOnClickListener(this);
		autoList.addView(flexibleAutoCBW);
	}

	private void initSpinners() {
		driveSpinner = (Spinner) findViewById(R.id.driveTypeSpinner);
		ArrayAdapter arrayadapter = ArrayAdapter.createFromResource(this, R.array.drive_type, android.R.layout.simple_spinner_item);
		arrayadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		driveSpinner.setAdapter(arrayadapter);
		wheelTypeSpinner = (Spinner) findViewById(R.id.wheelTypeSpinner);
		ArrayAdapter arrayAdapter1 = ArrayAdapter.createFromResource(this, R.array.wheel_type, android.R.layout.simple_spinner_item);
		arrayAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		wheelTypeSpinner.setAdapter(arrayAdapter1);
		wheelNumSpinner = (Spinner) findViewById(R.id.wheelNumSpinner);
		ArrayAdapter arrayAdapter2 = ArrayAdapter.createFromResource(this, R.array.num_wheels, android.R.layout.simple_spinner_item);
		arrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		wheelNumSpinner.setAdapter(arrayAdapter2);
		cimNumSpinner = (Spinner) findViewById(R.id.cimNumSpinner);
		ArrayAdapter arrayAdapter3 = ArrayAdapter.createFromResource(this, R.array.num_cims, android.R.layout.simple_spinner_item);
		arrayAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		cimNumSpinner.setAdapter(arrayAdapter3);
		highestPossibleStackSpinner = (Spinner) findViewById(R.id.highestStackSpinner);
		ArrayAdapter arrayAdapter4 = ArrayAdapter.createFromResource(this, R.array.highest_possible_stack, android.R.layout.simple_spinner_item);
		arrayAdapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		highestPossibleStackSpinner.setAdapter(arrayAdapter4);
	}

	private void initTeleCBWs() {
		teleList = (LinearLayout) findViewById(R.id.tele_list);
		wideTeleCBW = new CheckBoxWidget(this);
		wideTeleCBW.setTitleView(getString(R.string.wide_tele_label));
		wideTeleCBW.setOnClickListener(this);
		teleList.addView(wideTeleCBW);
		narrowTeleCBW = new CheckBoxWidget(this);
		narrowTeleCBW.setTitleView(getString(R.string.narrow_tele_label));
		narrowTeleCBW.setOnClickListener(this);
		teleList.addView(narrowTeleCBW);
		stepTeleCBW = new CheckBoxWidget(this);
		stepTeleCBW.setTitleView(getString(R.string.step_tele_label));
		stepTeleCBW.setOnClickListener(this);
		teleList.addView(stepTeleCBW);
		landfillTeleCBW = new CheckBoxWidget(this);
		landfillTeleCBW.setTitleView(getString(R.string.landfill_tele_label));
		landfillTeleCBW.setOnClickListener(this);
		teleList.addView(landfillTeleCBW);
		humanPlayerTeleCBW = new CheckBoxWidget(this);
		humanPlayerTeleCBW.setTitleView(getString(R.string.tote_chute_tele_label));
		humanPlayerTeleCBW.setOnClickListener(this);
		teleList.addView(humanPlayerTeleCBW);
	}

	private void initEditTexts() {
		teamNumber = (EditText) findViewById(R.id.teamNumberEditText);
		teamName = (EditText) findViewById(R.id.team_name_edit_text);
		maxSpeed = (Spinner) findViewById(R.id.maxSpeedSpinner);
		mainComment = (EditText) findViewById(R.id.main_comment);
		teleComment = (EditText) findViewById(R.id.tele_comment);
		autoComment = (EditText) findViewById(R.id.auto_comment);
		abilitiesComment = (EditText) findViewById(R.id.abilities_comment);
	}

	private void initToolbar() {
		toolbar = (Toolbar) findViewById(R.id.pit_toolbar);
		setSupportActionBar(toolbar);
		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setHomeAsUpIndicator(R.drawable.ic_close);
		mActionBarBackgroundDrawable = toolbar.getBackground();
		robotImage = (ImageView) findViewById(R.id.pit_scout_image);
		ObservableScrollable scrollView = (ObservableScrollable) findViewById(R.id.scroll_view);
		scrollView.setOnScrollChangedCallback(this);
		onScroll(-1, 0);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.save_menu, menu);
		return true;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pit_save_dialog);
		settings = PreferenceManager.getDefaultSharedPreferences(this);
		initToolbar();
		initEditTexts();
		initSpinners();
		initAutoCBWs();
		initAbilitiesCBWs();
		initTeleCBWs();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.save_action:
				if (teamNumber.getText().toString().equals("")) {
					teamNumber.setError("A Team Number is required");
				} else {
					savePitScoutToDatabase();
				}
				break;
			case R.id.clear_action:
				clearFields();
				break;
			case R.id.pic_action:
				dispatchTakePictureIntent();
				break;
			case android.R.id.home:
				finish();
				break;
			case R.id.settings:
				startActivity(new Intent(this, SettingsActivity.class));
				break;
			default:
				return super.onOptionsItemSelected(item);
		}
		return true;
	}

	private void clearFields() {
		autoZoneAutoCBW.setCheckBox(false);
		totesAutoCBW.setCheckBox(false);
		containersAutoCBW.setCheckBox(false);
		flexibleAutoCBW.setCheckBox(false);
		wideTeleCBW.setCheckBox(false);
		narrowTeleCBW.setCheckBox(false);
		stepTeleCBW.setCheckBox(false);
		landfillTeleCBW.setCheckBox(false);
		humanPlayerTeleCBW.setCheckBox(false);
		totesAbilityCBW.setCheckBox(false);
		containersAbilityCBW.setCheckBox(false);
		noodlesAbilityCBW.setCheckBox(false);
		shiftingAbilityCBW.setCheckBox(false);
		coopAbilityCBW.setCheckBox(false);
		driveSpinner.setSelection(0);
		wheelTypeSpinner.setSelection(0);
		wheelNumSpinner.setSelection(0);
		cimNumSpinner.setSelection(0);
		highestPossibleStackSpinner.setSelection(0);
		maxSpeed.setSelection(0);
		teamNumber.setText("");
		//scrollView.scrollTo(0, 0);
	}

	static final int REQUEST_IMAGE_CAPTURE = 1;

	private void dispatchTakePictureIntent() {
		Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
			// Create the File where the photo should go
			File photoFile = null;
			try {
				photoFile = createTempImage();
			} catch (IOException ex) {
			}
			// Continue only if the File was successfully created
			if (photoFile != null) {
				takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));
				startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
			}
		}
	}

	String mCurrentPath;

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
			BitmapFactory.Options bounds = new BitmapFactory.Options();
			bounds.inJustDecodeBounds = true;
			BitmapFactory.decodeFile(mCurrentPath, bounds);
			int originalSize = (bounds.outHeight > bounds.outWidth) ? bounds.outHeight
					: bounds.outWidth;
			BitmapFactory.Options opts = new BitmapFactory.Options();
			opts.inSampleSize = originalSize / 500;
			robotImage.setImageBitmap(BitmapFactory.decodeFile(mCurrentPath, opts));
		}
	}

	private File createTempImage() throws IOException {
		File sdDir = Environment.getExternalStorageDirectory();
		File sdTempDir = new File(sdDir, "/.temp/");
		if (!sdTempDir.exists())
			sdTempDir.mkdir();
		File image = File.createTempFile("Pit_Robot_Image", ".jpg", sdTempDir);
		mCurrentPath = image.getAbsolutePath();
		return image;
	}

	public void savePitScoutToDatabase() {

		List<PitRecord> listTest = SugarRecord.find(PitRecord.class, "team_Number=?", teamNumber.getText().toString());
		if (listTest.size() > 0) {
			System.out.println("it works!");
			Log.e("Tim1", String.valueOf(listTest.size()));

			// 1. Instantiate an AlertDialog.Builder with its constructor
			AlertDialog.Builder builder = new AlertDialog.Builder(this);

			builder.setMessage("Team number already entered")
					.setPositiveButton("OK", new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int id) {

						}
					});

			AlertDialog dialog = builder.create();
			dialog.show();
		} else {
			System.out.print(listTest.size());
			Log.e("Tim2", String.valueOf(listTest.size()));
			PitRecord record = new PitRecord();

			record.setAbilitiesComment(abilitiesComment.getText().toString());
			record.setAutoComment(autoComment.getText().toString());
			record.setWheelNumSpinner(wheelNumSpinner.getSelectedItemPosition());
			record.setMainComment(mainComment.getText().toString());
			record.setTeleComment(teleComment.getText().toString());


			record.setCoopAbilityCBW(coopAbilityCBW.isChecked());
			record.setAutoZoneAutoCBW(autoZoneAutoCBW.isChecked());
			record.setContainersAbilityCBW(containersAbilityCBW.isChecked());
			record.setNarrowTeleCBW(narrowTeleCBW.isChecked());
			record.setNoodlesAbilityCBW(noodlesAbilityCBW.isChecked());
			record.setFlexibleAutoCBW(flexibleAutoCBW.isChecked());
			record.setWideTeleCBW(wideTeleCBW.isChecked());
			record.setHumanPlayerTeleCBW(humanPlayerTeleCBW.isChecked());
			record.setLandfillTeleCBW(landfillTeleCBW.isChecked());
			record.setContainersAutoCBW(containersAutoCBW.isChecked());
			record.setShiftingAbilityCBW(shiftingAbilityCBW.isChecked());
			record.setStepTeleCBW(stepTeleCBW.isChecked());
			record.setTotesAbilityCBW(totesAbilityCBW.isChecked());
			record.setTotesAutoCBW(totesAutoCBW.isChecked());

			record.setCimNumSpinner(cimNumSpinner.getSelectedItemPosition());
			record.setDriveSpinner(driveSpinner.getSelectedItemPosition());
			record.setHighestPossibleStackSpinner(highestPossibleStackSpinner.getSelectedItemPosition());
			record.setWheelTypeSpinner(wheelTypeSpinner.getSelectedItemPosition());
			record.setWheelNumSpinner(wheelNumSpinner.getSelectedItemPosition());
			record.setMaxSpeed(maxSpeed.getSelectedItemPosition());

			record.setTeamName(teamName.getText().toString());
			record.setTeamNumber(teamNumber.getText().toString());
			record.save();
			finish();

			Toast.makeText(this, "PitScout " + MainActivity.singleton.getLastSavedTeam() + " saved successfully", Toast.LENGTH_LONG).show();
			MainPagerAdapter.pitScouts.teamAdapter.add(record);

			Log.e("Tim", "Saved 1");
			Log.e("Tim", record.toString());


		}


	}


	@Override
	public void onScroll(int i, int i2) {
		int headerHeight = robotImage.getHeight() - toolbar.getHeight();
		float ratio = 0;
		if (i2 > 0 && headerHeight > 0)
			ratio = (float) Math.min(Math.max(i2, 0), headerHeight) / headerHeight;

		updateActionBarTransparency(ratio);
		updateParallaxEffect(i2);
	}

	private void updateActionBarTransparency(float scrollRatio) {
		int newAlpha = (int) (scrollRatio * 255);
		mActionBarBackgroundDrawable.setAlpha(newAlpha);
		toolbar.setBackground(mActionBarBackgroundDrawable);
	}

	private void updateParallaxEffect(int scrollPosition) {
		float damping = 0.5f;
		int dampedScroll = (int) (scrollPosition * damping);
		int offset = mLastDampedScroll - dampedScroll;
		robotImage.offsetTopAndBottom(-offset);

		mLastDampedScroll = dampedScroll;
	}
}

class FireMissilesDialogFragment extends DialogFragment {
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		// Use the Builder class for convenient dialog construction
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setMessage("Team number already entered")
				.setPositiveButton("ok", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						// FIRE ZE MISSILES!
					}
				})
				.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						// User cancelled the dialog
					}
				});
		// Create the AlertDialog object and return it
		return builder.create();
	}
}