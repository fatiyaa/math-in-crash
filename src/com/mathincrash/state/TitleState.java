package com.mathincrash.state;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.mathincrash.onroad.Vehicle;
import com.mathincrash.ui.Button;
import com.mathincrash.ui.GamePanel;
import com.mathincrash.util.Sound;


public class TitleState extends State {

    private Button playButton;
    private Button quitButton;
    //    private Player playerIcon;
    private Button lefButton;
    private Button righButton;
    
    private BufferedImage bg;
    private int bgScreenX;

    public TitleState(GamePanel gp) {
        super(gp);

        int width = 3*(gp.tileSize-10);
        int height = gp.tileSize-10;
        int x = (gp.screenWidth-width)/2;
        int y = 13*gp.tileSize/4;
        int yOffset = 3*height/2;
        
        this.playButton = new Button(gp, "PLAY", x, y+=yOffset, width, height);
        this.quitButton = new Button(gp, "QUIT", x, y+=yOffset, width, height);

        int sizeChangeBtn = gp.tileSize /2;
        int yChangeCar = gp.screenHeight/2 + (gp.tileSize/4) -50;
        int gapLeft = 3*(gp.tileSize/4);
        int gapRight = gp.screenWidth-gapLeft-sizeChangeBtn;

        this.lefButton = new Button(gp, "<", gapLeft, yChangeCar, sizeChangeBtn, sizeChangeBtn);
        this.righButton = new Button(gp, ">", gapRight,  yChangeCar, sizeChangeBtn, sizeChangeBtn);

        this.bgScreenX = 0;
        gp.vehicle = new Vehicle(gp);


    }

    @Override
    public void update() {
        playButton.update();
        quitButton.update();
        gp.vehicle.update();
        lefButton.update(); 
        righButton.update(); 
        if (lefButton.state == Button.submitted) {
            gp.vehicle.carLeft(gp);
            System.out.println(gp.theCar);
        }
        if (righButton.state == Button.submitted) {
            gp.vehicle.carRight(gp);
            System.out.println(gp.theCar);
        }
        if (playButton.state == Button.submitted) {
            gp.sfx.play(Sound.sfxClick);
            gp.gameState = GamePanel.playState;
//            gp.bgm.playLoop(Sound.bgmGame);
            gp.bgm.playLoop(Sound.bgmGame);
        }
        if (quitButton.state == Button.submitted){
            System.exit(0);
        }  
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(bg, bgScreenX, 0 , null);
        if (bgScreenX-- < -gp.screenWidth) bgScreenX = 0;

        g.setFont(g.getFont().deriveFont(Font.BOLD, 70));
        String text = "Math in Crash";
        int x = (gp.screenWidth-getLength(g, text))/2;
        int y = 3*gp.tileSize/2;
        drawShadedText(g, text, x, y, 5, 5);

        playButton.draw(g);
        quitButton.draw(g);

        Graphics2D g2d = (Graphics2D) g;
        gp.vehicle.drawCar(g2d);
        lefButton.draw(g);
        righButton.draw(g);
    }

    public void reset() {

    }

}
