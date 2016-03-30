package com.user.navigation.Views;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.user.navigation.Constants.NavConstants;
import com.user.navigation.R;

/*
    Class to show splash screen for the application.
*/
public class SplashScreenActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.splash_layout);

        new Handler().postDelayed(new Runnable() {
            public void run() {
                startActivity(new Intent(SplashScreenActivity.this, MainMaterialActivity.class));
                finish();
            }
        }, NavConstants.SPLASH_DISPLAY_INTERVAL);

    }
}
