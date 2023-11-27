package com.mathincrash.math;

import java.awt.Graphics;

import com.mathincrash.ui.Drawable;

public class CountValue implements Drawable{

	private int x, y, width, height;
	private int value;
	private int xDraw, yDraw;
	
	public CountValue(int x, int y, int width, int height, int value) {
		this.x = x;
		this.y = y;
		this.value = value;
		this.width = width;
		this.height = height;
	}
	
	public void setValue(int value) {
		this.value = value;
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Graphics g) {
		g.drawRect(x, y, width, height);
		String strValue = Integer.toString(value);
		xDraw = x + (width - g.getFontMetrics().stringWidth(strValue))/2;
		yDraw = y + (height/2 - g.getFontMetrics().getHeight()/2 + g.getFontMetrics().getAscent());
		g.drawString(strValue, xDraw, yDraw);
		
	}

}
