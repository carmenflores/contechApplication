package com.contech.dailyworkorders;

import java.util.ArrayList;
import java.util.Arrays;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

public class DailyWorkOrderApplication extends Application{
	private static ArrayList<Room> rooms;
	private SharedPreferences sharedPref;
	Gson gson;
	
	/* Create global variable to hold all rooms for work order*/
	@Override
	public void onCreate(){
		sharedPref = DailyWorkOrderApplication.this.getSharedPreferences(getString(R.string.students_pref_file_key), Context.MODE_PRIVATE);
		String roomJSON = sharedPref.getString("Rooms","");
		
		gson = new Gson();
		
		Room[] roomsArray = gson.fromJson(roomJSON, Room[].class);
		
        if (roomsArray != null)
        	rooms = new ArrayList<Room>(Arrays.asList(roomsArray));
        else
        	rooms = new ArrayList<Room>();
	}
	
	public ArrayList<Room> getRooms(){
		return rooms;
	}
	
	public void clearRooms(){
		rooms.clear();
	}
	
	public void storeRoom(){
    	SharedPreferences.Editor editor = sharedPref.edit();
    	String studentsJSON = gson.toJson(rooms);
		editor.putString("Rooms", studentsJSON);
		editor.commit();
	}
	
}


