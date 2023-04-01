package com.GamerCodeFalse.Finisher.gameobjects.entity;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.GamerCodeFalse.Finisher.main.Game;
import com.GamerCodeFalse.Finisher.utilz.LoadSave;

public class Entity {
	protected int[] pos = new int[2];
	protected int[] size = new int[2];
	protected String type;
	protected Rectangle hitbox;
	protected BufferedImage sprite;
	protected int mass;
	protected boolean falling = true;
	protected boolean touchedGround = false;
	
	public Entity(int x, int y, int w, int h,String type,String path) {
		pos[0] = x;
		pos[1] = y;
		
		size[0] = w;
		size[1] = h;
		
		hitbox = new Rectangle();
		hitbox.x = (int) x;
		hitbox.y = (int) y;
		hitbox.width = (int) w;
		hitbox.height = (int) h;
		
		this.type = type;
		
		sprite = LoadSave.importSprite(path);
		
		this.mass = w/Game.TILE_WIDTH;
	}
	
	//Entity Functions
	
	public void draw(Graphics g) {}
	public void update() {updateHitboxPosition();}
	public void onCollide() {}
	public boolean isColliding(Rectangle hitbox) {if(this.hitbox.intersects(hitbox)) {return true;}else{return false;}}
	public void updateHitboxPosition() {hitbox.x = this.pos[0];hitbox.y = this.pos[1];}

	
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
