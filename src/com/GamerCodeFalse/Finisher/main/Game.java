package com.GamerCodeFalse.Finisher.main;

import com.GamerCodeFalse.Finisher.gameobjects.GameObjects;

public class Game implements Runnable{
	public static int TILE_WIDTH = 16;
	public static int TILE_HEIGHT = 16;
	
	public static int WIDTH_BY_TILE = 55;
	public static int HEIGHT_BY_TILE = WIDTH_BY_TILE*3/4;
	
	public static int WIDTH =TILE_WIDTH*WIDTH_BY_TILE;
	public static int HEIGHT =TILE_HEIGHT*HEIGHT_BY_TILE;
	
	public int FPS = 60;
	public int UPS = 20;
	
	public boolean Running = true;
	
	public Window window;
	public Panel panel;
	
	public Thread thread;

	public Game() {
		panel = new Panel();
		window = new Window(WIDTH, HEIGHT, "Finisher! ALPHA v1.0",panel, true);
		
		panel.requestFocus();
		beginLoop();
		
	}
	public void beginLoop() {
		thread = new Thread(this);
		thread.start();
	}
	public void update() {
		GameObjects.player.update();
	}
	public void render() {
		panel.repaint();
	}
	@Override
	public void run() {
		long initialTime = System.nanoTime();
		final double timeU = 1000000000 / UPS;
		final double timeF = 1000000000 / FPS;
		double deltaU = 0, deltaF = 0;
		int frames = 0, ticks = 0;
		long timer = System.currentTimeMillis();

		 while (Running) {

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
				 render();
				 frames++;
				 deltaF--;
			 }

			 if (System.currentTimeMillis() - timer > 1000) {
				 System.out.println(String.format("FPS: %s UPS: %s", frames, ticks));
				 frames = 0;
				 ticks = 0;
				 timer += 1000;
			 }
		 }
	}
}
