package ***REMOVED***gcse.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import ***REMOVED***gcse.Lessons.Lesson;
import ***REMOVED***gcse.R;

public class EndGameActivity extends AppCompatActivity {

    Lesson lesson;

    private InterstitialAd mInterstitialAd;
    //private final String ADMOB_ID = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_game);

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

        LinearLayout layout = (LinearLayout) findViewById(R.id.container_module_icon);
        LayoutInflater inflater = LayoutInflater.from(this);
        View inflatedLayout = inflater.inflate(R.layout.module_icon, null, false);

        layout.addView(inflatedLayout);

        ImageView moduleIcon = (ImageView)inflatedLayout.findViewById(R.id.module_icon_image);
        TextView moduleTitle = (TextView)inflatedLayout.findViewById(R.id.module_name);

        moduleIcon.setImageResource(lesson.getLessonIcon().getIcon());
        moduleTitle.setText(lesson.getLessonIcon().getLessonName());

    }

    private void updateProgress() {


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

}
