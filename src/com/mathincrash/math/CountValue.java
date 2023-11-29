package com.mathincrash.math;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import com.mathincrash.ui.Drawable;
import com.mathincrash.util.MakeImage;

public class CountValue implements Drawable{
	private int x, y, width, height;
	private int value;
	private int xDraw, yDraw;
	private Image board;
	
	public CountValue(int x, int y, int width, int height, int value) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.value = value;

		String imagePath = "assets/math/Blackboard.png";
		this.board = new MakeImage(imagePath, width, height).getImage();
	}
	
	public void setValue(int value) {
		this.value = value;
	}
	
	@Override
	public void update() {

	}

	@Override
	public void draw(Graphics g) {
		Font font = new Font("Arial", Font.PLAIN, 22);
		g.setFont(font);
		g.setColor(Color.WHITE); 

		String strValue = Integer.toString(value);
		xDraw = x + (width - g.getFontMetrics().stringWidth(strValue))/2;
		yDraw = y + (height/2 - g.getFontMetrics().getHeight()/2 + g.getFontMetrics().getAscent());

		g.drawImage(board, x, y , null);
		g.drawString(strValue, xDraw, yDraw);
	}
}
