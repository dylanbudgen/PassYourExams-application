package dylanbudgen.gcse.Lessons;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

import dylanbudgen.gcse.Lessons.Lesson;
import dylanbudgen.gcse.Managers.ColourManager;

/**
 * Created by dylanbudgen on 14/11/2017.
 */

public class Module implements Serializable {


    private String moduleId;
    private String moduleName;
    private ArrayList<Lesson> lessons;
    //private int foregroundColour;
   // private int backgroundColour;


    public Module(String moduleId, String moduleName,  ArrayList<Lesson> lessons) {
        this.moduleId = moduleId;
        this.moduleName  = moduleName;
        this.lessons = lessons;
        //foregroundColour = ColourManager.resolveColour(moduleColour, "LIGHT");
        //backgroundColour = ColourManager.resolveColour(moduleColour, "DARK");

    }

    public Lesson getLesson(String lessonId) {

        for (Lesson lesson : lessons) {
            if (lesson.getLessonID().equals(lessonId)) {
                return lesson;
            }
        }

        return null;

    }

    public String getModuleId() {
        return moduleId;
    }

    public String getModuleName() {
        return moduleName;
    }

    public ArrayList<Lesson> getLessons() {
        return lessons;
    }

}
