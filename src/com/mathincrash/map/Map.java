package com.mathincrash.map;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.mathincrash.ui.Drawable;
import com.mathincrash.ui.GamePanel;

public class Map implements Drawable {
    private Image grassImage;
    private Image crossRoadImage;
    private Image RoadLImage;
    private Image RoadMImage;
    private Image RoadRImage;
    private int tiles1, tiles7, tiles2, tiles6,tiles3,tiles4,tiles5;
    private double y, speed;
    private int tileSize;
    private int maxTileRow;
    private GamePanel gp;

    public Map(GamePanel gp) {
        this.tileSize = gp.tileSize;
        this.maxTileRow = gp.maxTileRow;
        this.gp = gp;
        
        this.y = -tileSize;
        this.tiles1 = tileSize * 0;
        this.tiles2 = tileSize * 1;
        this.tiles3 = tileSize * 2;
        this.tiles4 = tileSize * 3;
        this.tiles5 = tileSize * 4;
        this.tiles6 = tileSize * 5;
        this.tiles7 = tileSize * 6;


        try {
            grassImage = resizeImage(ImageIO.read(new File("assets/tiles/Grass.png")), tileSize, tileSize);
            crossRoadImage = resizeImage(ImageIO.read(new File("assets/tiles/CrossRoad.png")), tileSize, tileSize);
            RoadLImage = resizeImage(ImageIO.read(new File("assets/tiles/RoadL.png")), tileSize, tileSize);
            RoadMImage = resizeImage(ImageIO.read(new File("assets/tiles/RoadM.png")), tileSize, tileSize);
            RoadRImage = resizeImage(ImageIO.read(new File("assets/tiles/RoadR.png")), tileSize, tileSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    int a, b;
    @Override
    public void update() {
        y += gp.speed;
//        if (y > gp.speed ) {
//            y = -(double)(tileSize);
//        }
        if(y*10%10 > 4) {
        	a = (int) Math.ceil(y);
        }else {
        	a = (int)Math.floor(y);
        }
        
        if(gp.speed*10%10 > 4) {
        	b = (int) Math.ceil(gp.speed);
        }else {
        	b = (int)Math.floor(gp.speed);
        }
        
        if (a>-b) {
            y = -(double)(tileSize);
        }
        
//        System.out.println("map= "+gp.speed);
    }

    @Override
    public void draw(Graphics g) {
        for (int i = 0; i <= maxTileRow; i++) {
            g.drawImage(grassImage, tiles1, a + (tileSize * i), null);
            g.drawImage(grassImage, tiles7, a + (tileSize * i), null);
            g.drawImage(crossRoadImage, tiles2, a + (tileSize * i), null);
            g.drawImage(crossRoadImage, tiles6, a + (tileSize * i), null);
            g.drawImage(RoadLImage, tiles3, a + (tileSize * i), null);
            g.drawImage(RoadMImage, tiles4, a + (tileSize * i), null);
            g.drawImage(RoadRImage, tiles5, a + (tileSize * i), null);
        }
    }

    private Image resizeImage(Image originalImage, int width, int height) {
        return originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
    }
}
