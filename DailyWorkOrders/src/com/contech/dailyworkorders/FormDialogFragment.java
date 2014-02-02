package com.contech.dailyworkorders;

import java.util.HashMap;


import com.contech.dailyworkorders.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

/* ------- DIALOG FOR CREATING NEW ROOML ------ */
public class FormDialogFragment extends DialogFragment {
	CheckBox general1;
	CheckBox general2;
	CheckBox general3;
	CheckBox general4;
	CheckBox general5;
	CheckBox general6;
	CheckBox general7;
	CheckBox general8;
	
	CheckBox additionalInfo1;
	CheckBox additionalInfo2;
	CheckBox additionalInfo3;
	CheckBox additionalInfo4;
	CheckBox additionalInfo5;
	CheckBox additionalInfo6;
	CheckBox additionalInfo7;
	CheckBox additionalInfo8;
	CheckBox additionalInfoLF;
	EditText additionalInfoLFedit;
	TextView additionalInfoLFTV;

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
		
		general5 = (CheckBox) view.findViewById(R.id.checkbox_general5);
		general6 = (CheckBox) view.findViewById(R.id.checkbox_general6);
		general7 = (CheckBox) view.findViewById(R.id.checkbox_general7);
		general8 = (CheckBox) view.findViewById(R.id.checkbox_general8);
		additionalInfo1 = (CheckBox) view.findViewById(R.id.checkbox_additionalInfo1);
		additionalInfo2 = (CheckBox) view.findViewById(R.id.checkbox_additionalInfo2);
		additionalInfo3 = (CheckBox) view.findViewById(R.id.checkbox_additionalInfo3);
		additionalInfo4 = (CheckBox) view.findViewById(R.id.checkbox_additionalInfo4);
		additionalInfo5 = (CheckBox) view.findViewById(R.id.checkbox_additionalInfo5);
		additionalInfo6 = (CheckBox) view.findViewById(R.id.checkbox_additionalInfo6);
		additionalInfo7 = (CheckBox) view.findViewById(R.id.checkbox_additionalInfo7);
		additionalInfo8 = (CheckBox) view.findViewById(R.id.checkbox_additionalInfo8);
		additionalInfoLF = (CheckBox) view.findViewById(R.id.checkbox_additionalInfo9);
		additionalInfoLFedit = (EditText) view.findViewById(R.id.edittext_additionalInfo1);
		additionalInfoLFTV = (TextView) view.findViewById(R.id.textview_additionalInfo1);

		
		if (field.equals("Insulation")){
			
			general5.setVisibility(View.VISIBLE);
			general5.setChecked(false);

			general6.setVisibility(View.VISIBLE);
			general6.setChecked(false);
			
			general7.setVisibility(View.VISIBLE);
			general7.setChecked(false);
			
			general8.setVisibility(View.VISIBLE);
			general8.setChecked(false);
			
			general1.setText("25% C");
			general2.setText("25% W");
			general3.setText("50% C");
			general4.setText("50% W");
			general5.setText("75% C");
			general6.setText("75% W");
			general7.setText("100% C");
			general8.setText("100% W");

			additionalInfo1.setVisibility(View.VISIBLE);
			additionalInfo1.setChecked(false);
			additionalInfo2.setVisibility(View.VISIBLE);
			additionalInfo2.setChecked(false);
			additionalInfo3.setVisibility(View.VISIBLE);
			additionalInfo3.setChecked(false);
			additionalInfo4.setVisibility(View.VISIBLE);
			additionalInfo4.setChecked(false);
			additionalInfo5.setVisibility(View.VISIBLE);
			additionalInfo5.setChecked(false);
			additionalInfo6.setVisibility(View.VISIBLE);
			additionalInfo6.setChecked(false);
			
			additionalInfo1.setText(Html.fromHtml("Fiberglass Pink- R-12<sub> 4\"</sub>"));
			additionalInfo2.setText(Html.fromHtml("R-20<sub> 6\"</sub>"));
			additionalInfo3.setText("Rigid Foam");
			additionalInfo4.setText("Roxul");
			additionalInfo5.setText("Blow-in");
			additionalInfo6.setText("Batt");
		}
		else if (field.equals("Water Extracted") || field.equals("Lift Carpet to Save") 
				|| field.equals("U/Pad Removed") || field.equals("Carpet Removed") 
				|| field.equals("Flooring Removed") || field.equals("Sub Flooring")){
			general1.setText("25% F");
			general2.setText("50% F");
			general3.setText("75% F");
			general4.setText("100% F");
			
			if (field.equals("Water Extracted")){
				additionalInfo1.setVisibility(View.VISIBLE);
				additionalInfo1.setChecked(false);
				additionalInfo2.setVisibility(View.VISIBLE);
				additionalInfo2.setChecked(false);
				additionalInfo3.setVisibility(View.VISIBLE);
				additionalInfo3.setChecked(false);
				
				additionalInfo1.setText("Clean");
				additionalInfo2.setText("Grey");
				additionalInfo3.setText("Black");
			}
			else if (field.equals("Lift Carpet to Save")){
				additionalInfo1.setVisibility(View.VISIBLE);
				additionalInfo1.setChecked(false);
				additionalInfoLF.setVisibility(View.VISIBLE);
				additionalInfoLF.setChecked(false);
				
				additionalInfoLFedit.setVisibility(View.VISIBLE);
				additionalInfoLFTV.setVisibility(View.VISIBLE);

				additionalInfo1.setText("Float to Dry");
				additionalInfoLF.setText("Cut Seam");
			}
			else if (field.equals("U/Pad Removed")){
				additionalInfo1.setVisibility(View.VISIBLE);
				additionalInfo1.setChecked(false);
				additionalInfo2.setVisibility(View.VISIBLE);
				additionalInfo2.setChecked(false);
				additionalInfo3.setVisibility(View.VISIBLE);
				additionalInfo3.setChecked(false);
				additionalInfo4.setVisibility(View.VISIBLE);
				additionalInfo4.setChecked(false);
				
				additionalInfo1.setText("1/2\" Chip foam");
				additionalInfo2.setText("Sponge Cushion");
				additionalInfo3.setText("Rubber Cushion");
				additionalInfo4.setText("Fiber Cushion");
			}
			else if (field.equals("Carpet Removed")){
				additionalInfo1.setVisibility(View.VISIBLE);
				additionalInfo1.setChecked(false);
				additionalInfo2.setVisibility(View.VISIBLE);
				additionalInfo2.setChecked(false);
				additionalInfo3.setVisibility(View.VISIBLE);
				additionalInfo3.setChecked(false);
				additionalInfo4.setVisibility(View.VISIBLE);
				additionalInfo4.setChecked(false);
				
				additionalInfo1.setText("Standard Grade");
				additionalInfo2.setText("Commercial Grade");
				additionalInfo3.setText("Carpet Tile");
				additionalInfo4.setText("Glue Down");
			}
			else if (field.equals("Flooring Removed")){
				additionalInfo1.setVisibility(View.VISIBLE);
				additionalInfo1.setChecked(false);
				additionalInfo2.setVisibility(View.VISIBLE);
				additionalInfo2.setChecked(false);
				additionalInfo3.setVisibility(View.VISIBLE);
				additionalInfo3.setChecked(false);
				additionalInfo4.setVisibility(View.VISIBLE);
				additionalInfo4.setChecked(false);
				additionalInfo5.setVisibility(View.VISIBLE);
				additionalInfo5.setChecked(false);
				
				additionalInfo1.setText("Laminate");
				additionalInfo2.setText("Engineered");
				additionalInfo3.setText("Hardwood");
				additionalInfo4.setText("Vinyl / Linoleum");
				additionalInfo5.setText("Ceramic");
			}
			else if (field.equals("Sub Flooring")){
				additionalInfo1.setVisibility(View.VISIBLE);
				additionalInfo1.setChecked(false);
				additionalInfo2.setVisibility(View.VISIBLE);
				additionalInfo2.setChecked(false);
				additionalInfo3.setVisibility(View.VISIBLE);
				additionalInfo3.setChecked(false);
				additionalInfo4.setVisibility(View.VISIBLE);
				additionalInfo4.setChecked(false);
				additionalInfo5.setVisibility(View.VISIBLE);
				additionalInfo5.setChecked(false);
				additionalInfo6.setVisibility(View.VISIBLE);
				additionalInfo6.setChecked(false);
				additionalInfo7.setVisibility(View.VISIBLE);
				additionalInfo7.setChecked(false);
				additionalInfo8.setVisibility(View.VISIBLE);
				additionalInfo8.setChecked(false);
				
				additionalInfo1.setText("OSB");
				additionalInfo2.setText("Plywood");
				additionalInfo3.setText("1/4\"");
				additionalInfo4.setText("1/2\"");
				additionalInfo5.setText("3/4\"");
				additionalInfo6.setText("5/8\"");
				additionalInfo7.setText("Sleepers (1x3)\"");
				additionalInfo8.setText("(2x4)\"");
			}
			

		}
		else if (field.equals("Drywall Removed") || field.equals("Drill Hole") 
				|| field.equals("Panelling Removed")){
			general1.setText("25% PF");
			general2.setText("50% PF");
			general3.setText("75% PF");
			general4.setText("100% PF");

			if (field.equals("Drywall Removed")){
				additionalInfo1.setVisibility(View.VISIBLE);
				additionalInfo1.setChecked(false);
				additionalInfo2.setVisibility(View.VISIBLE);
				additionalInfo2.setChecked(false);
				additionalInfo3.setVisibility(View.VISIBLE);
				additionalInfo3.setChecked(false);
				additionalInfo4.setVisibility(View.VISIBLE);
				additionalInfo4.setChecked(false);
				additionalInfo5.setVisibility(View.VISIBLE);
				additionalInfo5.setChecked(false);
				additionalInfo6.setVisibility(View.VISIBLE);
				additionalInfo6.setChecked(false);
				
				additionalInfo1.setText("Double Layer");
				additionalInfo2.setText("Lathe & Plaster");
				additionalInfo3.setText("Green Board");
				additionalInfo4.setText("1/2\"");
				additionalInfo5.setText("5/8\"");
				additionalInfo6.setText("2'up");

			}
			else if (field.equals("Drill Hole")){
				additionalInfoLF.setVisibility(View.VISIBLE);
				additionalInfoLF.setChecked(false);

				additionalInfoLF.setText("Measured");

				additionalInfoLFedit.setVisibility(View.VISIBLE);
				additionalInfoLFTV.setVisibility(View.VISIBLE);
			}
			else if (field.equals("Panelling Removed")){
				additionalInfo1.setVisibility(View.VISIBLE);
				additionalInfo1.setChecked(false);
				additionalInfo2.setVisibility(View.VISIBLE);
				additionalInfo2.setChecked(false);
				additionalInfo3.setVisibility(View.VISIBLE);
				additionalInfo3.setChecked(false);
				additionalInfo4.setVisibility(View.VISIBLE);
				additionalInfo4.setChecked(false);
				additionalInfo5.setVisibility(View.VISIBLE);
				additionalInfo5.setChecked(false);
				
				additionalInfo1.setText("Double Layer");
				additionalInfo2.setText("Stand Grade");
				additionalInfo3.setText("Med Grade");
				additionalInfo4.setText("High Grade");
				additionalInfo5.setText("Stain Grade");
			}
			
		}
		else if (field.equals("Ceiling Removed")){
			general1.setText("25% C");
			general2.setText("50% C");
			general3.setText("75% C");
			general4.setText("100% C");

			if (field.equals("Ceiling Removed")){
				additionalInfo1.setVisibility(View.VISIBLE);
				additionalInfo1.setChecked(false);
				additionalInfo2.setVisibility(View.VISIBLE);
				additionalInfo2.setChecked(false);
				additionalInfo3.setVisibility(View.VISIBLE);
				additionalInfo3.setChecked(false);
				additionalInfo4.setVisibility(View.VISIBLE);
				additionalInfo4.setChecked(false);
				additionalInfo5.setVisibility(View.VISIBLE);
				additionalInfo5.setChecked(false);
				
				additionalInfo1.setText("Flat");
				additionalInfo2.setText("Textured Spray");
				additionalInfo3.setText("Lathe & Plaster");
				additionalInfo4.setText("Ceiling Tile");
				additionalInfo5.setText("Drop Ceiling");
			}
			
		}
		builder.setPositiveButton(
				"Submit",
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						
						//additional info
						if(additionalInfo1.isChecked()){
							information += " " + additionalInfo1.getText() + " &";
						}
						if(additionalInfo2.isChecked()){
							information += " " + additionalInfo2.getText() + " &";
						}
						if(additionalInfo3.isChecked()){
							information += " " + additionalInfo3.getText() + " &";
						}
						if(additionalInfo4.isChecked()){
							information += " " + additionalInfo4.getText() + " &";
						}
						if(additionalInfo5.isChecked()){
							information += " " + additionalInfo5.getText() + " &";
						}
						if(additionalInfo6.isChecked()){
							information += " " + additionalInfo6.getText() + " &";
						}
						if(additionalInfo7.isChecked()){
							information += " " + additionalInfo7.getText() + " &";
						}
						if(additionalInfo8.isChecked()){
							information += " " + additionalInfo8.getText() + " &";
						}
						if(additionalInfoLF.isChecked()){
							information += " " + additionalInfoLFedit.getText() + " Lf &";
						}
			    		information = information.substring(0, information.length()-2);

						
						//INSULATION check 
						if (general8.getVisibility() == View.VISIBLE) {							
							
							if(general1.isChecked()){
								information += " " + general1.getText() + " &";
							}
							if(general2.isChecked()){
								information += " " + general2.getText() + " &";
							}
							if(general3.isChecked()){
								information += " " + general3.getText() + " &";
							}
							if(general4.isChecked()){
								information += " " + general4.getText() + " &";
							}
							if(general5.isChecked()){
								information += " " + general5.getText() + " &";
							}
							if(general6.isChecked()){
								information += " " + general6.getText() + " &";
							}
							if(general7.isChecked()){
								information += " " + general7.getText() + " &";
							}
							if(general8.isChecked()){
								information += " " + general8.getText() + " &";
							}
							
				    		information = information.substring(0, information.length()-2);
							
						}else{
							
							if(general1.isChecked()){
								information += " " + general1.getText();
							}
							else if(general2.isChecked()){
								information += " " + general2.getText();
							}
							else if(general3.isChecked()){
								information += " " + general3.getText();
							}
							else if(general4.isChecked()){
								information += " " + general4.getText();
							}
						
						}
						
						mListener.onFormDialogPositiveClick(
								FormDialogFragment.this,field, information, position);
						dialog.dismiss();
						
					}
					
				});

		return builder.create();

	}
}