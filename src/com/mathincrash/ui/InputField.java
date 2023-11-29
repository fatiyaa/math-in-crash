package com.mathincrash.ui;

import java.awt.Graphics;


public class InputField implements Drawable{
	private GamePanel gp;
	
	private final String inputText;
	 private final int screenX;
	 private final int screenY;
	 private final int width;
	 private final int height;
	 private String text;
	 private String answer = "";

	
	public InputField(GamePanel gp, String text, int screenX, int screenY, int width, int height) {
		this.gp = gp;
        this.screenX = screenX;
        this.screenY = screenY;
        this.width = width;
        this.height = height;
        this.text = text;
        this.inputText = text;
	}
	
	
	public void addInput(String str) {
		this.answer += str;
//		System.out.println(answer);
		this.text = answer;
	}

	public void deleteBack() {
		if(answer.length()>1) {
			this.text = answer.substring(0, answer.length()-1);
			answer = text;
		}
		else if(answer.length()==1) {
			this.text = inputText;
			answer = "";
		}
	}
	
	public int getAnswer() {
		if(!answer.equals(""))
			return Integer.parseInt(answer);
		return 1000;
	}
	
	@Override
	public void update() {
	}


	@Override
	public void draw(Graphics g) {
		g.drawRect(screenX, screenY, width, height);
		int xDraw = screenX + (width - g.getFontMetrics().stringWidth(text))/2;
		int yDraw = screenY + (height/2 - g.getFontMetrics().getHeight()/2 + g.getFontMetrics().getAscent());
		g.drawString(text, xDraw, yDraw);
		
	}
}
