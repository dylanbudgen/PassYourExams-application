package ***REMOVED***gcse.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.util.Log;

import java.util.ArrayList;

import ***REMOVED***gcse.Lessons.Lesson;
import ***REMOVED***gcse.Lessons.Module;
import ***REMOVED***gcse.Question.InfoQuestion;
import ***REMOVED***gcse.Question.MultipleChoiceQuestion;
import ***REMOVED***gcse.Question.Question;
import ***REMOVED***gcse.Question.TrueFalseQuestion;

/**
 * Created by ***REMOVED*** on 03/10/2017.
 */

public class DatabaseReader {


    Context mContext;
    SQLiteDatabase mDatabase;

    public DatabaseReader(Context context, SQLiteDatabase database) {

        this.mContext = context;
        this.mDatabase = database;

    }

    // TODO THERE NEEDS TO BE EXCEPTIONS!!!!!!!!
    // TODO AND THEY NEED TO BE HANDLED

    // Return array of lesson ids
    private ArrayList<String> getLessonIds() {

        ArrayList<String> lessonIds = new ArrayList<>();

        // Define a projection that specifies which columns from the mDatabase
        // you will actually use after this query.
        String[] projection = {
                FeedReaderContract.FeedEntry.LESSONID
        };

        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                FeedReaderContract.FeedEntry.LESSONID;

        Cursor cursor = mDatabase.query(
                true,
                FeedReaderContract.FeedEntry.TABLE_NAME,                     // The table to query
                projection,                               // The columns to return
                null,                                // The columns for the WHERE clause
                null,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder,                           // The sort order
                null
        );

        while (cursor.moveToNext()) {

            lessonIds.add(cursor.getString(
                    cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.LESSONID)));

        }
        cursor.close();

        return lessonIds;

    }


    public ArrayList<String> getModuleIds() {

        ArrayList<String> moduleIds = new ArrayList<>();



        // Define a projection that specifies which columns from the mDatabase
        // you will actually use after this query.
        String[] projection = {
                FeedReaderContract.FeedEntry.MODULEID
        };

        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                FeedReaderContract.FeedEntry.MODULEID;

        Cursor cursor = mDatabase.query(
                true,
                FeedReaderContract.FeedEntry.TABLE_NAME,                     // The table to query
                projection,                               // The columns to return
                null,                                // The columns for the WHERE clause
                null,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder,                           // The sort order
                null
        );

        while (cursor.moveToNext()) {

            moduleIds.add(cursor.getString(cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.MODULEID)));

        }
        cursor.close();

        return moduleIds;

    }


    public ArrayList<Module> getModules() {

        ArrayList<Module> modules = new ArrayList<>();

        Log.d("DEBUG", "0000P module ids: "  + getModuleIds());

        for (String moduleId: getModuleIds()) {

            ArrayList<Lesson> lessons = new ArrayList<>();

            for (String lessonId : getModuleLessonIds(moduleId)) {

                lessons.add(getLesson(lessonId));
            }

            modules.add(new Module(moduleId, moduleId, lessons));

        }

        return modules;


    }


    // Return array of lesson ids
    private ArrayList<String> getModuleLessonIds(String moduleId) {

        ArrayList<String> lessonIds = new ArrayList<>();

        // Define a projection that specifies which columns from the mDatabase
        // you will actually use after this query.
        String[] projection = {
                FeedReaderContract.FeedEntry.LESSONID,
                FeedReaderContract.FeedEntry.MODULEID

        };

        // Filter results WHERE "title" = 'My Title'
        String selection = FeedReaderContract.FeedEntry.MODULEID + "=?";
        String[] selectionArgs = {moduleId};

        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                FeedReaderContract.FeedEntry.LESSONID;

        Cursor cursor = mDatabase.query(
                true,
                FeedReaderContract.FeedEntry.TABLE_NAME,                     // The table to query
                projection,                               // The columns to return
                selection,                              // The columns for the WHERE clause
                selectionArgs,                           // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder,                           // The sort order
                null
        );

        while (cursor.moveToNext()) {

            lessonIds.add(cursor.getString(
                    cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.LESSONID)));

        }
        cursor.close();

        return lessonIds;

    }

    // Return all lessons
    public ArrayList<Lesson> getLessons() {

        ArrayList<Lesson> lessons = new ArrayList<>();

        for (String lessonId : getLessonIds()) {

            lessons.add(getLesson(lessonId));
            //Log.d("DEBUG","0000P" + lessonId);
        }

        return lessons;

    }

    // Return a lesson by LESSON ID
    public Lesson getLesson(String lessonId) {

        ArrayList<Question> questions = new ArrayList<>();

        String lessonID = null;
        String lessonIcon = null;
        String lessonColour = null;

        int lessonIconDrawable = 0;

        // Define a projection that specifies which columns from the mDatabase
        // you will actually use after this query.
        String[] projection = {
                FeedReaderContract.FeedEntry._ID,
                FeedReaderContract.FeedEntry.MODULEID,
                FeedReaderContract.FeedEntry.LESSONID,
                FeedReaderContract.FeedEntry.LESSONICON,
                FeedReaderContract.FeedEntry.QUESTIONID,
                FeedReaderContract.FeedEntry.QUESTION,
                FeedReaderContract.FeedEntry.QUESTION_TYPE,
                FeedReaderContract.FeedEntry.CORRECT_ANS,
                FeedReaderContract.FeedEntry.INCORRECT_ANS,
                FeedReaderContract.FeedEntry.INCORRECT_ANS_2,
                FeedReaderContract.FeedEntry.COLOUR
        };

        // Filter results WHERE "title" = 'My Title'
        String selection = FeedReaderContract.FeedEntry.LESSONID + "=?";
        String[] selectionArgs = {lessonId};

        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                FeedReaderContract.FeedEntry.LESSONID;

        Cursor cursor = mDatabase.query(
                FeedReaderContract.FeedEntry.TABLE_NAME,                     // The table to query
                projection,                               // The columns to return
                selection,                                // The columns for the WHERE clause
                selectionArgs,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );

        while (cursor.moveToNext()) {

            if (lessonID == null) {
                lessonID = cursor.getString(
                        cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.LESSONID));
            }

            if (lessonIcon == null) {
                lessonIcon = cursor.getString(
                        cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.LESSONICON));
            }

            if (lessonIconDrawable == 0) {

                String itemLessonIcon = cursor.getString(
                        cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.LESSONICON));

                lessonIconDrawable = mContext.getResources().getIdentifier(itemLessonIcon, "drawable", mContext.getPackageName());

            }

            if (lessonColour == null) {
                lessonColour = cursor.getString(
                        cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.COLOUR));

            }

            String questionId = cursor.getString(
                    cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.QUESTIONID));

            String question = cursor.getString(
                    cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.QUESTION));

            String correctAnswer = cursor.getString(
                    cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.CORRECT_ANS));

            String incorrectAnswer = cursor.getString(
                    cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.INCORRECT_ANS));

            String incorrectAnswer2 = cursor.getString(
                    cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.INCORRECT_ANS_2));

            String questionType = cursor.getString(
                    cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.QUESTION_TYPE));

            Question newQuestion = null;

            switch(questionType) {
                case "INFO" :
                    newQuestion = new InfoQuestion(question, questionId, questionType, correctAnswer);
                    break;
                case "TRUE_FALSE" :
                    newQuestion = new TrueFalseQuestion(question, questionId, questionType, correctAnswer, incorrectAnswer);
                    break;
                case "MULT_CHOICE" :
                    newQuestion = new MultipleChoiceQuestion(question, questionId, questionType, correctAnswer, incorrectAnswer,
                            incorrectAnswer2, incorrectAnswer2);
                    break;
            }

            questions.add(newQuestion);

        }
        cursor.close();

        return new Lesson(lessonID, lessonID, lessonIconDrawable, questions, lessonColour);

    }

    /*public ArrayList<LessonIcon> getLessonList() {

        // Define a projection that specifies which columns from the mDatabase
        // you will actually use after this query.
        String[] projection = {
                FeedReaderContract.FeedEntry._ID,
                FeedReaderContract.FeedEntry.MODULEID,
                FeedReaderContract.FeedEntry.LESSONID,
                FeedReaderContract.FeedEntry.LESSONICON
        };

        // Filter results WHERE "title" = 'My Title'
        String selection = FeedReaderContract.FeedEntry.MODULEID + " = ?";
        String[] selectionArgs = {"MOD1"};

        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                FeedReaderContract.FeedEntry.LESSONID;

        Cursor cursor = mDatabase.query(true,
                FeedReaderContract.FeedEntry.TABLE_NAME,                     // The table to query
                projection,                               // The columns to return
                selection,                                // The columns for the WHERE clause
                selectionArgs,                            // The values for the WHERE clause
                "LESSONID",                                     // don't group the rows
                null,                                     // don't filter by row groups
                null,                             // The sort order
                null
        );

        ArrayList<LessonIcon> lessons = new ArrayList<>();

        while (cursor.moveToNext()) {

            String itemLessonId = cursor.getString(
                    cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.LESSONID));

            String itemLessonIcon = cursor.getString(
                    cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.LESSONICON));

            int itemLessonIconResource = mContext.getResources().getIdentifier(itemLessonIcon, "drawable", mContext.getPackageName());

            lessons.add(new LessonIcon(itemLessonId, itemLessonId, itemLessonIconResource));

        }
        cursor.close();

        return lessons;
    }*/


    final class FeedReaderContract {
        // To prevent someone from accidentally instantiating the contract class,
        // make the constructor private.
        private FeedReaderContract() {
        }

        /* Inner class that defines the table contents */
        public class FeedEntry implements BaseColumns {
            public static final String TABLE_NAME = "QUESTIONS";
            public static final String MODULEID = "MODULEID";
            public static final String LESSONID = "LESSONID";
            public static final String LESSONICON = "LESSONICON";
            public static final String QUESTION = "QUESTION";
            public static final String QUESTIONID = "QUESTIONID";
            public static final String QUESTION_TYPE = "QUESTION_TYPE";
            public static final String CORRECT_ANS = "CORRECT_ANS";
            public static final String INCORRECT_ANS = "INCORRECT_ANS";
            public static final String INCORRECT_ANS_2 = "INCORRECT_ANS_2";
            public static final String COLOUR = "COLOUR";
        }
    }

}

