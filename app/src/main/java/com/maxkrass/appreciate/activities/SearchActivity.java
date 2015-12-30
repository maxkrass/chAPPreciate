package com.maxkrass.appreciate.activities;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import com.maxkrass.appreciate.R;
import com.maxkrass.appreciate.Team;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;

public class SearchActivity extends ActionBarActivity {
	TextView autoZoneTeam1;
	TextView stackedTotesTeam1;
	TextView workedTeam1;
	TextView autoPointsTeam1;
	TextView stacksNumberTeam1;
	TextView totesStackTeam1;
	TextView containersStackTeam1;
	TextView noodlesStackTeam1;
	TextView functionalTeam1;
	TextView coopertitionTeam1;
	TextView totalPointsTeam1;
	TextView commentAutoTeam1;
	TextView commentTeleTeam1;
	TextView autoZoneTeam2;
	TextView stackedTotesTeam2;
	TextView workedTeam2;
	TextView autoPointsTeam2;
	TextView stacksNumberTeam2;
	TextView totesStackTeam2;
	TextView containersStackTeam2;
	TextView noodlesStackTeam2;
	TextView functionalTeam2;
	TextView coopertitionTeam2;
	TextView totalPointsTeam2;
	TextView commentAutoTeam2;
	TextView commentTeleTeam2;
	TextView autoZoneTeam3;
	TextView stackedTotesTeam3;
	TextView workedTeam3;
	TextView autoPointsTeam3;
	TextView stacksNumberTeam3;
	TextView totesStackTeam3;
	TextView containersStackTeam3;
	TextView noodlesStackTeam3;
	TextView functionalTeam3;
	TextView coopertitionTeam3;
	TextView totalPointsTeam3;
	TextView commentAutoTeam3;
	TextView commentTeleTeam3;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search);
		Toolbar toolbar = (Toolbar) findViewById(R.id.search_toolbar);
		setSupportActionBar(toolbar);
		autoZoneTeam1 = (TextView) findViewById(R.id.auto_zone_team1);
		stackedTotesTeam1 = (TextView) findViewById(R.id.stacked_totes_team1);
		workedTeam1 = (TextView) findViewById(R.id.worked_team1);
		autoPointsTeam1 = (TextView) findViewById(R.id.auto_points_team1);
		stacksNumberTeam1 = (TextView) findViewById(R.id.stacks_number_team1);
		totesStackTeam1 = (TextView) findViewById(R.id.totes_per_stack_team1);
		containersStackTeam1 = (TextView) findViewById(R.id.containers_per_stack_team1);
		noodlesStackTeam1 = (TextView) findViewById(R.id.noodles_per_stack_team1);
		functionalTeam1 = (TextView) findViewById(R.id.functional_team1);
		coopertitionTeam1 = (TextView) findViewById(R.id.coopertition_team1);
		totalPointsTeam1 = (TextView) findViewById(R.id.total_points_team1);
		commentAutoTeam1 = (TextView) findViewById(R.id.first_team_comment_auto);
		commentTeleTeam1 = (TextView) findViewById(R.id.first_team_comment_tele);
		autoZoneTeam2 = (TextView) findViewById(R.id.auto_zone_team2);
		stackedTotesTeam2 = (TextView) findViewById(R.id.stacked_totes_team2);
		workedTeam2 = (TextView) findViewById(R.id.worked_team2);
		autoPointsTeam2 = (TextView) findViewById(R.id.auto_points_team2);
		stacksNumberTeam2 = (TextView) findViewById(R.id.stacks_number_team2);
		totesStackTeam2 = (TextView) findViewById(R.id.totes_per_stack_team2);
		containersStackTeam2 = (TextView) findViewById(R.id.containers_per_stack_team2);
		noodlesStackTeam2 = (TextView) findViewById(R.id.noodles_per_stack_team2);
		functionalTeam2 = (TextView) findViewById(R.id.functional_team2);
		coopertitionTeam2 = (TextView) findViewById(R.id.coopertition_team2);
		totalPointsTeam2 = (TextView) findViewById(R.id.total_points_team2);
		commentAutoTeam2 = (TextView) findViewById(R.id.second_team_comment_auto);
		commentTeleTeam2 = (TextView) findViewById(R.id.second_team_comment_tele);
		autoZoneTeam3 = (TextView) findViewById(R.id.auto_zone_team3);
		stackedTotesTeam3 = (TextView) findViewById(R.id.stacked_totes_team3);
		workedTeam3 = (TextView) findViewById(R.id.worked_team3);
		autoPointsTeam3 = (TextView) findViewById(R.id.auto_points_team3);
		stacksNumberTeam3 = (TextView) findViewById(R.id.stacks_number_team3);
		totesStackTeam3 = (TextView) findViewById(R.id.totes_per_stack_team3);
		containersStackTeam3 = (TextView) findViewById(R.id.containers_per_stack_team3);
		noodlesStackTeam3 = (TextView) findViewById(R.id.noodles_per_stack_team3);
		functionalTeam3 = (TextView) findViewById(R.id.functional_team3);
		coopertitionTeam3 = (TextView) findViewById(R.id.coopertition_team3);
		totalPointsTeam3 = (TextView) findViewById(R.id.total_points_team3);
		commentAutoTeam3 = (TextView) findViewById(R.id.third_team_comment_auto);
		commentTeleTeam3 = (TextView) findViewById(R.id.third_team_comment_tele);
		EditText team1Input = (EditText) findViewById(R.id.team1_input);
		EditText team2Input = (EditText) findViewById(R.id.team2_input);
		EditText team3Input = (EditText) findViewById(R.id.team3_input);
		team1Input.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {

			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
			}

			@Override
			public void afterTextChanged(Editable s) {
				//updateTeam1(s.toString());
			}
		});
		team2Input.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {

			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
			}

			@Override
			public void afterTextChanged(Editable s) {
				//updateTeam2(s.toString());
			}
		});
		team3Input.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {

			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
			}

			@Override
			public void afterTextChanged(Editable s) {
				//updateTeam3(s.toString());
			}
		});
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == android.R.id.home)
			finish();
		return true;
	}

	private int getMaximumListSize(ArrayList<ArrayList> array) {
		int maxListSize = 0;
		for (ArrayList rowList : array) {
			if (maxListSize < rowList.size())
				maxListSize = rowList.size();
		}

		return maxListSize;
	}

	/*private void updateTeam3(String s) {
		File firstMatchFolder = new File(String.valueOf(Environment.getExternalStorageDirectory()) + "/" + MainActivity.singleton.settings.getString("folder_name", "FRCScouting") + "/data/Team " + s);
		if (firstMatchFolder.exists()) {
			FilenameFilter filter = new FilenameFilter() {
				@Override
				public boolean accept(File dir, String filename) {
					return filename.startsWith("Match ") && filename.endsWith(".match");
				}
			};
			File[] allMatches = firstMatchFolder.listFiles(filter);
			if (allMatches != null && allMatches.length > 0) {
				ArrayList<ArrayList<String>> dataMatrix = new ArrayList<>();
				for (File match : allMatches) {                                                     //Put all matches for this team
					ArrayList<String> dataList = Team.getTextFromFile(match);
					dataMatrix.add(dataList);
				}
				ArrayList<ArrayList<Integer>> stacks = new ArrayList<>();
				for (int r = 0; r < dataMatrix.size(); r++) {                                           //pull out the stacks
					for (int c = 0; c < Integer.parseInt(dataMatrix.get(r).get(5)); c++) {
						ArrayList<Integer> stack = new ArrayList<>();
						stack.add(Integer.parseInt(dataMatrix.get(r).remove(6)));
						stack.add(Integer.parseInt(dataMatrix.get(r).remove(6)));
						stack.add(dataMatrix.get(r).remove(6).equals("true") ? 1 : 0);
						stacks.add(stack);
					}
				}
				ArrayList<Double> avgStack = new ArrayList<>();
				for (int r = 0; r < stacks.size(); r++) {                                               //sum up all the stacks
					if (r == 0) {
						avgStack.add((double) stacks.get(r).get(0));
						avgStack.add((double) stacks.get(r).get(1));
						avgStack.add((double) stacks.get(r).get(2));
					} else {
						avgStack.set(0, avgStack.get(0) + stacks.get(r).get(0));
						avgStack.set(1, avgStack.get(1) + stacks.get(r).get(1));
						avgStack.set(2, avgStack.get(2) + stacks.get(r).get(2));
					}
				}
				if (stacks.size() > 0) {
					avgStack.set(0, ((int) ((avgStack.get(0) / stacks.size()) * 100)) / 100.0);         //average them
					avgStack.set(1, ((int) ((avgStack.get(1) / stacks.size()) * 100)) / 100.0);
					avgStack.set(2, ((int) ((avgStack.get(2) / stacks.size()) * 100)) / 100.0);
				} else {
					avgStack.add(0, 0.0);
					avgStack.add(1, 0.0);
					avgStack.add(2, 0.0);
				}
				ArrayList<ArrayList> parseDataMatrix = new ArrayList<>();
				for (int i = 0; i < dataMatrix.size(); i++) {                                           //parse the Strings in the dataMartix
					ArrayList parseDataList = new ArrayList();
					for (int j = 0; j < dataMatrix.get(i).size(); j++) {
						switch (dataMatrix.get(i).get(j)) {
							case "false":
								parseDataList.add(j, 0);
								break;
							case "true":
								parseDataList.add(j, 1);
								break;
							case "7+":
								parseDataList.add(j, 7);
								break;
							default:
								if (j == 8 || j == 3 || j == 5) {
									if (!dataMatrix.get(i).get(j).equals(" "))
										parseDataList.add(j, Integer.parseInt(dataMatrix.get(i).get(j)));
									else
										parseDataList.add(j, 0);
								} else {
									parseDataList.add(j, dataMatrix.get(i).get(j));
								}
						}
					}
					parseDataMatrix.add(parseDataList);
				}
				int arraySize = parseDataMatrix.size();
				int maxColumns = getMaximumListSize(parseDataMatrix);
				ArrayList<String> avgNumList = new ArrayList<>();
				ArrayList<String> textList = new ArrayList<>();
				ArrayList<Integer> pointsList = new ArrayList<>();
				for (int c = 0; c < maxColumns; c++) {
					int sum = 0, pointsSum = 0, count = 0, pointsCount = 0;
					boolean pointsChange = false, sumChange = false;
					for (int r = 0; r < arraySize; r++) {
						ArrayList rowList = parseDataMatrix.get(r);
						if (c < rowList.size()) {
							if (c == 4 || c == 9) {
								textList.add(rowList.get(c).toString());
							} else if (c == 3 || c == 5 || c == 8) {
								pointsSum += (int) rowList.get(c);
								pointsCount++;
								pointsChange = true;
							} else {
								sum += (int) rowList.get(c);
								sumChange = true;
								count++;
							}
						}
					}
					if (sumChange)
						avgNumList.add(((int) (((double) sum / count) * 100)) + "%");
					if (pointsChange)
						pointsList.add(((int) (((double) pointsSum / pointsCount) * 100)) / 100);
				}
				String commentAuto = "";
				String commentTele = "";
				for (int i = 0; i < textList.size() / 2; i++) {
					commentAuto += textList.get(i).equals(" ") ? "" : "\n" + textList.get(i);
				}
				commentAuto = commentAuto.replaceFirst("\\n", "");
				for (int i = textList.size() / 2; i < textList.size(); i++) {
					commentTele += textList.get(i).equals(" ") ? "" : "\n" + textList.get(i);
				}
				commentTele = commentTele.replaceFirst("\\n", "");
				autoZoneTeam3.setText(avgNumList.get(0));
				stackedTotesTeam3.setText(avgNumList.get(1));
				workedTeam3.setText(avgNumList.get(2));
				autoPointsTeam3.setText(pointsList.get(0).toString());
				stacksNumberTeam3.setText(pointsList.get(1).toString());
				totesStackTeam3.setText(avgStack.get(0).toString());
				containersStackTeam3.setText(avgStack.get(1).toString());
				noodlesStackTeam3.setText(avgStack.get(2).toString());
				functionalTeam3.setText(avgNumList.get(3));
				coopertitionTeam3.setText(avgNumList.get(4));
				totalPointsTeam3.setText(pointsList.get(2).toString());
				commentAutoTeam3.setText(commentAuto);
				commentTeleTeam3.setText(commentTele);
			}
		} else {
			autoZoneTeam3.setText("");
			stackedTotesTeam3.setText("");
			workedTeam3.setText("");
			autoPointsTeam3.setText("");
			stacksNumberTeam3.setText("");
			totesStackTeam3.setText("");
			containersStackTeam3.setText("");
			noodlesStackTeam3.setText("");
			functionalTeam3.setText("");
			coopertitionTeam3.setText("");
			totalPointsTeam3.setText("");
			commentAutoTeam3.setText("");
			commentTeleTeam3.setText("");
		}
	}*/

	/*private void updateTeam2(String s) {
		File firstMatchFolder = new File(String.valueOf(Environment.getExternalStorageDirectory()) + "/" + MainActivity.singleton.settings.getString("folder_name", "FRCScouting") + "/data/Team " + s);
		if (firstMatchFolder.exists()) {
			FilenameFilter filter = new FilenameFilter() {
				@Override
				public boolean accept(File dir, String filename) {
					return filename.startsWith("Match ") && filename.endsWith(".match");
				}
			};
			File[] allMatches = firstMatchFolder.listFiles(filter);
			if (allMatches != null && allMatches.length > 0) {
				ArrayList<ArrayList<String>> dataMatrix = new ArrayList<>();
				for (File match : allMatches) {                                                         //Put all matches for this team
					ArrayList<String> dataList = Team.getTextFromFile(match);
					dataMatrix.add(dataList);
				}
				ArrayList<ArrayList<Integer>> stacks = new ArrayList<>();
				for (int r = 0; r < dataMatrix.size(); r++) {                                           //pull out the stacks
					for (int c = 0; c < Integer.parseInt(dataMatrix.get(r).get(5)); c++) {
						ArrayList<Integer> stack = new ArrayList<>();
						stack.add(Integer.parseInt(dataMatrix.get(r).remove(6)));
						stack.add(Integer.parseInt(dataMatrix.get(r).remove(6)));
						stack.add(dataMatrix.get(r).remove(6).equals("true") ? 1 : 0);
						stacks.add(stack);
					}
				}
				ArrayList<Double> avgStack = new ArrayList<>();
				for (int r = 0; r < stacks.size(); r++) {                                               //sum up all the stacks
					if (r == 0) {
						avgStack.add((double) stacks.get(r).get(0));
						avgStack.add((double) stacks.get(r).get(1));
						avgStack.add((double) stacks.get(r).get(2));
					} else {
						avgStack.set(0, avgStack.get(0) + stacks.get(r).get(0));
						avgStack.set(1, avgStack.get(1) + stacks.get(r).get(1));
						avgStack.set(2, avgStack.get(2) + stacks.get(r).get(2));
					}
				}
				if (stacks.size() > 0) {
					avgStack.set(0, ((int) ((avgStack.get(0) / stacks.size()) * 100)) / 100.0);             //average them
					avgStack.set(1, ((int) ((avgStack.get(1) / stacks.size()) * 100)) / 100.0);
					avgStack.set(2, ((int) ((avgStack.get(2) / stacks.size()) * 100)) / 100.0);
				} else {
					avgStack.add(0, 0.0);
					avgStack.add(1, 0.0);
					avgStack.add(2, 0.0);
				}
				ArrayList<ArrayList> parseDataMatrix = new ArrayList<>();
				for (int i = 0; i < dataMatrix.size(); i++) {                                           //parse the Strings in the dataMartix
					ArrayList parseDataList = new ArrayList();
					for (int j = 0; j < dataMatrix.get(i).size(); j++) {
						switch (dataMatrix.get(i).get(j)) {
							case "false":
								parseDataList.add(j, 0);
								break;
							case "true":
								parseDataList.add(j, 1);
								break;
							case "7+":
								parseDataList.add(j, 7);
								break;
							default:
								if (j == 8 || j == 3 || j == 5) {
									if (!dataMatrix.get(i).get(j).equals(" "))
										parseDataList.add(j, Integer.parseInt(dataMatrix.get(i).get(j)));
									else
										parseDataList.add(j, 0);
								} else {
									parseDataList.add(j, dataMatrix.get(i).get(j));
								}
						}
					}
					parseDataMatrix.add(parseDataList);
				}
				int arraySize = parseDataMatrix.size();
				int maxColumns = getMaximumListSize(parseDataMatrix);
				ArrayList<String> avgNumList = new ArrayList<>();
				ArrayList<String> textList = new ArrayList<>();
				ArrayList<Integer> pointsList = new ArrayList<>();
				for (int c = 0; c < maxColumns; c++) {
					int sum = 0, pointsSum = 0, count = 0, pointsCount = 0;
					boolean pointsChange = false, sumChange = false;
					for (int r = 0; r < arraySize; r++) {
						ArrayList rowList = parseDataMatrix.get(r);
						if (c < rowList.size()) {
							if (c == 4 || c == 9) {
								textList.add(rowList.get(c).toString());
							} else if (c == 3 || c == 5 || c == 8) {
								pointsSum += (int) rowList.get(c);
								pointsCount++;
								pointsChange = true;
							} else {
								sum += (int) rowList.get(c);
								sumChange = true;
								count++;
							}
						}
					}
					if (sumChange)
						avgNumList.add(((int) (((double) sum / count) * 100)) + "%");
					if (pointsChange)
						pointsList.add(((int) (((double) pointsSum / pointsCount) * 100)) / 100);
				}
				String commentAuto = "";
				String commentTele = "";
				for (int i = 0; i < textList.size() / 2; i++) {
					commentAuto += textList.get(i).equals(" ") ? "" : "\n" + textList.get(i);
				}
				commentAuto = commentAuto.replaceFirst("\\n", "");
				for (int i = textList.size() / 2; i < textList.size(); i++) {
					commentTele += textList.get(i).equals(" ") ? "" : "\n" + textList.get(i);
				}
				commentTele = commentTele.replaceFirst("\\n", "");
				autoZoneTeam2.setText(avgNumList.get(0));
				stackedTotesTeam2.setText(avgNumList.get(1));
				workedTeam2.setText(avgNumList.get(2));
				autoPointsTeam2.setText(pointsList.get(0).toString());
				stacksNumberTeam2.setText(pointsList.get(1).toString());
				totesStackTeam2.setText(avgStack.get(0).toString());
				containersStackTeam2.setText(avgStack.get(1).toString());
				noodlesStackTeam2.setText(avgStack.get(2).toString());
				functionalTeam2.setText(avgNumList.get(3));
				coopertitionTeam2.setText(avgNumList.get(4));
				totalPointsTeam2.setText(pointsList.get(2).toString());
				commentAutoTeam2.setText(commentAuto);
				commentTeleTeam2.setText(commentTele);
			}
		} else {
			autoZoneTeam2.setText("");
			stackedTotesTeam2.setText("");
			workedTeam2.setText("");
			autoPointsTeam2.setText("");
			stacksNumberTeam2.setText("");
			totesStackTeam2.setText("");
			containersStackTeam2.setText("");
			noodlesStackTeam2.setText("");
			functionalTeam2.setText("");
			coopertitionTeam2.setText("");
			totalPointsTeam2.setText("");
			commentAutoTeam2.setText("");
			commentTeleTeam2.setText("");
		}
	}*/

	/*private void updateTeam1(String teamNumber) {
		File firstMatchFolder = new File(String.valueOf(Environment.getExternalStorageDirectory()) + "/" + MainActivity.singleton.settings.getString("folder_name", "FRCScouting") + "/data/Team " + teamNumber);
		if (firstMatchFolder.exists()) {
			FilenameFilter filter = new FilenameFilter() {
				@Override
				public boolean accept(File dir, String filename) {
					return filename.startsWith("Match ") && filename.endsWith(".match");
				}
			};
			File[] allMatches = firstMatchFolder.listFiles(filter);
			if (allMatches != null && allMatches.length > 0) {
				ArrayList<ArrayList<String>> dataMatrix = new ArrayList<>();
				for (File allMatche : allMatches) {                                                     //Put all matches for this team
					ArrayList<String> dataList = Team.getTextFromFile(allMatche);
					dataMatrix.add(dataList);
				}
				ArrayList<ArrayList<Integer>> stacks = new ArrayList<>();
				for (int r = 0; r < dataMatrix.size(); r++) {                                           //pull out the stacks
					for (int c = 0; c < Integer.parseInt(dataMatrix.get(r).get(5)); c++) {
						ArrayList<Integer> stack = new ArrayList<>();
						stack.add(Integer.parseInt(dataMatrix.get(r).remove(6)));
						stack.add(Integer.parseInt(dataMatrix.get(r).remove(6)));
						stack.add(dataMatrix.get(r).remove(6).equals("true") ? 1 : 0);
						stacks.add(stack);
					}
				}
				ArrayList<Double> avgStack = new ArrayList<>();
				for (int r = 0; r < stacks.size(); r++) {                                               //sum up all the stacks
					if (r == 0) {
						avgStack.add((double) stacks.get(r).get(0));
						avgStack.add((double) stacks.get(r).get(1));
						avgStack.add((double) stacks.get(r).get(2));
					} else {
						avgStack.set(0, avgStack.get(0) + stacks.get(r).get(0));
						avgStack.set(1, avgStack.get(1) + stacks.get(r).get(1));
						avgStack.set(2, avgStack.get(2) + stacks.get(r).get(2));
					}
				}
				if (stacks.size() > 0) {
					avgStack.set(0, ((int) ((avgStack.get(0) / stacks.size()) * 100)) / 100.0);             //average them
					avgStack.set(1, ((int) ((avgStack.get(1) / stacks.size()) * 100)) / 100.0);
					avgStack.set(2, ((int) ((avgStack.get(2) / stacks.size()) * 100)) / 100.0);
				} else {
					avgStack.add(0, 0.0);
					avgStack.add(1, 0.0);
					avgStack.add(2, 0.0);
				}
				ArrayList<ArrayList> parseDataMatrix = new ArrayList<>();
				for (int i = 0; i < dataMatrix.size(); i++) {                                           //parse the Strings in the dataMartix
					ArrayList parseDataList = new ArrayList();
					for (int j = 0; j < dataMatrix.get(i).size(); j++) {
						switch (dataMatrix.get(i).get(j)) {
							case "false":
								parseDataList.add(j, 0);
								break;
							case "true":
								parseDataList.add(j, 1);
								break;
							case "7+":
								parseDataList.add(j, 7);
								break;
							default:
								if (j == 8 || j == 3 || j == 5) {
									if (!dataMatrix.get(i).get(j).equals(" "))
										parseDataList.add(j, Integer.parseInt(dataMatrix.get(i).get(j)));
									else
										parseDataList.add(j, 0);
								} else {
									parseDataList.add(j, dataMatrix.get(i).get(j));
								}
						}
					}
					parseDataMatrix.add(parseDataList);
				}
				int arraySize = parseDataMatrix.size();
				int maxColumns = getMaximumListSize(parseDataMatrix);
				ArrayList<String> avgNumList = new ArrayList<>();
				ArrayList<String> textList = new ArrayList<>();
				ArrayList<Integer> pointsList = new ArrayList<>();
				for (int c = 0; c < maxColumns; c++) {
					int sum = 0, pointsSum = 0, count = 0, pointsCount = 0;
					boolean pointsChange = false, sumChange = false;
					for (int r = 0; r < arraySize; r++) {
						ArrayList rowList = parseDataMatrix.get(r);
						if (c < rowList.size()) {
							if (c == 4 || c == 9) {
								textList.add(rowList.get(c).toString());
							} else if (c == 3 || c == 5 || c == 8) {
								pointsSum += (int) rowList.get(c);
								pointsCount++;
								pointsChange = true;
							} else {
								sum += (int) rowList.get(c);
								sumChange = true;
								count++;
							}
						}
					}
					if (sumChange)
						avgNumList.add(((int) (((double) sum / count) * 100)) + "%");
					if (pointsChange)
						pointsList.add(((int) (((double) pointsSum / pointsCount) * 100)) / 100);
				}
				String commentAuto = "";
				String commentTele = "";
				for (int i = 0; i < textList.size() / 2; i++) {
					commentAuto += textList.get(i).equals(" ") ? "" : "\n" + textList.get(i);
				}
				commentAuto = commentAuto.replaceFirst("\\n", "");
				for (int i = textList.size() / 2; i < textList.size(); i++) {
					commentTele += textList.get(i).equals(" ") ? "" : "\n" + textList.get(i);
				}
				commentTele = commentTele.replaceFirst("\\n", "");
				autoZoneTeam1.setText(avgNumList.get(0));
				stackedTotesTeam1.setText(avgNumList.get(1));
				workedTeam1.setText(avgNumList.get(2));
				autoPointsTeam1.setText(pointsList.get(0).toString());
				stacksNumberTeam1.setText(pointsList.get(1).toString());
				totesStackTeam1.setText(avgStack.get(0).toString());
				containersStackTeam1.setText(avgStack.get(1).toString());
				noodlesStackTeam1.setText(avgStack.get(2).toString());
				functionalTeam1.setText(avgNumList.get(3));
				coopertitionTeam1.setText(avgNumList.get(4));
				totalPointsTeam1.setText(pointsList.get(2).toString());
				commentAutoTeam1.setText(commentAuto);
				commentTeleTeam1.setText(commentTele);
			}
		} else {
			autoZoneTeam1.setText("");
			stackedTotesTeam1.setText("");
			workedTeam1.setText("");
			autoPointsTeam1.setText("");
			stacksNumberTeam1.setText("");
			totesStackTeam1.setText("");
			containersStackTeam1.setText("");
			noodlesStackTeam1.setText("");
			functionalTeam1.setText("");
			coopertitionTeam1.setText("");
			totalPointsTeam1.setText("");
			commentAutoTeam1.setText("");
			commentTeleTeam1.setText("");
		}
	}*/
}