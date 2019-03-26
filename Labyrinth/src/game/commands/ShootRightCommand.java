package game.commands;

import game.engine.GameWindow;
import game.enums.Direction;

public class ShootRightCommand implements ICommand{

	@Override
	public boolean execute(GameWindow gameWindow) {
		gameWindow.shoot(Direction.RIGHT);
		return true;
	}
}
