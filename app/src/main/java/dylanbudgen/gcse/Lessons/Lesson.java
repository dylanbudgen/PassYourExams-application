package ***REMOVED***gcse.Lessons;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import ***REMOVED***gcse.Question.Question;

/**
 * Created by ***REMOVED*** on 15/10/2017.
 */

public class Lesson implements Serializable {


    private String lessonID;
    private LessonIcon lessonIcon;
    private ArrayList<Question> questions;


    public Lesson(String lessonID, int lessonIcon, ArrayList<Question> questions ) {

        this.lessonID = lessonID;
        this.lessonIcon = new LessonIcon(lessonID, lessonID, lessonIcon);
        this.questions = createQuestionsArray(questions);;

    }

    public String getLessonID() {
        return lessonID;
    }

    public ArrayList<Question> getQuestions() {

        return questions;
    }

    public LessonIcon getLessonIcon() {

        return lessonIcon;
    }

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


}
