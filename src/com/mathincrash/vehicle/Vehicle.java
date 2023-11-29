package com.mathincrash.vehicle;

import java.awt.Color;
import java.awt.Graphics;

import com.mathincrash.ui.Drawable;
import com.mathincrash.ui.GamePanel;

public class Vehicle implements Drawable{
	public static final int LEFT = 1;
	public static final int MID = 2;
	public static final int RIGHT = 3;
	private int x, y, rad;
	private int position;
	
	public Vehicle(GamePanel gp) {
		this.position = MID;
		this.x = (gp.maxTileCol-1)/2* gp.tileSize;
		this.y = (gp.maxTileRow-1)*gp.tileSize;
		this.rad = gp.tileSize;
	}

	@Override
	public void update() {
		
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(new Color(0,0,0));
		g.fillOval(x, y, rad, rad);
	}
	
	public void goLeft(GamePanel gp) {
		if (this.position != 1) {
			this.position--;
			this.x -= gp.tileSize;
			gp.repaint();
		}
	}
	
	public void goRight(GamePanel gp) {
		if (this.position != 3) {
			this.position++;
			this.x += gp.tileSize;
			gp.repaint();
		}
	}

}
