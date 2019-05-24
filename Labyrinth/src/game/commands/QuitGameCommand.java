package game.commands;

import game.engine.GameWindow;

/**
 * Quit Command
 * 
 * This command terminates the application.
 * 
 * @author Martin Holecek
 *
 */
public class QuitGameCommand implements ICommand {

	@Override
	public boolean execute(GameWindow gameWindow) {
		System.exit(0);
		return true;
	}
}
