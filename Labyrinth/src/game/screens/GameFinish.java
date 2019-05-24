package game.screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import game.utils.DrawSpecials;

/**
 * Display Game Finish Window on the screen
 * 
 * @author Martin Holecek
 *
 */
public class GameFinish extends DrawSpecials {
	/**
	 * Render Game Finish Window on the screen
	 * 
	 * @param g      - instance of the object
	 * @param width  - width of the game window
	 * @param height - height of the game window
	 * @param score  - score of the game
	 */
	public void render(Graphics g, int width, int height, int score) {
		drawTransparentBackground(g, width, height, 127);
		Font font = new Font("arial", Font.BOLD, 28);
		g.setFont(font);
		g.setColor(Color.WHITE);
		drawCenteredString("You Won the game!", width, height, g);
		drawCenteredString("Score: " + score, width, height + 80, g);
	}
}
