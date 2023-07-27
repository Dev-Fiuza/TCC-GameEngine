package source.menus;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class MainMenu {

	public String[] options = { "Iniciar Jogo", "Opções", "Créditos" };
	public int currentOption = 0;
	public int maxOption = options.length - 1;

	public boolean up, down, enter;

	public void tick() {
		if (up) {
			up = false;
			currentOption--;
			if (currentOption < 0) {
				currentOption = maxOption;
			}
		}
		if (down) {
			down = false;
			currentOption++;
			if (currentOption > maxOption) {
				currentOption = 0;
			}
		}

		if (enter) {
			enter = false;
			if (options[currentOption] == "Iniciar Jogo") {
				// Game.gameState = "INIT GAME";
			} else if (options[currentOption] == "Opções") {
				// Game.gameState = "OPTIONS";
			} else if (options[currentOption] == "Créditos") {
				// Game.gameState = "CREDITS";
			}
		}

	}

	public void render(Graphics g) {
		// Fundo do menu
		g.setColor(Color.BLACK);

		// Título do Game e informação adicional
		g.setColor(Color.RED);
		g.setFont(new Font("Verdana", Font.BOLD, 120));
		g.drawString("Demo Project", 90, 120);
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", Font.ROMAN_BASELINE, 40));
		g.drawString("Use as setas ou as teclas | w | | s | para", 100, 500);
		g.drawString("navegar no menu", 300, 540);
		g.drawString("Para selecionar uma opção pressione enter", 90, 600);

		// Opções do menu
		g.setFont(new Font("Arial", Font.ROMAN_BASELINE, 45));
		g.drawString("Iniciar Jogo", 340, 280);
		g.drawString("Opções", 340, 360);
		g.drawString("Créditos", 340, 440);

		if (options[currentOption] == "Iniciar Jogo") {
			g.drawString(">", 310, 280);
		} else if (options[currentOption] == "Opções") {
			g.drawString(">", 310, 360);
		} else if (options[currentOption] == "Créditos") {
			g.drawString(">", 310, 440);
		}

	}
}
