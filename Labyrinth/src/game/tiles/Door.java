package game.tiles;

import java.awt.image.BufferedImage;

import game.engine.Question;
import game.enums.GameObjectType;

public class Door extends Tile{
	
	private boolean opened = false;
	private Question quesetion;
	private BufferedImage openedDoorTexture;
	private BufferedImage closedDoorTexture;
	
	public Door(int row, int col, int tileSize, BufferedImage openedDoorTexture, BufferedImage closedDoorTexture, GameObjectType tileType, boolean solid, Question question) {
		super(row, col, tileSize, closedDoorTexture, tileType, solid);
		this.quesetion = question;
		this.openedDoorTexture = openedDoorTexture;
		this.closedDoorTexture = closedDoorTexture;
	}

	public Question getQuesetion() {
		return quesetion;
	}

	public boolean isOpened() {
		return opened;
	}

	public void setOpened(boolean opened) {
		this.opened = opened;
		if (opened) {
			setTexture(openedDoorTexture);
		} else {
			setTexture(closedDoorTexture);
		}
	}
	
	public boolean checkAnswer(String answer) {
		if (quesetion.checkAnswer(answer)) {
			return true;
		}
		return false;
	}
}
