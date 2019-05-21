package game.commands;

import game.engine.GameWindow;

public class FreezeCommand implements ICommand{

	@Override
	public boolean execute(GameWindow gameWindow) {
		gameWindow.freezeEnemies();
		return true;
	}

}
