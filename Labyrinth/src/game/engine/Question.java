package game.engine;

/**
 * This class stores the questions that is generated when the player steps on a
 * door.
 * 
 * @author Martin Holecek
 *
 */
public class Question {
	private String question;
	private String answer;

	/**
	 * Constructor
	 * 
	 * @param question - string value of the question
	 * @param answer   - string value of the answer
	 */
	public Question(String question, String answer) {
		this.question = question;
		this.answer = answer;
	}

	/**
	 * Get Question
	 * 
	 * @return - String value of the question
	 */
	public String getQuestion() {
		return question;
	}

	/**
	 * Get Answer
	 * 
	 * @return - String value of the answer
	 */
	public String getAnswer() {
		return answer;
	}

	/**
	 * Check if a user entered valid answer.
	 * 
	 * @param chosenAnswer - user's answer
	 * @return - true if the answer is correct, otherwise false.
	 */
	public boolean checkAnswer(String chosenAnswer) {
		if (answer.toLowerCase().equals(chosenAnswer.trim().toLowerCase())) {
			return true;
		}
		return false;
	}
}
