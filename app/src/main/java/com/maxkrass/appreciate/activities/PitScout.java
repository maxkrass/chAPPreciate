package com.maxkrass.appreciate.activities;

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
import android.util.Xml;
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
import com.maxkrass.appreciate.Team;
import com.maxkrass.appreciate.adapter.MainPagerAdapter;
import com.maxkrass.appreciate.views.CheckBoxWidget;
import com.maxkrass.appreciate.R;

import org.xmlpull.v1.XmlSerializer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;

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
					savePitScoutAsXML();
					Toast.makeText(this, "PitScout " + MainActivity.singleton.getLastSavedTeam() + " saved successfully", Toast.LENGTH_LONG).show();
					MainPagerAdapter.pitScouts.teamAdapter.add(Team.getTeamFromFile(new File(scoutFolder, "Team " + MainActivity.singleton.getLastSavedTeam() + ".pit")));
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

	public void savePitScoutAsXML() {
		scoutFolder = new File(String.valueOf(Environment.getExternalStorageDirectory()) + "/" + settings.getString("folder_name", "FRCScouting") + "/data/Team " + teamNumber.getText().toString());
		if (!scoutFolder.exists() && !scoutFolder.mkdirs()) {
			Log.w("File directory", "Failed to create directory");
		}
		pitScoutFile = new File(scoutFolder, "Team " + teamNumber.getText().toString() + ".pit");
		ArrayList<String> data = new ArrayList<>();
		data.add(teamNumber.getText() + "");
		data.add(teamName.getText() + "");
		data.add(driveSpinner.getSelectedItem() + "");
		data.add(wheelTypeSpinner.getSelectedItem() + "");
		data.add(wheelNumSpinner.getSelectedItem() + "");
		data.add(cimNumSpinner.getSelectedItem() + "");
		data.add(maxSpeed.getSelectedItem() + "");
		data.add(mainComment.getText() + "");
		data.add(wideTeleCBW + "");
		data.add(narrowTeleCBW + "");
		data.add(stepTeleCBW + "");
		data.add(landfillTeleCBW + "");
		data.add(humanPlayerTeleCBW + "");
		data.add(highestPossibleStackSpinner.getSelectedItem() + "");
		data.add(teleComment.getText() + "");
		data.add(autoZoneAutoCBW + "");
		data.add(totesAutoCBW + "");
		data.add(containersAutoCBW + "");
		data.add(flexibleAutoCBW + "");
		data.add(autoComment.getText() + "");
		data.add(totesAbilityCBW + "");
		data.add(containersAbilityCBW + "");
		data.add(noodlesAbilityCBW + "");
		data.add(shiftingAbilityCBW + "");
		data.add(coopAbilityCBW + "");
		data.add(abilitiesComment.getText() + "");
		for (int i = 0; i < data.size(); i++) {
			if (data.get(i).equals(""))
				data.set(i, " ");
		}
		try {
			fOut = new FileOutputStream(pitScoutFile);
			xml = Xml.newSerializer();
			StringWriter writer = new StringWriter();
			xml.setOutput(writer);
			xml.startDocument("UTF-8", true);
			text("");
			xml.startTag(null, "PitScout");
			xml.attribute(null, "localFile", "true");
			text("");
			start("main");
			start("teamNumber");
			text(data.get(0));
			end("teamNumber");
			start("teamName");
			text(data.get(1));
			end("teamName");
			start("driveType");
			text(data.get(2));
			end("driveType");
			start("wheelType");
			text(data.get(3));
			end("wheelType");
			start("wheelNumber");
			text(data.get(4));
			end("wheelNumber");
			start("cimNumber");
			text(data.get(5));
			end("cimNumber");
			start("maxSpeed");
			text(data.get(6));
			end("maxSpeed");
			start("mainComment");
			text(data.get(7));
			end("mainComment");
			end("main");
			start("totes");
			start("wideSideTote");
			text(data.get(8));
			end("wideSideTote");
			start("narrowSideTote");
			text(data.get(9));
			end("narrowSideTote");
			start("stepPickUp");
			text(data.get(10));
			end("stepPickUp");
			start("landfillPickUp");
			text(data.get(11));
			end("landfillPickUp");
			start("toteChutePickUp");
			text(data.get(12));
			end("toteChutePickUp");
			start("highestPossibleStack");
			text(data.get(13));
			end("highestPossibleStack");
			start("teleComment");
			text(data.get(14));
			end("teleComment");
			end("totes");
			start("auto");
			start("autoZone");
			text(data.get(15));
			end("autoZone");
			start("totes");
			text(data.get(16));
			end("totes");
			start("containers");
			text(data.get(17));
			end("containers");
			start("flexible");
			text(data.get(18));
			end("flexible");
			start("autoComment");
			text(data.get(19));
			end("autoComment");
			end("auto");
			start("abilities");
			start("canStackTotes");
			text(data.get(20));
			end("canStackTotes");
			start("canLiftCans");
			text(data.get(21));
			end("canLiftCans");
			start("canScoreNoodles");
			text(data.get(22));
			end("canScoreNoodles");
			start("canShift");
			text(data.get(23));
			end("canShift");
			start("canCoopStack");
			text(data.get(24));
			end("canCoopStack");
			start("abilitiesComment");
			text(data.get(25));
			end("abilitiesComment");
			end("abilities");
			end("PitScout");
			xml.endDocument();
			xml.flush();
			String dataWrite = writer.toString();
			/*String sendString = "";
			for (String s : data) {
				sendString += "|" + s;
			}
			sendString = sendString.substring(1);*/
			fOut.write(dataWrite.getBytes());
			fOut.close();
			//MainActivity.singleton.sendPit(pitScoutFile);
			/*if (imageBitmap != null) {
				File imageDir = new File(Environment.getExternalStorageDirectory() + "/" + settings.getString("folder_name", "FRCScouting") + "/local/PitScouts/images");
				if (!imageDir.exists())
					imageDir.mkdirs();
				File imageFile = new File(imageDir, teamNumber.getText() + "_Robot_Image.jpg");
				File tempImageFile = new File(mCurrentPath);
				tempImageFile.renameTo(imageFile);
			}*/
		} catch (IOException e) {
			e.printStackTrace();
		}
		MainActivity.singleton.setLastSavedTeam(teamNumber.getText().toString());
		finish();
	}

	private void start(String tagName) throws IOException {
		xml.text("\t");
		xml.startTag(null, tagName);
		xml.text("\n");
		for (int i = 1; i < xml.getDepth(); i++) {
			xml.text("\t");
		}
	}

	private void end(String tagName) throws IOException {
		xml.endTag(null, tagName);
		xml.text("\n");
		for (int i = 1; i < xml.getDepth(); i++) {
			xml.text("\t");
		}
	}

	private void text(String text) throws IOException {
		xml.text("\t" + text + "\n");
		for (int i = 1; i < xml.getDepth(); i++) {
			xml.text("\t");
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
