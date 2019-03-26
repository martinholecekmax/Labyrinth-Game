package game.commands;

import game.engine.GameWindow;

public class MoveDownCommand implements ICommand{

	@Override
	public boolean execute(GameWindow gameWindow) {
		return gameWindow.player.moveDown(32);
	}
}
