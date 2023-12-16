package com.mathincrash.onroad;

public class Crashable {
	public double y;
	public int width, height, x;
	
	public Crashable(int x, double y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public boolean crashed(Crashable crash) {
//		System.out.println("crashed");
		return (x+width > crash.x && y+height > crash.y && crash.x+crash.width > x && crash.y+crash.height > y);
	}
}
