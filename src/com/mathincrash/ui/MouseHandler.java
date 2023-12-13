package com.mathincrash.ui;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;


public class MouseHandler implements MouseListener, MouseMotionListener{
	
	GamePanel gp;
	
	public int mouseX, mouseY;
	public boolean mousePressed, mouseReleased, mouseDragged;
	
	public MouseHandler(GamePanel gp) {
        this.gp = gp;
        this.mousePressed = false;
        this.mouseReleased = true;
        this.mouseDragged = false;
	}
	
	@Override
    public void mousePressed(MouseEvent e) {
        this.mouseX = e.getX();
        this.mouseY = e.getY();
        this.mousePressed = true;
        this.mouseReleased = false;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        this.mouseX = e.getX();
        this.mouseY = e.getY();
        this.mouseReleased = true;
        this.mousePressed = false;
        this.mouseDragged = false;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        this.mouseX = e.getX();
        this.mouseY = e.getY();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        this.mouseX = e.getX();
        this.mouseY = e.getY();
        this.mouseDragged = true;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        this.mouseX = e.getX();
        this.mouseY = e.getY();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        this.mouseX = e.getX();
        this.mouseY = e.getY();
    }

    @Override
    public void mouseExited(MouseEvent e) {
        this.mousePressed = false;
        this.mouseReleased = true;
    }
}
