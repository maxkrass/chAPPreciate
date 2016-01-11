package com.maxkrass.appreciate.objects;


/**
 * Max made this for APPreciate on 18.12.2015 for APPreciate.
 */
public class MatchRecord extends Record {


    int autoPoints;
    int totalPoints;
    int matchNumber;
    String autoComment;
    String teleComment;

    public int getMatchNumber() {
        return matchNumber;
    }

    public void setMatchNumber(int matchNumber) {
        this.matchNumber = matchNumber;
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
}
