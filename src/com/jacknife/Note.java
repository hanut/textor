package com.jacknife;

public class Note{
    private String path;
    private String name;
    
    
    public Note(){
        super();
    }
    
    public Note(String name,String path) {
        super();
        this.setPath(path);
        this.setName(name);
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
}
