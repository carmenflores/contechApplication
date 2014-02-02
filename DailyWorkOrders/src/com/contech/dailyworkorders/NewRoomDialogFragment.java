package com.contech.dailyworkorders;

import java.util.HashMap;

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
						 formBooleans.put("Lift Carpet to Save", isCarpetToSave.isChecked());
						 formBooleans.put("U/Pad Removed", isUpadRemoved.isChecked());
						 formBooleans.put("Carpet Removed", isCarpetRemoved.isChecked());
						 formBooleans.put("Flooring Removed", isFlooringRemoved.isChecked());
						 formBooleans.put("Sub Flooring", isSubFlooring.isChecked());
						 formBooleans.put("Drywall Removed", isDrywallRemoved.isChecked());
						 formBooleans.put("Drill Hole", isDrillHole.isChecked());
						 formBooleans.put("Panelling Removed", isPanellingRemoved.isChecked());
						 formBooleans.put("Ceiling Removed", isCeilingRemoved.isChecked());
						 formBooleans.put("Insulation", isInsulation.isChecked());

						mListener.onNewRoomDialogPositiveClick(
								NewRoomDialogFragment.this, nameToSend, length, width, height, formBooleans);
						dialog.dismiss();
					}
				});

		return builder.create();

	}
}