package com.GamerCodeFalse.Finisher.main;

import java.awt.Dimension;

import javax.swing.JFrame;

public class Window{
	
	private static final long serialVersionUID = 1L;
	
	public static JFrame window;

	public Window(int Width,int Height,String Label,Panel panel,boolean isVisible) {
		window = new JFrame(Label);
		
		window.setMinimumSize(new Dimension(Width,Height));
		window.setVisible(isVisible);
		
		window.add(panel);
		window.pack();
		
		windowInit();
		
	}
	
	private void windowInit() {
		window.setLocationRelativeTo(null);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

}
