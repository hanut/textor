package com.jacknife;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class LoadIntent extends Activity{
	private ArrayList<Note> noteList = new ArrayList<Note>(); 
	
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
	        setContentView(R.layout.load_layout);
	        //Typeface font1 = Typeface.createFromAsset(getAssets(), "fonts/RobotoLight.ttf");
	        //TextView appHeader = (TextView)findViewById(R.id.appHeader);
	        //appHeader.setTypeface(font1);
	        
	        ListView listView = (ListView) findViewById(R.id.fileList);
	        noteList = getNotesList();
	        View header = (View)getLayoutInflater().inflate(R.layout.fl_header_row, null);
	        listView.addHeaderView(header);
	        Note tmp[] = new Note[noteList.size()];
	        for(int i=0;i<noteList.size();i++){
	        	tmp[i] = (Note)noteList.get(i);
	        	//System.err.println(noteList.get(i));
	        }
	        FLAdapter adapter = new FLAdapter(this,R.layout.fl_item, tmp);
	        listView.setAdapter(adapter);
	        
	        
	        /*ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
	        	android.R.layout.simple_list_item_1, android.R.id.text1, getNotesList());
	        listView.setAdapter(adapter);*/
	        listView.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
					//Utils.makeToast(((TextView)view).getText().toString(), getApplicationContext());
					loadNote(getPath(position-1));
				}  
	        	
	        });
	        
	    }
	 
	 	public ArrayList<Note> getNotesList(){
	 		ArrayList<Note> noteList = new ArrayList<Note>();
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
	            	//this.filePaths.add(fileList[i].getAbsolutePath());
	            	//noteList.add(fileList[i].getName());
	    	 		Note tmp = new Note();
	            	tmp.setName(fileList[i].getName());
	            	tmp.setPath(fileList[i].getAbsolutePath());
	            	noteList.add(i,tmp);
	            	//System.err.println(tmp.getName());
	            	//System.err.println(noteList.get(i).getName());
	            }
	 		}
	 		catch(Exception e){
	 			 e.printStackTrace();
	             String importError = e.getMessage();
	             Utils.makeToast(importError, getApplicationContext());
	 		}
	 		/*for(int i=0;i<noteList.size();i++){
	 			System.err.println(noteList.get(i).getName());
	 		}*/
			return noteList;
		}

	 	public void loadNote(String notePath){
	 		File noteFile = new File(notePath);
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
	 	
	 	public String getPath(int position){
	 		return this.noteList.get(position).getPath();
	 	}

}
