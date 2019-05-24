package game.engine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

/**
 * This class stores all the questions inside a list.
 * 
 * @author Martin Holecek
 *
 */
public class QuestionPool {
	private ArrayList<Question> questions = new ArrayList<Question>();
	private Random random = new Random();

	/**
	 * Constructor
	 * 
	 * @param path - the path of the file where are the questions stored
	 */
	public QuestionPool(String path) {
		loadQuestionsFromFile(path);
	}

	/**
	 * Load the questions from the file.
	 * 
	 * @param path - the path of the file where are the questions stored
	 */
	public void loadQuestionsFromFile(String path) {
		InputStream inputStream = this.getClass().getResourceAsStream(path);
		BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

		try {
			String line = reader.readLine();
			while (line != null) {
				String[] parsed = line.split(",");
				if (parsed.length == 2) {
					questions.add(new Question(parsed[0].trim(), parsed[1].trim()));
				}
				line = reader.readLine();
			}
		} catch (IOException e) {
			System.out.println("Error reading the file!");
			System.exit(1);
		}
	}

	/**
	 * Get random question from the list.
	 * 
	 * @return - instance of the question object
	 */
	public Question getRandomQuestion() {
		return questions.get(random.nextInt(questions.size()));
	}
}
