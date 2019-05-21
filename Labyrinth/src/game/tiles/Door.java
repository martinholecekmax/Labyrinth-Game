package game.tiles;

import java.awt.image.BufferedImage;

import game.engine.Question;
import game.enums.GameObjectType;

public class Door extends Tile{
	
	private boolean opened = false;
	private Question quesetion;
	
	public Door(int row, int col, int tileSize, BufferedImage texture, GameObjectType tileType, boolean solid, Question question) {
		super(row, col, tileSize, texture, tileType, solid);
		this.quesetion = question;
	}

	public Question getQuesetion() {
		return quesetion;
	}

	public boolean isOpened() {
		return opened;
	}

	public void setOpened(boolean opened) {
		this.opened = opened;
	}
	
	public boolean checkAnswer(String answer) {
		if (quesetion.checkAnswer(answer)) {
			return true;
		}
		return false;
	}
}
