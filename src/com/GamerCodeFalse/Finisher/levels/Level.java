package com.GamerCodeFalse.Finisher.levels;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.GamerCodeFalse.Finisher.main.Game;
import com.GamerCodeFalse.Finisher.utilz.LoadSave;

public class Level {
	public int[] collision;
	public BufferedImage sprite;
	public int WIDTH = 40;
	public int HEIGHT = 30;
	

	private int[] collisionTilesData = new int[(WIDTH/Game.TILE_WIDTH)*HEIGHT/Game.TILE_HEIGHT];
	private ArrayList<int[]> collisionMap = new ArrayList<int[]>();
	private ArrayList<Rectangle> collisionTiles = new ArrayList<Rectangle>();
	
	private int[] airTilesData = new int[(WIDTH/Game.TILE_WIDTH)*HEIGHT/Game.TILE_HEIGHT];
	private ArrayList<int[]> airMap = new ArrayList<int[]>();
	private ArrayList<Rectangle> airTiles = new ArrayList<Rectangle>();
	
	public Level(BufferedImage sprite0,int[] collision,int[] air) {
	
		
		collisionTilesData = collision;
		airTilesData = air;
		
		this.sprite = sprite0;
		setCollisionMap();
		setAirMap();
		
		initCollisionTiles();
		initAirTiles();
	}

	private void setCollisionMap() {
		for(int i =0;i<collisionTilesData.length;i+=40) {
			int[] tempArray = LoadSave.getSliceOfArray(collisionTilesData, i, i+40);
			collisionMap.add(tempArray);
		}
	}
	private void initAirTiles() {
		for(int i =0;i<airMap.size();i++) {
			for(int j =0;j<airMap.get(i).length;j++) {
				if(airMap.get(i)[j] == 1702) {
					Rectangle rectangle = new Rectangle(
							(int) (j*Game.TILE_WIDTH*Game.scale),
							(int) (i*Game.TILE_HEIGHT*Game.scale),
							(int) (Game.TILE_WIDTH*Game.scale),
							(int) (Game.TILE_HEIGHT*Game.scale)
					);
					getAirTiles().add(rectangle);
				}
			}
		}
	}
	private void setAirMap() {
		for(int i =0;i<airTilesData.length;i+=40) {
			int[] tempArray = LoadSave.getSliceOfArray(airTilesData, i, i+40);
			airMap.add(tempArray);
		}
	}
	private void initCollisionTiles() {
		for(int i =0;i<collisionMap.size();i++) {
			for(int j =0;j<collisionMap.get(i).length;j++) {
				if(collisionMap.get(i)[j] == 1701) {
					Rectangle rectangle = new Rectangle(
							(int) (j*Game.TILE_WIDTH*Game.scale),
							(int) (i*Game.TILE_HEIGHT*Game.scale),
							(int) (Game.TILE_WIDTH*Game.scale),
							(int) (Game.TILE_HEIGHT*Game.scale)
					);
					getCollisionTiles().add(rectangle);
				}
			}
		}
	}
	public void draw(Graphics g) {
		g.drawImage(sprite, 0, 0, (int)(WIDTH*Game.scale*Game.TILE_WIDTH),(int)(HEIGHT*Game.scale*Game.TILE_HEIGHT), null);
	}
	public void update() {}

	public ArrayList<Rectangle> getCollisionTiles() {
		return collisionTiles;
	}

	public ArrayList<Rectangle> getAirTiles() {
		return airTiles;
	}

	public void setAirTiles(ArrayList<Rectangle> airTiles) {
		this.airTiles = airTiles;
	}
}
