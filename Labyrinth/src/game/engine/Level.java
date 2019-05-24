package game.engine;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import game.entities.Enemy;
import game.entities.Player;
import game.enums.GameObjectType;
import game.sprites.Textures;
import game.tiles.Door;
import game.tiles.Tile;

public class Level {
	private Textures textures;
	private boolean isPlayerCreated = false;
	private boolean isGoalCreated = false;

	private int tileSize = 32;

	private ArrayList<Tile> tiles;
	private int rowCount;
	private int colCount;
	private QuestionPool questionPool;

	public Level(Textures textures, String questionPath) {
		this.textures = textures;
		tiles = new ArrayList<Tile>();
	}

	public ArrayList<String> loadLevel(File file) {

		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			ArrayList<String> allLines = new ArrayList<String>();
			String line = reader.readLine();
			while (line != null) {
				allLines.add(line);
				line = reader.readLine();
			}
			reader.close();
			return allLines;
		} catch (IOException e) {
			System.out.println("Error reading the file!");
			System.exit(1);
			return null;
		}
	}

	public void createQuestionPool(String path) {
		questionPool = new QuestionPool(path);
	}

	public void setLevel(GameWindow gameWindow, ArrayList<String> lines) {
		rowCount = lines.size();
		String[] tokens = lines.get(0).split(",");
		colCount = tokens.length;

		int type = 0;
		for (int row = 0; row < lines.size(); row++) {
			tokens = lines.get(row).split(",");
			if (tokens.length != colCount)
				throw new ArrayIndexOutOfBoundsException("Map must have all rows same size!");
			for (int col = 0; col < colCount; col++) {
				type = Integer.parseInt(tokens[col].trim());
				tiles.add(new Tile(row, col, tileSize, textures.background, GameObjectType.BACKGROUND, false));
				if (type == GameObjectType.PLAYER.getNumVal() && !isPlayerCreated) {
					gameWindow.player = new Player(row * tileSize, col * tileSize, textures.player, this);
					isPlayerCreated = true;
				} else if (type == GameObjectType.GOAL.getNumVal() & !isGoalCreated) {
					tiles.add(new Tile(row, col, tileSize, textures.goal, GameObjectType.GOAL, false));
					isGoalCreated = true;
				} else if (type == GameObjectType.WALL.getNumVal()) {
					tiles.add(new Tile(row, col, tileSize, textures.wall, GameObjectType.WALL, true));
				} else if (type == GameObjectType.ENEMY.getNumVal()) {
					gameWindow.enemiesController
							.addEnemy(new Enemy(row * tileSize, col * tileSize, textures.enemy, this));
				} else if (type == GameObjectType.DOOR.getNumVal()) {
					tiles.add(new Door(row, col, tileSize, textures.doorOpened, textures.doorClosed,
							GameObjectType.DOOR, false, questionPool.getRandomQuestion()));
				}
			}
		}
		if (!isPlayerCreated || !isGoalCreated) {
			gameWindow.setMessage("Invalid Map! It must include the player (1) and a goal token (3)!");
		}
	}

	public Rectangle getBounds() {
		return new Rectangle(0, 0, getColSize(), getRowSize());
	}

	public boolean contains(Rectangle rectangle) {
		return getBounds().contains(rectangle);
	}

	public int getRowCount() {
		return rowCount;
	}

	public int getColCount() {
		return colCount;
	}

	public int getRowSize() {
		return getRowCount() * tileSize;
	}

	public int getColSize() {
		return getColCount() * tileSize;
	}

	public int getTileSize() {
		return tileSize;
	}

	public void clearLevel() {
		isGoalCreated = false;
		isPlayerCreated = false;
		tiles.clear();
	}

	public Tile getTile(Rectangle bounds, GameObjectType tileType) {
		Tile tile;
		for (int index = 0; index < tiles.size(); index++) {
			tile = tiles.get(index);
			if (tile.getBounds().intersects(bounds) && tile.getTileType() == tileType)
				return tile;
		}
		return null;
	}

	public boolean intersects(Rectangle bounds, GameObjectType tileType) {
		Tile tile;
		for (int index = 0; index < tiles.size(); index++) {
			tile = tiles.get(index);
			if (tile.getBounds().intersects(bounds) && tile.getTileType() == tileType)
				return true;
		}
		return false;
	}

	public boolean intersectsSolid(Rectangle bounds) {
		Tile tile;
		for (int index = 0; index < tiles.size(); index++) {
			tile = tiles.get(index);
			if (tile.getBounds().intersects(bounds) && tile.isSolid())
				return true;
		}
		return false;
	}

	public void render(Graphics g) {
		Tile tile;
		for (int index = 0; index < tiles.size(); index++) {
			tile = tiles.get(index);
			tile.render(g);
		}
	}
}
