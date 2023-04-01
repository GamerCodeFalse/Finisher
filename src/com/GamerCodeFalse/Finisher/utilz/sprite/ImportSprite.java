package com.GamerCodeFalse.Finisher.utilz.sprite;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class ImportSprite {
	
	public static BufferedImage importSprite(String src) {
		BufferedImage img = null;
		InputStream is = ImportSprite.class.getResourceAsStream(src);
		try {
			img = ImageIO.read(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return img;
	}
	public static BufferedImage importSpriteFromSpriteSheet(BufferedImage img,int index,int width,int height) {
		return img.getSubimage(index*32, 0, width, height);
	}
	public static int GetImageWidth(BufferedImage img) {
		return img.getWidth();
	}
}
  