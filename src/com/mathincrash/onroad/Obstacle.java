package com.mathincrash.onroad;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Random;

import javax.swing.ImageIcon;

import com.mathincrash.ui.Drawable;
import com.mathincrash.ui.GamePanel;

public class Obstacle extends Crashable implements Drawable {
	public static final int LEFT = 1;
	public static final int MID = 2;
	public static final int RIGHT = 3;
	public static final int MAXOBST = 4;
	private GamePanel gp;

	private Random random = new Random();
	private Image obstacleImage;
	private int randObst;
	private String obstPath;

	public Obstacle(GamePanel gp) {
		super(0, 0, gp.tileSize, gp.tileSize / 2);
		x = (random.nextInt(RIGHT) + 2) * gp.tileSize;
		this.gp = gp;

	}

	public Obstacle(GamePanel gp, int y) {
		super(0, y, gp.tileSize, gp.tileSize / 2);
		x = (random.nextInt(RIGHT) + 2) * gp.tileSize;
		this.gp = gp;
		loadObstacleImage();
	}

	int a;

	@Override
	public void update() {
		y += gp.speed;
		if (y * 10 % 10 > 4) {
			a = (int) Math.ceil(y);
		} else {
			a = (int) Math.floor(y);
		}
		// System.out.println("obs= "+gp.speed);

	}

	private void loadObstacleImage() {
		randObst = random.nextInt(MAXOBST) + 1;
		obstPath = "assets/obstacle/obstacle" + randObst + ".png";
		ImageIcon ii = new ImageIcon(obstPath);
		obstacleImage = ii.getImage();
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(new Color(0, 0, 0));
		// g.drawRect(x, (int)y, width, height);
		g.drawImage(obstacleImage, x, (int) y, width, height, null);

	}

	public boolean onScreen() {
		return (int) this.y < gp.screenHeight;
		// return this.y > 0 && this.y < gp.screenHeight;
	}

}
