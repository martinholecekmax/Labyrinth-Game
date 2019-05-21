package game.engine;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import game.commands.ICommand;
import game.utils.TextLineNumber;
import interpreter.ExceptionSemantic;
import interpreter.Interpreter;
import parser.ast.ParseException;
import parser.ast.TokenMgrError;

public class GameEngine implements ActionListener {

	private static final String TITLE = "Labyrinth";
	private JFrame frame;
	private JPanel splitPane;
	private JPanel panelRight;
	private JPanel panelRightBottom;
	private JPanel panelGame;
	private JMenuBar menuBar;
	private JMenu mnFile;
	private JMenuItem mntmLoadProgramFile;
	private JMenuItem mntmLoadMapFile;
	private JTextArea textAreaProgram;
	private JTextArea textMessage;
	private JScrollPane scrollPaneProgram;
	private JFileChooser fileChooser;
	private JScrollPane scrollPaneMessage;
	private JButton btnGo;
	private FileNameExtensionFilter fileProgramFilter;
	private FileNameExtensionFilter fileMapFilter;
	private String mapFilePath = "/map.csv";
	private String questionsPath = "/questions.csv";
	private GameWindow gameWindow;
	private int numLines;

//	private static final int WIDTH = 480;
//	private static final int HEIGHT = WIDTH / 12 * 9;
//	private static final int SCALE = 2;

	public int getNumLines() {
		return numLines;
	}

	public GameEngine() {

		frame = new JFrame(TITLE);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);

//		frame.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
//		frame.setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
//		frame.setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));

		splitPane = new JPanel();
		panelRight = new JPanel();
		panelRightBottom = new JPanel();
		btnGo = new JButton("Go");
		textAreaProgram = new JTextArea();
		scrollPaneProgram = new JScrollPane(textAreaProgram);
		menuBar = new JMenuBar();
		mnFile = new JMenu("File");
		mntmLoadProgramFile = new JMenuItem("Load Program");
		mntmLoadMapFile = new JMenuItem("Load Map");
		fileChooser = new JFileChooser();

		btnGo.addActionListener(this);
		textAreaProgram.setRows(10);
		textAreaProgram.setColumns(30);
		panelRight.setLayout(new BorderLayout(0, 0));
		panelRight.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
		panelRight.add(scrollPaneProgram, BorderLayout.CENTER);

		TextLineNumber tln = new TextLineNumber(textAreaProgram);
		scrollPaneProgram.setRowHeaderView(tln);

		TitledBorder title = new TitledBorder("Message");
		TitledBorder titleProgram = new TitledBorder("Program");
		textAreaProgram.setBorder(titleProgram);

		textMessage = new JTextArea();
		scrollPaneMessage = new JScrollPane(textMessage);
		textMessage.setEditable(false);
		textMessage.setRows(5);
		textMessage.setBorder(title);

		gameWindow = new GameWindow(this, mapFilePath, questionsPath);
		panelGame = new JPanel(new GridLayout(1, 1));
		panelGame.setPreferredSize(gameWindow.getPreferredSize());
		panelGame.setMinimumSize(gameWindow.getMinimumSize());
		panelGame.setMaximumSize(gameWindow.getMaximumSize());
		panelGame.add(gameWindow);

		panelRightBottom.setLayout(new BorderLayout());
		panelRightBottom.add(btnGo, BorderLayout.SOUTH);
		panelRightBottom.add(scrollPaneMessage, BorderLayout.CENTER);

		panelRight.add(panelRightBottom, BorderLayout.SOUTH);

		frame.add(splitPane);

		frame.setJMenuBar(menuBar);
		menuBar.add(mnFile);
		mnFile.add(mntmLoadProgramFile);
		mntmLoadProgramFile.addActionListener(this);
		mnFile.add(mntmLoadMapFile);
		mntmLoadMapFile.addActionListener(this);

		fileChooser.setCurrentDirectory(new java.io.File("."));
		fileChooser.setDialogTitle("Choose file");
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		fileChooser.setAcceptAllFileFilterUsed(false);
		fileProgramFilter = new FileNameExtensionFilter("Labyrinth file", "sil");
		fileMapFilter = new FileNameExtensionFilter("Map file", "csv");

		splitPane.setLayout(new BorderLayout());
		splitPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		splitPane.add(panelGame, BorderLayout.CENTER);
		splitPane.add(panelRight, BorderLayout.EAST);

		frame.repaint();
		frame.revalidate();
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		numLines = 0;
		gameWindow.start();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		btnGo.setEnabled(false);
		if (e.getSource() == btnGo) {
			parse();
		} else if (e.getSource() == mntmLoadProgramFile) {
			fileChooser.setFileFilter(fileProgramFilter);
			textAreaProgram.setText(textAreaProgram.getText() + readFile());
		} else if (e.getSource() == mntmLoadMapFile) {
			fileChooser.setFileFilter(fileMapFilter);
			String t = readFile();
			System.out.println(t);
		}
		btnGo.setEnabled(true);
	}

	private String readFile() {
		if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			File file = fileChooser.getSelectedFile();
			try {
				BufferedReader reader = new BufferedReader(new FileReader(file));
				StringBuilder stb = new StringBuilder();
				String nextLine;
				while ((nextLine = reader.readLine()) != null) {
					stb.append(nextLine + "\n");
				}
				reader.close();
				return stb.toString();
			} catch (FileNotFoundException e1) {
				textMessage.setText(e1.getMessage());
			} catch (IOException e1) {
				textMessage.setText(e1.getMessage());
			}
		}
		return "";
	}

	private void parse() {
		String text = textAreaProgram.getText();
		String[] textLines = text.trim().split("\n");
		numLines += textLines.length;
		ArrayList<ICommand> done;
		try {
			textMessage.setText("");
			done = Interpreter.parse(text);
			for (ICommand command : done) {
				gameWindow.addCommand(command);
			}
		} catch (ParseException parseError) {
			textMessage.setText(parseError.getMessage());
		} catch (TokenMgrError tokenError) {
			textMessage.setText(tokenError.getMessage());
		} catch (ExceptionSemantic semanticError) {
			textMessage.setText(semanticError.getMessage());
		}
	}

	public void setMessage(String text) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				textMessage.setText(text);
			}
		});
	}
}
