package com.GamerCodeFalse.Finisher.levels;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.GamerCodeFalse.Finisher.main.Game;
import com.GamerCodeFalse.Finisher.utilz.LevelConstants;
import com.GamerCodeFalse.Finisher.utilz.LoadSave;

public class LevelManager {
	private Game game;
	private Level currentLevel;
	private BufferedImage sprite0;
	private Level[] levels = new Level[10];
	public LevelManager(Game game) {
		this.setGame(game);
		sprite0 = LoadSave.importSprite("/level/Level0/Level0.png");
		
		levels[0] = new Level(LevelConstants.level0Collision, sprite0);
		
		currentLevel = levels[0];
	}
	public void draw(Graphics g) {
		currentLevel.draw(g);
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
