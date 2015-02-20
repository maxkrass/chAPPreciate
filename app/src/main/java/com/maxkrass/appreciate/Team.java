package com.maxkrass.appreciate;

import android.support.annotation.NonNull;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;

public class Team implements Comparable<Team> {

	public String teamNumber;
	static FileInputStream fis;
	static InputStreamReader isr;
	static char[] inputBuffer;
	static String data;

	public static ArrayList<String> getTextFromFile(File teamFile) {
		try {
			fis = new FileInputStream(teamFile);
			isr = new InputStreamReader(fis);
			inputBuffer = new char[fis.available()];
			isr.read(inputBuffer);
			data = new String(inputBuffer);
			isr.close();
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		data = data.replaceAll("\\n|\\t", "");
		XmlPullParserFactory factory = null;
		try {
			factory = XmlPullParserFactory.newInstance();
		} catch (XmlPullParserException e2) {
			e2.printStackTrace();
		}
		factory.setNamespaceAware(true);
		XmlPullParser xpp = null;
		try {
			xpp = factory.newPullParser();
		} catch (XmlPullParserException e2) {
			e2.printStackTrace();
		}
		try {
			xpp.setInput(new StringReader(data));
		} catch (XmlPullParserException e1) {
			e1.printStackTrace();
		}
		int eventType = 0;
		try {
			eventType = xpp.getEventType();
		} catch (XmlPullParserException e1) {
			e1.printStackTrace();
		}
		ArrayList<String> userData = new ArrayList<>();
		while (eventType != XmlPullParser.END_DOCUMENT) {
			if (eventType == XmlPullParser.START_DOCUMENT) {
				System.out.println("Start document");
			} else if (eventType == XmlPullParser.START_TAG) {
				System.out.println("Start tag " + xpp.getName());
			} else if (eventType == XmlPullParser.END_TAG) {
				System.out.println("End tag " + xpp.getName());
			} else if (eventType == XmlPullParser.TEXT) {
				userData.add(xpp.getText());
			}
			try {
				eventType = xpp.next();
			} catch (XmlPullParserException | IOException e) {
				e.printStackTrace();
			}
		}
		return userData;
	}

	public static Team getTeamFromFile(File teamFile) {
		ArrayList<String> userData = getTextFromFile(teamFile);
		return new Team(userData.get(0) + (userData.get(1).equals(" ") ? "" : (": " + userData.get(1))));
	}

	public Team(String teamNumber) {
		this.teamNumber = teamNumber;
	}

	@Override
	public int compareTo(@NonNull Team another) {
		return this.teamNumber.compareTo(another.teamNumber);
	}
}
