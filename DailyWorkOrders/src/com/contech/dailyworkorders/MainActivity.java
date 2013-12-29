package com.contech.dailyworkorders;


import java.util.ArrayList;
import java.util.HashMap;


import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.view.View;
import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;



public class MainActivity extends Activity implements NewRoomDialogFragment.NoticeDialogListener{
	
	private static final int REQUEST_CODE = 10;
	
	private ListView listView;
	private ArrayAdapter<Room> adapter;
	private ArrayList<Room> rooms;
	private EditText insuredEdit;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);    
        /*CREATE LISTVIEW*/
        listView = (ListView) findViewById(R.id.listView);
        rooms = ((DailyWorkOrderApplication) this.getApplication()).getRooms();
	    adapter = new ArrayAdapter<Room>(this, android.R.layout.simple_list_item_1, android.R.id.text1, rooms);
	    listView.setAdapter(adapter);    
	    listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, final View view,
					int position, long id) {
				Intent intent = new Intent(view.getContext(), FormActivity.class);
				intent.putExtra("position", position);
				startActivity(intent);
			}

		});

	    Button button = (Button) findViewById(R.id.button_addRoom);	
		button.setOnClickListener(new View.OnClickListener(){
	    	public void onClick(View v){
	    		 showNewRoomDialog();
               
	    	}
	    });
		
		}	
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	super.onActivityResult(requestCode, resultCode, data);
    	 //if (resultCode == RESULT_OK) {
    		 adapter.notifyDataSetChanged();
    	 //}
    }
    
	private void showNewRoomDialog() {
		DialogFragment newFragment = NewRoomDialogFragment.newInstance();
		newFragment.show(getFragmentManager(), "room");
	}
	
	@Override
	public void onNewRoomDialogPositiveClick(DialogFragment dialog,
			String name, String length, String width, String height, HashMap<String, Boolean> formFields) {
			if (name.length() < 1 || width.length() < 1 || height.length() < 1 || length.length() < 1){
				Toast.makeText(this, "Form fields are incomplete", Toast.LENGTH_SHORT).show();
			}
			else{
			rooms.add(new Room(name, length, width, height, formFields));
			((DailyWorkOrderApplication) this.getApplication()).storeRoom();
			adapter.notifyDataSetChanged();
			}
	}
}
