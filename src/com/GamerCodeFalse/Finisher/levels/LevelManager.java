package com.GamerCodeFalse.Finisher.levels;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.GamerCodeFalse.Finisher.main.Game;
import com.GamerCodeFalse.Finisher.utilz.LevelConstants;
import com.GamerCodeFalse.Finisher.utilz.LoadSave;

public class LevelManager {
	private Game game;
	public Level currentLevel;
	private BufferedImage sprite0;
	private BufferedImage[] sprite1 = new BufferedImage[2];
	
	private Level[] levels = new Level[10];
	public LevelManager(Game game) {
		this.setGame(game);
		
		sprite0 = LoadSave.importSprite("/level/Level0/Level0.png");
		sprite1[0] = LoadSave.importSprite("/level/Level1/Level1.png");
		
		sprite1[1] = LoadSave.importSprite("/level/Level1/Level1Prop.png");
		
		levels[0] = new Level(sprite0,null,40,LevelConstants.level0Collision);
		levels[1] = new Level(sprite1[0],sprite1[1],60,LevelConstants.level1Collision);
		
		currentLevel = levels[1];
	}
	public void draw(Graphics g) {
		currentLevel.draw(g);
	}
	public void drawProp(Graphics g){
		currentLevel.drawProps(g);
	}
	public void update() {
		currentLevel.update();
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}
}
