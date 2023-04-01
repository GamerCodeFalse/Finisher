package com.GamerCodeFalse.Finisher.main;

import java.awt.Graphics;

import javax.swing.JPanel;

import com.GamerCodeFalse.Finisher.gameobjects.GameObjects;
import com.GamerCodeFalse.Finisher.utilz.input.KeyboardInput;
import com.GamerCodeFalse.Finisher.utilz.input.MouseInput;

public class Panel extends JPanel{
	private static final long serialVersionUID = 1L;

	public Panel() {
		
		addKeyListener(new KeyboardInput());
		addMouseListener(new MouseInput());
		addMouseMotionListener(new MouseInput());
		
	}
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		GameObjects.player.draw(g);
	}
}
