package com.contech.dailyworkorders;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.contech.dailyworkorders.FileLoaderFragment.NoticeDialogListener;
import com.contech.dailyworkorders.R.id;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;



public class AddWorkerFragment extends DialogFragment {
	// global class variables
	EditText wNameSection, wTimeIn, wTimeOut, wTimeInM, wTimeOutM, wTotalTime;
	CheckBox didComplete;
	String wName,info;
	/* TO SEND DATA BACK TO ACTIVITY */
	public interface NoticeDialogListener {
		public void onAddWorkerFragmentPositiveClick(DialogFragment dialog,
				String info, boolean didComplete);
	}

	NoticeDialogListener mListener;

	public static AddWorkerFragment newInstance() {
		AddWorkerFragment frag = new AddWorkerFragment();
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
				R.layout.fragment_add_worker, null);
		builder.setView(view);
		builder.setTitle("Add Worker");
		
			didComplete = (CheckBox)view.findViewById(R.id.completedBy);
			didComplete.setChecked(false);
			builder.setPositiveButton(
					"Add",
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							wTimeIn = (EditText)  ((AlertDialog) dialog).findViewById(R.id.wTimeInHour);
							wTimeOut = (EditText)  ((AlertDialog) dialog).findViewById(R.id.wTimeOutHour);
							wTimeInM = (EditText)  ((AlertDialog) dialog).findViewById(R.id.wTimeInMin);
							wTimeOutM = (EditText)  ((AlertDialog) dialog).findViewById(R.id.wTimeOutMin);
							wNameSection = (EditText)  ((AlertDialog) dialog).findViewById(R.id.wNameSection);
							wTotalTime = (EditText)  ((AlertDialog) dialog).findViewById(R.id.wTotal);
							info="";
							wName="";
							wName = wNameSection.getText().toString();
							String timeIn = wTimeIn.getText().toString() +":"+wTimeInM.getText().toString();
							String timeOut = wTimeOut.getText().toString() +":"+wTimeOutM.getText().toString();
							String totalTime = wTotalTime.getText().toString();
							
							info = wName+" "+timeIn+" - "+timeOut+ "  Total: "+totalTime;
							mListener.onAddWorkerFragmentPositiveClick(
									AddWorkerFragment.this, info, didComplete.isChecked());
							dialog.dismiss();
						}
			
			});
			return builder.create();
    }
}
