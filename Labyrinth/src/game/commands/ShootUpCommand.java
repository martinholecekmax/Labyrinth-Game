package game.commands;

import game.engine.GameWindow;
import game.enums.Direction;

public class ShootUpCommand implements ICommand{

	@Override
	public boolean execute(GameWindow gameWindow) {
		gameWindow.shoot(Direction.UP);
		return true;
	}

}
