package ***REMOVED***gcse.Activities;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.io.IOException;
import java.util.ArrayList;

import ***REMOVED***gcse.Database.DataBaseHelper;
import ***REMOVED***gcse.Database.DatabaseReader;
import ***REMOVED***gcse.Lessons.Lesson;
import ***REMOVED***gcse.Database.LessonIconImageAdapter;
import ***REMOVED***gcse.Managers.ColourManager;
import ***REMOVED***gcse.Managers.ProgressManager;
import ***REMOVED***gcse.Managers.ViewManager;
import ***REMOVED***gcse.R;

public class MainActivity extends AppCompatActivity {

    // DO a version check, where if the version is newer then update the mDatabase
    DataBaseHelper mDbHelper;
    DatabaseReader mDbReader;

    ArrayList<Lesson> lessons;

    private static String DB_PATH = "/data/data/***REMOVED***gcse/databases/QUESTIONS_TEST";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set up toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
        // #############################################################
        toolbar.setBackgroundColor(Color.parseColor("#D3D3D3"));

        //ViewManager.setNotificationBarColour(this.getWindow(), Color.parseColor("#D3D3D3"));
        ViewManager.setActivityBackground(this.getWindow().findViewById(R.id.activity_main), Color.WHITE);

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

        lessons = mDbReader.getLessons();

        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new LessonIconImageAdapter(this, lessons));

        // Start QuestionActivity with Questions
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                Intent intent = new Intent(v.getContext(), QuestionsActivity.class);
                intent.putExtra("LESSON", lessons.get(position));
                startActivity(intent);

            }
        });

    }


    private String returnGrade() {

        String grade = "U";

        int averageStength = calculateAverageStrength();

        if (averageStength >= 80) {
            grade = "A*";
        } else if (averageStength >= 70) {
            grade = "B";
        } else if (averageStength >= 60) {
            grade = "C";
        } else if (averageStength >= 50) {
            grade = "D";
        } else if (averageStength >= 40) {
            grade = "E";
        }

        return grade;

    }

    private int calculateAverageStrength() {

        int totalProgress = 0;

        for (Lesson lesson : lessons) {
            totalProgress = totalProgress + ProgressManager.getLevelProgress(this, lesson.getLessonID());
        }

        return totalProgress / lessons.size();



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

    // Required to set up toolbar and add buttons
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu, menu);

        MenuItem toolbarGrade = menu.findItem(R.id.toolbar_grade);
        toolbarGrade.setTitle("Grade: " + returnGrade());

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

/*            case R.id.action_connect:
                // Connect button pressed
                scan();
                return true;

            case R.id.action_settings:
                // Settings button pressed
                showSettings();
                return true;

            case R.id.action_about:
                // About button pressed
                showAbout();
                return true;*/

            default:
                // Input not recognised, invoke superclass.
                return super.onOptionsItemSelected(item);
        }
    }


}
