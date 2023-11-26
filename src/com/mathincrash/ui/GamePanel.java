package com.mathincrash.ui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import com.mathincrash.obstacle.Obstacle;
import com.mathincrash.vehicle.Vehicle;

public class GamePanel extends JPanel implements Runnable{

	public final int tile = 32;
	public final int scale = 3;
	public final int tileSize = tile*scale;
	public final int maxTileRow = 8;
	public final int maxTileCol = 7;
	
	public final int screenWidth = maxTileCol*tileSize;
	public final int screenHeight = maxTileRow*tileSize;
	
	public Thread thread;
	public KeyHandler keyH;
    public MouseHandler mouseH;
	
	public Vehicle vehicle;
	public Obstacle obstacle;
	
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.vehicle = new Vehicle(this);
		this.obstacle = new Obstacle(this);
		this.keyH = new KeyHandler(this);
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}
	
    public void startThread() {
        this.thread = new Thread(this);
        this.thread.start();
    }
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
    public void update() {
        this.obstacle.update();
    }
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.update();
		this.obstacle.draw(g);
		Graphics2D g2d = (Graphics2D) g;
		this.vehicle.draw(g2d);
	}
	
	

}
