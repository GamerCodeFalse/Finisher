package com.GamerCodeFalse.Finisher.gameobjects.entity;

import java.awt.Graphics;

import com.GamerCodeFalse.Finisher.main.Game;
import com.GamerCodeFalse.Finisher.utilz.LoadSave;
import com.GamerCodeFalse.Finisher.utilz.PlayerConstants;

public class Player extends Entity{

	public int speed = 5;
	public int animationIndex = 0;
	public int currentAnimation = PlayerConstants.APIdle;
	public int direction = 1;
	private String path;
	
	
	public Player(int x, int y, int w, int h, String type,String path) {
		super(x, y, w, h, type,path);
		
		this.path = path;
	}
	@Override
	public void draw(Graphics g) {
		sprite = LoadSave.importSpriteFromSpriteSheet(path,animationIndex,currentAnimation);
		
		if(direction == -1) {
			sprite  =LoadSave.flipSprite(sprite);
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
		if(falling && !touchedGround) {
			this.pos[1] += Game.gravity*this.mass;
			fall();
		}
		updateHitboxPosition();
		
	}
	
	public void run(int direction) {
		
			this.direction = direction;
		
			setX(direction*speed+getX());
			
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
	
	@Override
	public void onCollide() {
		int tempPos = this.pos[1];
		falling = false;
		this.pos[1] = tempPos-2;
		touchedGround = true;
		idle();
	}
}
