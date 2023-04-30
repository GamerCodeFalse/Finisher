package com.GamerCodeFalse.Finisher.gameobjects;

import com.GamerCodeFalse.Finisher.gameobjects.entity.Player;
import com.GamerCodeFalse.Finisher.main.Game;

public class GameObjects {
	public static Player player = new Player(
			80,
			Game.HEIGHT/2,
			64,
			64,
			"player",
			"/sprites/player/player_spritesheet.png",
			Game.getLevelManager(),
			"/sprites/player/jump.png",
			"/sprites/player/fall.png",null)
	;
}
