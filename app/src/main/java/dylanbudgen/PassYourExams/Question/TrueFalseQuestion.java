package ***REMOVED***PassYourExams.Question;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Created by ***REMOVED*** on 18/10/2017.
 */

public class TrueFalseQuestion extends Question implements Serializable {

    protected String incorrectAnswer;

    public TrueFalseQuestion(String question, String questionId, String questionType,
                                  String correctAnswer, String incorrectAnswer) {

        super(question, questionId, questionType, correctAnswer);
        this.correctAnswer = correctAnswer;
        this.incorrectAnswer = incorrectAnswer;
    }

    public ArrayList<String> getAnswers() {

        ArrayList<String> answers = new ArrayList<>();
        answers.addAll(Arrays.asList(correctAnswer, incorrectAnswer));
        Collections.shuffle(answers);
        return answers;

    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }



}
