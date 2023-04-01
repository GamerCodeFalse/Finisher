package com.GamerCodeFalse.Finisher.gameobjects;

import com.GamerCodeFalse.Finisher.gameobjects.entity.player.Player;
import com.GamerCodeFalse.Finisher.main.Game;

public class GameObjects {
	public static Player player = new Player(0, (Game.HEIGHT/2)-64, 64, 64, "/player/player1/player1_");

	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		GameObjects.player = player;
	}
}
