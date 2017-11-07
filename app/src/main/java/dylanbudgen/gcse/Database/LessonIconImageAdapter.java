package ***REMOVED***gcse.Database;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

import ***REMOVED***gcse.Lessons.LessonIcon;
import ***REMOVED***gcse.Progress.ProgressManager;
import ***REMOVED***gcse.R;

public class LessonIconImageAdapter extends BaseAdapter {

    private Context mContext;
    ArrayList<LessonIcon> lessonIcons;

    public LessonIconImageAdapter(Context c, ArrayList<LessonIcon> lessonIcons) {
        mContext = c;
        this.lessonIcons = lessonIcons;
    }

    public int getCount() {
        return lessonIcons.size();
    }

    public Object getItem(int position) {
        return lessonIcons.get(position);
    }

    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            convertView = layoutInflater.inflate(R.layout.module_icon, null);
        }

        final ImageView moduleIcon = (ImageView)convertView.findViewById(R.id.module_icon_image);
        final TextView moduleTitle = (TextView)convertView.findViewById(R.id.module_name);
        final ProgressBar progressBar = (ProgressBar)convertView.findViewById(R.id.progressbar);

        moduleIcon.setImageResource(lessonIcons.get(position).getIcon());
        moduleTitle.setText(lessonIcons.get(position).getLessonName());
        int progress = ProgressManager.getLevelProgress(mContext, lessonIcons.get(position).getLessonId());
        ProgressManager.updateProgressBar(progressBar, progress);


        return convertView;
    }


}