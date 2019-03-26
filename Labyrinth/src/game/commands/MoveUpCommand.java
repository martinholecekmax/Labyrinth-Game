package game.commands;

import game.engine.GameWindow;

public class MoveUpCommand implements ICommand{

	@Override
	public boolean execute(GameWindow gameWindow) {
		return gameWindow.player.moveUp(32);
	}
}
