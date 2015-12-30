package com.maxkrass.appreciate;

import android.support.annotation.NonNull;

//TODO replace this class's usages piece by piece with MatchRecord

public class Team implements Comparable<Team> {

	public String teamNumber;
	public Team(String teamNumber) {
		this.teamNumber = teamNumber;
	}

	@Override
	public int compareTo(@NonNull Team another) {
		return this.teamNumber.compareTo(another.teamNumber);
	}

	@Override
	public String toString() {
		return teamNumber;
	}
}
