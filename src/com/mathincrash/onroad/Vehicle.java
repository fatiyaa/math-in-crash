package com.mathincrash.onroad;

import java.awt.Color;
import java.awt.Graphics;

import com.mathincrash.ui.Drawable;
import com.mathincrash.ui.GamePanel;

public class Vehicle extends Crashable implements Drawable{
	public static final int LEFT = 1;
	public static final int MID = 2;
	public static final int RIGHT = 3;
	private int position;
	private GamePanel gp;
	
	public Vehicle(GamePanel gp) {
		super((gp.maxTileCol-1)/2* gp.tileSize, (gp.maxTileRow-1)*gp.tileSize, gp.tileSize, gp.tileSize);
		this.gp = gp;
		this.position = MID;
	}

	@Override
	public void update() {
		for(Obstacle ob : gp.obstacles) {
			if(this.crashed(ob)) {
				gp.gameState = GamePanel.mathState;
			}
		}
		
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(new Color(0,0,0));
		g.drawRect(x, y, width, height);
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
