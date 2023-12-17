package com.mathincrash.ui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

import com.mathincrash.util.Sound;

public class SoundButton implements Drawable {

	public static final int normal = 0;
	public static final int hovered = 1;
	public static final int clicked = 2;
	public static final int submitted = 3;

	private GamePanel gp;
	public int state;
	public boolean active;
	private int x, y, width, height;
	private Color bgColor;
	private Color shaderColor;
	private Image iconOn, iconMuted;

	public SoundButton(GamePanel gp, int x, int y) {
		this.gp = gp;
		this.x = x;
		this.y = y;
		width = gp.tileSize / 2;
		height = gp.tileSize / 2;
		this.state = normal;
		this.bgColor = new Color(114, 117, 27);
		this.shaderColor = new Color(0, 0, 0, 0.25F);
		active = false;

		this.iconOn = new ImageIcon("assets/sound/Icon on.png").getImage();
		this.iconMuted = new ImageIcon("assets/sound/Icon muted.png").getImage();

	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		if (gp.mouseH.mouseX < x || gp.mouseH.mouseX > x + width || gp.mouseH.mouseY < y
				|| gp.mouseH.mouseY > y + height) {
			this.state = normal;
			return;
		}

		if (state == clicked && gp.mouseH.mouseReleased == true) {
			if (active == true) {
				active = false;
				gp.bgm.playLoop(Sound.bgmGame);
			} else {
				active = true;
				gp.bgm.stop();
			}
			this.state = submitted;
		} else if (gp.mouseH.mousePressed == true)
			this.state = clicked;
		else
			this.state = hovered;

	}

	@Override
	public void draw(Graphics g) {
		g.setColor(shaderColor);
		g.drawRoundRect(x + 2, y + 2, width, height, 5, 5);

		if (active && state == hovered) {
			g.setColor(bgColor.darker());
		} else if (active) {
			g.setColor(bgColor.darker().darker());
		} else if (state == hovered) {
			g.setColor(bgColor.darker());
		} else if (state == normal)
			g.setColor(bgColor);
		else if (state == clicked)
			g.setColor(bgColor.darker().darker());

		g.fillRoundRect(x, y, width, height, 5, 5);

		g.setColor(Color.WHITE);
		g.drawRoundRect(x, y, width, height, 5, 5);
		
		if (active == true) {
			g.drawImage(iconMuted, x, y, width, height, null);
			g.setColor(Color.red);
			Graphics2D g2 = (Graphics2D) g;
			g2.setStroke(new BasicStroke(3));
			g2.drawLine(x, y, x + width, y + height);
		}
		else{
			g.drawImage(iconOn, x, y, width, height, null);
		}
	}
}
