package ***REMOVED***PassYourExams.Activities;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

//import com.google.android.gms.ads.AdListener;
//import com.google.android.gms.ads.AdRequest;
//import com.google.android.gms.ads.InterstitialAd;

import ***REMOVED***PassYourExams.Lessons.Lesson;
import ***REMOVED***PassYourExams.Lessons.Module;
import ***REMOVED***PassYourExams.Managers.ProgressManager;
import ***REMOVED***PassYourExams.Managers.ViewManager;
import ***REMOVED***PassYourExams.R;

public class EndGameActivity extends AppCompatActivity {

    Module module;
    Lesson lesson;

    private final int UPDATE_PROGRESS = 20;

    //private InterstitialAd mInterstitialAd;
    //private final String ADMOB_ID = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_game);

        String lessonId = (String) getIntent().getExtras().getSerializable("LESSON_ID");
        module = (Module) getIntent().getExtras().getSerializable("MODULE");
        lesson = module.getLesson(lessonId);

        // Change colour of background
        ViewManager.setActivityBackground(this.getWindow().findViewById(R.id.activity_end_game), lesson.getBackgroundColour());

        // Change the notification bar colour
        ViewManager.setNotificationBarColour(this.getWindow(), lesson.getBackgroundColour());

        /*
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
                Log.i("Ads", "onAdLoaded");
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                // Code to be executed when an ad request fails.
                Log.i("Ads", "onAdFailedToLoad");
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when the ad is displayed.
                Log.i("Ads", "onAdOpened");
            }

            @Override
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
                Log.i("Ads", "onAdLeftApplication");
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when when the interstitial ad is closed.
                Log.i("Ads", "onAdClosed");
                Log.d("DEBUG", "0000P onAdClosed");
                Intent intent = new Intent(getApplicationContext(), LessonGridActivity.class);
                intent.putExtra("MODULE", module);
                startActivity(intent);
            }
        }); */

        // Set up the module icon layout
        LinearLayout layout = (LinearLayout) findViewById(R.id.container_module_icon);
        LayoutInflater inflater = LayoutInflater.from(this);
        View inflatedLayout = inflater.inflate(R.layout.lesson_icon_block, null, false);

        layout.addView(inflatedLayout);

        // Update the module icon block
        ViewManager.setLessonIconBlock(inflatedLayout, lesson);

        // Update progress
        int currentProgress = ProgressManager.getProgress(this, lessonId);
        int newProgress = 100;

        if ((currentProgress + UPDATE_PROGRESS) >= 100) {
            newProgress = 100;
        } else {
            newProgress = currentProgress + UPDATE_PROGRESS;
        }
        ProgressManager.updateSetting(this, lesson.getLessonID(), newProgress);

        // Set the progress bar
        //ViewManager.setLessonProgressBar((ProgressBar) findViewById(R.id.progressbar), lesson)
        // TODO Maybe move to ViewManager
        int progress = ProgressManager.getProgress(this, lesson.getLessonID());
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressbar);
        ProgressManager.updateProgressBar(progressBar, progress, 3000);


        // Set the text depending on progress
        TextView textView = (TextView) findViewById(R.id.text_strength);

        if (progress > 80) {
            textView.setText("You have reached the max strength!");
        } else {
            textView.setText("You have increased your strength!");
        }

    }


    public void next(View v) {

        /*if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {*/
            Log.d("DEBUG", "0000P The interstitial wasn't loaded yet.");
            Intent intent = new Intent(this, LessonGridActivity.class);
            intent.putExtra("MODULE", module);
            startActivity(intent);
        //}

    }

    // Make the back button work without changing the fragment
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)  {
        if (Integer.parseInt(android.os.Build.VERSION.SDK) > 5
                && keyCode == KeyEvent.KEYCODE_BACK
                && event.getRepeatCount() == 0) {
            Log.d("CDA", "onKeyDown Called");
            onBackPressed();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    // Make the back button work without changing the fragment
    @Override
    public void onBackPressed() {
        Log.d("CDA", "onBackPressed Called");
        Intent intent = new Intent(this, LessonGridActivity.class);
        intent.putExtra("MODULE", module);
        startActivity(intent);
    }

}
