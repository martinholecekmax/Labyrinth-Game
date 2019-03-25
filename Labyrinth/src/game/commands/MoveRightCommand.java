package game.commands;

import game.engine.GameWindow;

public class MoveRightCommand implements ICommand {

	@Override
	public void execute(GameWindow gameWindow) {
		gameWindow.player.moveRight();
	}
}
