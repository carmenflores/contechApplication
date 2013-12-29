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
public class FormDialogFragment extends DialogFragment {
	CheckBox general1;
	CheckBox general2;
	CheckBox general3;
	CheckBox general4;
	String information;
	HashMap<String, Boolean> formBooleans;
	
	/* TO SEND DATA BACK TO ACTIVITY */
	public interface NoticeDialogListener {
		public void onFormDialogPositiveClick(DialogFragment dialog, String field,
				String information, int position);
	}

	NoticeDialogListener mListener;

	public static FormDialogFragment newInstance() {
		FormDialogFragment frag = new FormDialogFragment();
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
				R.layout.general_form_field, null);
		Bundle mArgs = getArguments();
		builder.setView(view);
		final String field = mArgs.getString("field");
		final int position = mArgs.getInt("position");
		builder.setTitle(field);
		information = "";
		
		general1 = (CheckBox) view.findViewById(R.id.checkbox_general1);
		general1.setChecked(false);

		
		general2 = (CheckBox) view.findViewById(R.id.checkbox_general2);
		general2.setChecked(false);
		
		general3 = (CheckBox) view.findViewById(R.id.checkbox_general3);
		general3.setChecked(false);

		general4 = (CheckBox) view.findViewById(R.id.checkbox_general4);
		general4.setChecked(false);

		
		if (field.equals("Water Extracted") || field.equals("Lift Carpet to Save") 
				|| field.equals("U/Pad Removed") || field.equals("Carpet Removed") 
				|| field.equals("Flooring Removed")){
			general1.setText(".25F");
			general2.setText(".5F");
			general3.setText(".75F");
			general4.setText("F");
		}
		builder.setPositiveButton(
				"Submit",
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						if(general1.isChecked()){
							information += " .25F";
						}
						else if(general2.isChecked()){
							information += " .5F";
						}
						else if(general3.isChecked()){
							information += " .75F";
						}
						else if(general4.isChecked()){
							information += " F";
						}
						
						mListener.onFormDialogPositiveClick(
								FormDialogFragment.this,field, information, position);
						dialog.dismiss();
					}
				});

		return builder.create();

	}
}