package com.GamerCodeFalse.Finisher.gameobjects.entity.player;

import com.GamerCodeFalse.Finisher.gameobjects.entity.Entity;
import com.GamerCodeFalse.Finisher.utilz.animation.AnimationValues;
import com.GamerCodeFalse.Finisher.utilz.sprite.ImportSprite;

public class Player extends Entity {

	public Player(int x, int y, int width, int height, String imgSrc) {
		super(x, y, width, height, imgSrc);
		
		this.setSpeed(10.0f);
		this.setVelocity(20.0f);
	}
	public void run(int dir) {
		this.setAction(AnimationValues.RUN_INDEX);
		this.getPosition()[0] += this.getSpeed()*dir;
	}
	@Override
	public void update() {
		this.setSpriteAtlas(ImportSprite.importSprite(this.getImgSrc()+Integer.toString(getAction())+".png"));
		this.setSprite(ImportSprite.importSpriteFromSpriteSheet(this.getSpriteAtlas(),this.getAnimationIndex(),32,32));
		if(this.getAnimationIndex() == (ImportSprite.GetImageWidth(this.getSpriteAtlas())/32)-1) {
			setAnimationIndex(0);
		}else {
			setAnimationIndex(getAnimationIndex() + 1);
		}
		
	}
	public void jump() {
		this.setAction(AnimationValues.JUMP_INDEX);
		this.getPosition()[1] -= this.getVelocity();
		
		if(this.getAnimationIndex() <= (ImportSprite.GetImageWidth(this.getSpriteAtlas())/32)-1) {
			setAnimationIndex(0);
		}else {
			setAnimationIndex(getAnimationIndex() + 1);
		}
	}
	public void fall() {
		this.setAction(AnimationValues.FALL_INDEX);
		this.getPosition()[1] += this.getVelocity();
		
		if(this.getAnimationIndex() <= (ImportSprite.GetImageWidth(this.getSpriteAtlas())/32)-1) {
			setAnimationIndex(0);
			idle();
		}else {
			setAnimationIndex(getAnimationIndex() + 1);
		}
	}
	public void idle() {
		this.setAction(AnimationValues.IDLE_INDEX);
		
		if(this.getAnimationIndex() == (ImportSprite.GetImageWidth(this.getSpriteAtlas())/32)-1) {
			setAnimationIndex(0);
		}else {
			setAnimationIndex(getAnimationIndex() + 1);
		}

	}
}
