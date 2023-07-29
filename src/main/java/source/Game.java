package source;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import source.entities.Entity;
import source.entities.player.Player;
import source.graphics.Spritesheet;
import source.menus.MainMenu;
import source.menus.PauseMenu;

public class Game extends Canvas implements Runnable, KeyListener {

	private static final long serialVersionUID = 1L;

	// Variáveis da parte Gráfica
	private static JFrame frame;
	private int width = 240;
	private int height = 120;
	private int scale = 3;

	// Variáveis para estados do game
	public static String gameState = "MAIN_MENU";

	// Variávies de controle interno
	private Thread thread;
	private boolean isRunning;

	// Objetos de diferentes partes do jogo
	private MainMenu mainMenu;
	private PauseMenu pauseMenu;

	// Entidades e gráficos
	private List<Entity> entities;
	public static Spritesheet playerSprite;
	private Player player;

	public Game() {
		addKeyListener(this);
		mainMenu = new MainMenu();
		pauseMenu = new PauseMenu();
		initFrame();
		// Inicialização de objetos
		entities = new ArrayList<Entity>();
		playerSprite = new Spritesheet("/character/player-char.png");
		player = new Player(0, 0, 64, 64, playerSprite.getSprite(0, 0, 64, 64));
		entities.add(player);
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
		isRunning = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void tick() {
		for (int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);
			e.tick();
		}

		if (gameState == "MAIN_MENU") {
			mainMenu.tick();
		} else if (gameState == "PAUSE_MENU") {
			pauseMenu.tick();
		} else if (gameState == "GAMEPLAY") {

		}

	}

	private void render() {

		// CONFIGURAÇÃO INICIAL PARA RENDERIZAÇÃO
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, width * scale, height * scale);

		// RENDERIZAÇÃO DO GAME
		for (int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);
			e.render(g);
		}

		g.dispose();
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

	// Configuração de ações após entrada de dados via teclado.

	public void keyTyped(KeyEvent e) {

	}

	public void keyPressed(KeyEvent e) {

		// Movimentação CIMA BAIXO
		if (e.getKeyCode() == KeyEvent.VK_W) {
			player.setUp(true);
		} else if (e.getKeyCode() == KeyEvent.VK_S) {
			player.setDown(true);
		}

		// Movimentaão ESQUERDA DIREITA
		if (e.getKeyCode() == KeyEvent.VK_A) {
			player.setLeft(true);
		} else if (e.getKeyCode() == KeyEvent.VK_D) {
			player.setRight(true);
		}
	}

	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_W) {
			player.setUp(false);
		} else if (e.getKeyCode() == KeyEvent.VK_S) {
			player.setDown(false);
		}

		// Movimentaão ESQUERDA DIREITA
		if (e.getKeyCode() == KeyEvent.VK_A) {
			player.setLeft(false);
		} else if (e.getKeyCode() == KeyEvent.VK_D) {
			player.setRight(false);
		}
	}

}
