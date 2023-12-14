package com.mathincrash.util;

import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import com.mathincrash.ui.Drawable;
import com.mathincrash.ui.GamePanel;

public class HighScore implements Drawable{

	    private String highScoreFile;
	    private GamePanel gp;
	    public int highScore;
	    
	    public HighScore(String highScoreFile, GamePanel gp) {
	        this.highScoreFile = highScoreFile;
	        this.gp = gp;
	        loadHighScore();
	    }

	    // Method untuk menyimpan highscore ke file
	    public void saveHighScore(int highScore) {
	        try (PrintWriter writer = new PrintWriter(new FileWriter(highScoreFile))) {
	            writer.print(highScore);
	            writer.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	    // Method untuk memuat highscore dari file
	    public void loadHighScore() {
	        try (BufferedReader reader = new BufferedReader(new FileReader(highScoreFile))) {
	            String line = reader.readLine();
	            if (line != null && !line.isEmpty()) {
	                highScore = Integer.parseInt(line);
	                reader.close();
	            }
	        } catch (IOException | NumberFormatException e) {
	            e.printStackTrace();
	        }
	    }

		@Override
		public void update() {
			
			
		}

		@Override
		public void draw(Graphics g) {
			// TODO Auto-generated method stub
			String strPoint = "High Score: " + Integer.toString(highScore);
			int xDraw = gp.screenWidth - g.getFontMetrics().stringWidth(strPoint) - 10;
			int yDraw = 30;
			g.drawString(strPoint, xDraw, yDraw);
		}
}
