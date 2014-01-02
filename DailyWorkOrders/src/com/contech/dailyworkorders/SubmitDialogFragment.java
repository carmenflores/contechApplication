package com.contech.dailyworkorders;

import java.util.ArrayList;
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
import android.widget.TextView;

/* ------- DIALOG FOR CREATING NEW ROOML ------ */
public class SubmitDialogFragment extends DialogFragment {
	TextView information;
	ArrayList<String> formFields;
	/* TO SEND DATA BACK TO ACTIVITY */
	public interface NoticeDialogListener {
		public void onSubmitDialogPositiveClick(DialogFragment dialog);
	}

	NoticeDialogListener mListener;

	public static SubmitDialogFragment newInstance(ArrayList<String> fields) {
		SubmitDialogFragment frag = new SubmitDialogFragment();
		Bundle args = new Bundle();
		args.putStringArrayList("fields", fields);
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
				R.layout.fragment_submit, null);
		builder.setView(view);
		builder.setTitle("Overview");
		formFields = new ArrayList<String>();
		formFields = getArguments().getStringArrayList("fields");
		information = (TextView) view.findViewById(R.id.info);
		String body = "";
		
		for ( String s : formFields){
			body += s;
		}
		information.setText(body);
		builder.setPositiveButton(
				getString(R.string.dialog_positive_submit),
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						mListener.onSubmitDialogPositiveClick(
								SubmitDialogFragment.this);
						dialog.dismiss();
					}
				});

		return builder.create();

	}
}