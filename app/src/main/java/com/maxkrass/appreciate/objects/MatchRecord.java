package com.maxkrass.appreciate.objects;


import android.support.annotation.NonNull;

import com.orm.SugarRecord;

/**
 * Max made this for APPreciate on 18.12.2015 for APPreciate.
 */
public class MatchRecord extends SugarRecord implements Comparable<MatchRecord> {


    int autoPoints;
    int totalPoints;
    int matchNumber;
    int teamNumber;
    String autoComment;
    String teleComment;

    public int getMatchNumber() {
        return matchNumber;
    }

    public void setMatchNumber(int matchNumber) {
        this.matchNumber = matchNumber;
    }

    public int getTeamNumber() {
        return teamNumber;
    }

    public void setTeamNumber(int teamNumber) {
        this.teamNumber = teamNumber;
    }

    public int getAutoPoints() {
        return autoPoints;
    }

    public void setAutoPoints(int autoPoints) {
        this.autoPoints = autoPoints;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }

    public String getAutoComment() {
        return autoComment;
    }

    public void setAutoComment(String autoComment) {
        this.autoComment = autoComment;
    }

    public String getTeleComment() {
        return teleComment;
    }

    public void setTeleComment(String teleComment) {
        this.teleComment = teleComment;
    }


    public MatchRecord() {

	}

    @Override
    public int compareTo(@NonNull MatchRecord another) {
        if (this.teamNumber < another.teamNumber)
            return -1;
        else if (this.teamNumber == another.teamNumber)
            return 0;
        else return 1;
    }

    @Override
    public boolean equals(Object o) {
        return getId().equals(((MatchRecord) o).getId());
    }
}
