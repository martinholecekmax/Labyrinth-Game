package game.commands;

import game.engine.GameWindow;

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
