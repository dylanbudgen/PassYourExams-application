package ***REMOVED***PassYourExams.ImageAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ProgressBar;

import java.util.ArrayList;

import ***REMOVED***PassYourExams.Lessons.Lesson;
import ***REMOVED***PassYourExams.Managers.ProgressManager;
import ***REMOVED***PassYourExams.Managers.ViewManager;
import ***REMOVED***PassYourExams.R;

public class LessonImageAdapter extends BaseAdapter {

    private Context mContext;
    ArrayList<Lesson> lessons;

    public LessonImageAdapter(Context c, ArrayList<Lesson> lessons) {
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

        return position;
    }


    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            convertView = layoutInflater.inflate(R.layout.lesson_icon_block, null);
        }

        // Update the module icon block
        ViewManager.setLessonIconBlock(convertView, lessons.get(position));

        // TODO Maybe move to ViewManager
        final ProgressBar progressBar = (ProgressBar)convertView.findViewById(R.id.progressbar);
        int progress = ProgressManager.getProgress(mContext, lessons.get(position).getLessonID());
        //progressBar.setProgress(progress);
        ProgressManager.updateProgressBar(progressBar, progress, 1000);

        return convertView;
    }


}