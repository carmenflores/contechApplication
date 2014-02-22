package com.contech.dailyworkorders;

import java.util.HashMap;
import java.util.LinkedHashMap;

import com.contech.dailyworkorders.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

/* ------- DIALOG FOR CREATING NEW ROOML ------ */
public class NewRoomDialogFragment extends DialogFragment {
	CheckBox isWaterExtracted;
	CheckBox isCarpetToSave;
	CheckBox isUpadRemoved;
	CheckBox isCarpetRemoved;
	CheckBox isFlooringRemoved;
	CheckBox isSubFlooring;
	CheckBox isDrywallRemoved;
	CheckBox isDrillHole;
	CheckBox isPanellingRemoved;
	CheckBox isCeilingRemoved;
	CheckBox isInsulation;
	CheckBox isCasingRemoved;
	CheckBox isJambRemoved;
	CheckBox isDoorsDetached;
	CheckBox isWallInsulation;
	CheckBox isBaseboardRemoved;
	CheckBox isQuarterRoundRemoved;
	CheckBox isCrownMoulding;
	CheckBox isKitchenCabinet;
	CheckBox isAccessPanelRemoved;
	CheckBox isContentsManipulation;
	CheckBox isAdditionalNotes;
	
	HashMap<String, Boolean> formBooleans;
	
	/* TO SEND DATA BACK TO ACTIVITY */
	public interface NoticeDialogListener {
		public void onNewRoomDialogPositiveClick(DialogFragment dialog,
				String roomName, String roomWidth, String roomLength, String roomHeight, HashMap<String,Boolean> formBoolean);
	}

	NoticeDialogListener mListener;

	public static NewRoomDialogFragment newInstance() {
		NewRoomDialogFragment frag = new NewRoomDialogFragment();
		return frag;
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			// Instantiate the NoticeDialogListener so we can send events to
			// the host
			mListener = (NoticeDialogListener) activity;
		} catch (ClassCastException e) {
			// The activity doesn't implement the interface, throw exception
			throw new ClassCastException(activity.toString()
					+ " must implement NoticeDialogListener");
		}
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {

		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		View view = getActivity().getLayoutInflater().inflate(
				R.layout.fragment_add_room, null);
		builder.setView(view);
		builder.setTitle("New Room");
		formBooleans = new HashMap<String, Boolean>();
		
		isWaterExtracted = (CheckBox) view.findViewById(R.id.checkbox_waterExtracted);
		isWaterExtracted.setChecked(false);
		
		isCarpetToSave = (CheckBox) view.findViewById(R.id.checkbox_carpetToSave);
		isCarpetToSave.setChecked(false);
		
		isUpadRemoved = (CheckBox) view.findViewById(R.id.checkbox_upadRemoved);
		isUpadRemoved.setChecked(false);
		
		isCarpetRemoved = (CheckBox) view.findViewById(R.id.checkbox_carpetRemoved);
		isCarpetRemoved.setChecked(false);
		
		isFlooringRemoved = (CheckBox) view.findViewById(R.id.checkbox_flooringRemoved);
		isFlooringRemoved.setChecked(false);
		
		isSubFlooring = (CheckBox) view.findViewById(R.id.checkbox_subFlooring);
		isSubFlooring.setChecked(false);
		
		isDrywallRemoved = (CheckBox) view.findViewById(R.id.checkbox_drywallRemoved);
		isDrywallRemoved.setChecked(false);
		
		isDrillHole = (CheckBox) view.findViewById(R.id.checkbox_drillHole);
		isDrillHole.setChecked(false);
		
		isPanellingRemoved = (CheckBox) view.findViewById(R.id.checkbox_panellingRemoved);
		isPanellingRemoved.setChecked(false);
		
		isCeilingRemoved = (CheckBox) view.findViewById(R.id.checkbox_ceilingRemoved);
		isCeilingRemoved.setChecked(false);
		
		isInsulation = (CheckBox) view.findViewById(R.id.checkbox_insulation);
		isInsulation.setChecked(false);
		
		isWallInsulation = (CheckBox) view.findViewById(R.id.checkbox_wall);
		isWallInsulation.setChecked(false);
		
		isCasingRemoved = (CheckBox) view.findViewById(R.id.checkbox_casingRemoved);
		isCasingRemoved.setChecked(false);
		
		isJambRemoved = (CheckBox) view.findViewById(R.id.checkbox_jambRemoved);
		isJambRemoved.setChecked(false);
		
		isDoorsDetached = (CheckBox) view.findViewById(R.id.checkbox_doorsDetached);
		isDoorsDetached.setChecked(false);
		
		isBaseboardRemoved = (CheckBox) view.findViewById(R.id.checkbox_baseboardRemoved);
		isBaseboardRemoved.setChecked(false);
		
		isQuarterRoundRemoved = (CheckBox) view.findViewById(R.id.checkbox_quarterRoundRemoved);
		isQuarterRoundRemoved.setChecked(false);
		
		isCrownMoulding = (CheckBox) view.findViewById(R.id.checkbox_crownMoulding);
		isCrownMoulding.setChecked(false);
		
		isKitchenCabinet = (CheckBox) view.findViewById(R.id.checkbox_kitchenCabinet);
		isKitchenCabinet.setChecked(false);
		
		isAccessPanelRemoved = (CheckBox) view.findViewById(R.id.checkbox_accessPanelRemoved);
		isAccessPanelRemoved.setChecked(false);

		isContentsManipulation = (CheckBox) view.findViewById(R.id.checkbox_contentsManip);
		isContentsManipulation.setChecked(false);
		
		isAdditionalNotes = (CheckBox) view.findViewById(R.id.checkbox_additionalNotes);
		isAdditionalNotes.setChecked(true);
		
		builder.setPositiveButton(
				getString(R.string.dialog_positive_new_room),
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						EditText nameEditText = (EditText) ((AlertDialog) dialog)
								.findViewById(R.id.name_field);
						 String name = nameEditText.getText().toString();
						 String nameToSend;
					 	 if (name.length() >= 1){
					 		 nameToSend = name.substring(0,1).toUpperCase() + name.substring(1);
					 	 }
					 	 else{
					 		 nameToSend = name;
					 	 }
						 EditText lengthText = (EditText)  ((AlertDialog) dialog).findViewById(R.id.length_field);
						 String length = lengthText.getText().toString();
						 
						 EditText widthText = (EditText)  ((AlertDialog) dialog).findViewById(R.id.width_field);
						 String width = widthText.getText().toString();
						 
						 EditText heightText = (EditText)  ((AlertDialog) dialog).findViewById(R.id.height_field);
						 String height = heightText.getText().toString();
						 
						 formBooleans.put( "Water Extracted", isWaterExtracted.isChecked());
						 formBooleans.put("Carpet Lifted", isCarpetToSave.isChecked());
						 formBooleans.put("U/Pad Removed", isUpadRemoved.isChecked());
						 formBooleans.put("Carpet Removed", isCarpetRemoved.isChecked());
						 formBooleans.put("Flooring Removed", isFlooringRemoved.isChecked());
						 formBooleans.put("Sub Flooring", isSubFlooring.isChecked());
						 formBooleans.put("Drywall Removed", isDrywallRemoved.isChecked());
						 formBooleans.put("Drill Hole", isDrillHole.isChecked());
						 formBooleans.put("Paneling Removed", isPanellingRemoved.isChecked());
						 formBooleans.put("Ceiling Removed", isCeilingRemoved.isChecked());
						 formBooleans.put("Ceiling Insulation", isInsulation.isChecked());
						 formBooleans.put("Wall Insulation", isWallInsulation.isChecked());
						 formBooleans.put("Casing Removed", isCasingRemoved.isChecked());
						 formBooleans.put("Jamb Removed", isJambRemoved.isChecked());
						 formBooleans.put("Doors Detached", isDoorsDetached.isChecked());
						 formBooleans.put("Baseboard Removed", isBaseboardRemoved.isChecked());
						 formBooleans.put("1/4 Round Removed", isQuarterRoundRemoved.isChecked());
						 formBooleans.put("Crown Moulding", isCrownMoulding.isChecked());
						 formBooleans.put("Kitchen Cabinet", isKitchenCabinet.isChecked());
						 formBooleans.put("Access Panel Removed", isAccessPanelRemoved.isChecked());
						 formBooleans.put("Contents Manipulation", isContentsManipulation.isChecked());
						 formBooleans.put("Additional Notes", isAdditionalNotes.isChecked());

						mListener.onNewRoomDialogPositiveClick(
								NewRoomDialogFragment.this, nameToSend, length, width, height, formBooleans);
						dialog.dismiss();
					}
				});

		return builder.create();

	}
}