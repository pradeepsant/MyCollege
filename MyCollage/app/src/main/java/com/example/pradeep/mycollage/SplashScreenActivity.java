package com.example.pradeep.mycollage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

/**
 * Created by pradeep on 02/08/2016.
 */
public class SplashScreenActivity extends Activity
{
    TextView mSplashTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_layout);
        mSplashTextView = (TextView) findViewById(R.id.SplashScreenTextView);
        new Handler().postDelayed(new Runnable() {

            // Using handler with postDelayed called runnable run method

            @Override
            public void run()
            {   // Starting Login Activity
                Intent i = new Intent(SplashScreenActivity.this, LoginActivity.class);
                startActivity(i);

                // close this activity
                finish();
            }
        }, 3* 1000); // wait for 3 seconds
    }
}

