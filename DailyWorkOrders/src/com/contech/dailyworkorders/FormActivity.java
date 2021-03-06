package com.contech.dailyworkorders;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.Spannable;
import android.text.Spanned;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class FormActivity extends Activity implements FormDialogFragment.NoticeDialogListener{
	private ArrayList<Room> rooms;
	private Room curRoom;
	private ArrayAdapter<String> adapter;
	private ListView listView;
	private LinkedHashMap<String, String> roomFields;
	private ArrayList<String> specs;
	private ArrayList<String> toDisplay;
	private String roomName;
	private String test;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.form_activity);
		//Create Listview
		listView = (ListView) findViewById(R.id.listView1);
		//Get rooms
        rooms = ((DailyWorkOrderApplication) this.getApplication()).getRooms();
        
        specs = new ArrayList<String>();
        toDisplay = new ArrayList<String>();
        
        //Get room selected
		Intent intent = getIntent();
		int position = intent.getIntExtra("position", 0);
		curRoom = rooms.get(position);
		
		//Set title to room name
		setTitle(curRoom.toString()+" Form ");
		

		//Create specifications array and array to display
		for (LinkedHashMap.Entry<String, String> entry:curRoom.getRoom_specs().entrySet()){
				specs.add(entry.getKey());
				toDisplay.add(entry.getKey() +"          "+ entry.getValue());
		}

		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, toDisplay);
	    listView.setAdapter(adapter);


	    
	    
	    listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			//Display Selected Field's Dialog
			public void onItemClick(AdapterView<?> parent, final View view,
					int position, long id) {
					showFieldDialog(position);
			}

		});
	    
		
	}
	private void showFieldDialog(int position) {
		
		Bundle arg = new Bundle();
		//Send field name and position number to Fragment
		arg.putString("field", specs.get(position));
		arg.putInt("position", position);
		DialogFragment newFragment = FormDialogFragment.newInstance();
		newFragment.setArguments(arg);
		newFragment.show(getFragmentManager(), "form");
		

	}
	
	@Override
	public void onFormDialogPositiveClick(DialogFragment dialog, String field,
			String information, int position) {
		
		curRoom.getRoom_specs().put(field, information);
		((DailyWorkOrderApplication) this.getApplication()).storeRoom();
		
		//Display information added
		toDisplay.set(position, field +"          "+ information);
		adapter.notifyDataSetChanged();
		

	}
	
	@Override  
	public void onBackPressed()   
	{  
		test= curRoom.toString() + "| |";
		//Create specifications array and array to display
		for (HashMap.Entry<String, String> entry:curRoom.getRoom_specs().entrySet()){
				test += entry.getKey() +" : "+ entry.getValue() + "|";
		}
		test += " |";
		roomName = curRoom.toString();

		Intent intent = new Intent(this, MainActivity.class);
		Bundle b = new Bundle();
		b.putString("prevRoomLookedAt", roomName);
		b.putString(roomName, test);
		intent.putExtras(b);
		startActivity(intent);
		
		FormActivity.super.onBackPressed();
	}	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	    // Respond to the action bar's Up/Home button
	    case android.R.id.home:
			test= curRoom.toString() + "| |";
			//Create specifications array and array to display
			for (HashMap.Entry<String, String> entry:curRoom.getRoom_specs().entrySet()){
					test += entry.getKey() +" : "+ entry.getValue() + "|";
			}
			test += " |";
			roomName = curRoom.toString();
			
			Intent intent = new Intent(this, MainActivity.class);
			Bundle b = new Bundle();
			b.putString("prevRoomLookedAt", roomName);
			b.putString(roomName, test);
			intent.putExtras(b);
			startActivity(intent);
						
			}
	    return super.onOptionsItemSelected(item);
	}
	
}
