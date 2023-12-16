package com.mathincrash.state;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import com.mathincrash.ui.Button;
import com.mathincrash.ui.GamePanel;
import com.mathincrash.util.Sound;

public class EndState extends State{

	 private Button homeButton;
	 private Button quitButton;
	
	 public EndState(GamePanel gp) {
	        super(gp);

	        int width = 3*(gp.tileSize-10);
	        int height = gp.tileSize-10;
	        int x = (gp.screenWidth-width)/2;
	        int y = 13*gp.tileSize/4;
	        int yOffset = 3*height/2;
	        
	        this.homeButton = new Button(gp, "MAIN MENU", x, y+=yOffset, width, height);
	        this.quitButton = new Button(gp, "QUIT", x, y+=yOffset, width, height);
	 }
	 
	        
	

	@Override
	public void update() {
		homeButton.update();
	    quitButton.update();
	    if (homeButton.state == Button.submitted) {
			gp.sfx.play(Sound.sfxClick);
			gp.reset();
		}
        if (quitButton.state == Button.submitted) System.exit(0);
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		g.setColor(shaderColor);
        g.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        g.setColor(Color.WHITE);
        g.setFont(g.getFont().deriveFont(Font.BOLD, 70));
        
        String text1 = "GAME OVER";
        String text2 = "SCORE: " + Integer.toString(gp.point.point);
        String text3 = "HIGH SCORE!";
        
        int x = (gp.screenWidth-getLength(g, text1))/2;
        int y = 7*gp.tileSize/4;
        drawShadedText(g, text1, x, y, 3, 3);
        x = (gp.screenWidth-getLength(g, text2))/2;
        y += g.getFontMetrics().getHeight();
        drawShadedText(g, text2, x, y, 3, 3);
        
        if(gp.point.point>gp.highScoreManager.highScore) {
        	gp.highScoreManager.saveHighScore(gp.point.point);
        	x = (gp.screenWidth-getLength(g, text3))/2;
            y += g.getFontMetrics().getHeight();
        	drawShadedText(g, text3, x, y, 3, 3);
        }
        g.setFont(g.getFont().deriveFont(Font.BOLD, 40));
        homeButton.draw(g);
        g.setFont(g.getFont().deriveFont(Font.BOLD, 60));
        quitButton.draw(g);
	}

}
