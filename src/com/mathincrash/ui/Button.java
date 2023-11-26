package com.mathincrash.ui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;


public class Button implements Drawable{
	
	public static final int normal = 0;
    public static final int hovered = 1;
    public static final int clicked = 2;
    public static final int submitted = 3;
	
    private final int screenX ;
    private final int screenY;
    private final int width;
    private final int height;

    private GamePanel gp;
    private String text;
    private Color bgColor;
    private Color shaderColor;
    private BasicStroke stroke;

    public int state;
    
    public Button(GamePanel gp, String text, int screenX, int screenY, int width, int height) {
        this.gp = gp;
        this.screenX = screenX;
        this.screenY = screenY;
        this.width = width;
        this.height = height;
        this.text = text;
        this.state = normal;
        this.bgColor = new Color(114, 117, 27);
        this.shaderColor = new Color(0, 0, 0, 0.25F);
        this.stroke = new BasicStroke(gp.scale);
    }

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		
	}

//	@Override
//	public void draw(Graphics2D g) {
//		// TODO Auto-generated method stub
//		
//	}

}
