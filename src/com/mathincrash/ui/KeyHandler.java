package com.mathincrash.ui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class KeyHandler implements KeyListener {

    private GamePanel gp;

    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        switch (key) {
	        case KeyEvent.VK_RIGHT:
	        case KeyEvent.VK_D: {
	            if (gp.gameState == GamePanel.playState) {
	        	System.out.println("right");
		        gp.vehicle.goRight(gp);
	            }
	            break;
	        }
	        case KeyEvent.VK_LEFT: 
	        case KeyEvent.VK_A: {
	            if (gp.gameState == GamePanel.playState) {
	        	System.out.println("left");
		        gp.vehicle.goLeft(gp);
	            }
	            break;
	        }
//	        case KeyEvent.VK_ESCAPE: {
//	            if (gp.gameState == GamePanel.playState) gp.gameState = GamePanel.pauseState;
//	            else if (gp.gameState == GamePanel.pauseState) gp.gameState = GamePanel.playState;
//	            break;
//	        }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }    
}

