package game.commands;

import game.engine.GameWindow;
import game.enums.ShootDirection;

public class ShootUpCommand implements ICommand{

	@Override
	public void execute(GameWindow gameWindow) {
		gameWindow.shoot(ShootDirection.UP);
	}

}
