package com.maxkrass.appreciate.activities;

import android.app.DialogFragment;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothClass;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.maxkrass.appreciate.R;
import com.maxkrass.appreciate.views.SlidingTabLayout;
import com.maxkrass.appreciate.adapter.MainPagerAdapter;
import com.maxkrass.appreciate.fragments.CreateMatchDialog;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.UUID;

public class MainActivity extends ActionBarActivity {

	private String lastSavedTeam = "";
	int REQUEST_ENABLE_BT = 1;

	FileOutputStream fOut;
	File scoutFolder;
	File localScoutFolder;
	File newScout;
	File savedScout;

	MainPagerAdapter scouts;
	SlidingTabLayout tabLayout;
	ViewPager viewPager;

	public SharedPreferences settings;

	BluetoothSocket btSocket;
	UUID uuid;
	InputStream receivingStream;
	OutputStream sendingStream;
	Thread receivingThread;

	BroadcastReceiver broadcastReceiver;
	BluetoothAdapter bluetoothAdapter;

	FloatingActionsMenu fam;

	boolean stopReceiving;
	byte[] resultBytes;

	public static MainActivity singleton;

	public String getLastSavedTeam() {
		return lastSavedTeam;
	}

	public void setLastSavedTeam(String lastSavedTeam) {
		this.lastSavedTeam = lastSavedTeam;
	}

	public void sendViaBluetooth(String data) {
		try {
			if (sendingStream != null) {
				sendingStream.write(data.getBytes("US-ASCII"));
			} else {
				setupBluetoothSending();
				sendViaBluetooth(data);
			}
			Toast.makeText(this, lastSavedTeam + " scout sent", Toast.LENGTH_LONG).show();
		} catch (IOException e) {
			e.printStackTrace();
			Log.e("Max - sendViaBluetooth, wrong Charset", data);
		}

	}

	void setupBluetooth() throws IOException {
		if (settings.getBoolean("use_bluetooth", false)) {              //if the bluetooth setting is on
			bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
			if (bluetoothAdapter == null) {                             //if there is no bluetooth adapter
				Toast.makeText(getApplicationContext(),
						"Device doesn't support Bluetooth",
						Toast.LENGTH_LONG).show();
				settings.edit().putBoolean("use_bluetooth", false).apply();     //turn the bluetooth setting off
			} else {
				if (!bluetoothAdapter.isEnabled()) {                    //if bluetooth is turned off
					Log.e("Max", "Requesting bluetooth");
					Intent turnOn = new Intent("android.bluetooth.adapter.action.REQUEST_ENABLE");
					startActivityForResult(turnOn, REQUEST_ENABLE_BT);  //request user to turn bluetooth on
					Log.e("Max", "Bluetooth requested");
				}
				uuid = UUID.fromString("d0903867-b0b4-4052-b0da-d066be10414b");
				if (settings.getBoolean("is_receiver", false)) {
					setupBluetoothReceiving();
				} else {
					setupBluetoothSending();
				}
			}
		}
	}

	private void setupBluetoothReceiving() throws IOException {
		if (bluetoothAdapter.getScanMode() != BluetoothAdapter.SCAN_MODE_CONNECTABLE_DISCOVERABLE) {
			Log.e("Max", "Requesting visibility");
			Intent infiniteVisible = new Intent("android.bluetooth.adapter.action.REQUEST_DISCOVERABLE");
			infiniteVisible.putExtra("android.bluetooth.adapter.extra.DISCOVERABLE_DURATION", 0);
			startActivity(infiniteVisible);             //request setting the discoverable for ever
			Log.e("Max", "Visibility requested");
		}
		final BluetoothServerSocket btServerSocket = bluetoothAdapter.listenUsingRfcommWithServiceRecord("APPreciate", uuid);
		Log.e("Max", "ServerSocket created");
		Thread acceptThread = new Thread(new Runnable() {
			@Override
			public void run() {
				BluetoothSocket socket = null;
				while (true) {
					Log.e("Max", "In the while loop of acceptThread");
					try {
						Log.e("Max", "Accepting");
						socket = btServerSocket.accept();
						Log.e("Max", "ServerSocket accepted");
					} catch (IOException e) {
						Log.e("Max", "Caught the accept");
					}
					if (socket != null) {
						stopReceiving = false;
						resultBytes = new byte[1024];
						try {
							receivingStream = socket.getInputStream();
						} catch (IOException e) {
							e.printStackTrace();
							break;
						}
						receivingThread = new Thread(new Runnable() {
							@Override
							public void run() {
								Log.e("Max", "Entering loop");
								while (!Thread.currentThread().isInterrupted() && !stopReceiving) {
									try {
										int bytesAvailable = receivingStream.available();
										if (bytesAvailable > 0) {
											byte[] packetBytes = new byte[bytesAvailable];
											int read = receivingStream.read(packetBytes);
											String data = new String(packetBytes, "US_ASCII");
											Log.e("Max", data + " " + read);
											writeDataToFile(data.substring(0, 3), false, data);
											Toast.makeText(getApplicationContext(), "File received and saved", Toast.LENGTH_LONG).show();
										}
									} catch (IOException e) {
										stopReceiving = true;
										break;
									}
								}
							}
						});
						receivingThread.start();
					}
				}
			}
		});
		acceptThread.start();
		Log.e("Max", "acceptThread started");
	}

	private void setupBluetoothSending() throws IOException {
		broadcastReceiver = new BroadcastReceiver() {
			public void onReceive(Context context, Intent intent) {
				if (BluetoothDevice.ACTION_FOUND.equals(intent.getAction())) {
					final BluetoothDevice bluetoothDevice = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
					if (BluetoothClass.Device.PHONE_SMART == bluetoothDevice.getBluetoothClass().getDeviceClass()) {
						bluetoothAdapter.cancelDiscovery();
						try {
							btSocket = bluetoothDevice.createRfcommSocketToServiceRecord(uuid);
							Log.e("Max", "Socket created");
							btSocket.connect();
							Log.e("Max", "Connected");
							sendingStream = btSocket.getOutputStream();
							Log.e("Max", "OutputStream created");
						} catch (IOException e) {
							e.printStackTrace();
							Log.e("Max", "Connect failed");
						}
					} else {
						Log.e("Max", "Not a smart phone");
					}
				}
			}
		};
		IntentFilter intentfilter = new IntentFilter("android.bluetooth.device.action.FOUND");
		registerReceiver(broadcastReceiver, intentfilter);
		Log.e("Max", "broadcastReceiver started");
		bluetoothAdapter.startDiscovery();
		Log.e("Max", "Discovery started");
	}

	public void writeDataToFile(String team, boolean localData, String... s) {
		localScoutFolder = new File(String.valueOf(Environment.getExternalStorageDirectory()) + "/" + settings.getString("folder_name", "FRCScouting") + "/" + (localData ? "local" : "received"));
		if (!localScoutFolder.exists() && !localScoutFolder.mkdir()) {
			Log.w("File directory", "Failed to create directory");
		}
		File file1 = localScoutFolder;
		newScout = new File(file1, team + ".xml");
		try {
			fOut = new FileOutputStream(newScout);
			for (int i = 1; i < s.length; i++){
				s[i] = "\n" + s[i];
			}
			for (String string : s) {
				fOut.write(string.getBytes());
			}
			fOut.close();
		} catch (IOException ioexception) {
			ioexception.printStackTrace();
		}
	}

	public void createNewPitScout(View view) {
		startActivity(new Intent(this, PitScout.class));
		fam.collapse();
	}

	protected void onActivityResult(int i, int j, Intent intent) {
		if (i == REQUEST_ENABLE_BT) {
			if (j == -1) {
				Toast.makeText(getApplicationContext(), "Bluetooth is now Enabled", Toast.LENGTH_LONG).show();
			}
			if (j == 0) {
				Toast.makeText(getApplicationContext(), "Error occurred while enabling. Leaving the application..", Toast.LENGTH_LONG).show();
			}
		}
	}

	public void undoSave(View view) {
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
		//TODO putDataIntoFields(data);
	}

	public void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		setContentView(R.layout.activity_main);
		settings = PreferenceManager.getDefaultSharedPreferences(this);
		scouts = new MainPagerAdapter(getSupportFragmentManager());
		viewPager = (ViewPager) findViewById(R.id.scouts_pager);
		viewPager.setAdapter(scouts);
		tabLayout = (SlidingTabLayout) findViewById(R.id.main_tabs);
		tabLayout.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
			@Override
			public int getIndicatorColor(int position) {
				return 0xfff44336;
			}
		});
		tabLayout.setViewPager(viewPager);
		Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);
		fam = (FloatingActionsMenu) findViewById(R.id.view);
		setSupportActionBar(toolbar);
		try {
			setupBluetooth();
		} catch (IOException e) {
			e.printStackTrace();
		}
		singleton = this;
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
		}

		return true;
	}

	protected void onDestroy() {
		super.onDestroy();
		if (settings.getBoolean("use_bluetooth", false) && !settings.getBoolean("is_receiver", false))
			unregisterReceiver(broadcastReceiver);
	}

	public void createNewMatchScout(View view) {
		DialogFragment createDialog = new CreateMatchDialog();
		createDialog.show(getFragmentManager(), "teams");
		fam.collapse();
	}
}
