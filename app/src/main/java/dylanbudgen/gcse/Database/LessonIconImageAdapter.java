package ***REMOVED***gcse.Database;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ProgressBar;

import java.util.ArrayList;

import ***REMOVED***gcse.Lessons.Lesson;
import ***REMOVED***gcse.Managers.ProgressManager;
import ***REMOVED***gcse.Managers.ViewManager;
import ***REMOVED***gcse.R;

public class LessonIconImageAdapter extends BaseAdapter {

    private Context mContext;
    ArrayList<Lesson> lessons;

    public LessonIconImageAdapter(Context c, ArrayList<Lesson> lessons) {
        mContext = c;
        this.lessons = lessons;
    }

    public int getCount() {
        return lessons.size();
    }

    public Object getItem(int position) {
        return lessons.get(position);
    }

    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            convertView = layoutInflater.inflate(R.layout.lesson_icon_block, null);
        }


        // Update the module icon block
        ViewManager.updateLessonIconBlock(convertView, lessons.get(position));

        // TODO Maybe move to ViewManager
        final ProgressBar progressBar = (ProgressBar)convertView.findViewById(R.id.progressbar);
        int progress = ProgressManager.getLevelProgress(mContext, lessons.get(position).getLessonID());
        //progressBar.setProgress(progress);
        ProgressManager.updateProgressBar(progressBar, progress, 1000);

        return convertView;
    }


}