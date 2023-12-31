package com.mathincrash.ui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

import com.mathincrash.map.Map;
import com.mathincrash.onroad.Obstacle;
import com.mathincrash.onroad.Vehicle;
import com.mathincrash.util.HighScore;
import com.mathincrash.util.Point;
import com.mathincrash.util.Sound;

public class GamePanel extends JPanel implements Runnable {

	public final int tile = 32;
	public final int scale = 3;
	public final int tileSize = tile * scale;
	public final int maxTileRow = 8;
	public final int maxTileCol = 7;
	public final int refreshRate = 120;

	public final int maxState = 5;
	public static final int titleState = 0;
	public static final int playState = 1;
	public static final int pauseState = 2;
	public static final int endState = 3;
	public static final int mathState = 4;

	public final int screenWidth = maxTileCol * tileSize;
	public final int screenHeight= maxTileRow * tileSize;

	public Thread thread;
	public KeyHandler keyH;
	public MouseHandler mouseH;
	public int gameState;
	public UI ui;

	public Map map;
	public SoundButton soundButton;
	public VolumeButton volumeButton;
	public Sound bgm;
	public Sound sfx;
	public int theCar;
	public Point point;
	public Vehicle vehicle;
	public ArrayList<Obstacle> obstacles;

	public double speed;
	public HighScore highScoreManager;
	String highScoreFile = "highscore.txt";

	
	
	public GamePanel() {
		this.soundButton = new SoundButton(this, (screenWidth-4*tileSize)/2, 3*tileSize+tileSize/4);
		this.volumeButton = new VolumeButton(this);
		this.buildGame();
		this.bgm = new Sound(this);
		this.sfx = new Sound(this);

		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.keyH = new KeyHandler(this);
		this.mouseH = new MouseHandler(this);
		this.addMouseMotionListener(mouseH);
		this.addMouseListener(mouseH);
		this.addKeyListener(keyH);
		this.setFocusable(true);
		bgm.playLoop(Sound.bgmGame);
	}

	public void buildGame() {
		this.ui = new UI(this);
		this.map = new Map(this);
		this.highScoreManager = new HighScore(highScoreFile, this);
		this.gameState = titleState;
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
			delta += (currentTime - lastTime) / drawInterval;
			lastTime = currentTime;

			if (delta >= 1) {
				this.repaint();
				delta--;
			}
		}
	}

	public void update() {
		this.ui.update();
		if (!this.soundButton.active) {
			bgm.update();
			sfx.update();
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.map.draw(g);
		this.ui.draw(g);
		this.update();
	}

	public void reset() {
		this.ui.reset();
		if(!soundButton.active)
			bgm.playLoop(Sound.bgmGame);
		this.gameState = titleState;
	}
}
