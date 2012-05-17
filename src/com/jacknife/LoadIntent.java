package com.jacknife;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class LoadIntent extends Activity{
			//private String note="";
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
	        setContentView(R.layout.load_layout);
	        Typeface font1 = Typeface.createFromAsset(getAssets(), "fonts/RobotoLight.ttf");
	        TextView appHeader = (TextView)findViewById(R.id.appHeader);
	        appHeader.setTypeface(font1);
	        ListView listView = (ListView) findViewById(R.id.fileList);
	        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
	        	android.R.layout.simple_list_item_1, android.R.id.text1, getNotesList());
	        listView.setAdapter(adapter);
	        listView.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
					//Utils.makeToast(((TextView)view).getText().toString(), getApplicationContext());
					loadNote(((TextView)view).getText().toString());
				}  
	        	
	        });  
	    }
	 
	 	public ArrayList<String> getNotesList(){
	 		ArrayList<String> noteList = new ArrayList<String>();
	 		try
	 		{
	 			File root = new File(Environment.getExternalStorageDirectory(), "Notes");
	            if (!root.exists()) {
	                root.mkdirs();
	            }
	            File fileList[] = root.listFiles();
	            if (fileList.length<1){
	            	Utils.makeToast("No Notes found. Go write some!", getApplicationContext());
	            	this.finish();
	            }
	            for(int i=0;i<fileList.length;i++){
	            	noteList.add(fileList[i].getAbsolutePath());
	            }
	 		}
	 		catch(Exception e){
	 			 e.printStackTrace();
	             String importError = e.getMessage();
	             Utils.makeToast(importError, getApplicationContext());
	 		}
			return noteList;
		}

	 	public void loadNote(String noteName){
	 		File noteFile = new File(noteName);
	 		//Read text from file
	 		StringBuilder text = new StringBuilder();
	 		//In this section we attempt to read the file into the text variable
	 		try {
	 		    BufferedReader br = new BufferedReader(new FileReader(noteFile));
	 		    String line;
	 		    while ((line = br.readLine()) != null) {
	 		        text.append(line);
	 		        text.append('\n');
	 		    }
	 		}
	 		catch (IOException e) {
	 		    e.printStackTrace();
	 		    Utils.makeToast("Sorry there was an I/O error!", getApplicationContext());
	 		}
	 		//Make a bundle and add value to Pass back to main intent
	 		//Utils.makeToast(text.toString(), getApplicationContext());
		 	setResult(RESULT_OK, new Intent().putExtra("note", text.toString()));
		 	finish();
	 	}
}
