package game.commands;

import game.engine.GameWindow;

/**
 * Keyboard Command
 * 
 * This command will either enable or disable keyboard.
 * 
 * @author Martin Holecek
 *
 */
public class KeyboardInputCommand implements ICommand{

	private boolean keyInputMovable;
	
	public KeyboardInputCommand(boolean keyInputMovable) {
		this.keyInputMovable = keyInputMovable;
	}

	@Override
	public boolean execute(GameWindow gameWindow) {
		gameWindow.setKeyInputMovable(keyInputMovable);
		return true;
	}

}
