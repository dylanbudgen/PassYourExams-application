package ***REMOVED***gcse.Lessons;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

import ***REMOVED***gcse.Managers.ColourManager;
import ***REMOVED***gcse.Question.Question;

/**
 * Created by ***REMOVED*** on 15/10/2017.
 */

public class Lesson implements Serializable {


    private String lessonID;
    private String lessonName;
    private ArrayList<Question> questions;
    private int lessonIconDrawable;
    private int foregroundColour;
    private int backgroundColour;


    public Lesson(String lessonID, String lessonName, int lessonIconDrawable, ArrayList<Question> questions, String lessonColour) {

        this.lessonID = lessonID;
        this.lessonName = lessonName;
        this.questions = createQuestionsArray(questions);
        this.lessonIconDrawable = lessonIconDrawable;
        foregroundColour = ColourManager.resolveColour(lessonColour, "LIGHT");
        backgroundColour = ColourManager.resolveColour(lessonColour, "DARK");

    }

    public String getLessonID() {
        return lessonID;
    }

    public String getLessonName() {
        return lessonName;
    }

    public ArrayList<Question> getQuestions() { return questions; }

    private ArrayList<Question> createQuestionsArray(ArrayList<Question> questions) {

        ArrayList<Question> list = new ArrayList<>();

        for (Question q : questions) {
            if (!q.getQuestionType().equals("INFO")) {
                list.add(q);
            }
        }

        Collections.shuffle(list);

        for (Question q : questions) {
            if (q.getQuestionType().equals("INFO")) {
                list.add(0, q);
            }
        }

        return list;
    }


    public int getLessonIconDrawable() { return lessonIconDrawable; }

    public int getForegroundColour() {
        return foregroundColour;
    }

    public int getBackgroundColour() {
        return backgroundColour;
    }
}
