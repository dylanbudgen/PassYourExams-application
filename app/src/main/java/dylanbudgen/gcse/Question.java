package ***REMOVED***gcse;

/**
 * Created by ***REMOVED*** on 03/10/2017.
 */

public class Question {

    private String questionId;
    private String correctAnswer;
    private String incorrectAnswer;
    private String incorrectAnswer2;

    public Question(String questionId, String correctAnswer, String incorrectAnswer, String incorrectAnswer2) {

        this.questionId = questionId;
        this.correctAnswer = correctAnswer;
        this.incorrectAnswer = incorrectAnswer;
        this.incorrectAnswer2 = incorrectAnswer2;

    }

    public String getQuestionId() {
        return questionId;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public String getIncorrectAnswer() {
        return incorrectAnswer;
    }

    public String getIncorrectAnswer2() {
        return incorrectAnswer2;
    }
}
