package com.GamerCodeFalse.Finisher.gameobjects.entity;

import java.awt.Graphics;
import java.awt.geom.Line2D;

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
	public int directionX = 1;
	public int directionY = 1;
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
		
		g.drawRect(hitbox.x, hitbox.y, hitbox.width, hitbox.height);
		
		for(int i = 0;i<current.getCollisionTiles().size();i++) {
			g.fillRect(
					(int)(current.getCollisionTiles().get(i).getX()), 
					(int)(current.getCollisionTiles().get(i).getY()), 
					(int)(current.getCollisionTiles().get(i).getWidth()),
					(int)(current.getCollisionTiles().get(i).getHeight()));
		}
	}
	
	@Override
	public void update() {
		animationIndex += 1;

		if(animationIndex == PlayerConstants.ALIdle) {
			animationIndex = 0;
		}
		
		if(!run && falling && !idle) {
			fall();
		}else {
			stopY();
			idle = true;
		}
		if(!run && !falling && idle) {
			idle();
		}
		
		this.setX(this.getVelocityX()*directionX+this.getX());
		this.setY(this.getVelocityY()*directionY+this.getY());
		
		for(int i = 0;i<current.getCollisionTiles().size();i++) {
			if(hitbox.intersectsLine(new Line2D.Double(current.getCollisionTiles().get(i).getX(), 
					current.getCollisionTiles().get(i).getY(), 
					current.getCollisionTiles().get(i).getX()+current.getCollisionTiles().get(i).getWidth(),
					current.getCollisionTiles().get(i).getY()))) {
				if(this.getY() % Game.TILE_HEIGHT != 0) {
					int currentY = this.getY();
					this.setY(currentY-1);
				}
				falling = false;
			}
		}
		
		updateHitboxPosition();
		
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
		
		this.setVelocityY((int)((Game.gravity*this.mass)));
		
		if(animationIndex == PlayerConstants.ALJump-1) {
			animationIndex = 0;
		}
	}
	public void idle() {
		currentAnimation = PlayerConstants.APIdle;
	}
	public void stopX() {
		this.velocityX = 0;
	}
	public void stopY() {
		this.velocityY = 0;
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
