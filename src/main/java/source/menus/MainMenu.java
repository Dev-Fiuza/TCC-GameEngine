package source.menus;

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
				//Game.gameState = "INIT GAME";
			} else if (options[currentOption] == "Opções") {
				//Game.gameState = "OPTIONS";
			}
			else if (options[currentOption] == "Créditos") {
				//Game.gameState = "CREDITS";
			}
		}

	}

	public void render() {

	}

}
