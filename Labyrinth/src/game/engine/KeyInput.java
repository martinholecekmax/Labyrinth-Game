package game.engine;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * This class handles keyboard input.
 * 
 * @author Martin Holecek
 *
 */
public class KeyInput extends KeyAdapter{

	GameWindow gameWindow;
	
	public KeyInput(GameWindow gameWindow) {
		this.gameWindow = gameWindow;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		gameWindow.keyPressed(e);
	}
}
