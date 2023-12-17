package com.mathincrash.onroad;

import java.awt.Graphics;
import java.awt.Image;

import com.mathincrash.state.MathState;
import com.mathincrash.ui.Drawable;
import com.mathincrash.ui.GamePanel;
import com.mathincrash.util.MakeImage;
import com.mathincrash.util.Sound;

public class Vehicle extends Crashable implements Drawable{
	public static final int LEFT = 1;
	public static final int MID = 2;
	public static final int RIGHT = 3;
	private int position;
	private GamePanel gp;
	private Image[] car;
	public final int typeCar = 6;
	
	public Vehicle(GamePanel gp) {
		super((gp.maxTileCol-1)/2* gp.tileSize, (gp.maxTileRow-1)*gp.tileSize, gp.tileSize, gp.tileSize);
		this.gp = gp;
		this.position = MID;
		gp.theCar = 1;

		car = new Image[typeCar];
		car[0] = new MakeImage("assets/vehicle/Monster Truck.png",gp.tileSize,gp.tileSize).getImage();
		car[1] = new MakeImage("assets/vehicle/Muscle1.png",gp.tileSize,gp.tileSize).getImage();
		car[2] = new MakeImage("assets/vehicle/Taxi.png",gp.tileSize,gp.tileSize).getImage();
		car[3] = new MakeImage("assets/vehicle/Roadster.png",gp.tileSize,gp.tileSize).getImage();
		car[4] = new MakeImage("assets/vehicle/Police.png",gp.tileSize,gp.tileSize).getImage();
		car[5] = new MakeImage("assets/vehicle/Ambulance.png",gp.tileSize,gp.tileSize).getImage();
	}

	@Override
	public void update() {
		for(Obstacle ob : gp.obstacles) {
			if(this.crashed(ob)) {
				gp.sfx.play(Sound.sfxCrash);

				gp.gameState = GamePanel.mathState;
				gp.bgm.stop();
				gp.bgm.playLoop(Sound.bgmMIC);

				MathState inp = (MathState) gp.ui.states[GamePanel.mathState];
				inp.reset();
			}
		}
		
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(car[gp.theCar], x, (int)y, null);
	}

	public void drawCar(Graphics g) {
		g.drawImage(car[gp.theCar], 3 * gp.tileSize, gp.screenHeight/2 - 50 , null);
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

	public void carLeft(GamePanel gp) {
		if (gp.theCar != 0) {
			gp.theCar--;
			gp.repaint();
		}
	}
	
	public void carRight(GamePanel gp) {
		if (gp.theCar != typeCar-1) {
			gp.theCar++;
			gp.repaint();
		}
	}
}
