package game.commands;

import game.engine.GameWindow;

/**
 * This interface is used for generating of the commands.
 * 
 * @author Martin Holecek
 *
 */
public interface ICommand {
	public boolean execute(GameWindow gameWindow);
}
