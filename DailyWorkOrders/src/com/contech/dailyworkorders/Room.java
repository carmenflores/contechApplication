package com.contech.dailyworkorders;

import java.util.HashMap;
import java.util.Set;

public class Room {
	
	private String name, length, width, height;
	private HashMap<String, String> room_specs;
	
	//Room Constructor
	public Room(String roomName, String roomLength, String roomWidth, String roomHeight, HashMap<String, Boolean> requiredFields) {
		
		name = roomName;
		length = roomLength;
		width = roomWidth;
		height= roomHeight;
		room_specs = new HashMap<String,String>();
		
		for (HashMap.Entry<String, Boolean> entry:requiredFields.entrySet()){
			if (entry.getValue()){
				room_specs.put(entry.getKey(), "");
			}
		}
	}
		
	public void setSpec(String field, String value){
		room_specs.put(field, value);
	}
	
	public HashMap<String,String> getRoom_specs(){
		return room_specs;
	}
	
	public String getSpec(String field){
		return room_specs.get(field);
	}
	
	@Override
	public String toString() {
		return name;
	}
	
}
