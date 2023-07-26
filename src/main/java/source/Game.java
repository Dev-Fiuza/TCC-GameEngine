package source;

import source.conf.WindowConf;

public class Game implements Runnable{
	
	private WindowConf window;
	private Thread thread;
	private boolean isRunning;
	
	public Game() {
		window = new WindowConf(240, 160, 4);
	}
	
	public static void main (String[] args) {
		Game game = new Game();
		game.start();
	}
	
	public synchronized void start() {
		thread = new Thread(this);
		isRunning = true;
		thread.start();
	}

	public synchronized void stop() {

	}
	
	
	public void tick() {
		
	}
	
	
	//Método que contém o sistema para controlar os ticks por segundos
	public void run() {
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		int frames = 0;
		double timer = System.currentTimeMillis();
		
		//Game loop
		while (isRunning) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if (delta >= 1) {
				tick();
				window.render();
				frames++;
				delta--;
			}

			if (System.currentTimeMillis() - timer >= 1000) {
				System.out.println("FPS: " + frames);
				frames = 0;
				timer += 1000;
			}
		}
		stop();
	}

}
