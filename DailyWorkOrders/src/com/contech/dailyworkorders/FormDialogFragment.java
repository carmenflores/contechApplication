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
	//CheckBox additionalInfoPcs;
	TextView additionalInfoPcs;
	CheckBox additionalInfoContentsManip;
	EditText additionalInfoLFedit;
	EditText additionalInfoPcsedit;
	EditText additionalInfoContentsManipedit;
	EditText additionalInfoContentsManipedit2;
	EditText additionalNotes;
	TextView additionalInfoLFTV;
	TextView additionalInfoPcsTV;
	TextView additionalInfoContentsManipTV;
	TextView additionalInfoContentsManipTV2;
	TextView additionalInfoContentsManipTV3;
	TextView textView_additionalinfo;
	TextView amountHeaderTV;
	TextView notesHeaderTV;
	String information;
	Integer checkSwitch;
	Integer checkAdditionalNotes;

	Integer checkAreaSwitch;
	//area LXW
	//CheckBox checkbox_areaMeasured;
	TextView areaMeasured;
	TextView textView_areaString1;
	EditText edittext_areaLength;
	TextView textView_areaString2;
	EditText edittext_areaWidth;
	TextView textView_areaString3;
	//area LF
	//CheckBox checkbox_areaMeasured2;
	TextView textView_area;
	EditText edittext_areaLF;
	TextView textView_areaString4;

	CheckBox checkbox_areaDisposed;
	CheckBox checkbox_areaSaved;
	
	CheckBox checkbox_area1;
	CheckBox checkbox_area2;
	CheckBox checkbox_area3;
	CheckBox checkbox_area4;
	//Chair Rail __ LF
	CheckBox checkbox_chair;
	TextView textView_areaNotes1;
	EditText edittext_areaNotes1;
	TextView textView_areaNotes2;
	//# of stairs __
	CheckBox checkbox_numStairs;
	TextView textView_areaNotes3;
	EditText edittext_areaNotes2;
	
	TextView empHeader;
	EditText empEdit;

	
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
		general4 = (CheckBox) view.findViewById(R.id.checkbox_general4);
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
		additionalInfoLF = (CheckBox) view.findViewById(R.id.checkbox_additionalInfoLF);
		//additionalInfoPcs = (CheckBox) view.findViewById(R.id.checkbox_additionalInfoPcs);
		additionalInfoPcs = (TextView) view.findViewById(R.id.textview_additionalInfoPcs);
		additionalInfoContentsManip = (CheckBox) view.findViewById(R.id.checkbox_additionalInfoContentsManip);
		additionalInfoLFedit = (EditText) view.findViewById(R.id.edittext_additionalInfo1);
		additionalInfoPcsedit = (EditText) view.findViewById(R.id.edittext_additionalInfo2);
		additionalInfoContentsManipedit = (EditText) view.findViewById(R.id.edittext_additionalInfo3);
		additionalInfoContentsManipedit2 = (EditText) view.findViewById(R.id.edittext_additionalInfo4);
		additionalInfoLFTV = (TextView) view.findViewById(R.id.textview_additionalInfo1);
		additionalInfoPcsTV = (TextView) view.findViewById(R.id.textview_additionalInfo2);
		additionalInfoContentsManipTV = (TextView) view.findViewById(R.id.textview_additionalInfo3);
		additionalInfoContentsManipTV2 = (TextView) view.findViewById(R.id.textview_additionalInfo4);
		additionalInfoContentsManipTV3 = (TextView) view.findViewById(R.id.textview_additionalInfo5);
		additionalNotes = (EditText) view.findViewById(R.id.edittext_additionalNotes);

		amountHeaderTV = (TextView) view.findViewById(R.id.textView_amount);
		notesHeaderTV = (TextView) view.findViewById(R.id.textView_additionalinfo);
		
		empHeader = (TextView) view.findViewById(R.id.textView_emp);
		empEdit = (EditText) view.findViewById(R.id.edittext_emp);
		areaMeasured =  (TextView) view.findViewById(R.id.textView_areaString0);
		//checkbox_areaMeasured = (CheckBox) view.findViewById(R.id.checkbox_areaMeasured);
		 textView_areaString1 = (TextView) view.findViewById(R.id.textView_areaString1);
		 edittext_areaLength = (EditText) view.findViewById(R.id.edittext_areaLength);
		 textView_areaString2 = (TextView) view.findViewById(R.id.textView_areaString2);
		 edittext_areaWidth = (EditText) view.findViewById(R.id.edittext_areaWidth);
		 textView_areaString3 = (TextView) view.findViewById(R.id.textView_areaString3);
		 checkbox_areaDisposed = (CheckBox) view.findViewById(R.id.checkbox_areaDisposed);
		 checkbox_areaSaved = (CheckBox) view.findViewById(R.id.checkbox_areaSaved);

		 checkbox_area1 = (CheckBox) view.findViewById(R.id.checkbox_area1);
		 checkbox_area2 = (CheckBox) view.findViewById(R.id.checkbox_area2);
		 checkbox_area3 = (CheckBox) view.findViewById(R.id.checkbox_area3);
		 checkbox_area4 = (CheckBox) view.findViewById(R.id.checkbox_area4);
		 checkbox_chair = (CheckBox) view.findViewById(R.id.checkbox_chair);
		 textView_areaNotes1 = (TextView) view.findViewById(R.id.textView_areaNotes1);
		 edittext_areaNotes1 = (EditText) view.findViewById(R.id.edittext_areaNotes1);
		 textView_areaNotes2 = (TextView) view.findViewById(R.id.textView_areaNotes2);
		 checkbox_numStairs = (CheckBox) view.findViewById(R.id.checkbox_numStairs);
		 edittext_areaNotes2 = (EditText) view.findViewById(R.id.edittext_areaNotes2);
		 //textView_areaNotes3 = (TextView) view.findViewById(R.id.textView_areaNotes3);
		 textView_additionalinfo = (TextView)view.findViewById(R.id.textView_additionalinfo);
		 textView_area = (TextView) view.findViewById(R.id.textView_area);
	     edittext_areaLF = (EditText) view.findViewById(R.id.edittext_areaLF);
		 textView_areaString4 = (TextView) view.findViewById(R.id.textView_areaString4);
		 
		checkAdditionalNotes = 0;
		
		if (field.equals("Ceiling Insulation") || field.equals("Wall Insulation")){
			
			amountHeaderTV.setVisibility(View.VISIBLE);
			
			general3.setVisibility(View.VISIBLE);
			general3.setChecked(false);
			
			general4.setVisibility(View.VISIBLE);
			general4.setChecked(false);
			
			if (field.equals("Ceiling Insulation")){
			general1.setText("25% C");
			general2.setText("50% C");
			general3.setText("75% C");
			general4.setText("100% C");
			}else{
				general1.setText("25% W");
				general2.setText("50% W");
				general3.setText("75% W");
				general4.setText("100% W");
			}
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
			
			additionalInfo1.setText(Html.fromHtml("Pink- R-12<sub> 4\"</sub>"));
			additionalInfo2.setText(Html.fromHtml("Pink- R-20<sub> 6\"</sub>"));
			additionalInfo3.setText("Rigid Foam");
			additionalInfo4.setText("Roxul");
			additionalInfo5.setText("Blow-in");
			additionalInfo6.setText("Batt");
			
			
			areaMeasured.setVisibility(View.VISIBLE);
			textView_areaString1.setVisibility(View.VISIBLE);
			edittext_areaLength.setVisibility(View.VISIBLE);
			textView_areaString2.setVisibility(View.VISIBLE);
			edittext_areaWidth.setVisibility(View.VISIBLE);
			textView_areaString3.setVisibility(View.VISIBLE);
			

		}
		else if (field.equals("Water Extracted") || field.equals("Carpet Lifted") 
				|| field.equals("U/Pad Removed") || field.equals("Carpet Removed") 
				|| field.equals("Flooring Removed") || field.equals("Sub Flooring")){
			
			amountHeaderTV.setVisibility(View.VISIBLE);
			
			general3.setVisibility(View.VISIBLE);
			general3.setChecked(false);
			
			general4.setVisibility(View.VISIBLE);
			general4.setChecked(false);
			
			general1.setText(".25 F");
			general2.setText(".5 F");
			general3.setText(".75 F");
			general4.setText("F");
			
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
				
				
				areaMeasured.setVisibility(View.VISIBLE);
				textView_areaString1.setVisibility(View.VISIBLE);
				edittext_areaLength.setVisibility(View.VISIBLE);
				textView_areaString2.setVisibility(View.VISIBLE);
				edittext_areaWidth.setVisibility(View.VISIBLE);
				textView_areaString3.setVisibility(View.VISIBLE);
				
				checkbox_area1.setVisibility(View.VISIBLE);
				checkbox_area1.setChecked(false);
				checkbox_area2.setVisibility(View.VISIBLE);
				checkbox_area2.setChecked(false);
				checkbox_area3.setVisibility(View.VISIBLE);
				checkbox_area3.setChecked(false);
				
				checkbox_area1.setText("Heavy Disinfected Floors");
				checkbox_area2.setText("Med. Disinfected Floors");
				checkbox_area3.setText("Light Disinfected Floors");

				
			}
			else if (field.equals("Carpet Lifted")){
				additionalInfo1.setVisibility(View.VISIBLE);
				additionalInfo1.setChecked(false);
				additionalInfoLF.setVisibility(View.VISIBLE);
				additionalInfoLF.setChecked(false);
				
				additionalInfoLFedit.setVisibility(View.VISIBLE);
				additionalInfoLFTV.setVisibility(View.VISIBLE);

				additionalInfo1.setText("Float to Dry");
				additionalInfoLF.setText("Cut Seam");
				
				areaMeasured.setVisibility(View.VISIBLE);
				textView_areaString1.setVisibility(View.VISIBLE);
				edittext_areaLength.setVisibility(View.VISIBLE);
				textView_areaString2.setVisibility(View.VISIBLE);
				edittext_areaWidth.setVisibility(View.VISIBLE);
				textView_areaString3.setVisibility(View.VISIBLE);
				
				checkbox_area1.setVisibility(View.VISIBLE);
				checkbox_area1.setChecked(false);
				checkbox_area2.setVisibility(View.VISIBLE);
				checkbox_area2.setChecked(false);
				
				checkbox_area1.setText("Pre-Existing Damage");
				checkbox_area2.setText("Tack Strips");
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
				
				areaMeasured.setVisibility(View.VISIBLE);
				textView_areaString1.setVisibility(View.VISIBLE);
				edittext_areaLength.setVisibility(View.VISIBLE);
				textView_areaString2.setVisibility(View.VISIBLE);
				edittext_areaWidth.setVisibility(View.VISIBLE);
				textView_areaString3.setVisibility(View.VISIBLE);
				
				checkbox_area1.setVisibility(View.VISIBLE);
				checkbox_area1.setChecked(false);
				checkbox_area2.setVisibility(View.VISIBLE);
				checkbox_area2.setChecked(false);
				
				checkbox_area1.setText("Glued to Floor");
				checkbox_area2.setText("Staples Removed");
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
				
				areaMeasured.setVisibility(View.VISIBLE);
				textView_areaString1.setVisibility(View.VISIBLE);
				edittext_areaLength.setVisibility(View.VISIBLE);
				textView_areaString2.setVisibility(View.VISIBLE);
				edittext_areaWidth.setVisibility(View.VISIBLE);
				textView_areaString3.setVisibility(View.VISIBLE);
				
				checkbox_area1.setVisibility(View.VISIBLE);
				checkbox_area1.setChecked(false);
				checkbox_numStairs.setVisibility(View.VISIBLE);
				checkbox_numStairs.setChecked(false);
				//textView_areaNotes3.setVisibility(View.VISIBLE);
				edittext_areaNotes2.setVisibility(View.VISIBLE);
				
				checkbox_area1.setText("Tack Strips Removed");
				checkbox_numStairs.setText("# of stairs");

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
				
				areaMeasured.setVisibility(View.VISIBLE);
				textView_areaString1.setVisibility(View.VISIBLE);
				edittext_areaLength.setVisibility(View.VISIBLE);
				textView_areaString2.setVisibility(View.VISIBLE);
				edittext_areaWidth.setVisibility(View.VISIBLE);
				textView_areaString3.setVisibility(View.VISIBLE);
				
				checkbox_area1.setVisibility(View.VISIBLE);
				checkbox_area1.setChecked(false);
				checkbox_area2.setVisibility(View.VISIBLE);
				checkbox_area2.setChecked(false);
				
				checkbox_area1.setText("Disinfected");
				checkbox_area2.setText("Double Layer");
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
				
				areaMeasured.setVisibility(View.VISIBLE);
				textView_areaString1.setVisibility(View.VISIBLE);
				edittext_areaLength.setVisibility(View.VISIBLE);
				textView_areaString2.setVisibility(View.VISIBLE);
				edittext_areaWidth.setVisibility(View.VISIBLE);
				textView_areaString3.setVisibility(View.VISIBLE);
				
				checkbox_area1.setVisibility(View.VISIBLE);
				checkbox_area1.setChecked(false);
				checkbox_area2.setVisibility(View.VISIBLE);
				checkbox_area2.setChecked(false);
				
				checkbox_area1.setText("Roofing Felt (tar paper)");
				checkbox_area2.setText("DriCore Subfloor");
			}
			

		}
		else if (field.equals("Drywall Removed") || field.equals("Drill Hole") 
				|| field.equals("Paneling Removed") || field.equals("Baseboard Removed") || field.equals("1/4 Round Removed")){
			
			amountHeaderTV.setVisibility(View.VISIBLE);
			
			general3.setVisibility(View.VISIBLE);
			general3.setChecked(false);
			
			general4.setVisibility(View.VISIBLE);
			general4.setChecked(false);
			
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
				
				areaMeasured.setVisibility(View.VISIBLE);
				textView_areaString1.setVisibility(View.VISIBLE);
				edittext_areaLength.setVisibility(View.VISIBLE);
				textView_areaString2.setVisibility(View.VISIBLE);
				edittext_areaWidth.setVisibility(View.VISIBLE);
				textView_areaString3.setVisibility(View.VISIBLE);
				
				checkbox_area1.setVisibility(View.VISIBLE);
				checkbox_area1.setChecked(false);
				checkbox_area2.setVisibility(View.VISIBLE);
				checkbox_area2.setChecked(false);

				checkbox_area1.setText("Disinfected Wall Cavity");
				checkbox_area2.setText("Insul/Vpr Bar.");

			}
			else if (field.equals("Drill Hole")){
				
				notesHeaderTV.setVisibility(View.GONE);
				textView_area.setVisibility(View.VISIBLE);
				textView_areaString4.setVisibility(View.VISIBLE);
				edittext_areaLF.setVisibility(View.VISIBLE);
				
				
			}
			else if (field.equals("Paneling Removed")){
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
				
				areaMeasured.setVisibility(View.VISIBLE);
				textView_areaString1.setVisibility(View.VISIBLE);
				edittext_areaLength.setVisibility(View.VISIBLE);
				textView_areaString2.setVisibility(View.VISIBLE);
				edittext_areaWidth.setVisibility(View.VISIBLE);
				textView_areaString3.setVisibility(View.VISIBLE);
				
				checkbox_area1.setVisibility(View.VISIBLE);
				checkbox_area1.setChecked(false);
				checkbox_chair.setVisibility(View.VISIBLE);
				checkbox_chair.setChecked(false);
				
				textView_areaNotes1.setVisibility(View.VISIBLE);
				edittext_areaNotes1.setVisibility(View.VISIBLE);
				textView_areaNotes2.setVisibility(View.VISIBLE);
				
				checkbox_area1.setText("Insul/Vpr Bar.");
			}
			else if (field.equals("Baseboard Removed")){
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
				
				additionalInfo1.setText("3 1/2\"");
				additionalInfo2.setText("4\"");
				additionalInfo3.setText("5\"");
				additionalInfo4.setText("6\"");
				additionalInfo5.setText("Oversized");
				
				textView_area.setVisibility(View.VISIBLE);
				textView_areaString4.setVisibility(View.VISIBLE);
				edittext_areaLF.setVisibility(View.VISIBLE);

				checkbox_areaDisposed.setVisibility(View.VISIBLE);
				checkbox_areaDisposed.setChecked(false);
				checkbox_areaSaved.setVisibility(View.VISIBLE);
				checkbox_areaSaved.setChecked(false);
				checkbox_areaDisposed.setText("Disposed");
				checkbox_areaSaved.setText("Saved");
				
				checkbox_area1.setVisibility(View.VISIBLE);
				checkbox_area1.setChecked(false);
				checkbox_area2.setVisibility(View.VISIBLE);
				checkbox_area2.setChecked(false);
				checkbox_area3.setVisibility(View.VISIBLE);
				checkbox_area3.setChecked(false);
				checkbox_area4.setVisibility(View.VISIBLE);
				checkbox_area4.setChecked(false);
				checkbox_area1.setText("Standard");
				checkbox_area2.setText("MDF");
				checkbox_area3.setText("Hardwood");
				checkbox_area4.setText("Carpet/Vinyl");

			}			
			else if (field.equals("1/4 Round Removed")){
				additionalInfo1.setVisibility(View.VISIBLE);
				additionalInfo1.setChecked(false);
				additionalInfo2.setVisibility(View.VISIBLE);
				additionalInfo2.setChecked(false);
				
				additionalInfo1.setText("1/2\" Shoe Mould");
				additionalInfo2.setText("3/4\" Shoe Mould");

				textView_area.setVisibility(View.VISIBLE);
				textView_areaString4.setVisibility(View.VISIBLE);
				edittext_areaLF.setVisibility(View.VISIBLE);
				
				checkbox_areaDisposed.setVisibility(View.VISIBLE);
				checkbox_areaDisposed.setChecked(false);
				checkbox_areaSaved.setVisibility(View.VISIBLE);
				checkbox_areaSaved.setChecked(false);
				checkbox_areaDisposed.setText("Disposed");
				checkbox_areaSaved.setText("Saved");
				
				checkbox_area1.setVisibility(View.VISIBLE);
				checkbox_area1.setChecked(false);
				checkbox_area2.setVisibility(View.VISIBLE);
				checkbox_area2.setChecked(false);
				checkbox_area3.setVisibility(View.VISIBLE);
				checkbox_area3.setChecked(false);
				checkbox_area4.setVisibility(View.VISIBLE);
				checkbox_area4.setChecked(false);
				checkbox_area1.setText("Standard");
				checkbox_area2.setText("MDF");
				checkbox_area3.setText("Hardwood");
				checkbox_area4.setText("Stain Grade");
			}
		}
		else if (field.equals("Ceiling Removed")){
			
			amountHeaderTV.setVisibility(View.VISIBLE);
			
			general3.setVisibility(View.VISIBLE);
			general3.setChecked(false);
			
			general4.setVisibility(View.VISIBLE);
			general4.setChecked(false);
			
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
				
				areaMeasured.setVisibility(View.VISIBLE);
				textView_areaString1.setVisibility(View.VISIBLE);
				edittext_areaLength.setVisibility(View.VISIBLE);
				textView_areaString2.setVisibility(View.VISIBLE);
				edittext_areaWidth.setVisibility(View.VISIBLE);
				textView_areaString3.setVisibility(View.VISIBLE);
				
				checkbox_area1.setVisibility(View.VISIBLE);
				checkbox_area1.setChecked(false);
				
				checkbox_area1.setText("Insul/Vpr Bar.");
			}
			
		}
		else if (field.equals("Crown Moulding")){
			
			amountHeaderTV.setVisibility(View.VISIBLE);
			
			general3.setVisibility(View.VISIBLE);
			general3.setChecked(false);
			
			general4.setVisibility(View.VISIBLE);
			general4.setChecked(false);
			
			general1.setText("25% PC");
			general2.setText("50% PC");
			general3.setText("75% PC");
			general4.setText("100% PC");
			
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
			
			additionalInfo1.setText("3 1/2\"");
			additionalInfo2.setText("4\"");
			additionalInfo3.setText("5\"");
			additionalInfo4.setText("6\"");
			additionalInfo5.setText("Oversized");
			
			textView_area.setVisibility(View.VISIBLE);
			textView_areaString4.setVisibility(View.VISIBLE);
			edittext_areaLF.setVisibility(View.VISIBLE);
			
			checkbox_areaDisposed.setVisibility(View.VISIBLE);
			checkbox_areaDisposed.setChecked(false);
			checkbox_areaSaved.setVisibility(View.VISIBLE);
			checkbox_areaSaved.setChecked(false);
			checkbox_areaDisposed.setText("Disposed");
			checkbox_areaSaved.setText("Saved");
			
			checkbox_area1.setVisibility(View.VISIBLE);
			checkbox_area1.setChecked(false);
			checkbox_area2.setVisibility(View.VISIBLE);
			checkbox_area2.setChecked(false);
			checkbox_area3.setVisibility(View.VISIBLE);
			checkbox_area3.setChecked(false);
			checkbox_area4.setVisibility(View.VISIBLE);
			checkbox_area4.setChecked(false);
			checkbox_area1.setText("Standard");
			checkbox_area2.setText("MDF");
			checkbox_area3.setText("Hardwood");
			checkbox_area4.setText("Plaster");
		}
		else if (field.equals("Casing Removed") || field.equals("Jamb Removed") || field.equals("Doors Detached")){
						
			general1.setText("Disposed");
			general2.setText("Saved");
			
			if (field.equals("Casing Removed")){
				textView_area.setVisibility(View.VISIBLE);
				textView_areaString4.setVisibility(View.VISIBLE);
				edittext_areaLF.setVisibility(View.VISIBLE);
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
				
				additionalInfo1.setText("2\"");
				additionalInfo2.setText("2 1/2\"");
				additionalInfo3.setText("3\"");
				additionalInfo4.setText("3 1/2\"");
				additionalInfo5.setText("4\"");
				additionalInfo6.setText("Oversized");
			}
			else if (field.equals("Jamb Removed")){
				
				additionalInfoPcs.setVisibility(View.VISIBLE);
				additionalInfoPcsedit.setVisibility(View.VISIBLE);
				additionalInfoPcsTV.setVisibility(View.VISIBLE);
				additionalInfo1.setVisibility(View.VISIBLE);
				additionalInfo1.setChecked(false);
				additionalInfo2.setVisibility(View.VISIBLE);
				additionalInfo2.setChecked(false);
				additionalInfo3.setVisibility(View.VISIBLE);
				additionalInfo3.setChecked(false);
				additionalInfo4.setVisibility(View.VISIBLE);
				additionalInfo4.setChecked(false);
				
				additionalInfo1.setText("Standard");
				additionalInfo2.setText("MDF");
				additionalInfo3.setText("Hardwood");
				additionalInfo4.setText("Stain Grade");
				
			}
			else if (field.equals("Doors Detached")){
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
				empHeader.setVisibility(View.VISIBLE);
				empEdit.setVisibility(View.VISIBLE);
				
				textView_additionalinfo.setText("Type:");
				empHeader.setText("Quantity:");
				additionalInfo1.setText("Slab");
				additionalInfo2.setText("Panel");
				additionalInfo3.setText("French");
				additionalInfo4.setText("Flush");
				additionalInfo5.setText("Bi-Fold");
				additionalInfo6.setText("Louvered");

			}
		}
		else if (field.equals("Kitchen Cabinet")){
				
				general3.setVisibility(View.VISIBLE);
				general3.setChecked(false);
				
				general4.setVisibility(View.VISIBLE);
				general4.setChecked(false);
				
				general1.setText("Lower Base");
				general2.setText("Upper Units");
				general3.setText("Full Height");
				general4.setText("Toe Kick Only");
				
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
				
				additionalInfo1.setText("Standard");
				additionalInfo2.setText("High Grade");
				additionalInfo3.setText("Premium Grade");
				additionalInfo4.setText("Custom");
				additionalInfo5.setText("Drilled Holes");
				
				textView_area.setVisibility(View.VISIBLE);
				textView_areaString4.setVisibility(View.VISIBLE);
				edittext_areaLF.setVisibility(View.VISIBLE);
				
				checkbox_areaDisposed.setVisibility(View.VISIBLE);
				checkbox_areaDisposed.setChecked(false);
				checkbox_areaSaved.setVisibility(View.VISIBLE);
				checkbox_areaSaved.setChecked(false);
				checkbox_areaDisposed.setText("Disposed");
				checkbox_areaSaved.setText("Saved");
				
		}
		else if (field.equals("Access Panel Removed")){
				general1.setText("Ceiling");
				general2.setText("Walls");
				
				additionalInfo1.setVisibility(View.VISIBLE);
				additionalInfo1.setChecked(false);
				
				additionalInfo1.setText("Wallpaper Removed");
				
				additionalInfoPcs.setVisibility(View.VISIBLE);

				additionalInfoPcsedit.setVisibility(View.VISIBLE);
				additionalInfoPcsTV.setVisibility(View.VISIBLE);
		}
		else if (field.equals("Contents Manipulation")){
				general3.setVisibility(View.VISIBLE);
				general3.setChecked(false);
				
				general1.setText("Small Room");
				general2.setText("Large Room");
				general3.setText("X Large Room");

				additionalInfoContentsManip.setVisibility(View.VISIBLE);
				additionalInfoContentsManip.setChecked(false);
				additionalInfoContentsManip.setText("Logged: ");
				additionalInfoContentsManipedit.setVisibility(View.VISIBLE);
				additionalInfoContentsManipedit2.setVisibility(View.VISIBLE);
				additionalInfoContentsManipTV.setVisibility(View.VISIBLE);
				additionalInfoContentsManipTV2.setVisibility(View.VISIBLE);
				additionalInfoContentsManipTV3.setVisibility(View.VISIBLE);

		}
		else if (field.equals("Additional Notes")){
			general1.setVisibility(View.GONE);
			general2.setVisibility(View.GONE);
			notesHeaderTV.setVisibility(View.GONE);
			additionalNotes.setVisibility(View.VISIBLE);
			checkAdditionalNotes = 1;
		}
		
		builder.setPositiveButton(
				"Submit",
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						
						checkAreaSwitch=0;
						if (field.equals("Doors Detached")){
							String quant = empEdit.getText().toString();
							if (! quant.isEmpty()){
									information += quant;
								
							}
							if(additionalInfo1.isChecked()){
								information += " " + additionalInfo1.getText();
								checkSwitch = 1;
							}
							if(additionalInfo2.isChecked()){
								information += " " + additionalInfo2.getText();								checkSwitch = 1;
							}
							if(additionalInfo3.isChecked()){
								information += " " + additionalInfo3.getText();								checkSwitch = 1;
							}
							if(additionalInfo4.isChecked()){
								information += " " + additionalInfo4.getText();								checkSwitch = 1;
							}
							if(additionalInfo5.isChecked()){
								information += " " + additionalInfo5.getText();								checkSwitch = 1;
							}
							if(additionalInfo6.isChecked()){
								information += " " + additionalInfo6.getText();								checkSwitch = 1;
							}
							if (quant.equals("1")){
								information += "Door Detached";
							}
							else{
								information += "Doors Detached";
							}
							information += " Doors Detached";
							if(general1.isChecked()){
								information += " and Disposed";
								checkSwitch = 1;
							}
							if(general2.isChecked()){
								information += " and Saved";
								checkSwitch = 1;
							}
							
						}
						if (field.equals("Water Extracted") || 
								field.equals ("Carpet Removed") || 
								field.equals("Carpet Lifted")|| 
								field.equals("Flooring Removed") ||
								field.equals("Drywall Removed") ||
								field.equals("Baseboard Removed") ||
								field.equals("Ceiling Removed") ||
								field.equals("Paneling Removed") ||
								field.equals("U/Pad Removed") ||
								field.equals("Drill Hole") ||
								field.equals("Ceiling Insultion") ||
								field.equals("Wall Insulation") ||
								field.equals("Sub Flooring")){
							if(general1.isChecked()){
								information += "25%";
								checkSwitch = 1;
							}
							if(general2.isChecked()){
								information += "50%";
								checkSwitch = 1;
							}
							if(general3.isChecked()){
								information += "75%";
								checkSwitch = 1;
							}
							if(general4.isChecked()){
								information += "100%";
								checkSwitch = 1;
							}
							
							if (field.equals("Water Extracted")){
								if (additionalInfo1.isChecked()){
									information += " "+ additionalInfo1.getText();
									checkSwitch = 1;
								}
								if (additionalInfo2.isChecked()){
									information += " "+ additionalInfo2.getText();
									checkSwitch = 1;
								}
								if (additionalInfo3.isChecked()){
									information += " "+ additionalInfo3.getText();
									checkSwitch = 1;
								}
								information += " "+field;
								
								if (checkbox_area1.isChecked()){
									information +=" & "+checkbox_area1.getText();
									checkSwitch = 1;
								}
								if (checkbox_area2.isChecked()){
									information +=" & "+checkbox_area2.getText();
									checkSwitch = 1;
								}
								if (checkbox_area3.isChecked()){
									information +=" & "+checkbox_area3.getText();
									checkSwitch = 1;
								}
								//Area String
								if (! edittext_areaLength.getText().toString().isEmpty()){
									information += " [ " + edittext_areaLength.getText().toString() + " x " +edittext_areaWidth.getText().toString() + " sq ft ]";
									checkSwitch = 1;
								}
							}else if(field.equals("Carpet Lifted")){
								information += " " + field;
								if (additionalInfo1.isChecked()){
									information += " - " + additionalInfo1.getText();
									checkSwitch = 1;
								}
								if (! additionalInfoLFedit.getText().toString().isEmpty()){
									information += " - " + additionalInfoLFedit.getText().toString();
									checkSwitch = 1;
								}
								
								if (checkbox_area1.isChecked()){
									information += " - " + checkbox_area1.getText();
									checkSwitch=1;
								}
								if (checkbox_area2.isChecked()){
									information += " - " + checkbox_area2.getText();
									checkSwitch=1;
								}
								
								if (! edittext_areaLength.getText().toString().isEmpty()){
									information += " [ " + edittext_areaLength.getText().toString() + " x " +edittext_areaWidth.getText().toString() + " sq ft ]";
									checkSwitch = 1;
								}
							}else if (field.equals("Carpet Removed") || field.equals("U/Pad Removed")){
									if (additionalInfo1.isChecked()){
										information += " "+ additionalInfo1.getText();
										checkSwitch = 1;
									}
									if (additionalInfo2.isChecked()){
										information += " "+ additionalInfo2.getText();
										checkSwitch = 1;
									}
									if (additionalInfo3.isChecked()){
										information += " "+ additionalInfo3.getText();
										checkSwitch = 1;
									}
									if (additionalInfo4.isChecked()){
										information += " "+ additionalInfo4.getText();
										checkSwitch = 1;
									}
									if (field.equals("Carpet Removed")){
										information += " "+field;
									}else{
										information += " "+ "Underpad Removed";
										
									}
										if (checkbox_area1.isChecked()){
											information += " - " + checkbox_area1.getText();
											checkSwitch=1;
										}
										if (checkbox_area2.isChecked()){
											information += " - " + checkbox_area2.getText();
											checkSwitch=1;
										}
									
										if (! edittext_areaLength.getText().toString().isEmpty()){
											information += " [ " + edittext_areaLength.getText().toString() + " x " +edittext_areaWidth.getText().toString() + " sq ft ]";
											checkSwitch = 1;
										}
									
								
							}else if(field.equals("Flooring Removed") 
									|| field.equals("Drywall Removed") 
									|| field.equals("Baseboard Removed")
									|| field.equals("Paneling Removed")
									|| field.equals("Ceiling Removed")
									|| field.equals("Ceiling Insulation")
									|| field.equals("Wall Insulation")){
								
									if (additionalInfo1.isChecked()){
										information += " "+ additionalInfo1.getText();
										checkSwitch = 1;
									}
									if (additionalInfo2.isChecked()){
										information += " "+ additionalInfo2.getText();
										checkSwitch = 1;
									}
									if (additionalInfo3.isChecked()){
										information += " "+ additionalInfo3.getText();
										checkSwitch = 1;
									}
									if (additionalInfo4.isChecked()){
										information += " "+ additionalInfo4.getText();
										checkSwitch = 1;
									
									}
									
									if (additionalInfo5.isChecked()){
										
										if (field.equals("Ceiling Removed")){
											information += " " + "Drop";
											checkSwitch = 1;
										}else{
											information += " "+ additionalInfo5.getText();
											checkSwitch = 1;
										}
									}
									if (field.equals("Ceiling Insulation") || field.equals("Wall Insulation")){
										if (additionalInfo6.isChecked()){
										information += " " + additionalInfo6.getText();
										checkSwitch = 1;
										}
									}
									
									information += " " + field;
									if (field.equals("Baseboard Removed")){
										if(checkbox_areaSaved.isChecked()){
											information += " and Disposed";
											checkSwitch = 1;
										}
										if(checkbox_areaDisposed.isChecked()){
											information += " and Saved";
											checkSwitch = 1;
										}
										
									}
									if (!field.equals("Ceiling Insulation") && !field.equals("Wall Insulation")){
									
									
										if (checkbox_area1.isChecked()){
											information += " - " + checkbox_area1.getText();
											checkSwitch=1;
										}
										if (!field.equals("Ceiling Removed")){
											if (checkbox_area2.isChecked()){
											information += " - " + checkbox_area2.getText();
											checkSwitch=1;
											}
										}
									}
										if(field.equals("Drywall Removed") || field.equals("Paneling Removed")){
											if (checkbox_area3.isChecked()){
												information += " - " + checkbox_area3.getText();
												checkSwitch=1;
											}									
										}
										if(field.equals("Baseboard Removed")){
											if (checkbox_area3.isChecked()){
												information += " - " + checkbox_area3.getText();
												checkSwitch=1;
											}
											if (checkbox_area4.isChecked()){
												information += " - " + checkbox_area3.getText();
												checkSwitch=1;
											}
										
											if (! edittext_areaLF.getText().toString().isEmpty()){
												information += " - " + edittext_areaLF.getText().toString() + "LF";
											}
										}else{
									
											if (! edittext_areaLength.getText().toString().isEmpty()){
												information += " [ " + edittext_areaLength.getText().toString() + " x " +edittext_areaWidth.getText().toString() + " sq ft ]";
												checkSwitch = 1;
											}}
							}else if (field.equals("Drill Hole")){
								information += " Drying Holes Drilled";
								if (! edittext_areaLF.getText().toString().isEmpty()){
									information += " - " + edittext_areaLF.getText().toString() + "LF";
								}
							}
							else{
								//Sub Flooring
								if (additionalInfo1.isChecked()){
									information += " "+ additionalInfo1.getText();
									checkSwitch = 1;
								}
								if (additionalInfo2.isChecked()){
									information += " "+ additionalInfo2.getText();
									checkSwitch = 1;
								}
								if (additionalInfo3.isChecked()){
									information += " "+ additionalInfo3.getText();
									checkSwitch = 1;
								}
								if (additionalInfo4.isChecked()){
									information += " "+ additionalInfo4.getText();
									checkSwitch = 1;
								}
								
								if (additionalInfo5.isChecked()){
									information += " "+ additionalInfo5.getText();
									checkSwitch = 1;
								}
								if (additionalInfo6.isChecked()){
									information += " "+ additionalInfo3.getText();
									checkSwitch = 1;
								}
								if (additionalInfo7.isChecked()){
									information += " "+ additionalInfo4.getText();
									checkSwitch = 1;
								}
								
								if (additionalInfo8.isChecked()){
									information += " "+ additionalInfo5.getText();
									checkSwitch = 1;
								}
								
								information += " " + field;
								
								if (checkbox_area1.isChecked()){
									information += " - " + checkbox_area1.getText();
									checkSwitch=1;
								}
								if (checkbox_area2.isChecked()){
									information += " - " + checkbox_area2.getText();
									checkSwitch=1;
								}
								
								if (! edittext_areaLength.getText().toString().isEmpty()){
									information += " [ " + edittext_areaLength.getText().toString() + " x " +edittext_areaWidth.getText().toString() + " sq ft ]";
									checkSwitch = 1;
								}
							}
							
								
						}

					
						
						if(checkAreaSwitch == 1){
							information = information.substring(0, information.length()-2);
						}
						
						
						mListener.onFormDialogPositiveClick(
								FormDialogFragment.this,field, information, position);
						dialog.dismiss();
						
					}
					
				});

		return builder.create();

	}
}