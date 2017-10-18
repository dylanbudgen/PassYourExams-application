package ***REMOVED***gcse.Lessons;

import android.widget.ProgressBar;

import java.io.Serializable;

/**
 * Created by ***REMOVED*** on 02/10/2017.
 */

public class LessonIcon implements Serializable {

    private String lessonName;
    private int icon;
    private ProgressBar progressBar;

    public LessonIcon(String lessonName, int icon) {
        this.lessonName = lessonName;
        this.icon = icon;
    }

    public String getLessonName() {
        return lessonName;
    }

    public int getIcon() {
        return icon;
    }


}
