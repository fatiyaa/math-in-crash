package com.mathincrash.ui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.mathincrash.state.MathState;

public class KeyHandler implements KeyListener {

    private GamePanel gp;

    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        char keys = e.getKeyChar();
        if(gp.gameState == GamePanel.playState) {
	        switch (key) {
		        case KeyEvent.VK_RIGHT:
		        case KeyEvent.VK_D: {
		            if (gp.gameState == GamePanel.playState) {
	//	        	System.out.println("right");
			        gp.vehicle.goRight(gp);
		            }
		            break;
		        }
		        case KeyEvent.VK_LEFT: 
		        case KeyEvent.VK_A: {
		            if (gp.gameState == GamePanel.playState) {
	//	        	System.out.println("left");
			        gp.vehicle.goLeft(gp);
		            }
		            break;
		        }
	        }
        }
        else if(gp.gameState == GamePanel.mathState) {
        	switch(key) {
        		case KeyEvent.VK_0:
        		case KeyEvent.VK_1:
        		case KeyEvent.VK_2:
        		case KeyEvent.VK_3:
        		case KeyEvent.VK_4:
        		case KeyEvent.VK_5:
        		case KeyEvent.VK_6:
        		case KeyEvent.VK_7:
        		case KeyEvent.VK_8:
        		case KeyEvent.VK_9:
        		case KeyEvent.VK_MINUS: {
        			MathState inp = (MathState) gp.ui.states[GamePanel.mathState];
        			inp.input.addInput(Character.toString(keys));
//        			System.out.println("tap");
        			break;
        			
        		}
        		case KeyEvent.VK_BACK_SPACE:{
        			MathState inp = (MathState) gp.ui.states[GamePanel.mathState];
        			inp.input.deleteBack();
//        			System.out.println("tap");
        			break;
        		}
        	}
        }
//	        case KeyEvent.VK_ESCAPE: {
//	            if (gp.gameState == GamePanel.playState) gp.gameState = GamePanel.pauseState;
//	            else if (gp.gameState == GamePanel.pauseState) gp.gameState = GamePanel.playState;
//	            break;
//	        }
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }    
}

