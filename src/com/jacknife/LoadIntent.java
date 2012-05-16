package com.jacknife;

import java.io.File;
import java.util.ArrayList;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Environment;
import android.view.Window;
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
	        //getNotesList();
	        /*String[] values = new String[] { "Android", "iPhone", "WindowsMobile",
	        	"Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
	        	"Linux", "OS/2" };*/

	        // First paramenter - Context
	        // Second parameter - Layout for the row
	        // Third parameter - ID of the View to which the data is written
	        // Forth - the Array of data
	        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
	        	android.R.layout.simple_list_item_1, android.R.id.text1, getNotesList());

	        // Assign adapter to ListView
	        listView.setAdapter(adapter);
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

}
