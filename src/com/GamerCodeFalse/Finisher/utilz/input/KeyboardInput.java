package com.GamerCodeFalse.Finisher.utilz.input;

import static java.awt.event.KeyEvent.*;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import com.GamerCodeFalse.Finisher.gameobjects.GameObjects;

public class KeyboardInput implements KeyListener {

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == VK_LEFT) {
			GameObjects.player.run(-1);
		}
		if(e.getKeyCode() == VK_RIGHT) {
			GameObjects.player.run(1);
		}
		if(e.getKeyCode() == VK_UP) {
			GameObjects.player.jump();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == VK_LEFT) {
			GameObjects.player.idle();
		}
		if(e.getKeyCode() == VK_RIGHT) {
			GameObjects.player.idle();
		}
		if(e.getKeyCode() == VK_UP) {
			GameObjects.player.fall();
		}
	}

}
