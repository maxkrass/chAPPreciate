package com.maxkrass.appreciate.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.Xml;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.maxkrass.appreciate.R;
import com.maxkrass.appreciate.Team;
import com.maxkrass.appreciate.adapter.MainPagerAdapter;
import com.maxkrass.appreciate.views.CheckBoxWidget;
import com.maxkrass.appreciate.adapter.AlliancePagerAdapter;

import org.xmlpull.v1.XmlSerializer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;

public class MatchScout extends AppCompatActivity implements View.OnClickListener {

	ViewPager viewPager;
	AlliancePagerAdapter alliance;
	TabLayout tabLayout;
	SharedPreferences settings;

	FileOutputStream fOut;
	XmlSerializer xml;

	int i0 = 0, i1 = 0, i2 = 0;
	String matchNumber = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Intent intent = getIntent();
		setContentView(R.layout.match_save_dialog);
		Toolbar toolbar = (Toolbar) findViewById(R.id.match_toolbar);
		matchNumber = intent.getStringExtra("matchNumber");
		//toolbar.setTitle("Match " + matchNumber);
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
		settings = PreferenceManager.getDefaultSharedPreferences(MainActivity.singleton);
		alliance = new AlliancePagerAdapter(getSupportFragmentManager(), intent.getStringExtra("team1"), intent.getStringExtra("team2"), intent.getStringExtra("team3"));
		viewPager = (ViewPager) findViewById(R.id.viewPager);
		viewPager.setOffscreenPageLimit(2);
		viewPager.setAdapter(alliance);
		tabLayout = (TabLayout) findViewById(R.id.tabStrip);
		tabLayout.setupWithViewPager(viewPager);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		//TODO create appropriate menu
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
				AlliancePagerAdapter.team1.clearFields();
				AlliancePagerAdapter.team2.clearFields();
				AlliancePagerAdapter.team3.clearFields();
				i0 = 0;
				i1 = 0;
				i2 = 0;
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
		data1.add(AlliancePagerAdapter.team1.autoZoneCBW + "");
		data1.add(AlliancePagerAdapter.team1.stackedTotesCBW + "");
		data1.add(AlliancePagerAdapter.team1.workedCBW + "");
		data1.add(AlliancePagerAdapter.team1.autoPoints.getText() + "");
		data1.add(AlliancePagerAdapter.team1.autoComment.getText() + "");
		data1.add(AlliancePagerAdapter.team1.functionalCBW + "");
		data1.add(AlliancePagerAdapter.team1.coopertitionCBW + "");
		data1.add(AlliancePagerAdapter.team1.totalPoints.getText() + "");
		data1.add(AlliancePagerAdapter.team1.teleComment.getText() + "");
		data2.add(AlliancePagerAdapter.team2.autoZoneCBW + "");
		data2.add(AlliancePagerAdapter.team2.stackedTotesCBW + "");
		data2.add(AlliancePagerAdapter.team2.workedCBW + "");
		data2.add(AlliancePagerAdapter.team2.autoPoints.getText() + "");
		data2.add(AlliancePagerAdapter.team2.autoComment.getText() + "");
		data2.add(AlliancePagerAdapter.team2.functionalCBW + "");
		data2.add(AlliancePagerAdapter.team2.coopertitionCBW + "");
		data2.add(AlliancePagerAdapter.team2.totalPoints.getText() + "");
		data2.add(AlliancePagerAdapter.team2.teleComment.getText() + "");
		data3.add(AlliancePagerAdapter.team3.autoZoneCBW + "");
		data3.add(AlliancePagerAdapter.team3.stackedTotesCBW + "");
		data3.add(AlliancePagerAdapter.team3.workedCBW + "");
		data3.add(AlliancePagerAdapter.team3.autoPoints.getText() + "");
		data3.add(AlliancePagerAdapter.team3.autoComment.getText() + "");
		data3.add(AlliancePagerAdapter.team3.functionalCBW + "");
		data3.add(AlliancePagerAdapter.team3.coopertitionCBW + "");
		data3.add(AlliancePagerAdapter.team3.totalPoints.getText() + "");
		data3.add(AlliancePagerAdapter.team3.teleComment.getText() + "");
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
		//Toast.makeText(this, stacks1 + "", Toast.LENGTH_LONG).show();
		//Toast.makeText(this, stacks2 + "", Toast.LENGTH_LONG).show();
		//Toast.makeText(this, stacks3 + "", Toast.LENGTH_LONG).show();
		File firstScoutFolder = new File(String.valueOf(Environment.getExternalStorageDirectory()) + "/" + settings.getString("folder_name", "FRCScouting") + "/data/Team " + AlliancePagerAdapter.teamNumbers[0]);
		if (!firstScoutFolder.exists() && !firstScoutFolder.mkdirs()) {
			Log.w("File directory", "Failed to create directory");
		}
		File firstMatchScoutFile = new File(firstScoutFolder, "Match " + matchNumber + ".match");
		try {
			fOut = new FileOutputStream(firstMatchScoutFile);
			xml = Xml.newSerializer();
			StringWriter writer = new StringWriter();
			xml.setOutput(writer);
			xml.startDocument("UTF-8", true);
			text("");
			xml.startTag(null, "MatchScout");
			xml.attribute(null, "localFile", "true");
			text("");
			start("autoZone");
			text(data1.get(0));
			end("autoZone");
			start("stackedTotes");
			text(data1.get(1));
			end("stackedTotes");
			start("worked");
			text(data1.get(2));
			end("worked");
			start("autoPoints");
			text(data1.get(3));
			end("autoPoints");
			start("autoComment");
			text(data1.get(4));
			end("autoComment");
			start("numberOfStacks");
			text(stacks1.size() + "");
			end("numberOfStacks");
			for (int i = 0; i < stacks1.size(); i++) {
				start("stack" + (i + 1));
				start("totes");
				text(stacks1.get(i).get(0));
				end("totes");
				start("containers");
				text(stacks1.get(i).get(1));
				end("containers");
				start("noodles");
				text(stacks1.get(i).get(2));
				end("noodles");
				end("stack" + (i + 1));
			}
			start("functional");
			text(data1.get(5));
			end("functional");
			start("coopertition");
			text(data1.get(6));
			end("coopertition");
			start("telePoints");
			text(data1.get(7));
			end("telePoints");
			start("teleComment");
			text(data1.get(8));
			end("teleComment");
			end("MatchScout");
			xml.endDocument();
			xml.flush();
			String dataWrite = writer.toString();
			MainActivity.singleton.sendMatch(dataWrite, AlliancePagerAdapter.teamNumbers[0], matchNumber);
			Toast.makeText(this, "Match " + matchNumber + ": Team " + AlliancePagerAdapter.teamNumbers[0] + " saved successfully", Toast.LENGTH_SHORT).show();
			fOut.write(dataWrite.getBytes());
			fOut.close();
			MainPagerAdapter.matchScouts.teamAdapter.add(new Team(AlliancePagerAdapter.teamNumbers[0]));
		} catch (IOException e) {
			e.printStackTrace();
		}

		File secondScoutFolder = new File(String.valueOf(Environment.getExternalStorageDirectory()) + "/" + settings.getString("folder_name", "FRCScouting") + "/data/Team " + AlliancePagerAdapter.teamNumbers[1]);
		if (!secondScoutFolder.exists() && !secondScoutFolder.mkdirs()) {
			Log.w("File directory", "Failed to create directory");
		}
		try {
			File secondMatchScoutFile = new File(secondScoutFolder, "Match " + matchNumber + ".match");
			FileOutputStream secondOut = new FileOutputStream(secondMatchScoutFile);
			xml = Xml.newSerializer();
			StringWriter writer = new StringWriter();
			xml.setOutput(writer);
			xml.startDocument("UTF-8", true);
			text("");
			xml.startTag(null, "MatchScout");
			xml.attribute(null, "localFile", "true");
			text("");
			start("autoZone");
			text(data2.get(0));
			end("autoZone");
			start("stackedTotes");
			text(data2.get(1));
			end("stackedTotes");
			start("worked");
			text(data2.get(2));
			end("worked");
			start("autoPoints");
			text(data2.get(3));
			end("autoPoints");
			start("autoComment");
			text(data2.get(4));
			end("autoComment");
			start("numberOfStacks");
			text(stacks2.size() + "");
			end("numberOfStacks");
			for (int i = 0; i < stacks2.size(); i++) {
				start("stack" + (i + 1));
				start("totes");
				text(stacks2.get(i).get(0));
				end("totes");
				start("containers");
				text(stacks2.get(i).get(1));
				end("containers");
				start("noodles");
				text(stacks2.get(i).get(2));
				end("noodles");
				end("stack" + (i + 1));
			}
			start("functional");
			text(data2.get(5));
			end("functional");
			start("coopertition");
			text(data2.get(6));
			end("coopertition");
			start("telePoints");
			text(data2.get(7));
			end("telePoints");
			start("teleComment");
			text(data2.get(8));
			end("teleComment");
			end("MatchScout");
			xml.endDocument();
			xml.flush();
			String dataWrite = writer.toString();
			//MainActivity.singleton.sendViaBluetooth(dataWrite);
			MainActivity.singleton.sendMatch(dataWrite, AlliancePagerAdapter.teamNumbers[1], matchNumber);
			Toast.makeText(this, "Match " + matchNumber + ": Team " + AlliancePagerAdapter.teamNumbers[1] + " saved successfully", Toast.LENGTH_SHORT).show();
			secondOut.write(dataWrite.getBytes());
			secondOut.close();
			MainPagerAdapter.matchScouts.teamAdapter.add(new Team(AlliancePagerAdapter.teamNumbers[1]));
		} catch (IOException e) {
			e.printStackTrace();
		}

		File thirdScoutFolder = new File(String.valueOf(Environment.getExternalStorageDirectory()) + "/" + settings.getString("folder_name", "FRCScouting") + "/data/Team " + AlliancePagerAdapter.teamNumbers[2]);
		if (!thirdScoutFolder.exists() && !thirdScoutFolder.mkdirs()) {
			Log.w("File directory", "Failed to create directory");
		}
		try {
			File thirdMatchScoutFile = new File(thirdScoutFolder, "Match " + matchNumber + ".match");
			FileOutputStream thirdOut = new FileOutputStream(thirdMatchScoutFile);
			xml = Xml.newSerializer();
			StringWriter writer = new StringWriter();
			xml.setOutput(writer);
			xml.startDocument("UTF-8", true);
			text("");
			xml.startTag(null, "MatchScout");
			xml.attribute(null, "localFile", "true");
			text("");
			start("autoZone");
			text(data3.get(0));
			end("autoZone");
			start("stackedTotes");
			text(data3.get(1));
			end("stackedTotes");
			start("worked");
			text(data3.get(2));
			end("worked");
			start("autoPoints");
			text(data3.get(3));
			end("autoPoints");
			start("autoComment");
			text(data3.get(4));
			end("autoComment");
			start("numberOfStacks");
			text(stacks3.size() + "");
			end("numberOfStacks");
			for (int i = 0; i < stacks3.size(); i++) {
				start("stack" + (i + 1));
				start("totes");
				text(stacks3.get(i).get(0));
				end("totes");
				start("containers");
				text(stacks3.get(i).get(1));
				end("containers");
				start("noodles");
				text(stacks3.get(i).get(2));
				end("noodles");
				end("stack" + (i + 1));
			}
			start("functional");
			text(data3.get(5));
			end("functional");
			start("coopertition");
			text(data3.get(6));
			end("coopertition");
			start("telePoints");
			text(data3.get(7));
			end("telePoints");
			start("teleComment");
			text(data3.get(8));
			end("teleComment");
			end("MatchScout");
			xml.endDocument();
			xml.flush();
			String dataWrite = writer.toString();
			//MainActivity.singleton.sendViaBluetooth(dataWrite);
			MainActivity.singleton.sendMatch(dataWrite, AlliancePagerAdapter.teamNumbers[2], matchNumber);
			Toast.makeText(this, "Match " + matchNumber + ": Team " + AlliancePagerAdapter.teamNumbers[2] + " saved successfully", Toast.LENGTH_SHORT).show();
			thirdOut.write(dataWrite.getBytes());
			thirdOut.close();
			MainPagerAdapter.matchScouts.teamAdapter.add(new Team(AlliancePagerAdapter.teamNumbers[2]));
		} catch (IOException e) {
			e.printStackTrace();
		}
		MainActivity.singleton.setLastSavedTeam(AlliancePagerAdapter.teamNumbers[0] + " & " +
				AlliancePagerAdapter.teamNumbers[1] + " & " +
				AlliancePagerAdapter.teamNumbers[2]);
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
	public void onClick(View v) {
		if (v instanceof CheckBoxWidget) {
			((CheckBoxWidget) v).setCheckBox(!((CheckBoxWidget) v).isChecked());
		}
	}
}
