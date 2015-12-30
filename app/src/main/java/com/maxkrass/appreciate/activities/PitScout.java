package com.maxkrass.appreciate.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.maxkrass.appreciate.R;
import com.maxkrass.appreciate.adapter.MainPagerAdapter;
import com.maxkrass.appreciate.objects.PitRecord;
import com.maxkrass.appreciate.views.CheckBoxWidget;
import com.orm.SugarRecord;

import java.io.File;
import java.io.IOException;

public class PitScout extends BaseActivity implements View.OnClickListener {
    LinearLayout abilitiesList;
    LinearLayout autoList;
    LinearLayout teleList;

    Spinner maxSpeed;
    EditText teamNumber;
    EditText teamName;
    EditText mainComment;
    EditText teleComment;
    EditText autoComment;
    EditText abilitiesComment;

    private ImageView robotImage;

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

    SharedPreferences settings;

    boolean edit;
    PitRecord editablePitRecord;

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
        wheelTypeSpinner = (Spinner) findViewById(R.id.wheelTypeSpinner);
        wheelNumSpinner = (Spinner) findViewById(R.id.wheelNumSpinner);
        cimNumSpinner = (Spinner) findViewById(R.id.cimNumSpinner);
        highestPossibleStackSpinner = (Spinner) findViewById(R.id.highestStackSpinner);
        maxSpeed = (Spinner) findViewById(R.id.maxSpeedSpinner);
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
        mainComment = (EditText) findViewById(R.id.main_comment);
        teleComment = (EditText) findViewById(R.id.tele_comment);
        autoComment = (EditText) findViewById(R.id.auto_comment);
        abilitiesComment = (EditText) findViewById(R.id.abilities_comment);
    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.pit_toolbar);
        setSupportActionBar(toolbar);
        robotImage = (ImageView) findViewById(R.id.pit_scout_image);
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
        if (edit = getIntent().hasExtra("teamNumber")) {
            editablePitRecord = SugarRecord.find(PitRecord.class, "team_number = ?", getIntent().getStringExtra("teamNumber")).get(0);
            ((CollapsingToolbarLayout) toolbar.getParent()).setTitle("Edit Team " + editablePitRecord.getTeamNumber());
            fillEditTexts();
            checkTeleCBWs();
            checkAutoCBWs();
            setSpinners();
            checkAbilitiesCBWs();

        }
    }

    private void checkTeleCBWs() {
        wideTeleCBW.setCheckBox(editablePitRecord.isWideTeleCBW());
        narrowTeleCBW.setCheckBox(editablePitRecord.isNarrowTeleCBW());
        stepTeleCBW.setCheckBox(editablePitRecord.isStepTeleCBW());
        landfillTeleCBW.setCheckBox(editablePitRecord.isLandfillTeleCBW());
        humanPlayerTeleCBW.setCheckBox(editablePitRecord.isHumanPlayerTeleCBW());
    }

    private void fillEditTexts() {
        teamNumber.setText(String.valueOf(editablePitRecord.getTeamNumber()));
        teamName.setText(editablePitRecord.getTeamName());
        mainComment.setText(editablePitRecord.getMainComment());
        teleComment.setText(editablePitRecord.getTeleComment());
        autoComment.setText(editablePitRecord.getAutoComment());
        abilitiesComment.setText(editablePitRecord.getAbilitiesComment());
    }

    private void checkAutoCBWs() {
        autoZoneAutoCBW.setCheckBox(editablePitRecord.isAutoZoneAutoCBW());
        totesAutoCBW.setCheckBox(editablePitRecord.isTotesAutoCBW());
        containersAutoCBW.setCheckBox(editablePitRecord.isContainersAutoCBW());
        flexibleAutoCBW.setCheckBox(editablePitRecord.isFlexibleAutoCBW());
    }

    private void setSpinners() {
        driveSpinner.setSelection(editablePitRecord.getDriveSpinner());
        wheelTypeSpinner.setSelection(editablePitRecord.getWheelTypeSpinner());
        wheelNumSpinner.setSelection(editablePitRecord.getWheelNumSpinner());
        cimNumSpinner.setSelection(editablePitRecord.getCimNumSpinner());
        highestPossibleStackSpinner.setSelection(editablePitRecord.getHighestPossibleStackSpinner());

    }

    private void checkAbilitiesCBWs() {
        totesAbilityCBW.setCheckBox(editablePitRecord.isTotesAbilityCBW());
        containersAbilityCBW.setCheckBox(editablePitRecord.isContainersAbilityCBW());
        noodlesAbilityCBW.setCheckBox(editablePitRecord.isNoodlesAbilityCBW());
        shiftingAbilityCBW.setCheckBox(editablePitRecord.isShiftingAbilityCBW());
        coopAbilityCBW.setCheckBox(editablePitRecord.isCoopAbilityCBW());
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
            } catch (IOException ignored) {
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

        if (!edit && SugarRecord.count(PitRecord.class, "team_Number=?", new String[]{teamNumber.getText().toString()}) > 0) {
            new AlertDialog.Builder(this)
                    .setTitle("Team number already exists")
                    .setMessage("Would you like to edit the existing Pit Scout?")
                    .setPositiveButton("Edit", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Intent intent = getIntent();
                            intent.putExtra("teamNumber", teamNumber.getText().toString());
                            finish();
                            startActivity(intent);
                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            teamNumber.setError("Team already exists");
                        }
                    }).show();
        } else {
            PitRecord record;

            if (edit) {
                record = editablePitRecord;
            } else {
                record = new PitRecord();
            }

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
            record.setTeamNumber(Integer.parseInt(teamNumber.getText().toString()));

            if (edit) {
                int from = MainPagerAdapter.pitScouts.teamAdapter.indexOf(record);
                record.save();
                MainPagerAdapter.pitScouts.teamAdapter.update(from, record);
            } else {
                record.save();
                MainPagerAdapter.pitScouts.teamAdapter.add(record);
            }
            finish();

        }
    }
}