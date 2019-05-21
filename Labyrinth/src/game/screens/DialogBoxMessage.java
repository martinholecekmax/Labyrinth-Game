package game.screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import game.engine.Question;
import game.utils.DrawSpecials;

public class DialogBoxMessage extends DrawSpecials {

	private Question question;

	public DialogBoxMessage(Question question) {
		this.question = question;
	}

	public void render(Graphics g, int width, int height) {
		g.setColor(new Color(13, 128, 242));
		g.fillRoundRect(50, 50, width - 100, height - 100, 15, 15);
		g.setColor(new Color(0, 102, 204));
		g.drawRoundRect(50, 50, width - 100, height - 100, 15, 15);
		g.setColor(Color.BLACK);
		Font font = new Font("arial", Font.BOLD, 16);
		g.setFont(font);
		drawMultilineString(question.getQuestion(), 25, width, height / 2, g);
	}
}
