package com.jacknife;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

public class TextorActivity extends Activity {
	
	static int NOTE_RETRIEVAL = 0;
	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main);
    }
    
    public void quitApp(View v){
    	finish();
        System.exit(0);    	
    }
    
    public void loadText(View v){
    	if(Utils.checkExtReady()){
    		//Utils.makeToast("All set! Ready to I/O",getApplicationContext());
    		Intent intent = new Intent(getApplicationContext(), LoadIntent.class);
            startActivityForResult(intent, NOTE_RETRIEVAL);  
    	}
    	else{
    		Utils.makeToast("External Storage I/O Error!",getApplicationContext());
    	}
    }
    
    public void newNote(View v){
    	Intent intent = new Intent(getApplicationContext(), NoteEditor.class);
    	intent.putExtra("note", "");
    	intent.putExtra("file name", "untitled.txt");
        startActivity(intent);  
    }/*
    
    public void cancel(View v){
    	EditText et = (EditText)findViewById(R.id.txtBox);
    	et.setText("");
    }*/

    protected void onActivityResult(int requestCode, int resultCode,
            Intent data) {
        if (requestCode == NOTE_RETRIEVAL) {
            if (resultCode == RESULT_OK) {
            	Bundle b = data.getExtras();
            	Intent intent = new Intent(getApplicationContext(), NoteEditor.class);
            	intent.putExtra("note", b.getString("note"));
            	intent.putExtra("file name", b.getString("file name"));
                startActivity(intent);
            }
        }
    }


}