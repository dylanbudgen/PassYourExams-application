package dylanbudgen.gcse.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import dylanbudgen.gcse.Lessons.Lesson;
import dylanbudgen.gcse.Managers.ProgressManager;
import dylanbudgen.gcse.R;

public class EndGameActivity extends AppCompatActivity {

    Lesson lesson;

    private final int UPDATE_PROGRESS = 20;

    private InterstitialAd mInterstitialAd;
    //private final String ADMOB_ID = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_game);

        // hide the toolbar
        getSupportActionBar().hide();

        lesson = (Lesson) getIntent().getExtras().getSerializable("LESSON");

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
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        // Set up the module icon layout
        LinearLayout layout = (LinearLayout) findViewById(R.id.container_module_icon);
        LayoutInflater inflater = LayoutInflater.from(this);
        View inflatedLayout = inflater.inflate(R.layout.module_icon, null, false);

        layout.addView(inflatedLayout);

        ImageView moduleIcon = (ImageView)inflatedLayout.findViewById(R.id.module_icon_image);
        TextView moduleTitle = (TextView)inflatedLayout.findViewById(R.id.module_name);

        moduleIcon.setImageResource(lesson.getLessonIcon().getIcon());
        moduleTitle.setText(lesson.getLessonIcon().getLessonName());

        // Update progress
        ProgressManager.increaseLevelProgress(this, lesson.getLessonID(), UPDATE_PROGRESS);

        // Set the progress bar
        int progress = ProgressManager.getLevelProgress(this, lesson.getLessonID());

        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressbar);
        ProgressManager.updateProgressBar(progressBar, progress);

        TextView text = (TextView) findViewById(R.id.textView_test_left);
        text.setText("Progress: " + progress);



    }


    public void next(View v) {

        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            Log.d("TAG", "The interstitial wasn't loaded yet.");
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }

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
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
