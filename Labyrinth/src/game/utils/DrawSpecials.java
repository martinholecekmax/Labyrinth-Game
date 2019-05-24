package game.utils;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.ArrayList;

/**
 * This class handles special drawing functions
 * 
 * @author Martin Holecek
 *
 */
public class DrawSpecials {
	/**
	 * Draw Transparent Background
	 * 
	 * @param g - Graphics 2D variable pasted from paintComponent method
	 * @param width - Width of the screen
	 * @param height - Height of the screen
	 * @param alpha - Integer between 0 - 255 (alpha = 127 is 50% transparent)
	 */
	public static void drawTransparentBackground(Graphics g, int width, int height, int alpha) {
		Color myColour = new Color(0, 0, 0, alpha);
		g.setColor(myColour);
		g.fillRect(0, 0, width, height);
	}

	/**
	 * This method centres a String
	 * 
	 * @param input - String which needs to be centred
	 * @param width - width value of the position on the screen where the string will be
	 *          positioned
	 * @param height - height value of the position on the screen where the string will be
	 *          positioned
	 * @param g - Graphics 2D variable pasted from paintComponent method
	 */
	public static void drawCenteredString(String input, int width, int height, Graphics g) {
		FontMetrics fm = g.getFontMetrics();
		int x = (width - fm.stringWidth(input)) / 2;
		int y = (fm.getAscent() + (height - (fm.getAscent() + fm.getDescent())) / 2);
		g.drawString(input, x, y);
	}
	
	/**
	 * Print Multiple lines of text on the screen
	 * 
	 * @param input - String text
	 * @param lineWidth - Width of the line
	 * @param xPosition - X position on the screen
	 * @param yPosition - Y position on the screen
	 * @param g - Graphics 2D variable pasted from paintComponent method
	 */
	public static void drawMultilineString(String input, int lineWidth, int xPosition, int yPosition, Graphics g)
	{
		ArrayList<String> lines = new ArrayList<String>();
		if (input.length() > lineWidth) {
			String[] split = input.split(" ");
			String words = "";
			for (int i = 0; i < split.length; i++) {
				if ((words + split[i]).length() < lineWidth) {
					words += " " + split[i];
				} else {
					lines.add(words.trim());
					words = "";
					words += " " + split[i];
				}
			}
			if (!words.isEmpty()) {
				lines.add(words);
			}
			for (String line : lines) {
				drawCenteredString(line, xPosition, yPosition, g);
				yPosition += 50;
			}
		} else {
			drawCenteredString(input, xPosition, yPosition, g);
		}
	}
}
