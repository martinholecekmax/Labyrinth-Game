package game.commands;

import game.engine.GameWindow;
import game.enums.Direction;

public class MoveCommand implements ICommand{

	Direction direction;
	public MoveCommand(Direction direction) {
		this.direction = direction;
	}
	
	@Override
	public boolean execute(GameWindow gameWindow) {
		switch (direction) {
		case UP:
			return gameWindow.getPlayer().moveUp(32);
		case DOWN:
			return gameWindow.getPlayer().moveDown(32);
		case LEFT:
			return gameWindow.getPlayer().moveLeft(32);
		case RIGHT:
			return gameWindow.getPlayer().moveRight(32);
		default:
			return false;
		}
	}
}
