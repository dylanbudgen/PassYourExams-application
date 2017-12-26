package ***REMOVED***gcse.ImageAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ProgressBar;

import java.util.ArrayList;

import ***REMOVED***gcse.Lessons.Module;
import ***REMOVED***gcse.Managers.ProgressManager;
import ***REMOVED***gcse.Managers.ViewManager;
import ***REMOVED***gcse.R;

public class ModuleImageAdapter extends BaseAdapter {

    private Context mContext;
    ArrayList<Module> modules;

    public ModuleImageAdapter(Context c, ArrayList<Module> modules) {
        mContext = c;
        this.modules = modules;
    }

    public int getCount() {

        return modules.size();
    }

    public Object getItem(int position) {

        return modules.get(position);
    }

    public long getItemId(int position) {

        return position;
    }


    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            convertView = layoutInflater.inflate(R.layout.module_icon_block, null);
        }

        // Update the module icon block
        //ViewManager.setModuleIconBlock(convertView, modules.get(position));
        ViewManager.setTextView(convertView, R.id.module_name, modules.get(position).getModuleName());
        ViewManager.setTextView(convertView, R.id.module_grade, "Grade: " + ProgressManager.getModuleGrade(convertView.getContext(), modules.get(position)));
        //setTextView(view, R.id.module_grade, ProgressManager.getModuleGrade(this, ));


        // TODO Maybe move to ViewManager
        //final ProgressBar progressBar = (ProgressBar)convertView.findViewById(R.id.progressbar);
        //int progress = ProgressManager.getProgress(mContext, modules.get(position).getLessonID());
        //progressBar.setProgress(progress);
        //ProgressManager.updateProgressBar(progressBar, progress, 1000);

        return convertView;
    }


}