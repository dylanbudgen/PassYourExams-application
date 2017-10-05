package ***REMOVED***gcse;

import java.util.ArrayList;

/**
 * Created by ***REMOVED*** on 30/09/2017.
 */

public class Module {


    private String title;
    private int number;
    private ArrayList<String> lessons;

    Module(String title, ArrayList<String> lessons) {

        this.title = title;
        this.setLessons(new ArrayList<>(lessons));

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public ArrayList<String> getLessons() {
        return lessons;
    }

    public void setLessons(ArrayList<String> lessons) {
        this.lessons = lessons;
    }
}
