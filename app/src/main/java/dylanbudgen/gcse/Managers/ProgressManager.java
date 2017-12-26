package ***REMOVED***gcse.Managers;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.animation.DecelerateInterpolator;
import android.widget.ProgressBar;

import java.util.ArrayList;

import ***REMOVED***gcse.Lessons.Lesson;
import ***REMOVED***gcse.Lessons.Module;

/**
 * Created by ***REMOVED*** on 20/10/2017.
 */

public class ProgressManager {

    private final static int DAILY_PERCENTAGE_REDUCTION = 3;


    public static int getProgress(Context context, String name) {

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        int progress = sharedPref.getInt(name, 20);
        return progress;

    }

    public static long getTimeProgress(Context context, String name) {

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        long progress = sharedPref.getLong(name, System.currentTimeMillis());
        return progress;

    }

    public static void updateSetting(Context context, String name, int value) {

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor edit = sharedPref.edit();
        edit.putInt(name, value);
        edit.apply();

    }

    public static void updateTimeSetting(Context context, String name, long value) {

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor edit = sharedPref.edit();
        edit.putLong(name, value);
        edit.apply();

    }

    public static void recalculateProgress(Context context, ArrayList<Module> modules) {

        for (Module module : modules) {
            for (Lesson lesson : module.getLessons()) {

                int currentProgress = getProgress(context, lesson.getLessonID());
                long previousTime = getTimeProgress(context, lesson.getLessonID() + "_TIME");
                int days = ((int) System.currentTimeMillis() / 86400000) - ((int) previousTime / 86400000);
                Log.d("DEBUG", "0000P Days: " + days);

                int newProgress = currentProgress;

                if (days != 0) {
                    newProgress = currentProgress - (days * DAILY_PERCENTAGE_REDUCTION);
                }

                if (newProgress < 0) {
                    newProgress = 0;
                }

                updateSetting(context, lesson.getLessonID(), newProgress);
                updateTimeSetting(context, lesson.getLessonID() + "_TIME", System.currentTimeMillis());


            }
        }

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


    public static String getModuleGrade(Context context, Module module) {

        int totalPercentage = 0;

        for (Lesson lesson : module.getLessons()) {
            totalPercentage = totalPercentage + ProgressManager.getProgress(context, lesson.getLessonID());
        }

        String grade = "U";

        int averagePercentage = totalPercentage / module.getLessons().size();

        if (averagePercentage >= 80) {
            grade = "A*";
        } else if (averagePercentage >= 70) {
            grade = "A";
        } else if (averagePercentage >= 60) {
            grade = "B";
        } else if (averagePercentage >= 50) {
            grade = "C";
        } else if (averagePercentage >= 40) {
            grade = "D";
        } else if (averagePercentage >= 30) {
            grade = "E";
        }

        return grade;
    }


    public static String getLessonGrade(Context context, Lesson lesson) {

        String grade = "U";

        int percentage =  ProgressManager.getProgress(context, lesson.getLessonID());

        if (percentage >= 80) {
            grade = "A*";
        } else if (percentage >= 70) {
            grade = "A";
        } else if (percentage >= 60) {
            grade = "B";
        } else if (percentage >= 50) {
            grade = "C";
        } else if (percentage >= 40) {
            grade = "D";
        } else if (percentage >= 30) {
            grade = "E";
        }

        return grade;

    }






}
