package game.commands;

import game.engine.GameWindow;

public class MoveLeftCommand implements ICommand{

	@Override
	public void execute(GameWindow gameWindow) {
		gameWindow.player.moveLeft();
	}
}
