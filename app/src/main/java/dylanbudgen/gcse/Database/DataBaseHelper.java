package ***REMOVED***gcse.Database;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by ***REMOVED*** on 01/10/2017.
 */

public class DataBaseHelper extends SQLiteOpenHelper {

    //The Android's default system path of your application mDatabase.

    // TODO Update the following line to not hardocde ---------------------------

    //private static String DB_PATH = "/data/data/YOUR_PACKAGE/databases/";
    private static String DB_PATH = "/data/data/***REMOVED***gcse/databases/";
    private static String DB_NAME = "QUESTIONS_TEST";
    private SQLiteDatabase myDataBase;
    private final Context myContext;

    /**
     * Constructor
     * Takes and keeps a reference of the passed context in order to access to the application assets and resources.
     * @param context
     */
    public DataBaseHelper(Context context) {

        super(context, DB_NAME, null, 1);
        this.myContext = context;

        //DB = myContext.getFilesDir().getPath();
    }

    /**
     * Creates a empty mDatabase on the system and rewrites it with your own mDatabase.
     * */
    public void createDataBase() throws IOException {

        boolean dbExist = checkDataBase();

        if(dbExist){
            //do nothing - mDatabase already exist
            Log.d("DEBUG","Database already exists");
        }else{

            Log.d("DEBUG","Database does not already exists");

            //By calling this method and empty mDatabase will be created into the default system path
            //of your application so we are gonna be able to overwrite that mDatabase with our mDatabase.
            this.getReadableDatabase();

            try {

                Log.d("DEBUG","Copying mDatabase");
                copyDataBase();

            } catch (IOException e) {

                throw new Error("Error copying mDatabase");

            }

            Log.d("DEBUG","Database copied");
        }

    }

    /**
     * Check if the mDatabase already exist to avoid re-copying the file each time you open the application.
     * @return true if it exists, false if it doesn't
     */
    private boolean checkDataBase(){

        SQLiteDatabase checkDB = null;

        try{
            String myPath = DB_PATH + DB_NAME;
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);

        } catch(SQLiteException e){

            //mDatabase does't exist yet.

        }

        if(checkDB != null){

            checkDB.close();

        }

        return checkDB != null ? true : false;
    }

    /**
     * Copies your mDatabase from your local assets-folder to the just created empty mDatabase in the
     * system folder, from where it can be accessed and handled.
     * This is done by transfering bytestream.
     * */
    private void copyDataBase() throws IOException{

        //Open your local db as the input stream
        InputStream myInput = myContext.getAssets().open("QUESTIONS_TEST.db");

                //myContext.getAssets().open(DB_NAME);

        // Path to the just created empty db
        String outFileName = DB_PATH + DB_NAME;

        //Open the empty db as the output stream
        OutputStream myOutput = new FileOutputStream(outFileName);

        //transfer bytes from the inputfile to the outputfile
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer))>0){
            myOutput.write(buffer, 0, length);
        }

        //Close the streams
        myOutput.flush();
        myOutput.close();
        myInput.close();

    }

    public void openDataBase() throws SQLException {

        //Open the mDatabase
        String myPath = DB_PATH + DB_NAME;
        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);

    }

    @Override
    public synchronized void close() {

        if(myDataBase != null)
            myDataBase.close();

        super.close();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    // Add your public helper methods to access and get content from the mDatabase.
    // You could return cursors by doing "return myDataBase.query(....)" so it'd be easy
    // to you to create adapters for your views.

}