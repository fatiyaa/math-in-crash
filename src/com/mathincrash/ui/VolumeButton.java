package com.mathincrash.ui;

import java.awt.Color;
import java.awt.Graphics;

public class VolumeButton implements Drawable{
	
	public static final int normal = 0;
    public static final int hovered = 1;
    public static final int clicked = 2;
    public static final int submitted = 3;

	public int volume = 100;

	private GamePanel gp;
	private int wLine, hLine, wBox, hBox;
	private int xLine, yLine, xBox, yBox;
	private int xOffset;
	private Color boxColor;
	
	public int boxState;
	public boolean lineState;
	
	
	public VolumeButton(GamePanel gp) {
		this.wLine = 3*gp.tileSize;
		this.hLine = 8;
		this.hBox = 20;
		this.wBox = 10;
		this.xLine = (gp.screenWidth-2*gp.tileSize)/2;
		this.yLine = 3*gp.tileSize+gp.tileSize/2;
		this.xBox = xLine + wLine - wBox/2;
		this.yBox = yLine - (hBox - hLine)/2;
		this.gp = gp;
		boxState = normal;
		lineState = false;
		boxColor = Color.GREEN;
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		if (gp.mouseH.mouseX < xBox || gp.mouseH.mouseX > xBox+wBox || gp.mouseH.mouseY < yBox || gp.mouseH.mouseY > yBox+hBox) {
			this.boxState = normal;
			if (gp.mouseH.mouseX < xLine || gp.mouseH.mouseX > xLine+wLine || gp.mouseH.mouseY < yLine || gp.mouseH.mouseY > yLine+hLine) {
				this.lineState = false;
				return;
			}
			
			if (lineState == true && gp.mouseH.mouseReleased == true) {
				this.lineState = false;
				xBox = gp.mouseH.mouseX-wBox/2;	
				getVolume();
				this.boxState = submitted;
				
			}else if (gp.mouseH.mousePressed == true) {
	        	this.lineState = true;
	        	
			}
			return;
			
		}

		if (boxState == clicked && gp.mouseH.mouseReleased == true) {
			getVolume();
			this.boxState = submitted;
			
		}
		else if(boxState == clicked && gp.mouseH.mousePressed == true) {
			moved(xOffset);
			
		}else if (gp.mouseH.mousePressed == true) {
        	this.boxState = clicked;
        	xOffset = gp.mouseH.mouseX - xBox;
        	
        }else 
        	this.boxState = hovered;
		
		
	}
	public void moved(int dX) {
		xBox = gp.mouseH.mouseX - dX;
		if(xBox<xLine-wBox/2) xBox = xLine-wBox/2;
    	else if(xBox+wBox/2>xLine+wLine) xBox = xLine+wLine-wBox/2;
	}

	@Override
	public void draw(Graphics g) {

		g.setColor(Color.WHITE);
		g.drawRect(xLine, yLine, wLine, hLine);
		g.fillRect(xLine, yLine, wLine, hLine);
		
		if (boxState == normal) g.setColor(boxColor);
        else if (boxState == hovered) g.setColor(boxColor.darker());
        else if (boxState == clicked) g.setColor(boxColor.darker().darker());
		g.drawRect(xBox, yBox, wBox, hBox);
		g.fillRect(xBox, yBox, wBox, hBox);
		
		
	}
	
	public int getVolume() {
		volume = (xBox+wBox/2-xLine)*100/wLine;
		return volume;
	}
}
