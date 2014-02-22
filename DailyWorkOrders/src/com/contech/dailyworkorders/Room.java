package com.contech.dailyworkorders;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.ArrayList;

public class Room {
	
	private String name, length, width, height;
	private LinkedHashMap<String, String> room_specs;
	
	//Room Constructor
	public Room(String roomName, String roomLength, String roomWidth, String roomHeight, HashMap<String, Boolean> requiredFields) {
		
		name = roomName;
		length = roomLength;
		width = roomWidth;
		height= roomHeight;
		room_specs = new LinkedHashMap<String,String>();
		
		for (Map.Entry<String, Boolean> entry:requiredFields.entrySet()){
			if (entry.getValue()){
				room_specs.put(entry.getKey(), "");
			}
		}
	}
		
	public void setSpec(String field, String value){
		room_specs.put(field, value);
	}
	
	public LinkedHashMap<String,String> getRoom_specs(){
		return room_specs;
	}
	public String getByIndex(LinkedHashMap<String, String> hMap, int index){
		   String Title = (String) hMap.keySet().toArray()[index];
		   String Content = (String) hMap.values().toArray()[index];
		   if (Content.contentEquals("")){
			   return Title;
		   }
		   else{
			   return Title+":"+"\n\t\t\t"+ Content;
		   }
	}
	public String getIndex(LinkedHashMap<String, String> hMap, int index){
		   String Title = (String) hMap.keySet().toArray()[index];
		   return Title;

	}
	
	
	
	public String getSpec(String field){
		return room_specs.get(field);
	}
	
	@Override
	public String toString() {
		return name;
	}
	
}
