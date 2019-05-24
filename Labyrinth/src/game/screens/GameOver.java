package game.screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import game.utils.DrawSpecials;

/**
 * Display Game Over Window on the screen
 * 
 * @author Martin Holecek
 *
 */
public class GameOver extends DrawSpecials {
	/**
	 * Render Game Over Window on the screen
	 * 
	 * @param g      - instance of the object
	 * @param width  - width of the game window
	 * @param height - height of the game window
	 */
	public void render(Graphics g, int width, int height) {
		drawTransparentBackground(g, width, height, 127);
		Font font = new Font("arial", Font.BOLD, 28);
		g.setFont(font);
		g.setColor(Color.WHITE);
		drawCenteredString("Game Over", width, height, g);
	}
}
