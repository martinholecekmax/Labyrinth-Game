import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

public class GameWindow extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;
	private boolean running = false;
	private Thread thread;

	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	private BufferedImage spriteSheet = null;
	private Player player;
	private Background background;
	private BulletController bulletController;
	private Level level;
	private Textures textures;
	private EnemiesController enemiesController;
	private STATE State = STATE.GAME;
	private GameOver gameOver;
	private Enemy enemy;
	
	private enum STATE {
		GAME, OVER, FINISH
	}

	public GameWindow() {
		loadTileSet();

		setPreferredSize(new Dimension(320, 320));
		setMinimumSize(new Dimension(320, 320));
		setMaximumSize(new Dimension(320, 320));

		addKeyListener(new KeyInput(this));

		textures = new Textures(this);

		level = new Level(textures);
		player = new Player(0, 0, textures, level);
		bulletController = new BulletController(level);
		enemiesController = new EnemiesController();
		enemy = new Enemy(2 * 32,2 * 32, textures, level);
		enemiesController.addEnemy(new Enemy(6 * 32,8 * 32, textures, level));
		enemiesController.addEnemy(new Enemy(4 * 32, 8 * 32, textures, level));
		background = new Background(textures, level);
		gameOver = new GameOver();
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
			} catch (Exception e) {}
		}
		stop();
	}
	
//	@Override
//	public void run() {
//		long lastTime = System.nanoTime();
//		final double amountOfTicks = 60.0;
//		double ns = 1000000000 / amountOfTicks;
//		double delta = 0;
//		int updates = 0;
//		int frames = 0;
//		long timer = System.currentTimeMillis();
//
//		while (running) {
//			long now = System.nanoTime();
//			delta += (now - lastTime) / ns;
//			lastTime = now;
//			if (delta >= 1) {
//				tick();
//				updates++;
//				delta--;
//			}
//			render();
//			frames++;
//
//			if (System.currentTimeMillis() - timer > 1000) {
//				timer += 1000;
//				System.out.println(updates + " Ticks, FPS: " + frames);
//				updates = 0;
//				frames = 0;
//			}
//		}
//		stop();
//	}

	private void tick() {
		if (State == STATE.GAME) {
			enemy.tick();
			player.tick();
			bulletController.tick();
			enemiesController.tick();
			if (Physics.Collision(player, enemiesController.getAllEnemies())) {
				gameOver();
			}
		} else if (State == STATE.OVER) {

		}
		collision();
	}

	private void collision() {
		LinkedList<IEntityEnemy> enemies = enemiesController.getAllEnemies();
		for (int index = 0; index < enemies.size(); index++) {
			if(bulletController.collision(enemies.get(index))) {
				enemiesController.removeEnemy(enemies.get(index));
			}
		}
	}

	private void gameOver() {
		System.out.println("Game Over");
		State = STATE.OVER;
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
		enemy.render(g);
		
		if (State == STATE.OVER) {
			gameOver.render(g, getWidth(), getHeight());
		}

		//////////////////////////////////////

		g.dispose();
		bufferStrategy.show();
	}

	private void loadTileSet() {
		BufferedImageLoader loader = new BufferedImageLoader();
		try {
			spriteSheet = loader.loadImage("/tileset.png");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public BufferedImage getSpriteSheet() {
		return spriteSheet;
	}

	public void keyPressed(KeyEvent e) {
		if (State == STATE.GAME) {
//			boolean st = false;
			int key = e.getKeyCode();
			if (key == KeyEvent.VK_RIGHT) {
				player.moveRight();
//				st = enemy.moveRight();
			} else if (key == KeyEvent.VK_LEFT) {
				player.moveLeft();
//				st = enemy.moveLeft();
			} else if (key == KeyEvent.VK_UP) {
				player.moveUp();
//				st = enemy.moveUp();
			} else if (key == KeyEvent.VK_DOWN) {
				player.moveDown();
//				st = enemy.moveDown();
			} else if (key == KeyEvent.VK_A) {
				IEntityBullets bullet = new BulletShootRight(player.getRow(), player.getCol(), textures);
				bulletController.addBullet(bullet);
			}
//			System.out.println("ST: " + st);
		}
	}
}
