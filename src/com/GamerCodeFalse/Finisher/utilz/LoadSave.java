package com.GamerCodeFalse.Finisher.utilz;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class LoadSave {
	public static BufferedImage importSprite(String path) {
		InputStream is = LoadSave.class.getResourceAsStream(path);
		BufferedImage img = null;
		
		try {
			img = ImageIO.read(is);
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			try {
				is.close();
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
		
		return img;
	}
	public static BufferedImage importSpriteFromSpriteSheet(String path,int x,int y) {
		InputStream is = LoadSave.class.getResourceAsStream(path);
		BufferedImage img = null;
		
		int scale = 32;
		
		try {
			img = ImageIO.read(is);
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			try {
				is.close();
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
		
		return img.getSubimage(x*scale, y*scale, scale, scale);
	}
	public static BufferedImage flipSprite(BufferedImage sprite){
        BufferedImage img = new BufferedImage(sprite.getWidth(),sprite.getHeight(),BufferedImage.TYPE_INT_ARGB);
        for(int xx = sprite.getWidth()-1;xx>0;xx--){
            for(int yy = 0;yy < sprite.getHeight();yy++){
                img.setRGB(sprite.getWidth()-xx, yy, sprite.getRGB(xx, yy));
            }
        }
        return img;
	}
	 public static int[] getSliceOfArray(int[] arr, int start, int end){
			
			// Get the slice of the Array
			int[] slice = new int[end - start];
			
			// Copy elements of arr to slice
			for (int i = 0; i < slice.length; i++) {
			slice[i] = arr[start + i];
			}
			
			// return the slice
			return slice;
	}
}
