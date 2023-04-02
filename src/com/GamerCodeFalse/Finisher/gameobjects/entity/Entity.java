package com.GamerCodeFalse.Finisher.gameobjects.entity;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.GamerCodeFalse.Finisher.utilz.LoadSave;

public class Entity {
	protected int[] pos = new int[2];
	protected int[] size = new int[2];
	protected String type;
	protected BufferedImage sprite;
	protected boolean falling = true;
	protected float mass;
	
	public Entity(int x, int y, int w, int h,String type,String path) {
		pos[0] = x;
		pos[1] = y;
		
		size[0] = w;
		size[1] = h;
		

		
		this.type = type;
		
		sprite = LoadSave.importSprite(path);

	}
	
	//Entity Functions
	
	public void draw(Graphics g) {}
	public void update() {}
	public void onCollide() {}

	
	//Getters
	
	public int getX() {
		return pos[0];
	}
	public int getY() {
		return pos[1];
	}
	public int getWidth() {
		return size[0];
	}
	public int getHeight() {
		return size[1];
	}
	public String getType() {
		return type;
	}
	
	
	//Setters
	
	public void setX(int x) {
		pos[0] = x;
	}
	public void setY(int y) {
		pos[1] = y;
	}
	public void setWidth(int w) {
		size[0] = w;
	}
	public void setHeight(int h) {
		size[1] = h;
	}
	public void setType(String type) {
		this.type = type;
	}
}
