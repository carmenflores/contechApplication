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

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;



public class FileLoaderActivity extends Activity {
	// global class variables


	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_file_loader);
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		
		// Get the intent that started this activity
		Intent intent = getIntent();
		Uri data = intent.getData();
		// Disply the Type for debug purpose
		TextView textView = (TextView) findViewById(R.id.pathname);
		textView.setText(intent.getData().toString());
		
		// Verify that the type is text file
		if (intent.getType().equals("text/plain")) {
			String path = data.toString();
            BufferedReader reader = null;
            int start= path.indexOf("/mnt");
            path =path.substring(start);
            textView.setText(path);
			try {
				reader = new BufferedReader(new FileReader(path));
				String line = null;
				while ((line = reader.readLine()) != null) {
					Toast.makeText(getApplicationContext(),
							line,
							Toast.LENGTH_SHORT).show();
				}
				}
				catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
	public boolean onOptionsItemSelected(MenuItem item){
	    Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
	    startActivityForResult(myIntent, 0);
	    return true;

	}



	public String getRealPathFromURI(Uri contentUri) {
		String[] proj = { MediaStore.Images.Media.DATA };
		@SuppressWarnings("deprecation")
		Cursor cursor = managedQuery(contentUri, proj, null, null, null);
		int column_index = cursor
				.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
		cursor.moveToFirst();
		return cursor.getString(column_index);
	}
}
