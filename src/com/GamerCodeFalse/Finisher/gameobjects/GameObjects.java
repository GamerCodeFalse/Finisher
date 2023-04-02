package com.GamerCodeFalse.Finisher.gameobjects;

import com.GamerCodeFalse.Finisher.gameobjects.entity.Player;
import com.GamerCodeFalse.Finisher.main.Game;
import com.GamerCodeFalse.Finisher.main.Window;

public class GameObjects {
	public static Player player = new Player(Window.WIDTH/2,Window.HEIGHT/2,64,64,"player","/sprites/player/player_spritesheet.png",Game.getLevelManager());
}
