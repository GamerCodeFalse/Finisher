package com.GamerCodeFalse.Finisher.main;


import com.GamerCodeFalse.Finisher.gameobjects.GameObjects;

public class Game implements Runnable{

	//Variables
    public static int TILE_WIDTH = 16;
    public static int TILE_HEIGHT = 16;
    public static int rows = 60;
    public static int cols = rows*480/640;
    public int UPS = 25;
    public int FPS = 120;
    public static int WIDTH = TILE_WIDTH*rows;
    public static int HEIGHT = TILE_HEIGHT*cols;
    public Window window;
    public Panel panel;
    public String version = "0.0.1v-ALPHA";
    public boolean running = false;
    public static float gravity = 2f;
    
    //Thread Variables
    private Thread thread;

    //Constructor
    public Game(){
        panel = new Panel();
        window = new Window(WIDTH,HEIGHT,"Finisher! "+version, panel, this);
        
        
        panel.setFocusable(true);
        panel.requestFocus();
    }
    
    //Updates Game
    public void update(){
    	GameObjects.player.update();
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
                System.out.println("FPS: "+frames+" | UPS: "+ticks);
                frames = 0;
                ticks = 0;
            }
        }
    }


}
