package game.commands;

import game.engine.GameWindow;

public class MoveUpCommand implements ICommand{

	@Override
	public void execute(GameWindow gameWindow) {
		gameWindow.player.moveUp();
	}
}
