package dylanbudgen.gcse.Lessons;

import java.io.Serializable;
import java.util.ArrayList;

import dylanbudgen.gcse.Question.Question;

/**
 * Created by dylanbudgen on 15/10/2017.
 */

public class Lesson implements Serializable {


    private String lessonID;
    private LessonIcon lessonIcon;
    private ArrayList<Question> questions;


    public Lesson(String lessonID, int lessonIcon, ArrayList<Question> questions ) {

        this.lessonID = lessonID;
        this.lessonIcon = new LessonIcon(lessonID, lessonIcon);
        this.questions = questions;

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
}