package ***REMOVED***gcse.Managers;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.preference.PreferenceManager;
import android.view.animation.DecelerateInterpolator;
import android.widget.ProgressBar;

/**
 * Created by ***REMOVED*** on 20/10/2017.
 */

public class ProgressManager {


    public static int getLevelProgress(Context context, String lessonId) {

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        int progress = sharedPref.getInt(lessonId, 20);
        return progress;

    }

    public static void increaseLevelProgress(Context context, String lessonId, int value) {

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        int currentProgress = getLevelProgress(context, lessonId);

        if ((currentProgress + value) >= 100) {
            value = 100;
        } else {
            value = value + currentProgress;
        }

        SharedPreferences.Editor edit = sharedPref.edit();
        edit.putInt(lessonId, value);
        edit.apply();

    }

    public static void updateProgressBar(ProgressBar progressBar, int progress, int speed) {

        if(progress >= 80) {
            progressBar.getProgressDrawable().setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_IN);
            progress = 100;
        } else if (progress <= 20) {
            progressBar.getProgressDrawable().setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN);
            progress = 20;
        } else {
            progressBar.getProgressDrawable().setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_IN);

        }

        ObjectAnimator animation = ObjectAnimator.ofInt(progressBar, "progress", progress);
        animation.setDuration(speed); // 300 = 0.3 second
        animation.setInterpolator(new DecelerateInterpolator());
        animation.start();


    }






}
