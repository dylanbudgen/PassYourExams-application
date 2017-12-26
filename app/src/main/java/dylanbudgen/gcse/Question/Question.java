package ***REMOVED***gcse.Question;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by ***REMOVED*** on 03/10/2017.
 */

public abstract class Question implements Serializable {

    protected String question;
    protected String questionId;
    protected String questionType;
    protected String correctAnswer;

    public Question(String question, String questionId, String questionType, String correctAnswer) {

        this.question = question;
        this.questionType = questionType;
        this.questionId = questionId;
        this.correctAnswer = correctAnswer;

    }

    public String getQuestion() { return question;  }

    public String getQuestionId() {
        return questionId;
    }

    public String getQuestionType() {
        return questionType;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public abstract ArrayList<String> getAnswers();

    @Override
    public String toString() {
        return "ID: " + questionId + " Type: " + questionType;
    }


}
