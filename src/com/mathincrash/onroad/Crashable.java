package com.mathincrash.onroad;

public class Crashable {
	protected int x,y;
	protected int width, height;
	
	public Crashable(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public boolean crashed(Crashable crash) {
		System.out.println("crashed");
		return (x+width > crash.x && y+height > crash.y && crash.x+crash.width > x && crash.y+crash.height > y);
	}
}
