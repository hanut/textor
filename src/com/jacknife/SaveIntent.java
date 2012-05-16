package com.jacknife;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;

public class SaveIntent extends Activity{
			private String note="";
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
	        setContentView(R.layout.save_layout);
	        Typeface font1 = Typeface.createFromAsset(getAssets(), "fonts/RobotoLight.ttf");
	        TextView appHeader = (TextView)findViewById(R.id.appHeader);
	        appHeader.setTypeface(font1);
	        Bundle b = getIntent().getExtras();
	        this.note = b.getString("note");
	    }
	 
	 	public void saveNoteToSD(View v){
	 		if(validateFileName()){
	        try
	        {
	            File root = new File(Environment.getExternalStorageDirectory(), "Notes");
	            if (!root.exists()) {
	                root.mkdirs();
	            }
	            String fileName = (((EditText)findViewById(R.id.fileName)).getText()).toString();
	            File noteFile = new File(root, fileName);
	            FileWriter writer = new FileWriter(noteFile);
	            writer.append(this.note);
	            writer.flush();
	            writer.close();
	            Utils.makeToast("Saved!", getApplicationContext());
	        }
	        catch(IOException e)
	        {
	             e.printStackTrace();
	             String importError = e.getMessage();
	             Utils.makeToast(importError, getApplicationContext());
	        }
	 	   }
	 		else{
	 			Utils.makeToast("Please enter a valid name", getApplicationContext());
	 		}
	 		super.onBackPressed();
	      }

		private boolean validateFileName() {
			// Method to validate the string in the file name field
			String s = (((EditText)findViewById(R.id.fileName)).getText()).toString();
			File f = new File(Environment.getExternalStorageDirectory(), "Notes/"+s);
			if(s.equals("") || f.exists()){
				//Utils.makeToast("NADA", getApplicationContext());
				return false;
				}
			else{
				//Utils.makeToast(s, getApplicationContext());
				return true;
				}	
			
		}

}
