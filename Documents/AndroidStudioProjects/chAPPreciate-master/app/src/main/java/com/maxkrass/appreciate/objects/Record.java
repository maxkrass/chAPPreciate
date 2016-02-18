package com.maxkrass.appreciate.objects;

import android.support.annotation.NonNull;

import com.orm.SugarRecord;

/**
 * Max made this for APPreciate on 11.01.2016.
 */
public class Record extends SugarRecord implements Comparable<Record> {
	int teamNumber;
	String teamName;

	public Record(int teamNumber, String teamName) {
		this.teamNumber = teamNumber;
		this.teamName = teamName;
	}

	public Record() {
	}

	public int getTeamNumber() {
		return teamNumber;
	}

	public void setTeamNumber(int teamNumber) {
		this.teamNumber = teamNumber;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	@Override
	public int compareTo(@NonNull Record another) {
		if (this.teamNumber < another.teamNumber)
			return -1;
		else if (this.teamNumber == another.teamNumber)
			return 0;
		else return 1;
	}

	@Override
	public boolean equals(Object o) {
		return getId().equals(((Record) o).getId());
	}

}
