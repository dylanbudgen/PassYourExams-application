package ***REMOVED***gcse;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Created by ***REMOVED*** on 03/10/2017.
 */

public class Question implements Serializable {

    private String questionId;
    private String correctAnswer;
    private String incorrectAnswer;
    private String incorrectAnswer2;
    private String questionType;


    // TODO   THIS SHOULD ACTUALLY USE POLYMORPHISM
    // TODO THERE SHOULD BE SUBCLASSES FOR THE DIFFERENT QUESTION TYPES
    // TODO -----------------------------------------------------------------


    public Question(String questionId, String correctAnswer, String incorrectAnswer,
                    String incorrectAnswer2, String questionType) {

        this.questionId = questionId;
        this.correctAnswer = correctAnswer;
        this.incorrectAnswer = incorrectAnswer;
        this.incorrectAnswer2 = incorrectAnswer2;
        this.questionType = questionType;

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

    public String getQuestionType() {
        return questionType;
    }

    public ArrayList<String> getAnswers() {

        ArrayList<String> answers = new ArrayList<String>(Arrays.asList(correctAnswer, incorrectAnswer, incorrectAnswer2, incorrectAnswer2));
        Collections.shuffle(answers);
        return answers;

    }


    @Override
    public String toString() {
        return "ID: " + questionId + " Type: " + questionType;
    }

}
