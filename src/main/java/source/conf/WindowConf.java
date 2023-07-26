package source.conf;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class WindowConf extends Canvas {
	
	private static final long serialVersionUID = 1L;

	private static JFrame frame;
	
	private int WIDTH;
	private int HEIGHT;
	private int SCALE;
	
	private BufferedImage image;

	
	public WindowConf() {
		
	}
	
	public WindowConf(int w, int h, int s) {
		this.WIDTH = w;
		this.HEIGHT = h;
		this.SCALE = s;
		initFrame();
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	}
	
	private void initFrame() {
		setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		frame = new JFrame("Projeto de Demonstração");
		frame.add(this);
		frame.setResizable(false);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = image.getGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);

		g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, WIDTH * SCALE, HEIGHT * SCALE, null);
		
		bs.show();
	}
	
}
