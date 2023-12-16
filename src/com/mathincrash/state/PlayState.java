package com.mathincrash.state;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javax.swing.Timer;

import com.mathincrash.onroad.Obstacle;
import com.mathincrash.onroad.Vehicle;
import com.mathincrash.ui.Button;
import com.mathincrash.ui.GamePanel;
import com.mathincrash.util.Point;
import com.mathincrash.util.Sound;

public class PlayState extends State {
    public Button pauseButton;
    private double speedDouble;
    private Random random;


    public PlayState(GamePanel gp) {
        super(gp);
        gp.speed = 2;
        gp.point = new Point(gp);
        gp.vehicle = new Vehicle(gp);
        gp.obstacles = new ArrayList<Obstacle>();
        this.pauseButton = new Button(gp, "||", 10, 10, gp.tileSize / 2, gp.tileSize / 2);
        this.random = new Random();
        this.speedDouble = 2;
        makeObstacle(1);
    }

    public void makeObstacle(int n) {

        while (gp.obstacles.size() < n) {
            Obstacle ob = new Obstacle(gp, random.nextInt(-5, 0) * gp.tileSize * 5);
            boolean crash = false;
            for (Obstacle obstacle : gp.obstacles) {
                if (ob.crashed(obstacle))
                    crash = true;
            }
            if (!crash) {
                gp.obstacles.add(ob);
                // System.out.println("added");
            }
        }

    }

    Timer timer = new Timer(1500, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (gp.gameState == GamePanel.playState)
                gp.point.update();
//            	gp.speed += 0.001;
        }
    });

    

    public void reset() {
        System.out.println("reset playstase");
        gp.speed = 2;
        gp.obstacles.clear();
        gp.obstacles = new ArrayList<Obstacle>();
        gp.point.point = 0;
        timer.restart();
        makeObstacle(1);
        this.update();
    }

    @Override
    public void update() {

        gp.vehicle.update();
        this.pauseButton.update();

        gp.map.update();
        if (pauseButton.state == Button.submitted) {
            gp.sfx.play(Sound.sfxClick);
            gp.gameState = GamePanel.pauseState;
        }
        
        boolean p = false;
        for (Iterator<Obstacle> i = gp.obstacles.iterator(); i.hasNext();) {
            Obstacle obstacle = i.next();
            obstacle.update();
            if (!obstacle.onScreen()) {
                i.remove();
                this.speedDouble += 0.05;
                if((int)(this.speedDouble*10)%10 == 5){
                	gp.speed += 0.5;
                	speedDouble+=0.05;
                }
                System.out.println(gp.speed);
            } else {
                p = true;
            }
        }
        if (p)
            makeObstacle(random.nextInt(1, 9));

        timer.start();
    }

    @Override
    public void draw(Graphics g) {
        for (Obstacle obstacle : gp.obstacles) {
            obstacle.draw(g);
        }
        Graphics2D g2d = (Graphics2D) g;
        gp.vehicle.draw(g2d);
        g.setFont(g.getFont().deriveFont(Font.BOLD, 20));
        gp.point.draw(g);
        this.pauseButton.draw(g);
        gp.highScoreManager.draw(g);

    }
}