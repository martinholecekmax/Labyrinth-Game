package game.commands;

import game.engine.GameWindow;

public class MoveRightCommand implements ICommand {

	@Override
	public boolean execute(GameWindow gameWindow) {
		return gameWindow.player.moveRight(32);
	}
}
