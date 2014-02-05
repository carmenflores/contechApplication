package com.contech.dailyworkorders;


import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;


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
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;



public class MainActivity extends Activity implements NewRoomDialogFragment.NoticeDialogListener, SubmitDialogFragment.NoticeDialogListener, FileLoaderFragment.NoticeDialogListener {
	
	private static final int REQUEST_CODE = 10;
	
	private ListView listView;
	private ArrayAdapter<Room> adapter;
	private ArrayList<Room> rooms;
	
	public EditText insuredEdit;
	public EditText jobNumberEdit;
	public EditText addressEdit;
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
	
	private CheckBox isEquipmentPick;
	private CheckBox isEquipmentInstalled;
	private CheckBox isFinalReadingsTaken;
	
	private EditText turbo;
	private EditText axial;
	private EditText dehumidifiers;
	private EditText lgr;
	private EditText evolution;
	private EditText airScrubbers;
	private EditText otherEquipment;
	
	private EditText garbageBags;
	private EditText boxes;
	private EditText plastic;
	private EditText masks;
	private EditText suits;
	private EditText gloves;
	private EditText rags;
	private EditText floorRunners;
	private EditText otherMaterials;
	
	private RadioGroup RGgarbage;
	private RadioGroup RGgtruck;
	
	private EditText notes;
	
	private EditText contentsReturned;
	private RadioGroup RGsource;
	private RadioGroup RGwaterCat;
	private RadioGroup RGclassWat;
	private RadioGroup RGcontentsMan;
	
	private String subject;
	private String path;
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
	    

	    Intent textIntent = getIntent();
	    Uri data = textIntent.getData();
	    if (data != null){
			if (textIntent.getType().equals("text/plain")) {
				path = data.toString();
	            int start= path.indexOf("/mnt");
	            path=path.substring(start);
	            showFileLoaderDialog();
			}
	            
	    }
	    
	    
	    Button button = (Button) findViewById(R.id.button_addRoom);	
		button.setOnClickListener(new View.OnClickListener(){
	    	public void onClick(View v){
	    		 showNewRoomDialog();
               
	    	}
	    });
		insuredEdit = (EditText) findViewById(R.id.insured);
		jobNumberEdit = (EditText) findViewById(R.id.jobNumber);
		addressEdit = (EditText) findViewById(R.id.address);
		Button map_button = (Button) findViewById(R.id.display_map);
		map_button.setOnClickListener(new View.OnClickListener(){
			public void onClick(View v){
				
				double latitude =0;
				double longitude=0;
				String address_m= addressEdit.getText().toString();
				if ( address_m.isEmpty()){
					address_m="";
				}
				Toast.makeText(MainActivity.this, address_m, Toast.LENGTH_SHORT).show();
				
				Geocoder coder = new Geocoder(MainActivity.this, Locale.CANADA);
				List<Address> addresses;
				try{
					addresses=coder.getFromLocationName(address_m, 1);
					if  (addresses.size() >0){
						Address addr = addresses.get(0);
						latitude=addr.getLatitude();
						longitude=addr.getLongitude();
					}
	
				}catch (Exception e){
					
				}finally{
					
				}
				
		        String uri = String.format(Locale.ENGLISH, "http://maps.google.com/maps?&daddr=%f,%f (%s)", latitude, longitude,"Insured Location");
		        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
		        intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
		        try
		        {
		            startActivity(intent);
		        }
		        catch(ActivityNotFoundException ex)
		        {
		            try
		            {
		                Intent unrestrictedIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
		                startActivity(unrestrictedIntent);
		            }
		            catch(ActivityNotFoundException innerEx)
		            {
		            }
		        }
				
				}
		});
		
		RGgarbage = (RadioGroup) findViewById(R.id.RGgarbage);
		RGgarbage.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (RGgarbage.getCheckedRadioButtonId()!=-1){
					RGgarbage.clearCheck();
				}
			}
		});
	    Button submitButton = (Button) findViewById(R.id.button_submit);	
			submitButton.setOnClickListener(new View.OnClickListener(){
		    	public void onClick(View v){
		    		 fields = new ArrayList<String>();
		    		 subject ="";

		    		 String jobNumber = jobNumberEdit.getText().toString();
		    		 subject += jobNumber+ " ";
		    		 fields.add(jobNumber + " ");
		    		 
		    		 String name = insuredEdit.getText().toString();
		    		 if (name.isEmpty()){
		    			 Toast.makeText(MainActivity.this, "Please enter insured Name", Toast.LENGTH_SHORT).show();
		    		 }else{
		    			 subject += name; 
			    		 if (name.contains(",")){
			    			 fields.add(name.split(",")[0].trim() + name.split(",")[1].trim());
			    		 }
			    		 else{
			    			 fields.add(name);
			    		 }
			    		 String addr = addressEdit.getText().toString();
			    		 if (addr.length() > 0){
			    			 fields.add("\n" +addr + "\n\n");
			    		 }
			    		 else{
			    			 fields.add("\n\n");
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
		    				 fields.add(" \t Carpet \n");
		    			 }
		    			 if (baseboardSample.isChecked()){
		    				 fields.add("\t Baseboard \n");
		    			 }
		    			 if (vinylSample.isChecked()){
		    				 fields.add("\t Vinyl Floor \n");
		    			 }
		    			 if (laminateSample.isChecked()){
		    				 fields.add("\t Laminate Floor \n");
		    				 
		    			 }
		    			 if (engineeredSample.isChecked()){
		    				 fields.add("\t Engineered Floor \n");
		    				 
		    			 }
		    			 if (underpadSample.isChecked()){
		    				 fields.add("\t Underpad \n");
		    			 }
		    			 if (trimSample.isChecked()){
		    				 fields.add("\t Trim \n");
		    			 }
		    			 if (hardwoodSample.isChecked()){
		    				 fields.add("\t Hardwood \n");
		    			 }
		    			 
		    			 if(subfloorSample.isChecked()){
		    				 fields.add("\t Sub Floor \n");
		    			 }
		    			 if (wallSample.isChecked()){
		    				 fields.add("\t Wall Paneling \n");
		    			 }
		    			 
		    			 if(otherSample.isChecked()){
		    				 otherSampleText = (EditText) findViewById(R.id.otherSampleEdit);
		    				 String sample = otherSampleText.getText().toString();
		    				 if (sample.isEmpty()){
		    					 Toast.makeText(MainActivity.this, "Please enter name of other sample", Toast.LENGTH_SHORT).show();
		    				 }
		    				 else{
		    					 fields.add("\t" + sample + "\n");
		    				 }
		    			 }
		    		 }
		    			contentsReturned = (EditText)findViewById(R.id.contentsRet);
		    			String content = contentsReturned.getText().toString();
                        if (content.length() > 0){
                                fields.add("\n" + "Contents Returned to Shop:" + "\n\t" + content + "\n\n");
                        }
		    			
                    
                     isEquipmentPick = (CheckBox) findViewById(R.id.equipmentPickedUp);
    		    	 isEquipmentInstalled = (CheckBox) findViewById(R.id.equipmentInstalled);
		    		 isFinalReadingsTaken = (CheckBox) findViewById(R.id.finalReadingsTaken);
		    		 if (isEquipmentPick.isChecked() || isEquipmentInstalled.isChecked() || isFinalReadingsTaken.isChecked()){ 
	                     String equipString = "";  
		    			 if (isEquipmentPick.isChecked()){
	   		    			 equipString += "Equipment picked up, ";
	   		    		 }
			    		 if (isEquipmentInstalled.isChecked()){
			    			 equipString += "Equipment installed, ";
			    		 }
			    		 if (isFinalReadingsTaken.isChecked()){
			    			 equipString += "Final readings taken, ";
			    		 }
			    		
			    		 /* remove last two characters */
			    		 equipString = equipString.substring(0, equipString.length()-2);
			    		 /* add colon */
			    		 equipString += ": \n\t";
			    				 
					     turbo = (EditText) findViewById(R.id.turbo);
			    		 String turboString = turbo.getText().toString();
			    		 if(turboString.length() > 0){
			    			 equipString += turboString + "x turbo / ";
			    		 }	 
			    		 axial = (EditText) findViewById(R.id.axial);
			    		 String axialString = axial.getText().toString();
			    		 if(axialString.length() > 0){
			    			 equipString += axialString + "x axial / ";
			    		 }	 
			    		 dehumidifiers = (EditText) findViewById(R.id.dehumidifiers);
			    		 String dehumidifiersString = dehumidifiers.getText().toString();
			    		 if(dehumidifiersString.length() > 0){
			    			 equipString += dehumidifiersString + "x axial / ";
			    		 }	 
			    		 lgr = (EditText) findViewById(R.id.lgr);
			    		 String lgrString = lgr.getText().toString();
			    		 if(lgrString.length() > 0){
			    			 equipString += lgrString + "x LGR / ";
			    		 }	
			    		 evolution = (EditText) findViewById(R.id.evolution);
			    		 String evolutionString = evolution.getText().toString();
			    		 if(evolutionString.length() > 0){
			    			 equipString += evolutionString + "x evolution / ";
			    		 }	
			    		 airScrubbers = (EditText) findViewById(R.id.scrubbers);
			    		 String airScrubbersString = airScrubbers.getText().toString();
			    		 if(airScrubbersString.length() > 0){
			    			 equipString += airScrubbersString + "x air scrubbers / ";
			    		 }	
			      		 otherEquipment = (EditText) findViewById(R.id.otherEquipment);
			    		 String otherEquipmentString = otherEquipment.getText().toString();
			    		 if(otherEquipmentString.length() > 0){
			    			 equipString += otherEquipmentString + "/ ";
			    		 }	
			    		 
			    		 equipString = equipString.substring(0, equipString.length()-2);
                         fields.add(equipString + "\n\n");

		    		 }
		    		 	
				     garbageBags = (EditText) findViewById(R.id.garbageSection);
		    		 String garbageBagsString = garbageBags.getText().toString();

				     boxes = (EditText) findViewById(R.id.boxesSection);
		    		 String boxesString = boxes.getText().toString();

				     plastic = (EditText) findViewById(R.id.plasticSection);
		    		 String plasticString = plastic.getText().toString();

				     masks = (EditText) findViewById(R.id.masksSection);
		    		 String masksString = masks.getText().toString();

				     suits = (EditText) findViewById(R.id.suitsSection);
		    		 String suitsString = suits.getText().toString();

				     gloves = (EditText) findViewById(R.id.glovesSection);
		    		 String glovesString = gloves.getText().toString();

				     rags = (EditText) findViewById(R.id.ragsSection);
		    		 String ragsString = rags.getText().toString();

				     floorRunners = (EditText) findViewById(R.id.floorRunnerSection);
		    		 String floorRunnersString = floorRunners.getText().toString();

				     otherMaterials = (EditText) findViewById(R.id.otherMaterialSection);
		    		 String otherMaterialsString = otherMaterials.getText().toString();

		    		 if(garbageBagsString.length() > 0 || boxesString.length() > 0 || plasticString.length() > 0 || 
		    				 masksString.length() > 0 || suitsString.length() > 0 || glovesString.length() > 0 ||
		    				 ragsString.length() > 0 || floorRunnersString.length() > 0 || otherMaterialsString.length() > 0 ){
	                     String materialsString = "Materials used: ";  
			    		 if(garbageBagsString.length() > 0){
			    			 materialsString += garbageBagsString + "x garbage bags / ";
			    		 }	
			    		 if(boxesString.length() > 0){
			    			 materialsString += boxesString + "x boxes / ";
			    		 }	
			    		 if(plasticString.length() > 0){
			    			 materialsString += plasticString + "x plastic / ";
			    		 }	
			    		 if(masksString.length() > 0){
			    			 materialsString += masksString + "x masks / ";
			    		 }	
			    		 if(suitsString.length() > 0){
			    			 materialsString += suitsString + "x suits / ";
			    		 }	
			    		 if(glovesString.length() > 0){
			    			 materialsString += glovesString + "x gloves / ";
			    		 }
			    		 if(ragsString.length() > 0){
			    			 materialsString += ragsString + "x rags / ";
			    		 }
			    		 if(floorRunnersString.length() > 0){
			    			 materialsString += floorRunnersString + "x floor runners / ";
			    		 }
			    		 if(otherMaterialsString.length() > 0){
			    			 materialsString += otherMaterialsString + "/ ";
			    		 }
			    		 materialsString = materialsString.substring(0, materialsString.length()-2);
                         fields.add(materialsString + "\n\n");
		    		 }
		    		 
		    		 /* = (RadioGroup) findViewById(R.id.);*/
		    		 if ( RGgarbage.getCheckedRadioButtonId()!= -1){
		    			 int id=  RGgarbage.getCheckedRadioButtonId();
		    			 View radioButton =  RGgarbage.findViewById(id);
		    			 int radioId =  RGgarbage.indexOfChild(radioButton);
		    			 RadioButton btn = (RadioButton)  RGgarbage.getChildAt(radioId);
		    			 String selection = (String) btn.getText();
		    			 fields.add("Garbage disposed: "+ selection + "\n\n"); 
		    	    }
		    		 
		    		 RGgtruck = (RadioGroup) findViewById(R.id.RGgtruck);
		    		 if ( RGgtruck.getCheckedRadioButtonId()!= -1){
		    			 int id=  RGgtruck.getCheckedRadioButtonId();
		    			 View radioButton =  RGgtruck.findViewById(id);
		    			 int radioId =  RGgtruck.indexOfChild(radioButton);
		    			 RadioButton btn = (RadioButton)  RGgtruck.getChildAt(radioId);
		    			 String selection = (String) btn.getText();
		    			 fields.add("Truck used: "+ selection + "\n\n"); 
		    	    }
		    		 
		    		 notes = (EditText) findViewById(R.id.noteSection);
		    		 String notesString = notes.getText().toString();
		    		 if(notesString.length() > 0){
		    			 fields.add("Instructions/Notes for next crew on site: \n" + notesString +"\n"); 
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
		DialogFragment newSubmitFragment = SubmitDialogFragment.newInstance(fields, subject);
		newSubmitFragment.show(getFragmentManager(), "submit");
	}
	
	private void showFileLoaderDialog(){
		DialogFragment nFileLoaderFragment= FileLoaderFragment.newInstance(path);
		nFileLoaderFragment.show(getFragmentManager(), "file");
	}
	
	public void onSubmitDialogPositiveClick(DialogFragment dialog){
		/*((DailyWorkOrderApplication) this.getApplication()).clearRooms();
		adapter.notifyDataSetChanged();*/
	}
	
	public void onFileLoaderDialogPositiveClick(DialogFragment dialog, String insuredName, String address, String jobNum){
		insuredEdit.setText(insuredName);
		jobNumberEdit.setText(jobNum);
		addressEdit.setText(address);
	}
	@Override
	public void onNewRoomDialogPositiveClick(DialogFragment dialog,
			String name, String length, String width, String height, HashMap<String, Boolean> formFields) {
			if (name.length() < 1 || width.length() < 1 || height.length() < 1 || length.length() < 1){
				Toast.makeText(this, "Form fields are incomplete", Toast.LENGTH_SHORT).show();
			}
			else{
			Toast.makeText(this, "Making your room", Toast.LENGTH_SHORT).show();		
			rooms.add(new Room(name, length, width, height, formFields));
			((DailyWorkOrderApplication) this.getApplication()).storeRoom();
			adapter.notifyDataSetChanged();
			}
	}

	
}
