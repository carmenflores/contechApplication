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
import android.widget.TextView;
import android.widget.Toast;



public class FileLoaderFragment extends DialogFragment {
	// global class variables
	TextView textView;
	String pathName, name, address, jobNum;
	BufferedReader reader;
	/* TO SEND DATA BACK TO ACTIVITY */
	public interface NoticeDialogListener {
		public void onFileLoaderDialogPositiveClick(DialogFragment dialog,
				String jobNumber, String address, String Insured);
	}

	NoticeDialogListener mListener;

	public static FileLoaderFragment newInstance(String path) {
		FileLoaderFragment frag = new FileLoaderFragment();
		Bundle args = new Bundle();
		args.putString("path", path);
		frag.setArguments(args);
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
				R.layout.fragment_file_loader, null);
		builder.setView(view);
		builder.setTitle("File Loader");
		

		textView = (TextView) view.findViewById(R.id.pathname);
		textView.setText("File Loaded Successfully");
		pathName = getArguments().getString("path");
			try {
				reader = null;
				reader = new BufferedReader(new FileReader(pathName));
				String line = null;
				int counter = 0;
				String[] pathArray =pathName.split("/");
				jobNum = pathArray[pathArray.length-1];
				jobNum = jobNum.substring(0, jobNum.length()-4);
				while ((line = reader.readLine()) != null) {
					if (counter == 0){
						name=line;
					}
					if (counter == 1){
						address=line;
					}
					if(counter >= 2){
						break;
					}
					counter ++;
				}
				reader.close();
			}
			catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
			} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
		    }
			builder.setPositiveButton(
					"Okay",
					new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							mListener.onFileLoaderDialogPositiveClick(FileLoaderFragment.this, name, address, jobNum);
							dialog.dismiss();
						}
			
			});
			return builder.create();
    }
}
