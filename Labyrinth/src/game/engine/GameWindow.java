package game.engine;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.Iterator;
import java.util.LinkedList;

import game.commands.ICommand;
import game.commands.MoveCommand;
import game.commands.ShootCommand;
import game.entities.EnemiesController;
import game.entities.IEntityEnemy;
import game.entities.Player;
import game.enums.Direction;
import game.enums.GameObjectType;
import game.enums.GameState;
import game.gun.BulletController;
import game.gun.BulletShoot;
import game.screens.GameFinish;
import game.screens.GameOver;
import game.sprites.BufferedImageLoader;
import game.sprites.Textures;

public class GameWindow extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;
	private static final int UPS = 60;
	private static final int FPS = 60;
	private static final boolean RENDER_TIME = false;

	private boolean running = false;
	private Thread thread;
	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	private BufferedImage spriteSheet = null;
	private BulletController bulletController;
	private Level level;
	private Textures textures;
	private GameState gameState = GameState.GAME;
	private GameOver gameOver;
	private GameFinish gameFinish;
	private boolean keyInputMovable;
	private LinkedList<ICommand> commands;

	protected Player player;
	protected EnemiesController enemiesController;

	private GameEngine gameEngine;

	public Player getPlayer() {
		return player;
	}
	
	public void setKeyInputMovable(boolean keyInputMovable) {
		this.keyInputMovable = keyInputMovable;
	}

	public GameWindow(GameEngine gameEngine, String mapFilePath) {
		this.gameEngine = gameEngine;
		loadTileSet();

		addKeyListener(new KeyInput(this));
		commands = new LinkedList<ICommand>();
		textures = new Textures(spriteSheet);
		enemiesController = new EnemiesController();
		level = new Level(textures);
		bulletController = new BulletController(level);
		gameOver = new GameOver();
		gameFinish = new GameFinish();
		keyInputMovable = true;
		initGame(mapFilePath);
	}

	public void initGame(String mapFilePath) {
		level.setLevel(this, level.loadLevel(mapFilePath));
		setPreferredSize(new Dimension(level.getColSize(), level.getRowSize()));
		setMinimumSize(new Dimension(level.getColSize(), level.getRowSize()));
		setMaximumSize(new Dimension(level.getColSize(), level.getRowSize()));
	}

	public void restartGame() {
		level.clearLevel();
		commands.clear();
		enemiesController.clear();
		bulletController.getBulletsList().clear();
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

	@Override
	public void run() {

		long initialTime = System.nanoTime();
		final double timeU = 1000000000 / UPS;
		final double timeF = 1000000000 / FPS;
		double deltaU = 0, deltaF = 0;
		int frames = 0, ticks = 0;
		long timer = System.currentTimeMillis();

		while (running) {

			long currentTime = System.nanoTime();
			deltaU += (currentTime - initialTime) / timeU;
			deltaF += (currentTime - initialTime) / timeF;
			initialTime = currentTime;

			if (deltaU >= 1) {
				tick();
				ticks++;
				deltaU--;
			}

			if (deltaF >= 1) {
				render();
				frames++;
				deltaF--;
			}

			if (System.currentTimeMillis() - timer > 1000) {
				if (RENDER_TIME) {
					System.out.println(String.format("UPS: %s, FPS: %s", ticks, frames));
				}
				frames = 0;
				ticks = 0;
				timer += 1000;
			}
		}
	}

//	public void run() {
//		while (running) {
//			tick();
//			render();
//			try {
//				Thread.sleep(20);
//			} catch (Exception e) {
//			}
//		}
//		stop();
//	}

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
				setMessage("");
			if (!command.execute(this))
				setMessage("Can't move there bro!");
		}
	}

	private void collision() {
		Iterator<IEntityEnemy> iter = enemiesController.iterator();

		while (iter.hasNext()) {
		    IEntityEnemy enemy = iter.next();

		    if (Physics.Collision(player, enemy)) {
				gameState = GameState.OVER;
			}
			if (bulletController.collision(enemy)) {
				iter.remove();
			}
		}
		if (level.intersects(player.getBounds(), GameObjectType.GOAL)) {
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

	public void shoot(Direction direction) {
		bulletController.addBullet(new BulletShoot(player, textures, direction, level));
	}

	public synchronized void setMessage(String message) {
		gameEngine.setMessage(message);
	}
}
