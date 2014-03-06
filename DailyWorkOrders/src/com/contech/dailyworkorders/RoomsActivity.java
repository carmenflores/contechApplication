package com.contech.dailyworkorders;

import java.util.ArrayList;
import android.app.Activity;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.app.ExpandableListActivity;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.ExpandableListContextMenuInfo;
import android.widget.AdapterView.OnItemLongClickListener;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.Toast;

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
		expandableList.setOnItemLongClickListener(new OnItemLongClickListener() {

		      @Override
		      public boolean onItemLongClick(AdapterView<?> parent, View view,
		          int position, long id) {
		    	  
		    	  ExpandableListView listView = (ExpandableListView) parent;
		    	  long pos = listView.getExpandableListPosition(position);

		    	  // get type and correct positions
		    	  int itemType = ExpandableListView.getPackedPositionType(pos);
		    	  int groupPos = ExpandableListView.getPackedPositionGroup(pos);
		    	  int childPos = ExpandableListView.getPackedPositionChild(pos);
		    	  //  GROUP-item clicked
		    	    if (itemType == ExpandableListView.PACKED_POSITION_TYPE_GROUP) {
		    	        //  ...
		    	        onGroupLongClick(pos, groupPos);
		    	    }
		    	    //  CHILD-item clicked
		    	    else if (itemType == ExpandableListView.PACKED_POSITION_TYPE_CHILD) {
		    	        //  ...
				        Toast.makeText(RoomsActivity.this,
					            "Item in position " + position + " clicked yo",
					            Toast.LENGTH_LONG).show();		    	    }

		        // Return true to consume the click event. In this case the
		        // onListItemClick listener is not called anymore.
		        return true;
		      }


		    });
		
		
	}

	public boolean onGroupLongClick(long pos, int groupPos) {     
        Toast.makeText(RoomsActivity.this,
	            "Item in position " + pos + " clicked, group position: " + groupPos,
	            Toast.LENGTH_LONG).show();		    	    

		    return true;
	}


	@Override
	public boolean onChildClick(ExpandableListView parent, View v,
			int groupPosition, int childPosition, long id) {     
		    curRoom = rooms.get(groupPosition);
		    field = curRoom.getIndex(curRoom.getRoom_specs(), childPosition);
		    showFieldDialog(groupPosition);
		    return true;
	}
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
	        ContextMenuInfo menuInfo) {
	    super.onCreateContextMenu(menu, v, menuInfo);
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.room_group_menu, menu);
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

