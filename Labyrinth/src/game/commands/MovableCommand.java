package game.commands;

import game.engine.GameWindow;

/**
 * Movable Command
 * 
 * This command prevents the player from moving.
 * 
 * @author Martin Holecek
 *
 */
public class MovableCommand implements ICommand{

	boolean movable;
	
	public MovableCommand(boolean movable) {
		this.movable = movable;
	}
	
	@Override
	public boolean execute(GameWindow gameWindow) {
		gameWindow.getPlayer().setMovable(movable);
		return true;
	}

}
