package com.GamerCodeFalse.Finisher.gameobjects.entity;

import static com.GamerCodeFalse.Finisher.main.Game.getTILE_WIDTH;

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
	private int speed = 10;
	private int jumpSpeed = 25;
	public int animationIndex = 0;
	public int currentAnimation = PlayerConstants.APIdle;
	public int directionX = 0;
	public int directionY = 0;
	private String path;
	private Level current;
	private boolean idle = false;
	private boolean run = false;
	private boolean jump = false;
	private String[] extraPaths = new String[2];
	private Game game;
	private boolean touchingGround = false;
	private int[] spawnCoords = new int[2];

	
	public Player(int x, int y, int w, int h, String type, String path,LevelManager levelManager,String jump,String fall,Game game) {
		super(x, y, w, h, type, path);
		
		this.path = path;
		this.mass = w/Game.getTILE_WIDTH();
		
		current = levelManager.currentLevel;
		
		spawnCoords[0] = x;
		spawnCoords[1] = y;
		
		extraPaths[0] = jump;
		extraPaths[1] = fall;
		
		
		this.setGame(game);
	}
	@Override
	public void draw(Graphics g) {	
		sprite = LoadSave.importSpriteFromSpriteSheet(path,animationIndex,currentAnimation);
		
		if(directionY == 1 && !run) {
			sprite = LoadSave.importSprite(extraPaths[1]);
			if(directionX == -1) {
				sprite = LoadSave.flipSprite(sprite);
				g.drawImage(sprite, getX(),getY(),getWidth(),getHeight(), null);
			}
			else if(directionX == 1){
				g.drawImage(sprite, getX(),getY(),getWidth(),getHeight(), null);
			}else {
				g.drawImage(sprite, getX(),getY(),getWidth(),getHeight(), null);
			}
		}else if(directionY == -1 && !run) {
			sprite = LoadSave.importSprite(extraPaths[0]);
			if(directionX == -1) {
				sprite = LoadSave.flipSprite(sprite);
				g.drawImage(sprite, getX(),getY(),getWidth(),getHeight(), null);
			}
			else if(directionX == 1){
				g.drawImage(sprite, getX(),getY(),getWidth(),getHeight(), null);
			}else {
				g.drawImage(sprite, getX(),getY(),getWidth(),getHeight(), null);
			}
			
		}else if(directionY == 1 && run) {
			currentAnimation = PlayerConstants.APRun;	
			if(animationIndex == PlayerConstants.ALRun) {
				animationIndex = 0;
			}
			if(directionX == -1) {
				sprite = LoadSave.flipSprite(sprite);
				g.drawImage(sprite, getX(),getY(),getWidth(),getHeight(), null);
			}
			else if(directionX == 1){
				g.drawImage(sprite, getX(),getY(),getWidth(),getHeight(), null);
			}else {
				g.drawImage(sprite, getX(),getY(),getWidth(),getHeight(), null);
			}
		}else if(directionY == -1 && run) {
			currentAnimation = PlayerConstants.APRun;
			if(animationIndex == PlayerConstants.ALRun) {
				animationIndex = 0;
			}
			if(directionX == -1) {
				sprite = LoadSave.flipSprite(sprite);
				g.drawImage(sprite, getX(),getY(),getWidth(),getHeight(), null);
			}
			else if(directionX == 1){
				g.drawImage(sprite, getX(),getY(),getWidth(),getHeight(), null);
			}else {
				g.drawImage(sprite, getX(),getY(),getWidth(),getHeight(), null);
			}
		}
		else {
			if(directionX == -1) {
				sprite = LoadSave.flipSprite(sprite);
				g.drawImage(sprite, getX(),getY(),getWidth(),getHeight(), null);
			}
			else if(directionX == 1){
				g.drawImage(sprite, getX(),getY(),getWidth(),getHeight(), null);
			}else {
				g.drawImage(sprite, getX(),getY(),getWidth(),getHeight(), null);
			}
		}
		
//		g.drawRect(getBoundsX().x, getBoundsX().y, getBoundsX().width, getBoundsX().height);
//		g.drawRect(getBoundsY().x, getBoundsY().y, getBoundsY().width, getBoundsY().height);
//		
//	for(int i = 0;i<current.getCollisionTiles().size();i++) {
//		g.fillRect(
//					(int)(current.getCollisionTiles().get(i).getX()), 
//					(int)(current.getCollisionTiles().get(i).getY()), 
//					(int)(current.getCollisionTiles().get(i).getWidth()),
//					(int)(current.getCollisionTiles().get(i).getHeight()));
//			}
		
//		g.drawRect(getBoundsY2().x,getBoundsY2().y,getBoundsY2().width,getBoundsY2().height);
	}
	
	@Override
	public void update() {
		animationIndex += 1;

		if(animationIndex == PlayerConstants.ALIdle) {
			animationIndex = 0;
		}
		
		
		
		if(falling && !idle && inAir) {
			fall();
		}else {
			idle = true;
		}
		if(!run && !falling && idle &&!jump) {
			idle();
		}
		if(falling) {
			jump = false;
		}
		if(jump) {
			falling = false;
		}
		
		if(getX() <= -25) {
			setX(spawnCoords[0]);
			setY(spawnCoords[1]);	
		}
		
		if(getX() >= current.WIDTH*getTILE_WIDTH()+100) {
			setX(spawnCoords[0]);
			setY(spawnCoords[1]);	
		}
		

		if(getY() >= Game.HEIGHT) {
			setX(spawnCoords[0]);
			setY(spawnCoords[1]);
		}
		
		for(int i = 0;i<current.getCollisionTiles().size();i++) {
			if(getBoundsX().intersects(current.getCollisionTiles().get(i))) {
				if(directionX == 1) {
					this.setVelocityX(0);
					directionX = 1;
					this.setX(current.getCollisionTiles().get(i).x-54);
					
				}
				if(directionX == -1) {
					this.setVelocityX(0);
					directionX = -1;
					this.setX(current.getCollisionTiles().get(i).x+15);
				}
			}
				
			
			if(getBoundsY().intersects(current.getCollisionTiles().get(i))) {
				if(directionY == 1) {
					this.setVelocityY(0);
					directionY = 1;
					this.setY(current.getCollisionTiles().get(i).y-64);
					falling = false;
					inAir = false;
					if(!run) {
						idle();
					}
					jump = false;
				}
				if(directionY == -1) {
					directionY = -1;
					this.setVelocityY(0);
					this.setY(current.getCollisionTiles().get(i).y+current.getCollisionTiles().get(i).height);
				}
			}
			if(!getBoundsY2().intersects(current.getCollisionTiles().get(i))) {
				inAir = true;
			}
		}
		
		
		if(idle && inAir) {
			idle = false;
			falling = true;
		}
		
		this.setX(this.getVelocityX()*directionX+this.getX());
		this.setY(this.getVelocityY()*directionY+this.getY());
	}
	
	public void run(int direction) {
		
			this.directionX = direction;
			
			run = true;
			idle = false;
			
			this.setVelocityX(this.getSpeed());
			
			currentAnimation = PlayerConstants.APRun;
			
			if(animationIndex == PlayerConstants.ALRun) {
				animationIndex = 0;
			}
	}
	public void jump() {
		
		jump = true;
		inAir = true;
		
		directionY = -1;
		
		this.setVelocityY((int)(this.mass*this.jumpSpeed*Game.gravity));
		
	
	}
	public void fall() {
		
		directionY = 1;
		
		inAir = true;
		
		this.setVelocityY((int)((Game.gravity*this.mass)));
		
	}
	public Rectangle getBoundsX() {
		int bx = this.getX()+this.velocityX*directionX+10;
		int by = this.getY()+14;
		int bw = 44+(this.velocityX/2)*directionX;
		int bh = 50;
		
		return new Rectangle(bx,by,bw,bh);
	}
	public Rectangle getBoundsY() {
		int bx = this.getX()+10;
		int by = this.getY()+this.velocityY*directionY+14;
		int bw = 44;
		int bh = 50+(this.velocityY/2)*directionY;
		
		return new Rectangle(bx,by,bw,bh);
	}
	public Rectangle getBoundsY2() {
		int bx = this.getX();
		int by = this.getY();
		int bw = 0;
		int bh = 70+(this.velocityY/2)*directionY;
		
		return new Rectangle(bx,by,bw,bh);
	}
	public void idle() {
		run = false;
		directionY = 0;
		currentAnimation = PlayerConstants.APIdle;
	}
	public void stopX() {
		idle = true;
		this.setVelocityX(0);
	}
	public void stopY() {
		directionY = 0;
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
	public Game getGame() {
		return game;
	}
	public void setGame(Game game) {
		this.game = game;
	}
	public boolean isTouchingGround() {
		return touchingGround;
	}
	public void setTouchingGround(boolean touchingGround) {
		this.touchingGround = touchingGround;
	}
}
