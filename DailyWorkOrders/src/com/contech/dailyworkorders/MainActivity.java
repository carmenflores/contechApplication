package com.contech.dailyworkorders;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;


import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.view.View;
import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;



public class MainActivity extends Activity implements NewRoomDialogFragment.NoticeDialogListener, SubmitDialogFragment.NoticeDialogListener{
	
	private static final int REQUEST_CODE = 10;
	
	private ListView listView;
	private ArrayAdapter<Room> adapter;
	private ArrayList<Room> rooms;
	
	private EditText insuredEdit;
	private EditText jobNumberEdit;
	private EditText crewEdit;
	private EditText hoursEdit;
	private EditText workPerformedEdit;
	
	private CheckBox islockBoxInstalled;
	private EditText lockBoxCode;
	
	private CheckBox isreadingsTaken;
	private CheckBox isphotosTaken;
	private CheckBox isafterHoursEmergency;
	private CheckBox issampleTaken;
	
	private CheckBox carpetSample;
	private CheckBox baseboardSample;
	private CheckBox vinylSample;
	private CheckBox laminateSample;
	private CheckBox engineeredSample;
	private CheckBox underpadSample;
	private CheckBox trimSample;
	private CheckBox hardwoodSample;
	private CheckBox subfloorSample;
	private CheckBox wallSample;
	private CheckBox otherSample;
	private EditText otherSampleText;
	
	
	private RadioGroup RGsource;
	private RadioGroup RGwaterCat;
	private RadioGroup RGclassWat;
	private RadioGroup RGcontentsMan;
	private ArrayList<String> fields;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		fields = new ArrayList<String>();
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
		
	    Button submitButton = (Button) findViewById(R.id.button_submit);	
			submitButton.setOnClickListener(new View.OnClickListener(){
		    	public void onClick(View v){
		    		 fields = new ArrayList<String>();
		    		 
		    		 insuredEdit = (EditText) findViewById(R.id.insured);
		    		 jobNumberEdit = (EditText) findViewById(R.id.jobNumber);
		    		 
		    		 String jobNumber = jobNumberEdit.getText().toString();
		    		 fields.add(jobNumber + " ");
		    		 
		    		 String name = insuredEdit.getText().toString();
		    		 if (name.isEmpty()){
		    			 Toast.makeText(MainActivity.this, "Please enter insured Name", Toast.LENGTH_SHORT).show();
		    		 }else{
			    		 if (name.contains(",")){
			    			 fields.add(name.split(",")[0].trim() + "\n\n");
			    		 }
			    		 else{
			    			 fields.add(name.split(" ")[1].trim() + "\n\n");
			    		 }
		    		 }
		    		 Calendar c = Calendar.getInstance();
		    		 String date = c.getTime().toString();
		    		 String[] dateInfo = date.split(" ");
		    		 date = dateInfo[1] +" "+ dateInfo[2]+", "+ dateInfo[dateInfo.length-1];
		    		 String time = dateInfo[3].split(":")[0]+ " : "+dateInfo[3].split(":")[1];
		    		 fields.add("Daily Work Order - " + date + "   " + time +"\n\n");
		    		 
		    		 RGsource = (RadioGroup) findViewById(R.id.RGsource);
		    		 if (RGsource.getCheckedRadioButtonId()!= -1){
		    			 int id= RGsource.getCheckedRadioButtonId();
		    			 View radioButton = RGsource.findViewById(id);
		    			 int radioId = RGsource.indexOfChild(radioButton);
		    			 RadioButton btn = (RadioButton) RGsource.getChildAt(radioId);
		    			 String selection = (String) btn.getText();
		    			 if (selection.contentEquals("Other")){
		    				 EditText other = (EditText) findViewById(R.id.otherText);
		    				 selection = other.getText().toString();
		    				 
		    			 }
		    			 fields.add("Source " + selection + " / "); 
		    	    }
		    		 
		    		 RGwaterCat = (RadioGroup) findViewById(R.id.RGwaterCat);
		    		 if (RGwaterCat.getCheckedRadioButtonId()!= -1){
		    			 int id= RGwaterCat.getCheckedRadioButtonId();
		    			 View radioButton = RGwaterCat.findViewById(id);
		    			 int radioId = RGwaterCat.indexOfChild(radioButton);
		    			 RadioButton btn = (RadioButton) RGwaterCat.getChildAt(radioId);
		    			 String selection = (String) btn.getText();
		    			 fields.add("Water cat " + selection.split(" ")[0] + " / "); 
		    	    }
		    		 RGclassWat = (RadioGroup) findViewById(R.id.RGclassWat);
		    		 if ( RGclassWat.getCheckedRadioButtonId()!= -1){
		    			 int id=  RGclassWat.getCheckedRadioButtonId();
		    			 View radioButton =  RGclassWat.findViewById(id);
		    			 int radioId =  RGclassWat.indexOfChild(radioButton);
		    			 RadioButton btn = (RadioButton)  RGclassWat.getChildAt(radioId);
		    			 String selection = (String) btn.getText();
		    			 fields.add("Class " + selection + " / "); 
		    	    }
		    		islockBoxInstalled = (CheckBox) findViewById(R.id.lockBoxInstalled);
		    		isreadingsTaken = (CheckBox) findViewById(R.id.readingsTaken);
		    		isphotosTaken = (CheckBox) findViewById(R.id.photosTaken);
		    		isafterHoursEmergency = (CheckBox) findViewById(R.id.afterHoursEmergency);
		    		
		    		if (islockBoxInstalled.isChecked()){
		    			lockBoxCode = (EditText) findViewById(R.id.lockBoxCode);
		    			fields.add("Lock box installed " + lockBoxCode.getText().toString() + " / ");
		    		}
		    		if (isreadingsTaken.isChecked()){
		    			fields.add("Readings taken / " );
		    		}
		    		 if (isphotosTaken.isChecked()){
		    			 fields.add("Photos taken / ");
		    		 }
		    		 
		    		 RGcontentsMan = (RadioGroup) findViewById(R.id.RGcontents);
		    		 if ( RGcontentsMan.getCheckedRadioButtonId()!= -1){
		    			 int id=  RGcontentsMan.getCheckedRadioButtonId();
		    			 View radioButton =  RGcontentsMan.findViewById(id);
		    			 int radioId =  RGcontentsMan.indexOfChild(radioButton);
		    			 RadioButton btn = (RadioButton)  RGcontentsMan.getChildAt(radioId);
		    			 String selection = (String) btn.getText();
		    			 fields.add(selection + " content manipulation /"); 
		    	    }
		    		 crewEdit = (EditText) findViewById(R.id.crewText);
		    		 String crewNum = crewEdit.getText().toString();
		    		 hoursEdit = (EditText) findViewById(R.id.hoursText); 
		    		 String hoursNum = hoursEdit.getText().toString();
		    		 
		    		 
		    		 if (crewNum.isEmpty()){
		    			 Toast.makeText(MainActivity.this, "Please enter number of people in crew ", Toast.LENGTH_SHORT).show();
		    		 }
		    		 else if(crewNum.length() != 0){
		    			 if (hoursNum.isEmpty()){
		    				 Toast.makeText(MainActivity.this, "Please enter hours put in by crew", Toast.LENGTH_SHORT).show();
		    			 }
		    			 else{
		    				 
		    				 fields.add(crewNum + " x " + hoursNum +" hours\n\n");
		    			 }
		    		 }
		    		 workPerformedEdit = (EditText) findViewById(R.id.workText);
		    		 String workPerformed = workPerformedEdit.getText().toString();
		    		 if(workPerformed.length() > 0){
		    			 fields. add("Work Performed: \n\n" + workPerformed + "\n\n");
		    		 }
		    		 
		    		 issampleTaken = (CheckBox) findViewById(R.id.samplesTaken);
		    		 
		    		 carpetSample = (CheckBox) findViewById(R.id.carpetSample);
		    		 baseboardSample = (CheckBox) findViewById(R.id.baseboardSample);
		    		 vinylSample = (CheckBox) findViewById(R.id.vinylSample);
		    		 laminateSample = (CheckBox) findViewById(R.id.laminateSample);
		    	     engineeredSample = (CheckBox) findViewById(R.id.engineeredSample);
		    		 underpadSample = (CheckBox) findViewById(R.id.underpadSample);
		    		 trimSample = (CheckBox) findViewById(R.id.trimSample);
		    		 hardwoodSample = (CheckBox) findViewById(R.id.hardwoodSample);
		    		 subfloorSample = (CheckBox) findViewById(R.id.subfloorSample);
		    		 wallSample = (CheckBox) findViewById(R.id.wallSample);
		    		 otherSample = (CheckBox) findViewById(R.id.otherSample);
		    		 if (issampleTaken.isChecked()){
		    			 fields.add("Samples Taken: \n");
		    			 if (carpetSample.isChecked()){
		    				 fields.add("Carpet \n");
		    			 }
		    			 if (baseboardSample.isChecked()){
		    				 fields.add("Baseboard \n");
		    			 }
		    			 if (vinylSample.isChecked()){
		    				 fields.add("Vinyl Floor \n");
		    			 }
		    			 if (laminateSample.isChecked()){
		    				 fields.add("Laminate Floor \n");
		    				 
		    			 }
		    			 if (engineeredSample.isChecked()){
		    				 fields.add("Engineered Floor \n");
		    				 
		    			 }
		    			 if (underpadSample.isChecked()){
		    				 fields.add("Underpad \n");
		    			 }
		    			 if (trimSample.isChecked()){
		    				 fields.add("Trim \n");
		    			 }
		    			 if (hardwoodSample.isChecked()){
		    				 fields.add("Hardwood \n");
		    			 }
		    			 
		    			 if(subfloorSample.isChecked()){
		    				 fields.add("Sub Floor \n");
		    			 }
		    			 if (wallSample.isChecked()){
		    				 fields.add("Wall Paneling \n");
		    			 }
		    			 
		    			 if(otherSample.isChecked()){
		    				 otherSampleText = (EditText) findViewById(R.id.otherSampleEdit);
		    				 String sample = otherSampleText.getText().toString();
		    				 if (sample.isEmpty()){
		    					 Toast.makeText(MainActivity.this, "Please enter name of other sample", Toast.LENGTH_SHORT).show();
		    				 }
		    				 else{
		    					 fields.add(sample + "\n");
		    				 }
		    			 }
		    		 }
		    		 showSubmitDialog();
	               
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
	
	private void showSubmitDialog() {
		DialogFragment newSubmitFragment = SubmitDialogFragment.newInstance(fields);
		newSubmitFragment.show(getFragmentManager(), "submit");
	}
	
	public void onSubmitDialogPositiveClick(DialogFragment dialog){

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
