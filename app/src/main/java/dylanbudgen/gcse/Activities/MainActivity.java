package dylanbudgen.gcse.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.io.IOException;
import java.util.ArrayList;

import dylanbudgen.gcse.Database.DataBaseHelper;
import dylanbudgen.gcse.Database.DatabaseReader;
import dylanbudgen.gcse.Lessons.Lesson;
import dylanbudgen.gcse.Lessons.LessonIcon;
import dylanbudgen.gcse.Database.LessonIconImageAdapter;
import dylanbudgen.gcse.R;

public class MainActivity extends AppCompatActivity {


    // DO a version check, where if the version is newer then update the mDatabase
    DataBaseHelper mDbHelper;
    DatabaseReader mDbReader;

    private static String DB_PATH = "/data/data/dylanbudgen.gcse/databases/QUESTIONS_TEST";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // hide the toolbar
        getSupportActionBar().hide();

        Log.d("DEBUG", "Deleting database for debugging purposes");
        this.deleteDatabase(DB_PATH);

        // TODO MAKE SURE THERE IS A CHECK IF THE DATABASE UPDATES, THE INTERNALLY STORED DB UPDATES TOO
        // TODO, WITH THIS CODE YOU CAN ALSO DOWNLOAD A DB FROM ONLINE......

        mDbHelper = new DataBaseHelper(this);

        try {
            mDbHelper.createDataBase();
        } catch (IOException ioe) {
            throw new Error("Unable to create mDatabase");
        }

        mDbReader = new DatabaseReader(this, mDbHelper.getReadableDatabase());

        // TODO TRY AND CATCH HERE

        final ArrayList<LessonIcon> lessonIcons = mDbReader.getLessonList();

        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new LessonIconImageAdapter(this, lessonIcons));

        // Start QuestionActivity with Questions
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                Lesson lesson = mDbReader.getLesson(lessonIcons.get(position).getLessonName());


                Intent intent = new Intent(v.getContext(), QuestionsActivity.class);
                intent.putExtra("LESSON", lesson);
                startActivity(intent);

            }
        });

    }

    // Make the back button work doesn't do anything
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)  {
        if (Integer.parseInt(android.os.Build.VERSION.SDK) > 5
                && keyCode == KeyEvent.KEYCODE_BACK
                && event.getRepeatCount() == 0) {
            Log.d("CDA", "onKeyDown Called");
            //onBackPressed();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


}
