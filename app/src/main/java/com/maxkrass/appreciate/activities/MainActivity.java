package com.maxkrass.appreciate.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.text.format.Time;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.Toast;

import com.maxkrass.appreciate.R;
import com.maxkrass.appreciate.adapter.IconArrayAdapter;
import com.maxkrass.appreciate.adapter.MainPagerAdapter;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class MainActivity extends BaseActivity {

    private String lastSavedTeam = "";

    MainPagerAdapter scouts;
    TabLayout tabLayout;
    String exportedFileName = "";
    ViewPager viewPager;
    Intent intent;

    public SharedPreferences settings;


    public static MainActivity singleton;

    public String getLastSavedTeam() {
        return lastSavedTeam;
    }

    public void setLastSavedTeam(String lastSavedTeam) {
        this.lastSavedTeam = lastSavedTeam;
    }

    public void createScout(View view) {
        Integer[] icons = new Integer[]{R.drawable.ic_pit_black, R.drawable.ic_game, R.drawable.ic_game};
        ListAdapter adapter = new IconArrayAdapter(MainActivity.this, getResources().getStringArray(R.array.mode), icons);
        new AlertDialog.Builder(this)
                .setTitle("What would you like to do?")
                .setAdapter(adapter, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0: {
                                startActivity(new Intent(MainActivity.this, PitScout.class));
                                break;
                            }
                            case 2: {
                                startActivity(new Intent(MainActivity.this, MatchScoutOneTeam.class));
                                break;
                            }
                            case 1: {
                                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                                LayoutInflater inflater = MainActivity.this.getLayoutInflater();
                                View v = inflater.inflate(R.layout.dialog_layout, null);
                                final EditText matchNumber = (EditText) v.findViewById(R.id.match_number);
                                final EditText firstTeamNumber = (EditText) v.findViewById(R.id.first_team);
                                final EditText secondTeamNumber = (EditText) v.findViewById(R.id.second_team);
                                final EditText thirdTeamNumber = (EditText) v.findViewById(R.id.third_team);
                                builder.setView(v)
                                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
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
                                                    Intent matchScout = new Intent(MainActivity.this, MatchScout.class);
                                                    matchScout.putExtra("matchNumber", matchNumber.getText() + "");
                                                    matchScout.putExtra("team1", firstTeamNumber.getText() + "");
                                                    matchScout.putExtra("team2", secondTeamNumber.getText() + "");
                                                    matchScout.putExtra("team3", thirdTeamNumber.getText() + "");
                                                    startActivity(matchScout);
                                                }
                                            }
                                        })
                                        .setNegativeButton(android.R.string.cancel, null)
                                        .setTitle("Define teams").show();
                                break;
                            }
                        }
                    }
                }).show();

    }

    /**
     * Sends a message.
     *
     * @param message A string of text to send.
     */

    public void sendPit(File message) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        Uri u = Uri.fromFile(message);
        intent.putExtra(Intent.EXTRA_STREAM, u);
        intent.setType("text/plain");
        intent.setPackage("com.android.bluetooth");
        startActivity(Intent.createChooser(intent, "Send pits to... (Select Bluetooth)"));
    }

    public void sendMatch(String fileAsString, String teamNumber, String matchNumber) {

    }

	/*public void undoSave(View view) {
        savedScout = new File(String.valueOf(Environment.getExternalStorageDirectory()) + "/" + settings.getString("folder_name", "FRCScouting") + "/" + lastSavedTeam + ".txt");
		String s = "";
		String data[];
		try {
			FileInputStream fileinputstream = new FileInputStream(savedScout);
			InputStreamReader inputstreamreader = new InputStreamReader(fileinputstream);
			BufferedReader bufferedreader = new BufferedReader(inputstreamreader);
			s = bufferedreader.readLine();
			bufferedreader.close();
			fileinputstream.close();
		} catch (IOException ioexception) {
			Log.getStackTraceString(ioexception);
		}
		data = s.split(",");
		//
	}*/

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_main);
        settings = PreferenceManager.getDefaultSharedPreferences(this);
        scouts = new MainPagerAdapter(getSupportFragmentManager());
        viewPager = (ViewPager) findViewById(R.id.scouts_pager);
        viewPager.setAdapter(scouts);
        tabLayout = (TabLayout) findViewById(R.id.main_tabs);
        tabLayout.setupWithViewPager(viewPager);
        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
        intent = getIntent();
        checkForInput();
        singleton = this;
    }

    private void checkForInput() {
        if (intent.getDataString() != null) {
            Uri u = intent.getData();
            if (u != null) {
                File receivedFile = new File(u.getPath());
                /*String fileName = receivedFile.getName();
                fileName = fileName.replaceAll("[^0-9]", "");
				if (receivedFile.getName().contains(".pit")) {
					File dstFolder = new File(Environment.getExternalStorageDirectory() + "/" + settings.getString("folder_name", "FRCScouting") + "/Team " + fileName);
					if (!dstFolder.exists() && !dstFolder.mkdirs()) {

					}
					File dst = new File(dstFolder, receivedFile.getName());

					try {
						FileInputStream in = new FileInputStream(receivedFile);
						Log.e("Max", "input created");
						FileOutputStream out = new FileOutputStream(dst);

						// Transfer bytes from in to out
						byte[] buf = new byte[1024];
						int len;
						while ((len = in.read(buf)) > 0) {
							out.write(buf, 0, len);
						}
						in.close();
						out.close();
						receivedFile.delete();
						Toast.makeText(this, "Team " + fileName + " imported successfully", Toast.LENGTH_LONG).show();
						finish();
					} catch (IOException e) {
						Log.e("Max", e.getMessage());
					}
				}*/
                try {
                    File f = new File(Environment.getExternalStorageDirectory() + "/" + settings.getString("folder_name", "FRCScouting"));
                    if (!f.isDirectory()) {
                        f.mkdirs();
                    }
                    ZipInputStream zin = new ZipInputStream(new FileInputStream(receivedFile));
                    try {
                        ZipEntry ze;
                        while ((ze = zin.getNextEntry()) != null) {
                            String path = Environment.getExternalStorageDirectory() + "/" + settings.getString("folder_name", "FRCScouting") + ze.getName();
                            if (ze.isDirectory()) {
                                File unzipFile = new File(path);
                                if (!unzipFile.isDirectory()) {
                                    unzipFile.mkdirs();
                                }
                            } else {
                                File output = new File(path);
                                File outputFolder = output.getParentFile();
                                if (!outputFolder.exists() && !outputFolder.mkdirs()) {

                                }
                                FileOutputStream fout = new FileOutputStream(output);
                                try {
                                    for (int c = zin.read(); c != -1; c = zin.read()) {
                                        fout.write(c);
                                    }
                                    zin.closeEntry();
                                } finally {
                                    fout.close();
                                }
                            }
                        }
                    } finally {
                        zin.close();
                        receivedFile.delete();
                        Toast.makeText(this, "Library imported successfully", Toast.LENGTH_LONG).show();
                        finish();
                    }
                } catch (Exception e) {
                    Log.e("Max", "Unzip exception", e);
                }
            }
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuitem) {
        switch (menuitem.getItemId()) {
            default:
                return super.onOptionsItemSelected(menuitem);
            case R.id.settings_menu:
                startActivity(new Intent(this, SettingsActivity.class));
                break;
            case R.id.exit_menu:
                finish();
                break;
            case R.id.search_menu:
                startActivity(new Intent(this, SearchActivity.class));
                break;
            case R.id.export_menu:
                if (export()) {
                    Intent mailIntent = new Intent();
                    mailIntent.setAction(Intent.ACTION_SEND);
                    Uri u = Uri.fromFile(new File(String.valueOf(Environment.getExternalStorageDirectory()) + "/" + settings.getString("folder_name", "FRCScouting") + "/" + exportedFileName));
                    mailIntent.putExtra(Intent.EXTRA_STREAM, u);
                    mailIntent.setType("application/zip");
                    mailIntent.putExtra(Intent.EXTRA_SUBJECT, "Export Scouting Files");
                    mailIntent.putExtra(Intent.EXTRA_TEXT, "The exported data is attached");
                    startActivity(Intent.createChooser(mailIntent, "Send Email"));
                }
                break;
        }

        return true;
    }

    private boolean export() {
        /*File outputFileDir = new File(String.valueOf(Environment.getExternalStorageDirectory()) + "/" + settings.getString("folder_name", "FRCScouting"));
        if (!outputFileDir.exists() && !outputFileDir.mkdirs()){

		}
		File[] inputFiles = outputFileDir.listFiles();
		byte[] buffer = new byte[1024];
		if (inputFiles != null && inputFiles.length > 0) {
			File outputFile = new File(outputFileDir, "export.zip");
			try {
				ZipOutputStream zipOut = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(outputFile)));
				ZipEntry entry = null;
				FileInputStream fIn = null;
				BufferedInputStream bIn;
				for (File file : inputFiles) {
					if (file.isFile()) {
						entry = new ZipEntry(file.getName());
						fIn = new FileInputStream(file);
					} else if (file.isDirectory()) {
						File [] otherFiles = file.listFiles();
						if (otherFiles != null && otherFiles.length > 0) {
							for (File f: otherFiles) {
								entry = new ZipEntry(f.getName());
								fIn = new FileInputStream(f);
							}
						}
					}
					bIn = new BufferedInputStream(fIn, 1024);
					zipOut.putNextEntry(entry);
					int count;
					while ((count = bIn.read(buffer, 0, 1024)) != -1) {
						zipOut.write(buffer, 0, count);
					}
					bIn.close();
				}
				zipOut.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}*/
        final int BUFFER = 2048;
        String sourcePath = String.valueOf(Environment.getExternalStorageDirectory()) + "/" + settings.getString("folder_name", "FRCScouting") + "/data/";
        Time time = new Time();
        time.setToNow();
        exportedFileName = "Export " + time.format("%m-%d-%Y %H:%M:%S") + ".zip";
        String toLocation = String.valueOf(Environment.getExternalStorageDirectory()) + "/" + settings.getString("folder_name", "FRCScouting") + "/" + exportedFileName;
        File sourceFile = new File(sourcePath);
        try {
            BufferedInputStream origin;
            FileOutputStream dest = new FileOutputStream(toLocation);
            ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(dest));
            if (sourceFile.isDirectory()) {
                zipSubFolder(out, sourceFile, sourceFile.getParent().length());
            } else {
                byte data[] = new byte[BUFFER];
                FileInputStream fi = new FileInputStream(sourcePath);
                origin = new BufferedInputStream(fi, BUFFER);
                ZipEntry entry = new ZipEntry(getLastPathComponent(sourcePath));
                out.putNextEntry(entry);
                int count;
                while ((count = origin.read(data, 0, BUFFER)) != -1) {
                    out.write(data, 0, count);
                }
            }
            out.close();
            Toast.makeText(this, "Successfully exported to SDCard", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private void zipSubFolder(ZipOutputStream out, File folder, int basePathLength) throws IOException {
        final int BUFFER = 2048;

        File[] fileList = folder.listFiles();
        BufferedInputStream origin;
        for (File file : fileList) {
            if (file.isDirectory()) {
                zipSubFolder(out, file, basePathLength);
            } else {
                byte data[] = new byte[BUFFER];
                String unmodifiedFilePath = file.getPath();
                String relativePath = unmodifiedFilePath
                        .substring(basePathLength);
                Log.i("ZIP SUBFOLDER", "Relative Path : " + relativePath);
                FileInputStream fi = new FileInputStream(unmodifiedFilePath);
                origin = new BufferedInputStream(fi, BUFFER);
                ZipEntry entry = new ZipEntry(relativePath);
                out.putNextEntry(entry);
                int count;
                while ((count = origin.read(data, 0, BUFFER)) != -1) {
                    out.write(data, 0, count);
                }
                origin.close();
            }
        }
    }

    /*
     * gets the last path component
     *
     * Example: getLastPathComponent("downloads/example/fileToZip");
     * Result: "fileToZip"
     */
    public String getLastPathComponent(String filePath) {
        String[] segments = filePath.split("/");
        return segments[segments.length - 1];
    }


}
