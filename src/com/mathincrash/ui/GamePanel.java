package com.mathincrash.ui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

import com.mathincrash.map.Map;
import com.mathincrash.onroad.Obstacle;
import com.mathincrash.onroad.Vehicle;
import com.mathincrash.util.Point;

public class GamePanel extends JPanel implements Runnable{

	public final int tile = 32;
	public final int scale = 3;
	public final int tileSize = tile*scale;
	public final int maxTileRow = 8;
	public final int maxTileCol = 7;
	public final int refreshRate = 120;
	
	public final int maxState = 5;
    public static final int titleState = 0;
    public static final int playState = 1;
    public static final int pauseState = 2;
    public static final int endState = 3;
    public static final int mathState = 4;
	
	public final int screenWidth = maxTileCol*tileSize;
	public final int screenHeight = maxTileRow*tileSize;
	private Random random;
	
	public Thread thread;
	public KeyHandler keyH;
    public MouseHandler mouseH;
<<<<<<< Updated upstream
    public int gameState;
    public UI ui;
    
	public Point point;
=======
	
	public Map map;
>>>>>>> Stashed changes
	public Vehicle vehicle;
	public ArrayList <Obstacle> obstacles;
	
	Timer timer = new Timer(1000, new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			point.update();
		}
	});
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.vehicle = new Vehicle(this);
		this.map = new Map(this);
		this.obstacles = new ArrayList<Obstacle>();
		this.point = new Point(this);
		this.random = new Random();
		makeObstacle(1);
		this.keyH = new KeyHandler(this);
		this.mouseH = new MouseHandler(this);
		this.addKeyListener(keyH);
		this.addMouseListener(mouseH);
        this.addMouseMotionListener(mouseH);
		this.setFocusable(true);
		this.ui = new UI(this);
		
		this.gameState = playState;
	}
	
	public void makeObstacle(int n) {
		if(obstacles.isEmpty()) {
			obstacles.add(new Obstacle(this));
		}
		while(obstacles.size() < n) {
			Obstacle ob = new Obstacle(this, random.nextInt(-5,0)*tileSize*5);
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
<<<<<<< Updated upstream
    	if (gameState != playState) this.ui.update();
    	else if(gameState == mathState) {
    		ui.update();
    	}
    	else if (gameState == playState){
    		vehicle.update();
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
=======
		this.map.update();
    	boolean p = false;
    	for(Iterator<Obstacle> i = obstacles.iterator(); i.hasNext();) {
    		Obstacle obstacle = i.next();
    		obstacle.update();
			if(!obstacle.onScreen()) {
				i.remove();
>>>>>>> Stashed changes
			}
	    	if (p) makeObstacle(random.nextInt(1,11));
	    	System.out.println(obstacles.size());
	    	timer.start();
    	}
    }
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
<<<<<<< Updated upstream
		this.update();
		if (gameState == titleState) ui.draw(g);
		else if(gameState == mathState) {
//			System.out.println("math");
			ui.draw(g);
		}
		else {
			for(Obstacle obstacle : obstacles) {
				obstacle.draw(g);
			}
			Graphics2D g2d = (Graphics2D) g;
			this.vehicle.draw(g2d);
			this.point.draw(g);
		}
=======
		this.map.draw(g);
		for(Obstacle obstacle : obstacles) {
			obstacle.draw(g);
		}
		Graphics2D g2d = (Graphics2D) g;
		this.vehicle.draw(g2d);
		this.update();
>>>>>>> Stashed changes
	}
	
	

}
