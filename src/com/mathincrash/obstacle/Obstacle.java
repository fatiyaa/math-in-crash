package com.mathincrash.obstacle;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import com.mathincrash.ui.Drawable;
import com.mathincrash.ui.GamePanel;
import com.mathincrash.onroad.Vehicle;

public class Obstacle implements Drawable{
	public static final int LEFT = 1;
	public static final int MID = 2;
	public static final int RIGHT = 3;
	private GamePanel gp;
	
	private Random random  = new Random();
	private int width, height;
	private int x, y=0;
	float speedY = 1;
	
	public Obstacle(GamePanel gp) {
		this.gp = gp;
		this.width = gp.tileSize;
		this.height = gp.tileSize/2;
		this.x = (random.nextInt(RIGHT)+2) * gp.tileSize;
		
	}
	
	@Override
	public void update() {
		y+=speedY;
		gp.repaint();
		
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(new Color(0,0,0));
		g.drawRect(x, y, width, height);
		
	}
	
	public void isCollide(Vehicle vehicle) {
		
	}

}
