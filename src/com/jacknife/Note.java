package com.jacknife;

public class Note{
    private String path;
    private String name;
    private String text;
    
    
    public Note(){
        super();
    }
    
    public Note(String name,String path,String text) {
        super();
        this.setPath(path);
        this.setName(name);
        this.setText(text);
    }

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
