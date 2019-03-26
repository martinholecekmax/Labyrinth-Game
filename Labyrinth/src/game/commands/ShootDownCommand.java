package game.commands;

import game.engine.GameWindow;
import game.enums.Direction;

public class ShootDownCommand implements ICommand{

	@Override
	public boolean execute(GameWindow gameWindow) {
		gameWindow.shoot(Direction.DOWN);
		return true;
	}
}
