package ***REMOVED***gcse.Managers;

import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.ProgressBar;

/**
 * Created by ***REMOVED*** on 09/11/2017.
 */

public class ViewManager {


    public static void updateProgressBar(ProgressBar progressBar, int progress) {


        ObjectAnimator animation = ObjectAnimator.ofInt(progressBar, "progress", progress);
        animation.setDuration(300); // 0.3 second
        animation.setInterpolator(new DecelerateInterpolator());
        animation.start();


        //progressBar.setProgress(progress);

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
