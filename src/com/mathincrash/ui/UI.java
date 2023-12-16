package com.mathincrash.ui;

import java.awt.Graphics;

import com.mathincrash.state.EndState;
import com.mathincrash.state.MathState;
import com.mathincrash.state.PauseState;
import com.mathincrash.state.PlayState;
import com.mathincrash.state.State;
import com.mathincrash.state.TitleState;


public class UI implements Drawable {
    
    private GamePanel gp;
//    private Font maruMonica;
    public State[] states;

    public UI(GamePanel gp) {
        this.gp = gp;
        this.states = new State[gp.maxState];
        this.states[GamePanel.titleState] = new TitleState(gp);
        this.states[GamePanel.playState] = new PlayState(gp);
        this.states[GamePanel.pauseState] = new PauseState(gp);
        this.states[GamePanel.endState] = new EndState(gp);
        this.states[GamePanel.mathState] = new MathState(gp);

//        try {
//            this.maruMonica = Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/font/MaruMonica.ttf"));
//        } catch (FontFormatException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    @Override
    public void update() {
        switch (gp.gameState) {
        case GamePanel.titleState: {
            states[GamePanel.titleState].update();
            break;
        }
        case GamePanel.playState: {
            states[GamePanel.playState].update();
            break;
        }
        case GamePanel.pauseState: {
            states[GamePanel.pauseState].update();
            break;
        }
        case GamePanel.endState: {
            states[GamePanel.endState].update();
            break;
        }
        case GamePanel.mathState: {
        	states[GamePanel.mathState].update();
        	break;
        }
        }
    }

    @Override
    public void draw(Graphics g) {
//        g.setFont(maruMonica.deriveFont(Font.PLAIN, 20F));
//        g.setColor(Color.WHITE);

        switch (gp.gameState) {
        case GamePanel.titleState: {
            states[GamePanel.titleState].draw(g);
            break;
        }
        case GamePanel.playState: {
            states[GamePanel.playState].draw(g);
            break;
        }
        case GamePanel.pauseState: {
            states[GamePanel.playState].draw(g);
            states[GamePanel.pauseState].draw(g);
            break;
        }
        case GamePanel.endState: {
//            states[GamePanel.playState].draw(g);
            states[GamePanel.endState].draw(g);
            break;
        }
        case GamePanel.mathState:{
        	states[GamePanel.playState].draw(g);
        	states[GamePanel.mathState].draw(g);
        }
        }
    }
    
    public void reset() {
        switch (gp.gameState) {
            case GamePanel.titleState: {
            states[GamePanel.titleState].reset();
            break;
        }
        case GamePanel.playState: {
            states[GamePanel.playState].reset();
            break;
        }
        case GamePanel.pauseState: {
            states[GamePanel.playState].reset();
            states[GamePanel.pauseState].reset();
            break;
        }
        case GamePanel.endState: {
            states[GamePanel.playState].reset();
            states[GamePanel.endState].reset();
            break;
        }
        case GamePanel.mathState:{
            states[GamePanel.playState].reset();
            states[GamePanel.mathState].reset();
        }
        }
    }
}

