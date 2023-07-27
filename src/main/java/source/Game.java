package source;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import source.menus.MainMenu;

public class Game extends Canvas implements Runnable,KeyListener {

	private static final long serialVersionUID = 1L;
	
	//Variáveis da parte Gráfica
	private static JFrame frame;
	private int width = 240;
	private int height = 120;
	private int scale = 3;

	//Variáveis para estados do game
	private String gameState = "MAIN_MENU";
	
	//Variávies de controle interno
	private Thread thread;
	private boolean isRunning;

	// Objetos de diferentes partes do jogo
	public static MainMenu mainMenu;

	public Game() {
		mainMenu = new MainMenu();
		initFrame();
	}

	public static void main(String[] args) {
		Game game = new Game();
		game.start();
	}

	private void initFrame() {
		setPreferredSize(new Dimension(width * scale, height * scale));
		frame = new JFrame("Projeto de Demonstração");
		frame.add(this);
		frame.setResizable(false);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	public synchronized void start() {
		thread = new Thread(this);
		isRunning = true;
		thread.start();
	}

	public synchronized void stop() {

	}

	private void tick() {
		if (gameState == "MAIN_MENU") {
			mainMenu.tick(); 
			} 
	}

	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, width*scale, height*scale);
		
		if(gameState=="MAIN_MENU") {
			mainMenu.render(g);
		}
		
		bs.show();

	}

	// Método que contém o sistema para controlar os ticks por segundos
	public void run() {
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		int frames = 0;
		double timer = System.currentTimeMillis();

		// Game loop
		while (isRunning) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if (delta >= 1) {
				tick();
				render();
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

	
	//Configuração de ações após entrada de dados via teclado.
	
	public void keyTyped(KeyEvent e) {
		
	}

	public void keyPressed(KeyEvent e) {
		
	}

	public void keyReleased(KeyEvent e) {
		
	}

}
