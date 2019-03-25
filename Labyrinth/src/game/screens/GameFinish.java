package game.screens;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class GameFinish extends DrawSpecials{
	public void render(Graphics g, int width, int height, int score) {

		drawTransparentBackground(g, width, height);

		Font font = new Font("arial", Font.BOLD, 28);
		g.setFont(font);
		g.setColor(Color.WHITE);
		drawCenteredString("You Won the game!", width, height, g);
		drawCenteredString("Score: " + score, width, height + 80, g);
	}
}
