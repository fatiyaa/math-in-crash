package com.mathincrash.state;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;


import com.mathincrash.ui.Button;
import com.mathincrash.ui.GamePanel;
import com.mathincrash.util.Sound;


public class TitleState extends State {

    private Button playButton;
    private Button quitButton;
//    private Player playerIcon;

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

        this.bgScreenX = 0;

    }

    @Override
    public void update() {
//        playerIcon.update();
        playButton.update();
        quitButton.update();
        if (playButton.state == Button.submitted) {
            gp.sfx.play(Sound.sfxClick);
        	// gp.buildGame();
            gp.gameState = GamePanel.playState;
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
//        playerIcon.draw(g);
    }

    public void reset() {
//        this.playerIcon = new Player(gp);
    }

}
