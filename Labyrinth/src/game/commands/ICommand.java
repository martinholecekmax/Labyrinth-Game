package game.commands;

import game.engine.GameWindow;

public interface ICommand {
	public boolean execute(GameWindow gameWindow);
}
