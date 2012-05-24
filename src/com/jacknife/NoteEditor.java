package com.jacknife;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;

public class NoteEditor extends Activity{
		private String note = "";
		private String fileName = "";
		private Note noteObj;
	
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
	        setContentView(R.layout.editor_layout);
	        //Typeface font1 = Typeface.createFromAsset(getAssets(), "fonts/RobotoLight.ttf");
	        //TextView appHeader = (TextView)findViewById(R.id.appHeader);
	        //appHeader.setTypeface(font1);
	        Bundle b = getIntent().getExtras();
	         this.note = b.getString("note");
	         this.fileName = b.getString("file name");
	         TextView tv = (TextView)findViewById(R.id.appHeader);
	         tv.setText(note);
	    }
	 
	 public boolean onCreateOptionsMenu(Menu menu) {
	        MenuInflater inflater = getMenuInflater();
	        inflater.inflate(R.menu.editor_menu, menu);
	        return true;
	    }
	 
	    public void saveNote(View v){
	    	String value = ((EditText)findViewById(R.id.noteBox)).getText().toString();
	    	Intent intent = new Intent(getApplicationContext(), SaveIntent.class);
	    	intent.putExtra("note", this.note);
	    	intent.putExtra("file name", this.fileName);
	        startActivity(intent);  
	    }
	    
	    public void clear(View v){
	    	EditText et = (EditText)findViewById(R.id.noteBox);
	    	et.setText("");
	    }

}
