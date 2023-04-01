package com.GamerCodeFalse.Finisher.main;

import java.awt.Graphics;

import javax.swing.JPanel;

import com.GamerCodeFalse.Finisher.gameobjects.GameObjects;
import com.GamerCodeFalse.Finisher.input.KeyboardListner;

public class Panel extends JPanel{
    private static final long serialVersionUID = -8867558410352870995L;
    
    public Panel() {
    	addKeyListener(new KeyboardListner());
    	
    	setFocusable(true);
    	requestFocus();
    }

	@Override
    public void paint(Graphics g){
        super.paint(g);
        GameObjects.player.draw(g);
    }
}
