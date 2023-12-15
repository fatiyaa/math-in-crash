package com.mathincrash.state;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.mathincrash.ui.Button;
import com.mathincrash.ui.GamePanel;

public class PauseState extends State {
    
    private Button resumeButton;
    private Button homeButton;
    private Button quitButton;

    private BufferedImage bg;

    public PauseState(GamePanel gp) {
        super(gp);
        int width = 4* gp.tileSize;
        int height = gp.tileSize;
        int x = (gp.screenWidth-width)/2;
        int y = 8*gp.tileSize/4;
        int yOffset = 3*height/2;
        
        this.resumeButton = new Button(gp, "RESUME", x, y+=yOffset, width, height);
        this.homeButton = new Button(gp, "MAIN MENU", x, y+=yOffset, width, height);
        this.quitButton = new Button(gp, "QUIT", x, y+=yOffset, width, height);

//        try {
//            this.bg = ImageIO.read(getClass().getResourceAsStream("/state/menu/background.png"));
//            BufferedImage resizedBg = new BufferedImage(gp.screenWidth, gp.screenHeight, bg.getType());
//            Graphics2D g = resizedBg.createGraphics();
//            g.drawImage(bg, 0, 0, gp.screenWidth, gp.screenHeight, null);
//            this.bg = resizedBg;
//            g.dispose();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    @Override
    public void update() {
        resumeButton.update();
        homeButton.update();
        quitButton.update();
        if (resumeButton.state == Button.submitted) gp.gameState = GamePanel.playState;
        if (homeButton.state == Button.submitted) gp.reset();
        if (quitButton.state == Button.submitted) {
        	if(gp.point.point > gp.highScoreManager.highScore) {
            	gp.highScoreManager.saveHighScore(gp.point.point);
            }
        	System.exit(0);
        }
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(shaderColor);
        g.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        g.drawImage(bg, 0, 0, null);

        g.setColor(Color.WHITE);
        g.setFont(g.getFont().deriveFont(Font.BOLD, 80F));
        String text = "PAUSED";
        int x = (gp.screenWidth-getLength(g, text))/2;
        int y = 2*gp.tileSize;
        drawShadedText(g, text, x, y, 3, 3);
        g.setFont(g.getFont().deriveFont(Font.BOLD, 70));
        resumeButton.draw(g);
        quitButton.draw(g);
        g.setFont(g.getFont().deriveFont(Font.BOLD, 60));
        homeButton.draw(g);
    }
}