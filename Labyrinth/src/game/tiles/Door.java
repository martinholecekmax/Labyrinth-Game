package game.tiles;

import java.awt.image.BufferedImage;

import game.engine.Question;
import game.enums.GameObjectType;

/**
 * Door class object
 * 
 * @author Martin Holecek
 *
 */
public class Door extends Tile {

	private boolean opened = false;
	private Question quesetion;
	private BufferedImage openedDoorTexture;
	private BufferedImage closedDoorTexture;

	/**
	 * Constructor
	 * 
	 * @param row               - row position of the door
	 * @param col               - column position of the door
	 * @param tileSize          - size of the texture
	 * @param openedDoorTexture - texture of the door that are opened
	 * @param closedDoorTexture - texture of the door that are closed
	 * @param tileType          - type of the texture (door)
	 * @param solid             - determines if the player and enemy can go through
	 * @param question          - instance of the question class
	 */
	public Door(int row, int col, int tileSize, BufferedImage openedDoorTexture, BufferedImage closedDoorTexture,
			GameObjectType tileType, boolean solid, Question question) {
		super(row, col, tileSize, closedDoorTexture, tileType, solid);
		this.quesetion = question;
		this.openedDoorTexture = openedDoorTexture;
		this.closedDoorTexture = closedDoorTexture;
	}

	/**
	 * Get the question that has been assign to the door
	 * 
	 * @return instance of the question object
	 */
	public Question getQuesetion() {
		return quesetion;
	}

	/**
	 * Check if the door is opened
	 * 
	 * @return - true if the door is opened, otherwise, return false.
	 */
	public boolean isOpened() {
		return opened;
	}

	/**
	 * Change state of the door
	 * 
	 * @param opened - true will open the door, false will close them
	 */
	public void setOpened(boolean opened) {
		this.opened = opened;
		if (opened) {
			setTexture(openedDoorTexture);
		} else {
			setTexture(closedDoorTexture);
		}
	}

	/**
	 * Check if a user answers to the question correctly
	 * 
	 * @param answer - string value of the user's answer
	 * @return - true if answer is correct, and false if answer is wrong
	 */
	public boolean checkAnswer(String answer) {
		if (quesetion.checkAnswer(answer)) {
			return true;
		}
		return false;
	}
}
