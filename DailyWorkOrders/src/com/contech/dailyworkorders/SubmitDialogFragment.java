package com.contech.dailyworkorders;

import java.util.ArrayList;
import java.util.HashMap;

import com.contech.dailyworkorders.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

/* ------- DIALOG FOR CREATING NEW ROOML ------ */
public class SubmitDialogFragment extends DialogFragment {
	EditText message;
	EditText to;
	EditText cc;
	EditText subject;
	ArrayList<String> formFields;
	/* TO SEND DATA BACK TO ACTIVITY */
	public interface NoticeDialogListener {
		public void onSubmitDialogPositiveClick(DialogFragment dialog);
	}

	NoticeDialogListener mListener;

	public static SubmitDialogFragment newInstance(ArrayList<String> fields, String subject) {
		SubmitDialogFragment frag = new SubmitDialogFragment();
		Bundle args = new Bundle();
		args.putStringArrayList("fields", fields);
		args.putString("subj", subject);
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
		String subj = getArguments().getString("subj");
		message = (EditText) view.findViewById(R.id.info1);
		cc = (EditText) view.findViewById(R.id.editTextcc);
		to = (EditText) view.findViewById(R.id.editTextTo);
		subject = (EditText) view.findViewById(R.id.editTextSubject);
		
		String body = "";
		
		for ( String s : formFields){
			body += s;
		}
		message.setText(body);
		subject.setText(subj);
		builder.setPositiveButton(
				getString(R.string.dialog_positive_submit),
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						mListener.onSubmitDialogPositiveClick(
								SubmitDialogFragment.this);
						String recipient = to.getText().toString();
						String sub = subject.getText().toString();
						String mes = message.getText().toString();
						String ccr = cc.getText().toString();
			            Intent email = new Intent(Intent.ACTION_SEND);
			            email.putExtra(Intent.EXTRA_EMAIL, new String[] { recipient });
			            email.putExtra(Intent.EXTRA_SUBJECT, sub);
			            email.putExtra(Intent.EXTRA_CC, new String[]{ccr});
			            email.putExtra(Intent.EXTRA_TEXT, mes);

			            // need this to prompts email client only
			            email.setType("message/rfc822");

			            startActivity(Intent.createChooser(email, "Choose an Email client :"));

						
						dialog.dismiss();
					}
				});
		builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
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