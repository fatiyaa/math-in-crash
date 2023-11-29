package com.mathincrash.math;

import java.awt.Font;
import java.awt.Graphics;
import java.util.Random;

import com.mathincrash.ui.Drawable;
import com.mathincrash.ui.GamePanel;

public class Operator implements Drawable{
	
	private Random rand;
	private int x, y, width, height;
	private int xDraw, yDraw;
	public boolean operator;
	public String stringOperator;
	private Font font;
	
	public Operator(GamePanel gp) {
		width = gp.tileSize;
		height = gp.tileSize;
		x = 3*gp.tileSize;
		y = 2*gp.tileSize + gp.tileSize/2;
		rand = new Random();
		font = new Font(null, Font.PLAIN, 100);
		generate();
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		g.setFont(font);
		xDraw = x + (width - g.getFontMetrics().stringWidth(stringOperator))/2;
		yDraw = y + (height/2 - g.getFontMetrics().getHeight()/2 + g.getFontMetrics().getAscent());
		g.drawString(stringOperator, xDraw, yDraw);
	}
	
	public void generate() {
		operator = rand.nextBoolean();
		if(operator == true) stringOperator = "+";
		else stringOperator = "-";
	}
	

}
