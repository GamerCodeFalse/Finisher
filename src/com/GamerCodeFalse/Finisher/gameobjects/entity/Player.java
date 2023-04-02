package com.GamerCodeFalse.Finisher.gameobjects.entity;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.GamerCodeFalse.Finisher.levels.Level;
import com.GamerCodeFalse.Finisher.levels.LevelManager;
import com.GamerCodeFalse.Finisher.main.Game;
import com.GamerCodeFalse.Finisher.utilz.LoadSave;
import com.GamerCodeFalse.Finisher.utilz.PlayerConstants;

public class Player extends Entity{

	private int velocityX = 0;
	private int velocityY = 0;
	private int speed = 8;
	public int animationIndex = 0;
	public int currentAnimation = PlayerConstants.APIdle;
	public int directionX = 0;
	public int directionY = 0;
	private String path;
	private Level current;
	private boolean idle = false;
	private boolean run = false;

	
	public Player(int x, int y, int w, int h, String type, String path,LevelManager levelManager) {
		super(x, y, w, h, type, path);
		
		this.path = path;
		this.mass = w/Game.TILE_WIDTH;
		
		current = levelManager.currentLevel;
		
	}
	@Override
	public void draw(Graphics g) {
		sprite = LoadSave.importSpriteFromSpriteSheet(path,animationIndex,currentAnimation);
		
		
		if(directionX == -1) {
			sprite = LoadSave.flipSprite(sprite);
			g.drawImage(sprite, getX(),getY(),getWidth(),getHeight(), null);
		}
		else {

			g.drawImage(sprite, getX(),getY(),getWidth(),getHeight(), null);
		}
		
//		g.drawRect(getBoundsY().x, getBoundsY().y, getBoundsY().width, getBoundsY().height);
//		
//		for(int i = 0;i<current.getCollisionTiles().size();i++) {
//			g.fillRect(
//					(int)(current.getCollisionTiles().get(i).getX()), 
//					(int)(current.getCollisionTiles().get(i).getY()), 
//					(int)(current.getCollisionTiles().get(i).getWidth()),
//					(int)(current.getCollisionTiles().get(i).getHeight()));
//		}
	}
	
	@Override
	public void update() {
		animationIndex += 1;

		if(animationIndex == PlayerConstants.ALIdle) {
			animationIndex = 0;
		}
		
		if(falling && !idle) {
			fall();
		}else {
			stopY();
			idle = true;
		}
		if(!run && !falling && idle) {
			idle();
		}
		
		for(int i = 0;i<current.getCollisionTiles().size();i++) {
			if(getBoundsX().intersects(current.getCollisionTiles().get(i))) {
				if(directionX == 1) {
					this.setVelocityX(0);
					this.setX(current.getCollisionTiles().get(i).x-54);
				}
				if(directionX == -1) {
					this.setVelocityX(0);
					this.setX(current.getCollisionTiles().get(i).x+10);
				}
			}
			if(getBoundsY().intersects(current.getCollisionTiles().get(i))) {
				if(directionY == 1) {
					this.setVelocityY(0);
					this.setY(current.getCollisionTiles().get(i).y-64);
					falling = false;
				}
				if(directionY == -1) {
					this.setVelocityY(0);
					this.setY(current.getCollisionTiles().get(i).y+current.getCollisionTiles().get(i).height);
				}
			}
		}
		
		this.setX(this.getVelocityX()*directionX+this.getX());
		this.setY(this.getVelocityY()*directionY+this.getY());
		
	}
	
	public void run(int direction) {
		
			this.directionX = direction;
			
			run = true;
			
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
		
		directionY = 1;
		
		this.setVelocityY((int)((Game.gravity*this.mass)));
		
		if(animationIndex == PlayerConstants.ALJump-1) {
			animationIndex = 0;
		}
	}
	public Rectangle getBoundsX() {
		int bx = this.getX()+this.velocityX*directionX+10;
		int by = this.getY()+10;
		int bw = 44+(this.velocityX/2)*directionX;
		int bh = 54;
		
		return new Rectangle(bx,by,bw,bh);
	}
	public Rectangle getBoundsY() {
		int bx = this.getX()+10;
		int by = this.getY()+this.velocityY*directionY+10;
		int bw = 44;
		int bh = 54+(this.velocityY/2)*directionY;
		
		return new Rectangle(bx,by,bw,bh);
	}
	public void idle() {
		directionX = 0;
		directionY = 0;
		currentAnimation = PlayerConstants.APIdle;
	}
	public void stopX() {
		this.velocityX = 0;
	}
	public void stopY() {
		this.velocityY = 0;
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
	public int getVelocityY() {
		return velocityY;
	}
	public void setVelocityY(int velocityY) {
		this.velocityY = velocityY;
	}
	public boolean isIdle() {
		return idle;
	}
	public void setIdle(boolean idle) {
		this.idle = idle;
	}
	public boolean isRun() {
		return run;
	}
	public void setRun(boolean run) {
		this.run = run;
	}
}
