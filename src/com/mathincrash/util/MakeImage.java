package com.mathincrash.util;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class MakeImage {
    private Image image;

	public MakeImage(String path, int width, int height) {
		try {
            image = resizeImage(ImageIO.read(new File(path)), width, height);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	private Image resizeImage(Image originalImage, int width, int height) {
        return originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
    }    

    public Image getImage() {
        return image;
    }
}
