package com.maxkrass.appreciate.objects;

import com.orm.SugarRecord;

import java.util.List;

/**
 * Created by Sarah, Calyx, and Tim... and Max:) on 12/8/15.
 */
public class PitRecord extends Record {
	int maxSpeed;
	String mainComment;
	String teleComment;
	String autoComment;
	String abilitiesComment;

	


	int cimNumSpinner;
	int driveSpinner;
	int highestPossibleShotSpinner;
	int wheelNumSpinner;
	int wheelTypeSpinner;

	public PitRecord() {
	}

	@Override
	public void setTeamNumber(int teamNumber) {
		super.setTeamNumber(teamNumber);
		List<PitRecord> pitRecords = SugarRecord.find(PitRecord.class, "team_number = ?", String.valueOf(teamNumber));
		if (pitRecords.size() == 1)
			super.setTeamName(pitRecords.get(0).getTeamName());
	}

	public int getMaxSpeed() {

		return maxSpeed;
	}

	public void setMaxSpeed(int maxSpeed) {
		this.maxSpeed = maxSpeed;
	}

	public String getMainComment() {
		return mainComment;
	}

	public void setMainComment(String mainComment) {
		this.mainComment = mainComment;
	}

	public String getTeleComment() {
		return teleComment;
	}

	public void setTeleComment(String teleComment) {
		this.teleComment = teleComment;
	}

	public String getAutoComment() {
		return autoComment;
	}

	public void setAutoComment(String autoComment) {
		this.autoComment = autoComment;
	}

	public String getAbilitiesComment() {
		return abilitiesComment;
	}

	public void setAbilitiesComment(String abilitiesComment) {
		this.abilitiesComment = abilitiesComment;
	}
	
	



	public int getCimNumSpinner() {
		return cimNumSpinner;
	}

	public void setCimNumSpinner(int cimNumSpinner) {
		this.cimNumSpinner = cimNumSpinner;
	}

	public int getDriveSpinner() {
		return driveSpinner;
	}

	public void setDriveSpinner(int driveSpinner) {
		this.driveSpinner = driveSpinner;
	}

	public int getHighestPossibleStackSpinner() {
		return highestPossibleStackSpinner;
	}

	public void setHighestPossibleStackSpinner(int highestPossibleStackSpinner) {
		this.highestPossibleStackSpinner = highestPossibleStackSpinner;
	}

	public int getWheelNumSpinner() {
		return wheelNumSpinner;
	}

	public void setWheelNumSpinner(int wheelNumSpinner) {
		this.wheelNumSpinner = wheelNumSpinner;
	}

	public int getWheelTypeSpinner() {
		return wheelTypeSpinner;
	}

	public void setWheelTypeSpinner(int wheelTypeSpinner) {
		this.wheelTypeSpinner = wheelTypeSpinner;
	}
}
