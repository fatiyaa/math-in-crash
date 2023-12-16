package com.mathincrash.ui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import com.mathincrash.util.Sound;

public class SoundButton implements Drawable{
	

    public static final int normal = 0;
    public static final int hovered = 1;
    public static final int clicked = 2;
    public static final int submitted = 3;
    
	private GamePanel gp;
	public int state;
	public boolean active;
	private int x, y, width, height;
    private Color bgColor;
    private Color shaderColor;
    
	public SoundButton(GamePanel gp, int x, int y){
		this.gp = gp;
		this.x = x;
		this.y = y;
		width = gp.tileSize/2;
		height = gp.tileSize/2;
		this.state = normal;
        this.bgColor = new Color(114, 117, 27);
        this.shaderColor = new Color(0, 0, 0, 0.25F);
        active = false;
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		 if (gp.mouseH.mouseX < x || gp.mouseH.mouseX > x+width || gp.mouseH.mouseY < y || gp.mouseH.mouseY > y+height) {
			 this.state = normal;
			 return;
		 }

		 if (state == clicked && gp.mouseH.mouseReleased == true) {
			 if(active == true) {
				 active = false;
				 gp.bgm.playLoop(Sound.bgmGame);
//				 System.out.println("on");
			 }
			 else {
//				 System.out.println("off");
				 active = true;
				 gp.bgm.stop();
			 }
			 this.state = submitted;
		 }
	     else if (gp.mouseH.mousePressed == true) this.state = clicked;
	     else this.state = hovered;   
		 
		
	}
	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		 g.setColor(shaderColor);
	     g.drawRoundRect(x+2, y+2, width, height, 5, 5);
	     
	     if(active && state == hovered) {
	    	 g.setColor(bgColor.darker());
//	    	 System.out.println("line");
	     }
	     else if(active) {
	    	 g.setColor(bgColor.darker().darker());
	     }
	     else if (state == hovered) {
	    	 g.setColor(bgColor.darker());
	     }
	     else if (state == normal) g.setColor(bgColor);
	     else if (state == clicked) g.setColor(bgColor.darker().darker());
	     g.fillRoundRect(x, y, width, height, 5, 5);
	     
	     g.setColor(Color.WHITE);
	     g.drawRoundRect(x, y, width, height, 5, 5);
	     if(active == true) {
	    	 g.setColor(Color.red);
	    	 Graphics2D g2 = (Graphics2D) g;
	    	 g2.setStroke(new BasicStroke(3)); 
	    	 g2.drawLine(x, y, x+width, y+height);
//	    	 gp.bgm.stop();
//	    	 
	     }
//	     }else {
//	    	 gp.bgm.playLoop(Sound.bgmGame);
//	     }
	     
	}
	
	

}
