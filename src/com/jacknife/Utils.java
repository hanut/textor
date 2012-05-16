package com.jacknife;

import android.content.Context;
import android.os.Environment;
import android.widget.Toast;

public class Utils {

    public static boolean checkExtReady(){
    	boolean mExtAvail = false;
    	boolean mExtWriteAvail = false;
    	String state = Environment.getExternalStorageState();
    	if (Environment.MEDIA_MOUNTED.equals(state)) {
    	    // We can read and write the media
    	    mExtAvail = mExtWriteAvail = true;
    	} else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
    	    // We can only read the media
    	    mExtAvail = true;
    	    mExtWriteAvail = false;
    	} else {
    	    // Something else is wrong. It may be one of many other states, but all we need
    	    //  to know is we can neither read nor write
    	    mExtAvail = mExtWriteAvail = false;
    	}
    	if(mExtAvail==true && mExtWriteAvail==true)
    		return true;
    	else
    		return false;
    }
    
    public static void makeToast(String msg,Context context){
    	CharSequence text = msg;
    	int duration = Toast.LENGTH_SHORT;
    	Toast toast = Toast.makeText(context, text, duration);
    	toast.show();
    }

}
