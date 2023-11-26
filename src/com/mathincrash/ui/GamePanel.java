package com.mathincrash.ui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javax.swing.JPanel;

import com.mathincrash.onroad.Obstacle;
import com.mathincrash.onroad.Vehicle;

public class GamePanel extends JPanel implements Runnable{

	public final int tile = 32;
	public final int scale = 3;
	public final int tileSize = tile*scale;
	public final int maxTileRow = 8;
	public final int maxTileCol = 7;
	public final int refreshRate = 120;
	
	public final int screenWidth = maxTileCol*tileSize;
	public final int screenHeight = maxTileRow*tileSize;
	private Random random;
	
	public Thread thread;
	public KeyHandler keyH;
    public MouseHandler mouseH;
	
	public Vehicle vehicle;
	public ArrayList <Obstacle> obstacles;
	
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.vehicle = new Vehicle(this);
		this.obstacles = new ArrayList<Obstacle>();
		this.random = new Random();
		makeObstacle(1);
		this.keyH = new KeyHandler(this);
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}
	
	public void makeObstacle(int n) {
		if(obstacles.isEmpty()) {
			Obstacle ob = new Obstacle(this);
//			System.out.println("added");
			obstacles.add(ob);
		}
		while(obstacles.size() < n) {
			Obstacle ob = new Obstacle(this, -tileSize);
			boolean crash = false;
			for(Obstacle obstacle : obstacles) {
				if(ob.crashed(obstacle))crash = true;
			}
			if(!crash) {
				obstacles.add(ob);
//				System.out.println("added");
			}
		}
		
	}
	
	
    public void startThread() {
        this.thread = new Thread(this);
        this.thread.start();
    }
	
	@Override
	public void run() {
		double delta = 0;
        double drawInterval = 1e9 / refreshRate;
        long lastTime = System.nanoTime();
        long currentTime;

        while (this.thread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime-lastTime) / drawInterval;
            lastTime = currentTime;

            if (delta >= 1) {
                this.repaint();
                delta--;
            }
        }
		
	}
	
    public void update() {
    	boolean p = false;
    	for(Iterator<Obstacle> i = obstacles.iterator(); i.hasNext();) {
    		Obstacle obstacle = i.next();
    		obstacle.update();
			if(!obstacle.onScreen()) {
				i.remove();
			}
			else {
				p = true;
			}
		}
    	if (p) makeObstacle(random.nextInt(1,3));
    	System.out.println(obstacles.size());
    }
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.update();
		for(Obstacle obstacle : obstacles) {
			obstacle.draw(g);
		}
		Graphics2D g2d = (Graphics2D) g;
		this.vehicle.draw(g2d);
	}
	
	

}
