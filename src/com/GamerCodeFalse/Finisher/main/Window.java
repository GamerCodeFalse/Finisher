package com.GamerCodeFalse.Finisher.main;

import java.awt.Dimension;

import javax.swing.JFrame;


public class Window extends JFrame{
	   private static final long serialVersionUID = -2145602635662551898L;
	//Variables
    public static int WIDTH = 0;
    public static int HEIGHT = 0;
    private String title = "";

    //Constructor
    public Window(int width, int height, String title,Panel panel,Game game){
        Window.WIDTH = width;
        Window.HEIGHT = height;
        this.title = title;

        add(panel);
        initWindow();
        game.start();

    }

    //Public Methods
    public void initWindow(){
        setTitle(title);
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setMaximumSize(new Dimension(WIDTH, HEIGHT));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
