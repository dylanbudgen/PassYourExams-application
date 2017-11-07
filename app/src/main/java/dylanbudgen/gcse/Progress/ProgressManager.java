package ***REMOVED***gcse.Progress;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.preference.PreferenceManager;
import android.widget.ProgressBar;

/**
 * Created by ***REMOVED*** on 20/10/2017.
 */

public class ProgressManager {


    public static int getLevelProgress(Context context, String lessonId) {

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        int progress = sharedPref.getInt(lessonId, 0);
        return progress;

    }

    public static void increaseLevelProgress(Context context, String lessonId, int value) {

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        int currentProgress = sharedPref.getInt(lessonId, 0);

        if ((currentProgress + value) >= 100) {
            value = 100;
        } else {
            value = value + currentProgress;
        }

        SharedPreferences.Editor edit = sharedPref.edit();
        edit.putInt(lessonId, value);
        edit.apply();

    }

    public static void updateProgressBar(ProgressBar progressBar, int progress) {

        progressBar.setProgress(progress);

        if(progress >= 70) {
            progressBar.getProgressDrawable().setColorFilter(Color.GREEN, PorterDuff.Mode.SRC_IN);
        } else {
            progressBar.getProgressDrawable().setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_IN);
        }


    }






}
