package game.commands;

import game.engine.GameWindow;
import game.enums.Direction;

public class ShootLeftCommand implements ICommand{

	@Override
	public boolean execute(GameWindow gameWindow) {
		gameWindow.shoot(Direction.LEFT);
		return true;
	}
}
