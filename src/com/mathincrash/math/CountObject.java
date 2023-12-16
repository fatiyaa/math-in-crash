package com.mathincrash.math;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import com.mathincrash.onroad.Crashable;
import com.mathincrash.ui.Drawable;
import com.mathincrash.ui.GamePanel;

public class CountObject extends Crashable implements Drawable{
	private GamePanel gp;
	public int val;
	float centerX, centerY;
	public boolean status = true;
	private int offsetX, offsetY;
	private Image hole;
	private Image bumps;
	
	public CountObject(GamePanel gp, int x, int y, int val) {
		super(x, y, gp.tile, gp.tile);
		this.gp = gp;
		this.val = val;
		centerX = x+ width/2;
		centerY = y+height/2;

		this.hole = new ImageIcon("assets/math/Hole.png").getImage();
		this.bumps = new ImageIcon("assets/math/BUmps.png").getImage();
	}

	@Override
	public void update() {
		if ((gp.mouseH.mouseX < x || gp.mouseH.mouseX > x+width|| gp.mouseH.mouseY < y || gp.mouseH.mouseY > y+height)) {
			this.status = false;
			return;
		}
		
		if(this.status == true && gp.mouseH.mouseReleased == true) this.status = false;
		else if(this.status == true  && gp.mouseH.mousePressed == true) {
//			System.out.println("Drag");
			
			moved(offsetX, offsetY);
		}
		else if (gp.mouseH.mousePressed == true) {
			this.status = true;
			offsetX = gp.mouseH.mouseX - x;
			offsetY = gp.mouseH.mouseY - (int)y;
		}
		
		
	}

	public void moved(int dX, int dY) {
		x = gp.mouseH.mouseX - dX;
		y = gp.mouseH.mouseY - dY;
	}
	
	@Override
	public void draw(Graphics g) {		
	}
	
	public void drawHole(Graphics g) {
		g.drawImage(hole,x, (int)y, width, height, null);
	}
	
	public void drawBumps(Graphics g) {
		g.drawImage(bumps,x, (int)y, width, height, null);
	}
	

}
