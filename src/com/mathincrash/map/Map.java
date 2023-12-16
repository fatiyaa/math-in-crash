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
    private double y;
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

    @Override
    public void update() {
        y += gp.speed;
        y = (double)Math.round(y*1e3)/1e3;
        if (Double.compare(y, -gp.speed)>=0) {
            y = -(double)tileSize;
        }
//        System.out.println("map= "+gp.speed);
    }

    @Override
    public void draw(Graphics g) {
        for (int i = 0; i <= maxTileRow; i++) {
            g.drawImage(grassImage, tiles1, (int)y + (tileSize * i), null);
            g.drawImage(grassImage, tiles7, (int)y + (tileSize * i), null);
            g.drawImage(crossRoadImage, tiles2, (int)y + (tileSize * i), null);
            g.drawImage(crossRoadImage, tiles6, (int)y + (tileSize * i), null);
            g.drawImage(RoadLImage, tiles3, (int)y + (tileSize * i), null);
            g.drawImage(RoadMImage, tiles4, (int)y + (tileSize * i), null);
            g.drawImage(RoadRImage, tiles5, (int)y + (tileSize * i), null);
        }
    }

    private Image resizeImage(Image originalImage, int width, int height) {
        return originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
    }
}
