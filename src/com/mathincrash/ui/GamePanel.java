package com.mathincrash.ui;

import java.awt.Dimension;
import java.awt.Font;
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
import com.mathincrash.util.HighScore;
import com.mathincrash.util.Point;
import com.mathincrash.util.Sound;

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
    public int gameState;
    public UI ui;

	public Sound bgm;
    public Sound sfx;
    
    public Button pauseButton;
	public Point point;
	String highScoreFile = "highscore.txt";
	public HighScore highScoreManager;
	
	public Map map;
	public Vehicle vehicle;
	public ArrayList <Obstacle> obstacles;
	public int speed;
	private double speedDouble;
	
	
	Timer timer = new Timer(1500, new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(gameState == GamePanel.playState)
			point.update();
		}
	});
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.keyH = new KeyHandler(this);
		this.mouseH = new MouseHandler(this);
		this.addKeyListener(keyH);
		this.addMouseListener(mouseH);
		this.addMouseMotionListener(mouseH);
		this.setFocusable(true);
		this.ui = new UI(this);
		this.random = new Random();
		this.gameState = titleState;
		this.map = new Map(this);
		this.bgm = new Sound();
        this.sfx = new Sound();
	}
	
	public void buildGame () {
		this.speed = 2;
		this.speedDouble = 2;
		this.vehicle = new Vehicle(this);
		this.obstacles = new ArrayList<Obstacle>();
		this.point = new Point(this);
		makeObstacle(1);
		this.pauseButton = new Button(this, "||", 10, 10, this.tileSize/2, this.tileSize/2);
		this.highScoreManager =  new HighScore(highScoreFile, this);
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

    	if (gameState != playState) this.ui.update();
    	else if(gameState == mathState) {
    		ui.update();
    	}
    	else if (gameState == playState){
    		this.map.update();
    		vehicle.update();
    		this.pauseButton.update();
    		this.speedDouble += 0.00001;
    		this.speed = (int) Math.floor(speedDouble);
    		if (pauseButton.state == Button.submitted){
				sfx.play(Sound.sfxClick);
				this.gameState = pauseState;
			}
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
	    	if (p) makeObstacle(random.nextInt(1,9));
//	    	System.out.println(obstacles.size());
	    	timer.start();
    	}
    }
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.map.draw(g);

		this.update();
//		if (gameState == titleState) ui.draw(g);
//		else if (gameState == endState) ui.draw(g);
//		else if(gameState == mathState) {
////			System.out.println("math");t
//			ui.draw(g);
//		}
		if(gameState == playState){
			for(Obstacle obstacle : obstacles) {
				obstacle.draw(g);
			}
			Graphics2D g2d = (Graphics2D) g;
			this.vehicle.draw(g2d);
			g.setFont(g.getFont().deriveFont(Font.BOLD, 20));
			this.point.draw(g);
			this.pauseButton.draw(g);
			this.highScoreManager.draw(g);
		}
		else ui.draw(g);
	}
	
	public void reset() {
        this.obstacles.clear();;
        this.ui.reset();
        this.gameState = titleState;
    }

	public void popList() {
		for(Iterator<Obstacle> i = obstacles.iterator(); i.hasNext();) {
    		Obstacle obstacle = i.next();
			if(obstacle.crashed(vehicle)) {
				i.remove();
			}
		}
	}
	public void addPoint(int point) {
		this.point.point += point;
	}
	
	

//    // Simpan highscore
//    int currentHighScore = 5000; // Ganti dengan highscore saat ini dari game
//    highScoreManager.saveHighScore(currentHighScore);
//
//    // Memuat dan menampilkan highscore
//    int loadedHighScore = highScoreManager.loadHighScore();
//    System.out.println("Highscore yang disimpan: " + loadedHighScore);
}
