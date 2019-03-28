package game.commands;

import game.engine.GameWindow;
import game.enums.Direction;

public class ShootCommand implements ICommand{

	Direction direction;
	
	public ShootCommand(Direction direction) {
		this.direction = direction;
	}

	@Override
	public boolean execute(GameWindow gameWindow) {
		gameWindow.shoot(direction);
		return true;
	}
}
