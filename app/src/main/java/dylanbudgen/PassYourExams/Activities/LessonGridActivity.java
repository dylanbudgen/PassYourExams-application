package dylanbudgen.PassYourExams.Activities;

import android.content.Intent;
import android.graphics.Color;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;

import dylanbudgen.PassYourExams.ImageAdapters.LessonImageAdapter;
import dylanbudgen.PassYourExams.Lessons.Lesson;
import dylanbudgen.PassYourExams.Lessons.Module;
import dylanbudgen.PassYourExams.Managers.ProgressManager;
import dylanbudgen.PassYourExams.Managers.ViewManager;
import dylanbudgen.PassYourExams.R;

public class LessonGridActivity extends AppCompatActivity {

    Module module;
    ArrayList<Lesson> lessons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_grid);

        // Get Module and lessons
        module = (Module) getIntent().getSerializableExtra("MODULE");
        lessons = module.getLessons();

        // Set up toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.lesson_grid_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(module.getModuleName());
        toolbar.setBackgroundColor(lessons.get(0).getForegroundColour());
        toolbar.setTitleTextColor(Color.WHITE);
        // TODO move to view manager

        // Change colour of background
        ViewManager.setActivityBackground(this.getWindow().findViewById(R.id.activity_lesson_grid), lessons.get(0).getBackgroundColour());

        // Change the notification bar colour
        ViewManager.setNotificationBarColour(this.getWindow(), lessons.get(0).getBackgroundColour());


        if (module == null) {
            Log.d("DEBUG", "0000P Module is NULL");
        } else {
            Log.d("DEBUG", "0000P Module is NOT null" + module.getModuleId());
        }

        // Initiate gridview with adapter
        GridView gridview = (GridView) findViewById(R.id.lessons_gridview);
        gridview.setAdapter(new LessonImageAdapter(this, lessons));

        // Start QuestionActivity with Questions
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                Intent intent = new Intent(v.getContext(), QuestionsActivity.class);
                intent.putExtra("LESSON_ID", lessons.get(position).getLessonID());
                intent.putExtra("MODULE", module);
                startActivity(intent);

            }
        });
    }

    // Required to set up toolbar and add buttons
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.lesson_grid_menu, menu);
        menu.findItem(R.id.lesson_grid_grade).setTitle("Grade: " + ProgressManager.getModuleGrade(this, module));  // Set grade
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onBackPressed() {
        Log.d("CDA", "onBackPressed Called");

        // TODO warning box for going back
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        //overridePendingTransition(R.anim.activity_left_to_right, R.anim.activity_right_to_left);
    }
}
