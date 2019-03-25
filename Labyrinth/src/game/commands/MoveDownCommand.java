package game.commands;

import game.engine.GameWindow;

public class MoveDownCommand implements ICommand{

	@Override
	public void execute(GameWindow gameWindow) {
		gameWindow.player.moveDown();
	}
}
