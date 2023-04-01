package com.GamerCodeFalse.Finisher.gameobjects.entity;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.GamerCodeFalse.Finisher.utilz.LoadSave;
import com.GamerCodeFalse.Finisher.utilz.PlayerConstants;

public class Player extends Entity{

	private int velocityX = 0;
	private int speed = 8;
	public int animationIndex = 0;
	public int currentAnimation = PlayerConstants.APIdle;
	public int direction = 1;
	private String path;
	public BufferedImage sprite2;
	
	public Player(int x, int y, int w, int h, String type, String path) {
		super(x, y, w, h, type, path);
		
		this.path = path;
		sprite2 = LoadSave.flipSprite(LoadSave.importSprite(path));
		
	}
	@Override
	public void draw(Graphics g) {
		sprite = LoadSave.importSpriteFromSpriteSheet(path,animationIndex,currentAnimation);
		
		
		if(direction == -1) {
			sprite = LoadSave.flipSprite(sprite);
			g.drawImage(sprite, getX(),getY(),getWidth(),getHeight(), null);
		}
		else {

			g.drawImage(sprite, getX(),getY(),getWidth(),getHeight(), null);
		}
		
//		g.drawRect(hitbox.x, hitbox.y, hitbox.width, hitbox.height);
	}
	
	@Override
	public void update() {
		animationIndex += 1;

		if(animationIndex == PlayerConstants.ALIdle) {
			animationIndex = 0;
		}
		
		this.setX(this.getVelocityX()+this.getX()*direction);
		updateHitboxPosition();
		
	}
	
	public void run(int direction) {
		
			this.direction = direction;
			
			this.setVelocityX(this.getSpeed());
			
			currentAnimation = PlayerConstants.APRun;
			
			if(animationIndex == PlayerConstants.ALRun) {
				animationIndex = 0;
			}
	}
	public void jump() {
		currentAnimation = PlayerConstants.APJump;
		
		if(animationIndex == PlayerConstants.ALJump) {
			animationIndex = 0;
		}
	}
	public void fall() {
		currentAnimation = PlayerConstants.APJump;
		
		if(animationIndex == PlayerConstants.ALJump-1) {
			animationIndex = 0;
		}
	}
	public void idle() {
		this.setVelocityX(0);
		currentAnimation = PlayerConstants.APIdle;
	}
	
	@Override
	public void updateHitboxPosition() {
		int wAdjust = 12;
		int hAdjust = 10;
		
		hitbox.x = this.pos[0]+wAdjust;
		hitbox.y = this.pos[1]+hAdjust;
		
		hitbox.width = this.size[0]-wAdjust;
		hitbox.height = this.size[1]-hAdjust;
	}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public int getVelocityX() {
		return velocityX;
	}
	public void setVelocityX(int velocityX) {
		this.velocityX = velocityX;
	}
}
