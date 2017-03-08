package com.maxkrass.appreciate.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.maxkrass.appreciate.fragments.TeamFragment;

public class AlliancePagerAdapter extends FragmentStatePagerAdapter {

	public static TeamFragment team1;
	public static TeamFragment team2;
	public static TeamFragment team3;
	public static int[] teamNumbers;
	public String matchNumber;

	public AlliancePagerAdapter(FragmentManager fm, String matchNumber, int... teamNumbers) {
		super(fm);
		AlliancePagerAdapter.teamNumbers = teamNumbers;
		this.matchNumber = matchNumber;
	}

	@Override
	public Fragment getItem(int position) {
		switch (position) {
			case 0:
				return team1 = new TeamFragment(teamNumbers[position], matchNumber);
			case 1:
				return team2 = new TeamFragment(teamNumbers[position], matchNumber);
			case 2:
				return team3 = new TeamFragment(teamNumbers[position], matchNumber);
			default:
				return null;
		}
	}

	@Override
	public int getCount() {
		return 3;
	}

	@Override
	public CharSequence getPageTitle(int position) {
		return "Team " + teamNumbers[position];
	}

	/*public static void saveAlliance() {
		MainActivity.singleton.setLastSavedTeam(teamNumbers[0] + " - " + teamNumbers[1] + " - " + teamNumbers[2]);
		MainActivity.singleton.writeDataToFile(MainActivity.singleton.getLastSavedTeam(), true, team1.getDataString(), team2.getDataString(), team3.getDataString());
		//team1.clearFields();
		//team2.clearFields();
		//team3.clearFields();
		SnackbarManager.show(
				Snackbar.with(MainActivity.singleton)
						.actionColorResource(R.color.accent)
						.actionLabel(R.string.save_snackbar_action)
						.animation(true)
						.colorResource(R.color.grey800)
						.duration(com.nispok.snackbar.Snackbar.SnackbarDuration.LENGTH_LONG)
						.text(R.string.save_snackbar_text)
						.textColorResource(R.color.textColorPrimary)
						.actionListener(new ActionClickListener() {
							public void onActionClicked(Snackbar snackbar) {
								MainActivity.singleton.undoSave(snackbar);
							}
						})
						.eventListener(new EventListener() {
							public void onDismiss(Snackbar snackbar) {
							}

							public void onDismissed(Snackbar snackbar) {
								if (MainActivity.singleton.settings.getBoolean("use_bluetooth", false) && !MainActivity.singleton.settings.getBoolean("is_receiver", false)) {
									MainActivity.singleton.sendViaBluetooth(team1.getDataString());
									MainActivity.singleton.sendViaBluetooth(team2.getDataString());
									MainActivity.singleton.sendViaBluetooth(team3.getDataString());
								}
							}

							public void onShow(Snackbar snackbar) {
							}

							public void onShown(Snackbar snackbar) {
							}
						}));
	}*/
}
