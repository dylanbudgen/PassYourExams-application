package ***REMOVED***gcse.Managers;

import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import ***REMOVED***gcse.Lessons.Lesson;
import ***REMOVED***gcse.Lessons.Module;
import ***REMOVED***gcse.R;

import static com.google.android.gms.internal.zzahf.runOnUiThread;

/**
 * Created by ***REMOVED*** on 09/11/2017.
 */

public class ViewManager {


    /**
     * Update the visibility of the progress bar
     */
    public static void setProgressBar(final View view, final int id, final int progress) {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                ProgressBar progressBar = (ProgressBar) view.findViewById(id);
                progressBar.setProgress(progress);
            }
        });
    }

    /**
     * Update the status message text
     * @param id id of text view
     * @param message string to show to user
     */
    public static void setTextView(final View view, final int id, final String message) {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                TextView textView = (TextView) view.findViewById(id);
                textView.setText(message);
            }
        });
    }


    public static void setImageView(final View view, final int id, final int drawable) {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                ImageView imageView = (ImageView) view.findViewById(id);
                imageView.setImageResource(drawable);

            }
        });
    }

    public static void setLessonIconBlock(final View view, final Lesson lesson) {

        setTextView(view, R.id.lesson_name, lesson.getLessonName());
        //setImageView(view, R.id.lesson_icon_imageview, lesson.getLessonIconDrawable());
        //setImageView(view, R.id.lesson_icon_imageview, R.drawable.sample_1_b);

        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                ImageView img = (ImageView) view.findViewById(R.id.lesson_icon_imageview);
                img.setColorFilter(lesson.getForegroundColour());
            }
        });


    }

    public static void setModuleIconBlock(final View view, final Module module) {

       // setTextView(view, R.id.module_name, module.getModuleName());
        //setTextView(view, R.id.module_grade, ProgressManager.getModuleGrade(this, ));
        //setImageView(view, R.id.lesson_icon_imageview, lesson.getLessonIconDrawable());
        //setImageView(view, R.id.lesson_icon_imageview, R.drawable.sample_1_b);

        /*runOnUiThread(new Runnable() {
            @Override
            public void run() {

                //ImageView img = (ImageView) view.findViewById(R.id.lesson_icon_imageview);
               // img.setColorFilter(lesson.getForegroundColour());
            }
        });*/

        //
        //
        //
        //
        //

    }

    public static void setNotificationBarColour(final Window window, final int colour) {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(colour);

            }
        });


    }

    public static void setActivityBackground(final View view, final int colour) {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                view.setBackgroundColor(colour);
                //view.getBackground().setColorFilter(colour, PorterDuff.Mode.DARKEN);
                view.invalidate();

            }
        });


    }



/*    public static void setAllParentsClip(View v, boolean enabled) {
        while (v.getParent() != null && v.getParent() instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) v.getParent();
            viewGroup.setClipChildren(enabled);
            viewGroup.setClipToPadding(enabled);
            v = viewGroup;
        }
    }*/
}
