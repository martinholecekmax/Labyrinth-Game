package game.commands;

import game.engine.GameWindow;

/**
 * Freeze Command
 * 
 * This command will freeze all enemies for several seconds.
 * 
 * @author Martin Holecek
 *
 */
public class FreezeCommand implements ICommand{

	@Override
	public boolean execute(GameWindow gameWindow) {
		gameWindow.freezeEnemies();
		return true;
	}

}
