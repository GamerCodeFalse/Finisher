package com.GamerCodeFalse.Finisher.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.GamerCodeFalse.Finisher.gameobjects.GameObjects;

public class KeyboardListner implements KeyListener {

	@Override
	public void keyTyped(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_D) {
			GameObjects.player.run(-1);
		}
		if(e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_A) {
			GameObjects.player.run(1);
		}
		if(e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
			GameObjects.player.jump();
		}
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
			GameObjects.player.run(-1);
		}
		if(e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
			GameObjects.player.run(1);
		}
		if(e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
			GameObjects.player.jump();
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_D) {
			GameObjects.player.stopX();
			GameObjects.player.idle();
		}
		if(e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_A) {
			GameObjects.player.stopX();
			GameObjects.player.idle();
		}
		if(e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W)  {
			GameObjects.player.fall();
			GameObjects.player.idle();
		}
	}

}
