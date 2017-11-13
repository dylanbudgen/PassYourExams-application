package dylanbudgen.gcse.Database;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ProgressBar;

import java.util.ArrayList;

import dylanbudgen.gcse.Lessons.Lesson;
import dylanbudgen.gcse.Managers.ProgressManager;
import dylanbudgen.gcse.Managers.ViewManager;
import dylanbudgen.gcse.R;

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



        if (position == 4) {
            final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            convertView = layoutInflater.inflate(R.layout.module_title, null);
            return convertView;
        }

        if (position == 5) {
            final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            convertView = layoutInflater.inflate(R.layout.module_title, null);
            return convertView;
        }





        if (convertView == null) {
            final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            convertView = layoutInflater.inflate(R.layout.lesson_icon_block, null);
        }

        // Update the module icon block
        ViewManager.setLessonIconBlock(convertView, lessons.get(position));

        // TODO Maybe move to ViewManager
        final ProgressBar progressBar = (ProgressBar)convertView.findViewById(R.id.progressbar);
        int progress = ProgressManager.getLevelProgress(mContext, lessons.get(position).getLessonID());
        //progressBar.setProgress(progress);
        ProgressManager.updateProgressBar(progressBar, progress, 1000);

        return convertView;
    }


}