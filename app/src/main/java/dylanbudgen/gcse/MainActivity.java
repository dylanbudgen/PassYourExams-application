package ***REMOVED***gcse;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    // DO a version check, where if the version is newer then update the mDatabase
    DataBaseHelper mDbHelper;
    DatabaseReader mDbReader;

    private static String DB_PATH = "/data/data/***REMOVED***gcse/databases/QUESTIONS_TEST";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



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

        final ArrayList<LessonIcon> lessons = mDbReader.getLessonList();

        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(new LessonIconImageAdapter(this, lessons));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                ArrayList<Question> questions = mDbReader.getQuestions(lessons.get(position).getLessonName());

                Intent intent = new Intent(v.getContext(), QuestionsActivity.class);
                intent.putExtra("QUESTIONS", questions);
                startActivity(intent);

            }
        });

    }

}
