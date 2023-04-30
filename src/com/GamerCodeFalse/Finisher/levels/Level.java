package com.GamerCodeFalse.Finisher.levels;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.GamerCodeFalse.Finisher.gameobjects.GameObjects;
import com.GamerCodeFalse.Finisher.main.Game;
import com.GamerCodeFalse.Finisher.utilz.LoadSave;

public class Level {
	public int[] collision;
	public BufferedImage sprite0;
	public BufferedImage sprite1;
	public int WIDTH = 40;
	public int HEIGHT = 30;
	

	private int[] collisionTilesData = new int[(WIDTH/Game.getTILE_WIDTH())*HEIGHT/Game.TILE_HEIGHT];
	private ArrayList<int[]> collisionMap = new ArrayList<int[]>();
	private ArrayList<Rectangle> collisionTiles = new ArrayList<Rectangle>();
	
	
	public Level(BufferedImage sprite0, BufferedImage sprite1,int w,int[] collision) {
	
		
		collisionTilesData = collision;
		
		this.sprite0 = sprite0;
		this.sprite1 = sprite1;
		
		this.WIDTH = w;
		
		setCollisionMap();
		
		initCollisionTiles();
	}

	private void setCollisionMap() {
		for(int i =0;i<collisionTilesData.length;i+=WIDTH) {
			int[] tempArray = LoadSave.getSliceOfArray(collisionTilesData, i, i+WIDTH);
			collisionMap.add(tempArray);
		}
	}
	private void initCollisionTiles() {
		for(int i =0;i<collisionMap.size();i++) {
			for(int j =0;j<collisionMap.get(i).length;j++) {
				if(collisionMap.get(i)[j] != 0) {
					Rectangle rectangle = new Rectangle(
							(int) (j*Game.getTILE_WIDTH()*Game.scale),
							(int) (i*Game.TILE_HEIGHT*Game.scale),
							(int) (Game.getTILE_WIDTH()*Game.scale),
							(int) (Game.TILE_HEIGHT*Game.scale)
					);
					getCollisionTiles().add(rectangle);
				}
			}
		}
	}
	public void draw(Graphics g) {
		if(GameObjects.player.getX() >= Game.WIDTH/2 && GameObjects.player.getX() <= 800) {
			g.translate(-GameObjects.player.getX()+500, 0);
		}
		if(!(GameObjects.player.getX() <= 800)) {
			g.translate(-500, 0);
		}
		g.drawImage(sprite0, 0, 0, (int)(WIDTH*Game.scale*Game.getTILE_WIDTH()),(int)(HEIGHT*Game.scale*Game.TILE_HEIGHT), null);
	}
	public void drawProps(Graphics g) {
		if(GameObjects.player.getX() >= Game.WIDTH/2 && GameObjects.player.getX() <= 800) {
			g.translate(-GameObjects.player.getX()+500, 0);
		}
		if(!(GameObjects.player.getX() <= 800)) {
			g.translate(-500, 0);
		}
		g.drawImage(sprite1, 0, 0, (int)(WIDTH*Game.scale*Game.getTILE_WIDTH()),(int)(HEIGHT*Game.scale*Game.TILE_HEIGHT), null);
	}
	public void update() {}

	public ArrayList<Rectangle> getCollisionTiles() {
		return collisionTiles;
	}
}
