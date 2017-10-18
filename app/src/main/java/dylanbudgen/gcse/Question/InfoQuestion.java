package ***REMOVED***gcse.Question;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Created by ***REMOVED*** on 18/10/2017.
 */

public class InfoQuestion extends Question implements Serializable {

    public InfoQuestion(String question, String questionId, String questionType, String correctAnswer) {

        super(question, questionId, questionType, correctAnswer);
    }

    public ArrayList<String> getAnswers() {
        ArrayList<String> answers = new ArrayList<>();
        answers.addAll(Arrays.asList(correctAnswer));
        return answers;
    }

}
