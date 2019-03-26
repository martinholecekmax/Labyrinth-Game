package game.commands;

import game.engine.GameWindow;

public class MoveLeftCommand implements ICommand{

	@Override
	public boolean execute(GameWindow gameWindow) {
		return gameWindow.player.moveLeft(32);
	}
}
