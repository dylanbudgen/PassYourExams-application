package ***REMOVED***PassYourExams.Question;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Created by ***REMOVED*** on 18/10/2017.
 */

public class MultipleChoiceQuestion extends Question implements Serializable {

    protected String incorrectAnswer1;
    protected String incorrectAnswer2;
    protected String incorrectAnswer3;

    public MultipleChoiceQuestion(String question, String questionId, String questionType,
                                String correctAnswer, String incorrectAnswer1,
                                  String incorrectAnswer2, String incorrectAnswer3) {

        super(question, questionId, questionType, correctAnswer);
        this.incorrectAnswer1 = incorrectAnswer1;
        this.incorrectAnswer2 = incorrectAnswer2;
        this.incorrectAnswer3 = incorrectAnswer3;
    }

    public ArrayList<String> getAnswers() {

        ArrayList<String> answers = new ArrayList<>();
        answers.addAll(Arrays.asList(correctAnswer, incorrectAnswer1, incorrectAnswer2, incorrectAnswer2));
        Collections.shuffle(answers);
        return answers;

    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

}
