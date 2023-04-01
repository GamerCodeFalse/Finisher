package com.GamerCodeFalse.Finisher.levels;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.GamerCodeFalse.Finisher.main.Game;

public class Level {
	public int[] collision;
	public BufferedImage sprite;
	public int WIDTH = 40;
	public int HEIGHT = 30;
	
	public Level(int[] collision,BufferedImage sprite) {
		this.collision = collision;
		this.sprite = sprite;
	}
	public void draw(Graphics g) {
		g.drawImage(sprite, 0, 0, (int)(WIDTH*Game.scale*Game.TILE_WIDTH),(int)(HEIGHT*Game.scale*Game.TILE_HEIGHT), null);
	}
	public void update() {}
}
