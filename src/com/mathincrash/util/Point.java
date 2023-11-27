package com.mathincrash.util;

import java.awt.Graphics;

import com.mathincrash.ui.Drawable;
import com.mathincrash.ui.GamePanel;

public class Point implements Drawable{
	private int point = 0;
	private int x, y, width, height;
	private int xDraw, yDraw;
	
	public Point(GamePanel gp) {
		width = gp.tileSize;
		height = gp.tileSize/2;
		x = gp.screenWidth - width -10;
		y = 10;
	}
	
	@Override
	public void update() {
		point+=10;
		
	}

	@Override
	public void draw(Graphics g) {
		g.drawRect(x, y, width, height);
		String strPoint = Integer.toString(point);
		xDraw = x + (width - g.getFontMetrics().stringWidth(strPoint))/2;
		yDraw = y + (height/2 - g.getFontMetrics().getHeight()/2 + g.getFontMetrics().getAscent());
		g.drawString(strPoint, xDraw, yDraw);
		
	}
	
}
