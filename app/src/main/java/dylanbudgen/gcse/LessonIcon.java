package ***REMOVED***gcse;

import android.widget.ProgressBar;

/**
 * Created by ***REMOVED*** on 02/10/2017.
 */

public class LessonIcon {

    private String lessonName;
    private int lessonIcon;
    private ProgressBar progressBar;

    public LessonIcon(String lessonName, int lessonIcon) {
        this.lessonName = lessonName;
        this.lessonIcon = lessonIcon;
    }

    public String getLessonName() {
        return lessonName;
    }

    public int getLessonIcon() {
        return lessonIcon;
    }


}
