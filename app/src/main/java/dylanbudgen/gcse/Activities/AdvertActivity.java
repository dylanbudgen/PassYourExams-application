package ***REMOVED***gcse.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.google.android.gms.ads.MobileAds;

import ***REMOVED***gcse.R;

public class AdvertActivity extends AppCompatActivity {

    //Lesson lesson;

    private final String ADMOB_ID = "ca-app-pub-3940256099942544~3347511713";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advert);

        // Sample AdMob app ID: ca-app-pub-3940256099942544~3347511713
        MobileAds.initialize(this, ADMOB_ID);

    }


    public void end(View v) {

        Intent intent = new Intent(v.getContext(), MainActivity.class);
        startActivity(intent);

    }
}
