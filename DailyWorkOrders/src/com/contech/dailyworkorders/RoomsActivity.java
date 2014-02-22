package com.contech.dailyworkorders;

import java.util.ArrayList;
import android.app.Activity;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;

public class RoomsActivity extends Activity implements FormDialogFragment.NoticeDialogListener, OnChildClickListener{
	private ArrayList<Room> rooms;
	private ExpandableListView expandableList;
	private RoomsExpandableListView adapter;
	private ArrayList<String> specs;
	private String field;
	private Room curRoom;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.rooms_activity);
		//Get rooms
        rooms = ((DailyWorkOrderApplication) this.getApplication()).getRooms();
		/* --- COURSES EXPANDABLE LISTVIEW--- */       
		adapter = new RoomsExpandableListView(this, rooms);
		expandableList = (ExpandableListView) findViewById(R.id.list);
		expandableList.setDividerHeight(2);
		expandableList.setGroupIndicator(null);
		expandableList.setClickable(true);
		adapter.setInflater((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE));
		expandableList.setOnChildClickListener(this);
		expandableList.setAdapter(adapter);
		
		
	}

	@Override
	public boolean onChildClick(ExpandableListView parent, View v,
			int groupPosition, int childPosition, long id) {     
		    curRoom = rooms.get(groupPosition);
		    field = curRoom.getIndex(curRoom.getRoom_specs(), childPosition);
		    showFieldDialog(groupPosition);
		    return true;
	}
	private void showFieldDialog(int position) {
		
		Bundle arg = new Bundle();
		//Send field name and position number to Fragment
		arg.putString("field", field);
		arg.putInt("position", position);
		DialogFragment newFragment = FormDialogFragment.newInstance();
		newFragment.setArguments(arg);
		newFragment.show(getFragmentManager(), "form");
		

	}
	@Override
	public void onFormDialogPositiveClick(DialogFragment dialog, String field,
			String information, int position) {
		String oldinfo = curRoom.getSpec(field);
		curRoom.getRoom_specs().put(field, oldinfo +"\n\t\t\t"+ information);
		((DailyWorkOrderApplication) this.getApplication()).storeRoom();
		adapter.notifyDataSetChanged();
	}
}

