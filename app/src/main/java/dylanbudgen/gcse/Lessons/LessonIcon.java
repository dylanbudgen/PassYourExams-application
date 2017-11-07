package ***REMOVED***gcse.Lessons;

import android.widget.ProgressBar;

import java.io.Serializable;

/**
 * Created by ***REMOVED*** on 02/10/2017.
 */

public class LessonIcon implements Serializable {

    private String lessonName;
    private String lessonId;
    private int icon;

    public LessonIcon(String lessonId, String lessonName, int icon) {
        this.lessonId = lessonId;
        this.lessonName = lessonName;
        this.icon = icon;
    }

    public String getLessonId() { return lessonId; }

    public String getLessonName() {
        return lessonName;
    }

    public int getIcon() {
        return icon;
    }


}
