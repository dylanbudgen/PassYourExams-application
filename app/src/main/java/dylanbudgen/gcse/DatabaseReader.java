package ***REMOVED***gcse;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;
import android.util.Log;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

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


    // TODO
    // TODO THIS CODE WILL BE INSIDE A CLASS WHICH WILL HOLD A CURSOR OBJECT AS A FIELD.
    // TODO THE FOLLOWING CODE WILL EXECUTE, WITH ALL OF THE FIELDS AND THEN THE REVELENT FIELD WILL BE RETURNED
    // TODO ACTUALLY, MAYBE THAT WONT WORK BECAUSE SOME CALLS WILL NEED A DIFFERENT WHERE QUERY

    // TODO THERE NEEDS TO BE EXCEPTIONS!!!!!!!!
    // TODO AND THEY NEED TO BE HANDLED


    // Return a lesson by LESSON ID
    public ArrayList<Question> getQuestions(String lessonId) {

        ArrayList<Question> questions = new ArrayList<>();


            // Define a projection that specifies which columns from the mDatabase
            // you will actually use after this query.
            String[] projection = {
                    FeedReaderContract.FeedEntry._ID,
                    FeedReaderContract.FeedEntry.MODULEID,
                    FeedReaderContract.FeedEntry.LESSONID,
                    FeedReaderContract.FeedEntry.QUESTIONID,
                    FeedReaderContract.FeedEntry.CORRECT_ANS,
                    FeedReaderContract.FeedEntry.INCORRECT_ANS,
                    FeedReaderContract.FeedEntry.INCORRECT_ANS_2,
                    FeedReaderContract.FeedEntry.QUESTION_TYPE
            };

            // Filter results WHERE "title" = 'My Title'
            String selection = FeedReaderContract.FeedEntry.LESSONID + " = ?";
            String[] selectionArgs = { lessonId };

            // How you want the results sorted in the resulting Cursor
            String sortOrder =
                    FeedReaderContract.FeedEntry.LESSONID+ " DESC";

            Cursor cursor = mDatabase.query(
                    FeedReaderContract.FeedEntry.TABLE_NAME,                     // The table to query
                    projection,                               // The columns to return
                    selection,                                // The columns for the WHERE clause
                    selectionArgs,                            // The values for the WHERE clause
                    null,                                     // don't group the rows
                    null,                                     // don't filter by row groups
                    sortOrder                                 // The sort order
            );

            while(cursor.moveToNext()) {

                String questionId = cursor.getString(
                        cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.QUESTIONID));

                String correctAnswer = cursor.getString(
                        cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.CORRECT_ANS));

                String incorrectAnswer = cursor.getString(
                        cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.INCORRECT_ANS));

                String incorrectAnswer2 = cursor.getString(
                        cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.INCORRECT_ANS_2));

                String questionType = cursor.getString(
                        cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.QUESTION_TYPE));


                questions.add(new Question(questionId, correctAnswer, incorrectAnswer,
                        incorrectAnswer2, questionType));

            }
            cursor.close();

            Log.d("DEBUG", "Returning lesson with question objects ");

            return questions;

        }

    public ArrayList<LessonIcon> getLessonList() {

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
                FeedReaderContract.FeedEntry.LESSONID + " DESC";

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

            lessons.add(new LessonIcon(itemLessonId, itemLessonIconResource));

        }
        cursor.close();

        return lessons;


    }


    public ArrayList<LessonIcon> getLessonListTesting() {

        // Define a projection that specifies which columns from the mDatabase
        // you will actually use after this query.
        String[] projection = {
                FeedReaderContract.FeedEntry._ID,
                FeedReaderContract.FeedEntry.MODULEID,
                FeedReaderContract.FeedEntry.LESSONID,
                FeedReaderContract.FeedEntry.QUESTIONID,
                FeedReaderContract.FeedEntry.LESSONICON
        };

        // Filter results WHERE "title" = 'My Title'
        String selection = FeedReaderContract.FeedEntry.MODULEID + " = ?";
        String[] selectionArgs = {"MOD1"};

        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                FeedReaderContract.FeedEntry.LESSONID + " DESC";

        Cursor cursor = mDatabase.query(
                FeedReaderContract.FeedEntry.TABLE_NAME,                     // The table to query
                projection,                               // The columns to return
                selection,                                // The columns for the WHERE clause
                selectionArgs,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );


        List itemIds = new ArrayList<>();
        List itemLessonIds = new ArrayList<>();
        List itemQuestionIds = new ArrayList<>();

        // HERE
        ArrayList<LessonIcon> lessons = new ArrayList<>();


        while (cursor.moveToNext()) {

            long itemId = cursor.getLong(
                    cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry._ID));
            itemIds.add(itemId);

            String itemLessonId = cursor.getString(
                    cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.LESSONID));
            itemLessonIds.add(itemLessonId);

            String itemLessonIcon = cursor.getString(
                    cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.LESSONICON));

            int itemLessonIconResource = mContext.getResources().getIdentifier(itemLessonIcon, "drawable", mContext.getPackageName());
            itemLessonIds.add(itemLessonIconResource);

            // HERE
            lessons.add(new LessonIcon(itemLessonId, itemLessonIconResource));

            String itemQuestionId = cursor.getString(
                    cursor.getColumnIndexOrThrow(FeedReaderContract.FeedEntry.QUESTIONID));
            itemQuestionIds.add(itemQuestionId);
        }
        cursor.close();

        Log.d("DEBUG", "Everything worked fine apparantly");
        Log.d("DEBUG", itemIds.toString());
        Log.d("DEBUG", itemLessonIds.toString());
        Log.d("DEBUG", itemQuestionIds.toString());

        return lessons;

    }
}

    final class FeedReaderContract {
        // To prevent someone from accidentally instantiating the contract class,
        // make the constructor private.
        private FeedReaderContract() {}

        /* Inner class that defines the table contents */
        public class FeedEntry implements BaseColumns {
            public static final String TABLE_NAME = "QUESTIONS";
            public static final String MODULEID = "MODULEID";
            public static final String LESSONID = "LESSONID";
            public static final String QUESTIONID = "QUESTIONID";
            public static final String LESSONICON = "LESSONICON";
            public static final String CORRECT_ANS = "CORRECT_ANS";
            public static final String INCORRECT_ANS = "INCORRECT_ANS";
            public static final String INCORRECT_ANS_2 = "INCORRECT_ANS_2";
            public static final String QUESTION_TYPE = "QUESTION_TYPE";
        }
    }

