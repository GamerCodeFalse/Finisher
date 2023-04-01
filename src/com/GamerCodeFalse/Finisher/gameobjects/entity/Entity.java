package com.GamerCodeFalse.Finisher.gameobjects.entity;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.GamerCodeFalse.Finisher.utilz.animation.AnimationValues;
import com.GamerCodeFalse.Finisher.utilz.sprite.ImportSprite;

public abstract class Entity {
	private int[] position =new int[2];
	private int width;
	private int height;
	
	private float speed;
	private float velocity;
	
	private int action = AnimationValues.IDLE_INDEX;
	private int animationIndex = 0;
	
	
	private String imgSrc;
	private BufferedImage spriteAtlas;
	private BufferedImage sprite;
	
	public Entity(int x,int y, int width, int height,String imgSrc) {
		this.getPosition()[0]=x;
		this.getPosition()[1]=y;
		
		this.setWidth(width);
		this.setHeight(height);
		
		this.setImgSrc(imgSrc);
		this.setSpriteAtlas(ImportSprite.importSprite(this.getImgSrc()+Integer.toString(getAction())+".png"));
	}
	public void draw(Graphics g) {
		g.drawImage(this.getSprite(), this.getPosition()[0],this.getPosition()[1],this.getWidth(),this.getHeight(),null);
	}

	public void update() {
		this.setSpriteAtlas(ImportSprite.importSprite(this.getImgSrc()+Integer.toString(getAction())+".png"));
		this.setSprite(ImportSprite.importSpriteFromSpriteSheet(this.getSpriteAtlas(),animationIndex,32,32));
		
		if(animationIndex == (ImportSprite.GetImageWidth(this.getSpriteAtlas())/32)-1) {
			animationIndex = 0;
		}else {
			animationIndex++;
		}
	}
	
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int[] getPosition() {
		return position;
	}

	public void setPosition(int[] position) {
		this.position = position;
	}

	public BufferedImage getSprite() {
		return sprite;
	}

	public void setSprite(BufferedImage sprite) {
		this.sprite = sprite;
	}
	public String getImgSrc() {
		return imgSrc;
	}
	public void setImgSrc(String imgSrc) {
		this.imgSrc = imgSrc;
	}
	public int getAnimationIndex() {
		return animationIndex;
	}
	public void setAnimationIndex(int animationIndex) {
		this.animationIndex = animationIndex;
	}
	public BufferedImage getSpriteAtlas() {
		return spriteAtlas;
	}
	public void setSpriteAtlas(BufferedImage spriteAtlas) {
		this.spriteAtlas = spriteAtlas;
	}
	public float getVelocity() {
		return velocity;
	}
	public void setVelocity(float velocity) {
		this.velocity = velocity;
	}
	public float getSpeed() {
		return speed;
	}
	public void setSpeed(float speed) {
		this.speed = speed;
	}
	public int getAction() {
		return action;
	}
	public void setAction(int action) {
		this.action = action;
	}
}
