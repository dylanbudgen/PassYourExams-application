package ***REMOVED***gcse.Managers;

import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import ***REMOVED***gcse.Lessons.Lesson;
import ***REMOVED***gcse.R;

/**
 * Created by ***REMOVED*** on 09/11/2017.
 */

public class ViewManager {

    public static void updateLessonIconBlock(View view, Lesson lesson) {

        ImageView moduleIcon = (ImageView)view.findViewById(R.id.lesson_icon_imageview);
        TextView moduleTitle = (TextView)view.findViewById(R.id.module_name);

        moduleIcon.setImageResource(lesson.getLessonIconDrawable());
        moduleTitle.setText(lesson.getLessonName());

    }

    public static void setNotificationBarColour(Window window, int colour) {

        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(colour);

    }

    public static void setActivityBackground(View view, int colour) {


        view.setBackgroundColor(colour);
        //view.getBackground().setColorFilter(colour, PorterDuff.Mode.DARKEN);
        view.invalidate();
    }

    public static void setAllParentsClip(View v, boolean enabled) {
        while (v.getParent() != null && v.getParent() instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) v.getParent();
            viewGroup.setClipChildren(enabled);
            viewGroup.setClipToPadding(enabled);
            v = viewGroup;
        }
    }
}
