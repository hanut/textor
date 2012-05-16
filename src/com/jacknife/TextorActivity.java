package com.jacknife;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;

public class TextorActivity extends Activity {
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
            startActivity(intent);  
    	}
    	else{
    		Utils.makeToast("External Storage I/O Error!",getApplicationContext());
    	}
    }
    
    public void saveText(View v){
    	String value = ((EditText)findViewById(R.id.txtBox)).getText().toString();
    	Intent intent = new Intent(getApplicationContext(), SaveIntent.class);
    	intent.putExtra("note", value);
        startActivity(intent);  
    }
    
    public void cancel(View v){
    	EditText et = (EditText)findViewById(R.id.txtBox);
    	et.setText("");
    }
}