package game.engine;

public class Question {
	private String question;
	private String answer;
	
	public Question(String question, String answer) {
		this.question = question;
		this.answer = answer;
	}

	public String getQuestion() {
		return question;
	}

	public String getAnswer() {
		return answer;
	}
	
	public boolean checkAnswer(String chosenAnswer) {
		if (answer.toLowerCase().equals(chosenAnswer.trim().toLowerCase())) {
			return true;
		}
		return false;
	}
}
