package com.mathincrash.math;

import java.awt.Graphics;

import com.mathincrash.onroad.Crashable;
import com.mathincrash.ui.Drawable;
import com.mathincrash.ui.GamePanel;

public class CountObject extends Crashable implements Drawable{
	private GamePanel gp;
	public int val;
	float centerX, centerY;
	public boolean status = false;
	private int offsetX, offsetY;
	
	public CountObject(GamePanel gp, int x, int y, int val) {
		super(x, y, gp.tile, gp.tile);
		this.gp = gp;
		this.val = val;
		centerX = x+ width/2;
		centerY = y+height/2;
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
			offsetY = gp.mouseH.mouseY - y;
		}
		
		
	}

	public void moved(int dX, int dY) {
		x = gp.mouseH.mouseX - dX;
		y = gp.mouseH.mouseY - dY;
	}
	
	@Override
	public void draw(Graphics g) {
		g.drawRect(x, y, width, height);
		
	}
	

}
