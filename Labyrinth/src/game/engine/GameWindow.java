package game.engine;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.Queue;

import game.commands.ICommand;
import game.commands.MoveDownCommand;
import game.commands.MoveLeftCommand;
import game.commands.MoveRightCommand;
import game.commands.MoveUpCommand;
import game.commands.ShootDownCommand;
import game.commands.ShootLeftCommand;
import game.commands.ShootRightCommand;
import game.commands.ShootUpCommand;
import game.entities.EnemiesController;
import game.entities.Enemy;
import game.entities.IEntityEnemy;
import game.entities.Player;
import game.enums.GameState;
import game.enums.ShootDirection;
import game.enums.TileType;
import game.gun.BulletController;
import game.gun.BulletShoot;
import game.gun.IEntityBullets;
import game.screens.GameFinish;
import game.screens.GameOver;
import game.sprites.Background;
import game.sprites.BufferedImageLoader;
import game.sprites.Textures;

public class GameWindow extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;
	private boolean running = false;
	private Thread thread;
	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	private BufferedImage spriteSheet = null;
	private Background background;
	private BulletController bulletController;
	private Level level;
	private Textures textures;
	private EnemiesController enemiesController;
	private GameState gameState = GameState.GAME;
	private GameOver gameOver;
	private GameFinish gameFinish;
	private boolean movable;
	private Queue<ICommand> commands;
	
	public Player player;

	public GameWindow() {
		loadTileSet();
		commands = new LinkedList<ICommand>();

		setPreferredSize(new Dimension(320, 320));
		setMinimumSize(new Dimension(320, 320));
		setMaximumSize(new Dimension(320, 320));

		addKeyListener(new KeyInput(this));

		textures = new Textures(spriteSheet);

		level = new Level(textures);
		player = new Player(0, 0, textures, level);
		bulletController = new BulletController(level);
		enemiesController = new EnemiesController();
		enemiesController.addEnemy(new Enemy(6 * 32, 8 * 32, textures, level));
		enemiesController.addEnemy(new Enemy(4 * 32, 8 * 32, textures, level));
		background = new Background(textures, level);
		gameOver = new GameOver();
		gameFinish = new GameFinish();
		movable = true;
	}

	private void loadTileSet() {
		BufferedImageLoader loader = new BufferedImageLoader();
		try {
			spriteSheet = loader.loadImage("/tileset.png");
		} catch (Exception e) {
			stop();
		}
	}

	public synchronized void start() {
		if (!running) {
			running = true;
			thread = new Thread(this);
			thread.start();
		}
	}

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

	public void run() {
		while (running) {
			tick();
			render();
			try {
				Thread.sleep(20);
			} catch (Exception e) {
			}
		}
		stop();
	}

	private void tick() {
		if (gameState == GameState.GAME) {
			player.tick();
			bulletController.tick();
			enemiesController.tick();
			collision();
			processCommand();
		} else if (gameState == GameState.OVER) {

		}
	}

	private void processCommand() {
		if (!commands.isEmpty()) {
			ICommand command = commands.poll();
			if (command != null)
				command.execute(this);
		}
	}

	private void collision() {
		LinkedList<IEntityEnemy> enemies = enemiesController.getAllEnemies();
		for (int index = 0; index < enemies.size(); index++) {
			if (bulletController.collision(enemies.get(index))) {
				enemiesController.removeEnemy(enemies.get(index));
			}
		}
		if (Physics.Collision(player, enemiesController.getAllEnemies())) {
			gameState = GameState.OVER;
		}
		if (level.intersects(player.getBounds(), TileType.GOAL)) {
			gameState = GameState.FINISH;
		}
	}

	private void render() {
		BufferStrategy bufferStrategy = this.getBufferStrategy();

		if (bufferStrategy == null) {
			createBufferStrategy(3);
			return;
		}

		Graphics g = bufferStrategy.getDrawGraphics();

		/////// Draw on the screen ////////////

		g.drawImage(image, 0, 0, getWidth(), getHeight(), this);

		background.render(g);
		level.render(g);
		player.render(g);
		bulletController.render(g);
		enemiesController.render(g);

		if (gameState == GameState.OVER) {
			gameOver.render(g, getWidth(), getHeight());
		} else if (gameState == GameState.FINISH) {
			gameFinish.render(g, getWidth(), getHeight(), enemiesController.getEnemyKilled());
		}

		//////////////////////////////////////

		g.dispose();
		bufferStrategy.show();
	}

	public void addCommand(ICommand command) {
		commands.add(command);
	}

	public void keyPressed(KeyEvent e) {
		if (gameState == GameState.GAME && movable) {
			int key = e.getKeyCode();
			if (key == KeyEvent.VK_RIGHT) {
				commands.add(new MoveRightCommand());
			} else if (key == KeyEvent.VK_LEFT) {
				commands.add(new MoveLeftCommand());
			} else if (key == KeyEvent.VK_UP) {
				commands.add(new MoveUpCommand());
			} else if (key == KeyEvent.VK_DOWN) {
				commands.add(new MoveDownCommand());
			} else if (key == KeyEvent.VK_A) {
				commands.add(new ShootLeftCommand());
			} else if (key == KeyEvent.VK_D) {
				commands.add(new ShootRightCommand());
			} else if (key == KeyEvent.VK_W) {
				commands.add(new ShootUpCommand());
			} else if (key == KeyEvent.VK_S) {
				commands.add(new ShootDownCommand());
			}
		}
	}

	public void shoot(ShootDirection direction) {
		IEntityBullets bullet = new BulletShoot(player.getRow(), player.getCol(), textures, direction);
		bulletController.addBullet(bullet);
	}
}
