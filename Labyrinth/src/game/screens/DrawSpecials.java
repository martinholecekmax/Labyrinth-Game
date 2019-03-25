package game.screens;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;

public class DrawSpecials {
	public void drawTransparentBackground(Graphics g, int width, int height) {
		int alpha = 127; // 50% transparent
		Color myColour = new Color(0, 0, 0, alpha);
		g.setColor(myColour);
		g.fillRect(0, 0, width, height);
	}

	/**
	 * This method centers a String
	 * 
	 * @param s String which needs to be centered
	 * @param w width value of the position on the screen where the string will be
	 *          positioned
	 * @param h height value of the position on the screen where the string will be
	 *          positioned
	 * @param g Graphics 2D variable pasted from paintComponent method
	 */
	public void drawCenteredString(String s, int w, int h, Graphics g) {
		FontMetrics fm = g.getFontMetrics();
		int x = (w - fm.stringWidth(s)) / 2;
		int y = (fm.getAscent() + (h - (fm.getAscent() + fm.getDescent())) / 2);
		g.drawString(s, x, y);
	}
}
