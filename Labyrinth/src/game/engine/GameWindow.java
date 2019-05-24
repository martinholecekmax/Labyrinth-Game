package game.engine;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Iterator;
import java.util.LinkedList;

import game.commands.ICommand;
import game.commands.MoveCommand;
import game.commands.OpenDoorCommand;
import game.commands.ShootCommand;
import game.entities.EnemiesController;
import game.entities.IEntityEnemy;
import game.entities.Player;
import game.enums.Direction;
import game.enums.GameObjectType;
import game.enums.GameState;
import game.gun.BulletController;
import game.gun.BulletShoot;
import game.screens.DialogBoxMessage;
import game.screens.GameFinish;
import game.screens.GameOver;
import game.sprites.BufferedImageLoader;
import game.sprites.Textures;
import game.tiles.Door;

/**
 * This class implements the game loop and renders the objects on the screen.
 * 
 * @author Martin Holecek
 *
 */
public class GameWindow extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;
	private static final int FPS = 60;

	private long processCommandsTimer = System.currentTimeMillis();
	private boolean running = false;
	private boolean keyInputMovable;
	private boolean freeze = false;
	private boolean dialog = false;
	private Thread thread;
	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	private BufferedImage spriteSheet = null;
	private BulletController bulletController;
	private Level level;
	private Textures textures;
	private GameEngine gameEngine;
	private GameState gameState = GameState.GAME;
	private GameOver gameOver;
	private GameFinish gameFinish;
	private DialogBoxMessage questionMessage;

	protected Player player;
	protected EnemiesController enemiesController;
	private LinkedList<ICommand> commands;

	/**
	 * Get the player in order to control things such as movement.
	 * 
	 * @return object of the player class
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * This setter will enable or disable the keyboard input.
	 * 
	 * @param keyInputMovable - true will enabled keyboard, and false will disable
	 *                        it.
	 */
	public void setKeyInputMovable(boolean keyInputMovable) {
		this.keyInputMovable = keyInputMovable;
	}

	/**
	 * Constructor of the Game Window Class
	 * 
	 * @param gameEngine    - instance of the Game Engine object class
	 * @param mapFile       - File where is the map located
	 * @param questionsPath - The path of the file where are the questions stored
	 */
	public GameWindow(GameEngine gameEngine, File mapFile, String questionsPath) {
		this.gameEngine = gameEngine;
		loadTileSet();

		addKeyListener(new KeyInput(this));
		commands = new LinkedList<ICommand>();
		textures = new Textures(spriteSheet);
		enemiesController = new EnemiesController();
		level = new Level(textures, questionsPath);
		bulletController = new BulletController(level);
		gameOver = new GameOver();
		gameFinish = new GameFinish();
		keyInputMovable = false;
		initGame(mapFile, questionsPath);
	}

	/**
	 * This method will initiate the game, like creation of the level and setting
	 * the size of the window.
	 * 
	 * @param mapFile       - File where is the map located
	 * @param questionsPath - The path of the file where are the questions stored
	 */
	public void initGame(File mapFile, String questionsPath) {
		level.createQuestionPool(questionsPath);
		level.setLevel(this, level.loadLevel(mapFile));
		setPreferredSize(new Dimension(level.getColSize(), level.getRowSize()));
		setMinimumSize(new Dimension(level.getColSize(), level.getRowSize()));
		setMaximumSize(new Dimension(level.getColSize(), level.getRowSize()));
	}

	/**
	 * This method will restart the game.
	 * 
	 * @param mapFile       - File where is the map located
	 * @param questionsPath - The path of the file where are the questions stored
	 */
	public void restartGame(File mapFile, String questionsPath) {
		enemiesController.clear();
		bulletController.getBulletsList().clear();
		level.clearLevel();
		commands.clear();
		level.createQuestionPool(questionsPath);
		level.setLevel(this, level.loadLevel(mapFile));
		dialog = false;
		freeze = false;
		gameState = GameState.GAME;
	}

	/**
	 * Loading the tile set from the file.
	 */
	private void loadTileSet() {
		BufferedImageLoader loader = new BufferedImageLoader();
		try {
			spriteSheet = loader.loadImage("/tileset.png");
		} catch (Exception e) {
			stop();
		}
	}

	/**
	 * Start the game loop.
	 */
	public synchronized void start() {
		if (!running) {
			running = true;
			thread = new Thread(this);
			thread.start();
		}
	}

	/**
	 * End the game loop.
	 */
	private synchronized void stop() {
		if (running) {
			running = false;
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.exit(1);
		}
	}

	/**
	 * Game loop
	 */
	public void run() {
		long initialTime = System.nanoTime();
		final double timerFrames = 1000000000 / FPS;
		double delta = 0;
		while (running) {
			long currentTime = System.nanoTime();
			delta += (currentTime - initialTime) / timerFrames;
			initialTime = currentTime;
			if (delta >= 1) {
				tick();
				render();
				delta--;
			}
		}
	}

	/**
	 * This method will update position of the player and enemies, handle collisions
	 * and process commands.
	 */
	private void tick() {
		if (gameState == GameState.GAME && !freeze) {
			player.tick();
			bulletController.tick();
			enemiesController.tick();
			collision();
		} else if (gameState == GameState.OVER) {

		}
		processCommand();
	}

	/**
	 * Processing of the commands.
	 */
	private void processCommand() {
		if (!commands.isEmpty() && System.currentTimeMillis() - processCommandsTimer > 300) {
			ICommand command = commands.poll();
			if (freeze && !(command instanceof OpenDoorCommand)) {
				setMessage("You shall not pass! You must answer the question first!");
				return;
			}
			if (command != null)
				setMessage("");
			if (!command.execute(this))
				setMessage("Can't move there bro!");
			processCommandsTimer = System.currentTimeMillis();
		}
	}

	/**
	 * Manage collisions between player, enemies, bullets and other objects.
	 */
	private void collision() {
		Iterator<IEntityEnemy> iter = enemiesController.iterator();

		while (iter.hasNext()) {
			IEntityEnemy enemy = iter.next();

			if (Physics.Collision(player, enemy)) {
				gameState = GameState.OVER;
				bulletController.getBulletsList().clear();
				commands.clear();
			}
			if (bulletController.collision(enemy)) {
				enemiesController.increaseKilled();
				iter.remove();
			}
		}

		if (level.intersects(player.getBounds(), GameObjectType.GOAL)) {
			gameState = GameState.FINISH;
		}

		if (level.intersects(player.getBounds(), GameObjectType.DOOR)) {
			Door door = (Door) level.getTile(player.getBounds(), GameObjectType.DOOR);
			if (door != null && door.isOpened() == false) {
				questionMessage = new DialogBoxMessage(door.getQuesetion());
				player.setMovable(false);
				freeze = true;
				dialog = true;
			}
		}
	}

	/**
	 * Render objects on the screen.
	 */
	private void render() {
		BufferStrategy bufferStrategy = this.getBufferStrategy();

		if (bufferStrategy == null) {
			createBufferStrategy(3);
			return;
		}

		Graphics g = bufferStrategy.getDrawGraphics();
		g.drawImage(image, 0, 0, getWidth(), getHeight(), this);

		level.render(g);
		player.render(g);
		bulletController.render(g);
		enemiesController.render(g);
		if (dialog) {
			questionMessage.render(g, getWidth(), getHeight());
		}

		if (gameState == GameState.OVER) {
			gameOver.render(g, getWidth(), getHeight());
		} else if (gameState == GameState.FINISH) {
			int score = 10000 - gameEngine.getNumLines();
			score = score * enemiesController.getEnemyKilled();
			gameFinish.render(g, getWidth(), getHeight(), score);
		}

		g.dispose();
		bufferStrategy.show();
	}

	/**
	 * Add new command to the list of commands which will be processed by
	 * processCommands method.
	 * 
	 * @param command - instance of the command interface
	 */
	public void addCommand(ICommand command) {
		commands.add(command);
	}

	/**
	 * Handle keyboard control.
	 * 
	 * @param e - key event
	 */
	public void keyPressed(KeyEvent e) {
		if (gameState == GameState.GAME && keyInputMovable) {
			int key = e.getKeyCode();
			if (key == KeyEvent.VK_RIGHT) {
				commands.add(new MoveCommand(Direction.RIGHT));
			} else if (key == KeyEvent.VK_LEFT) {
				commands.add(new MoveCommand(Direction.LEFT));
			} else if (key == KeyEvent.VK_UP) {
				commands.add(new MoveCommand(Direction.UP));
			} else if (key == KeyEvent.VK_DOWN) {
				commands.add(new MoveCommand(Direction.DOWN));
			} else if (key == KeyEvent.VK_A) {
				commands.add(new ShootCommand(Direction.LEFT));
			} else if (key == KeyEvent.VK_D) {
				commands.add(new ShootCommand(Direction.RIGHT));
			} else if (key == KeyEvent.VK_W) {
				commands.add(new ShootCommand(Direction.UP));
			} else if (key == KeyEvent.VK_S) {
				commands.add(new ShootCommand(Direction.DOWN));
			}
		}
	}

	/**
	 * Add bullet to the bullet controller.
	 * 
	 * @param direction - direction of the bullet
	 */
	public void shoot(Direction direction) {
		bulletController.addBullet(new BulletShoot(player, textures, direction, level));
	}

	/**
	 * Set the message inside the text area.
	 * 
	 * @param message
	 */
	public synchronized void setMessage(String message) {
		gameEngine.setMessage(message);
	}

	/**
	 * This method will freeze all enemies for several seconds.
	 */
	public void freezeEnemies() {
		enemiesController.setFreeze(true);
	}

	/**
	 * This method will open the door if the player is standing on it.
	 * 
	 * @param answer
	 */
	public void openDoor(String answer) {
		Door door = (Door) level.getTile(player.getBounds(), GameObjectType.DOOR);
		if (door != null) {
			if (door.checkAnswer(answer)) {
				door.setOpened(true);
				player.setMovable(true);
				freeze = false;
				dialog = false;
			} else {
				setMessage("Wrong Answer! Try Again!");
			}
		}
	}
}
