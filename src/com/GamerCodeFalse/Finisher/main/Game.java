package com.GamerCodeFalse.Finisher.main;


import com.GamerCodeFalse.Finisher.gameobjects.GameObjects;
import com.GamerCodeFalse.Finisher.levels.LevelManager;

public class Game implements Runnable{

	//Variables
    public static int rows = 40;
    public static int cols = rows*3/4;
    public static int UPS = 25;
    public static int FPS = 60;
    public static float scale = 1.25f;
    public static int TILE_WIDTH = (int) (16*scale);
    public static int TILE_HEIGHT = (int) (16*scale);
    public static int WIDTH = (int)(TILE_WIDTH*rows*scale);
    public static int HEIGHT = (int)(TILE_HEIGHT*cols*scale);
    public static Window window;
    public static Panel panel;
    public static String version = "0.1.1v-ALPHA";
    public boolean running = false;
    public static float gravity = 3f;
    
    //Thread Variables
    private Thread thread;
    
    //Game Objects
    private static LevelManager levelManager;

    //Constructor
    public Game(){
    	setLevelManager(new LevelManager(this));
    	
        panel = new Panel();
        window = new Window(WIDTH,HEIGHT,"Finisher! "+version, panel, this);
        
        panel.setFocusable(true);
        panel.requestFocus();
    }
    
    //Updates Game
    public void update(){
    	GameObjects.player.update();
    	getLevelManager().update();
    }
    
    //Thread Methods
    public synchronized void start(){
        running = true;
        thread = new Thread(this);
        thread.start();
    }
    public synchronized void stop(){
        try {
            thread.join();
            running = false;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    //Runnable Methods
    @Override
    public void run() {
        long initialTime = System.nanoTime();
        final double timeU = 1000000000 / UPS;
        final double timeF = 1000000000 / FPS;
        double deltaU = 0, deltaF = 0;
        int frames = 0, ticks = 0;
        long timer = System.currentTimeMillis();

        while (true) {
            long currentTime = System.nanoTime();
            deltaU += (currentTime - initialTime) / timeU;
            deltaF += (currentTime - initialTime) / timeF;
            initialTime = currentTime;

            if (deltaU >= 1) {
                update();
                ticks++;
                deltaU--;
            }

            if (deltaF >= 1) {
                panel.repaint();
                frames++;
                deltaF--;
            }

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
//                System.out.println("FPS: "+frames+" | UPS: "+ticks);
                frames = 0;
                ticks = 0;
            }
        }
    }

	public static LevelManager getLevelManager() {
		return levelManager;
	}

	public static void setLevelManager(LevelManager levelManager) {
		Game.levelManager = levelManager;
	}


}
