package game.commands;

import game.engine.GameWindow;

/**
 * Open Door Command
 * 
 * This command will open the door.
 * 
 * @author Martin Holecek
 *
 */
public class OpenDoorCommand implements ICommand{

	private String answer;
	
	public OpenDoorCommand(String stringValue) {
		this.answer = stringValue;
	}

	@Override
	public boolean execute(GameWindow gameWindow) {
		gameWindow.openDoor(answer);
		return true;
	}
}
