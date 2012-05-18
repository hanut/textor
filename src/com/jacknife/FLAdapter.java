package com.jacknife;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class FLAdapter extends ArrayAdapter<Note>{
	 Context context; 
	    int layoutResourceId;    
	    Note data[] = null;
	    
	    public FLAdapter(Context context, int layoutResourceId, Note[] data) {
	        super(context, layoutResourceId, data);
	        this.layoutResourceId = layoutResourceId;
	        this.context = context;
	        this.data = data;
	    }

	    @Override
	    public View getView(int position, View convertView, ViewGroup parent) {
	        View row = convertView;
	        NoteHolder holder = null;
	        
	        if(row == null)
	        {
	            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
	            row = inflater.inflate(layoutResourceId, parent, false);
	            
	            holder = new NoteHolder();
	            holder.imgIcon = (ImageView)row.findViewById(R.id.imgIcon);
	            holder.txtTitle = (TextView)row.findViewById(R.id.txtTitle);
	            row.setTag(holder);
	        }
	        else
	        {
	            holder = (NoteHolder)row.getTag();
	        }
	        
	        Note note = data[position];
	        holder.txtTitle.setTextColor(Color.BLACK);
	        holder.txtTitle.setText(note.getName());
	        holder.imgIcon.setImageResource(R.drawable.ic_note);
	        
	        return row;
	    }
	    
	    static class NoteHolder
	    {
	        ImageView imgIcon;
	        TextView txtTitle;
	    }
}