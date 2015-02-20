package com.maxkrass.appreciate.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.Xml;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.maxkrass.appreciate.R;
import com.maxkrass.appreciate.views.CheckBoxWidget;
import com.maxkrass.appreciate.views.SlidingTabLayout;
import com.maxkrass.appreciate.adapter.AlliancePagerAdapter;

import org.xmlpull.v1.XmlSerializer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;

public class MatchScout extends ActionBarActivity implements View.OnClickListener {

	ViewPager viewPager;
	AlliancePagerAdapter alliance;
	SlidingTabLayout tabLayout;
	SharedPreferences settings;

	File matchScoutFile;
	FileOutputStream fOut;
	XmlSerializer xml;

	int i0 = 0, i1 = 0, i2 = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.match_save_dialog);
		Toolbar toolbar = (Toolbar) findViewById(R.id.match_toolbar);
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
		settings = PreferenceManager.getDefaultSharedPreferences(MainActivity.singleton);
		Intent intent = getIntent();
		alliance = new AlliancePagerAdapter(getSupportFragmentManager(), intent.getStringExtra("team1"), intent.getStringExtra("team2"), intent.getStringExtra("team3"));
		viewPager = (ViewPager) findViewById(R.id.viewPager);
		viewPager.setOffscreenPageLimit(3);
		viewPager.setAdapter(alliance);
		tabLayout = (SlidingTabLayout) findViewById(R.id.tabStrip);
		tabLayout.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
			@Override
			public int getIndicatorColor(int position) {
				return 0xfff44336;
			}
		});
		tabLayout.setViewPager(viewPager);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.save_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case android.R.id.home:
				finish();
				break;
			case R.id.save_action:
				saveMatchTeam();
				break;
			case R.id.clear_action:
				//AlliancePagerAdapter.team1.clearFields();
				//AlliancePagerAdapter.team2.clearFields();
				//AlliancePagerAdapter.team3.clearFields();
				break;
			case R.id.settings:
				startActivity(new Intent(this, SettingsActivity.class));
				break;
			default:
				return false;
		}
		return true;
	}

	public void addStack(View v) {
		View stackView = getLayoutInflater().inflate(R.layout.stack, null);
		LinearLayout stack = (LinearLayout) stackView.findViewById(R.id.stack);
		TextView stackLabel = ((TextView) stack.findViewById(R.id.stack_label));
		CheckBoxWidget noodlesOnStack = new CheckBoxWidget(this);
		noodlesOnStack.setTitleView("Noodles");
		noodlesOnStack.setOnClickListener(this);
		noodlesOnStack.setTag("noodle_cbw");
		((FrameLayout) stack.findViewById(R.id.noodle_box)).addView(noodlesOnStack);
		switch (viewPager.getCurrentItem()) {
			case 0:
				stack.setTag("stack_" + i0);
				stackLabel.setText("" + stackLabel.getText() + (i0 + 1));
				AlliancePagerAdapter.team1.teleMatchList.addView(stackView);
				i0++;
				break;
			case 1:
				stack.setTag("stack_" + i1);
				stackLabel.setText("" + stackLabel.getText() + (i1 + 1));
				AlliancePagerAdapter.team2.teleMatchList.addView(stackView);
				i1++;
				break;
			case 2:
				stack.setTag("stack_" + i2);
				stackLabel.setText("" + stackLabel.getText() + (i2 + 1));
				AlliancePagerAdapter.team3.teleMatchList.addView(stackView);
				i2++;
				break;
		}

	}

	private void saveMatchTeam() {
		ArrayList<String> data1 = new ArrayList<>();
		ArrayList<String> data2 = new ArrayList<>();
		ArrayList<String> data3 = new ArrayList<>();
		data1.add(AlliancePagerAdapter.team1.teamName.getText() + "");
		data1.add(AlliancePagerAdapter.team1.autoZoneCBW + "");
		data1.add(AlliancePagerAdapter.team1.stackedTotesCBW + "");
		data1.add(AlliancePagerAdapter.team1.workedCBW + "");
		data1.add(AlliancePagerAdapter.team1.functionalCBW + "");
		data1.add(AlliancePagerAdapter.team1.coopertitionCBW + "");
		data2.add(AlliancePagerAdapter.team2.teamName.getText() + "");
		data2.add(AlliancePagerAdapter.team2.autoZoneCBW + "");
		data2.add(AlliancePagerAdapter.team2.stackedTotesCBW + "");
		data2.add(AlliancePagerAdapter.team2.workedCBW + "");
		data2.add(AlliancePagerAdapter.team2.functionalCBW + "");
		data2.add(AlliancePagerAdapter.team2.coopertitionCBW + "");
		data3.add(AlliancePagerAdapter.team3.teamName.getText() + "");
		data3.add(AlliancePagerAdapter.team3.autoZoneCBW + "");
		data3.add(AlliancePagerAdapter.team3.stackedTotesCBW + "");
		data3.add(AlliancePagerAdapter.team3.workedCBW + "");
		data3.add(AlliancePagerAdapter.team3.functionalCBW + "");
		data3.add(AlliancePagerAdapter.team3.coopertitionCBW + "");
		for (int i = 0; i < data1.size(); i++) {
			if (data1.get(i).equals(""))
				data1.set(i, " ");
		}
		for (int i = 0; i < data2.size(); i++) {
			if (data2.get(i).equals(""))
				data2.set(i, " ");
		}
		for (int i = 0; i < data3.size(); i++) {
			if (data3.get(i).equals(""))
				data3.set(i, " ");
		}
		ArrayList<ArrayList<String>> stacks1 = new ArrayList<>();
		ArrayList<ArrayList<String>> stacks2 = new ArrayList<>();
		ArrayList<ArrayList<String>> stacks3 = new ArrayList<>();
		for (int i = 0; i < i0; i++) {
			ArrayList<String> stackData = new ArrayList<>(3);
			LinearLayout theStack = ((LinearLayout) AlliancePagerAdapter.team1.getView().findViewWithTag("stack_" + i));
			stackData.add(((Spinner) theStack.findViewById(R.id.totes_spinner)).getSelectedItem() + "");
			stackData.add(((Spinner) theStack.findViewById(R.id.containers_spinner)).getSelectedItem() + "");
			stackData.add((theStack.findViewWithTag("noodle_cbw")) + "");
			stacks1.add(stackData);
		}
		for (int i = 0; i < i1; i++) {
			ArrayList<String> stackData = new ArrayList<>(3);
			LinearLayout theStack = ((LinearLayout) AlliancePagerAdapter.team2.getView().findViewWithTag("stack_" + i));
			stackData.add(((Spinner) theStack.findViewById(R.id.totes_spinner)).getSelectedItem() + "");
			stackData.add(((Spinner) theStack.findViewById(R.id.containers_spinner)).getSelectedItem() + "");
			stackData.add((theStack.findViewWithTag("noodle_cbw")) + "");
			stacks2.add(stackData);
		}
		for (int i = 0; i < i2; i++) {
			ArrayList<String> stackData = new ArrayList<>(3);
			LinearLayout theStack = ((LinearLayout) AlliancePagerAdapter.team3.getView().findViewWithTag("stack_" + i));
			stackData.add(((Spinner) theStack.findViewById(R.id.totes_spinner)).getSelectedItem() + "");
			stackData.add(((Spinner) theStack.findViewById(R.id.containers_spinner)).getSelectedItem() + "");
			stackData.add((theStack.findViewWithTag("noodle_cbw")) + "");
			stacks3.add(stackData);
		}
		for (int i = stacks1.size() - 1; i > -1; i--) {
			if (stacks1.get(i).get(0).equals("0") && stacks1.get(i).get(1).equals("0") && stacks1.get(i).get(2).equals("false")) {
				stacks1.remove(i);
			}
		}
		for (int i = stacks2.size() - 1; i > -1; i--) {
			if (stacks2.get(i).get(0).equals("0") && stacks2.get(i).get(1).equals("0") && stacks2.get(i).get(2).equals("false")) {
				stacks2.remove(i);
			}
		}
		for (int i = stacks3.size() - 1; i > -1; i--) {
			if (stacks3.get(i).get(0).equals("0") && stacks3.get(i).get(1).equals("0") && stacks3.get(i).get(2).equals("false")) {
				stacks3.remove(i);
			}
		}
		Toast.makeText(this, stacks1 + "", Toast.LENGTH_LONG).show();
		Toast.makeText(this, stacks2 + "", Toast.LENGTH_LONG).show();
		Toast.makeText(this, stacks3 + "", Toast.LENGTH_LONG).show();
		File scoutFolder = new File(String.valueOf(Environment.getExternalStorageDirectory()) + "/" + settings.getString("folder_name", "FRCScouting") + "/local/MatchScouts");
		if (!scoutFolder.exists() && !scoutFolder.mkdirs()) {
			Log.w("File directory", "Failed to create directory");
		}
		matchScoutFile = new File(scoutFolder,
				AlliancePagerAdapter.teamNumbers[0] + " & " +
				AlliancePagerAdapter.teamNumbers[1] + " & " +
				AlliancePagerAdapter.teamNumbers[2] + ".xml");
		/*try {
			fOut = new FileOutputStream(matchScoutFile);
			xml = Xml.newSerializer();
			StringWriter writer = new StringWriter();
			xml.setOutput(writer);
			xml.startDocument("UTF-8", true);
			text("");
			xml.startTag(null, "MatchScout");
			xml.attribute(null, "localFile", "true");
			text("");
			xml.startTag(null, "team1");
			xml.attribute(null, "teamNumber", AlliancePagerAdapter.team1.teamNumber.getText() + "");
			text("");
			start("main");
			start("teamNumber");
			text(AlliancePagerAdapter.team1.teamNumber.getText() + "");
			end("teamNumber");
			start("driveType");
			text(AlliancePagerAdapter.team1.driveSpinner.getSelectedItem() + "");
			end("driveType");
			start("wheelType");
			text(AlliancePagerAdapter.team1.wheelTypeSpinner.getSelectedItem() + "");
			end("wheelType");
			start("wheelNumber");
			text(AlliancePagerAdapter.team1.wheelNumSpinner.getSelectedItem() + "");
			end("wheelNumber");
			start("cimNumber");
			text(AlliancePagerAdapter.team1.cimNumSpinner.getSelectedItem() + "");
			end("cimNumber");
			start("maxSpeed");
			text(AlliancePagerAdapter.team1.maxSpeed.getText() + "");
			end("maxSpeed");
			start("mainComment");
			//text(AlliancePagerAdapter.team1.mainComment.getText() + "");
			end("mainComment");
			end("main");
			start("totes");
			start("wideSideTote");
			text(AlliancePagerAdapter.team1.wideTeleCBW + "");
			end("wideSideTote");
			start("narrowSideTote");
			text(AlliancePagerAdapter.team1.narrowTeleCBW + "");
			end("narrowSideTote");
			start("stepPickUp");
			text(AlliancePagerAdapter.team1.stepTeleCBW + "");
			end("stepPickUp");
			start("landfillPickUp");
			text(AlliancePagerAdapter.team1.landfillTeleCBW + "");
			end("landfillPickUp");
			start("toteChutePickUp");
			text(AlliancePagerAdapter.team1.humanPlayerTeleCBW + "");
			end("toteChutePickUp");
			start("highestPossibleStack");
			//text(AlliancePagerAdapter.team1.highestPossibleStackSpinner.getSelectedItem() + "");
			end("highestPossibleStack");
			start("teleComment");
			//text(AlliancePagerAdapter.team1.teleComment.getText() + "");
			end("teleComment");
			end("totes");
			start("auto");
			start("autoZone");
			text(AlliancePagerAdapter.team1.autoZoneAutoCBW + "");
			end("autoZone");
			start("totes");
			text(AlliancePagerAdapter.team1.totesAutoCBW + "");
			end("totes");
			start("containers");
			text(AlliancePagerAdapter.team1.containersAutoCBW + "");
			end("containers");
			start("flexible");
			text(AlliancePagerAdapter.team1.flexibleAutoCBW + "");
			end("flexible");
			start("autoComment");
			//text(AlliancePagerAdapter.team1.autoComment.getText() + "");
			end("autoComment");
			end("auto");
			start("abilities");
			start("canStackTotes");
			text(AlliancePagerAdapter.team1.totesAbilityCBW + "");
			end("canStackTotes");
			start("canLiftCans");
			text(AlliancePagerAdapter.team1.containersAbilityCBW + "");
			end("canLiftCans");
			start("canScoreNoodles");
			text(AlliancePagerAdapter.team1.noodlesAbilityCBW + "");
			end("canScoreNoodles");
			start("canShift");
			text(AlliancePagerAdapter.team1.shiftingAbilityCBW + "");
			end("canShift");
			start("canCoopStack");
			text(AlliancePagerAdapter.team1.coopAbilityCBW + "");
			end("canCoopStack");
			start("abilitiesComment");
			//text(AlliancePagerAdapter.team1.abilitiesComment.getText() + "");
			end("abilitiesComment");
			end("abilities");
			end("team1");
			end("MatchScout");
			xml.endDocument();
			xml.flush();
			String dataWrite = writer.toString();
			fOut.write(dataWrite.getBytes());
			fOut.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		MainActivity.singleton.setLastSavedTeam(AlliancePagerAdapter.team1.teamNumber.getText() + " & " +
				AlliancePagerAdapter.team2.teamNumber.getText() + " & " +
				AlliancePagerAdapter.team3.teamNumber.getText());
		finish();*/
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
	public void onClick(View v) {
		if (v instanceof CheckBoxWidget) {
			((CheckBoxWidget) v).setCheckBox(!((CheckBoxWidget) v).isChecked());
		}
	}
}
