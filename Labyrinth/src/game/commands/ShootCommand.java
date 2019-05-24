package game.commands;

import game.engine.GameWindow;
import game.enums.Direction;

/**
 * Shoot Command
 * 
 * This command allows the player to shoot enemies.
 * 
 * @author Martin Holecek
 *
 */
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
