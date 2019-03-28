package game.commands;

import game.engine.GameWindow;

public class QuitGameCommand implements ICommand{

	@Override
	public boolean execute(GameWindow gameWindow) {
		System.exit(0);
		return true;
	}

}
