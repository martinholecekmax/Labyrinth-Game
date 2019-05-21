package game.screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import game.utils.DrawSpecials;

public class GameOver extends DrawSpecials {
	public void render(Graphics g, int width, int height) {
		drawTransparentBackground(g, width, height, 127);
		Font font = new Font("arial", Font.BOLD, 28);
		g.setFont(font);
		g.setColor(Color.WHITE);
		drawCenteredString("Game Over", width, height, g);
	}
}
