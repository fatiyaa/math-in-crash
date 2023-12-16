package com.mathincrash.onroad;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import com.mathincrash.ui.Drawable;
import com.mathincrash.ui.GamePanel;


public class Obstacle extends Crashable implements Drawable{
	public static final int LEFT = 1;
	public static final int MID = 2;
	public static final int RIGHT = 3;
	private GamePanel gp;
	
	private Random random  = new Random();
	
	public Obstacle(GamePanel gp) {
		super(0, 0, gp.tileSize, gp.tileSize/2);
		x = (random.nextInt(RIGHT)+2)*gp.tileSize;
		this.gp = gp;
		
	}

	public Obstacle(GamePanel gp, int y) {
		super(0, y, gp.tileSize, gp.tileSize/2);
		x = (random.nextInt(RIGHT)+2)*gp.tileSize;
		this.gp = gp;
		
	}
	
	@Override
	public void update() {
		y+=gp.speed;
		y = (double)Math.round(y*1e3)/1e3;
//		System.out.println("obs= "+gp.speed);
		
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(new Color(0,0,0));
		g.drawRect(x, (int)y, width, height);
		
	}
	
	public boolean onScreen() {
		return (int)this.y < gp.screenHeight;
//		return this.y > 0 && this.y < gp.screenHeight;
	}

}
