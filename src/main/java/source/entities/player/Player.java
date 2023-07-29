package source.entities.player;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import source.Game;
import source.entities.Entity;

public class Player extends Entity {

	private boolean right, up, left, down = false;
	// Direções:
	private int rDir = 0, lDir = 1, uDir = 2, dDir = 3;
	private int dir = dDir;

	private int speed = 3;

	// Controle de quadrados
	private int frames = 0;
	private int maxFrames = 6;
	private int indexAnimation = 0;
	private int maxIndex = 3;
	private boolean isMoving = false;

	// animações de caminhada (walk)
	private BufferedImage[] wLeftDir, wRightDir, wUpDir, wDownDir;

	// animações de corrida (run)
	// private BufferedImage[] rLeftDir, rRightDir, rUpDir, rDownDir;

	public Player(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
		wLeftDir = new BufferedImage[4];
		wRightDir = new BufferedImage[4];
		wUpDir = new BufferedImage[4];
		wDownDir = new BufferedImage[4];

		for (int i = 0; i < 4; i++) {
			wLeftDir[i] = Game.playerSprite.getSprite(0 + i * 64, 448, 64, 64);
			wRightDir[i] = Game.playerSprite.getSprite(0 + i * 64, 384, 64, 64);
			wUpDir[i] = Game.playerSprite.getSprite(0 + i * 64, 320, 64, 64);
			wDownDir[i] = Game.playerSprite.getSprite(0 + i * 64, 256, 64, 64);
		}

	}

	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public boolean isUp() {
		return up;
	}

	public void setUp(boolean up) {
		this.up = up;
	}

	public boolean isLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}

	public boolean isDown() {
		return down;
	}

	public void setDown(boolean down) {
		this.down = down;
	}

	public void tick() {
		isMoving = false;
		if (right) {
			isMoving = true;
			dir = rDir;
			x += speed;
		} else if (left) {
			isMoving = true;
			dir = lDir;
			x -= speed;
		}
		if (up) {
			isMoving = true;
			dir = uDir;
			y -= speed;
		} else if (down) {
			isMoving = true;
			dir = dDir;
			y += speed;
		}

		if (isMoving) {
			frames++;
			if (frames == maxFrames) {
				frames = 0;
				indexAnimation++;
				if (indexAnimation > maxIndex) {
					indexAnimation = 0;
				}
			}
		}

	}

	public void render(Graphics g) {
		if (dir == rDir) {
			g.drawImage(wRightDir[indexAnimation], this.getX(), this.getY(), null);
		} else if (dir == lDir) {
			g.drawImage(wLeftDir[indexAnimation], this.getX(), this.getY(), null);
		} else if (dir == uDir) {
			g.drawImage(wUpDir[indexAnimation], this.getX(), this.getY(), null);
		} else if (dir == dDir) {
			g.drawImage(wDownDir[indexAnimation], this.getX(), this.getY(), null);
		}

	}

}
