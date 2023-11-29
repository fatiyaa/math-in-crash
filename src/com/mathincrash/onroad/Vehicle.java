package com.mathincrash.onroad;

import java.awt.Graphics;
import java.awt.Image;

import com.mathincrash.ui.Drawable;
import com.mathincrash.ui.GamePanel;
import com.mathincrash.util.MakeImage;

public class Vehicle extends Crashable implements Drawable{
	public static final int LEFT = 1;
	public static final int MID = 2;
	public static final int RIGHT = 3;
	private int position;
	private GamePanel gp;
	private Image car;
	
	public Vehicle(GamePanel gp) {
		super((gp.maxTileCol-1)/2* gp.tileSize, (gp.maxTileRow-1)*gp.tileSize, gp.tileSize, gp.tileSize);
		this.gp = gp;
		this.position = MID;

		String carPath = "assets/vehicle/Muscle.png";
		this.car = new MakeImage(carPath,gp.tileSize,gp.tileSize).getImage();
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
		g.drawImage(car, x, y, null);
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
