import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.JPanel;

public class GameEngine {

	private static final String TITLE = "Labyrinth";
	private static final int WIDTH = 480;
	private static final int HEIGHT = WIDTH / 12 * 9;
	private static final int SCALE = 2;
	private JFrame frame;
	private JTextField textField;
	private JPanel panel;

	public GameEngine() {
		
		frame = new JFrame(TITLE);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		frame.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		frame.setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		frame.setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		
		textField = new JTextField();
		frame.getContentPane().add(textField, BorderLayout.NORTH);
		textField.setColumns(10);
		
		panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.SOUTH);
		
		GameWindow gameWindow = new GameWindow();
		panel.add(gameWindow);
		
		gameWindow.start();
		
		frame.pack();
	}
}
