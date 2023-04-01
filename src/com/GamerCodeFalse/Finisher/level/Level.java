package com.GamerCodeFalse.Finisher.level;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.GamerCodeFalse.Finisher.main.Game;
import com.GamerCodeFalse.Finisher.utilz.LoadSave;

public class Level {
	private static int HEIGHT;
	private static int WIDTH;
	
	private static float scale;
	
	private BufferedImage lvlSprite;
	
	private int[] collisionTilesData = new int[(WIDTH/Game.TILE_WIDTH)*HEIGHT/Game.TILE_HEIGHT];
	private ArrayList<int[]> collisionMap = new ArrayList<int[]>();
	private ArrayList<Rectangle> collisionTiles = new ArrayList<Rectangle>();
	
	public Level(int width,int height,String lvlSpritePath,int[] collision, float scale) {
		WIDTH = (int) (width*Game.TILE_WIDTH*scale);
		HEIGHT = (int) (height*Game.TILE_HEIGHT*scale);
		
		Level.scale = scale;
		
		collisionTilesData = collision;
		
		setLvlSprite(LoadSave.importSprite(lvlSpritePath));
		setCollisionMap();
		initCollisionTiles();
	}

	private void setCollisionMap() {
		for(int i =0;i<collisionTilesData.length;i+=40) {
			int[] tempArray = LoadSave.getSliceOfArray(collisionTilesData, i, i+40);
			collisionMap.add(tempArray);
		}
	}
	private void initCollisionTiles() {
		for(int i =0;i<collisionMap.size();i++) {
			for(int j =0;j<collisionMap.get(i).length;j++) {
				if(collisionMap.get(i)[j] == 1701) {
					Rectangle rectangle = new Rectangle(
							(int) (j*Game.TILE_WIDTH*Level.scale),
							(int) (i*Game.TILE_HEIGHT*Level.scale),
							(int) (Game.TILE_WIDTH*Level.scale),
							(int) (Game.TILE_HEIGHT*Level.scale)
					);
					collisionTiles.add(rectangle);
				}
			}
		}
	}
	
	public void drawLevel(Graphics g) {
		g.drawImage(lvlSprite, 0, 0, WIDTH, HEIGHT, null);
		
	}
	
	public int[] getCollisionTilesData() {
		return collisionTilesData;
	}

	public BufferedImage getLvlSprite() {
		return lvlSprite;
	}

	public void setLvlSprite(BufferedImage lvlSprite) {
		this.lvlSprite = lvlSprite;
	}

	public ArrayList<Rectangle> getCollisionTiles() {
		return collisionTiles;
	}
}
