package game.commands;

import game.engine.GameWindow;

/**
 * Write Command
 * 
 * This command writes text to the console and message textbox.
 * 
 * @author Martin Holecek
 *
 */
public class WriteCommand implements ICommand{

	String message;
	public WriteCommand(String message) {
		this.message = message;
	}
	
	@Override
	public boolean execute(GameWindow gameWindow) {
		gameWindow.setMessage(message);
		return true;
	}

}
