package com.GamerCodeFalse.Finisher.main;

import java.awt.Graphics;

import javax.swing.JPanel;

import com.GamerCodeFalse.Finisher.gameobjects.GameObjects;
import com.GamerCodeFalse.Finisher.input.KeyboardListner;
import com.GamerCodeFalse.Finisher.levels.LevelManager;

public class Panel extends JPanel{
    private static final long serialVersionUID = -8867558410352870995L;
    private static LevelManager levelManager;
    
    public Panel() {
    	addKeyListener(new KeyboardListner());
    	
    	this.setLevelManager(Game.getLevelManager());
    	
    	setFocusable(true);
    	requestFocus();
    }

	@Override
    public void paint(Graphics g){
        super.paint(g);
        getLevelManager().draw(g);
        GameObjects.player.draw(g);
    }

	public static LevelManager getLevelManager() {
		return levelManager;
	}

	public void setLevelManager(LevelManager levelManager) {
		Panel.levelManager = levelManager;
	}
}
