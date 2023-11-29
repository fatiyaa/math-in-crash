package com.mathincrash.state;

import java.awt.Color;
import java.awt.Graphics;

import com.mathincrash.ui.Drawable;
import com.mathincrash.ui.GamePanel;

public abstract class State implements Drawable {

    protected GamePanel gp;
    protected Color shaderColor;
    
    protected State(GamePanel gp) {
        this.gp = gp;
        this.shaderColor = new Color(0, 0, 0, 0.3F);
    }

    protected void drawShadedText(Graphics g, String text, int x, int y, int offsetX, int offsetY) {
        g.setColor(shaderColor);
        g.drawString(text, x+offsetX, y+offsetY);

        g.setColor(Color.WHITE);
        g.drawString(text, x, y);
    }

    protected int getLength(Graphics g, String text) {
        return (int)g.getFontMetrics().getStringBounds(text, g).getWidth();
    }

    public void reset() {}
}